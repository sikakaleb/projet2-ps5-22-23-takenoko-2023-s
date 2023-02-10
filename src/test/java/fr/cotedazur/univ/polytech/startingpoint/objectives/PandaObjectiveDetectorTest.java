package fr.cotedazur.univ.polytech.startingpoint.objectives;

import fr.cotedazur.univ.polytech.startingpoint.gameplay.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import fr.cotedazur.univ.polytech.startingpoint.supplies.Bamboo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static fr.cotedazur.univ.polytech.startingpoint.tools.Color.*;

class PandaObjectiveDetectorTest {

    private Player player;
    private PandaObjectiveDetector detector;

    @BeforeEach
    void setUp(){
        player = new Player("Ted");
        detector = new PandaObjectiveDetector(player);
    }

    @Test
    void findTwoYellowTest(){
        assertFalse(detector.findTwoYellow());
        player.getEatenBamboos().add(new Bamboo(YELLOW));
        player.getEatenBamboos().add(new Bamboo(YELLOW));
        assertTrue(detector.findTwoYellow());
    }

    @Test
    void findtwoGreenTest(){
        assertFalse(detector.findTwoGreen());
        player.getEatenBamboos().add(new Bamboo(GREEN));
        player.getEatenBamboos().add(new Bamboo(GREEN));
        assertTrue(detector.findTwoGreen());
    }

    @Test
    void findTwoPinkTest(){
        assertFalse(detector.findTwoPink());
        player.getEatenBamboos().add(new Bamboo(PINK));
        player.getEatenBamboos().add(new Bamboo(PINK));
        assertTrue(detector.findTwoPink());
    }

    @Test
    void findThreeGreenTest(){
        assertFalse(detector.findThreeGreen());
        player.getEatenBamboos().add(new Bamboo(GREEN));
        player.getEatenBamboos().add(new Bamboo(GREEN));
        assertFalse(detector.findThreeGreen());
        player.getEatenBamboos().add(new Bamboo(GREEN));
        assertTrue(detector.findThreeGreen());
    }

    @Test
    void findOneOfEachTest(){
        assertFalse(detector.findOneOfEach());
        player.getEatenBamboos().add(new Bamboo(GREEN));
        player.getEatenBamboos().add(new Bamboo(YELLOW));
        assertFalse(detector.findOneOfEach());
        player.getEatenBamboos().add(new Bamboo(GREEN));
        assertFalse(detector.findOneOfEach());
        assertFalse(detector.findOneOfEach());
        player.getEatenBamboos().add(new Bamboo(PINK));
        assertTrue(detector.findOneOfEach());
    }
}
