package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import fr.cotedazur.univ.polytech.startingpoint.supplies.HexPlot;

import static org.junit.jupiter.api.Assertions.*;

class PandaTest {

    Panda pad;
    HexPlot plo;
    @BeforeEach
    void setUp(){
        plo = new HexPlot();
        pad = new Panda(plo);
    }

    @Test
    void getPosition() {
        assertEquals(pad.getPosition(),new HexPlot());
    }

    @Test
    void setPosition() {
    }

    @Test
    void pandaMove() {
        pad.pandaMove(new HexPlot(-3,0,3));
        assertEquals(pad.getPosition(),new HexPlot(-3,0,3));
    }
    @Test
    void pandaMove2() {
        pad.pandaMove(new HexPlot(-3,1,3));
        assertNotEquals(pad.getPosition(),new HexPlot(-3,1,3));
    }

    void pandaMove3() {
        pad.pandaMove(new HexPlot(-3,0,3));
        assertEquals(pad.getPosition(),new HexPlot(-3,0,3));
    }



}