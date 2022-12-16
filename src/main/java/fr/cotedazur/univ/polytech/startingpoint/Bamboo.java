package fr.cotedazur.univ.polytech.startingpoint;

/**
 * @class Bamboo
 */
public class Bamboo {

    private PlotColor color;

    /**
     * @constructor
     * @param c {PlotColor}
     */
    public Bamboo(PlotColor c){
        this.color=c;
    }

    /**
     *
     * @return {PlotColor}
     */
    public PlotColor getColor(){
        return this.color;
    }
}
