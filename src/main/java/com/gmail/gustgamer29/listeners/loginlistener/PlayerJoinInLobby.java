package com.gmail.gustgamer29.listeners.loginlistener;

import com.gmail.gustgamer29.itemization.itens.ProcessManager;
import com.gmail.gustgamer29.listeners.listenerutil.HandlePlayerConnection;
import com.gmail.gustgamer29.util.expiring.Duration;
import com.gmail.gustgamer29.util.expiring.ExpiringSet;
import fr.xephi.authme.events.LoginEvent;
import fr.xephi.authme.events.LogoutEvent;
import fr.xephi.authme.util.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PlayerJoinInLobby implements Listener {

    private ProcessManager manager;
    private HandlePlayerConnection handle;
    private final ExpiringSet<Player> arrowDelay = new ExpiringSet<>(300, TimeUnit.SECONDS);
    private final ExpiringSet<Player> commandDelay = new ExpiringSet<>(5, TimeUnit.SECONDS);
    private final List<Material> allowItems = Arrays.asList(Material.COMPASS, Material.ARROW, Material.BOW, Material.INK_SACK);

    public PlayerJoinInLobby(ProcessManager man, HandlePlayerConnection handle) {
       this.manager = man;
       this.handle = handle;
    }

    @EventHandler
    public void afterJoinAndLoginOrRegister(LoginEvent event){
        Player player = event.getPlayer();
        if(!player.isOnline()) return;
        handle.putPlayerToListenPackets(player);
        verifyEmailAndGiveSerializtedItens(player, event.getEmail());
        reviewInventory(player);
    }

    @EventHandler
    public void onLogOut(LogoutEvent event){
        handle.removePlayer(event.getPlayer());
    }

    @EventHandler
    public void onWhiteList(AsyncPlayerPreLoginEvent event){
        if(Bukkit.getServer().hasWhitelist()){
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_WHITELIST, "\n§6Olá! estamos em §fWhitelist§6!\n\n§7Estamos realizando uma manutenção, e §nrefazendo§r§7 todas as permissões\n§7relatados desde o dia §f15/02/2018§7\n§7O servidor estará de volta as §f14hrs§7.\n§6\n §6\\ See You Later /");
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event){
        if(event == null || event.getItem() == null) return;
        if((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)){
            if(!(event.getItem().getType() == Material.BOW)) return;
            if(!arrowDelay.contains(event.getPlayer())){
                manager.getArrowGive().sendItemWithMessage(event.getPlayer(), "§aVocê recebeu mais 5 flechas! aproveite.");
                arrowDelay.add(event.getPlayer());
                return;
            }
            if(commandDelay.contains(event.getPlayer())) return;
            Inventory inv = event.getPlayer().getInventory();
            if(inv.contains(Material.ARROW))return;
            Duration duration = arrowDelay.getExpiration(event.getPlayer());
            long expiring = duration.getDuration();
            event.setUseItemInHand(Event.Result.DENY);
            event.getPlayer().sendMessage("§6Whoops!§7 Parece que você gastou todas suas flechas§c :(");
            if(duration.getTimeUnit() == TimeUnit.SECONDS) {
                event.getPlayer().sendMessage("§7Você receberá mais em §a" + expiring + "§7 segundo(s)!");
            }else {
                event.getPlayer().sendMessage("§7Você receberá mais em §c" + expiring + "§7 minuto(s)!");
            }
            commandDelay.add(event.getPlayer());
        }
    }

    private void verifyEmailAndGiveSerializtedItens(Player player, String email){
        manager.getDye().sendItemToPlayer(player);
        if(!Utils.isEmailEmpty(email)){
            manager.getBowGive().sendItemToPlayer(player);
            if(!arrowDelay.contains(player)) {
                manager.getArrowGive().sendItemToPlayer(player);
                arrowDelay.add(player);
            }
        }
    }
    private void reviewInventory(Player pl){
        Inventory inv = pl.getInventory();
        if(inv != null && inv.getContents().length != 0){
            List<ItemStack> ind = Arrays.asList(inv.getContents());
            ind.forEach(i -> {
                if(i == null) return;
                if(!allowItems.contains(i.getType())){
                    inv.remove(i);
                }
            });
        }
    }
}
