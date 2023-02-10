package fr.cotedazur.univ.polytech.startingpoint.data;


import fr.cotedazur.univ.polytech.startingpoint.gameplay.Player;
import fr.cotedazur.univ.polytech.startingpoint.tools.Strategy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BotStatisticsTest {
    private static final String CSV_FILE_PATH = "stats/gamestats.csv";
    private BotStatistics gameStats;
    Player player1;
    Player player2;

    @BeforeEach
    void setup() {
        gameStats = new BotStatistics();
        player1 = new Player("Bot 1");
        player2 = new Player("Bot 2");
        File file = new File(CSV_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @AfterEach
    void cleanup() {
        File file = new File(CSV_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testWriteToFile() {
        List<BotStat> stats = Arrays.asList(
                new BotStat("player1", Strategy.PANDASTRATEGY, 100,50, 0 ,50,5.0),
                new BotStat("player2", Strategy.WITHOUTSTRATEGY,200, 60, 80 ,60,5.0)
        );
        gameStats.writeToFile(stats);

        // verify that the data was written to the file correctly
        List<BotStat> data = gameStats.readFromFile();
        assertEquals(stats,data);
    }

    @Test
    void testAddToFile() {
        gameStats.addToFile(new BotStat("player1", Strategy.PANDASTRATEGY, 100, 50, 0,50,5.0));
        gameStats.addToFile(new BotStat("player2", Strategy.WITHOUTSTRATEGY, 200, 60, 1,139,5.0));

        // verify that the data was added to the file correctly
        List<BotStat> expected = Arrays.asList(
                new BotStat("player1", Strategy.PANDASTRATEGY, 100, 50,0,50,5.0),
                new BotStat("player2", Strategy.WITHOUTSTRATEGY, 200, 60,1,139,5.0)
        );
        List<BotStat> actual = gameStats.readFromFile();
        assertTrue(actual.containsAll(expected));
    }

    @Test
    void testAggregateData() {
               List<BotStat> additionalData = Arrays.asList(
                new BotStat("player1", Strategy.PANDASTRATEGY, 200, 70,0,130,5.0),
                new BotStat("player2", Strategy.WITHOUTSTRATEGY, 200, 60,1,139,5.0));
        gameStats.aggregateData(additionalData);

        // verify that the data was aggregated correctly
        List<BotStat> expected = Arrays.asList(
                new BotStat("player1", Strategy.PANDASTRATEGY,200, 70,0,130,5.0),
                new BotStat("player2", Strategy.WITHOUTSTRATEGY, 200, 60,1,139,5.0)
        );
        List<BotStat> actual = gameStats.readFromFile();
        assertEquals(expected,actual);
        assertTrue(actual.containsAll(expected));
    }
    @Test
     void testReadFromFile_fileDoesNotExist() {
        File file = new File(CSV_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
        List<BotStat> data = gameStats.readFromFile();
        assertTrue(data.isEmpty());
    }

    @Test
     void testAggregateData_emptyData() {
        List<BotStat> additionalData = Arrays.asList();
        gameStats.aggregateData(additionalData);

        List<BotStat> data = gameStats.readFromFile();
        assertTrue(data.isEmpty());
    }

}
