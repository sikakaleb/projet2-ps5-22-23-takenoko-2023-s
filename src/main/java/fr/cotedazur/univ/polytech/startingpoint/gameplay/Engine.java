package fr.cotedazur.univ.polytech.startingpoint.gameplay;

import fr.cotedazur.univ.polytech.startingpoint.data.BotStat;
import fr.cotedazur.univ.polytech.startingpoint.display.Display;
import fr.cotedazur.univ.polytech.startingpoint.supplies.Emperor;
import java.util.Collections;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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


    /** Dans notre version, avec des bots peu intelligents,pour éviter que la partie
    * ne soit interminable, on fixe nombre de tours prédéterminé :**/
    private int maxRounds;

    public Engine(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        maxRounds=50;
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

    public Map<Player, BotStat> runGame(Game game, boolean stats) {
        int nbRound = 0;
        int nbObjectivesToWin = objectivesForNbPlayers.get(game.getPlayerList().size());
        List<Player> playerList = game.getPlayerList();
        Emperor emperor = new Emperor(game);

        boolean lastRound = false;
        boolean loop = true;

        Display.printMessage("---------------BEGIN----------------");
        while (nbRound < maxRounds && loop) {
            loop = !lastRound;
            for (Player p : playerList) {
                Display.printMessage("");
                if (p.getObjectiveAchieved().size() == nbObjectivesToWin) {
                    lastRound = true;
                    Display.printMessage("Last round! " + p.getName() + " achieved " + nbObjectivesToWin + " objectives.");
                    p.pickEmperor();
                }
                Display.printMessage("It's " + p.getName() + "'s turn.");
                if (Boolean.TRUE.equals(game.play(p))) game.display();
                if (nbRound == 0 && p.getStrategy() == FA3STRATEGY) {
                    Display.printMessage(p.getStrategy().getActions().toString());
                    p.getStrategy().add(MOVE_GARDENER);
                    p.getStrategy().add(MOVE_PANDA);
                }
            }
            nbRound++;
        }

        Player winner = emperor.judgement();

        if (stats) {
            Map<Player, BotStat> statsMap = getGameStats();
            for (Player p : playerList) {
                BotStat playerStat = statsMap.get(p);
                playerStat.played();

                if (p == winner) {
                    playerStat.win();
                } else if (winner != null) {
                    playerStat.lose();
                } else {
                    playerStat.tie();
                }

                if (!Objects.isNull(p)) {
                    playerStat.score(p.getScore());
                    playerStat.updateAverageScore();
                }
            }
            return statsMap;
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
