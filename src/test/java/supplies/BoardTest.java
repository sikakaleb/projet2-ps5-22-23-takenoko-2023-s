package supplies;

import fr.cotedazur.univ.polytech.startingpoint.Game;
import fr.cotedazur.univ.polytech.startingpoint.Player;
import tools.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static tools.Color.*;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    
    private Board board;
    private DeckOfPlots deckOfPlots;


    private Game game;
    @BeforeEach
    public void setUp(){
        game=new Game(new Player("fred"),new Player("akossiwa"));
        board = new Board();
        deckOfPlots = new DeckOfPlots();
        HexPlot hex1= new HexPlot(1,0,-1,GREEN);
        hex1.getBamboos().add(new Bamboo(GREEN));
        HexPlot hex2= new HexPlot(0,1,-1,YELLOW);
        hex2.getBamboos().add(new Bamboo(YELLOW));
        HexPlot hex3= new HexPlot(0,-2,2,PINK);
        hex3.getBamboos().add(new Bamboo(PINK));
        HexPlot hex4= new HexPlot(-1,0,1,GREEN);
        hex4.getBamboos().add(new Bamboo(GREEN));
        HexPlot hex5= new HexPlot(0,-1,1,YELLOW);
        hex5.getBamboos().add(new Bamboo(YELLOW));
        HexPlot hex6= new HexPlot(0,+3,-3,PINK);
        hex6.getBamboos().add(new Bamboo(PINK));
        game.board.add(hex1);
        game.board.add(hex2);
        game.board.add(hex3);
        game.board.add(hex4);
        game.board.add(hex5);
        game.board.add(hex6);
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

    @Test
    void pandaNewPositionPossibilities() {
        assertEquals(game.board.pandaNewPositionPossibilities().size(),6);
    }

    @Test
    void testPandaNewPositionPossibilities() {
    }
}
