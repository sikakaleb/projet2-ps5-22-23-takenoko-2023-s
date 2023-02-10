package fr.cotedazur.univ.polytech.startingpoint.supplies;

import fr.cotedazur.univ.polytech.startingpoint.gameplay.Game;
import fr.cotedazur.univ.polytech.startingpoint.gameplay.Player;
import fr.cotedazur.univ.polytech.startingpoint.supplies.Emperor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmperorTest {

    Player player1;
    Player player2;
    Emperor emperor;

    @BeforeEach
    void setUp() {
        player1 = new Player("Ted");
        player2 = new Player("Wilfried");
        emperor = new Emperor(new Game(player1, player2));
    }

    @Test
    void judgementTest() {
        player1.setScore(10);
        player2.setScore(20);
        assertEquals(emperor.judgement(), player2);
    }
    @Test
    void Equality() {
        player1.setScore(20);
        player2.setScore(20);
        assertEquals( null,emperor.judgement());
    }
}
