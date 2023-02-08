package fr.cotedazur.univ.polytech.startingpoint;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class BotStatistics {

    private static final String STATS_DIRECTORY = "stats";
    private static final String STATS_FILE = "gamestats.csv";
    private static final String FILE_LOCATION = Paths.get(STATS_DIRECTORY, STATS_FILE).toString();

    public void saveStatistics(List<BotStat> stats) {
        File file = new File(FILE_LOCATION);

        try (CSVWriter writer = new CSVWriter(new FileWriter(file, true))) {
            for (BotStat stat : stats) {
                writer.writeNext(new String[] { stat.getBotName(), String.valueOf(stat.getGamesPlayed()),
                        String.valueOf(stat.getWins()), String.valueOf(stat.getLosses()) });
            }
        } catch (IOException e) {
            System.err.println("An error occurred while saving statistics to file: " + e.getMessage());
        }
    }

    public List<BotStat> loadStatistics() {
        File file = new File(FILE_LOCATION);

        try {
            CsvToBean<BotStat> csvToBean = new CsvToBeanBuilder<BotStat>(new FileReader(file))
                    .withType(BotStat.class).build();
            return csvToBean.parse();
        } catch (IOException e) {
            System.err.println("An error occurred while loading statistics from file: " + e.getMessage());
            return null;
        }
    }
}
