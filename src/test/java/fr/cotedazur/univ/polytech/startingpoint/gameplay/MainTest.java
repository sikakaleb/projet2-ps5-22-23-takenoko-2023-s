package fr.cotedazur.univ.polytech.startingpoint.gameplay;

import fr.cotedazur.univ.polytech.startingpoint.data.BotStat;
import fr.cotedazur.univ.polytech.startingpoint.data.BotStatistics;
import fr.cotedazur.univ.polytech.startingpoint.display.Display;
import fr.cotedazur.univ.polytech.startingpoint.tools.Strategy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.stream.IntStream;

import static fr.cotedazur.univ.polytech.startingpoint.tools.Strategy.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {
    private static final String CSV_FILE_PATH = "stats/gamestats.csv";
    private static final int NBGAMESCSV = 10;
    @BeforeEach
    public void setup() {
        File file = new File(CSV_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @AfterEach
    public void cleanup() {
        File file = new File(CSV_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testCsv() {
        Main main= new Main();
        main.csv();
        BotStatistics gameStats = new BotStatistics();
        List<BotStat> data = gameStats.readFromFile();
        assertEquals( 2, data.size());
    }
    @Test
    public void testNBGAMESCSVCsv() {
        IntStream.range(0,NBGAMESCSV).forEach(m->{
            Main main= new Main();
            main.csv();
        });
        BotStatistics gameStats = new BotStatistics();
        List<BotStat> data = gameStats.readFromFile();
        // Add some asserts to check the output
        assertEquals( 2*NBGAMESCSV, data.size());
    }


    @Test
    public void testTwoThousand() {
        Main main = new Main();

        assertTrue(main.twoThousand());

    }
   /* @Test
    *public void testCommandCsv() throws Exception {
    *    for (int i = 0; i < 10; i++) {
    *        Process process = Runtime.getRuntime().exec("cmd /c C:\\Program Files\\apache-maven-3.8.7\\bin\\mvn exec:java -Dexec.args=\"--csv\" -e");
    *       process.waitFor();
    *    }
    *}
    *
    *@Test
    *public void test2CommandThousands() throws Exception {
    *   for (int i = 0; i < 10; i++) {
    *        Process process = Runtime.getRuntime().exec("cmd /c C:\\Program Files\\apache-maven-3.8.7\\bin\\mvn exec:java -Dexec.args=\"--2thousands\" -e");
    *        process.waitFor();
    *    }
    *}*/


    @Test
    public void testCsvCheckContent() {
        Main main= new Main();
        Display.setUp(Level.SEVERE);
        Display.printMessage("Simulation de " + NBGAMESCSV +" parties avec relecture de \"stats/gamestats.csv\" s'il existe et ajout des nouvelles statistiques", Level.SEVERE);
        List<BotStat> botStats = Arrays.asList(
                new BotStat("BotIntelligent", Strategy.PANDASTRATEGY),
                new BotStat("BotRandom", Strategy.WITHOUTSTRATEGY)
        );
        IntStream.range(0, NBGAMESCSV).forEach(i -> {
            Display.printMessage("Partie "+(i+1), Level.SEVERE);
            Engine engine = new Engine(new Player("BotIntelligent", Strategy.PANDASTRATEGY),new Player("BotRandom", Strategy.WITHOUTSTRATEGY));
            main.gameStats = Map.of(engine.getP1(), new BotStat(), engine.getP2(), new BotStat());
            main.gameStats = engine.runGame(new Game(engine.getP1(), engine.getP2()),true);
            botStats.get(0).addGameStat(main.gameStats.get(engine.getP1()));
            botStats.get(1).addGameStat(main.gameStats.get(engine.getP2()));
        });
        Display.printGameStats(botStats);
        List<BotStat> existingStats = BotStatistics.readFromFile();
        Display.printMessage(String.valueOf(existingStats.size()), Level.SEVERE);
        existingStats.addAll(botStats);
        BotStatistics.writeToFile(botStats);

        // Add some asserts to check the output
        assertEquals( 2, botStats.size());
        // Check if the statistics were written to the file
        List<BotStat> writtenStats = BotStatistics.readFromFile();
        assertEquals(existingStats, writtenStats);
    }
}
