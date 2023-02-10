package fr.cotedazur.univ.polytech.startingpoint.data;


import com.opencsv.CSVWriter;
import com.opencsv.ICSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import fr.cotedazur.univ.polytech.startingpoint.display.Display;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class BotStatistics {
    private static final String CSV_FILE_PATH = "stats/gamestats.csv";
    private static final String FILE_NAME = "gamestats.csv";
    private static final Path FILE_PATH = Paths.get("stats", FILE_NAME);

/**   public static void main(String[] args) {
         retrieve the latest statistics of the bots
      Player player1 = new Player("Bot 3");
       Player player2 = new Player("Bot 4");
        List<BotStat> botStats = Arrays.asList(
                new BotStat(player1, 15000, 1050,10),
                new BotStat(player2, 20000, 16000,500)
        );
        List<BotStat> existingStats = readFromFile();
        Display.printMessage(String.valueOf(existingStats.size()), Level.SEVERE);
        existingStats.addAll(botStats);
        writeToFile(existingStats);
    }**/

    public /**/static/**/ List<BotStat> readFromFile() {
        Path filePath = Paths.get(CSV_FILE_PATH);
        List<BotStat> existingData = new ArrayList<>();

        /**check if the file exists and read the existing data if it does**/
        File file = filePath.toFile();
        if (file.exists() && !file.isDirectory()) {
            try (Reader reader = new FileReader(file)) {
                ColumnPositionMappingStrategy<BotStat> strategy = new ColumnPositionMappingStrategy<>();
                strategy.setType(BotStat.class);
                strategy.setColumnMapping("name","strategy", "gamesPlayed","wins","ties","losses", "points");

                CsvToBean<BotStat> csvToBean = new CsvToBeanBuilder<BotStat>(reader)
                        .withType(BotStat.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                existingData = csvToBean.parse();
            } catch (Exception ex) {
                Display.printMessage("Error reading the CSV file: " + ex.getMessage(),Level.SEVERE);
            }
        }

        return existingData;
    }
    public static void writeToFile(List<BotStat> stats) {
        Path filePath = Paths.get(CSV_FILE_PATH);
        Path directoryPath = filePath.getParent();

        try {
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }

            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }

            writeDataToFile(stats, filePath);
        } catch (IOException ex) {
            Display.printMessage("Error creating the directory or file: " + ex.getMessage(), Level.SEVERE);
        }
    }

    private static void writeDataToFile(List<BotStat> stats, Path filePath) {
        try (FileWriter writer = new FileWriter(filePath.toString(), true)) {
            ICSVWriter csvWriter = new CSVWriter(writer, ICSVWriter.DEFAULT_SEPARATOR, ICSVWriter.NO_QUOTE_CHARACTER, ICSVWriter.DEFAULT_ESCAPE_CHARACTER, ICSVWriter.DEFAULT_LINE_END);
            if (Files.size(filePath) == 0) {
                csvWriter.writeNext(new String[] { "name","strategy", "gamesPlayed","wins","ties","losses", "points" });
            }
            for (BotStat stat : stats) {
                csvWriter.writeNext(new String[] { stat.getName(), String.valueOf(stat.getStrategy()), String
                        .valueOf(stat.getGamesPlayed()), String.valueOf(stat.getWins()), String.valueOf(stat.getTies()) ,String.valueOf(stat.getLosses()), String.valueOf(stat.getPoints())});
            }
            csvWriter.close();
        } catch (Exception ex) {
            Display.printMessage("Error writing to the CSV file: " + ex.getMessage(), Level.SEVERE);
        }
    }


    public void addToFile(BotStat stat) {
        List<BotStat> existingData = readFromFile();
        existingData.add(stat);
        writeToFile(existingData);
    }
    public static Boolean createFileIfNotExists() throws IOException {
        File directory = FILE_PATH.getParent().toFile();
        Boolean result = true;
        if (!directory.exists()) {
            result&= directory.mkdirs();
        }
        File file = FILE_PATH.toFile();
        if (!file.exists()) {
            result&=file.createNewFile();
        }
        return result;
    }


    public void aggregateData(List<BotStat> stats) {
        List<BotStat> existingData = readFromFile();
        existingData.addAll(stats);
        writeToFile(existingData);
    }
}
