package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static fr.cotedazur.univ.polytech.startingpoint.HexPlot.DIRECTION;
import static fr.cotedazur.univ.polytech.startingpoint.VectorDirection.*;
import static org.junit.jupiter.api.Assertions.*;

class HexPlotTest {
    HexPlot initialHexPlot;
    Set<HexPlot> HexPlotNeighborList;

    @BeforeEach
    public void setUp() {
        initialHexPlot = new HexPlot();
        HexPlotNeighborList = new HashSet<>();
        HexPlotNeighborList.add(new HexPlot(0,-1,1));
        HexPlotNeighborList.add(new HexPlot(0,1,-1));
        HexPlotNeighborList.add(new HexPlot(+1, -1, 0));
        HexPlotNeighborList.add(new HexPlot(-1, +1, 0));
        HexPlotNeighborList.add(new HexPlot(-1, 0, +1));
        HexPlotNeighborList.add(new HexPlot(+1, 0, -1));

    }
    @Test
    void plotAdd() {
        HexPlot hex1= initialHexPlot.plotAdd(Q_UP);
        assertEquals(hex1,new HexPlot(0,-1,1));
    }

    @Test
    void plotNeighbor() {
        Set<HexPlot> temporalSet = new HashSet<>();
        for (VectorDirection vec:DIRECTION) {
            temporalSet.add(initialHexPlot.plotAdd(vec));
        }
        assertTrue(temporalSet.equals(HexPlotNeighborList));

    }
}