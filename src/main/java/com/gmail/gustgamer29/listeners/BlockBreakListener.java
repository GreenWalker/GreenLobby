package com.gmail.gustgamer29.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void blockBreak(BlockBreakEvent event){
        if(!event.getPlayer().isOp()){
            event.setCancelled(true);
        }
    }
}
