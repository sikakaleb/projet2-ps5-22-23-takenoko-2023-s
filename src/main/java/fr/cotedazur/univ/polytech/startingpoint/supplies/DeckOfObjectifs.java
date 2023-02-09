package fr.cotedazur.univ.polytech.startingpoint.supplies;

import fr.cotedazur.univ.polytech.startingpoint.objectives.GardenerObjective;
import fr.cotedazur.univ.polytech.startingpoint.objectives.Objective;
import fr.cotedazur.univ.polytech.startingpoint.objectives.PandaObjective;
import fr.cotedazur.univ.polytech.startingpoint.objectives.PlotObjective;
import fr.cotedazur.univ.polytech.startingpoint.tools.Color;

import java.util.ArrayList;

import static fr.cotedazur.univ.polytech.startingpoint.tools.Color.*;
import static fr.cotedazur.univ.polytech.startingpoint.tools.GardenerObjectiveConfiguration.*;
import static fr.cotedazur.univ.polytech.startingpoint.tools.PandaObjectiveConfiguration.*;
import static fr.cotedazur.univ.polytech.startingpoint.tools.PlotObjectiveConfiguration.*;

public class DeckOfObjectifs extends ArrayList<Objective> {
    
    public DeckOfObjectifs(){

        /**
         * Plot objectives :
         */
        this.add(new PlotObjective(2,DIRECTSAMEPLOTS,GREEN));
        this.add(new PlotObjective(3,DIRECTSAMEPLOTS,YELLOW));
        this.add(new PlotObjective(4,DIRECTSAMEPLOTS,PINK));
        this.add(new PlotObjective(2,INDIRECTSAMEPLOTS,GREEN));
        this.add(new PlotObjective(3,INDIRECTSAMEPLOTS,YELLOW));
        this.add(new PlotObjective(4,INDIRECTSAMEPLOTS,PINK));
        this.add(new PlotObjective(2,TRIANGULARSAMEPLOTS,GREEN));
        this.add(new PlotObjective(3,TRIANGULARSAMEPLOTS,YELLOW));
        this.add(new PlotObjective(4,TRIANGULARSAMEPLOTS,PINK));
        this.add(new PlotObjective(3,QUADRILATERALSAMEPLOTS,GREEN));
        this.add(new PlotObjective(4,QUADRILATERALSAMEPLOTS,YELLOW));
        this.add(new PlotObjective(5,QUADRILATERALSAMEPLOTS,PINK));
        this.add(new PlotObjective(3,QUADRILATERALSAMEPLOTSGY));
        this.add(new PlotObjective(4, QUADRILATERALSAMEPLOTSGP));
        this.add(new PlotObjective(5, QUADRILATERALSAMEPLOTSPY));

        /**
         * Panda objectives
         */
        this.add(new PandaObjective(4,TWO_YELLOW, new Color[]{YELLOW}));
        this.add(new PandaObjective(4,TWO_YELLOW, new Color[]{YELLOW}));
        this.add(new PandaObjective(4,TWO_YELLOW, new Color[]{YELLOW}));
        this.add(new PandaObjective(4,TWO_YELLOW, new Color[]{YELLOW}));

        this.add(new PandaObjective(3,TWO_GREEN, new Color[]{GREEN}));
        this.add(new PandaObjective(3,TWO_GREEN, new Color[]{GREEN}));
        this.add(new PandaObjective(3,TWO_GREEN, new Color[]{GREEN}));
        this.add(new PandaObjective(3,TWO_GREEN, new Color[]{GREEN}));
        this.add(new PandaObjective(3,TWO_GREEN, new Color[]{GREEN}));

        this.add(new PandaObjective(5,TWO_PINK, new Color[]{PINK}));
        this.add(new PandaObjective(5,TWO_PINK, new Color[]{PINK}));
        this.add(new PandaObjective(5,TWO_PINK, new Color[]{PINK}));

        this.add(new PandaObjective(6,ONE_OF_EACH, new Color[]{YELLOW, GREEN, PINK}));
        this.add(new PandaObjective(6,ONE_OF_EACH, new Color[]{YELLOW, GREEN, PINK}));
        this.add(new PandaObjective(6,ONE_OF_EACH, new Color[]{YELLOW, GREEN, PINK}));

        /**
         * Gardener objectives
         */
        this.add(new GardenerObjective(5,FOUR_AND_FERTILIZER, new Color[]{PINK}));
        this.add(new GardenerObjective(4,FOUR_AND_FERTILIZER, new Color[]{YELLOW}));
        this.add(new GardenerObjective(3,FOUR_AND_FERTILIZER, new Color[]{GREEN}));

        this.add(new GardenerObjective(5,FOUR_AND_POOL, new Color[]{YELLOW}));
        this.add(new GardenerObjective(4,FOUR_AND_POOL, new Color[]{GREEN}));
        this.add(new GardenerObjective(6,FOUR_AND_POOL, new Color[]{PINK}));

        this.add(new GardenerObjective(6,FOUR_AND_FENCE, new Color[]{PINK}));
        this.add(new GardenerObjective(4,FOUR_AND_FENCE, new Color[]{GREEN}));
        this.add(new GardenerObjective(5,FOUR_AND_FENCE, new Color[]{YELLOW}));

        this.add(new GardenerObjective(6, FOUR_NO_IMPOROVEMENT, new Color[]{YELLOW}));
        this.add(new GardenerObjective(7, FOUR_NO_IMPOROVEMENT, new Color[]{PINK}));
        this.add(new GardenerObjective(5, FOUR_NO_IMPOROVEMENT, new Color[]{GREEN}));

        this.add(new GardenerObjective(8, THREE_GREEN_X4, new Color[]{GREEN}));
        this.add(new GardenerObjective(7, THREE_YELLOW_X3, new Color[]{YELLOW}));
        this.add(new GardenerObjective(6, THREE_PINK_X2, new Color[]{PINK}));

    }
}
