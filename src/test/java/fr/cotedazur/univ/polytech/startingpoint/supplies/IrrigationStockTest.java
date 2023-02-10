package fr.cotedazur.univ.polytech.startingpoint.supplies;

import fr.cotedazur.univ.polytech.startingpoint.gameplay.Game;
import fr.cotedazur.univ.polytech.startingpoint.gameplay.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static fr.cotedazur.univ.polytech.startingpoint.tools.Color.*;
import static org.junit.jupiter.api.Assertions.*;

class IrrigationStockTest {
    IrrigationStock irrigationStock;
    Board board;

    Game game;

    @BeforeEach
    void setUp() {
        irrigationStock=new IrrigationStock();
        board=new Board();
        game=new Game(new Player("p"),new Player("v"));
    }

    @Test
    void test1() {
        List<IrrigationCanal> unSed= irrigationStock.getUnUsedCanal();
        assertEquals(26,unSed.size());
        HexPlot hex =new HexPlot(0,1,-1,YELLOW);
        hex.getBamboos().add(new Bamboo(YELLOW));
        hex.getBamboos().add(new Bamboo(YELLOW));
        hex.getBamboos().add(new Bamboo(YELLOW));
        hex.getBamboos().add(new Bamboo(YELLOW));
        board.add(hex);
        Optional<IrrigationCanal> canal= irrigationStock.getOneUnused();
        irrigationStock.add(canal.get(),new HexPlot(),hex,board);
        unSed= irrigationStock.getUnUsedCanal();
        List<IrrigationCanal> uSed= irrigationStock.getUsedCanal();
        assertEquals(1,uSed.size());
    }

    @Test
    void getAllHexplotFrom1() {
        Set<HexPlot> set =irrigationStock.getAllHexplotFrom();
        assertEquals(1,set.size());

    }

    @Test
    void getAllHexplotFrom2() {
        List<IrrigationCanal> unSed= irrigationStock.getUnUsedCanal();
        assertEquals(26,unSed.size());
        HexPlot hex =new HexPlot(0,1,-1,YELLOW);
        hex.getBamboos().add(new Bamboo(YELLOW));
        hex.getBamboos().add(new Bamboo(YELLOW));
        hex.getBamboos().add(new Bamboo(YELLOW));
        hex.getBamboos().add(new Bamboo(YELLOW));
        board.add(hex);
        Optional<IrrigationCanal> canal= irrigationStock.getOneUnused();
        irrigationStock.add(canal.get(),new HexPlot(),hex,board);
        unSed= irrigationStock.getUnUsedCanal();
        List<IrrigationCanal> uSed= irrigationStock.getUsedCanal();
        assertEquals(1,uSed.size());
        Set<HexPlot> set =irrigationStock.getAllHexplotFrom();
        assertEquals(2,set.size());

    }

    @Test
    void add() {
        List<IrrigationCanal> unSed= irrigationStock.getUnUsedCanal();
        assertEquals(26,unSed.size());
        HexPlot hex =new HexPlot(0,1,-1,YELLOW);
        hex.getBamboos().add(new Bamboo(YELLOW));
        hex.getBamboos().add(new Bamboo(YELLOW));
        hex.getBamboos().add(new Bamboo(YELLOW));
        hex.getBamboos().add(new Bamboo(YELLOW));
        board.add(hex);
        Optional<IrrigationCanal> canal= irrigationStock.getOneUnused();
        irrigationStock.add(canal.get(),new HexPlot(),hex,board);

        Set<HexPlot> set =irrigationStock.getAllHexplotFrom();
        assertTrue(hex.isIrrigated());
        assertTrue(set.contains(hex));
         unSed= irrigationStock.getUnUsedCanal();
        List<IrrigationCanal> uSed= irrigationStock.getUsedCanal();
        assertEquals(1,uSed.size());
        assertNotEquals(0,uSed.get(0).getCanalId());
        assertFalse(unSed.contains(canal));
    }

