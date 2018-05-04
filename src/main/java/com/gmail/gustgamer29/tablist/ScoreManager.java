package com.gmail.gustgamer29.tablist;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ScoreManager {

    private Scoreboard score;
    private Team team;

    public ScoreManager(Scoreboard score) {
        this.score = score;
        team = score.getTeam("HIDED");
        if(team == null)score.registerNewTeam("HIDED");
        team.setPrefix("");
        team.setSuffix("§7 [ESCONDIDO]");
    }

    public void addPlayer(Player player){
        team.addPlayer(player);
    }

    public void removePlayer(Player player){
        team.removePlayer(player);
        team.setCanSeeFriendlyInvisibles(false);
    }
}
