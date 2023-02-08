package fr.cotedazur.univ.polytech.startingpoint.gameplay;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import fr.cotedazur.univ.polytech.startingpoint.data.BotStat;
import fr.cotedazur.univ.polytech.startingpoint.data.BotStatistics;
import fr.cotedazur.univ.polytech.startingpoint.data.PlayerData;
import fr.cotedazur.univ.polytech.startingpoint.display.Display;
import fr.cotedazur.univ.polytech.startingpoint.supplies.Emperor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.stream.IntStream;

import static fr.cotedazur.univ.polytech.startingpoint.tools.Action.GameAction.*;
import static fr.cotedazur.univ.polytech.startingpoint.tools.Strategy.*;

public class Main {

    @Parameter(names = { "--2thousands" }, description = "2 x 1000 parties")
    private static boolean twothousand;

    @Parameter(names = "--demo", description = "Mode demo d’un seule partie avec log complet")
    private static boolean demo;

    @Parameter(names = "--csv", description = "Simulation à plusieurs parties avec relecture de \"stats/gamestats.csv\" s’il existe et ajout des nouvelles statistiques")
    private static boolean csv;

    private static Player p1 = new Player("BotIntelligent", PANDASTRATEGY);
    private static Player p2 = new Player("BotRandom", WITHOUTSTRATEGY);
    public static Game game = new Game(p1,p2);
    private static Map<Player, PlayerData> gameStats;
    public static int ITERATIONS = 1000;
    public static int ties = 0;
    Map<Integer, Integer> objectivesForNbPlayers = Map.of(
            2, 9,
            3, 8,
            4, 7
    );
    private int nbObjectivesToWin = objectivesForNbPlayers.get(game.getPlayerList().size());
    public int nbRound = 0;
    //public int maxRounds = 50;

    /*
    * JeReflechis() utilisé pour marquer un temps de pause
    * la transition entre les tours de jeu de chaque joueurs
    */
    public static void  jeReflechis() {
        try {
            for (int i = 0; i < 6; i++) {
                Thread.sleep(6);
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

        demo=true;
        if (demo) {
            Display.setUp(Level.INFO);
            main.runGame();
        }

        else if (twothousand) {
            Display.setUp(Level.SEVERE);

            Display.printMessage("Simulation de "+ITERATIONS+" parties de votre meilleur bot contre le second", Level.SEVERE);
            ties = 0;
            gameStats = Map.of(p1, new PlayerData(), p2, new PlayerData());
            IntStream.range(0, ITERATIONS).forEach(i -> main.runGame());
            Display.printGameStats(game.playerList, gameStats);

            Display.printMessage("\nSimulation de "+ITERATIONS+" parties de votre meilleur bot contre lui-même", Level.SEVERE);
            p2.setStrategy(PANDASTRATEGY);
            ties = 0;
            gameStats = Map.of(p1, new PlayerData(), p2, new PlayerData());
            IntStream.range(0, ITERATIONS).forEach(i -> main.runGame());
            Display.printGameStats(game.playerList, gameStats);
        }

        else if(csv){
            Display.setUp(Level.SEVERE);
            Display.printMessage("Simulation de " + ITERATIONS +" parties avec relecture de \"stats/gamestats.csv\" s’il existe et ajout des nouvelles statistiques", Level.SEVERE);
            ties = 0;
            gameStats = Map.of(p1, new PlayerData(), p2, new PlayerData());
            IntStream.range(0, ITERATIONS).forEach(i -> main.runGame());
            Display.printGameStats(game.playerList, gameStats);
            List<BotStat> botStats = Arrays.asList(
                    new BotStat(p1, ITERATIONS, gameStats.get(p1).getWins(), gameStats.get(p1).getLosses()),
                    new BotStat(p2, ITERATIONS, gameStats.get(p2).getWins(), gameStats.get(p2).getLosses())
            );
            List<BotStat> existingStats = BotStatistics.readFromFile();
            Display.printMessage(String.valueOf(existingStats.size()), Level.SEVERE);
            existingStats.addAll(botStats);
            BotStatistics.writeToFile(existingStats);
        }

    }

    public void runGame(){
        Boolean loop = true, lastRound = false;
        Emperor emperor = new Emperor(game);
        List<Player> playerList = game.getPlayerList();

        Display.printMessage("---------------BEGIN----------------");
        while (loop){

            loop = !lastRound;

            for(Player p : playerList ){
                Display.printMessage("");

                if (p.getObjectiveAchieved().size() == nbObjectivesToWin) {
                    Display.printMessage( "Dernier tour ! "+p.getName()+" a valide "+nbObjectivesToWin+" objectifs.");
                    p.pickEmperor();
                    lastRound = true;
                }

                Display.printMessage("C'est le tour de : " + p.getName());
                Display.printMessage("Objectifs de "+p.getName()+p.getUnMetObjectives());
                if (demo) jeReflechis();
                if (game.play(p)) game.display();

                if (nbRound==0  && p.getStrategy()==Fa3STRATEGY){
                    System.out.println(p.getStrategy().getActions());
                    p.getStrategy().add(MOVE_GARDENER);
                    p.getStrategy().add(MOVE_PANDA);
                }

            }
            nbRound++;
        }
        //if (nbRound == maxRounds)
            //Display.printMessage("Le jeu se termine au bout de "+nbRound+" tours.");

        Player winner = emperor.judgement();
        if (twothousand || csv) {
            if (winner != null) {
                gameStats.get(winner).win();
            } else {
                ties++;
            }
            gameStats.get(p1).score(p1.getScore());
            gameStats.get(p2).score(p2.getScore());
        }
    }
}
