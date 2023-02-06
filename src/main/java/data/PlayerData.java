package data;

public class PlayerData {

    private int games = 1000;
    private int win;
    //private int loss;
    //private int tie;
    private int points;

    public void win() {
        this.win ++;
    }

    public void score(int points) {
        this.points += points;
    }

    public int getWins(){
        return this.win;
    }

    public int getLosses(){
        return games-this.win;
    }

    public double getAvgPoints() {
        return points/games;
    }
}
