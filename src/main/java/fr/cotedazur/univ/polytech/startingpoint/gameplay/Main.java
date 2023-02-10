package fr.cotedazur.univ.polytech.startingpoint.gameplay;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import fr.cotedazur.univ.polytech.startingpoint.data.BotStat;
import fr.cotedazur.univ.polytech.startingpoint.data.BotStatistics;
import fr.cotedazur.univ.polytech.startingpoint.display.Display;

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

    private static final int ITERATIONS = 1000;
    private static final int NBGAMESCSV = 20;
    static Map<Player, BotStat> gameStats;
    private Player p1;
    private Player p2;
    protected static List<Player> playerList = new ArrayList<>();

    private static final String BOTRANDOM="BotRandom";
    private static final String BOTINTELLIGENT="BotIntelligent";

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

    public Boolean demo() {
        Display.setUp(Level.INFO);
        Engine engine = new Engine(new Player(BOTINTELLIGENT, PANDASTRATEGY), new Player(BOTRANDOM, WITHOUTSTRATEGY));
        engine.runGame(new Game(engine.getP1(), engine.getP2()),false);
        return true;
    }

    public void csv() {
        Display.setUp(Level.SEVERE);
        Display.printMessage("Simulation de " + NBGAMESCSV +" parties avec relecture de \"stats/gamestats.csv\" s'il existe et ajout des nouvelles statistiques", Level.SEVERE);
        List<BotStat> botStats = Arrays.asList(
                new BotStat(BOTINTELLIGENT, PANDASTRATEGY),
                new BotStat(BOTRANDOM, WITHOUTSTRATEGY)
        );
        IntStream.range(0, NBGAMESCSV).forEach(i -> {
            Display.printMessage("Partie "+(i+1), Level.SEVERE);
            Engine engine = new Engine(new Player(BOTINTELLIGENT, PANDASTRATEGY),new Player(BOTRANDOM, WITHOUTSTRATEGY));
            runWithStats(botStats, engine);
        });
        Display.printGameStats(botStats);
        List<BotStat> existingStats = BotStatistics.readFromFile();
        Display.printMessage(String.valueOf(existingStats.size()), Level.SEVERE);
        existingStats.addAll(botStats);
        BotStatistics.writeToFile(botStats);
    }

    public Boolean twoThousand() {
        Display.setUp(Level.SEVERE);
        List<BotStat> botStats = Arrays.asList(
                new BotStat("FA3", Fa3STRATEGY),
                new BotStat("Random", WITHOUTSTRATEGY)
        );
        Display.printMessage("Simulation de "+ITERATIONS+" parties de votre meilleur bot contre le second", Level.SEVERE);
        IntStream.range(0, ITERATIONS).forEach(i -> {
            Engine engine = new Engine(new Player("FA3", Fa3STRATEGY),new Player("Panda", PANDASTRATEGY));
            runWithStats(botStats, engine);
        });
        Display.printGameStats(botStats);


        List<BotStat> botStats2 = Arrays.asList(
                new BotStat("FA3", Fa3STRATEGY),
                new BotStat("FA3v2", Fa3STRATEGY)
        );
        Display.printMessage("\nSimulation de "+ITERATIONS+" parties de votre meilleur bot contre lui-même", Level.SEVERE);
        IntStream.range(0, ITERATIONS).forEach(i -> {
            Engine engine = new Engine(new Player("FA3", Fa3STRATEGY),new Player("FA3v2", Fa3STRATEGY));
            runWithStats(botStats2, engine);
        });
        Display.printGameStats(botStats2);
        return true;
    }

    private static void runWithStats(List<BotStat> botStats2, Engine engine) {
        gameStats = Map.of(engine.getP1(), new BotStat(), engine.getP2(), new BotStat());
        gameStats = engine.runGame(new Game(engine.getP1(), engine.getP2()),true);
        botStats2.get(0).addGameStat(gameStats.get(engine.getP1()));
        botStats2.get(1).addGameStat(gameStats.get(engine.getP2()));
    }

    public static Map<Player, BotStat> getGameStats() {
        return gameStats;
    }
}
