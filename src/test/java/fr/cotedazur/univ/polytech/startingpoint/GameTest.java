package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.supplies.Bamboo;
import fr.cotedazur.univ.polytech.startingpoint.supplies.HexPlot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import fr.cotedazur.univ.polytech.startingpoint.supplies.Dice;


import static fr.cotedazur.univ.polytech.startingpoint.tools.Color.*;
import static org.junit.jupiter.api.Assertions.*;


class GameTest {

    private Game game;
    Player player1;
    Player player2;

    @BeforeEach
    public void init(){
        player1 = new Player("Ted");
        player2 = new Player("Wilfried");
        game = new Game(player1, player2);
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
    void choiceObjectiveTest() {
        game.choiceObjective(player1);
        assertEquals(game.getObjective().size(), 29);
        assertTrue(game.choiceObjective(player1));
        assertTrue(game.choiceObjective(player2));
        game.choiceObjective(player2);
        assertEquals(game.getObjective().size(), 26);
        assertTrue(game.choiceObjective(player2));
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
        game.board.getLastHexPlot().getBamboos().clear();
        Dice.Condition condition = Dice.Condition.CLOUDS;
        game.actOnWeather(condition);

    }
}