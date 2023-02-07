package fr.cotedazur.univ.polytech.startingpoint.objectives;

import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.tools.Action;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static fr.cotedazur.univ.polytech.startingpoint.tools.Strategy.*;
import static org.junit.jupiter.api.Assertions.*;

public class StategyTest {

    private Player player;

    @BeforeEach
    public void init(){
        player = new Player("Ted");
    }

    @Test
    public void ActionTest(){
        assertEquals(new Action().getActions().length, 6);
    }

    @Test
    public void StategyTest(){
        assertEquals(PLOTSTRATEGY.getActions().size(), 2);
        assertEquals(PANDASTRATEGY.getActions().size(), 5);
        assertEquals(WITHOUTSTRATEGY.getActions().size(), 6);
    }

    @Test
    public void pickTestPLOTSTRATEGY(){
        player.setStrategy(PLOTSTRATEGY);
        assertTrue(PLOTSTRATEGY.getActions().contains(player.getStrategy().pick()));
    }

    @Test
    public void pickTestPANDASTRATEGY(){
        player.setStrategy(PANDASTRATEGY);
        assertTrue(PANDASTRATEGY.getActions().contains(player.getStrategy().pick()));
    }

    @Test
    public void pickTestWITHOUTSTRATEGY(){
        player.setStrategy(WITHOUTSTRATEGY);
        assertTrue(WITHOUTSTRATEGY.getActions().contains(player.getStrategy().pick()));
    }

    @Test
    public void pickTwoDistinctTestPLOTSTRATEGY(){
        player.setStrategy(PLOTSTRATEGY);
        Action.GameAction[] twoDistinct = player.getStrategy().pickTwoDistinct();
        assertNotEquals(twoDistinct[0], twoDistinct[1]);
    }

    @Test
    public void pickTwoDistinctTestPANDASTRATEGY(){
        player.setStrategy(PANDASTRATEGY);
        Action.GameAction[] twoDistinct = player.getStrategy().pickTwoDistinct();
        assertNotEquals(twoDistinct[0], twoDistinct[1]);
    }

    @Test
    public void pickTwoDistinctTestWITHOUTSTRATEGY(){
        player.setStrategy(WITHOUTSTRATEGY);
        Action.GameAction[] twoDistinct = player.getStrategy().pickTwoDistinct();
        assertNotEquals(twoDistinct[0], twoDistinct[1]);
    }
}
