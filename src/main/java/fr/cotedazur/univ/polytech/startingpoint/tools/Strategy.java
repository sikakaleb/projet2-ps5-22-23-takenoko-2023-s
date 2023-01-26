package fr.cotedazur.univ.polytech.startingpoint.tools;

import static fr.cotedazur.univ.polytech.startingpoint.tools.Action.GameAction.*;

public enum Strategy {
    /** valid que les objectifs parcelle*/
    PLOTSTRATEGY(new Action.GameAction[]{PICK_OBJECTIVE, PICK_PLOT}),
    /** valid que les objectifs panda */
    PANDASTRATEGY(new Action.GameAction[]{PICK_OBJECTIVE, MOVE_PANDA}),
    /** sans Strategi **/
    WITHOUTSTRATEGY(new Action().pickTwoDistinct());

    private Action.GameAction[] actions;

    Strategy(Action.GameAction[] actions){
        this.actions = actions;
    }

    public Action.GameAction[] getActions() {
        return actions;
    }
}
