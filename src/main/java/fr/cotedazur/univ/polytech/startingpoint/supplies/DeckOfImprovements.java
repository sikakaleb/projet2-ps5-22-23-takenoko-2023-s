package fr.cotedazur.univ.polytech.startingpoint.supplies;

import fr.cotedazur.univ.polytech.startingpoint.display.Display;
import fr.cotedazur.univ.polytech.startingpoint.tools.PlotImprovement;

import java.security.SecureRandom;
import java.util.ArrayList;
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
    public DeckOfImprovements(){
        IntStream.range(0, 3).forEach(i -> {
            this.add(FENCE);
            this.add(FERTILIZER);
            this.add(POOL);
        });
        rand = new SecureRandom();
        byte bytes[] = new byte[20];
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


}
