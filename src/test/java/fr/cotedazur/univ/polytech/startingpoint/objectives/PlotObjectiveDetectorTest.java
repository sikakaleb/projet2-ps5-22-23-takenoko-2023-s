package fr.cotedazur.univ.polytech.startingpoint.objectives;

import fr.cotedazur.univ.polytech.startingpoint.gameplay.Game;
import fr.cotedazur.univ.polytech.startingpoint.gameplay.Player;
import fr.cotedazur.univ.polytech.startingpoint.supplies.*;
import fr.cotedazur.univ.polytech.startingpoint.tools.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static fr.cotedazur.univ.polytech.startingpoint.tools.Color.*;
import static org.junit.jupiter.api.Assertions.*;

class PlotObjectiveDetectorTest {

    private Board board;
    private DeckOfPlots deckOfPlots;
    private PlotObjectiveDetector detector;
    private Game game;



    @BeforeEach
    void init(){
        game = new Game(new Player("Ted"), new Player("Wilfried"));
        board = new Board();
        deckOfPlots = new DeckOfPlots();
        detector = new PlotObjectiveDetector(board);
    }

    @Test
    void listOfCombinationsTest() {
        board.choicePlot(deckOfPlots.pickPlot());
        board.choicePlot(deckOfPlots.pickPlot());
        board.choicePlot(deckOfPlots.pickPlot());
        List<HexPlot> temp=new ArrayList<>(board);
        temp.remove(new HexPlot());
        List<List<HexPlot>> list = detector.listOfCombinations(3);
        assertTrue(list.get(0).containsAll(temp));
        assertEquals(1,list.size());
    }

