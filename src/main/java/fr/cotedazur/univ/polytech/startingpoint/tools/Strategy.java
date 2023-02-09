package fr.cotedazur.univ.polytech.startingpoint.tools;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static fr.cotedazur.univ.polytech.startingpoint.tools.Action.GameAction.*;

public enum Strategy {
    /** valid que les objectifs parcelle*/
    PLOTSTRATEGY(new Action.GameAction[]{PICK_OBJECTIVE, PICK_PLOT}),

    /** valid que les objectifs panda */
    PANDASTRATEGY(new Action.GameAction[]{PICK_OBJECTIVE, MOVE_PANDA, MOVE_GARDENER, PLACE_IMPROVEMENT, PLACE_IRRIGATION}),

    /** sans Strategi **/
    WITHOUTSTRATEGY(new Action().getActions()),

    /**
     * Strategie de la fonction additionelle 3 :
     *
     * - Il essaie d’avoir 5 cartes objectif en main tout le temps. Les deux premiers mouvements du bot
     * devraient donc être de prendre une carte objectif et de prendre un canal d’irrigation.
     */
    Fa3STRATEGY(new Action.GameAction[]{PICK_OBJECTIVE, PLACE_IRRIGATION});


    private List<Action.GameAction> actions;
    private SecureRandom rand;

    Strategy(Action.GameAction[] actions){
        this.actions = new ArrayList(Arrays.asList(actions));
        rand = new SecureRandom();
        byte bytes[] = new byte[20];
        rand.nextBytes(bytes);
    }

    public List<Action.GameAction> getActions() {
        return actions;
    }

    public void add(Action.GameAction action){
        if (! this.actions.contains(action))
            this.actions.add(action);
    }

    public String getName() {
        return this.name();
    }

    public Action.GameAction pick(){
        int pick = rand.nextInt(this.actions.size());
        return this.actions.get(pick);
    }

    public Action.GameAction[] pickDifferent(Action.GameAction action){
        Action.GameAction[] actions = new Action.GameAction[2];
        actions[0] = action;
        do {
            actions[1] = pick();
        } while (actions[0] == actions[1]);
        return actions;
    }

    public Action.GameAction[] pickTwoDistinct(){
        Action.GameAction action = pick();
        return pickDifferent(action);
    }

    public void noMorePlots(){
        actions.removeIf(Action.GameAction.PICK_PLOT::equals);
    }

    public void noMoreObjectives(){
        actions.removeIf(Action.GameAction.PICK_OBJECTIVE::equals);
    }

}
