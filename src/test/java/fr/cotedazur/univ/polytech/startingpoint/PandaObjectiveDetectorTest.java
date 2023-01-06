package fr.cotedazur.univ.polytech.startingpoint;

import objectives.PandaObjectiveDetector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import supplies.Bamboo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tools.Color.*;

public class PandaObjectiveDetectorTest {

    private Player player;
    private PandaObjectiveDetector detector;
    
    @BeforeEach
    public void setUp(){
        player = new Player("Ted");
        detector = new PandaObjectiveDetector(player);
    }

    @Test
    public void findTwoYellow(){
        assertFalse(detector.findTwoYellow());
        player.eatenBamboos.add(new Bamboo(YELLOW));
        player.eatenBamboos.add(new Bamboo(YELLOW));
        assertTrue(detector.findTwoYellow());
    }

    @Test
    public void findThreeGreen(){
        assertFalse(detector.findThreeGreen());
        player.eatenBamboos.add(new Bamboo(GREEN));
        player.eatenBamboos.add(new Bamboo(GREEN));
        assertFalse(detector.findThreeGreen());
        player.eatenBamboos.add(new Bamboo(GREEN));
        assertTrue(detector.findThreeGreen());
    }

    @Test
    public void findOneOfEach(){
        assertFalse(detector.findOneOfEach());
        player.eatenBamboos.add(new Bamboo(GREEN));
        player.eatenBamboos.add(new Bamboo(YELLOW));
        assertFalse(detector.findOneOfEach());
        player.eatenBamboos.add(new Bamboo(GREEN));
        assertFalse(detector.findOneOfEach());
        assertFalse(detector.findOneOfEach());
        player.eatenBamboos.add(new Bamboo(PINK));
        assertTrue(detector.findOneOfEach());
    }
}
