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
import static fr.cotedazur.univ.polytech.startingpoint.tools.Action.GameAction.PICK_OBJECTIVE;
import static fr.cotedazur.univ.polytech.startingpoint.tools.Action.GameAction.PLACE_IRRIGATION;
import static fr.cotedazur.univ.polytech.startingpoint.tools.Color.*;
import static fr.cotedazur.univ.polytech.startingpoint.tools.Strategy.Fa3STRATEGY;
import static org.junit.jupiter.api.Assertions.*;


class GameTest {

    private Game game;
    Player player1;
    Player player2;
    ByteArrayOutputStream outputStreamCaptor;

    @BeforeEach
    public void init() {
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
        board.add(hex1);
        board.add(hex2);
        board.add(hex3);
        board.add(hex4);
        board.add(hex5);
        board.add(hex6);

        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void choiceObjectiveTest() {
        game.choiceObjective(player1);
        assertEquals(game.getObjective().size(), 44);
        assertTrue(game.choiceObjective(player1));
        assertTrue(game.choiceObjective(player2));
        game.choiceObjective(player2);
        assertEquals(game.getObjective().size(), 41);
        assertTrue(game.choiceObjective(player2));
    }

    @Test
    public void rollDiceTest() {
        assertTrue(new Dice().roll() instanceof Dice.Condition);
    }

    @Test
    void choiceAnIrrigation() {
        assertTrue(game.choiceAnIrrigation(player1));
    }

    @Test
    void placeAnIrrigation() {
        HexPlot hex3 = new HexPlot(0, -2, 2, PINK);
        board.remove(hex3);
        assertTrue(game.choiceAnIrrigation(player1));
        assertEquals(player1.getCanalList().size(), 1);
        assertTrue(game.placeAnIrrigation(player1));
    }
    @Test
    void placeAnIrrigation2() {
        HexPlot hex3 = new HexPlot(0, -2, 2, PINK);
        board.remove(hex3);
        assertTrue(game.choiceAnIrrigation(player1));
        assertEquals(player1.getCanalList().size(), 1);
        assertTrue(game.placeAnIrrigation(player1));
        assertEquals(player1.getCanalList().size(), 0);
    }

    @Test
    void movePanda() {
        assertTrue(game.movePanda(player2));
    }

    @Test
    void testMovePanda() {
    }

    @Test
    public void moveGardener() {
        int oldStock = game.bambooStock.size();
        HashMap<HexPlot, Integer> bambooPerPlot = new HashMap<>();
        for (HexPlot plot : board) {
            bambooPerPlot.put(plot, plot.getBamboos().size());
        }
        game.moveGardener(player1);
        //assertTrue(outputStreamCaptor.toString().contains("La position du jardinier aprés deplacement"));
        //assertTrue(outputStreamCaptor.toString().contains("Un bambou "+game.gardener.getPosition().getColor()+" pousse sur la parcelle HexPlot"));
        assertEquals(bambooStock.size(), oldStock - 1);
    }

    @Test
    public void plotEnclosed() {
        board = new Board();
        HexPlot hex1 = new HexPlot(1, 0, -1, GREEN);
        hex1.setImprovement(PlotImprovement.FENCE);
        assertEquals(hex1.getBamboos().size(), 0);
        board.add(hex1);
        game.movePanda(player1);
        assertEquals(game.panda.getPosition(), hex1);
        //assertTrue(outputStreamCaptor.toString().contains("cette parcelle est protégée par un enclos"));
    }

    @Test
    public void placeImprovementTest() {
        board.forEach(hexPlot -> {
            assertNull(hexPlot.getImprovement());
        });
        board.getLastHexPlot().getBamboos().clear();
        Dice.Condition condition = Dice.Condition.CLOUDS;
        game.actOnWeather(condition, player1);
        Stream<HexPlot> improved = board.stream().filter(hexPlot -> hexPlot.getImprovement() != null);
        assertEquals(improved.count(), 1);
    }

    @Test
    public void noImprovablePlotsTest() {
        Dice.Condition condition = Dice.Condition.CLOUDS;
        game.actOnWeather(condition, player2);
        //assertTrue(outputStreamCaptor.toString().contains("Aucune parcelle aménageable"));
    }

    @Test
    public void noMoreImprovementsTest() {
        Dice.Condition condition = Dice.Condition.CLOUDS;
        game.deckOfImprovements.clear();
        game.actOnWeather(condition, player1);
        //assertTrue(outputStreamCaptor.toString().contains("Il y a plus d'aménagements dans la liste"));
    }

    @Test
    public void actOnWeatherSTORM() {
        Dice.Condition condition = Dice.Condition.STORM;
        HexPlot oldPosition = game.panda.getPosition();
        int eatenBamboos = player2.eatenBamboos.size();
        game.actOnWeather(condition, player2);
        //assertNotEquals(oldPosition, game.panda.getPosition());
        assertEquals(eatenBamboos + 1, player2.eatenBamboos.size());
    }

    @Test
    public void actOnWeatherMYSTERY() {
        Action.GameAction[] twoActions = player1.getStrategy().pickTwoDistinct();
        game.playerActions[0] = twoActions[0];
        game.playerActions[1] = twoActions[1];
        Dice.Condition condition = Dice.Condition.MYSTERY;
        game.actOnWeather(condition, player1);
    }

    @Test
    public void actOnWeatherRAIN() {
        Dice.Condition condition = Dice.Condition.RAIN;
        int oldStock = game.bambooStock.size();
        game.actOnWeather(condition, player1);
        assertNotEquals(oldStock, game.bambooStock.size());
        assertEquals(oldStock - 1, game.bambooStock.size());
    }

    @Test
    public void actOnWeatherSUN() {
        Action.GameAction[] twoActions = player1.getStrategy().pickTwoDistinct();
        game.playerActions[0] = twoActions[0];
        game.playerActions[1] = twoActions[1];
        game.actOnWeather(Dice.Condition.SUN, player1);
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
    public void actOnWeatherWIND() {
        Action.GameAction[] twoActions = player1.getStrategy().pickTwoDistinct();
        game.playerActions[0] = twoActions[0];
        game.playerActions[1] = twoActions[1];
        game.actOnWeather(WIND, player1);
        assertEquals(game.playerActions[0], game.playerActions[0]);
    }

    @Test
    public void cannotActOnWeatherRAINnoMoreBamboos() {
        Dice.Condition condition = Dice.Condition.RAIN;
        game.bambooStock.clear();
        int oldStock = game.bambooStock.size();
        game.actOnWeather(condition, player1);
        assertEquals(oldStock, game.bambooStock.size());
    }

    @Test
    public void cannotActOnWeatherRAINnoPlots() {
        Dice.Condition condition = Dice.Condition.RAIN;
        board = new Board();
        game.bambooStock = new BambooStock();
        int oldStock = game.bambooStock.size();
        game.actOnWeather(condition, player1);
        assertEquals(oldStock, game.bambooStock.size());
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

    @Test
    // Les deux premiers mouvements du bot Fa3STRATEGY sont PICK_OBJECTIVE et PLACE_IRRIGATION
    void playWithFa3STRATEGY(){
        player1.setStrategy(Fa3STRATEGY);
        game.play(player1);
        if (game.dice.getLastValue() != WIND)
            assertTrue(game.playerActions[0]==PICK_OBJECTIVE || game.playerActions[1]==PICK_OBJECTIVE);
    }

    @Test
    void playWithFa3STRATEGYandWeatherWIND(){
        player1.setStrategy(Fa3STRATEGY);
        game.play(player1);
        if (game.dice.getLastValue() == WIND) {
            assertTrue(game.playerActions[0] == PICK_OBJECTIVE && game.playerActions[1] == PICK_OBJECTIVE
            || game.playerActions[0] == PLACE_IRRIGATION && game.playerActions[1] == PLACE_IRRIGATION );
        }

    }

    @Test
    // Le bot Fa3STRATEGY essaie d’avoir 5 cartes objectif en main tout le temps
    void always5objectivesFa3STRATEGY(){
        player2.setStrategy(Fa3STRATEGY);
        for (int i = 0; i < 10; i++) { // test sur 10 tours
            game.play(player2);
            if (game.dice.getLastValue() != WIND)
                assertTrue(game.playerActions[0]==PICK_OBJECTIVE
                    || player2.getUnMetObjectives().size()>=4);
        }
    }

    @Test
    // Quand il tire une la météo « ? » dans les premiers tours, le bot Fa3STRATEGY prend une irrigation.
    void Fa3STRATEGYactOnWeatherMYSTERY(){
        player1.setStrategy(Fa3STRATEGY);
        int irrigationsbefore = player1.getCanalList().size();
        Dice.Condition condition = Dice.Condition.MYSTERY;
        game.actOnWeather(condition, player1);
        assertEquals(irrigationsbefore+1, player1.getCanalList().size());
    }
}