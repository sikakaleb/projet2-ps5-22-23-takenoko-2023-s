package supplies;

import tools.Color;

import java.util.ArrayList;
import java.util.stream.IntStream;
import static tools.Color.*;

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
        IntStream.range(0, 36).forEach(i -> this.add(new Bamboo(GREEN)));
        IntStream.range(0, 30).forEach(i -> this.add(new Bamboo(YELLOW)));
        IntStream.range(0, 24).forEach(i -> this.add(new Bamboo(PINK)));
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
     * Get a bamboo of color c
     * @param c {Color}
     * @return {Bamboo}
     */
    public Bamboo getByColor(Color c){
        return this.stream().filter(bamboo -> bamboo.getColor() == c)
                .findFirst().orElse(null);
    }

    /**
     * Check if there are bamboos of color c left
     * @param c {Color}
     * @return {boolean}
     */
    public boolean areLeft(Color c){
        return count(c) > 0;
    }

    /**
     * Remove bamboo from stock
     * @param c {Color}
     * @return bamboo {Bamboo}
     */
    public Bamboo pickBamboo(Color c){
        Bamboo bamboo = getByColor(c);
        if(bamboo != null){
            this.remove(bamboo);
        }
        return bamboo;
    }

    /**
     * Add bamboo of color c back into stock
     * @param numberOfBamboos {int}
     * @param c {Color}
     */
    public void addBack(int numberOfBamboos, Color c) {
        IntStream.range(0, numberOfBamboos).forEach(i -> this.add(new Bamboo(c)));
    }

    /**
     * Add 2 yellow bamboos back to stock
     */
    public void addTwoYellow() {
        if(this.count(YELLOW)<=34) addBack(2, YELLOW);
    }
    /**
     * Add 2 green bamboos back to stock
     */
    public void addTwoGreen() {
        if(this.count(GREEN)<=28) addBack(2, GREEN);
    }
    /**
     * Add 2 pink bamboos back to stock
     */
    public void addTwoPink() {
        if(this.count(PINK)<=22) addBack(2, PINK);
    }
    /**
     * Add 3 green bamboos back to stock
     */
    public void addThreeGreen() {
        if(this.count(GREEN)<=27) addBack(3, GREEN);
    }
    /**
     * Add 3 bamboos back to stock, one of each color
     */
    public void addOneOfEach() {
        if(this.count(YELLOW)<=35 && this.count(GREEN)<=29 && this.count(YELLOW)<=23) {
            addBack(1, YELLOW);
            addBack(1, GREEN);
            addBack(1, PINK);
        }
    }
}
