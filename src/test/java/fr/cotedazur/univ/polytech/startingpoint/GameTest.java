package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import supplies.*;
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

    @Test
    public void rollDiceTest(){
        assertTrue(new Dice().roll() instanceof Dice.Condition);
    }

    @Test
    public void placeImprovementTest(){
        game.board.forEach( hexPlot -> {
            assertNull(hexPlot.getImprovement());
        });
        Dice.Condition condition = Dice.Condition.CLOUDS;
        game.actOnWeather(condition);

    }
}