package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.display.Display;

import java.util.Comparator;

public class Emperor {

    private Game game;

    public Emperor(Game game) {
        this.game = game;
    }

    public Player judgement() {
        Display.printMessage("L'empereur va déterminer le gagnant...");
        game.playerList.sort(Comparator.comparing(Player::getScore).reversed());

        if (game.playerList.get(0).getScore() > game.playerList.get(1).getScore()) {

            Display.printMessage("The winner is, " + game.playerList.get(0).getName() +
                    ", with " + game.playerList.get(0).getScore() + " points and " +
                    + game.playerList.get(0).getObjectiveAchieved().size() + " achieved objectives");

            return game.playerList.get(0);
        }

        else {
            Display.printMessage(game.playerList.get(0).getName() +" et "
                    + game.playerList.get(0).getName() +
                    " sont ex aequo avec " + game.playerList.get(0).getScore() + " points.");

            return null;
        }
        System.out.println("The winner is, "
                +winner.getName()+",\nhe won with "
                +winner.getScore()+" Points.\nhis achievedObjectifsList is  :\n"
                +winner.getObjectiveAchieved()+"\nhis unMetObjectifsList is :\n"
                +winner.getUnMetObjectives());
        return winner;
    }
}
