package com.gmail.gustgamer29.listeners.listenerutil;

import io.netty.channel.*;
import io.netty.util.internal.ConcurrentSet;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class HandlePlayerConnection {

    private static final Set<Player> hidedPlayer = new ConcurrentSet<>();

    public void putPlayerToListenPackets(Player player){
        ChannelDuplexHandler channel = new ChannelDuplexHandler(){
            @Override
            public void write(ChannelHandlerContext context, Object packet, ChannelPromise promise) throws Exception{
                if(packet instanceof PacketPlayOutPlayerInfo){
                    if(hidedPlayer.contains(player)) {
                        return;
                    }
                }
                if(packet instanceof PacketPlayOutTabComplete){
                    PacketPlayOutTabComplete tab = (PacketPlayOutTabComplete) packet;
                    player.sendMessage("§4 ERROR 404 - ERA O QUE VOCÊ ESTAVA PROCURANDO? ");

                }
                super.write(context, packet, promise);
            }

            @Override
            public void channelRead(ChannelHandlerContext channelHandlerContext, Object packet) throws Exception {
                if(packet instanceof PacketPlayInTabComplete){
                    PacketPlayInTabComplete c = (PacketPlayInTabComplete) packet;
                    List<String> string = Arrays.asList("/plugin ", "/? ");
                    //System.out.println(string.contains(c.a()));
                    //player.sendMessage("Teste 2 " + packet.toString()
                    if(!string.contains(c.a())){
                    return;
                    }
                }
                if(packet instanceof PacketPlayInChat){
                    PacketPlayInChat chat = (PacketPlayInChat) packet;
                    //System.out.println(chat.a() + " " + chat.a().startsWith("/"));
                    if(!chat.a().startsWith("/")) {
                        player.sendMessage("§CVocê não pode falar neste chat!");
                        return;
                    }
                }
                super.channelRead(channelHandlerContext, packet);
            }
        };
        ChannelPipeline pipeline = ((CraftPlayer) player).getHandle().playerConnection.networkManager.channel.pipeline();
        pipeline.addBefore("packet_handler", player.getName(), channel);
    }

    public void removePlayer(Player player){
        Channel ch = ((CraftPlayer) player).getHandle().playerConnection.networkManager.channel;
        ch.eventLoop().submit(() -> {
            ch.pipeline().remove(player.getName());
            return null;
        });
    }

    public static Set<Player> getHidedPlayer() {
        return hidedPlayer;
    }

}
