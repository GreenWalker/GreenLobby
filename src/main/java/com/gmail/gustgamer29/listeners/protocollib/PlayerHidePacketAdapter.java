//package com.gmail.gustgamer29.listeners.protocollib;
//
//import com.comphenix.protocol.PacketType;
//import com.comphenix.protocol.ProtocolLibrary;
//import com.comphenix.protocol.events.ListenerPriority;
//import com.comphenix.protocol.events.PacketAdapter;
//import com.comphenix.protocol.events.PacketEvent;
//import com.google.common.collect.Sets;
//import org.bukkit.plugin.Plugin;
//
//import java.util.Set;
//
//public class PlayerHidePacketAdapter extends PacketAdapter {
//
//    private Set<String> toHide = Sets.newHashSet();
//
//
//    public PlayerHidePacketAdapter(Plugin plugin) {
//        super(plugin, ListenerPriority.NORMAL, PacketType.Play.Server.PLAYER_INFO);
//    }
//
//    @Override
//    public void onPacketReceiving(PacketEvent event) {
//        if(event.getPacketType() == PacketType.Play.Server.PLAYER_INFO){
//
//        }
//    }
//
//    public void registerPacktEvent(){
//        ProtocolLibrary.getProtocolManager().addPacketListener(this);
//    }
//}
