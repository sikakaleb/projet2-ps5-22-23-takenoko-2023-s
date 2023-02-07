package fr.cotedazur.univ.polytech.startingpoint.supplies;

import java.util.Random;

/**
 * @class Dice for weather
 */
public class Dice {

    public enum Condition {
        SUN,
        RAIN,
        WIND,
        STORM,
        CLOUDS,
        MYSTERY;
    }

    private Condition value;

    public Dice(){
        this.value = null;
    }

    public Condition getLastValue(){
        return this.value;
    }

    public Condition roll(){
        int pick = new Random().nextInt(Condition.values().length);
        this.value = Condition.values()[pick];
        return Condition.values()[pick];
    }
}
