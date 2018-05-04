package com.gmail.gustgamer29;

import com.google.common.collect.ImmutableList;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Command implements CommandExecutor, TabCompleter {

    private final List<String> ann = Arrays.asList("Cacchorro", "Gato", "Passarinho", "Ave", "Avestruz", "Ornitorrinco", "Boi", "Bode",
            "Burro", "Cavalo", "Egua", "Viado", "Carneiro", "Porco", "Baleia", "Tubrao", "Peixe", "Piranha", "Escorpiao", "Aranha",
            "Lagarto", "Leao", "Tigre", "Zebra", "Pantera", "Leopardo", "Macaco", "Voce", "Homem", "Cobra", "Ovelha", "Rato", "Aguia",
            "Borboleta", "Jacare", "Hipopotamo", "Mamute", "NaoUseDrogas", "NaoPerdeuNadaAqui", "Kraken", "Esquilo", "Galinha", "Galo");

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] args) {
        if (args.length == 1) {
            List<String> matchedTopics = new ArrayList<String>();
            String searchString = args[0];
            for (String topic : animalsList()) {
                if (topic.startsWith(searchString)) {
                    matchedTopics.add(topic);
                }
            }
                    Collections.sort(matchedTopics);
            return matchedTopics;
        }
        return ImmutableList.of();
    }

    private List<String> animalsList(){
        return ann;
    }
}
