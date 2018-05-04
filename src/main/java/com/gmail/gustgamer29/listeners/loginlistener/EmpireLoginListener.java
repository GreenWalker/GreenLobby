package com.gmail.gustgamer29.listeners.loginlistener;

import com.gmail.gustgamer29.EmpireLobby;
import com.gmail.gustgamer29.configuration.ConfigHandler;
import com.gmail.gustgamer29.itemization.itens.ProcessManager;
import fr.xephi.authme.events.EmailAddEvent;
import fr.xephi.authme.events.EmailChangeEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.Arrays;

public class EmpireLoginListener implements Listener {

    private ProcessManager manager;

    public EmpireLoginListener(ProcessManager manager) {
        this.manager = manager;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onAddEmail(EmailAddEvent event){
        Player player = event.getPlayer();
        if(!event.isSuccefullAdded()){
            player.sendMessage("§cVocê não receberá seu arco, pois o email não foi adicionado com sucesso!");
            return;
        }
        manager.getBowGive().sendItemToPlayer(player);
        manager.getArrowGive().sendItemToPlayer(player);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onChangeEmail(EmailChangeEvent event){
        Player player = event.getPlayer();
        if(!event.isSuccefullChanged()){
            player.sendMessage("§cVocê não receberá seu arco, pois o email não foi alterado com sucesso!");
            return;
        }
        manager.getBowGive().sendItemToPlayer(player);
    }

}
