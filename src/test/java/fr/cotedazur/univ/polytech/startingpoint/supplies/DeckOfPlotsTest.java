package fr.cotedazur.univ.polytech.startingpoint.supplies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckOfPlotsTest {

    private DeckOfPlots deckOfPlots;


    @BeforeEach
    void init(){
        deckOfPlots = new DeckOfPlots();
    }

    @Test
    void pickPlotTest() {
        assertEquals(deckOfPlots.size(), 27);
        deckOfPlots.pickPlot();
        assertEquals(deckOfPlots.size(), 26);
        for(int i = 0; i < 26; i++){
            deckOfPlots.pickPlot();
        }
        assertEquals(deckOfPlots.size(), 0);
        assertThrowsExactly(IndexOutOfBoundsException.class, deckOfPlots::pickPlot);
    }
}
