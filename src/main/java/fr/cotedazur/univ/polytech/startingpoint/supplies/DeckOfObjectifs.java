package fr.cotedazur.univ.polytech.startingpoint.supplies;

import fr.cotedazur.univ.polytech.startingpoint.objectives.PandaObjective;
import fr.cotedazur.univ.polytech.startingpoint.objectives.PlotObjective;
import fr.cotedazur.univ.polytech.startingpoint.tools.Color;
import java.util.ArrayList;
import static fr.cotedazur.univ.polytech.startingpoint.tools.Color.*;
import static fr.cotedazur.univ.polytech.startingpoint.tools.PlotObjectiveConfiguration.*;
import static fr.cotedazur.univ.polytech.startingpoint.tools.PandaObjectiveConfiguration.*;

public class DeckOfObjectifs extends ArrayList {
    
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
        this.add(new PlotObjective(3,QUADRILATERALSAMEPLOTS_G_Y));
        this.add(new PlotObjective(4,QUADRILATERALSAMEPLOTS_G_P));
        this.add(new PlotObjective(5,QUADRILATERALSAMEPLOTS_P_Y));

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
    }
}
