package fr.cotedazur.univ.polytech.startingpoint.supplies;

import fr.cotedazur.univ.polytech.startingpoint.supplies.Bamboo;
import fr.cotedazur.univ.polytech.startingpoint.tools.Color;

import java.util.ArrayList;
import java.util.stream.IntStream;

import static fr.cotedazur.univ.polytech.startingpoint.tools.Color.*;

public class EatenBamboos extends ArrayList<Bamboo> {

    /**
     * @constructor
     */
    public EatenBamboos(){
        super();
    }

    /**
     * Get a bamboo of color c
     * @param c {Color}
     * @return {Bamboo}
     */
    public Bamboo getByColor(Color c){
        return this.stream().filter(bamboo -> bamboo.getColor() == c)
                .findFirst().orElse(null);
    }

    /**
     * Count the number of bamboo of color c
     * @param c {Color}
     * @return {int}
     */
    public int count(Color c){
        return (int) this.stream().filter(bamboo -> bamboo.getColor() == c).count();
    }

    /**
     * add bamboo of color c from players' plate
     * @param numberOfBamboos {int}
     * @param c {Color}
     */
    public void addMultiple(int numberOfBamboos, Color c) {
        IntStream.range(0, numberOfBamboos).forEach(i -> this.add(new Bamboo(c)));
    }

    /**
     * Remove bamboo of color c from players' plate
     * @param c {Color}
     */
    public boolean remove(Color c){
        return this.remove(getByColor(c));
    }


    /**
     * Remove 2 yellow bamboos from player's plate
     */
    public void removeTwoYellow() {
        if(this.count(YELLOW)>=2) IntStream.range(0, 2).forEach(i -> this.remove(YELLOW));
    }
    /**
     * Remove 2 green bamboos from player's plate
     */
    public void removeTwoGreen() {
        if(this.count(GREEN)>=2) IntStream.range(0, 2).forEach(i -> this.remove(GREEN));
    }
    /**
     * Remove 2 pink bamboos from player's plate
     */
    public void removeTwoPink() {
        if(this.count(PINK)>=2) IntStream.range(0, 2).forEach(i -> this.remove(PINK));
    }
    /**
     * Remove 3 green bamboos from player's plate
     */
    public void removeThreeGreen() {
        if(this.count(GREEN)>=3) IntStream.range(0, 3).forEach(i -> this.remove(GREEN));
    }
    /**
     * Remove 3 bamboos from player's plate, one of each color
     */
    public void removeOneOfEach() {
        if(this.count(YELLOW)>=1 && this.count(GREEN)>=1 && this.count(YELLOW)>=1) {
            this.remove(YELLOW);
            this.remove(GREEN);
            this.remove(PINK);
        }
    }
}
