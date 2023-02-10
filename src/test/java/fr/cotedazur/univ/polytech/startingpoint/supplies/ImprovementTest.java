package fr.cotedazur.univ.polytech.startingpoint.supplies;

import fr.cotedazur.univ.polytech.startingpoint.tools.PlotImprovement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static fr.cotedazur.univ.polytech.startingpoint.tools.PlotImprovement.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ImprovementTest {

    private DeckOfImprovements deck;

    @BeforeEach
    void setUp(){
        deck = new DeckOfImprovements();
    }

    @Test
    void DeckOfImprovementsTest(){

        assertTrue(deck instanceof ArrayList<PlotImprovement>);
        assertEquals(deck.size(), 9);
        assertEquals(
                deck.stream().filter(plotImprovement -> plotImprovement == FENCE).count(),
                3
        );
        assertEquals(
                deck.stream().filter(plotImprovement -> plotImprovement == FERTILIZER).count(),
                3
        );
        assertEquals(
                deck.stream().filter(plotImprovement -> plotImprovement == POOL).count(),
                3
        );
    }

    @Test
    void pickFromDeckTest(){
        assertEquals(deck.size(),9);

        deck.pick();
        assertEquals(deck.size(),8);

        deck.removeAll(deck);
        assertTrue(deck.isEmpty());
        try {
            deck.pick();
        }
        catch (IndexOutOfBoundsException e) {
            assertEquals(e.getMessage(), "Il y a plus d'amenagements dans la liste");
        }
    }
}

