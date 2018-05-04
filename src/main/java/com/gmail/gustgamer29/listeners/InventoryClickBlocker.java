package com.gmail.gustgamer29.listeners;

import com.gmail.gustgamer29.itemization.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

import javax.annotation.Priority;

public class InventoryClickBlocker implements Listener {

    @EventHandler
    public void player(InventoryMoveItemEvent event){
        event.setCancelled(true);
        if(event.getDestination() != null){
            System.out.println("Sourcer " + (event.getSource() != null ? event.getSource().getName() : "Sourcer is NUll"));
            System.out.println("Initiator " + (event.getInitiator() != null ? event.getInitiator().getName() : "iNITIATOR is NUll"));
        }
    }

    @EventHandler
    public void playerDropItem(PlayerDropItemEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void playerD(InventoryClickEvent event){
        event.setResult(Event.Result.DENY);
        event.setCancelled(true);
    }

    @EventHandler
    public void playerD(InventoryDragEvent event){
        event.setResult(Event.Result.DENY);
        event.setCancelled(true);
    }

}
