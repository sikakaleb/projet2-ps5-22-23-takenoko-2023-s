package fr.cotedazur.univ.polytech.startingpoint;

import tools.Color;
import objectives.PlotObjectiveDetector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import supplies.Board;
import supplies.DeckOfPlots;
import supplies.HexPlot;

import java.util.*;

import static tools.Color.*;
import static org.junit.jupiter.api.Assertions.*;

public class PlotObjectiveDetectorTest {
    
    private Board board;
    private DeckOfPlots deckOfPlots;
    private PlotObjectiveDetector detector;
    
    @BeforeEach
    public void setUp(){
        board = new Board();
        deckOfPlots = new DeckOfPlots();
        detector = new PlotObjectiveDetector(board);
    }

    @Test
    void listOfCombinationsTest() {
        board.ChoicePlot(deckOfPlots.pickPlot());
        board.ChoicePlot(deckOfPlots.pickPlot());
        board.ChoicePlot(deckOfPlots.pickPlot());
        List<HexPlot> temp=new ArrayList<>(board);
        temp.remove(new HexPlot());
        List<List<HexPlot>> list = detector.listOfCombinations(3);
        assertTrue(list.get(0).containsAll(temp));
        assertEquals(list.size(),1);
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
        assertTrue(map.equals(detector.extractPlotsData(list)));
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
        assertTrue(mapR.equals(detector.countR(list)));
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
        assertTrue(mapS.equals(detector.countS(list)));
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
        assertTrue(mapQ.equals(detector.countQ(list)));
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
        board.add(new HexPlot(-3,3,0, GREEN));
        board.add(new HexPlot(-2,2,0,GREEN));
        board.add(new HexPlot(-1,1,0,GREEN));
        assertTrue(detector.findDirectSamePlots(GREEN));
    }

    @Test
    void isIndirectDirectSamePlots() {
        board.add(new HexPlot(-3,2,1, GREEN));
        board.add(new HexPlot(-2,2,0,GREEN));
        board.add(new HexPlot(-1,1,0,GREEN));
        assertTrue(detector.findInDirectSamePlots(GREEN));
    }
    @Test
    void checkSetSuitConfDirect() {
        List<HexPlot> list = new ArrayList<>();
        Set<Integer> answerSet = new HashSet<>();
        list.add(new HexPlot(-3,0,3));
        list.add(new HexPlot(-2,0,2));
        list.add(new HexPlot(-1,0,1));
        answerSet.add(3);
        answerSet.add(1);
        Map<String , Map<Integer,Integer>> map= detector.extractPlotsData(list);
        assertTrue(answerSet.equals(detector.checkSetSuitConf(map)));

    }
    @Test
    void checkSetSuitConfInDirect() {
        List<HexPlot> list = new ArrayList<>();
        Set<Integer> answerSet = new HashSet<>();
        list.add(new HexPlot(-3,0,3));
        list.add(new HexPlot(-2,0,2));
        list.add(new HexPlot(-2,1,1));
        answerSet.add(2);
        answerSet.add(3);
        Map<String , Map<Integer,Integer>> map= detector.extractPlotsData(list);
        assertTrue(answerSet.equals(detector.checkSetSuitConf(map)));

    }
    @Test
    void checkSetSuitConfTriangular() {
        List<HexPlot> list = new ArrayList<>();
        Set<Integer> answerSet = new HashSet<>();
        list.add(new HexPlot(-3,2,1));
        list.add(new HexPlot(-2,2,0));
        list.add(new HexPlot(-2,1,1));
        answerSet.add(2);
        Map<String , Map<Integer,Integer>> map= detector.extractPlotsData(list);
        assertTrue(answerSet.equals(detector.checkSetSuitConf(map)));

    }
    @Test
    void checkSetSuitConfQuadrilateral() {
        List<HexPlot> list = new ArrayList<>();
        Set<Integer> answerSet = new HashSet<>();
        list.add(new HexPlot(-3,2,1));
        list.add(new HexPlot(-2,2,0));
        list.add(new HexPlot(-2,1,1));
        list.add(new HexPlot(-3,1,2));
        answerSet.add(2);
        answerSet.add(3);
        Map<String , Map<Integer,Integer>> map= detector.extractPlotsData(list);
        assertTrue(answerSet.equals(detector.checkSetSuitConf(map)));

    }

