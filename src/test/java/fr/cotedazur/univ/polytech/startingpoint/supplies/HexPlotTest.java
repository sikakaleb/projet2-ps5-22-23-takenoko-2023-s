package fr.cotedazur.univ.polytech.startingpoint.supplies;

import fr.cotedazur.univ.polytech.startingpoint.gameplay.Game;
import fr.cotedazur.univ.polytech.startingpoint.gameplay.Player;
import fr.cotedazur.univ.polytech.startingpoint.tools.Color;
import fr.cotedazur.univ.polytech.startingpoint.tools.VectorDirection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static fr.cotedazur.univ.polytech.startingpoint.gameplay.Game.deckOfImprovements;
import static fr.cotedazur.univ.polytech.startingpoint.supplies.HexPlot.DIRECTION;
import static fr.cotedazur.univ.polytech.startingpoint.tools.Color.*;
import static fr.cotedazur.univ.polytech.startingpoint.tools.PlotImprovement.*;
import static fr.cotedazur.univ.polytech.startingpoint.tools.VectorDirection.Q_UP;
import static org.junit.jupiter.api.Assertions.*;

class HexPlotTest {
    Game game;
    Board board;
    HexPlot pond;
    Set<HexPlot> HexPlotNeighborList;
    private ByteArrayOutputStream outputStreamCaptor;

    @BeforeEach
    public void setUp() {
        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        game = new Game(new Player("Ted"), new Player("Wilfried"));
        board = new Board();
        pond = board.getPond();
        HexPlotNeighborList = new HashSet<>();
        HexPlotNeighborList.add(new HexPlot(0,-1,1, Color.BLANK));
        HexPlotNeighborList.add(new HexPlot(0,1,-1, Color.BLANK));
        HexPlotNeighborList.add(new HexPlot(+1, -1, 0, Color.BLANK));
        HexPlotNeighborList.add(new HexPlot(-1, +1, 0, Color.BLANK));
        HexPlotNeighborList.add(new HexPlot(-1, 0, +1, Color.BLANK));
        HexPlotNeighborList.add(new HexPlot(+1, 0, -1, Color.BLANK));
    }

    @Test
    void isPondTest(){
        assertTrue(pond.isPond());
        HexPlotNeighborList.forEach( hexPlot -> {
            assertFalse(hexPlot.isPond());
        });
    }

    @Test
    void plotAddTest() {
        HexPlot hex1 = pond.plotAdd(Q_UP, Color.GREEN );
        assertEquals(hex1,new HexPlot(0,-1,1, Color.GREEN));
    }

    @Test
    void plotNeighborTest() {
        Set<HexPlot> temporalSet = new HashSet<>();
        for (VectorDirection vec:DIRECTION) {
            temporalSet.add(pond.plotAdd(vec, Color.BLANK));
        }
        assertTrue(temporalSet.equals(HexPlotNeighborList));
    }

    @Test
    void isAdjacentWithAnotherOnSameColor() {
        List<HexPlot> list = new ArrayList<>();
        HexPlot tempHex=new HexPlot(-3,2,1,YELLOW);
        list.add(tempHex);
        list.add(new HexPlot(-2,2,0,PINK));
        list.add(new HexPlot(-2,1,1,PINK));
        list.add(new HexPlot(-3,1,2,YELLOW));
        assertTrue(tempHex.isAdjacentWithAnotherOnSameColor(list));

        HexPlot tempHex2=new HexPlot(-3,2,2,GREEN);
        assertFalse(tempHex2.isAdjacentWithAnotherOnSameColor(list));
    }

    @Test
    public void addBambooTest(){
        assertEquals(pond.getBamboos(), new ArrayList<>());
        HexPlot plot = pond.plotAdd(Q_UP, Color.GREEN );
        assertEquals(plot.getBamboos().size(), 0);
        plot.addBamboo();
        assertEquals(plot.getBamboos().size(), 1);
        assertEquals(plot.getBamboos().get(0).getColor(), GREEN);

        plot.addBamboo();
        plot.addBamboo();
        plot.addBamboo();
        //assertThrowsExactly(IndexOutOfBoundsException.class, plot::addBamboo);
        plot.addBamboo();
        //assertTrue(outputStreamCaptor.toString().contains("Il y a trop de bambous sur cette parcelle"));

        //assertThrowsExactly(IndexOutOfBoundsException.class, pond::addBamboo);
        pond.addBamboo();
        //assertTrue(outputStreamCaptor.toString().contains("On ne pose pas un bamboo sur la parcelle Etang"));

        HexPlot notIrrigated = new HexPlot(1,-1,1, PINK);
        board.add(notIrrigated);
        //assertThrowsExactly(IndexOutOfBoundsException.class, notIrrigated::addBamboo);
        notIrrigated.addBamboo();
        //assertTrue(outputStreamCaptor.toString().contains("On ne pose pas un bambou sur une parcelle non irriguée"));
    }

