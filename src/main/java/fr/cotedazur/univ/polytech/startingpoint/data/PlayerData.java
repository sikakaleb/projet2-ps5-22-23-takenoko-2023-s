package fr.cotedazur.univ.polytech.startingpoint.data;

import static fr.cotedazur.univ.polytech.startingpoint.Main.ITERATIONS;
import static fr.cotedazur.univ.polytech.startingpoint.Main.ties;

public class PlayerData {

    private int wins;
    private double points;

    public void win() {
        this.wins ++;
    }

    public void score(int points) {
        this.points += points;
    }

    public int getWins(){
        return this.wins;
    }

    public int getLosses(){
        return ITERATIONS - ties - this.wins;
    }

    public double getAvgPoints() {
        return this.points/ITERATIONS;
    }

}
