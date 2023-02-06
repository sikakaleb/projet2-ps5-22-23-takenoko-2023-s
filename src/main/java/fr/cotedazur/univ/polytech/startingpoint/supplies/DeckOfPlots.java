package fr.cotedazur.univ.polytech.startingpoint.supplies;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.stream.IntStream;

import static fr.cotedazur.univ.polytech.startingpoint.display.Display.LOGGER;
import static fr.cotedazur.univ.polytech.startingpoint.tools.Color.*;

/**
 * @class DeckOfPlots
 */
public class DeckOfPlots extends ArrayList<HexPlot> {

    /**
     * @constructor
     * Create deck with 9 plots of each color
     */
    public DeckOfPlots(){
        IntStream.range(0, 9).forEach(i -> {
            this.add(new HexPlot(GREEN));
            this.add(new HexPlot(YELLOW));
            this.add(new HexPlot(PINK));
        });
    }

    /**
     * Pick a plot from the deck
     * @returns hex {HexPlot}
     */
    public HexPlot pickPlot(){
        if(this.size()==0){
            throw  new IndexOutOfBoundsException("Il n'y a plus de parcelle a poser");
        }
        int randNumber = new Random().nextInt(this.size());
        HexPlot hex = this.get(randNumber);
        this.remove(randNumber);
        LOGGER.log(Level.FINE,"Nombre de parcelle dans la pile :"+this.size());
        return hex;
    }
}
