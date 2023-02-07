package fr.cotedazur.univ.polytech.startingpoint.data;


import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import fr.cotedazur.univ.polytech.startingpoint.gameplay.Player;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BotStatistics {
    private static final String CSV_FILE_PATH = "stats/gamestats.csv";
    private static final String FILE_NAME = "gamestats.csv";
    private static final Path FILE_PATH = Paths.get("stats", FILE_NAME);

    public static void main(String[] args) {
        // retrieve the latest statistics of the bots
       Player player1 = new Player("Bot 3");
       Player player2 = new Player("Bot 4");
        List<BotStat> botStats = Arrays.asList(
                new BotStat(player1, 15000, 1050,10),
                new BotStat(player2, 20000, 16000,500)
        );
        List<BotStat> existingStats = readFromFile();
        System.out.println(existingStats.size());
        existingStats.addAll(botStats);
        writeToFile(existingStats);
    }

    public /**/static/**/ List<BotStat> readFromFile() {
        Path filePath = Paths.get(CSV_FILE_PATH);
        List<BotStat> existingData = new ArrayList<>();

        // check if the file exists and read the existing data if it does
        File file = filePath.toFile();
        if (file.exists() && !file.isDirectory()) {
            try (Reader reader = new FileReader(file)) {
                ColumnPositionMappingStrategy<BotStat> strategy = new ColumnPositionMappingStrategy<>();
                strategy.setType(BotStat.class);
                strategy.setColumnMapping(new String[] { "name", "gamesPlayed","wins","losses" });

                CsvToBean<BotStat> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(BotStat.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                existingData = csvToBean.parse();
            } catch (Exception ex) {
                System.out.println("Error reading the CSV file: " + ex.getMessage());
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

            // write the data to the file
            try (FileWriter writer = new FileWriter(filePath.toString(), true)) {
                CSVWriter csvWriter = new CSVWriter(writer, CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

                // Write header only if the file is empty
                if (Files.size(filePath) == 0) {
                    csvWriter.writeNext(new String[] { "name", "gamesPlayed","wins","losses" });
                }

                for (BotStat stat : stats) {
                    csvWriter.writeNext(new String[] { stat.getBotName(), String
                            .valueOf(stat.getGamesPlayed()), String.valueOf(stat.getWins()),String.valueOf(stat.getLosses()) });
                }

                csvWriter.close();
            } catch (Exception ex) {
                System.out.println("Error writing to the CSV file: " + ex.getMessage());
            }
        } catch (IOException ex) {
            System.out.println("Error creating the directory or file: " + ex.getMessage());
        }
    }


    public void addToFile(BotStat stat) {
        List<BotStat> existingData = readFromFile();
        existingData.add(stat);
        writeToFile(existingData);
    }
    public static void createFileIfNotExists() throws IOException {
        File directory = FILE_PATH.getParent().toFile();
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File file = FILE_PATH.toFile();
        if (!file.exists()) {
            file.createNewFile();
        }
    }


    public void aggregateData(List<BotStat> stats) {
        List<BotStat> existingData = readFromFile();
        existingData.addAll(stats);
        writeToFile(existingData);
    }
}
