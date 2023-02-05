package fr.cotedazur.univ.polytech.startingpoint;

public class Emperor {

    private Game game;

    public Emperor(Game game) {
        this.game = game;
    }

    public Player judgement() {
        System.out.println("The Emperor will choose a winner...");
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
