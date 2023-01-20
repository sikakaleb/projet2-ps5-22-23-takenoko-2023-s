package supplies;
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

    public Dice(){}

    public Condition roll(){
        int pick = new Random().nextInt(Condition.values().length);
        return Condition.values()[pick];
    }
}
