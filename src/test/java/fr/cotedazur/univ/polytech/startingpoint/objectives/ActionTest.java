package fr.cotedazur.univ.polytech.startingpoint.objectives;

import fr.cotedazur.univ.polytech.startingpoint.Game;
import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.tools.Action;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ActionTest {

    private Game game;

    @BeforeEach
    public void init(){
        game = new Game(new Player("Ted"), null);
    }

    @Test
    public void ActionTest(){
        assertEquals(new Action().getActions().length, 8);
    }

    @Test
    public void pickTest(){
        Action action = new Action();
        assertTrue(Arrays.stream(action.getActions()).toList().contains(action.pick()));
    }

    @Test
    public void pickTwoDistinctTest(){
        Action.GameAction[] twoDistinct = new Action().pickTwoDistinct();
        assertNotEquals(twoDistinct[0], twoDistinct[1]);
    }
}