    @Test
    void getUnUsed() {
        Optional<IrrigationCanal> canal = irrigationStock.getOneUnused();
        assertTrue(canal.isPresent());
    }
    @Test
    void test2() {
        List<IrrigationCanal> unSed= irrigationStock.getUnUsedCanal();
        assertEquals(26,unSed.size());
        HexPlot hex =new HexPlot(0,1,-1,YELLOW);
        HexPlot hex2 =new HexPlot(0,-1,1,PINK);
        hex2.getBamboos().add(new Bamboo(PINK));
        HexPlot hex3 =new HexPlot(1,-1,0,GREEN);
        hex3.getBamboos().add(new Bamboo(GREEN));
        HexPlot hex4 =new HexPlot(1,1,-2,GREEN);
        hex4.getBamboos().add(new Bamboo(GREEN));
        hex4.getBamboos().add(new Bamboo(GREEN));
        hex4.getBamboos().add(new Bamboo(GREEN));
        board.add(hex);
        board.add(hex2);
        board.add(hex3);
        board.add(hex4);
        Optional<IrrigationCanal> canal= irrigationStock.getOneUnused();
        irrigationStock.add(canal.get(),new HexPlot(),hex4,board);
        unSed= irrigationStock.getUnUsedCanal();
        List<IrrigationCanal> uSed= irrigationStock.getUsedCanal();
        assertEquals(3,uSed.size());
        assertFalse(hex4.isIrrigated());
        assertEquals(3,hex4.getBamboos().size());
    }


    @Test
    void initAdd() {
        List<IrrigationCanal> unSed= irrigationStock.getUnUsedCanal();
        assertEquals(26,unSed.size());
        HexPlot hex =new HexPlot(0,1,-1,YELLOW);
        HexPlot hex2 =new HexPlot(0,-1,1,PINK);
        hex2.getBamboos().add(new Bamboo(PINK));
        HexPlot hex3 =new HexPlot(1,-1,0,GREEN);
        hex3.getBamboos().add(new Bamboo(GREEN));
        HexPlot hex4 =new HexPlot(1,1,-2,GREEN);
        hex4.getBamboos().add(new Bamboo(GREEN));
        hex4.getBamboos().add(new Bamboo(GREEN));
        hex4.getBamboos().add(new Bamboo(GREEN));
        irrigationStock.primordialCanal(board);
        List<IrrigationCanal> uSed= irrigationStock.getUsedCanal();
        assertEquals(0,uSed.size());
        board.add(hex);
        board.add(hex2);
        irrigationStock.primordialCanal(board);
        uSed= irrigationStock.getUsedCanal();
        assertEquals(2,uSed.size());
    }

