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
        assertEquals(27,deckOfPlots.size());
        deckOfPlots.pickPlot();
        assertEquals(26,deckOfPlots.size());
        for(int i = 0; i < 26; i++){
            deckOfPlots.pickPlot();
        }
        assertEquals(0,deckOfPlots.size());
        assertThrowsExactly(IndexOutOfBoundsException.class, deckOfPlots::pickPlot);
    }
}
