package fr.cotedazur.univ.polytech.startingpoint.supplies;

import fr.cotedazur.univ.polytech.startingpoint.gameplay.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static fr.cotedazur.univ.polytech.startingpoint.tools.Action.GameAction.PICK_OBJECTIVE;
import static fr.cotedazur.univ.polytech.startingpoint.tools.Action.GameAction.PICK_PLOT;
import static fr.cotedazur.univ.polytech.startingpoint.tools.Strategy.Fa3STRATEGY;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {

    Main main;

    @BeforeEach
    public void setUp() {
        main = new Main(

        );
    }

    @Test
    // Le bot Fa3STRATEGY récupère un maximum de bambous, même s’il n’a pas de cartes avec la couleur correspondante
    void Fa3STRATEGYcollectBamboos(){
        main.game.getPlayerList().get(0).setStrategy(Fa3STRATEGY);
        main.runGame();
        main.maxRounds = 10;
        System.out.println(main.game.getPlayerActions());
        assertTrue(main.game.getPlayerActions()[0]==PICK_OBJECTIVE
                ||main.game.getPlayerActions()[0]==PICK_PLOT
                || main.game.getPlayerList().get(0).getStrategy().getActions().size()>=4);

    }

}