    @Test
    void extractPlotsDataTest() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,2,1,YELLOW));
        list.add(new HexPlot(-2,2,0,PINK));
        list.add(new HexPlot(-2,1,1,PINK));
        list.add(new HexPlot(-3,1,2,YELLOW));
        Map<String, Map<Integer,Integer>> map = new HashMap<>();
        map.put("Q",detector.countQ(list));
        map.put("S",detector.countS(list));
        map.put("R",detector.countR(list));
        assertEquals(map,detector.extractPlotsData(list));
    }

    @Test
    void countRTest() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,2,1,YELLOW));
        list.add(new HexPlot(-2,2,0,PINK));
        list.add(new HexPlot(-2,1,1,PINK));
        list.add(new HexPlot(-3,1,2,YELLOW));
        Map<Integer,Integer> mapR=new HashMap<>();
        mapR.put(1,2);
        mapR.put(0,1);
        mapR.put(2,1);
        assertEquals(mapR,detector.countR(list));
    }

    @Test
    void countSTest() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,2,1,YELLOW));
        list.add(new HexPlot(-2,2,0,PINK));
        list.add(new HexPlot(-2,1,1,PINK));
        list.add(new HexPlot(-3,1,2,YELLOW));
        Map<Integer,Integer> mapS=new HashMap<>();
        mapS.put(2,2);
        mapS.put(1,2);;
        assertEquals(mapS,detector.countS(list));
    }

    @Test
    void countQTest() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,2,1,YELLOW));
        list.add(new HexPlot(-2,2,0,PINK));
        list.add(new HexPlot(-2,1,1,PINK));
        list.add(new HexPlot(-3,1,2,YELLOW));
        Map<Integer,Integer> mapQ=new HashMap<>();
        mapQ.put(-2,2);
        mapQ.put(-3,2);;
        assertEquals(mapQ,detector.countQ(list));
    }

    @Test
    void sSuiteTest() {
        Set<Integer> intSet = new HashSet<>();
        intSet.add(0);
        intSet.add(-1);
        intSet.add(1);
        intSet.add(-2);
        assertTrue(detector.sSuite(intSet,4));
    }

    @Test
    void isDirectSamePlotsTest() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,3,0, GREEN));
        list.add(new HexPlot(-2,2,0,GREEN));
        list.add(new HexPlot(-1,1,0,GREEN));
        assertTrue(detector.isDirectSamePlots(list));
    }

    @Test
    void findDirectSamePlotsTest() {
        assertFalse(detector.findDirectSamePlots(GREEN));
        board.add(new HexPlot(-3,3,0, GREEN));
        board.add(new HexPlot(-2,2,0,GREEN));
        board.add(new HexPlot(-1,1,0,GREEN));
        assertTrue(detector.findDirectSamePlots(GREEN));
    }

    @Test
    void isIndirectDirectSamePlotsTest() {
        board.add(new HexPlot(-3,2,1, GREEN));
        board.add(new HexPlot(-2,2,0,GREEN));
        board.add(new HexPlot(-1,1,0,GREEN));
        assertTrue(detector.findInDirectSamePlots(GREEN));
    }
    @Test
    void checkSetSuitConfDirectTest() {
        List<HexPlot> list = new ArrayList<>();
        Set<Integer> answerSet = new HashSet<>();
        list.add(new HexPlot(-3,0,3));
        list.add(new HexPlot(-2,0,2));
        list.add(new HexPlot(-1,0,1));
        answerSet.add(3);
        answerSet.add(1);
        Map<String , Map<Integer,Integer>> map= detector.extractPlotsData(list);
        assertEquals(answerSet,detector.checkSetSuitConf(map));

    }
    @Test
    void checkSetSuitConfInDirectTest() {
        List<HexPlot> list = new ArrayList<>();
        Set<Integer> answerSet = new HashSet<>();
        list.add(new HexPlot(-3,0,3));
        list.add(new HexPlot(-2,0,2));
        list.add(new HexPlot(-2,1,1));
        answerSet.add(2);
        answerSet.add(3);
        Map<String , Map<Integer,Integer>> map= detector.extractPlotsData(list);
        assertEquals(answerSet,detector.checkSetSuitConf(map));

    }
    @Test
    void checkSetSuitConfTriangularTest() {
        List<HexPlot> list = new ArrayList<>();
        Set<Integer> answerSet = new HashSet<>();
        list.add(new HexPlot(-3,2,1));
        list.add(new HexPlot(-2,2,0));
        list.add(new HexPlot(-2,1,1));
        answerSet.add(2);
        Map<String , Map<Integer,Integer>> map= detector.extractPlotsData(list);
        assertEquals(answerSet,detector.checkSetSuitConf(map));

    }
    @Test
    void checkSetSuitConfQuadrilateralTest() {
        List<HexPlot> list = new ArrayList<>();
        Set<Integer> answerSet = new HashSet<>();
        list.add(new HexPlot(-3,2,1));
        list.add(new HexPlot(-2,2,0));
        list.add(new HexPlot(-2,1,1));
        list.add(new HexPlot(-3,1,2));
        answerSet.add(2);
        answerSet.add(3);
        Map<String , Map<Integer,Integer>> map= detector.extractPlotsData(list);
        assertEquals(answerSet,detector.checkSetSuitConf(map));

    }

    @Test
    void checkPairAdjacentColorFalseTest() {
        List<HexPlot> list = new ArrayList<>();
        Set<Integer> answerSet = new HashSet<>();
        list.add(new HexPlot(-3,2,1,GREEN));
        list.add(new HexPlot(-2,2,0,PINK));
        list.add(new HexPlot(-2,1,1,GREEN));
        list.add(new HexPlot(-3,1,2,PINK));
        assertFalse(detector.checkPairAdjacentColor(list));
    }
    @Test
    void checkPairAdjacentColorTrueTest() {
        List<HexPlot> list = new ArrayList<>();
        Set<Integer> answerSet = new HashSet<>();
        list.add(new HexPlot(-3,2,1,GREEN));
        list.add(new HexPlot(-2,2,0,PINK));
        list.add(new HexPlot(-2,1,1,PINK));
        list.add(new HexPlot(-3,1,2,GREEN));
        assertTrue(detector.checkPairAdjacentColor(list));
    }

    @Test
    void allColorInHexPlotListTest() {
        List<HexPlot> list = new ArrayList<>();
        Set<Color> colorSet = new HashSet<>();
        colorSet.add(GREEN);
        colorSet.add(PINK);
        list.add(new HexPlot(-3,2,1,GREEN));
        list.add(new HexPlot(-2,2,0,PINK));
        list.add(new HexPlot(-2,1,1,PINK));
        list.add(new HexPlot(-3,1,2,GREEN));
        assertEquals(colorSet,detector.allColorInHexPlotList(list));

    }

    @Test
    void checkSetSuitConfTest() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,2,1,GREEN));
        list.add(new HexPlot(-2,2,0,PINK));
        list.add(new HexPlot(-2,1,1,PINK));
        list.add(new HexPlot(-3,1,2,GREEN));
        Map<String,Map<Integer,Integer>> map = detector.extractPlotsData(list);
        Set<Integer> answerset=new HashSet<>();
        answerset.add(2);
        answerset.add(3);
        assertEquals(answerset,detector.checkSetSuitConf(map));
    }

    @Test
    void isQuadrilateralPlots_P_YTrueTest() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,2,1,YELLOW));
        list.add(new HexPlot(-2,2,0,PINK));
        list.add(new HexPlot(-2,1,1,PINK));
        list.add(new HexPlot(-3,1,2,YELLOW));
        assertTrue(detector.isQuadrilateralPlotsPY(list));
    }
    @Test
    void isQuadrilateralPlotsTest() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,2,1,YELLOW));
        list.add(new HexPlot(-2,2,0,PINK));
        list.add(new HexPlot(-2,1,1,PINK));
        list.add(new HexPlot(-3,1,2,YELLOW));
        assertTrue(detector.isQuadrilateralPlots(list));
    }
    @Test
    void isQuadrilateralPlots_P_YFalseFaTest() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,2,1,GREEN));
        list.add(new HexPlot(-2,2,0,PINK));
        list.add(new HexPlot(-2,1,1,PINK));
        list.add(new HexPlot(-3,1,2,YELLOW));
        assertFalse(detector.isQuadrilateralPlotsPY(list));
    }
    @Test
    void isQuadrilateralPlots_P_YFalseSaTest() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,2,1,GREEN));
        list.add(new HexPlot(-2,2,15,PINK));
        list.add(new HexPlot(-2,1,1,PINK));
        list.add(new HexPlot(-3,1,2,YELLOW));
        assertFalse(detector.isQuadrilateralPlotsPY(list));
    }

    @Test
    void isQuadrilateralPlots_G_PTrueTest() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,2,1,GREEN));
        list.add(new HexPlot(-2,2,0,PINK));
        list.add(new HexPlot(-2,1,1,PINK));
        list.add(new HexPlot(-3,1,2,GREEN));
        assertTrue(detector.isQuadrilateralPlotsGP(list));
    }
    @Test
    void isQuadrilateralPlots_G_PFalseFnTest() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,2,1,PINK));
        list.add(new HexPlot(-2,2,15,GREEN));
        list.add(new HexPlot(-2,1,1,GREEN));
        list.add(new HexPlot(-3,1,2,PINK));
        assertFalse(detector.isQuadrilateralPlotsGP(list));
    }
    @Test
    void isQuadrilateralPlots_G_PFalseSnTest() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,2,1,GREEN));
        list.add(new HexPlot(-2,2,15,PINK));
        list.add(new HexPlot(-2,1,1,PINK));
        list.add(new HexPlot(-3,1,2,YELLOW));
        assertFalse(detector.isQuadrilateralPlotsPY(list));
    }

    @Test
    void isQuadrilateralPlots_G_YTrueTest() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,2,1,GREEN));
        list.add(new HexPlot(-2,2,0,YELLOW));
        list.add(new HexPlot(-2,1,1,YELLOW));
        list.add(new HexPlot(-3,1,2,GREEN));
        assertTrue(detector.isQuadrilateralPlotsGY(list));
    }
    @Test
    void isQuadrilateralPlots_G_YFalseFkTest() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,2,1,YELLOW));
        list.add(new HexPlot(-2,2,15,GREEN));
        list.add(new HexPlot(-2,1,1,GREEN));
        list.add(new HexPlot(-3,1,2,YELLOW));
        assertFalse(detector.isQuadrilateralPlotsGY(list));
    }
    @Test
    void isQuadrilateralPlots_G_YFalseSkTest() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,2,1,GREEN));
        list.add(new HexPlot(-2,2,15,PINK));
        list.add(new HexPlot(-2,1,1,PINK));
        list.add(new HexPlot(-3,1,2,YELLOW));
        assertFalse(detector.isQuadrilateralPlotsGY(list));
    }


    @Test
    void findInDirectSamePlotsTest() {
        assertFalse(detector.findInDirectSamePlots(PINK));
        board.add(new HexPlot(-3,2,1,GREEN));
        board.add(new HexPlot(-2,2,0,PINK));
        board.add(new HexPlot(-2,1,1,PINK));
        board.add(new HexPlot(-1,0,1,PINK));
        board.add(new HexPlot(-3,1,2,YELLOW));
        assertTrue(detector.findInDirectSamePlots(PINK));
    }

    @Test
    void findQuadrilateralSamePlotsTest() {
        assertFalse(detector.findQuadrilateralSamePlots(PINK));
        board.add(new HexPlot(-3,2,1,PINK));
        board.add(new HexPlot(-2,0,2,PINK));
        board.add(new HexPlot(-2,1,1,PINK));
        board.add(new HexPlot(-1,0,1,GREEN));
        board.add(new HexPlot(-3,1,2,PINK));
        assertTrue(detector.findQuadrilateralSamePlots(PINK));
    }

    @Test
    void findQuadrilateralPlots_P_YTest() {
        assertFalse(detector.findQuadrilateralPlotsPY());
        board.add(new HexPlot(-3,2,1,YELLOW));
        board.add(new HexPlot(-2,2,0,PINK));
        board.add(new HexPlot(-2,1,1,PINK));
        board.add(new HexPlot(-3,1,2,YELLOW));
        assertTrue(detector.findQuadrilateralPlotsPY());
    }

    @Test
    void findQuadrilateralPlots_G_PTest() {
        assertFalse(detector.findQuadrilateralPlotsGP());
        board.add(new HexPlot(-3,2,1,GREEN));
        board.add(new HexPlot(-2,2,0,PINK));
        board.add(new HexPlot(-2,1,1,PINK));
        board.add(new HexPlot(-3,1,2,GREEN));
        assertTrue(detector.findQuadrilateralPlotsGP());
    }

    @Test
    void findQuadrilateralPlots_G_YTest() {
        assertFalse(detector.findQuadrilateralPlotsGY());
        board.add(new HexPlot(-3,2,1,GREEN));
        board.add(new HexPlot(-2,2,0,YELLOW));
        board.add(new HexPlot(-2,1,1,YELLOW));
        board.add(new HexPlot(-3,1,2,GREEN));
        assertTrue(detector.findQuadrilateralPlotsGY());
    }
}
