package supplies;

import tools.Color;
import objectives.*;
import java.util.ArrayList;
import static tools.Color.*;
import static tools.PlotObjectiveConfiguration.*;
import static tools.PandaObjectiveConfiguration.*;

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
        this.add(new PandaObjective(4,THREE_GREEN, new Color[]{GREEN}));
        this.add(new PandaObjective(6,ONE_OF_EACH, new Color[]{YELLOW, GREEN, PINK}));
    }
}
