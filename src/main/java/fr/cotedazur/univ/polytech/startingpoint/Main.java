package fr.cotedazur.univ.polytech.startingpoint;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import data.PlayerData;
import fr.cotedazur.univ.polytech.startingpoint.display.Display;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import static fr.cotedazur.univ.polytech.startingpoint.tools.Strategy.PANDASTRATEGY;
import static fr.cotedazur.univ.polytech.startingpoint.tools.Strategy.PLOTSTRATEGY;

public class Main {

    @Parameter(names = { "--2thousands" }, description = "2 x 1000 parties")
    private static boolean twothousand;

    @Parameter(names = "--demo", description = "Mode démo d’un seule partie avec log complet")
    private static boolean demo = true;

    @Parameter(names = "--csv", description = "Simulation à plusieurs parties avec relecture de \"stats/gamestats.csv\" s’il existe et ajout des nouvelles statistiques")
    private static boolean csv;

    private Player p1 = new Player("Ted", PLOTSTRATEGY);
    private Player p2 = new Player("Willfried", PANDASTRATEGY);
    private Map<Player, PlayerData> gameStats = Map.of(p1, new PlayerData(), p2, new PlayerData());

    /*
    * JeReflechis() utilisé pour marquer un temps de pause
    * la transition entre les tours de jeu de chaque joueurs
    */
    public static void  jeReflechis() {
        try {
            for (int i = 0; i < 6; i++) {
                Thread.sleep(100);
            }
        }catch(Exception e) {
            Display.printMessage( String.valueOf(e));
        }
    }

    /*
     * Main dans lequel se trouve une similation du jeu entre 2 joueurs
     */

    public static void main(String... argv) {

        Main main = new Main();
        JCommander.newBuilder()
                .addObject(main)
                .build()
                .parse(argv);
        //main.test();

        if (demo) {
            Display.setUp(Level.INFO);
            main.runGame();
        }

    }

    private void runGame(){
        Boolean loop = true, lastRound = false;
        Game game = new Game(p1,p2);
        Emperor emperor = new Emperor(game);
        List<Player> playerList = game.getPlayerList();
        Map<Integer, Integer> objectivesForNbPlayers = Map.of(
                2, 9,
                3, 8,
                4, 7
        );
        int nbObjectivesToWin = objectivesForNbPlayers.get(game.getPlayerList().size());
        // Dans notre version, avec des bots peu intelligents, pour éviter que la partie ne soit interminable :
        // 1 : on réduit de 5 le nombre d'objectifs à atteindre pour gagner :
        nbObjectivesToWin -= 5;
        // 2 : on fixe nombre de tours prédéterminé
        int nbRound = 0, maxRounds = 30;


        Display.printMessage("---------------BEGIN----------------");
        while (loop && nbRound < maxRounds){

            loop = !lastRound;

            for(Player p : playerList ){
                Display.printMessage("");

                if (p.getObjectiveAchieved().size() == nbObjectivesToWin) {
                    Display.printMessage( "Dernier tour ! "+p.getName()+" a validé "+nbObjectivesToWin+" objectifs.");
                    p.pickEmperor();
                    lastRound = true;
                }

                Display.printMessage("C'est le tour de :" + p.getName());
                jeReflechis();
                if (game.play(p)) {
                    game.display();
                }
            }
            nbRound++;
        }
        if (nbRound == maxRounds)
            Display.printMessage("Le jeu se termine au bout de "+nbRound+" tours.");

        Player winner = emperor.judgement();
        if (twothousand) {
            gameStats.get(winner).win();
            gameStats.get(p1).score(p1.getScore());
            gameStats.get(p2).score(p2.getScore());
        }
        System.exit(0);
    }

}
