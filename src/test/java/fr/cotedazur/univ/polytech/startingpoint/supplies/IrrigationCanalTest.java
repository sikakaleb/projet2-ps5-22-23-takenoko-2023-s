package fr.cotedazur.univ.polytech.startingpoint.supplies;

import fr.cotedazur.univ.polytech.startingpoint.tools.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static fr.cotedazur.univ.polytech.startingpoint.tools.Color.*;
import static fr.cotedazur.univ.polytech.startingpoint.tools.Color.GREEN;
import static org.junit.jupiter.api.Assertions.*;

class IrrigationCanalTest {
    IrrigationCanal canal;
    HexPlot src;
    HexPlot dst;

    @BeforeEach
    void setUp() {
        canal= new IrrigationCanal();
        src= new HexPlot();
        dst=new HexPlot(0,-1,-1, Color.YELLOW);
        canal.setDestPlot(Optional.of(dst));
        canal.setSourcePlot(Optional.of(src));
    }

    @Test
    void getSourcePlot() {
        assertEquals(canal.getSourcePlot().get(),src);
    }

    @Test
    void setSourcePlot() {
        IrrigationCanal can = new IrrigationCanal();
        can.setSourcePlot(Optional.of(src));
        assertEquals(can.getSourcePlot().get(),src);

    }

    @Test
    void getDestPlot() {
        assertEquals(canal.getDestPlot().get(),dst);
    }

    @Test
    void setDestPlot() {
        IrrigationCanal can = new IrrigationCanal();
        can.setDestPlot(Optional.of(dst));
        assertEquals(can.getDestPlot().get(),dst);
    }

    @Test
    void getAvailable() {
        assertFalse(canal.getAvailable());
    }

    @Test
    void setAvailableToTrue() {
        canal.setAvailableToTrue();
        assertTrue(canal.getAvailable());
    }

    @Test
    void setAvailableToFalse() {
        canal.setAvailableToTrue();
        assertTrue(canal.getAvailable());
        canal.setAvailableToFalse();
        assertFalse(canal.getAvailable());
    }
    @Test
    void equal(){
        IrrigationCanal can1 = new IrrigationCanal();
        can1.setDestPlot(Optional.of(dst));
        assertEquals(can1.getDestPlot().get(),dst);
        IrrigationCanal can2 = new IrrigationCanal();
        can1.setDestPlot(Optional.of(dst));
        assertEquals(can1.getDestPlot().get(),dst);
        can2.setCanalId(can1.getCanalId());
        assertEquals(can1,can2);
    }

    @Test
    void canbeIntheSameNetwork() {
        HexPlot pond =new HexPlot();
        HexPlot hex =new HexPlot(0,1,-1,YELLOW);
        HexPlot hex2 =new HexPlot(0,-1,1,PINK);
        hex2.getBamboos().add(new Bamboo(PINK));
        HexPlot hex3 =new HexPlot(1,-1,0,GREEN);
        hex3.getBamboos().add(new Bamboo(GREEN));
        HexPlot hex4 =new HexPlot(1,-2,1,GREEN);
        hex4.getBamboos().add(new Bamboo(GREEN));
        hex4.getBamboos().add(new Bamboo(GREEN));
        hex4.getBamboos().add(new Bamboo(GREEN));
        IrrigationCanal can1=new IrrigationCanal(pond,hex2);
        IrrigationCanal can2=new IrrigationCanal(pond,hex3);
        IrrigationCanal can3=new IrrigationCanal(hex2,hex3);
        IrrigationCanal can4=new IrrigationCanal(hex3,hex4);
        assertTrue(can1.canbeIntheSameNetwork(can3));
        assertTrue(can3.canbeIntheSameNetwork(can4));

    }
}