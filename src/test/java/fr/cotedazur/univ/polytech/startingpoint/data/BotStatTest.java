package fr.cotedazur.univ.polytech.startingpoint.data;


import fr.cotedazur.univ.polytech.startingpoint.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BotStatTest {
    Player player1;
    Player player2;
    @BeforeEach
    public void setUp() {
        player1 = new Player("Bot 1");
        player2 = new Player("Bot 2");
    }
    @Test
    public void testGetName() {
        BotStat botStat = new BotStat(player1, 100, 10, 20);
        assertEquals("Bot 1", botStat.getBotName());
    }

    @Test
    public void testGetGamePlayed() {
        BotStat botStat = new BotStat(player2, 100, 10, 20);
        assertEquals(100, botStat.getGamesPlayed());
    }

    @Test
    public void testGetWins() {
        BotStat botStat = new BotStat(player1, 100, 10, 20);
        assertEquals(10, botStat.getWins());
    }

    @Test
    public void testGetLosses() {
        BotStat botStat = new BotStat(player2, 100, 10, 20);
        assertEquals(20, botStat.getLosses());
    }

    @Test
    public void testToString() {
        BotStat botStat = new BotStat(player1, 100, 10, 20);
        System.out.println(botStat);
        assertEquals("BotStat [name=Bot 1, gamesPlayed=100, wins=10, losses=20]", botStat.toString());
    }
}
