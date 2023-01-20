package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}