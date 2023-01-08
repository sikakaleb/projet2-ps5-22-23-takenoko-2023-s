package supplies;

import tools.Color;

/**
 * @class Bamboo
 */
public class Bamboo {

    private Color color;

    /**
     * @constructor
     * @param c {Color}
     */
    public Bamboo(Color c){
        this.color=c;
    }

    /**
     * Getters
     */
    public Color getColor(){
        return this.color;
    }

    @Override
    public String toString() {
        return "Bamboo{" +
                "color=" + color +
                '}';
    }
}
