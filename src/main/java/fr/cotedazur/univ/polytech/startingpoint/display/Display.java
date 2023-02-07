package fr.cotedazur.univ.polytech.startingpoint.display;

import fr.cotedazur.univ.polytech.startingpoint.gameplay.Player;
import fr.cotedazur.univ.polytech.startingpoint.data.PlayerData;

import java.util.List;
import java.util.Map;
import java.util.logging.*;

import static fr.cotedazur.univ.polytech.startingpoint.gameplay.Main.ties;

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

    public static void printGameStats(List<Player> playerList, Map<Player, PlayerData> gameStats) {
        for (Player player : playerList) {
            LOGGER.log(Level.SEVERE,player.getName()+" (jouant avec "+player.getStrategy().getName()+") : ");
            LOGGER.log(Level.SEVERE,"parties gagnées : "+gameStats.get(player).getWins());
            LOGGER.log(Level.SEVERE, "parties perdues : "+gameStats.get(player).getLosses());
            LOGGER.log(Level.SEVERE, "parties nulles : "+ties);
            LOGGER.log(Level.SEVERE, "score moyen : "+gameStats.get(player).getAvgPoints());
        }
    }
}