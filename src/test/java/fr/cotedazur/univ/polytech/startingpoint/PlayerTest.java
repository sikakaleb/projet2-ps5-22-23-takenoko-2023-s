package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        List<HexPlot> temp=new ArrayList<>(listOfPlots);
        List<List<HexPlot>> list =player1.listOfCombinations(3);
        assertTrue(list.contains(temp)&&list.size()==1);
    }

    @Test
    void extractPlotsData() {
    }

    @Test
    void countR() {
    }

    @Test
    void countS() {
    }

    @Test
    void countQ() {
    }

    @Test
    void sSuite() {
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
    @Test
    void getPlayerId() {
        System.out.println(player1.getPlayerId()+"*************");
        assertEquals(player1.getPlayerId(),19);
    }
}