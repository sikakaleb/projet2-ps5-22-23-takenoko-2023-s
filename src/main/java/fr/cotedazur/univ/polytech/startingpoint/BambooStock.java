package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;

/**
 * @class BambooStock
 * @extends ArrayList
 * a stock of bamboos, of different color
 */
public class BambooStock extends ArrayList<Bamboo> {

    /**
     * @constructor
     */
    public BambooStock(){
        for(int i=0; i<36; i++){
            this.add(new Bamboo(PlotColor.GREEN));
        }
        for(int i=0; i<30; i++){
            this.add(new Bamboo(PlotColor.YELLOW));
        }
        for(int i=0; i<24; i++){
            this.add(new Bamboo(PlotColor.PINK));
        }
    }

    /**
     * Count the number of bamboo of color c
     * @param c {PlotColor}
     * @return {int}
     */
    public int count(PlotColor c){
        return (int) this.stream().filter(bamboo -> bamboo.getColor() == c).count();
    }

    /**
     * Get a bamboo of color c
     * @param c {PlotColor}
     * @return {Bamboo}
     */
    public Bamboo getByColor(PlotColor c){
        return this.stream().filter(bamboo -> bamboo.getColor() == c)
                .findFirst().orElse(null);
    }

    /**
     * Check if there are bamboos of color c left
     * @param c {PlotColor}
     * @return {boolean}
     */
    public boolean areLeft(PlotColor c){
        return count(c) > 0;
    }

    /**
     * Remove bamboo from stock
     * @param c {PlotColor}
     * @return {boolean} if has been removed or not
     */
    public boolean remove(PlotColor c){
        return this.remove(getByColor(c));
    }
}
