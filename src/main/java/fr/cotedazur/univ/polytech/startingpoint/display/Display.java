package fr.cotedazur.univ.polytech.startingpoint.display;

import fr.cotedazur.univ.polytech.startingpoint.tools.Strategy;

import java.util.logging.*;

/**
 * Class use to print every step of the game
 */
public class Display {

    private static final Logger LOGGER;
    
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

    private static void printPercentageWinPlayer(int idPlayer, Strategy strategy, double[] infos) {
        LOGGER.log(Level.INFO, "Le joueur {0} | {1}", new Object[]{idPlayer, strategy});
        LOGGER.log(Level.INFO, "\ta gagne {0} parties", infos[0]);
        LOGGER.log(Level.INFO, "\ta perdu {0} parties", infos[1]);
        LOGGER.log(Level.INFO, "\ta gagne {0}% des parties", infos[2]);
        LOGGER.log(Level.INFO, "\ta perdu {0}% des parties", infos[3]);
        LOGGER.log(Level.INFO, "\ta obtenu {0} de points en moyenne", infos[4]);
    }
}