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
        assertEquals(stock.size(), 90);
    }

    @Test
    void countTest(){
        assertEquals(stock.count(Color.GREEN), 36);
        assertEquals(stock.count(Color.YELLOW), 30);
        assertEquals(stock.count(Color.PINK), 24);
    }

    @Test
    void getByColorTest(){
        Bamboo greenBamboo = stock.getByColor(Color.GREEN);
        Bamboo yellowBamboo = stock.getByColor(Color.YELLOW);
        Bamboo pinkBamboo = stock.getByColor(Color.PINK);
        assert(greenBamboo instanceof Bamboo);
        assertEquals(greenBamboo.getColor(), Color.GREEN);
        assert(yellowBamboo instanceof Bamboo);
        assertEquals(yellowBamboo.getColor(), Color.YELLOW);
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
        assertEquals(stock.areLeft(Color.GREEN), true);
        assertEquals(stock.areLeft(Color.YELLOW), true);
        assertEquals(stock.areLeft(Color.PINK), true);
        for (int i = 0; i < 36; i++) {
            stock.pickBamboo(Color.GREEN);
        }
        for (int i = 0; i < 30; i++) {
            stock.pickBamboo(Color.YELLOW);
        }
        for (int i = 0; i < 24; i++) {
            stock.pickBamboo(Color.PINK);
        }
        assertEquals(stock.areLeft(Color.GREEN), false);
        assertEquals(stock.areLeft(Color.YELLOW), false);
        assertEquals(stock.areLeft(Color.PINK), false);
    }

    @Test
    void addBackTest(){
        assertEquals(stock.count(Color.GREEN), 36);
        assertEquals(stock.count(Color.YELLOW), 30);
        assertEquals(stock.count(Color.PINK), 24);
        stock.addBack(2, Color.GREEN);
        stock.addBack(3, Color.YELLOW);
        stock.addBack(1, Color.PINK);
        assertEquals(stock.count(Color.GREEN), 38);
        assertEquals(stock.count(Color.YELLOW), 33);
        assertEquals(stock.count(Color.PINK), 25);
    }

    @Test
    void addTwoYellowTest(){
        stock.pickBamboo(Color.YELLOW);
        stock.pickBamboo(Color.YELLOW);
        assertEquals(stock.count(Color.YELLOW), 28);
        stock.addTwoYellow();
        assertEquals(stock.count(Color.YELLOW), 30);
        stock.addTwoYellow();
        assertEquals(stock.count(Color.YELLOW), 30);
    }

    @Test
    void addTwoGreenTest(){
        stock.pickBamboo(Color.GREEN);
        stock.pickBamboo(Color.GREEN);
        assertEquals(stock.count(Color.GREEN), 34);
        stock.addTwoGreen();
        assertEquals(stock.count(Color.GREEN), 36);
        stock.addThreeGreen();
        assertEquals(stock.count(Color.GREEN), 36);
    }

    @Test
    void addTwoPinkTest(){
        stock.pickBamboo(Color.PINK);
        stock.pickBamboo(Color.PINK);
        assertEquals(stock.count(Color.PINK), 22);
        stock.addTwoPink();
        assertEquals(stock.count(Color.PINK), 24);
        stock.addTwoPink();
        assertEquals(stock.count(Color.PINK), 24);
    }

    @Test
    void addThreeGreenTest(){
        stock.pickBamboo(Color.GREEN);
        stock.pickBamboo(Color.GREEN);
        stock.pickBamboo(Color.GREEN);
        assertEquals(stock.count(Color.GREEN), 33);
        stock.addThreeGreen();
        assertEquals(stock.count(Color.GREEN), 36);
        stock.addThreeGreen();
        assertEquals(stock.count(Color.GREEN), 36);
    }

    @Test
    void addOneOfEachTest(){
        stock.pickBamboo(Color.GREEN);
        stock.pickBamboo(Color.YELLOW);
        stock.pickBamboo(Color.PINK);
        assertEquals(stock.count(Color.GREEN), 35);
        assertEquals(stock.count(Color.YELLOW), 29);
        assertEquals(stock.count(Color.PINK), 23);
        stock.addOneOfEach();
        assertEquals(stock.count(Color.GREEN), 36);
        assertEquals(stock.count(Color.YELLOW), 30);
        assertEquals(stock.count(Color.PINK), 24);
        stock.addOneOfEach();
        assertEquals(stock.count(Color.GREEN), 36);
        assertEquals(stock.count(Color.YELLOW), 30);
        assertEquals(stock.count(Color.PINK), 24);

    }


}
