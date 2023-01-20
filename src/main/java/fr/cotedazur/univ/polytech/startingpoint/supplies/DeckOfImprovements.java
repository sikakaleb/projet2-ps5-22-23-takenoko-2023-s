package fr.cotedazur.univ.polytech.startingpoint.supplies;

import fr.cotedazur.univ.polytech.startingpoint.tools.PlotImprovement;
import java.util.ArrayList;
import java.util.Random;
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
    public DeckOfImprovements(){
        IntStream.range(0, 3).forEach(i -> {
            this.add(FENCE);
            this.add(FERTILIZER);
            this.add(POOL);
        });
    }

    public PlotImprovement pick(){
        if(this.isEmpty()){
            System.out.println("Il y a plus d'aménagements dans la liste");
            return null;
        }
        int rnd = new Random().nextInt(this.size());
        PlotImprovement plotImprovement = this.get(rnd);
        this.remove(plotImprovement);
        return plotImprovement;
    }


}
