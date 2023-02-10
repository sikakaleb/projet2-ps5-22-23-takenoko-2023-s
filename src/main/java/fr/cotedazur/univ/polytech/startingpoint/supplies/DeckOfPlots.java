package fr.cotedazur.univ.polytech.startingpoint.supplies;

import fr.cotedazur.univ.polytech.startingpoint.display.Display;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.IntStream;

import static fr.cotedazur.univ.polytech.startingpoint.tools.Color.*;

/**
 * @class DeckOfPlots
 */
public class DeckOfPlots extends ArrayList<HexPlot> {

    /**
     * @constructor
     * Create deck with 9 plots of each color
     */
    private SecureRandom rand;
    private byte[] bytes;
    public DeckOfPlots(){
        IntStream.range(0, 9).forEach(i -> {
            this.add(new HexPlot(GREEN));
            this.add(new HexPlot(YELLOW));
            this.add(new HexPlot(PINK));
        });
        rand = new SecureRandom();
        bytes= new byte[20];
        rand.nextBytes(bytes);
    }

    /**
     * Pick a plot from the deck
     * @returns hex {HexPlot}
     */
    public HexPlot pickPlot(){
        if(this.isEmpty()){
            throw  new IndexOutOfBoundsException("Il n'y a plus de parcelle a poser");
        }
        int randNumber = rand.nextInt(this.size());
        HexPlot hex = this.get(randNumber);
        this.remove(randNumber);
        Display.printMessage("Nombre de parcelle dans la pile :"+this.size());
        return hex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeckOfPlots hexPlots)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(rand, hexPlots.rand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), rand);
    }
}
