package fr.cotedazur.univ.polytech.startingpoint.smarterobots;

import fr.cotedazur.univ.polytech.startingpoint.gameplay.Game;
import fr.cotedazur.univ.polytech.startingpoint.gameplay.Player;
import fr.cotedazur.univ.polytech.startingpoint.objectives.MarkObjective;
import fr.cotedazur.univ.polytech.startingpoint.objectives.Objective;
import fr.cotedazur.univ.polytech.startingpoint.objectives.PandaObjective;
import fr.cotedazur.univ.polytech.startingpoint.objectives.PlotObjective;
import fr.cotedazur.univ.polytech.startingpoint.supplies.Bamboo;
import fr.cotedazur.univ.polytech.startingpoint.supplies.HexPlot;
import fr.cotedazur.univ.polytech.startingpoint.tools.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static fr.cotedazur.univ.polytech.startingpoint.tools.Color.*;
import static fr.cotedazur.univ.polytech.startingpoint.tools.PandaObjectiveConfiguration.*;
import static fr.cotedazur.univ.polytech.startingpoint.tools.PlotObjectiveConfiguration.DIRECTSAMEPLOTS;
import static fr.cotedazur.univ.polytech.startingpoint.tools.PlotObjectiveConfiguration.INDIRECTSAMEPLOTS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EvalObjectiveBotTest {

    EvalObjectiveBot player1;
    Player player2;

    Game game;
    List<Objective> singlelist;

    PlotObjective directPlotObj = new PlotObjective(2, DIRECTSAMEPLOTS,GREEN);
    PlotObjective indirectPlotObj = new PlotObjective(3, INDIRECTSAMEPLOTS,GREEN);
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
        player1 = new EvalObjectiveBot("Ted");
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
        HexPlot hex7 = new HexPlot(2, -2, 0, GREEN);
        hex4.getBamboos().add(new Bamboo(GREEN));
        HexPlot hex8 = new HexPlot(3, -3, 0, GREEN);
        hex5.getBamboos().add(new Bamboo(GREEN));
        HexPlot hex9 = new HexPlot(2, -3, 1, GREEN);
        hex6.getBamboos().add(new Bamboo(GREEN));
        HexPlot hex10 = new HexPlot(1, -3, 2, GREEN);
        hex6.getBamboos().add(new Bamboo(GREEN));
        game.getBoard().add(hex1);
        game.getBoard().add(hex2);
        game.getBoard().add(hex3);
        game.getBoard().add(hex4);
        game.getBoard().add(hex5);
        game.getBoard().add(hex6);
        game.getBoard().add(hex7);
        game.getBoard().add(hex8);
        game.getBoard().add(hex9);
        game.getBoard().add(hex10);

        singlelist = new ArrayList<>();
        singlelist.add(directPlotObj);
        singlelist.add(indirectPlotObj);

        player1.getEatenBamboos().add(new Bamboo(YELLOW));
        player1.getEatenBamboos().add(new Bamboo(GREEN));
        player1.getEatenBamboos().add(new Bamboo(PINK));
        player1.getEatenBamboos().add(new Bamboo(YELLOW));
        player1.getEatenBamboos().add(new Bamboo(GREEN));
        player1.getEatenBamboos().add(new Bamboo(GREEN));
        player1.getEatenBamboos().add(new Bamboo(PINK));

        player1.getUnMetObjectives().add(oneOfEach);
        player1.getUnMetObjectives().add(twoYellow);
        player1.getUnMetObjectives().add(twoGreen);
        player1.getUnMetObjectives().add(twoPink);
        player1.getUnMetObjectives().add(threeGreen);

        singlelist.forEach(p->{
            player1.addNewObjective(p);
        });

        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void getMarkedUnMetObjectives() {
        List<MarkObjective> result = player1.getMarkedUnMetObjectives();
        assertEquals(result,player1.getMarkedUnMetObjectives());

    }

    @Test
    void sortMarkObjectiveList() {
        List<MarkObjective> result = player1.getMarkedUnMetObjectives();
        EvalObjectiveBot.sortMarkObjectiveList(result);
        assertEquals(result,player1.getMarkedUnMetObjectives());
    }

    @Test
    void getTopMarkObjectives() {
        List<MarkObjective> result= new ArrayList<>();
        result.addAll(player1.evaluateGardenerObjective());
        result.addAll(player1.evaluatePlotObjective());
        result.addAll(player1.evaluatePandaObjective());
        EvalObjectiveBot.sortMarkObjectiveList(result);
        List<MarkObjective> res= new ArrayList<>();
        res.add(new MarkObjective(oneOfEach));
        assertEquals(EvalObjectiveBot.getTopMarkObjectives(result), res);
    }

    @Test
    void evaluatObjectif() {
        List<MarkObjective> result = player1.getMarkedUnMetObjectives();
        MarkObjective res=new MarkObjective(oneOfEach);
        assertEquals(player1.evaluatObjectif(), res);
    }

    @Test
    void evaluatePlotObjective() {
        List<MarkObjective> result = new ArrayList<>();
        MarkObjective mark1= new MarkObjective(directPlotObj);
        MarkObjective mark2= new MarkObjective(indirectPlotObj);
        mark1.setMark(10);
        mark2.setMark(15);
        result.add(mark1);
        result.add(mark2);
        assertTrue(player1.evaluatePlotObjective().containsAll(result)&&result.containsAll(player1.evaluatePlotObjective()));
    }

    @Test
    void evaluatePandaObjective() {
        List<MarkObjective> result = new ArrayList<>();
        MarkObjective mark1=new MarkObjective(oneOfEach);
        MarkObjective mark2=new MarkObjective(twoYellow);
        MarkObjective mark3=new MarkObjective(twoGreen);
        MarkObjective mark4=new MarkObjective(twoPink);
        MarkObjective mark5=new MarkObjective(threeGreen);
        result.add(mark1);
        result.add(mark2);
        result.add(mark3);
        result.add(mark4);
        result.add(mark5);
        mark1.setMark(33);
        mark2.setMark(23);
        mark3.setMark(58);
        mark4.setMark(23);
        mark5.setMark(28);
        assertTrue(player1.evaluatePandaObjective().containsAll(result)&&result.containsAll(player1.evaluatePandaObjective()));
    }

    @Test
    void evaluateGardenerObjective() {
        List<MarkObjective> result = new ArrayList<>();
        assertEquals(player1.evaluateGardenerObjective(), result);
    }

    @Test
    void removeDuplicates() {
        List<MarkObjective> result = new ArrayList<>();
        List<MarkObjective> re = new ArrayList<>();
        MarkObjective mark1= new MarkObjective(directPlotObj);
        MarkObjective mark2= new MarkObjective(indirectPlotObj);
        result.add(mark1);
        result.add(mark2);
        result.add(mark1);
        result.add(mark2);
        re.add(mark1);
        re.add(mark2);
        assertTrue(re.containsAll(EvalObjectiveBot.removeDuplicates(result))&&EvalObjectiveBot.removeDuplicates(result).containsAll(re));

    }

}