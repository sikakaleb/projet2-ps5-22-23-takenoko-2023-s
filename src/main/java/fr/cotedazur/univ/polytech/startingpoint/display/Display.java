package fr.cotedazur.univ.polytech.startingpoint.display;

import fr.cotedazur.univ.polytech.startingpoint.Player;

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

    private static void printPercentageWinPlayer(Player player, double[] infos) {
        LOGGER.log(Level.SEVERE, "Le joueur "+player.getName()+" avec la stratégie "+player.getStrategy().getName());
        LOGGER.log(Level.SEVERE, "\ta gagne {0} parties", infos[0]);
        LOGGER.log(Level.SEVERE, "\ta perdu {0} parties", infos[1]);
        LOGGER.log(Level.SEVERE, "\ta gagne {0}% des parties", infos[2]);
        LOGGER.log(Level.SEVERE, "\ta perdu {0}% des parties", infos[3]);
        LOGGER.log(Level.SEVERE, "\ta obtenu {0} de points en moyenne", infos[4]);
    }
}