    @Test
    void primordialCanal() {
        List<IrrigationCanal> unSed= irrigationStock.getUnUsedCanal();
        assertEquals(26,unSed.size());
        HexPlot hex =new HexPlot(0,1,-1,YELLOW);
        HexPlot hex2 =new HexPlot(0,-1,1,PINK);
        hex2.getBamboos().add(new Bamboo(PINK));
        HexPlot hex3 =new HexPlot(1,-1,0,GREEN);
        hex3.getBamboos().add(new Bamboo(GREEN));
        HexPlot hex4 =new HexPlot(1,1,-2,GREEN);
        hex4.getBamboos().add(new Bamboo(GREEN));
        hex4.getBamboos().add(new Bamboo(GREEN));
        hex4.getBamboos().add(new Bamboo(GREEN));
        irrigationStock.primordialCanal(board);
        List<IrrigationCanal> uSed= irrigationStock.getUsedCanal();
        assertEquals(0,uSed.size());
        board.add(hex);
        board.add(hex2);
        board.add(hex3);
        board.add(hex4);
        irrigationStock.primordialCanal(board);
        uSed= irrigationStock.getUsedCanal();
        assertEquals(3,uSed.size());
    }
    @Test
    void exist() {
        List<IrrigationCanal> unSed= irrigationStock.getUnUsedCanal();
        assertEquals(26,unSed.size());
        HexPlot hex =new HexPlot(0,1,-1,YELLOW);
        HexPlot hex2 =new HexPlot(0,-1,1,PINK);
        hex2.getBamboos().add(new Bamboo(PINK));
        HexPlot hex3 =new HexPlot(1,-1,0,GREEN);
        hex3.getBamboos().add(new Bamboo(GREEN));
        HexPlot hex4 =new HexPlot(1,1,-2,GREEN);
        hex4.getBamboos().add(new Bamboo(GREEN));
        hex4.getBamboos().add(new Bamboo(GREEN));
        hex4.getBamboos().add(new Bamboo(GREEN));
        irrigationStock.primordialCanal(board);
        List<IrrigationCanal> uSed= irrigationStock.getUsedCanal();
        assertEquals(0,uSed.size());
        board.add(hex);
        board.add(hex2);
        board.add(hex3);
        board.add(hex4);
        irrigationStock.primordialCanal(board);
        uSed= irrigationStock.getUsedCanal();
        assertEquals(3,uSed.size());
        assertTrue(irrigationStock.exist(new IrrigationCanal(new HexPlot(),new HexPlot(0,-1,1))));
    }
    @Test
    void test3() {
        List<IrrigationCanal> unSed= irrigationStock.getUnUsedCanal();
        assertEquals(26,unSed.size());
        HexPlot hex =new HexPlot(0,1,-1,YELLOW);
        HexPlot hex2 =new HexPlot(0,-1,1,PINK);
        hex2.getBamboos().add(new Bamboo(PINK));
        HexPlot hex3 =new HexPlot(1,-1,0,GREEN);
        hex3.getBamboos().add(new Bamboo(GREEN));
        HexPlot hex4 =new HexPlot(1,1,-2,GREEN);
        hex4.getBamboos().add(new Bamboo(GREEN));
        hex4.getBamboos().add(new Bamboo(GREEN));
        hex4.getBamboos().add(new Bamboo(GREEN));
        board.add(hex);
        board.add(hex2);
        board.add(hex3);
        board.add(hex4);
        Optional<IrrigationCanal> canal= irrigationStock.getOneUnused();
        irrigationStock.add(canal.get(),hex2,hex4,board);
        unSed= irrigationStock.getUnUsedCanal();
        List<IrrigationCanal> uSed= irrigationStock.getUsedCanal();
        assertEquals(3,uSed.size());
        assertFalse(hex4.isIrrigated());
        assertEquals(3,hex4.getBamboos().size());
    }
    @Test
    void test4() {
        List<IrrigationCanal> unSed= irrigationStock.getUnUsedCanal();
        assertEquals(26,unSed.size());
        HexPlot hex =new HexPlot(0,1,-1,YELLOW);
        HexPlot hex2 =new HexPlot(0,-1,1,PINK);
        hex2.getBamboos().add(new Bamboo(PINK));
        HexPlot hex3 =new HexPlot(1,-1,0,GREEN);
        hex3.getBamboos().add(new Bamboo(GREEN));
        HexPlot hex4 =new HexPlot(1,1,-2,GREEN);
        hex4.getBamboos().add(new Bamboo(GREEN));
        hex4.getBamboos().add(new Bamboo(GREEN));
        hex4.getBamboos().add(new Bamboo(GREEN));
        board.add(hex);
        board.add(hex2);
        board.add(hex3);
        board.add(hex4);
        Optional<IrrigationCanal> canal= irrigationStock.getOneUnused();
        irrigationStock.add(canal.get(),hex3,hex4,board);
        unSed= irrigationStock.getUnUsedCanal();
        List<IrrigationCanal> uSed= irrigationStock.getUsedCanal();
        assertEquals(3,uSed.size());
        assertFalse(hex4.isIrrigated());
        assertEquals(3,hex4.getBamboos().size());
    }
    @Test
    void test5() {
        List<IrrigationCanal> unSed= irrigationStock.getUnUsedCanal();
        assertEquals(26,unSed.size());
        HexPlot hex =new HexPlot(0,1,-1,YELLOW);
        HexPlot hex2 =new HexPlot(0,-1,1,PINK);
        hex2.getBamboos().add(new Bamboo(PINK));
        HexPlot hex3 =new HexPlot(1,-1,0,GREEN);
        hex3.getBamboos().add(new Bamboo(GREEN));
        HexPlot hex4 =new HexPlot(1,-2,1,GREEN);
        hex4.getBamboos().add(new Bamboo(GREEN));
        hex4.getBamboos().add(new Bamboo(GREEN));
        hex4.getBamboos().add(new Bamboo(GREEN));
        board.add(hex);
        board.add(hex2);
        board.add(hex3);
        board.add(hex4);
        Optional<IrrigationCanal> canal= irrigationStock.getOneUnused();
        irrigationStock.add(canal.get(),hex2,hex3,board);
        unSed= irrigationStock.getUnUsedCanal();
        List<IrrigationCanal> uSed= irrigationStock.getUsedCanal();
        assertEquals(4,uSed.size());
        assertTrue(hex3.isIrrigated());
        assertEquals(2,hex3.getBamboos().size());
        assertFalse(hex4.isIrrigated());
        assertEquals(3,hex4.getBamboos().size());
        irrigationStock.add(canal.get(),hex3,hex4,board);
        unSed= irrigationStock.getUnUsedCanal();
        uSed= irrigationStock.getUsedCanal();
        assertEquals(5,uSed.size());
        assertTrue(hex4.isIrrigated());
        assertEquals(4,hex4.getBamboos().size());
    }
}