package fr.cotedazur.univ.polytech.startingpoint.data;


import fr.cotedazur.univ.polytech.startingpoint.gameplay.Player;
import fr.cotedazur.univ.polytech.startingpoint.tools.Strategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BotStatTest {

    @BeforeEach
    public void setUp() {
    }
    @Test
    public void testGetName() {
        BotStat botStat = new BotStat("player2", Strategy.WITHOUTSTRATEGY, 100, 10,0, 20,5.0);
        assertEquals("player2", botStat.getBotName());
    }

    @Test
    public void testGetGamePlayed() {
        BotStat botStat = new BotStat("player2", Strategy.WITHOUTSTRATEGY, 100, 10,0, 20,5.0);
        assertEquals(100, botStat.getGamesPlayed());
    }

    @Test
    public void testGetWins() {
        BotStat botStat = new BotStat("player2", Strategy.WITHOUTSTRATEGY, 100, 10,0, 20,5.0);
        assertEquals(10, botStat.getWins());
    }

    @Test
    public void testGetLosses() {
        BotStat botStat = new BotStat("player2", Strategy.WITHOUTSTRATEGY, 100, 10,0, 90,5.0);
        assertEquals(90, botStat.getLosses());
    }

    @Test
    public void testToString() {
        BotStat botStat = new BotStat("player1", Strategy.WITHOUTSTRATEGY, 100, 10, 70,20,5.0);
        assertEquals("BotStat [name=player1, strategy=WITHOUTSTRATEGY, gamesPlayed=100, wins=10, ties=70, losses=20, points=5.0]", botStat.toString());
    }

    @Test
    public void testWin() {
        BotStat botStat = new BotStat("player1", Strategy.WITHOUTSTRATEGY, 100, 10, 70,20,5.0);
        botStat.win();
        assertEquals(11, botStat.getWins());
    }

    @Test
    public void testScore() {
        BotStat botStat = new BotStat("player1", Strategy.WITHOUTSTRATEGY, 100, 10, 70,20,5.0);
        botStat.score(10);
        assertEquals(15.0, botStat.getPoints());
    }

    @Test
    public void testGetTies() {
        BotStat botStat = new BotStat("player1", Strategy.WITHOUTSTRATEGY, 100, 10, 70,20,5.0);
        assertEquals(70, botStat.getTies());
    }

    @Test
    public void testPlayed() {
        BotStat botStat = new BotStat("player1", Strategy.WITHOUTSTRATEGY, 100, 10, 70,20,5.0);
        botStat.played();
        assertEquals(101, botStat.getGamesPlayed());
    }

    @Test
    public void testGetPoints() {
        BotStat botStat = new BotStat("player1", Strategy.WITHOUTSTRATEGY, 100, 10, 70,20,5.0);
        assertEquals(5.0, botStat.getPoints());
    }

    @Test
    public void testTie(){
        BotStat botStat = new BotStat("player1", Strategy.WITHOUTSTRATEGY, 100, 10, 70,20,5.0);
        botStat.tie();
        assertEquals(71, botStat.getTies());
    }

    @Test
    public void testUpdateAverageScore(){
        BotStat botStat = new BotStat("player1", Strategy.WITHOUTSTRATEGY, 9, 9, 0,0,90.0);
        botStat.score(10);
        botStat.played();
        assertEquals(10.0, botStat.updateAverageScore());

    }
}
