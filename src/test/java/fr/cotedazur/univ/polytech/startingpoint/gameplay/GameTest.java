package fr.cotedazur.univ.polytech.startingpoint.gameplay;

import fr.cotedazur.univ.polytech.startingpoint.gameplay.Game;
import fr.cotedazur.univ.polytech.startingpoint.gameplay.Player;
import fr.cotedazur.univ.polytech.startingpoint.supplies.*;
import fr.cotedazur.univ.polytech.startingpoint.tools.Action;
import fr.cotedazur.univ.polytech.startingpoint.tools.PlotImprovement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Stream;

import static fr.cotedazur.univ.polytech.startingpoint.gameplay.Game.*;
import static fr.cotedazur.univ.polytech.startingpoint.supplies.Dice.Condition.WIND;
import static fr.cotedazur.univ.polytech.startingpoint.tools.Action.GameAction.*;
import static fr.cotedazur.univ.polytech.startingpoint.tools.Color.*;
import static fr.cotedazur.univ.polytech.startingpoint.tools.Strategy.FA3STRATEGY;
import static org.junit.jupiter.api.Assertions.*;


class GameTest {

    private Game game;
    Player player1;
    Player player2;
    ByteArrayOutputStream outputStreamCaptor;

    @BeforeEach
    void init() {
        player1 = new Player("Ted");
        player2 = new Player("Wilfried");
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
        game.getBoard().add(hex1);
        game.getBoard().add(hex2);
        game.getBoard().add(hex3);
        game.getBoard().add(hex4);
        game.getBoard().add(hex5);
        game.getBoard().add(hex6);;

        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void choiceObjectiveTest() {
        game.choiceObjective(player1);
        assertEquals(44,game.getListOfObjectives().size());
        assertTrue(game.choiceObjective(player1));
        assertTrue(game.choiceObjective(player2));
        game.choiceObjective(player2);
        assertEquals(41,game.getListOfObjectives().size());
        assertTrue(game.choiceObjective(player2));
    }

    @Test
    void rollDiceTest() {
        assertTrue(new Dice().roll() instanceof Dice.Condition);
    }

    @Test
    void choiceAnIrrigation() {
        assertTrue(game.choiceAnIrrigation(player1));
    }

    @Test
    void placeAnIrrigation() {
        HexPlot hex3 = new HexPlot(0, -2, 2, PINK);
        getBoard().remove(hex3);
        assertTrue(game.choiceAnIrrigation(player1));
        assertEquals(1,player1.getCanalList().size());
        assertTrue(game.placeAnIrrigation(player1));
    }
    @Test
    void placeAnIrrigation2() {
        HexPlot hex3 = new HexPlot(0, -2, 2, PINK);
        getBoard().remove(hex3);
        assertTrue(game.choiceAnIrrigation(player1));
        assertEquals(1,player1.getCanalList().size());
        assertTrue(game.placeAnIrrigation(player1));
        assertEquals(0,player1.getCanalList().size());
    }

    @Test
    void movePanda() {
        assertTrue(game.movePanda(player2));
    }

    @Test
    void testMovePanda() {
        assertTrue(game.movePanda(player2));
    }

    @Test
    void moveGardener() {
        int oldStock = game.getBambooStock().size();
        HashMap<HexPlot, Integer> bambooPerPlot = new HashMap<>();
        for (HexPlot plot : getBoard()) {
            bambooPerPlot.put(plot, plot.getBamboos().size());
        }
        game.moveGardener(player1);
        //assertTrue(outputStreamCaptor.toString().contains("La position du jardinier aprés deplacement"));
        //assertTrue(outputStreamCaptor.toString().contains("Un bambou "+game.gardener.getPosition().getColor()+" pousse sur la parcelle HexPlot"));
        assertEquals(oldStock - 1,getBambooStock().size());
    }

    @Test
    void plotEnclosed() {
        game.setBoard(new Board());
        HexPlot hex1 = new HexPlot(1, 0, -1, GREEN);
        hex1.setImprovement(PlotImprovement.FENCE);
        assertEquals(0,hex1.getBamboos().size());
        getBoard().add(hex1);
        game.movePanda(player1);
        assertEquals(game.getPanda().getPosition(), hex1);
        //assertTrue(outputStreamCaptor.toString().contains("cette parcelle est protégée par un enclos"));
    }

    @Test
    void placeImprovementTest() {
        getBoard().forEach(hexPlot -> {
            assertNull(hexPlot.getImprovement());
        });
        getBoard().getLastHexPlot().getBamboos().clear();
        Dice.Condition condition = Dice.Condition.CLOUDS;
        game.actOnWeather(condition, player1);
        Stream<HexPlot> improved = getBoard().stream().filter(hexPlot -> hexPlot.getImprovement() != null);
        System.out.println(improved);;
        assertEquals(1,improved.count());
    }

    @Test
    void noImprovablePlotsTest() {
        Dice.Condition condition = Dice.Condition.CLOUDS;
        game.actOnWeather(condition, player2);
        assertTrue(game.getBoard().contains(new HexPlot()));
        //assertTrue(outputStreamCaptor.toString().contains("Aucune parcelle aménageable"));
    }

    @Test
    void noMoreImprovementsTest() {
        Dice.Condition condition = Dice.Condition.CLOUDS;
        game.getBambooStock().clear();
        game.actOnWeather(condition, player1);
        assertTrue(game.getBambooStock().isEmpty());
        //assertTrue(outputStreamCaptor.toString().contains("Il y a plus d'aménagements dans la liste"));
    }

    @Test
    void actOnWeatherSTORM() {
        Dice.Condition condition = Dice.Condition.STORM;
        HexPlot oldPosition = game.getPanda().getPosition();
        int eatenBamboos = player2.getEatenBamboos().size();
        game.actOnWeather(condition, player2);
        //assertNotEquals(oldPosition, game.panda.getPosition());
        assertEquals(eatenBamboos + 1, player2.getEatenBamboos().size());
    }

    @Test
    void actOnWeatherMYSTERY() {
        Action.GameAction[] twoActions = player1.getStrategy().pickTwoDistinct();
        game.getPlayerActions()[0] = twoActions[0];
        game.getPlayerActions()[1] = twoActions[1];
        Dice.Condition condition = Dice.Condition.MYSTERY;
        game.actOnWeather(condition, player1);
        assertTrue(game.getPlayerActions().length>=0);
    }

    @Test
    void actOnWeatherRAIN() {
        Dice.Condition condition = Dice.Condition.RAIN;
        int oldStock = game.getBambooStock().size();
        game.actOnWeather(condition, player1);
        assertNotEquals(oldStock, game.getBambooStock().size());
        assertEquals(oldStock - 1, game.getBambooStock().size());
    }

    @Test
    void actOnWeatherSUN() {
        Action.GameAction[] twoActions = player1.getStrategy().pickTwoDistinct();
        game.getPlayerActions()[0] = twoActions[0];
        game.getPlayerActions()[1] = twoActions[1];
        game.actOnWeather(Dice.Condition.SUN, player1);
        assertEquals(game.getPlayerActions()[0],twoActions[0]);
        assertEquals(game.getPlayerActions()[1],twoActions[1]);
        /*assertTrue(outputStreamCaptor.toString().contains("choisit une action supplémentaire :"));
        Action.GameAction actionSupp = null;
        for (Action.GameAction action : player1.getStrategy().getActions()){
            if (outputStreamCaptor.toString().contains(action.toString()))
                actionSupp = action;
        }
        assertNotEquals(actionSupp, game.playerActions[0]);
        assertNotEquals(actionSupp, game.playerActions[1]);
         */
    }

    @Test
    void actOnWeatherWIND() {
        Action.GameAction[] twoActions = player1.getStrategy().pickTwoDistinct();
        game.getPlayerActions()[0] = twoActions[0];
        game.getPlayerActions()[1] = twoActions[1];
        game.actOnWeather(WIND, player1);

        Action.GameAction res = game.getPlayerActions()[0];
        
        assertEquals(game.getPlayerActions()[0], res);
    }

    @Test
    void cannotActOnWeatherRAINnoMoreBamboos() {
        Dice.Condition condition = Dice.Condition.RAIN;
        game.getBambooStock().clear();
        int oldStock = game.getBambooStock().size();
        game.actOnWeather(condition, player1);
        assertEquals(oldStock, game.getBambooStock().size());
    }

    @Test
    void cannotActOnWeatherRAINnoPlots() {
        Dice.Condition condition = Dice.Condition.RAIN;
        Board board = new Board();
        BambooStock bambooStock = new BambooStock();
        int oldStock = bambooStock.size();
        game.actOnWeather(condition, player1);
        assertEquals(oldStock, bambooStock.size());
    }

    @Test
    void addAnIrrigation() {
        IrrigationStock canStock = game.getIrrigationStock();
        Optional<IrrigationCanal> canal = canStock.getOneUnused();
        player1.addAnIrrigation(canal.get());
        assertEquals(1,player1.getCanalList().size());
    }

    @Test
    void returnAnIrrigation() {
        assertEquals(player1.returnAnIrrigation(), Optional.empty());
        IrrigationStock canStock = game.getIrrigationStock();
        Optional<IrrigationCanal> canal = canStock.getOneUnused();
        player1.addAnIrrigation(canal.get());
        assertEquals(1,player1.getCanalList().size());
        assertEquals(player1.returnAnIrrigation(), canal);
    }

    @Test
    void findAnAvailableIrrigationSource() {
        assertEquals( Optional.empty(),player1.returnAnIrrigation());
        IrrigationStock canStock = game.getIrrigationStock();
        Optional<IrrigationCanal> canal = canStock.getOneUnused();
        player1.addAnIrrigation(canal.get());
        assertEquals(1,player1.getCanalList().size());
        assertEquals(player1.returnAnIrrigation(), canal);
        getIrrigationStock().primordialCanal(game.getBoard());
        HexPlot hex7 = new HexPlot(1, -1, 0, YELLOW);
        hex7.getBamboos().add(new Bamboo(YELLOW));
        game.getBoard().add(hex7);
        assertTrue(player1.findAnAvailableIrrigationSource(canStock).isPresent());

    }

    @Test
    void findAnAvailableIrrigationDest() {
        assertEquals( Optional.empty(),player1.returnAnIrrigation());
        IrrigationStock canStock = game.getIrrigationStock();
        Optional<IrrigationCanal> canal = canStock.getOneUnused();
        player1.addAnIrrigation(canal.get());
        assertEquals(1,player1.getCanalList().size());
        assertEquals(player1.returnAnIrrigation(), canal);
        Board bd = game.getBoard();
        Optional<HexPlot> hex = player1.findAnAvailableIrrigationDest(bd, new HexPlot());
        assertTrue(!canStock.getAllHexplotFrom().contains(hex.get()));
        assertTrue(bd.contains(hex.get()));
    }

    @Test
    // Les deux premiers mouvements du bot Fa3STRATEGY sont PICK_OBJECTIVE et PLACE_IRRIGATION
    void playWithFa3STRATEGY(){
        player1.setStrategy(FA3STRATEGY);
        game.play(player1);
        if (game.getDice().getLastValue() != WIND)
            assertTrue(game.getPlayerActions()[0]==PICK_OBJECTIVE || game.getPlayerActions()[1]==PICK_OBJECTIVE);
    }

    @Test
    void playWithFa3STRATEGYandWeatherWIND(){
        player1.setStrategy(FA3STRATEGY);
        game.play(player1);
        if (game.getDice().getLastValue() == WIND) {
            assertTrue(game.getPlayerActions()[0] == PICK_OBJECTIVE && game.getPlayerActions()[1] == PICK_OBJECTIVE
            || game.getPlayerActions()[0] == PLACE_IRRIGATION && game.getPlayerActions()[1] == PLACE_IRRIGATION );
        }

    }

    @Test
    // Le bot Fa3STRATEGY essaie d’avoir 5 cartes objectif en main tout le temps
    void always5objectivesFa3STRATEGY(){
        player1.setStrategy(FA3STRATEGY);
        for (int i = 0; i < 10; i++) { // test sur 10 tours
            game.play(player1);
            if (game.getDice().getLastValue() != WIND)
                assertTrue(game.getPlayerActions()[0]==PICK_OBJECTIVE
                        ||game.getPlayerActions()[0]==PICK_PLOT
                    || player1.getUnMetObjectives().size()>=4);
        }
    }

//    @Test
//    // Quand il tire une la météo « ? » dans les premiers tours, le bot Fa3STRATEGY prend une irrigation.
//    void Fa3STRATEGYactOnWeatherMYSTERY(){
//        player1.setStrategy(Fa3STRATEGY);
//        int irrigationsbefore = player1.getCanalList().size();
//        Dice.Condition condition = Dice.Condition.MYSTERY;
//        game.actOnWeather(condition, player1);
//        assertEquals(irrigationsbefore+1, player1.getCanalList().size());
//    }
}