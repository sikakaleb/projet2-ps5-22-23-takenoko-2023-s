package fr.cotedazur.univ.polytech.startingpoint.supplies;

import fr.cotedazur.univ.polytech.startingpoint.tools.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BambooStockTest {

    private BambooStock stock;

    @BeforeEach
    void init(){
        stock = new BambooStock();
    }

    @Test
    void BambooStockTest(){
        assert(stock instanceof ArrayList<Bamboo>);
        assertEquals(90,stock.size());
    }

    @Test
    void countTest(){
        assertEquals(36,stock.count(Color.GREEN));
        assertEquals(30,stock.count(Color.YELLOW));
        assertEquals(24,stock.count(Color.PINK));
    }

    @Test
    void getByColorTest(){
        Bamboo greenBamboo = stock.getByColor(Color.GREEN);
        Bamboo yellowBamboo = stock.getByColor(Color.YELLOW);
        Bamboo pinkBamboo = stock.getByColor(Color.PINK);
        assert(greenBamboo instanceof Bamboo);
        assertEquals( Color.GREEN,greenBamboo.getColor());
        assert(yellowBamboo instanceof Bamboo);
        assertEquals(Color.YELLOW,yellowBamboo.getColor());
        assert(pinkBamboo instanceof Bamboo);
        assertEquals(pinkBamboo.getColor(), Color.PINK);
    }

    @Test
    void pickBambooTest(){
        assertEquals(stock.pickBamboo(Color.GREEN).getColor(), stock.getByColor(Color.GREEN).getColor());
        assertEquals(stock.pickBamboo(Color.YELLOW).getColor(), stock.getByColor(Color.YELLOW).getColor());
        assertEquals(stock.pickBamboo(Color.PINK).getColor(), stock.getByColor(Color.PINK).getColor());
    }

    @Test
    void areLeftTest(){
        assertEquals(true,stock.areLeft(Color.GREEN));
        assertEquals(true,stock.areLeft(Color.YELLOW));
        assertEquals(true,stock.areLeft(Color.PINK));
        for (int i = 0; i < 36; i++) {
            stock.pickBamboo(Color.GREEN);
        }
        for (int i = 0; i < 30; i++) {
            stock.pickBamboo(Color.YELLOW);
        }
        for (int i = 0; i < 24; i++) {
            stock.pickBamboo(Color.PINK);
        }
        assertEquals(false,stock.areLeft(Color.GREEN));
        assertEquals(false,stock.areLeft(Color.YELLOW));
        assertEquals(false,stock.areLeft(Color.PINK));
    }

    @Test
    void addBackTest(){
        assertEquals(36,stock.count(Color.GREEN));
        assertEquals(30,stock.count(Color.YELLOW));
        assertEquals(24,stock.count(Color.PINK));
        stock.addBack(2, Color.GREEN);
        stock.addBack(3, Color.YELLOW);
        stock.addBack(1, Color.PINK);
        assertEquals(38,stock.count(Color.GREEN));
        assertEquals(33,stock.count(Color.YELLOW));
        assertEquals(25,stock.count(Color.PINK));
    }

    @Test
    void addTwoYellowTest(){
        stock.pickBamboo(Color.YELLOW);
        stock.pickBamboo(Color.YELLOW);
        assertEquals(28,stock.count(Color.YELLOW));
        stock.addTwoYellow();
        assertEquals(30,stock.count(Color.YELLOW));
        stock.addTwoYellow();
        assertEquals(30,stock.count(Color.YELLOW));
    }

    @Test
    void addTwoGreenTest(){
        stock.pickBamboo(Color.GREEN);
        stock.pickBamboo(Color.GREEN);
        assertEquals(34,stock.count(Color.GREEN));
        stock.addTwoGreen();
        assertEquals(36,stock.count(Color.GREEN));
        stock.addThreeGreen();
        assertEquals(36,stock.count(Color.GREEN));
    }

    @Test
    void addTwoPinkTest(){
        stock.pickBamboo(Color.PINK);
        stock.pickBamboo(Color.PINK);
        assertEquals(22,stock.count(Color.PINK));
        stock.addTwoPink();
        assertEquals(24,stock.count(Color.PINK));
        stock.addTwoPink();
        assertEquals(24,stock.count(Color.PINK));
    }

    @Test
    void addThreeGreenTest(){
        stock.pickBamboo(Color.GREEN);
        stock.pickBamboo(Color.GREEN);
        stock.pickBamboo(Color.GREEN);
        assertEquals(33,stock.count(Color.GREEN));
        stock.addThreeGreen();
        assertEquals(36,stock.count(Color.GREEN));
        stock.addThreeGreen();
        assertEquals(36,stock.count(Color.GREEN));
    }

    @Test
    void addOneOfEachTest(){
        stock.pickBamboo(Color.GREEN);
        stock.pickBamboo(Color.YELLOW);
        stock.pickBamboo(Color.PINK);
        assertEquals(35,stock.count(Color.GREEN));
        assertEquals(29,stock.count(Color.YELLOW));
        assertEquals(23,stock.count(Color.PINK));
        stock.addOneOfEach();
        assertEquals(36,stock.count(Color.GREEN));
        assertEquals(30,stock.count(Color.YELLOW));
        assertEquals(24,stock.count(Color.PINK));
        stock.addOneOfEach();
        assertEquals(36,stock.count(Color.GREEN));
        assertEquals(30,stock.count(Color.YELLOW));
        assertEquals(24,stock.count(Color.PINK));

    }


}
