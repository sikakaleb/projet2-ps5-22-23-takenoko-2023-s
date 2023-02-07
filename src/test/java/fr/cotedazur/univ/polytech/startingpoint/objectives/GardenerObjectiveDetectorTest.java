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
import static fr.cotedazur.univ.polytech.startingpoint.tools.PlotImprovement.FERTILIZER;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GardenerObjectiveDetectorTest {

    private Player player;
    private Game game;
    private HexPlot hex1;
    private GardenerObjectiveDetector detector;
    
    @BeforeEach
    public void setUp(){
        player = new Player("Ted");
        game = new Game(player, null);
        detector = new GardenerObjectiveDetector(player);
        hex1= new HexPlot(1,0,-1,GREEN);
        ArrayList<Bamboo> bamboos = new ArrayList<>();
        IntStream.range(0, 4).forEach(i -> bamboos.add(new Bamboo(GREEN)));
        hex1.setBamboos(bamboos);
        game.board.add(hex1);
    }

    @Test
    public void findFourAndFertilizer(){
        assertNull(detector.findFourAndFertilizer());
        HexPlot hex4= new HexPlot(-1,0,1,GREEN);
        hex4.setImprovement(FERTILIZER);
        ArrayList<Bamboo> bamboos = new ArrayList<>();
        IntStream.range(0, 4).forEach(i -> bamboos.add(new Bamboo(GREEN)));
        hex4.setBamboos(bamboos);
        game.board.add(hex4);
        assertNotNull(detector.findFourAndFertilizer());
    }

    @Test
    public void findFourNoImprovement(){
        assertNotNull(detector.findFourNoImprovement());
    }

    @Test
    public void findThreeGreenX4(){

        ArrayList<Bamboo> bamboos = new ArrayList<>();
        IntStream.range(0, 3).forEach(i -> bamboos.add(new Bamboo(GREEN)));

        HexPlot hex2 = new HexPlot(0,1,-1,GREEN),
                hex3 = new HexPlot(0,-2,2,GREEN),
                hex4 = new HexPlot(-1,0,1,GREEN);

        game.board.add(hex2);
        game.board.add(hex3);
        game.board.add(hex4);

        hex1.setBamboos(bamboos);
        hex2.setBamboos(bamboos);
        hex3.setBamboos(bamboos);
        hex4.setBamboos(bamboos);

        assertNotNull(detector.findThreeGreenX4());
    }

}
