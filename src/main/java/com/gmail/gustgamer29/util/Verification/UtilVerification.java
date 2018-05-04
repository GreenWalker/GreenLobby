package com.gmail.gustgamer29.util.Verification;

import org.bukkit.inventory.Inventory;

public final class UtilVerification {

    public static boolean haveSpaceInInventory(Inventory inventory){
        return inventory.firstEmpty() != -1;

    }

}
