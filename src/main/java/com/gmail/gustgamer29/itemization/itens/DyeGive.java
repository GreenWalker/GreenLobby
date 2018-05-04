package com.gmail.gustgamer29.itemization.itens;

import com.gmail.gustgamer29.itemization.Item;
import com.gmail.gustgamer29.itemization.ItemBuilder;
import com.gmail.gustgamer29.itemization.ItemGiveProcess;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import java.util.List;

public class DyeGive extends ItemGiveProcess{

    DyeGive() {
    }

    @Override
    public Item createItem(String name, List<String> lore, int amount) {
        return new ItemBuilder(Material.INK_SACK)
                .setDurability(8)
                .setName(name)
                .setLore(lore)
                .setAmount(amount)
                .setEnchant(Enchantment.DURABILITY, 1)
                .hideAttributes()
                .send(getConfig()
                        .getInt("dye_slot"));
    }
}
