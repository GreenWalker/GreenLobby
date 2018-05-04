package com.gmail.gustgamer29.itemization.itens;

import com.gmail.gustgamer29.EmpireLobby;
import com.gmail.gustgamer29.itemization.Item;
import com.gmail.gustgamer29.itemization.itens.BowGive;
import org.bukkit.enchantments.Enchantment;

import java.util.Arrays;

public class ProcessManager {

    private BowGive bowGive;
    private DyeGive dyeGive;
    private ArrowGive arrowGive;

    private static final int BOW_POS = 1;
    private static final int ARROW_POS = 18;
    private static final int DYE_POS = 6;


    public ProcessManager(EmpireLobby plugin) {
        this.bowGive = new BowGive();
        this.dyeGive = new DyeGive();
        this.arrowGive = new ArrowGive();

    }

    public Item getArrowGive() {
        return arrowGive.createItem("&6&lFlecha &f&ldo Teleporte", Arrays.asList("§7Estas flechas podem te teleportar", "&7Para onde ele cair!"), 5);
    }

    public Item getBowGive(){
        return bowGive.createItem("&6&lArco &f&ldo Teleporte", Arrays.asList("§7Com este arco de Flechas limitadas você", "&7Pode se &6teletransportar &7para onde a flecha cair!"), 1);
    }

    public Item getDye(){
        return
                dyeGive.createItem("&a&lEsconder os Jogadores &7&o(Botão Direito) (Não afeta o Tab)", null, 1);
    }

}
