package supplies;

import tools.Color;
import tools.VectorDirection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static supplies.HexPlot.DIRECTION;
import static tools.Color.*;
import static tools.VectorDirection.*;
import static org.junit.jupiter.api.Assertions.*;

class HexPlotTest {
    Board board;
    HexPlot pond;
    Set<HexPlot> HexPlotNeighborList;

    @BeforeEach
    public void setUp() {
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
        plot.addBamboo(new Bamboo(PINK));
        assertEquals(plot.getBamboos().size(), 0);
        plot.addBamboo(new Bamboo(GREEN));
        assertEquals(plot.getBamboos().size(), 1);
    }
}