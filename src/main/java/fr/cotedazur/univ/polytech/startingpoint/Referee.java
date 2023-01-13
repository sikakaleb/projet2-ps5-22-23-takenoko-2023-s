package fr.cotedazur.univ.polytech.startingpoint;

public class Referee {

    private Game game;

    public Referee(Game game) {
        this.game = game;
    }

    public void judgement() {
        Player winner = null;
        int max=0;
        for(Player p: game.getPlayerList()){
            if(max<p.getCumulOfpoint()){
                max= p.getCumulOfpoint();
                winner=p;
            }
        }
        System.out.println("The winner is, "
                +winner.getName()+",\nhe won with "
                +winner.getCumulOfpoint()+" Points.\nhis achievedObjectifsList is  :\n"
                +winner.getObjectiveAchieved()+"\nhis unMetObjectifsList is :\n"
                +winner.getUnMetObjectives());
    }
}
