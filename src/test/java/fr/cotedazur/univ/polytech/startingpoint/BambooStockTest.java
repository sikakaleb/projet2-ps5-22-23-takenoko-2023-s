package fr.cotedazur.univ.polytech.startingpoint;

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
        assertEquals(stock.count(PlotColor.GREEN), 36);
        assertEquals(stock.count(PlotColor.YELLOW), 30);
        assertEquals(stock.count(PlotColor.PINK), 24);
    }

    @Test
    public void getByColor(){
        Bamboo greenBamboo = stock.getByColor(PlotColor.GREEN);
        Bamboo yellowBamboo = stock.getByColor(PlotColor.YELLOW);
        Bamboo pinkBamboo = stock.getByColor(PlotColor.PINK);
        assert(greenBamboo instanceof Bamboo);
        assertEquals(greenBamboo.getColor(), PlotColor.GREEN);
        assert(yellowBamboo instanceof Bamboo);
        assertEquals(yellowBamboo.getColor(), PlotColor.YELLOW);
        assert(pinkBamboo instanceof Bamboo);
        assertEquals(pinkBamboo.getColor(), PlotColor.PINK);
    }

    @Test
    public void remove(){
        stock.remove(PlotColor.GREEN);
        assertEquals(stock.size(), 89);
        this.removeAllPink();
        assertEquals(stock.size(), 65);
        assertFalse(stock.remove(PlotColor.PINK));
    }

    @Test
    public void countNoneLeft(){
        this.removeAllPink();
        assertEquals(stock.count(PlotColor.PINK), 0);
    }

    @Test
    public void getByColorNoneLeft(){
        this.removeAllPink();
        assertEquals(stock.getByColor(PlotColor.PINK), null);
    }

    private void removeAllPink(){
        for(int i=0; i<24; i++){
            stock.remove(PlotColor.PINK);
        }
    }

}