    @Test
    public void irrigatedTest() {
        for (HexPlot pondNeighbor : pond.plotNeighbor()) {
            assertTrue(pondNeighbor.isIrrigated());
        }
    }
    @Test
    public void notIrrigatedTest() {
        HexPlot notIrrigated = new HexPlot(1,-1,1, PINK);
        board.add(notIrrigated);
        assertFalse(notIrrigated.isIrrigated());
    }

    @Test
    public void setImprovement(){
        HexPlot plot = new HexPlot(1,-1,1, PINK);
        assertNull(plot.getImprovement());
        plot.setImprovement(POOL);
        assertEquals(plot.getImprovement(), POOL);
        assertEquals(deckOfImprovements.size(), 8);
    }

    @Test
    public void irrigateWithPOOL(){
        HexPlot notIrrigated = new HexPlot(1,-1,1, PINK);
        assertFalse(notIrrigated.isIrrigated());
        notIrrigated.setImprovement(POOL);
        assertTrue(notIrrigated.isIrrigated());
    }

    @Test
    public void sproutWhenIrrigateWithPOOL(){
        HexPlot notIrrigated = new HexPlot(1,-1,1, PINK);
        notIrrigated.setImprovement(POOL);
        assertEquals(notIrrigated.getBamboos().size(), 1);
    }

    @Test
    public void cannotSetImprovementAlreadyOne(){
        HexPlot plot = new HexPlot(1,1,1, YELLOW);
        plot.setImprovement(FENCE);
        plot.setImprovement(POOL);
        //assertTrue(outputStreamCaptor.toString().contains("Il y a déjà un aménagement sur cette parcelle"));
        assertFalse(plot.isIrrigated());
        assertEquals(deckOfImprovements.size(), 8);
    }

    @Test
    public void cannotSetImprovementBamboo(){
        HexPlot plot = new HexPlot(0,1,1, YELLOW);
        plot.addBamboo();
        plot.setImprovement(FENCE);
        //assertTrue(outputStreamCaptor.toString().contains("Impossible de placer l'emplacement, il y a un bambou sur cette parcelle"));
        assertNull(plot.getImprovement());
        assertEquals(deckOfImprovements.size(), 9);
    }

    @Test
    public void growTwiceWithFERTILIZER(){
        HexPlot plot = new HexPlot(0,1,1, YELLOW);
        plot.setImprovement(FERTILIZER);
        plot.addBamboo();
        assertEquals(plot.getBamboos().size(), 2);
        plot.addBamboo();
        assertEquals(plot.getBamboos().size(), 4);
        plot.addBamboo();
        plot.addBamboo();
        plot.addBamboo();
        assertEquals(plot.getBamboos().size(), 4);
    }

    @Test
    void setIrrigatedToTrue() {
        HexPlot hex = new HexPlot(-2,1,1);
        hex.setIrrigatedToTrue();
        assertTrue(hex.isIrrigated());
    }

    @Test
    void setIrrigatedToFalse() {
        HexPlot hex = new HexPlot(-2,1,1);
        hex.setIrrigatedToTrue();
        assertTrue(hex.isIrrigated());
        hex.setIrrigatedToFalse();
        assertFalse(hex.isIrrigated());
    }
    @Test
    void test8(){
        HexPlot hex= new HexPlot(1,-1,1,YELLOW);
        hex.setIrrigatedToTrue();
        hex.getBamboos().add(new Bamboo(YELLOW));
        assertEquals(hex,new HexPlot(1,-1,1));
    }
    @Test
    void haveSamePosition(){
        HexPlot hex= new HexPlot(1,-5,4,GREEN);
        hex.setIrrigatedToTrue();
        hex.getBamboos().add(new Bamboo(GREEN));
        assertEquals(hex,new HexPlot(1,-5,4));
    }
}