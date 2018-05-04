package com.gmail.gustgamer29.itemization;

import com.gmail.gustgamer29.EmpireLobby;
import com.gmail.gustgamer29.configuration.ConfigHandler;

import java.util.List;

public abstract class ItemGiveProcess{

    private ConfigHandler config;
    private ConfigHandler language;
    private Item sendItem;

    protected ItemGiveProcess() {
        this.config = EmpireLobby.getConfiguration();
        this.language = EmpireLobby.getInstance().getLanguage();
    }

    public abstract Item createItem(String name, List<String> lore, int amount);


    public Item getSendItem() {
        return sendItem;
    }

    protected ConfigHandler getConfig() {
        return config;
    }

    protected ConfigHandler getLanguage() {
        return language;
    }
}
