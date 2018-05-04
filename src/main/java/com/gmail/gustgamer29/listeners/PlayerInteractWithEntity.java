package com.gmail.gustgamer29.listeners;

import com.gmail.gustgamer29.util.expiring.ExpiringSet;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

public class PlayerInteractWithEntity implements Listener {

    private static final Map<String, Integer> playerClickCounter = new TreeMap<>();
    private static final ExpiringSet<String> exaustion = new ExpiringSet<>(10, TimeUnit.SECONDS);
    private Plugin pl;

    public PlayerInteractWithEntity(Plugin pl) {
        this.pl = pl;
    }

    @EventHandler
    public void onInteractWithAnotherEntityAndShootThis(PlayerInteractAtEntityEvent event){
        Player player = event.getPlayer();
        Entity entityToEject = event.getRightClicked();

        if(player == null) return;

        if(entityToEject == null) return;

        Predicate<ExpiringSet<String>> predicate = e -> e.contains(player.getName());

        if(predicate.test(exaustion)){
            player.sendMessage("§aVocê está um pouco cansado para fazer isso agora..");
            return;
        }

        if(!hasCliking(player)){
            verifyClicks(player);
            scheduleClicks(pl, player, 10, entityToEject);
        }
        addClick(player);
    }

    private void verifyClicks(Player player){
        String name = player.getName();
        if(!playerClickCounter.containsKey(name)){
            playerClickCounter.put(name, 0);
        }
            player.sendMessage("§cClique o máximo que puder!");
    }

    private int getClicks(Player player){
        return hasCliking(player) ? playerClickCounter.get(player.getName()) : 0;
    }

    private boolean hasCliking(Player player){
        return playerClickCounter.containsKey(player.getName());
    }

    private void addClick(Player player){
        if(hasCliking(player)){
            int i = getClicks(player);
            playerClickCounter.put(player.getName(), i + 1);
        }
    }

    private void playerExaustion(Player player){
        if(!exaustion.contains(player.getName())){
            exaustion.add(player.getName());
        }
    }

    private void scheduleClicks(Plugin pl, Player player, int secs, Entity interactEntity) {
        if (!hasCliking(player)) return;
        new BukkitRunnable() {
            @Override
            public void run() {
                int i = getClicks(player);
                double launch = i / 5;
                playerClickCounter.remove(player.getName());
                player.sendMessage("§aVocê clicou §c%i§a vezes! lançando %e a §c%d§a blocos!"
                        .replaceAll("%i", String.valueOf(i))
                        .replaceAll("%d", String.valueOf(launch))
                        .replaceAll("%e", interactEntity.getName().toLowerCase()));
                launchEntity(interactEntity, (int) launch);
                playerExaustion(player);

                if(interactEntity instanceof Player){
                    Player pl = (Player) interactEntity;
                    pl.sendMessage("§cVocê foi arremesado a §e" + launch + "§c blocos por §e" + player.getName());
                }
                cancel();
            }
        }.runTaskLater(pl, secs * 20);

    }

    private void launchEntity(Entity entity, int blocks){
        if(entity != null){
            entity.setVelocity(new Vector(entity.getVelocity().getX(), entity.getVelocity().getY() + blocks, entity.getVelocity().getZ()));
        }
    }

}
