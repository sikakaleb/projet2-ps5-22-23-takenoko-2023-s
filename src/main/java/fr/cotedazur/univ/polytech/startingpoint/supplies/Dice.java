package fr.cotedazur.univ.polytech.startingpoint.supplies;

import java.security.SecureRandom;

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

    private SecureRandom rand;
    private byte[] bytes;
    public Dice(){
        this.value = null;
        rand = new SecureRandom();
        bytes= new byte[20];
        rand.nextBytes(bytes);
    }

    public Condition getLastValue(){
        return this.value;
    }



    public Condition roll(){
        int pick = rand.nextInt(Condition.values().length);
        this.value = Condition.values()[pick];
        return Condition.values()[pick];
    }
}
