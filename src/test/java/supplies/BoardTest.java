package supplies;

import tools.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static tools.Color.*;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    
    private Board board;
    private DeckOfPlots deckOfPlots;

    @BeforeEach
    public void setUp(){
        board = new Board();
        deckOfPlots = new DeckOfPlots();
    }

    @Test
    void checkPondNeighborTest(){
        HexPlot pond = board.iterator().next();

        assertTrue(pond.isPond());
        assertFalse(board.checkPondNeighbor(pond));

        assertTrue(board.checkPondNeighbor(new HexPlot(-1, 1, 0, GREEN)));
        assertTrue(board.checkPondNeighbor(new HexPlot(1, -1, 0, PINK)));
        assertFalse(board.checkPondNeighbor(new HexPlot(-2, 2, -0, YELLOW)));
    }

    @Test
    void checkTwoPlotNeighborsTest(){
        HexPlot nextHexPlot = board.iterator().next();
        assertFalse(board.checkTwoPlotNeighbors(nextHexPlot));

        assertFalse(board.checkTwoPlotNeighbors(new HexPlot(-1, 1, 0, Color.GREEN)));
        board.add(new HexPlot(-1, 1, 0, Color.GREEN));

        assertTrue(board.checkTwoPlotNeighbors(new HexPlot(0,1,-1, Color.PINK)));
        assertTrue(board.checkTwoPlotNeighbors(new HexPlot(-1,0,1, Color.PINK)));

        assertFalse(board.checkTwoPlotNeighbors(new HexPlot(-2, 2, -0, Color.YELLOW)));
    }

    @Test
    void findAvailableNeighborsTest(){
        HexPlot hexPlot = board.iterator().next();

        assertEquals(board.findAvailableNeighbors(hexPlot).size(), 6);
        System.out.println(hexPlot);
        board.ChoicePlot(deckOfPlots.pickPlot());
        System.out.println(board.findAvailableNeighbors(hexPlot));
        System.out.println(board);
        assertEquals(board.findAvailableNeighbors(hexPlot).size(), 5);

        board.addAll(hexPlot.plotNeighbor());
        assertEquals(board.findAvailableNeighbors(hexPlot).size(), 0);
    }

    @Test
    void addPlotTest() {
        assertEquals(board.size(),1);
        board.ChoicePlot(deckOfPlots.pickPlot());
        board.ChoicePlot(deckOfPlots.pickPlot());
        board.ChoicePlot(deckOfPlots.pickPlot());
        System.out.println(board);
        assertEquals(board.size(),4);
    }

    @Test
    void choicePlotTest() {
        board.ChoicePlot(board.iterator().next());
        System.out.println(board);
        assertEquals(board.size(),2);
    }

}
