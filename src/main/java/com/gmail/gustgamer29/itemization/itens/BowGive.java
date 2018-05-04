package com.gmail.gustgamer29.itemization.itens;

import com.gmail.gustgamer29.itemization.Item;
import com.gmail.gustgamer29.itemization.ItemBuilder;
import com.gmail.gustgamer29.itemization.ItemGiveProcess;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;


import java.util.List;

public final class BowGive extends ItemGiveProcess {

     BowGive() {
    }

    @Override
    public Item createItem(String name, List<String> lore, int amount) {
        return
        new ItemBuilder(Material.BOW).setName(name).setLore(lore).setAmount(amount).setEnchant(Enchantment.DURABILITY, 1).hideAttributes().send(getConfig().getInt("bow_slot"));
    }

//    public void giveBowToPlayer(Player playerToGive, int amoutOfArrow){
//        if(playerToGive == null){
//            throw new IllegalArgumentException("Player cannot be null!");
//        }
//        if(!UtilVerification.haveSpaceInInventory(playerToGive.getInventory())){
//            playerToGive.sendMessage(languageHandler.getStringReplaced(MessageKey.NOT_HAVE_SPACE_IN_INVENTORY.getMessage()));
//            return;
//        }
//        giveBow(playerToGive);
//        giveArrow(playerToGive, amoutOfArrow);
//    }
//
//
//    private ItemStack bowCreator(String name, List<String> lore, boolean infinity/* Arrow is Inifity? */){
//        return new Item(Material.BOW)
//                .setName(name)
//                .setLore(lore)
//                .setAmount(1)
//                .setEnchant(infinity ? Enchantment.ARROW_INFINITE : Enchantment.ARROW_DAMAGE, 1)
//                .hideAttributes().build();
//    }
//
//    private ItemStack arrowCreator(String name, List<String> lore, int amount){
//        return new Item(Material.ARROW)
//                .setName(name)
//                .setLore(lore)
//                .setAmount(amount)
//                .build();
//    }
//
//    private void giveBow(Player player){
//        Inventory inv = player.getInventory();
//        int i = configHandler.getInt("bow_slot");
//        String bowName = languageHandler.getStringReplaced(MessageKey.BOW_NAME.getMessage());
//        List<String> bowLore = languageHandler.getReplacedStringList(MessageKey.BOW_LORE.getMessage());
//        ItemStack firstItem = inv.getItem(i);
//        if(firstItem != null && !firstItem.getType().equals(Material.AIR)){
//            player.sendMessage(languageHandler.getStringReplaced(MessageKey.FIRST_SLOT_IS_BUSY.getMessage()));
//            return;
//        }
//        ItemStack bow = bowCreator(bowName, bowLore, false);
//        inv.setItem(i, bow);
//    }
//
//    private void giveArrow(Player player, int amount){
//        Inventory inv = player.getInventory();
//        int i = configHandler.getInt("arrow_slot");
//        String arrowName = languageHandler.getStringReplaced(MessageKey.ARROW_NAME.getMessage());
//        List<String> arrowLore = languageHandler.getReplacedStringList(MessageKey.ARROW_LORE.getMessage());
//        ItemStack firstItem = inv.getItem(i);
//        if(firstItem != null && !firstItem.getType().equals(Material.AIR)){
//            player.sendMessage(languageHandler.getStringReplaced(MessageKey.FIRST_SLOT_IS_BUSY.getMessage()));
//            return;
//        }
//        ItemStack bow = arrowCreator(arrowName, arrowLore, amount);
//        inv.setItem(i, bow);
//    }
}
