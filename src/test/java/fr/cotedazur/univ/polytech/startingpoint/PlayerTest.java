package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static fr.cotedazur.univ.polytech.startingpoint.Game.listOfPlots;
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
    void getHeightTest() {
        System.out.println(player1.getPlayerId()+"*************");
        assertEquals(player1.getHeight(),1);
    }

    @Test
    void getNameTest() {
        assertEquals(player1.getName(),"Ted");
    }

    @Test
    void getObjectiveAchievedTest() {
        List<Objective> objList =new ArrayList<>();
        assertTrue(player1.getObjectiveAchieved().equals(objList));
    }

    @Test
    void addObjectiveAchievedTest() {
        player1.addObjectiveAchieved(triangularPlotObj);
        int lastIdx = player1.getObjectiveAchieved().size() - 1;
        assertEquals(player1.objectiveAchieved.get(lastIdx),triangularPlotObj);
    }

    @Test
    void getUnMetObjectivesTest() {
        System.out.println(player1.getUnMetObjectives());
        System.out.println(singlelist);
        assertTrue(player1.getUnMetObjectives().equals(singlelist));
    }

    @Test
    void addNewObjectiveTest() {
        player1.addNewObjective(quadriPlotObj);
        int lastIdx = player1.getUnMetObjectives().size() - 1;
        assertEquals(player1.getUnMetObjectives().get(lastIdx),quadriPlotObj);
    }

    @Test
    void withdrawUnMetObjectiveTest() {
        player1.withdrawUnMetObjective(quadriPlotObj);
        assertFalse(player1.withdrawUnMetObjective(quadriPlotObj));
    }

    @Test
    void validateUnMetObjectivesTest() {
        List<Objective> objList= new ArrayList<>();
        player1.addNewObjective(quadriPlotObj);
        player1.validateUnMetObjectives(quadriPlotObj);
        assertTrue(player1.getUnMetObjectives().equals(singlelist));

    }

    @Test
    void getCumulOfpointTest() {
        player1.validateUnMetObjectives(quadriPlotObj);
        assertEquals(player1.getCumulOfpoint(),4);
    }

    @Test
    void checkPondNeighborTest(){
        HexPlot pond = listOfPlots.iterator().next();
        System.out.println(pond);
        assertTrue(pond.isPond());
        assertFalse(player1.checkPondNeighbor(pond));
        player1.addAplotToGame();
        assertTrue(player1.checkPondNeighbor(listOfPlots.iterator().next()));
        assertTrue(player1.checkPondNeighbor(new HexPlot(-1, 1, 0, Color.GREEN)));
        assertTrue(player1.checkPondNeighbor(new HexPlot(1, -1, 0, Color.PINK)));
        assertFalse(player1.checkPondNeighbor(new HexPlot(-2, 2, -0, Color.YELLOW)));
    }

    @Test
    void checkTwoPlotNeighborsTest(){
        HexPlot nextHexPlot = listOfPlots.iterator().next();
        assertFalse(player1.checkTwoPlotNeighbors(nextHexPlot));

        assertFalse(player1.checkTwoPlotNeighbors(new HexPlot(-1, 1, 0, Color.GREEN)));
        listOfPlots.add(new HexPlot(-1, 1, 0, Color.GREEN));

        assertTrue(player1.checkTwoPlotNeighbors(new HexPlot(0,1,-1, Color.PINK)));
        assertTrue(player1.checkTwoPlotNeighbors(new HexPlot(-1,0,1, Color.PINK)));

        assertFalse(player1.checkTwoPlotNeighbors(new HexPlot(-2, 2, -0, Color.YELLOW)));
    }

    @Test
    void findAvailableNeighborsTest(){
        HexPlot hexPlot = listOfPlots.iterator().next();
        System.out.println(hexPlot.plotNeighbor());

        assertEquals(player1.findAvailableNeighbors(hexPlot).size(), 6);
        player1.addAplotToGame();
        assertEquals(player1.findAvailableNeighbors(hexPlot).size(), 5);

        listOfPlots.addAll(hexPlot.plotNeighbor());
        assertEquals(player1.findAvailableNeighbors(hexPlot).size(), 0);
    }

    @Test
    void addAplotToGameTest() {
        player1.addAplotToGame();
        player2.addAplotToGame();
        player2.addAplotToGame();
        System.out.println(listOfPlots);
        assertEquals(listOfPlots.size(),4);
    }

    @Test
    void choicePlotTest() {
        player2.ChoicePlot(listOfPlots.iterator().next());
        System.out.println(listOfPlots);
        assertEquals(listOfPlots.size(),2);
    }

    /*A revoir */
    @Test
    void listOfCombinationsTest() {
        player1.addAplotToGame();
        player2.addAplotToGame();
        player1.addAplotToGame();
        List<HexPlot> temp=new ArrayList<>(listOfPlots);
        temp.remove(new HexPlot());
        List<List<HexPlot>> list =player1.listOfCombinations(3);
        assertTrue(list.contains(temp)&&list.size()==1);
    }

    @Test
    void extractPlotsDataTest() {
    }

    @Test
    void countRTest() {
    }

    @Test
    void countSTest() {
    }

    @Test
    void countQTest() {
    }

    @Test
    void sSuiteTest() {
    }

    @Test
    void isDirectSamePlotsTest() {
        List<HexPlot> list = new ArrayList<>();
        list.add(new HexPlot(-3,0,3, Color.GREEN));
        list.add(new HexPlot(-2,0,2, Color.YELLOW));
        list.add(new HexPlot(-1,0,1, Color.PINK));
        assertTrue(player1.isDirectSamePlots(list));
    }

    @Test
    void findDirectSamePlotsTest() {
        listOfPlots.add(new HexPlot(-3,0,3, Color.GREEN));
        listOfPlots.add(new HexPlot(-2,0,2, Color.YELLOW));
        listOfPlots.add(new HexPlot(-1,0,1, Color.PINK));
        assertTrue(player1.findDirectSamePlots());
    }
    @Test
    void getPlayerIdTest() {
        System.out.println(player1.getPlayerId()+"*************");
        assertEquals(player1.getPlayerId(),19);
    }
}