package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static fr.cotedazur.univ.polytech.startingpoint.Game.listOfPlots;
import static fr.cotedazur.univ.polytech.startingpoint.PlotColor.*;
import static fr.cotedazur.univ.polytech.startingpoint.PlotObjectiveConfiguration.DIRECTSAMEPLOTS;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player player1;
    Player player2;

    Game game ;
    List<Objective> singlelist;

    PlotObjective directPlotObj = new PlotObjective(2,DIRECTSAMEPLOTS);
    PlotObjective indirectPlotObj = new PlotObjective(3,DIRECTSAMEPLOTS);
    PlotObjective triangularPlotObj = new PlotObjective(4,DIRECTSAMEPLOTS);
    PlotObjective quadriPlotObj = new PlotObjective(4,DIRECTSAMEPLOTS);


    @BeforeEach
    public void setUp() {
        player1= new Player("Ted");
        player2 = new Player("Willfried");
        /*******************************/
        player1.setMaxUnmetObj(4);
        /*******************************/
        player1.addNewObjective(directPlotObj);
        player1.addNewObjective(indirectPlotObj);
        game = new Game(player1,player2);

        singlelist= new ArrayList<>();
        singlelist.add(directPlotObj);
        singlelist.add(indirectPlotObj);
    }



    @Test
    void getHeight() {
        System.out.println(player1.getPlayerId()+"*************");
        assertEquals(player1.getHeight(),1);
    }

    @Test
    void getName() {
        assertEquals(player1.getName(),"Ted");
    }

    @Test
    void getObjectiveAchieved() {
        List<Objective> objList =new ArrayList<>();
        assertTrue(player1.getObjectiveAchieved().equals(objList));
    }

    @Test
    void addObjectiveAchieved() {
        player1.addObjectiveAchieved(triangularPlotObj);
        int lastIdx = player1.getObjectiveAchieved().size() - 1;
        assertEquals(player1.objectiveAchieved.get(lastIdx),triangularPlotObj);
    }

    @Test
    void getUnMetObjectives() {
        System.out.println(player1.getUnMetObjectives());
        System.out.println(singlelist);
        assertTrue(player1.getUnMetObjectives().equals(singlelist));
    }

    @Test
    void addNewObjective() {
        player1.addNewObjective(quadriPlotObj);
        int lastIdx = player1.getUnMetObjectives().size() - 1;
        assertEquals(player1.getUnMetObjectives().get(lastIdx),quadriPlotObj);
    }

    @Test
    void withdrawUnMetObjective() {
        player1.withdrawUnMetObjective(quadriPlotObj);
        assertFalse(player1.withdrawUnMetObjective(quadriPlotObj));
    }

    @Test
    void validateUnMetObjectives() {
        List<Objective> objList= new ArrayList<>();
        player1.addNewObjective(quadriPlotObj);
        player1.validateUnMetObjectives(quadriPlotObj);
        assertTrue(player1.getUnMetObjectives().equals(singlelist));

    }

    @Test
    void getCumulOfpoint() {
        player1.validateUnMetObjectives(quadriPlotObj);
        assertEquals(player1.getCumulOfpoint(),4);
    }

    @Test
    void addAplotToGame() {
        player1.addAplotToGame();
        player2.addAplotToGame();
        player2.addAplotToGame();
        System.out.println(listOfPlots);
        assertEquals(listOfPlots.size(),4);
    }

    @Test
    void choicePlot() {
        player2.ChoicePlot(new HexPlot(-1,1,0));
        System.out.println(listOfPlots);
        assertEquals(listOfPlots.size(),2);
    }

    /*A revoir */
    @Test
    void listOfCombinations() {
        player1.addAplotToGame();
        player2.addAplotToGame();
        player1.addAplotToGame();
        List<HexPlot> temp=new ArrayList<>(listOfPlots);
        temp.remove(new HexPlot());
        List<List<HexPlot>> list =player1.listOfCombinations(3);
        assertTrue(list.contains(temp)&&list.size()==1);
    }
    @Test
    void extractPlotsData() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,2,1,YELLOW));
        list.add(new HexPlot(-2,2,0,PINK));
        list.add(new HexPlot(-2,1,1,PINK));
        list.add(new HexPlot(-3,1,2,YELLOW));
        Map<String,Map<Integer,Integer>> map = new HashMap<>();
        map.put("Q",player1.countQ(list));
        map.put("S",player1.countS(list));
        map.put("R",player1.countR(list));
        assertTrue(map.equals(player1.extractPlotsData(list)));
    }

    @Test
    void countR() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,2,1,YELLOW));
        list.add(new HexPlot(-2,2,0,PINK));
        list.add(new HexPlot(-2,1,1,PINK));
        list.add(new HexPlot(-3,1,2,YELLOW));
        Map<Integer,Integer> mapR=new HashMap<>();
        mapR.put(1,2);
        mapR.put(0,1);
        mapR.put(2,1);
        assertTrue(mapR.equals(player1.countR(list)));
    }

    @Test
    void countS() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,2,1,YELLOW));
        list.add(new HexPlot(-2,2,0,PINK));
        list.add(new HexPlot(-2,1,1,PINK));
        list.add(new HexPlot(-3,1,2,YELLOW));
        Map<Integer,Integer> mapS=new HashMap<>();
        mapS.put(2,2);
        mapS.put(1,2);;
        assertTrue(mapS.equals(player1.countS(list)));
    }

    @Test
    void countQ() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,2,1,YELLOW));
        list.add(new HexPlot(-2,2,0,PINK));
        list.add(new HexPlot(-2,1,1,PINK));
        list.add(new HexPlot(-3,1,2,YELLOW));
        Map<Integer,Integer> mapQ=new HashMap<>();
        mapQ.put(-2,2);
        mapQ.put(-3,2);;
        assertTrue(mapQ.equals(player1.countQ(list)));
    }

    @Test
    void sSuite() {
        Set<Integer> intSet = new HashSet<>();
        intSet.add(0);
        intSet.add(-1);
        intSet.add(1);
        intSet.add(-2);
        assertTrue(player1.sSuite(intSet,4));

    }

    @Test
    void isDirectSamePlots() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,0,3));
        list.add(new HexPlot(-2,0,2));
        list.add(new HexPlot(-1,0,1));
        assertTrue(player1.isDirectSamePlots(list));
    }

    @Test
    void findDirectSamePlots() {
        listOfPlots.add(new HexPlot(-3,0,3));
        listOfPlots.add(new HexPlot(-2,0,2));
        listOfPlots.add(new HexPlot(-1,0,1));
        assertTrue(player1.findDirectSamePlots());
    }
    /*@Test
    void getPlayerId() {
        System.out.println(player1.getPlayerId()+"*************");
        assertEquals(player1.getPlayerId(),19);
    }*/
    @Test
    void isIndirectDirectSamePlots() {
        listOfPlots.add(new HexPlot(-3,0,3));
        listOfPlots.add(new HexPlot(-2,0,2));
        listOfPlots.add(new HexPlot(-1,0,1));
        assertTrue(player1.findDirectSamePlots());
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
        Map<String , Map<Integer,Integer>> map= player1.extractPlotsData(list);
        assertTrue(answerSet.equals(player1.checkSetSuitConf(map)));

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
        Map<String , Map<Integer,Integer>> map= player1.extractPlotsData(list);
        assertTrue(answerSet.equals(player1.checkSetSuitConf(map)));

    }
    @Test
    void checkSetSuitConfTriangular() {
        List<HexPlot> list = new ArrayList<>();
        Set<Integer> answerSet = new HashSet<>();
        list.add(new HexPlot(-3,2,1));
        list.add(new HexPlot(-2,2,0));
        list.add(new HexPlot(-2,1,1));
        answerSet.add(2);
        Map<String , Map<Integer,Integer>> map= player1.extractPlotsData(list);
        assertTrue(answerSet.equals(player1.checkSetSuitConf(map)));

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
        Map<String , Map<Integer,Integer>> map= player1.extractPlotsData(list);
        assertTrue(answerSet.equals(player1.checkSetSuitConf(map)));

    }

    @Test
    void checkPairAdjacentColorFalse() {
        List<HexPlot> list = new ArrayList<>();
        Set<Integer> answerSet = new HashSet<>();
        list.add(new HexPlot(-3,2,1,GREEN));
        list.add(new HexPlot(-2,2,0,PINK));
        list.add(new HexPlot(-2,1,1,GREEN));
        list.add(new HexPlot(-3,1,2,PINK));
        assertFalse(player1.checkPairAdjacentColor(list));
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
        assertTrue(player1.checkPairAdjacentColor(list));
    }

    @Test
    void allColorInHexPlotList() {
        List<HexPlot> list = new ArrayList<>();
        Set<PlotColor> colorSet = new HashSet<>();
        colorSet.add(GREEN);
        colorSet.add(PINK);
        list.add(new HexPlot(-3,2,1,GREEN));
        list.add(new HexPlot(-2,2,0,PINK));
        list.add(new HexPlot(-2,1,1,PINK));
        list.add(new HexPlot(-3,1,2,GREEN));
        assertTrue(colorSet.equals(player1.allColorInHexPlotList(list)));

    }

    @Test
    void checkSetSuitConf() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,2,1,GREEN));
        list.add(new HexPlot(-2,2,0,PINK));
        list.add(new HexPlot(-2,1,1,PINK));
        list.add(new HexPlot(-3,1,2,GREEN));
        Map<String,Map<Integer,Integer>> map = player1.extractPlotsData(list);
        Set<Integer> answerset=new HashSet<>();
        answerset.add(2);
        answerset.add(3);
        assertTrue(answerset.equals(player1.checkSetSuitConf(map)));
    }

    @Test
    void isQuadrilateralPlots_P_YTrue() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,2,1,YELLOW));
        list.add(new HexPlot(-2,2,0,PINK));
        list.add(new HexPlot(-2,1,1,PINK));
        list.add(new HexPlot(-3,1,2,YELLOW));
        assertTrue(player1.isQuadrilateralPlots_P_Y(list));
    }
    @Test
    void isQuadrilateralPlots_P_YFalse1() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,2,1,GREEN));
        list.add(new HexPlot(-2,2,0,PINK));
        list.add(new HexPlot(-2,1,1,PINK));
        list.add(new HexPlot(-3,1,2,YELLOW));
        assertFalse(player1.isQuadrilateralPlots_P_Y(list));
    }
    @Test
    void isQuadrilateralPlots_P_YFalse2() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,2,1,GREEN));
        list.add(new HexPlot(-2,2,15,PINK));
        list.add(new HexPlot(-2,1,1,PINK));
        list.add(new HexPlot(-3,1,2,YELLOW));
        assertFalse(player1.isQuadrilateralPlots_P_Y(list));
    }

    @Test
    void isQuadrilateralPlots_G_PTrue() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,2,1,GREEN));
        list.add(new HexPlot(-2,2,0,PINK));
        list.add(new HexPlot(-2,1,1,PINK));
        list.add(new HexPlot(-3,1,2,GREEN));
        assertTrue(player1.isQuadrilateralPlots_G_P(list));
    }
    @Test
    void isQuadrilateralPlots_G_PFalse1() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,2,1,PINK));
        list.add(new HexPlot(-2,2,15,GREEN));
        list.add(new HexPlot(-2,1,1,GREEN));
        list.add(new HexPlot(-3,1,2,PINK));
        assertFalse(player1.isQuadrilateralPlots_G_P(list));
    }
    @Test
    void isQuadrilateralPlots_G_PFalse2() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,2,1,GREEN));
        list.add(new HexPlot(-2,2,15,PINK));
        list.add(new HexPlot(-2,1,1,PINK));
        list.add(new HexPlot(-3,1,2,YELLOW));
        assertFalse(player1.isQuadrilateralPlots_P_Y(list));
    }

    @Test
    void isQuadrilateralPlots_G_YTrue() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,2,1,GREEN));
        list.add(new HexPlot(-2,2,0,YELLOW));
        list.add(new HexPlot(-2,1,1,YELLOW));
        list.add(new HexPlot(-3,1,2,GREEN));
        assertTrue(player1.isQuadrilateralPlots_G_Y(list));
    }
    @Test
    void isQuadrilateralPlots_G_YFalse1() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,2,1,YELLOW));
        list.add(new HexPlot(-2,2,15,GREEN));
        list.add(new HexPlot(-2,1,1,GREEN));
        list.add(new HexPlot(-3,1,2,YELLOW));
        assertFalse(player1.isQuadrilateralPlots_G_Y(list));
    }
    @Test
    void isQuadrilateralPlots_G_YFalse2() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,2,1,GREEN));
        list.add(new HexPlot(-2,2,15,PINK));
        list.add(new HexPlot(-2,1,1,PINK));
        list.add(new HexPlot(-3,1,2,YELLOW));
        assertFalse(player1.isQuadrilateralPlots_G_Y(list));
    }


    @Test
    void findInDirectSamePlots() {
        listOfPlots.add(new HexPlot(-3,2,1,GREEN));
        listOfPlots.add(new HexPlot(-2,2,0,PINK));
        listOfPlots.add(new HexPlot(-2,1,1,PINK));
        listOfPlots.add(new HexPlot(-1,0,1,PINK));
        listOfPlots.add(new HexPlot(-3,1,2,YELLOW));
        assertTrue(player1.findInDirectSamePlots(PINK));
    }

    @Test
    void findQuadrilateralSamePlots() {
        listOfPlots.add(new HexPlot(-3,2,1,PINK));
        listOfPlots.add(new HexPlot(-2,2,15,PINK));
        listOfPlots.add(new HexPlot(-2,1,1,PINK));
        listOfPlots.add(new HexPlot(-1,0,1,GREEN));
        listOfPlots.add(new HexPlot(-3,1,2,PINK));
        assertTrue(player1.findQuadrilateralSamePlots());
    }

    @Test
    void findQuadrilateralPlots_P_Y() {
        listOfPlots.add(new HexPlot(-3,2,1,YELLOW));
        listOfPlots.add(new HexPlot(-2,2,0,PINK));
        listOfPlots.add(new HexPlot(-2,1,1,PINK));
        listOfPlots.add(new HexPlot(-3,1,2,YELLOW));
        assertTrue(player1.findQuadrilateralPlots_P_Y());
    }

    @Test
    void findQuadrilateralPlots_G_P() {
        listOfPlots.add(new HexPlot(-3,2,1,GREEN));
        listOfPlots.add(new HexPlot(-2,2,0,PINK));
        listOfPlots.add(new HexPlot(-2,1,1,PINK));
        listOfPlots.add(new HexPlot(-3,1,2,GREEN));
        assertTrue(player1.findQuadrilateralPlots_G_P());
    }

    @Test
    void findQuadrilateralPlots_G_Y() {
        listOfPlots.add(new HexPlot(-3,2,1,GREEN));
        listOfPlots.add(new HexPlot(-2,2,0,YELLOW));
        listOfPlots.add(new HexPlot(-2,1,1,YELLOW));
        listOfPlots.add(new HexPlot(-3,1,2,GREEN));
        assertTrue(player1.findQuadrilateralPlots_G_Y());
    }

}