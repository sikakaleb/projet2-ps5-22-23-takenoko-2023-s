package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.gameplay.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static fr.cotedazur.univ.polytech.startingpoint.tools.Action.GameAction.*;
import static fr.cotedazur.univ.polytech.startingpoint.tools.Strategy.Fa3STRATEGY;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {

    Main main;

    @BeforeEach
    public void setUp() {
        main = new Main();
    }

    @Test
    // Le bot Fa3STRATEGY récupère un maximum de bambous, même s’il n’a pas de cartes avec la couleur correspondante
    void Fa3STRATEGYcollectBamboos(){
        main.game.playerList.get(0).setStrategy(Fa3STRATEGY);
        main.runGame();
        assertTrue(main.game.playerList.get(0).getStrategy().getActions().contains(MOVE_GARDENER));
        assertTrue(main.game.playerList.get(0).getStrategy().getActions().contains(MOVE_PANDA));

    }

    @Test
    // Le bot Fa3STRATEGY essaie d’avoir 5 cartes objectif en main tout le temps
    void always5objectivesFa3STRATEGY(){
        main.game.playerList.get(1).setStrategy(Fa3STRATEGY);
        main.runGame();
        assertTrue(main.game.playerActions[0]==PICK_OBJECTIVE
                || main.game.playerList.get(1).getUnMetObjectives().size()>=4);
    }
}
