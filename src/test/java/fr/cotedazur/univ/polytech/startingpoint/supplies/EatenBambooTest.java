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
        assertEquals(eatenBamboos.count(Color.GREEN), 0);
        assertEquals(eatenBamboos.count(Color.YELLOW), 0);
        assertEquals(eatenBamboos.count(Color.PINK), 0);
        eatenBamboos.add(new Bamboo(Color.YELLOW));
        assertEquals(eatenBamboos.count(Color.YELLOW), 1);
        assertEquals(eatenBamboos.count(Color.GREEN), 0);
        assertEquals(eatenBamboos.count(Color.PINK), 0);
    }

    @Test
    void addMultipleTest(){
        eatenBamboos.addMultiple(2, YELLOW);
        assertEquals(eatenBamboos.size(), 2);
        eatenBamboos.addMultiple(6, PINK);
        assertEquals(eatenBamboos.size(), 8);
    }

    @Test
    void removeTest(){
        eatenBamboos.addMultiple(2, YELLOW);
        eatenBamboos.remove(YELLOW);
        assertEquals(eatenBamboos.size(), 1);
        eatenBamboos.remove(PINK);
        assertEquals(eatenBamboos.size(), 1);
    }

    @Test
    void removeTwoYellowTest() {
        eatenBamboos.removeTwoYellow();
        assertEquals(eatenBamboos.size(), 0);
        eatenBamboos.addMultiple(2, YELLOW);
        eatenBamboos.removeTwoYellow();
        assertEquals(eatenBamboos.size(), 0);
    }

    @Test
    void removeTwoGreenTest() {
        eatenBamboos.removeTwoGreen();
        assertEquals(eatenBamboos.size(), 0);
        eatenBamboos.addMultiple(2, GREEN);
        eatenBamboos.removeTwoGreen();
        assertEquals(eatenBamboos.size(), 0);
    }

    @Test
    void removeTwoPinkTest() {
        eatenBamboos.removeTwoPink();
        assertEquals(eatenBamboos.size(), 0);
        eatenBamboos.addMultiple(2, PINK);
        eatenBamboos.removeTwoPink();
        assertEquals(eatenBamboos.size(), 0);
    }

    @Test
    void removeThreeGreenTest() {
        eatenBamboos.addMultiple(3, GREEN);
        eatenBamboos.removeThreeGreen();
        assertEquals(eatenBamboos.size(), 0);
    }

    @Test
    void removeOneOfEachTest() {
        eatenBamboos.addMultiple(2, YELLOW);
        eatenBamboos.addMultiple(2, GREEN);
        eatenBamboos.addMultiple(1, PINK);
        eatenBamboos.removeOneOfEach();
        assertEquals(eatenBamboos.size(), 2);
        assertEquals(eatenBamboos.count(YELLOW), 1);
        assertEquals(eatenBamboos.count(GREEN), 1);
        assertEquals(eatenBamboos.count(PINK), 0);
    }

}
