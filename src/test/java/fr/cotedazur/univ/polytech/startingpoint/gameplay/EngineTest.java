package fr.cotedazur.univ.polytech.startingpoint.gameplay;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static fr.cotedazur.univ.polytech.startingpoint.tools.Action.GameAction.PICK_OBJECTIVE;
import static fr.cotedazur.univ.polytech.startingpoint.tools.Strategy.Fa3STRATEGY;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EngineTest {

    Engine engine;

    @BeforeEach
    public void setUp() {

    }

    @Test
    // Le bot Fa3STRATEGY récupère un maximum de bambous, même s’il n’a pas de cartes avec la couleur correspondante
    void Fa3STRATEGYcollectBamboos(){
//        engine.game.playerList.get(0).setStrategy(Fa3STRATEGY);
//        engine.runGame(false);
//        engine.maxRounds = 10;
//
//        assertTrue(engine.game.playerActions[0]==PICK_OBJECTIVE
//                || engine.game.playerList.get(0).getUnMetObjectives().size()==5);

    }
}
