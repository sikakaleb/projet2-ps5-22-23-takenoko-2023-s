package fr.cotedazur.univ.polytech.startingpoint.supplies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class GardenerTest {

    Gardener gardener;
    ByteArrayOutputStream outputStreamCaptor;

    @BeforeEach
    void setUp(){
        gardener = new Gardener(new HexPlot());
        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void getPosition() {
        assertEquals(gardener.getPosition(), new HexPlot());
    }

    @Test
    void setPosition() {
        gardener.setPosition(new HexPlot(-3,0,3));
        assertEquals(gardener.getPosition(), new HexPlot(-3,0,3));

    }

    @Test
    void move() {
        gardener.move(new HexPlot(-3,0,3));
        assertEquals(gardener.getPosition(),new HexPlot(-3,0,3));
    }
    @Test
    void cannotMove() {
        gardener.move(new HexPlot(-3,1,3));
        assertNotEquals(gardener.getPosition(),new HexPlot(-3,1,3));
        //assertTrue(outputStreamCaptor.toString().contains("Le jardinier ne se déplace qu'en ligne droite"));
    }

}