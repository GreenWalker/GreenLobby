package com.gmail.gustgamer29.itemization;

import com.gmail.gustgamer29.EmpireLobby;
import com.gmail.gustgamer29.util.MessageKey;
import com.gmail.gustgamer29.util.Verification.UtilVerification;

import org.apache.commons.lang.Validate;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Item {

    private ItemStack item;
    private int postion;

    public Item(ItemStack item, int postion) {
        this.item = item;
        this.postion = postion;
    }

    public void setPostion(int postion) {
        this.postion = postion;
    }

    public void sendItemWithMessage(Player player, String msg) {
        this.sendItemToPlayer(player);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }

    public void sendItemWithMessage(Player player, int pos, String msg) {
        this.sendItemToPlayer(player, pos);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }

    public void sendItemToPlayer(Player player) {
        Inventory inv = player.getInventory();
        int firstPosition = inv.firstEmpty();
        sendItemToPlayer(player, checkPosition() ? postion : firstPosition);
    }

    public void sendItemToPlayer(Player player, int pos) {
        Validate.notNull(player, "Receiver Player cannot be null!");
        if (!UtilVerification.haveSpaceInInventory(player.getInventory())) {
            Logger.getLogger("Minecraft").log(Level.INFO, "Player %p not have blank space in inventory!".replaceAll("%p", player.getName()));
            player.sendMessage(EmpireLobby.getInstance().getLanguage().getStringReplaced(MessageKey.NOT_HAVE_SPACE_IN_INVENTORY.getMessage()));
            return;
        }
        player.getInventory().setItem(pos, this.item);
    }

    public ItemBuilder toItemBuilder(){
        return new ItemBuilder(item);
    }

    private boolean checkPosition() {
        return postion != -1;
    }

    public ItemStack toItemStack() {
        return this.item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

}
