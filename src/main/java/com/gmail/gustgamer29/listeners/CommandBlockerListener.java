package com.gmail.gustgamer29.listeners;

import com.gmail.gustgamer29.configuration.ConfigHandler;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CommandBlockerListener implements Listener {

    private static ConfigHandler allowedCmds;

    public CommandBlockerListener(Plugin pl) {
        try {
            allowedCmds = new ConfigHandler(pl, "allow-commands.yml");
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event){
        Set<String> stringSet = new HashSet<>();
        stringSet.addAll(allowedCmds.getStringList("allowed"));
        if(stringSet.isEmpty()) event.setCancelled(true);
        String comm = event.getMessage();
        if(comm == null) return;

        if(comm.split(" ")[0].contains(":")){
            //System.out.println("Player tryed use short command!");
            event.setCancelled(true);
            return;
        }
        if(comm.split(" ").length > 1 && comm.split(" ")[1] != null) comm = comm.split(" ")[0];
        for(String s : stringSet){
           if(comm.equalsIgnoreCase(s)){
               System.out.println(s);
               return;
           }
        }
        event.setCancelled(true);
    }
}
