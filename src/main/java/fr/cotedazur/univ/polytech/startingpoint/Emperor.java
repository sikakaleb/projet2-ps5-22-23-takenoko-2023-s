package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.display.Display;

public class Emperor {

    private Game game;

    public Emperor(Game game) {
        this.game = game;
    }

    public Player judgement() {
        Display.printMessage("The Emperor will choose a winner...");
        Player winner = null;
        int max=0;
        for(Player p: game.getPlayerList()){
            if(max<p.getScore()){
                max= p.getScore();
                winner=p;
            }
        }
        Display.printMessage("The winner is, "
                +winner.getName()+",\nhe won with "
                +winner.getScore()+" Points.\nhis achievedObjectifsList is  :\n"
                +winner.getObjectiveAchieved()+"\nhis unMetObjectifsList is :\n"
                +winner.getUnMetObjectives());
        return winner;
    }
}
