package fr.cotedazur.univ.polytech.startingpoint;

import objectives.Objective;
import objectives.PandaObjective;
import objectives.PlotObjective;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import supplies.Bamboo;
import tools.Color;

import java.util.*;

import static tools.Color.*;
import static tools.PandaObjectiveConfiguration.*;
import static tools.PlotObjectiveConfiguration.*;
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

    /*@Test
    void getPlayerIdTest() {
        System.out.println(player1.getPlayerId()+"*************");
        assertEquals(player1.getPlayerId(),19);
    }*/

    @Test
    public void dectectPandaObjectiveTWO_YELLOW(){
        assertEquals(player1.eatenBamboos.size(), 0);
        player1.addNewObjective(new PandaObjective(4,TWO_YELLOW, new Color[]{YELLOW}));
        player1.eatenBamboos.add(new Bamboo(GREEN));
        player1.eatenBamboos.add(new Bamboo(YELLOW));
        assertFalse(player1.dectectPandaObjective());
        assertEquals(player1.eatenBamboos.size(), 2);
        player1.eatenBamboos.add(new Bamboo(YELLOW));
        assertEquals(player1.eatenBamboos.size(), 3);
        assertTrue(player1.dectectPandaObjective());
        assertEquals(player1.eatenBamboos.size(), 1);
    }

    @Test
    public void dectectPandaObjectiveTHREE_GREEN(){
        player1.addNewObjective(new PandaObjective(4,THREE_GREEN, new Color[]{GREEN}));
        assertEquals(player1.eatenBamboos.size(), 0);
        player1.eatenBamboos.add(new Bamboo(GREEN));
        player1.eatenBamboos.add(new Bamboo(GREEN));
        assertEquals(player1.eatenBamboos.size(), 2);
        assertFalse(player1.dectectPandaObjective());
        player1.eatenBamboos.add(new Bamboo(GREEN));
        assertEquals(player1.eatenBamboos.size(), 3);
        assertTrue(player1.dectectPandaObjective());
        assertEquals(player1.eatenBamboos.size(), 0);
    }

    @Test
    public void dectectPandaObjectiveONE_OF_EACH(){
        player1.addNewObjective(new PandaObjective(6,ONE_OF_EACH, new Color[]{YELLOW, GREEN, PINK}));
        assertEquals(player1.eatenBamboos.size(), 0);
        player1.eatenBamboos.add(new Bamboo(GREEN));
        player1.eatenBamboos.add(new Bamboo(YELLOW));
        assertEquals(player1.eatenBamboos.size(), 2);
        assertFalse(player1.dectectPandaObjective());
        assertEquals(player1.eatenBamboos.size(), 2);
        player1.eatenBamboos.add(new Bamboo(PINK));
        assertEquals(player1.eatenBamboos.size(), 3);
        assertTrue(player1.dectectPandaObjective());
        assertEquals(player1.eatenBamboos.size(), 0);
    }

}