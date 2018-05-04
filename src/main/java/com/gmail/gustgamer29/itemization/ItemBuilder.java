package com.gmail.gustgamer29.itemization;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class ItemBuilder {

    private ItemStack item;

    public ItemBuilder(ItemStack item) {
        this.item = item;
    }

    public ItemBuilder(Material mat){
        this.item = new ItemStack(mat);
    }

    public ItemBuilder(String matname){
        this.item = new ItemStack(Material.valueOf(matname));
    }

    public ItemBuilder setName(String name){
        ItemMeta meta = this.item.getItemMeta();
        meta.setDisplayName(name.replaceAll("&", "§"));
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setLore(List<String> lore){
        if(lore == null) return this;
        ItemMeta meta = this.item.getItemMeta();
        for(int i = 0; i < lore.size(); i++){
            lore.set(i, lore.get(i).replaceAll("&", "§"));
        }
        meta.setLore(lore);
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setLore(String ... lore){
        if(lore == null) return this;
        ItemMeta meta = this.item.getItemMeta();
        for(int i = 0; i < lore.length; i++){
            lore[i] = (lore[i].replaceAll("&", "§"));
        }
        meta.setLore(Arrays.asList(lore));
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setEnchant(Enchantment enchant, int lvl){
        this.item.addUnsafeEnchantment(enchant, lvl);
        return this;
    }

    public ItemBuilder setEnchants(int lvl, List<? extends Enchantment> enchant){
        if(enchant == null) {
            return this;
        }
        enchant.forEach(ench -> setEnchant(ench, lvl));
        return this;
    }

    public ItemBuilder setEnchants(int lvl, Enchantment ... enchants){
        if(enchants == null){
            return this;
        }
        List<Enchantment> enchant = Arrays.asList(enchants);
        setEnchants(lvl, enchant);
        return this;
    }

    public ItemBuilder setAmount(int amount){
        if(amount < 1 || amount > 64){
            amount = 1;
        }
        this.item.setAmount(amount);
        return this;
    }

    public ItemBuilder hideAttributes(){
        ItemMeta item = this.item.getItemMeta();
        item.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_DESTROYS);
        this.item.setItemMeta(item);
        return this;
    }

    public ItemBuilder setDurability(int d){
        this.item.setDurability((short) d);
        return this;
    }

    public String getItemName(){
        if(this.item.hasItemMeta() && this.item.getItemMeta().hasDisplayName()){
            return this.item.getItemMeta().getDisplayName();
        }
        return "";
    }

    public ItemBuilder getItem(){
        return this;
    }

    public ItemStack build(){
        return item;
    }

    public Item send(int position){
        return new Item(item, position);
    }

}
