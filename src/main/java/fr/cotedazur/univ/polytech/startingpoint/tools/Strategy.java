package fr.cotedazur.univ.polytech.startingpoint.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static fr.cotedazur.univ.polytech.startingpoint.tools.Action.GameAction.*;

public enum Strategy {
    /** valid que les objectifs parcelle*/
    PLOTSTRATEGY(new Action.GameAction[]{PICK_OBJECTIVE, PICK_PLOT}),

    /** valid que les objectifs panda */
    PANDASTRATEGY(new Action.GameAction[]{PICK_OBJECTIVE, MOVE_PANDA, MOVE_GARDENER, PLACE_IMPROVEMENT, PLACE_IRRIGATION}),

    /** sans Strategi **/
    WITHOUTSTRATEGY(new Action().getActions());


    private List<Action.GameAction> actions;

    Strategy(Action.GameAction[] actions){
        this.actions = new ArrayList(Arrays.asList(actions));
        ;
    }

    public List<Action.GameAction> getActions() {
        return actions;
    }

    public String getName() {
        return this.name();
    }

    public Action.GameAction pick(){
        int pick = new Random().nextInt(this.actions.size());
        return this.actions.get(pick);
    }

    public Action.GameAction[] pickTwoDistinct(){
        Action.GameAction[] actions = new Action.GameAction[2];
        actions[0] = pick();
        do {
            actions[1] = pick();
        } while (actions[0] == actions[1]);
        return actions;
    }

    public void noMorePlots(){
        actions.removeIf(Action.GameAction.PICK_PLOT::equals);
    }

    public void noMoreObjectives(){
        actions.removeIf(Action.GameAction.PICK_OBJECTIVE::equals);
    }

}