    @Test
    void checkPairAdjacentColorFalse() {
        List<HexPlot> list = new ArrayList<>();
        Set<Integer> answerSet = new HashSet<>();
        list.add(new HexPlot(-3,2,1,GREEN));
        list.add(new HexPlot(-2,2,0,PINK));
        list.add(new HexPlot(-2,1,1,GREEN));
        list.add(new HexPlot(-3,1,2,PINK));
        assertFalse(detector.checkPairAdjacentColor(list));
    }
    @Test
    void checkPairAdjacentColorTrue() {
        List<HexPlot> list = new ArrayList<>();
        Set<Integer> answerSet = new HashSet<>();
        list.add(new HexPlot(-3,2,1,GREEN));
        list.add(new HexPlot(-2,2,0,PINK));
        list.add(new HexPlot(-2,1,1,PINK));
        list.add(new HexPlot(-3,1,2,GREEN));
        System.out.println(list);
        assertTrue(detector.checkPairAdjacentColor(list));
    }

    @Test
    void allColorInHexPlotList() {
        List<HexPlot> list = new ArrayList<>();
        Set<Color> colorSet = new HashSet<>();
        colorSet.add(GREEN);
        colorSet.add(PINK);
        list.add(new HexPlot(-3,2,1,GREEN));
        list.add(new HexPlot(-2,2,0,PINK));
        list.add(new HexPlot(-2,1,1,PINK));
        list.add(new HexPlot(-3,1,2,GREEN));
        assertTrue(colorSet.equals(detector.allColorInHexPlotList(list)));

    }

