package fr.cotedazur.univ.polytech.startingpoint.display;

import fr.cotedazur.univ.polytech.startingpoint.data.BotStat;

import java.util.List;
import java.util.logging.*;

/**
 * Class use to print every step of the game
 */
public class Display {

    public static final Logger LOGGER;

    private Display(){}

    static {
        LOGGER = Logger.getLogger(Display.class.getName());
        LOGGER.setUseParentHandlers(false);
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.FINE);
        handler.setFormatter(new SimpleFormatter() {
            @Override
            public synchronized String format(LogRecord logrecord) {
                String formattedMessage = formatMessage(logrecord);
                String throwable = "";
                String outputFormat = "%2$s\n%3$s";
                return String.format(outputFormat, logrecord.getLevel().getName(), formattedMessage, throwable);
            }
        });
        LOGGER.addHandler(handler);
    }

    public static void setUp(Level level) {
        LOGGER.setLevel(level);
    }

    public static void printMessage(String msg) {
        LOGGER.log(Level.INFO, msg);
    }

    public static void printMessage(String msg, Level level) {
        LOGGER.log(level, msg);
    }

    public static void printGameStats(List<BotStat> botStats) {
        for (BotStat botStat : botStats) {
            if (LOGGER.isLoggable(Level.SEVERE)) {
                LOGGER.log(Level.SEVERE, String.format("%s (jouant avec %s) : ",
                        botStat.getName(),
                        botStat.getStrategy().getName()));

            LOGGER.log(Level.SEVERE, String.format("parties gagnees : %d", botStat.getWins()));
            LOGGER.log(Level.SEVERE, String.format("parties perdues : %d", botStat.getLosses()));
            LOGGER.log(Level.SEVERE, String.format("parties nulles : %d", botStat.getTies()));
            LOGGER.log(Level.SEVERE, String.format("winrate : %.2f%%",
                    (((botStat.getWins())+0.0) / (0.0+botStat.getGamesPlayed()))*100));
            LOGGER.log(Level.SEVERE, String.format("score moyen : %.2f", botStat.updateAverageScore()));
            }
        }
    }
}
