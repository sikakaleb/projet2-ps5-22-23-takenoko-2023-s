package fr.cotedazur.univ.polytech.startingpoint.supplies;

import fr.cotedazur.univ.polytech.startingpoint.gameplay.Game;
import fr.cotedazur.univ.polytech.startingpoint.gameplay.Player;
import fr.cotedazur.univ.polytech.startingpoint.tools.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static fr.cotedazur.univ.polytech.startingpoint.tools.Color.*;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    private Game game;
    private Board board;
    private DeckOfPlots deckOfPlots;

    @BeforeEach
    public void setUp(){
        game=new Game(new Player("Ted"),new Player("Wilfried"));
        board=new Board();
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
        game.getBoard().add(hex1);
        game.getBoard().add(hex2);
        game.getBoard().add(hex3);
        game.getBoard().add(hex4);
        game.getBoard().add(hex5);
        game.getBoard().add(hex6);;
    }

    @Test
    void getLastHexPlotTest(){
        HexPlot hex = new HexPlot(0,1,1,GREEN);
        game.getBoard().add(hex);
        assertEquals(hex,game.getBoard().getLastHexPlot());
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
        board.choicePlot(deckOfPlots.pickPlot());
        assertEquals(board.findAvailableNeighbors(hexPlot).size(), 5);

        board.addAll(hexPlot.plotNeighbor());
        assertEquals(board.findAvailableNeighbors(hexPlot).size(), 0);
    }

    @Test
    void addPlotTest() {
        assertEquals(board.size(),1);
        board.choicePlot(deckOfPlots.pickPlot());
        board.choicePlot(deckOfPlots.pickPlot());
        board.choicePlot(deckOfPlots.pickPlot());
        assertEquals(board.size(),4);
    }

    @Test
    void choicePlotTest() {
        board.choicePlot(board.iterator().next());
        assertEquals(board.size(),2);
    }

    @Test
    void getNewPositionPossibilitiesTest() {
        assertEquals(game.getBoard().getNewPositionPossibilities().size(),6);
    }

    @Test
    public void choosePlotForImprovementTest(){
        assertEquals(game.getBoard().size(),7);
        game.getBoard().getLastHexPlot().getBamboos().clear();

        HexPlot plot = game.getBoard().choosePlotForImprovement();
        assertEquals(game.getBoard().size(),7);
        assertTrue(game.getBoard().contains(plot));
        assertFalse(plot.isPond());
        assertNull(plot.getImprovement());
        assertTrue(plot.getBamboos().isEmpty());

        game.getBoard().removeIf(hexPlot -> !hexPlot.isPond());
        assertEquals(game.getBoard().size(), 1);

        Board emptyBoard = new Board();
        assertEquals(emptyBoard.choosePlotForImprovement(), null);
    }

}