    @Test
    void checkSetSuitConf() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,2,1,GREEN));
        list.add(new HexPlot(-2,2,0,PINK));
        list.add(new HexPlot(-2,1,1,PINK));
        list.add(new HexPlot(-3,1,2,GREEN));
        Map<String,Map<Integer,Integer>> map = detector.extractPlotsData(list);
        Set<Integer> answerset=new HashSet<>();
        answerset.add(2);
        answerset.add(3);
        assertTrue(answerset.equals(detector.checkSetSuitConf(map)));
    }

    @Test
    void isQuadrilateralPlots_P_YTrue() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,2,1,YELLOW));
        list.add(new HexPlot(-2,2,0,PINK));
        list.add(new HexPlot(-2,1,1,PINK));
        list.add(new HexPlot(-3,1,2,YELLOW));
        assertTrue(detector.isQuadrilateralPlots_P_Y(list));
    }
    @Test
    void isQuadrilateralPlots() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,2,1,YELLOW));
        list.add(new HexPlot(-2,2,0,PINK));
        list.add(new HexPlot(-2,1,1,PINK));
        list.add(new HexPlot(-3,1,2,YELLOW));
        assertTrue(detector.isQuadrilateralPlots(list));
    }
    @Test
    void isQuadrilateralPlots_P_YFalse1() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,2,1,GREEN));
        list.add(new HexPlot(-2,2,0,PINK));
        list.add(new HexPlot(-2,1,1,PINK));
        list.add(new HexPlot(-3,1,2,YELLOW));
        assertFalse(detector.isQuadrilateralPlots_P_Y(list));
    }
    @Test
    void isQuadrilateralPlots_P_YFalse2() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,2,1,GREEN));
        list.add(new HexPlot(-2,2,15,PINK));
        list.add(new HexPlot(-2,1,1,PINK));
        list.add(new HexPlot(-3,1,2,YELLOW));
        assertFalse(detector.isQuadrilateralPlots_P_Y(list));
    }

    @Test
    void isQuadrilateralPlots_G_PTrue() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,2,1,GREEN));
        list.add(new HexPlot(-2,2,0,PINK));
        list.add(new HexPlot(-2,1,1,PINK));
        list.add(new HexPlot(-3,1,2,GREEN));
        assertTrue(detector.isQuadrilateralPlots_G_P(list));
    }
    @Test
    void isQuadrilateralPlots_G_PFalse1() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,2,1,PINK));
        list.add(new HexPlot(-2,2,15,GREEN));
        list.add(new HexPlot(-2,1,1,GREEN));
        list.add(new HexPlot(-3,1,2,PINK));
        assertFalse(detector.isQuadrilateralPlots_G_P(list));
    }
    @Test
    void isQuadrilateralPlots_G_PFalse2() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,2,1,GREEN));
        list.add(new HexPlot(-2,2,15,PINK));
        list.add(new HexPlot(-2,1,1,PINK));
        list.add(new HexPlot(-3,1,2,YELLOW));
        assertFalse(detector.isQuadrilateralPlots_P_Y(list));
    }

    @Test
    void isQuadrilateralPlots_G_YTrue() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,2,1,GREEN));
        list.add(new HexPlot(-2,2,0,YELLOW));
        list.add(new HexPlot(-2,1,1,YELLOW));
        list.add(new HexPlot(-3,1,2,GREEN));
        assertTrue(detector.isQuadrilateralPlots_G_Y(list));
    }
    @Test
    void isQuadrilateralPlots_G_YFalse1() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,2,1,YELLOW));
        list.add(new HexPlot(-2,2,15,GREEN));
        list.add(new HexPlot(-2,1,1,GREEN));
        list.add(new HexPlot(-3,1,2,YELLOW));
        assertFalse(detector.isQuadrilateralPlots_G_Y(list));
    }
    @Test
    void isQuadrilateralPlots_G_YFalse2() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,2,1,GREEN));
        list.add(new HexPlot(-2,2,15,PINK));
        list.add(new HexPlot(-2,1,1,PINK));
        list.add(new HexPlot(-3,1,2,YELLOW));
        assertFalse(detector.isQuadrilateralPlots_G_Y(list));
    }


    @Test
    void findInDirectSamePlots() {
        board.add(new HexPlot(-3,2,1,GREEN));
        board.add(new HexPlot(-2,2,0,PINK));
        board.add(new HexPlot(-2,1,1,PINK));
        board.add(new HexPlot(-1,0,1,PINK));
        board.add(new HexPlot(-3,1,2,YELLOW));
        assertTrue(detector.findInDirectSamePlots(PINK));
    }

    @Test
    void findQuadrilateralSamePlots() {
        board.add(new HexPlot(-3,2,1,PINK));
        board.add(new HexPlot(-2,0,2,PINK));
        board.add(new HexPlot(-2,1,1,PINK));
        board.add(new HexPlot(-1,0,1,GREEN));
        board.add(new HexPlot(-3,1,2,PINK));
        assertTrue(detector.findQuadrilateralSamePlots(PINK));
    }

    @Test
    void findQuadrilateralPlots_P_Y() {
        board.add(new HexPlot(-3,2,1,YELLOW));
        board.add(new HexPlot(-2,2,0,PINK));
        board.add(new HexPlot(-2,1,1,PINK));
        board.add(new HexPlot(-3,1,2,YELLOW));
        assertTrue(detector.findQuadrilateralPlots_P_Y());
    }

    @Test
    void findQuadrilateralPlots_G_P() {
        board.add(new HexPlot(-3,2,1,GREEN));
        board.add(new HexPlot(-2,2,0,PINK));
        board.add(new HexPlot(-2,1,1,PINK));
        board.add(new HexPlot(-3,1,2,GREEN));
        assertTrue(detector.findQuadrilateralPlots_G_P());
    }

    @Test
    void findQuadrilateralPlots_G_Y() {
        board.add(new HexPlot(-3,2,1,GREEN));
        board.add(new HexPlot(-2,2,0,YELLOW));
        board.add(new HexPlot(-2,1,1,YELLOW));
        board.add(new HexPlot(-3,1,2,GREEN));
        assertTrue(detector.findQuadrilateralPlots_G_Y());
    }
}
