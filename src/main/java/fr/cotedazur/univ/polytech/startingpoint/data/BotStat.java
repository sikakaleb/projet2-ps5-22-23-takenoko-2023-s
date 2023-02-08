package fr.cotedazur.univ.polytech.startingpoint.data;


import fr.cotedazur.univ.polytech.startingpoint.Player;

import java.util.Objects;

public class BotStat {
    //private Player bot;
    private String name;
    private int gamesPlayed;
    private int wins;
    private int losses;

    public BotStat() {
    }

    public BotStat(Player bot, int gamesPlayed, int wins, int losses) {
        this.name=bot.getName();
        //this.bot = bot;
        this.gamesPlayed = gamesPlayed;
        this.wins = wins;
        this.losses = losses;
    }

    public String getBotName(){
        return this.name;
    }

    /*public void setBot(Player bot) {
        this.bot = bot;
    }*/

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    @Override
    public String toString() {
        return "BotStat [" +
                "name=" + getBotName() +
                ", gamesPlayed=" + gamesPlayed +
                ", wins=" + wins +
                ", losses=" + losses +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BotStat botStat)) return false;
        return getGamesPlayed() == botStat.getGamesPlayed() && getWins() == botStat.getWins() && getLosses() == botStat.getLosses() && name.equals(botStat.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, getGamesPlayed(), getWins(), getLosses());
    }
}

