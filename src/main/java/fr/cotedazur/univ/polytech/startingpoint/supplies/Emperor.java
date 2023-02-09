package fr.cotedazur.univ.polytech.startingpoint.supplies;

import fr.cotedazur.univ.polytech.startingpoint.gameplay.Game;
import fr.cotedazur.univ.polytech.startingpoint.gameplay.Player;
import fr.cotedazur.univ.polytech.startingpoint.display.Display;

import java.util.Comparator;

public class Emperor {

    private Game game;

    public Emperor(Game game) {
        this.game = game;
    }

    public Player judgement() {
        Display.printMessage("L'empereur va determiner le gagnant...");
        game.getPlayerList().sort(Comparator.comparing(Player::getScore).reversed());

        if (game.getPlayerList().get(0).getScore() > game.getPlayerList().get(1).getScore()) {

            Display.printMessage("Le gagnant est " + game.getPlayerList().get(0).getName() + " \uD83D\uDC4F\uD83D\uDC4F ");
            game.display();
            return game.getPlayerList().get(0);
        }

        else {
            Display.printMessage(game.getPlayerList().get(0).getName() +" et "
                    + game.getPlayerList().get(1).getName() +
                    " sont ex aequo avec " + game.getPlayerList().get(0).getScore() + " points.");

            return null;
        }
    }
}
