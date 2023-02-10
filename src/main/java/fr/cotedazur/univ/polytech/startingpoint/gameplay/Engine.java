package fr.cotedazur.univ.polytech.startingpoint.gameplay;

import fr.cotedazur.univ.polytech.startingpoint.data.BotStat;
import fr.cotedazur.univ.polytech.startingpoint.display.Display;
import fr.cotedazur.univ.polytech.startingpoint.supplies.Emperor;
import java.util.Collections;

import java.util.List;
import java.util.Map;

import static fr.cotedazur.univ.polytech.startingpoint.gameplay.Main.*;
import static fr.cotedazur.univ.polytech.startingpoint.tools.Action.GameAction.MOVE_GARDENER;
import static fr.cotedazur.univ.polytech.startingpoint.tools.Action.GameAction.MOVE_PANDA;
import static fr.cotedazur.univ.polytech.startingpoint.tools.Strategy.*;

public class Engine {


    private Player p1;
    private Player p2;

    private Map<Integer, Integer> objectivesForNbPlayers = Map.of(
            2, 9,
            3, 8,
            4, 7
    );
    private int nbObjectivesToWin;

    /** Dans notre version, avec des bots peu intelligents,pour éviter que la partie
    * ne soit interminable, on fixe nombre de tours prédéterminé :**/
    private int maxRounds;
    private int nbRound ;

    public Engine(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        maxRounds=50;
        nbRound=0;
    }

    /*
     * JeReflechis() utilisé pour marquer un temps de pause
     * la transition entre les tours de jeu de chaque joueurs
     */
    public static void jeReflechis() {
        try {
            for (int i = 0; i < 6; i++) {
                Thread.sleep(6);
            }
        } catch (InterruptedException e) {
            Display.printMessage("Erreur d'interruption : " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    public Map<Player, BotStat> runGame(Game game, boolean stats){
        Boolean loop = true;
        Boolean lastRound = false;
        Emperor emperor = new Emperor(game);
        nbObjectivesToWin = objectivesForNbPlayers.get(game.getPlayerList().size());
        List<Player> playerList = game.getPlayerList();

        Display.printMessage("---------------BEGIN----------------");
        while (Boolean.TRUE.equals(nbRound < maxRounds && loop)){

            loop = !lastRound;

            for(Player p : playerList ){
                Display.printMessage("");

                if (p.getObjectiveAchieved().size() == nbObjectivesToWin) {
                    Display.printMessage( "Dernier tour ! "+p.getName()+" a valide "+nbObjectivesToWin+" objectifs.");
                    p.pickEmperor();
                    lastRound = true;
                }

                Display.printMessage("C'est le tour de : " + p.getName());
                if (Boolean.TRUE.equals(game.play(p))) game.display();

                if (nbRound==0  && p.getStrategy()==Fa3STRATEGY){
                    Display.printMessage(p.getStrategy().getActions().toString());
                    p.getStrategy().add(MOVE_GARDENER);
                    p.getStrategy().add(MOVE_PANDA);
                }

            }
            nbRound++;
        }
        if (nbRound == maxRounds)
            Display.printMessage("Le jeu se termine au bout de "+nbRound+" tours.");

        Player winner = emperor.judgement();
        if (stats) {
            for(Player p : playerList){
                getGameStats().get(p).played();
            }
            if (winner != null) {
                for (Player p : playerList) {
                    if (p == winner) getGameStats().get(winner).win();
                    else getGameStats().get(p).lose();
                }
            } else {
                for (Player p : playerList) {
                    getGameStats().get(p).tie();
                }
            }

            getGameStats().get(p1).score(p1.getScore());
            getGameStats().get(p2).score(p2.getScore());

            getGameStats().get(p1).updateAverageScore();
            getGameStats().get(p2).updateAverageScore();

            return getGameStats();

        }
        return Collections.emptyMap();
    }

    public Player getP1() {
        return p1;
    }


    public Player getP2() {
        return p2;
    }
}
