package fr.cotedazur.univ.polytech.startingpoint.data;


import fr.cotedazur.univ.polytech.startingpoint.gameplay.Player;
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
    public void setup() {
        gameStats = new BotStatistics();
        player1 = new Player("Bot 1");
        player2 = new Player("Bot 2");
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
    public void testWriteToFile() {
        List<BotStat> stats = Arrays.asList(
                new BotStat(player1, 100, 50,50),
                new BotStat(player2, 200, 60,60)
        );
        gameStats.writeToFile(stats);

        // verify that the data was written to the file correctly
        List<BotStat> data = gameStats.readFromFile();
        System.out.println(data.size());
        assertEquals(stats,data);
    }

    @Test
    public void testAddToFile() {
        gameStats.addToFile(new BotStat(player1, 100, 50,50));
        gameStats.addToFile(new BotStat(player2, 200, 60,140));

        // verify that the data was added to the file correctly
        List<BotStat> expected = Arrays.asList(
                new BotStat(player1, 100, 50,50),
                new BotStat(player2, 200, 60,140)
        );
        List<BotStat> actual = gameStats.readFromFile();
        assertTrue(actual.containsAll(expected));
    }

    @Test
    public void testAggregateData() {
               List<BotStat> additionalData = Arrays.asList(
                new BotStat(player1, 200, 70,130),
                new BotStat(player2, 300, 80,220)
        );
        gameStats.aggregateData(additionalData);

        // verify that the data was aggregated correctly
        List<BotStat> expected = Arrays.asList(
                new BotStat(player1, 200, 70,130),
                new BotStat(player2, 300, 80,220)
        );
        List<BotStat> actual = gameStats.readFromFile();
        assertEquals(expected,actual);
        assertTrue(actual.containsAll(expected));
    }
    @Test
    public void testReadFromFile_fileDoesNotExist() {
        File file = new File(CSV_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
        List<BotStat> data = gameStats.readFromFile();
        assertTrue(data.isEmpty());
    }

    @Test
    public void testAggregateData_emptyData() {
        List<BotStat> additionalData = Arrays.asList();
        gameStats.aggregateData(additionalData);

        List<BotStat> data = gameStats.readFromFile();
        assertTrue(data.isEmpty());
    }

}
