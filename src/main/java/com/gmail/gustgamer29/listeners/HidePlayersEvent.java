package com.gmail.gustgamer29.listeners;

//import com.comphenix.protocol.ProtocolManager;

import com.gmail.gustgamer29.EmpireLobby;
import com.gmail.gustgamer29.listeners.listenerutil.HandlePlayerConnection;
//import com.gmail.gustgamer29.listeners.protocollib.PlayerHidePacketAdapter;
import com.gmail.gustgamer29.tablist.ScoreManager;
import com.gmail.gustgamer29.util.expiring.Duration;
import com.gmail.gustgamer29.util.expiring.ExpiringSet;
import io.netty.util.internal.ConcurrentSet;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.concurrent.TimeUnit;

public class HidePlayersEvent implements Listener {


    private final ExpiringSet<String> useTooQuickly = new ExpiringSet<>(4, TimeUnit.SECONDS);
    private final ScoreManager manager;

    public HidePlayersEvent(ScoreManager manager) {
     this.manager = manager;
    }

    @EventHandler
    public void onInteractWithItem(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (item == null) return;
        if (!player.isOnline()) return;
        if (item.getType() != Material.INK_SACK) return;

        if (useTooQuickly.contains(player.getName())) {
            Duration duration = useTooQuickly.getExpiration(player.getName());
            player.sendMessage("§aVocê deve esperar §c%i §asegundos para usar novamente!".replaceAll("%i", String.valueOf(duration.getDuration())));
            return;
        }
        short data = item.getDurability();
        if (data == 8) {
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§c§lMostrar Jogadores §7§o(Botão Direito)");
            item.setItemMeta(meta);
            item.setDurability((short) 10);
            EmpireLobby.getReflect().getNmsVersion().sendActionBar(player, "§cVocê escondeu os outros jogadores!");

            Bukkit.getServer().getOnlinePlayers().forEach(pl -> {
                if(player.equals(pl)) return;
                hidePlayer(player, pl);
            });
            HandlePlayerConnection.getHidedPlayer().add(player);
        } else if (data == 10) {
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§a§lEsconder os Jogadores §7§o(Botão Direito) (Não afeta o Tab)");
            item.setItemMeta(meta);
            item.setDurability((short) 8);
            EmpireLobby.getReflect().getNmsVersion().sendActionBar(player, "§aAgora você verá outros jogadores!");
            Bukkit.getServer().getOnlinePlayers().forEach(pl -> showPlayer(player, pl));
            HandlePlayerConnection.getHidedPlayer().remove(player);
        }
        useTooQuickly.add(player.getName());
    }

    private void hidePlayer(Player player, Player hideTo) {
//        CraftPlayer cp = ((CraftPlayer) player);
//        PacketPlayOutPlayerInfo packetInfo = new PacketPlayOutPlayerInfo(
//                PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, cp.getHandle());
//        cp.sendMessage("Invisivel");
//        sendPacket(((CraftPlayer) hideTo), packetInfo);
        player.hidePlayer(hideTo);
        //manager.addPlayer(hideTo);
    }

    private void showPlayer(Player player, Player showTo) {
//        CraftPlayer cp = (CraftPlayer) player;
//        PacketPlayOutPlayerInfo playOutPlayerInfo = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, cp.getHandle());
//        cp.sendMessage("Visivel");
//        sendPacket(((CraftPlayer) showTo), playOutPlayerInfo);
        player.showPlayer(showTo);
        //manager.removePlayer(showTo);
    }

    private void sendPacket(CraftPlayer player, Packet packet) {
        player.getHandle().playerConnection.sendPacket(packet);
    }

}
