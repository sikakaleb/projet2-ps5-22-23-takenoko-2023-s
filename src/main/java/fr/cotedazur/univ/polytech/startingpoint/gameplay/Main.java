package fr.cotedazur.univ.polytech.startingpoint.gameplay;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import fr.cotedazur.univ.polytech.startingpoint.data.BotStat;
import fr.cotedazur.univ.polytech.startingpoint.data.BotStatistics;
import fr.cotedazur.univ.polytech.startingpoint.display.Display;
import fr.cotedazur.univ.polytech.startingpoint.tools.Strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.stream.IntStream;

import static fr.cotedazur.univ.polytech.startingpoint.tools.Strategy.*;

public class Main {

    @Parameter(names = { "--2thousands" }, description = "2 x 1000 parties")
    private static boolean twothousand;

    @Parameter(names = "--demo", description = "Mode demo d’un seule partie avec log complet")
    private static boolean demo;

    @Parameter(names = "--csv", description = "Simulation à plusieurs parties avec relecture de \"stats/gamestats.csv\" s’il existe et ajout des nouvelles statistiques")
    private static boolean csv;

    public static int ITERATIONS = 1000;
    public static Map<Player, BotStat> gameStats;
    Player p1;
    Player p2;
    public static List<Player> playerList = new ArrayList<>();

    /*
     * Main dans lequel se trouve une similation du jeu entre 2 joueurs
     */

    public static void main(String... argv) {

        Main main = new Main();
        JCommander.newBuilder()
                .addObject(main)
                .build()
                .parse(argv);
        main.run();
    }
    public void run() {
        if (twothousand) {
            twoThousand();
        } else if (demo) {
            demo();
        } else if (csv) {
            csv();
        } else {
            Display.printMessage("Aucun argument n'a été passé en paramètre");
        }
    }

    public void demo() {
        Display.setUp(Level.INFO);
        Engine engine = new Engine(new Player("BotIntelligent", PANDASTRATEGY), new Player("BotRandom", WITHOUTSTRATEGY));
        engine.runGame(new Game(engine.p1, engine.p2),false);
    }

    public void csv() {
        Display.setUp(Level.SEVERE);
        Display.printMessage("Simulation de " + ITERATIONS +" parties avec relecture de \"stats/gamestats.csv\" s'il existe et ajout des nouvelles statistiques", Level.SEVERE);
        gameWithStats(PANDASTRATEGY,WITHOUTSTRATEGY);
        Display.printGameStats(playerList, gameStats);
        List<BotStat> botStats = Arrays.asList(
                new BotStat("p1", PANDASTRATEGY,gameStats.get(p1).getGamesPlayed(), gameStats.get(p1).getWins(), gameStats.get(p1).getTies(), gameStats.get(p1).getLosses(), gameStats.get(p1).getPoints()),
                new BotStat("p2", WITHOUTSTRATEGY, gameStats.get(p2).getGamesPlayed(), gameStats.get(p2).getWins(), gameStats.get(p2).getTies(), gameStats.get(p2).getLosses(), gameStats.get(p2).getPoints())
        );
        List<BotStat> existingStats = BotStatistics.readFromFile();
        Display.printMessage(String.valueOf(existingStats.size()), Level.SEVERE);
        existingStats.addAll(botStats);
        BotStatistics.writeToFile(botStats);

    }

    public void twoThousand() {
        Display.setUp(Level.SEVERE);

        Display.printMessage("Simulation de "+ITERATIONS+" parties de votre meilleur bot contre le second", Level.SEVERE);
        gameWithStats(PANDASTRATEGY,WITHOUTSTRATEGY);

        Display.printMessage("\nSimulation de "+ITERATIONS+" parties de votre meilleur bot contre lui-même", Level.SEVERE);
        gameWithStats(PANDASTRATEGY,PANDASTRATEGY);
    }

    private void gameWithStats(Strategy strategy1, Strategy strategy2) {
        gameStats = Map.of(p1, new BotStat(), p2, new BotStat());
        IntStream.range(0, ITERATIONS).forEach(i -> {
            Display.printMessage("Partie "+(i+1), Level.SEVERE);
            Engine engine = new Engine(new Player("BotIntelligent", strategy1), new Player("BotRandom", strategy2));
            engine.runGame(new Game(engine.p1,engine.p2),true);
        });
        playerList.add(p1);
        playerList.add(p2);
        Display.printGameStats(playerList, gameStats);
    }

}
