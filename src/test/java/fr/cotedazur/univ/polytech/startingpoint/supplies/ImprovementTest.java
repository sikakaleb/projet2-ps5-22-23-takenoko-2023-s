package fr.cotedazur.univ.polytech.startingpoint.supplies;

import fr.cotedazur.univ.polytech.startingpoint.tools.PlotImprovement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static fr.cotedazur.univ.polytech.startingpoint.tools.PlotImprovement.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ImprovementTest {

    private DeckOfImprovements deck;

    @BeforeEach
    public void setUp(){
        deck = new DeckOfImprovements();
    }

    @Test
    public void DeckOfImprovementsTest(){

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
    public void pickFromDeckTest(){
        assertEquals(deck.size(),9);

        deck.pick();
        assertEquals(deck.size(),8);

        deck.removeAll(deck);
        assertTrue(deck.isEmpty());
        try {
            deck.pick();
        }
        catch (IndexOutOfBoundsException e) {
            assertEquals(e.getMessage(), "Il y a plus d'aménagements dans la liste");
        }
    }
}

