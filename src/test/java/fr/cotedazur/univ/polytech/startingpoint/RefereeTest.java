package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RefereeTest {

    Player player1;
    Player player2;
    Referee referee;

    @BeforeEach
    void setUp() {
        player1 = new Player("Ted");
        player2 = new Player("Wilfried");
        referee = new Referee(new Game(player1, player2));
    }

    @Test
    void judgementTest() {
        player1.setScore(10);
        player2.setScore(20);
        assertEquals(referee.judgement(), player2);
    }
}
