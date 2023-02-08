package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.gameplay.Game;
import fr.cotedazur.univ.polytech.startingpoint.gameplay.Player;
import fr.cotedazur.univ.polytech.startingpoint.objectives.Objective;
import fr.cotedazur.univ.polytech.startingpoint.objectives.PandaObjective;
import fr.cotedazur.univ.polytech.startingpoint.objectives.PlotObjective;
import fr.cotedazur.univ.polytech.startingpoint.supplies.*;
import fr.cotedazur.univ.polytech.startingpoint.tools.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static fr.cotedazur.univ.polytech.startingpoint.gameplay.Game.irrigationStock;
import static fr.cotedazur.univ.polytech.startingpoint.tools.Color.*;
import static fr.cotedazur.univ.polytech.startingpoint.tools.PandaObjectiveConfiguration.*;
import static fr.cotedazur.univ.polytech.startingpoint.tools.PlotObjectiveConfiguration.DIRECTSAMEPLOTS;
import static fr.cotedazur.univ.polytech.startingpoint.tools.Strategy.WITHOUTSTRATEGY;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player player1;
    Player player2;

    Game game;
    List<Objective> singlelist;

    PlotObjective directPlotObj = new PlotObjective(2, DIRECTSAMEPLOTS);
    PlotObjective indirectPlotObj = new PlotObjective(3, DIRECTSAMEPLOTS);
    PlotObjective triangularPlotObj = new PlotObjective(4, DIRECTSAMEPLOTS);
    PlotObjective quadriPlotObj = new PlotObjective(4, DIRECTSAMEPLOTS);

    PandaObjective twoYellow = new PandaObjective(4, TWO_YELLOW, new Color[]{YELLOW});

    PandaObjective twoGreen = new PandaObjective(3, TWO_GREEN, new Color[]{GREEN});

    PandaObjective twoPink = new PandaObjective(5, TWO_PINK, new Color[]{PINK});

    PandaObjective threeGreen = new PandaObjective(4, THREE_GREEN, new Color[]{GREEN});

    PandaObjective oneOfEach = new PandaObjective(6, ONE_OF_EACH, new Color[]{YELLOW, GREEN, PINK});

    ByteArrayOutputStream outputStreamCaptor;

    @BeforeEach
    public void setUp() {
        player1 = new Player("Ted");
        player2 = new Player("Willfried");
        /*******************************/
        player1.setMaxUnmetObj(4);
        /*******************************/
        player1.addNewObjective(directPlotObj);
        player1.addNewObjective(indirectPlotObj);
        game = new Game(player1, player2);
        HexPlot hex1 = new HexPlot(1, 0, -1, GREEN);
        hex1.getBamboos().add(new Bamboo(GREEN));
        HexPlot hex2 = new HexPlot(0, 1, -1, YELLOW);
        hex2.getBamboos().add(new Bamboo(YELLOW));
        HexPlot hex3 = new HexPlot(0, -2, 2, PINK);
        hex3.getBamboos().add(new Bamboo(PINK));
        HexPlot hex4 = new HexPlot(-1, 0, 1, GREEN);
        hex4.getBamboos().add(new Bamboo(GREEN));
        HexPlot hex5 = new HexPlot(0, -1, 1, YELLOW);
        hex5.getBamboos().add(new Bamboo(YELLOW));
        HexPlot hex6 = new HexPlot(0, +3, -3, PINK);
        hex6.getBamboos().add(new Bamboo(PINK));
        game.board.add(hex1);
        game.board.add(hex2);
        game.board.add(hex3);
        game.board.add(hex4);
        game.board.add(hex5);
        game.board.add(hex6);

        singlelist = new ArrayList<>();
        singlelist.add(directPlotObj);
        singlelist.add(indirectPlotObj);

        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void getHeightTest() {
        assertEquals(player1.getHeight(), 1);
    }

    @Test
    void getNameTest() {
        assertEquals(player1.getName(), "Ted");
    }

    @Test
    void getObjectiveAchievedTest() {
        List<Objective> objList = new ArrayList<>();
        assertTrue(player1.getObjectiveAchieved().equals(objList));
    }

    @Test
    void addObjectiveAchievedTest() {
        player1.addObjectiveAchieved(triangularPlotObj);
        int lastIdx = player1.getObjectiveAchieved().size() - 1;
        assertEquals(player1.objectiveAchieved.get(lastIdx), triangularPlotObj);
    }

    @Test
    void getUnMetObjectivesTest() {
        assertTrue(player1.getUnMetObjectives().equals(singlelist));
    }

    @Test
    void addNewObjectiveTest() {
        player1.addNewObjective(quadriPlotObj);
        int lastIdx = player1.getUnMetObjectives().size() - 1;
        assertEquals(player1.getUnMetObjectives().get(lastIdx), quadriPlotObj);
    }

    @Test
    void withdrawUnMetObjectiveTest() {
        player1.withdrawUnMetObjective(quadriPlotObj);
        assertFalse(player1.withdrawUnMetObjective(quadriPlotObj));
    }

    @Test
    void validateUnMetObjectivesTest() {
        List<Objective> objList = new ArrayList<>();
        player1.addNewObjective(quadriPlotObj);
        player1.validateUnMetObjectives(quadriPlotObj);
        assertTrue(player1.getUnMetObjectives().equals(singlelist));

    }

    @Test
    void getCumulOfpointTest() {
        player1.validateUnMetObjectives(quadriPlotObj);
        assertEquals(player1.getScore(), 4);
    }

    @Test
    public void detectPandaObjectiveTWO_YELLOW() {
        assertEquals(player1.eatenBamboos.size(), 0);
        player1.addNewObjective(new PandaObjective(4, TWO_YELLOW, new Color[]{YELLOW}));
        player1.eatenBamboos.add(new Bamboo(GREEN));
        player1.eatenBamboos.add(new Bamboo(YELLOW));
        assertNull(player1.detectPandaObjective());
        assertEquals(player1.eatenBamboos.size(), 2);
        player1.eatenBamboos.add(new Bamboo(YELLOW));
        assertEquals(player1.eatenBamboos.size(), 3);
        assertNotNull(player1.detectPandaObjective());
        assertEquals(player1.eatenBamboos.size(), 1);
    }

    @Test
    public void detectPandaObjectiveTWO_GREEN() {
        assertEquals(player1.eatenBamboos.size(), 0);
        player1.addNewObjective(twoGreen);
        player1.eatenBamboos.add(new Bamboo(GREEN));
        player1.eatenBamboos.add(new Bamboo(YELLOW));
        assertNull(player1.detectPandaObjective());
        assertEquals(player1.eatenBamboos.size(), 2);
        player1.eatenBamboos.add(new Bamboo(GREEN));
        assertEquals(player1.eatenBamboos.size(), 3);
        assertNotNull(player1.detectPandaObjective());
        assertEquals(player1.eatenBamboos.size(), 1);
    }

    @Test
    public void detectPandaObjectiveTWO_PINK() {
        assertEquals(player1.eatenBamboos.size(), 0);
        player1.addNewObjective(twoPink);
        player1.eatenBamboos.add(new Bamboo(GREEN));
        player1.eatenBamboos.add(new Bamboo(PINK));
        assertNull(player1.detectPandaObjective());
        assertEquals(player1.eatenBamboos.size(), 2);
        player1.eatenBamboos.add(new Bamboo(PINK));
        assertEquals(player1.eatenBamboos.size(), 3);
        assertNotNull(player1.detectPandaObjective());
        assertEquals(player1.eatenBamboos.size(), 1);
    }

    @Test
    public void detectPandaObjectiveTHREE_GREEN() {
        player1.addNewObjective(new PandaObjective(4, THREE_GREEN, new Color[]{GREEN}));
        assertEquals(player1.eatenBamboos.size(), 0);
        player1.eatenBamboos.add(new Bamboo(GREEN));
        player1.eatenBamboos.add(new Bamboo(GREEN));
        assertEquals(player1.eatenBamboos.size(), 2);
        assertNull(player1.detectPandaObjective());
        player1.eatenBamboos.add(new Bamboo(GREEN));
        assertEquals(player1.eatenBamboos.size(), 3);
        assertNotNull(player1.detectPandaObjective());
        assertEquals(player1.eatenBamboos.size(), 0);
    }

    @Test
    public void detectPandaObjectiveONE_OF_EACH() {
        player1.addNewObjective(new PandaObjective(6, ONE_OF_EACH, new Color[]{YELLOW, GREEN, PINK}));
        assertEquals(player1.eatenBamboos.size(), 0);
        player1.eatenBamboos.add(new Bamboo(GREEN));
        player1.eatenBamboos.add(new Bamboo(YELLOW));
        assertEquals(player1.eatenBamboos.size(), 2);
        assertNull(player1.detectPandaObjective());
        assertEquals(player1.eatenBamboos.size(), 2);
        player1.eatenBamboos.add(new Bamboo(PINK));
        assertEquals(player1.eatenBamboos.size(), 3);
        assertNotNull(player1.detectPandaObjective());
        assertEquals(player1.eatenBamboos.size(), 0);
    }

    @Test
    void getStrategy() {
        assertEquals(player1.getStrategy(), WITHOUTSTRATEGY);
    }
    @Test
    void addAnIrrigation() {
        IrrigationStock canStock = game.getIrrigationStock();
        Optional<IrrigationCanal> canal = canStock.getOneUnused();
        player1.addAnIrrigation(canal.get());
        assertEquals(player1.getCanalList().size(), 1);
    }

    @Test
    void returnAnIrrigation() {
        assertEquals(player1.returnAnIrrigation(), Optional.empty());
        IrrigationStock canStock = game.getIrrigationStock();
        Optional<IrrigationCanal> canal = canStock.getOneUnused();
        player1.addAnIrrigation(canal.get());
        assertEquals(player1.getCanalList().size(), 1);
        assertEquals(player1.returnAnIrrigation(), canal);
    }

    @Test
    void findAnAvailableIrrigationSource() {
        assertEquals(player1.returnAnIrrigation(), Optional.empty());
        IrrigationStock canStock = game.getIrrigationStock();
        Optional<IrrigationCanal> canal = canStock.getOneUnused();
        player1.addAnIrrigation(canal.get());
        assertEquals(player1.getCanalList().size(), 1);
        assertEquals(player1.returnAnIrrigation(), canal);
        irrigationStock.primordialCanal(game.getBoard());
        assertTrue(player1.findAnAvailableIrrigationSource(canStock).isPresent());

    }

    @Test
    void findAnAvailableIrrigationDest() {
        assertEquals(player1.returnAnIrrigation(), Optional.empty());
        IrrigationStock canStock = game.getIrrigationStock();
        Optional<IrrigationCanal> canal = canStock.getOneUnused();
        player1.addAnIrrigation(canal.get());
        assertEquals(player1.getCanalList().size(), 1);
        assertEquals(player1.returnAnIrrigation(), canal);
        Board bd = game.getBoard();
        Optional<HexPlot> hex = player1.findAnAvailableIrrigationDest(bd, new HexPlot());
        assertTrue(!canStock.getAllHexplotFrom().contains(hex.get()));
        assertTrue(bd.contains(hex.get()));
    }

}