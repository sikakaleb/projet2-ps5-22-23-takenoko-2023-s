package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static fr.cotedazur.univ.polytech.startingpoint.HexPlot.DIRECTION;
import static fr.cotedazur.univ.polytech.startingpoint.PlotColor.PINK;
import static fr.cotedazur.univ.polytech.startingpoint.PlotColor.YELLOW;
import static fr.cotedazur.univ.polytech.startingpoint.VectorDirection.*;
import static org.junit.jupiter.api.Assertions.*;

class HexPlotTest {
    HexPlot initialHexPlot;
    Set<HexPlot> HexPlotNeighborList;

    @BeforeEach
    public void setUp() {
        initialHexPlot = new HexPlot();
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
        assertTrue(initialHexPlot.isPond());
        HexPlotNeighborList.forEach( hexPlot -> {
            assertFalse(hexPlot.isPond());
        });
    }

    @Test
    void plotAddTest() {
        HexPlot hex1= initialHexPlot.plotAdd(Q_UP, Color.GREEN );
        assertEquals(hex1,new HexPlot(0,-1,1, Color.GREEN));
    }

    @Test
    void plotNeighborTest() {
        Set<HexPlot> temporalSet = new HashSet<>();
        for (VectorDirection vec:DIRECTION) {
            temporalSet.add(initialHexPlot.plotAdd(vec, Color.BLANK));
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
}