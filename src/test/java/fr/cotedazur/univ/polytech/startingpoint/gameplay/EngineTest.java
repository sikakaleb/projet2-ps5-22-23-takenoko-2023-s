package fr.cotedazur.univ.polytech.startingpoint.gameplay;

import fr.cotedazur.univ.polytech.startingpoint.gameplay.Engine;
import fr.cotedazur.univ.polytech.startingpoint.gameplay.Main;
import fr.cotedazur.univ.polytech.startingpoint.gameplay.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static fr.cotedazur.univ.polytech.startingpoint.tools.Action.GameAction.*;
import static fr.cotedazur.univ.polytech.startingpoint.tools.Strategy.Fa3STRATEGY;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EngineTest {

    Player player1;
    Player player2;
    Engine engine;
    Game game;

    @BeforeEach
    public void setUp() {
        player1 = new Player("Fa3STRATEGY", Fa3STRATEGY);
        player2 = new Player("Fa3STRATEGY", Fa3STRATEGY);
        engine = new Engine(player1, player2);
        game = new Game(engine.p1,engine.p2);

    }

    @Test
    // Le bot Fa3STRATEGY récupère un maximum de bambous, même s’il n’a pas de cartes avec la couleur correspondante
    void Fa3STRATEGYcollectBamboos(){
        engine.runGame(game, false);
        assertTrue(game.getPlayerList().get(0).getStrategy().getActions().contains(MOVE_GARDENER));
        assertTrue(game.getPlayerList().get(0).getStrategy().getActions().contains(MOVE_PANDA));

    }

    @Test
    // Le bot Fa3STRATEGY essaie d’avoir 5 cartes objectif en main tout le temps
    void always5objectivesFa3STRATEGY(){
        game.getPlayerList().get(1).setStrategy(Fa3STRATEGY);
        engine.runGame(game,false);
        assertTrue(game.getPlayerList().get(1).getStrategy().getActions().contains(PICK_OBJECTIVE)
                || game.getPlayerList().get(1).getUnMetObjectives().size()>=4);
    }
}
