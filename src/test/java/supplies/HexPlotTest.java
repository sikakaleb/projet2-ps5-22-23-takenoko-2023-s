package supplies;

import fr.cotedazur.univ.polytech.startingpoint.Game;
import fr.cotedazur.univ.polytech.startingpoint.Player;
import tools.Color;
import tools.VectorDirection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static fr.cotedazur.univ.polytech.startingpoint.Game.deckOfImprovements;
import static supplies.HexPlot.DIRECTION;
import static tools.Color.*;
import static tools.PlotImprovement.*;
import static tools.VectorDirection.*;
import static org.junit.jupiter.api.Assertions.*;

class HexPlotTest {
    Game game;
    Board board;
    HexPlot pond;
    Set<HexPlot> HexPlotNeighborList;

    @BeforeEach
    public void setUp() {
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
    }

    @Test
    public void addBambooToPlot(){
        assertEquals(pond.getBamboos(), new ArrayList<>());
        HexPlot plot = pond.plotAdd(Q_UP, Color.GREEN );
        assertEquals(plot.getBamboos().size(), 0);
        plot.addBamboo();
        assertEquals(plot.getBamboos().size(), 1);
        assertEquals(plot.getBamboos().get(0).getColor(), GREEN);
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
        try {
            plot.setImprovement(POOL);
        }
        catch (IndexOutOfBoundsException e) {
            assertEquals(e.getMessage(), "Il y a déjà un aménagement sur cette parcelle");
        }
        assertFalse(plot.isIrrigated());
        assertEquals(deckOfImprovements.size(), 8);
    }

    @Test
    public void cannotSetImprovementBamboo(){
        HexPlot plot = new HexPlot(0,1,1, YELLOW);
        plot.addBamboo();
        try {
            plot.setImprovement(FENCE);
        }
        catch (IndexOutOfBoundsException e) {
            assertEquals(e.getMessage(), "Il y a un bambou sur cette parcelle");
        }
        assertNull(plot.getImprovement());
        assertEquals(deckOfImprovements.size(), 9);
    }
}