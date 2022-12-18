package supplies;

import tools.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class BambooStockTest {

    private BambooStock stock;

    @BeforeEach
    public void init(){
        stock = new BambooStock();
    }

    @Test
    public void BambooStock(){
        assert(stock instanceof ArrayList<Bamboo>);
        assertEquals(stock.size(), 90);
    }

    @Test
    public void count(){
        assertEquals(stock.count(Color.GREEN), 36);
        assertEquals(stock.count(Color.YELLOW), 30);
        assertEquals(stock.count(Color.PINK), 24);
    }

    @Test
    public void getByColor(){
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
    public void remove(){
        stock.remove(Color.GREEN);
        assertEquals(stock.size(), 89);
        this.removeAllPink();
        assertEquals(stock.size(), 65);
        assertFalse(stock.remove(Color.PINK));
    }

    @Test
    public void countNoneLeft(){
        this.removeAllPink();
        assertEquals(stock.count(Color.PINK), 0);
    }

    @Test
    public void getByColorNoneLeft(){
        this.removeAllPink();
        assertEquals(stock.getByColor(Color.PINK), null);
    }

    private void removeAllPink(){
        for(int i=0; i<24; i++){
            stock.remove(Color.PINK);
        }
    }

}
