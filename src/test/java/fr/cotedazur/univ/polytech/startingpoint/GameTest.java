package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import supplies.Bamboo;
import supplies.BambooStock;
import supplies.Board;
import supplies.HexPlot;
import tools.Color;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.IntStream;

import static fr.cotedazur.univ.polytech.startingpoint.Game.*;
import static org.junit.jupiter.api.Assertions.*;
import static tools.Color.*;

class GameTest {

    private Game game;

    @BeforeEach
    public void init(){
        game = new Game(new Player("Ted"), new Player("Wilfried"));
    }

    @Test
    void choiceObjective() {
    }

    @Test
    void choicePlot() {
    }

    @Test
    public void cannotAddBambooToPond(){
        try {
            game.addBambooToPlot(board.getPond());
        }
        catch (IndexOutOfBoundsException e) {
            assertEquals(e.getMessage(), "On ne pose pas un bamboo sur la parcelle Etang");
        }
        assertTrue(board.getPond().getBamboos().isEmpty());
        assertEquals(bambooStock.size(), 90);
    }

    @Test
    public void cannotAddBambooNotIrrigated(){
        HexPlot notIrrigated = new HexPlot(1,-1,1, PINK);
        board.add(notIrrigated);
        try {
            game.addBambooToPlot(notIrrigated);
        }
        catch (IndexOutOfBoundsException e) {
            assertEquals(e.getMessage(), "Cette parcelle n'est pas irriguée");
        }
        assertTrue(notIrrigated.getBamboos().isEmpty());
        assertEquals(bambooStock.size(), 90);
    }

    @Test
    public void cannotAddGREENBamboo(){
        bambooStock.removeIf( bamboo -> bamboo.getColor() == GREEN);
        assertEquals(bambooStock.count(GREEN), 0);

        HexPlot plot = new HexPlot(0,0,1, GREEN);
        try {
            game.addBambooToPlot(plot);
        }
        catch (IndexOutOfBoundsException e) {
            assertEquals(e.getMessage(), "Il y a plus de bambou " + plot.getColor());
        }
        assertTrue(plot.getBamboos().isEmpty());
    }

    @Test
    public void cannotAddPINKBamboo(){
        bambooStock.removeIf( bamboo -> bamboo.getColor() == PINK);
        assertEquals(bambooStock.count(PINK), 0);

        HexPlot plot = new HexPlot(0,0,1, PINK);
        try {
            game.addBambooToPlot(plot);
        }
        catch (IndexOutOfBoundsException e) {
            assertEquals(e.getMessage(), "Il y a plus de bambou " + plot.getColor());
        }
        assertTrue(plot.getBamboos().isEmpty());
    }

    @Test
    public void cannotAddYELLOWBamboo(){
        bambooStock.removeIf( bamboo -> bamboo.getColor() == YELLOW);
        assertEquals(bambooStock.count(YELLOW), 0);

        HexPlot plot = new HexPlot(0,0,1, YELLOW);
        try {
            game.addBambooToPlot(plot);
        }
        catch (IndexOutOfBoundsException e) {
            assertEquals(e.getMessage(), "Il y a plus de bambou " + plot.getColor());
        }
        assertTrue(plot.getBamboos().isEmpty());
    }

    @Test
    public void sproutBamboo(){
        HexPlot plot = new HexPlot(0,0,1, PINK);
        board.add(plot);
        assertEquals(plot.getBamboos().size(),1);
        assertEquals(plot.getBamboos().get(0).getColor(),plot.getColor());
        assertEquals(bambooStock.size(), 89);
        assertEquals(bambooStock.count(PINK), 23);
    }

    @Test
    public void addBambooToPlot(){
        HexPlot plot = new HexPlot(0,0,1, PINK);
        board.add(plot);
        plot.addBamboo();
        assertEquals(plot.getBamboos().size(),2);
        assertEquals(plot.getBamboos().get(0).getColor(),plot.getColor());
        assertEquals(bambooStock.size(), 88);
        assertEquals(bambooStock.count(PINK), 22);
    }
}