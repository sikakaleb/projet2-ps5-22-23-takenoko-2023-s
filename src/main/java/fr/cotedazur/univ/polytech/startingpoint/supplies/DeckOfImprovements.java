package fr.cotedazur.univ.polytech.startingpoint.supplies;

import fr.cotedazur.univ.polytech.startingpoint.display.Display;
import fr.cotedazur.univ.polytech.startingpoint.tools.PlotImprovement;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.IntStream;

import static fr.cotedazur.univ.polytech.startingpoint.tools.PlotImprovement.*;

/**
 * @class DeckOfImprovements
 */
public class DeckOfImprovements extends ArrayList<PlotImprovement> {

    /**
     * @constructor
     * Create deck with 9 plots of each color
     */
    private SecureRandom rand;
    private byte[] bytes;
    public DeckOfImprovements(){
        IntStream.range(0, 3).forEach(i -> {
            this.add(FENCE);
            this.add(FERTILIZER);
            this.add(POOL);
        });
        rand = new SecureRandom();
        bytes = new byte[20];
        rand.nextBytes(bytes);
    }

    public PlotImprovement pick(){
        if(this.isEmpty()){
            Display.printMessage("Il y a plus d'amenagements dans la liste");
            return null;
        }
        int rnd = rand.nextInt(this.size());
        PlotImprovement plotImprovement = this.get(rnd);
        this.remove(plotImprovement);
        return plotImprovement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeckOfImprovements that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(rand, that.rand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), rand);
    }
}
