package com.gmail.gustgamer29.itemization.itens;

import com.gmail.gustgamer29.itemization.Item;
import com.gmail.gustgamer29.itemization.ItemBuilder;
import com.gmail.gustgamer29.itemization.ItemGiveProcess;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import java.util.List;

public class ArrowGive extends ItemGiveProcess{
    @Override
    public Item createItem(String name, List<String> lore, int amount) {
        return new ItemBuilder(Material.ARROW).setName(name).setLore(lore).setAmount(amount).setEnchant(Enchantment.DURABILITY, 1).hideAttributes().send(getConfig().getInt("arrow_slot"));
    }
}
