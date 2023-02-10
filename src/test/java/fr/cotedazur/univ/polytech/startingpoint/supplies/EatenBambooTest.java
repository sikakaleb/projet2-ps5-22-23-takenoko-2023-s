package fr.cotedazur.univ.polytech.startingpoint.supplies;

import fr.cotedazur.univ.polytech.startingpoint.supplies.Bamboo;
import fr.cotedazur.univ.polytech.startingpoint.supplies.EatenBamboos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import fr.cotedazur.univ.polytech.startingpoint.tools.Color;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static fr.cotedazur.univ.polytech.startingpoint.tools.Color.*;

class EatenBambooTest {

    private EatenBamboos eatenBamboos;

    @BeforeEach
    void init(){
        eatenBamboos = new EatenBamboos();
    }

    @Test
    void countTest(){
        assertEquals(0,eatenBamboos.count(Color.GREEN));
        assertEquals(0,eatenBamboos.count(Color.YELLOW));
        assertEquals(0,eatenBamboos.count(Color.PINK));
        eatenBamboos.add(new Bamboo(Color.YELLOW));
        assertEquals(1,eatenBamboos.count(Color.YELLOW));
        assertEquals(0,eatenBamboos.count(Color.GREEN));
        assertEquals(0,eatenBamboos.count(Color.PINK));
    }

    @Test
    void addMultipleTest(){
        eatenBamboos.addMultiple(2, YELLOW);
        assertEquals(2,eatenBamboos.size());
        eatenBamboos.addMultiple(6, PINK);
        assertEquals(8,eatenBamboos.size());
    }

    @Test
    void removeTest(){
        eatenBamboos.addMultiple(2, YELLOW);
        eatenBamboos.remove(YELLOW);
        assertEquals(1,eatenBamboos.size());
        eatenBamboos.remove(PINK);
        assertEquals(1,eatenBamboos.size());
    }

    @Test
    void removeTwoYellowTest() {
        eatenBamboos.removeTwoYellow();
        assertEquals(0,eatenBamboos.size());
        eatenBamboos.addMultiple(2, YELLOW);
        eatenBamboos.removeTwoYellow();
        assertEquals(0,eatenBamboos.size());
    }

    @Test
    void removeTwoGreenTest() {
        eatenBamboos.removeTwoGreen();
        assertEquals(0,eatenBamboos.size());
        eatenBamboos.addMultiple(2, GREEN);
        eatenBamboos.removeTwoGreen();
        assertEquals(0,eatenBamboos.size());
    }

    @Test
    void removeTwoPinkTest() {
        eatenBamboos.removeTwoPink();
        assertEquals(0,eatenBamboos.size());
        eatenBamboos.addMultiple(2, PINK);
        eatenBamboos.removeTwoPink();
        assertEquals(0,eatenBamboos.size());
    }

    @Test
    void removeThreeGreenTest() {
        eatenBamboos.addMultiple(3, GREEN);
        eatenBamboos.removeThreeGreen();
        assertEquals(0,eatenBamboos.size());
    }

    @Test
    void removeOneOfEachTest() {
        eatenBamboos.addMultiple(2, YELLOW);
        eatenBamboos.addMultiple(2, GREEN);
        eatenBamboos.addMultiple(1, PINK);
        eatenBamboos.removeOneOfEach();
        assertEquals(2,eatenBamboos.size());
        assertEquals(1,eatenBamboos.count(YELLOW));
        assertEquals(1,eatenBamboos.count(GREEN));
        assertEquals(0,eatenBamboos.count(PINK));
    }

}
