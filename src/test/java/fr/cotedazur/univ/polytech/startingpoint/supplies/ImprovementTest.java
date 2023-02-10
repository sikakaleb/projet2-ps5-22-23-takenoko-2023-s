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
        assertEquals(9,deck.size());
        assertEquals(3,
                deck.stream().filter(plotImprovement -> plotImprovement == FENCE).count()
        );
        assertEquals(3,
                deck.stream().filter(plotImprovement -> plotImprovement == FERTILIZER).count()
        );
        assertEquals(3,
                deck.stream().filter(plotImprovement -> plotImprovement == POOL).count()
        );
    }

    @Test
    void pickFromDeckTest(){
        assertEquals(9,deck.size());

        deck.pick();
        assertEquals(8,deck.size());

        deck.removeAll(deck);
        assertTrue(deck.isEmpty());
        try {
            deck.pick();
        }
        catch (IndexOutOfBoundsException e) {
            assertEquals("Il y a plus d'amenagements dans la liste",e.getMessage());
        }
    }
}

