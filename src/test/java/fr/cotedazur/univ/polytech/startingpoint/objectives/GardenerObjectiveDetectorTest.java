package fr.cotedazur.univ.polytech.startingpoint.objectives;

import fr.cotedazur.univ.polytech.startingpoint.gameplay.Game;
import fr.cotedazur.univ.polytech.startingpoint.gameplay.Player;
import fr.cotedazur.univ.polytech.startingpoint.supplies.Bamboo;
import fr.cotedazur.univ.polytech.startingpoint.supplies.HexPlot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.IntStream;

import static fr.cotedazur.univ.polytech.startingpoint.tools.Color.GREEN;
import static fr.cotedazur.univ.polytech.startingpoint.tools.PlotImprovement.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class GardenerObjectiveDetectorTest {

    private Player player;
    private Game game;
    private HexPlot hex1;
    private GardenerObjectiveDetector detector;

    @BeforeEach
    void setUp(){
        player = new Player("Ted");
        game = new Game(player, null);
        detector = new GardenerObjectiveDetector(player);
        hex1= new HexPlot(1,0,-1,GREEN);
        ArrayList<Bamboo> bamboos = new ArrayList<>();
        IntStream.range(0, 4).forEach(i -> bamboos.add(new Bamboo(GREEN)));
        hex1.setBamboos(bamboos);
        game.getBoard().add(hex1);
    }

    @Test
    void findFourAndFertilizer(){
        assertNull(detector.findFourAndFertilizer());
        HexPlot hex4= new HexPlot(-1,0,1,GREEN);
        hex4.setImprovement(FERTILIZER);
        ArrayList<Bamboo> bamboos = new ArrayList<>();
        IntStream.range(0, 4).forEach(i -> bamboos.add(new Bamboo(GREEN)));
        hex4.setBamboos(bamboos);
        game.getBoard().add(hex4);
        assertNotNull(detector.findFourAndFertilizer());
    }

    @Test
    void findFourAndPool(){
        assertNull(detector.findFourAndPool());
        HexPlot hex4= new HexPlot(-1,0,1,GREEN);
        hex4.setImprovement(POOL);
        ArrayList<Bamboo> bamboos = new ArrayList<>();
        IntStream.range(0, 4).forEach(i -> bamboos.add(new Bamboo(GREEN)));
        hex4.setBamboos(bamboos);
        game.getBoard().add(hex4);
        assertNotNull(detector.findFourAndPool());
    }

    @Test
    void findFourAndFence(){
        assertNull(detector.findFourAndFence());
        HexPlot hex4= new HexPlot(-1,0,1,GREEN);
        hex4.setImprovement(FENCE);
        ArrayList<Bamboo> bamboos = new ArrayList<>();
        IntStream.range(0, 4).forEach(i -> bamboos.add(new Bamboo(GREEN)));
        hex4.setBamboos(bamboos);
        game.getBoard().add(hex4);
        assertNotNull(detector.findFourAndFence());
    }

    @Test
    void findFourNoImprovement(){
        assertNotNull(detector.findFourNoImprovement());
    }

    @Test
    void findThreeGreenX4(){

        ArrayList<Bamboo> bamboos = new ArrayList<>();
        IntStream.range(0, 3).forEach(i -> bamboos.add(new Bamboo(GREEN)));

        HexPlot hex2 = new HexPlot(0,1,-1,GREEN),
                hex3 = new HexPlot(0,-2,2,GREEN),
                hex4 = new HexPlot(-1,0,1,GREEN);

        game.getBoard().add(hex2);
        game.getBoard().add(hex3);
        game.getBoard().add(hex4);

        hex1.setBamboos(bamboos);
        hex2.setBamboos(bamboos);
        hex3.setBamboos(bamboos);
        hex4.setBamboos(bamboos);

        assertNotNull(detector.findThreeGreenX4());
    }

}
