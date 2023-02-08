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
        game.playerList.sort(Comparator.comparing(Player::getScore).reversed());

        if (game.playerList.get(0).getScore() > game.playerList.get(1).getScore()) {

            Display.printMessage("Le gagnant est " + game.playerList.get(0).getName() + " \uD83D\uDC4F\uD83D\uDC4F ");
            game.display();
            return game.playerList.get(0);
        }

        else {
            Display.printMessage(game.playerList.get(0).getName() +" et "
                    + game.playerList.get(1).getName() +
                    " sont ex aequo avec " + game.playerList.get(0).getScore() + " points.");

            return null;
        }
    }
}
