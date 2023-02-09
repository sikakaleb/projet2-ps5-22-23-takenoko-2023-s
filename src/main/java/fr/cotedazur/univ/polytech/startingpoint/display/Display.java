package fr.cotedazur.univ.polytech.startingpoint.display;

import fr.cotedazur.univ.polytech.startingpoint.data.BotStat;
import fr.cotedazur.univ.polytech.startingpoint.gameplay.Player;
import fr.cotedazur.univ.polytech.startingpoint.tools.Strategy;

import java.util.List;
import java.util.Map;
import java.util.logging.*;

/**
 * Class use to print every step of the game
 */
public class Display {

    public static final Logger LOGGER;
    
    static {
        LOGGER = Logger.getLogger(Display.class.getName());
        LOGGER.setUseParentHandlers(false);
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.FINE);
        handler.setFormatter(new SimpleFormatter() {
            @Override
            public synchronized String format(LogRecord record) {
                String formattedMessage = formatMessage(record);
                String throwable = "";
                String outputFormat = "%2$s\n%3$s";
                return String.format(outputFormat, record.getLevel().getName(), formattedMessage, throwable);
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
            LOGGER.log(Level.SEVERE,botStat.getName()+" (jouant avec "+botStat.getStrategy().getName()+") : ");
            LOGGER.log(Level.SEVERE,"parties gagnees : "+botStat.getWins());
            LOGGER.log(Level.SEVERE, "parties perdues : "+botStat.getLosses());
            LOGGER.log(Level.SEVERE, "parties nulles : "+botStat.getTies());
            LOGGER.log(Level.SEVERE, "score moyen : "+botStat.updateAverageScore());
        }
    }
}