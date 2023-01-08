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
        System.out.println("The winner is ,"
                +winner.getName()+" gagne avec "
                +winner.getCumulOfpoint()+" sa liste d'objectif validé est "
                +winner.getObjectiveAchieved()+" sa liste d'objectif restant est "
                +winner.getUnMetObjectives());
    }
}
