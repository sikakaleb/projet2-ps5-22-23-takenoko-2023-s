package fr.cotedazur.univ.polytech.startingpoint.supplies;

import fr.cotedazur.univ.polytech.startingpoint.Game;
import fr.cotedazur.univ.polytech.startingpoint.Player;
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
        game.board.add(hex1);
        game.board.add(hex2);
        game.board.add(hex3);
        game.board.add(hex4);
        game.board.add(hex5);
        game.board.add(hex6);
    }

    @Test
    void getLastHexPlotTest(){
        HexPlot hex = new HexPlot(0,1,1,GREEN);
        game.board.add(hex);
        assertEquals(hex,game.board.getLastHexPlot());
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
    void getNewPositionPossibilitiesTest() {
        assertEquals(game.board.getNewPositionPossibilities().size(),6);
    }

    @Test
    public void choosePlotForImprovementTest(){
        assertEquals(game.board.size(),7);
        game.board.getLastHexPlot().getBamboos().clear();

        HexPlot plot = game.board.choosePlotForImprovement();
        assertEquals(game.board.size(),7);
        assertTrue(game.board.contains(plot));
        assertFalse(plot.isPond());
        assertNull(plot.getImprovement());
        assertTrue(plot.getBamboos().isEmpty());

        game.board.removeIf(hexPlot -> !hexPlot.isPond());
        assertEquals(game.board.size(), 1);

        Board emptyBoard = new Board();
        assertEquals(emptyBoard.choosePlotForImprovement(), null);
    }

}
