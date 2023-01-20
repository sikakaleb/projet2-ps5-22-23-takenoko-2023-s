package fr.cotedazur.univ.polytech.startingpoint;

public class Referee {

    private Game game;

    public Referee(Game game) {
        this.game = game;
    }

    public Player judgement() {
        Player winner = null;
        int max=0;
        for(Player p: game.getPlayerList()){
            if(max<p.getScore()){
                max= p.getScore();
                winner=p;
            }
        }
        System.out.println("The winner is, "
                +winner.getName()+",\nhe won with "
                +winner.getScore()+" Points.\nhis achievedObjectifsList is  :\n"
                +winner.getObjectiveAchieved()+"\nhis unMetObjectifsList is :\n"
                +winner.getUnMetObjectives());
        return winner;
    }
}
