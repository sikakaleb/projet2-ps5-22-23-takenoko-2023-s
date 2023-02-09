package fr.cotedazur.univ.polytech.startingpoint.data;


import fr.cotedazur.univ.polytech.startingpoint.tools.Strategy;

import java.util.Objects;

public class BotStat {
    private String name;
    private Strategy strategy;
    private int gamesPlayed;
    private int wins;
    private int ties;
    private int losses;
    private double points;
    private double averageScore;

    public BotStat(String name, Strategy strategy, int gamesPlayed, int wins, int ties, int losses, double points) {
        this.name = name;
        this.strategy=strategy;
        this.gamesPlayed = gamesPlayed;
        this.wins = wins;
        this.ties = ties;
        this.losses = losses;
        this.points = points;
    }

    public BotStat() {
        this.name = "Bot";
        this.strategy = Strategy.WITHOUTSTRATEGY;
        this.gamesPlayed = 0;
        this.wins = 0;
        this.ties = 0;
        this.losses = 0;
        this.points = 0;
    }

    public BotStat(String name, Strategy strategy) {
        this.name = name;
        this.strategy = strategy;
        this.gamesPlayed = 0;
        this.wins = 0;
        this.ties = 0;
        this.losses = 0;
        this.points = 0;
    }

    public String getName(){
        return this.name;
    }

    public Strategy getStrategy() {
        return strategy;
    }
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getWins(){
        return this.wins;
    }

    public int getTies(){
        return this.ties;
    }

    public int getLosses(){
        return this.losses;
    }

    public double getPoints() {
        return points;
    }
    public void played() {
        this.gamesPlayed ++;
    }
    public void win() {
        this.wins ++;
    }
    public void score(int points) {
        this.points += points;
    }

    public double updateAverageScore() {
        return this.averageScore = this.points/gamesPlayed;
    }
    public double getAverageScore() {
        return averageScore;
    }
    public void tie() { this.ties ++; }

    @Override
    public String toString() {
        return "BotStat [" +
                "name=" + name +
                ", strategy=" + strategy +
                ", gamesPlayed=" + gamesPlayed +
                ", wins=" + wins +
                ", ties=" + ties +
                ", losses=" + losses +
                ", points=" + points +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BotStat botStat)) return false;
        return strategy.equals(botStat.getStrategy()) && name.equals(botStat.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getStrategy(), getGamesPlayed(), getWins(), getTies(), getLosses(), getPoints());
    }

    public void addGameStat(BotStat botStat) {
        this.gamesPlayed += botStat.getGamesPlayed();
        this.wins += botStat.getWins();
        this.ties += botStat.getTies();
        this.losses += botStat.getLosses();
        this.points += botStat.getPoints();
    }

    public void lose() {
        this.losses ++;
    }
}

