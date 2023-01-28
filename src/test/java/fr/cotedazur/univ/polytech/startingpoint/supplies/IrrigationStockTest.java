package fr.cotedazur.univ.polytech.startingpoint.supplies;

import fr.cotedazur.univ.polytech.startingpoint.Game;
import fr.cotedazur.univ.polytech.startingpoint.Player;
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
        assertEquals(unSed.size(),26);
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
        assertEquals(uSed.size(),1);
    }

    @Test
    void getAllHexplotFrom1() {
        Set<HexPlot> set =irrigationStock.getAllHexplotFrom();
        assertEquals(set.size(),1);

    }

    @Test
    void getAllHexplotFrom2() {
        List<IrrigationCanal> unSed= irrigationStock.getUnUsedCanal();
        assertEquals(unSed.size(),26);
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
        System.out.println(uSed);
        assertEquals(uSed.size(),1);
        Set<HexPlot> set =irrigationStock.getAllHexplotFrom();
        assertEquals(set.size(),2);

    }

    @Test
    void add() {
        List<IrrigationCanal> unSed= irrigationStock.getUnUsedCanal();
        assertEquals(unSed.size(),26);
        HexPlot hex =new HexPlot(0,1,-1,YELLOW);
        hex.getBamboos().add(new Bamboo(YELLOW));
        hex.getBamboos().add(new Bamboo(YELLOW));
        hex.getBamboos().add(new Bamboo(YELLOW));
        hex.getBamboos().add(new Bamboo(YELLOW));
        board.add(hex);
        Optional<IrrigationCanal> canal= irrigationStock.getOneUnused();
        System.out.println(canal);
        irrigationStock.add(canal.get(),new HexPlot(),hex,board);
        System.out.println(canal);

        Set<HexPlot> set =irrigationStock.getAllHexplotFrom();
        assertTrue(hex.isIrrigated());
        assertTrue(set.contains(hex));
         unSed= irrigationStock.getUnUsedCanal();
        List<IrrigationCanal> uSed= irrigationStock.getUsedCanal();
        assertEquals(uSed.size(),1);
        assertTrue(uSed.get(0).getCanalId()!=0);
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
        assertEquals(unSed.size(),26);
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
        System.out.println("RIIIIIIIIIIIIIIIIIIIIIIEEEEEEEEEENG");
        Optional<IrrigationCanal> canal= irrigationStock.getOneUnused();
        System.out.println("-----------------------------------------------");
        irrigationStock.add(canal.get(),new HexPlot(),hex4,board);
        unSed= irrigationStock.getUnUsedCanal();
        List<IrrigationCanal> uSed= irrigationStock.getUsedCanal();
        assertEquals(uSed.size(),3);
        assertFalse(hex4.isIrrigated());
        assertEquals(hex4.getBamboos().size(),3);
    }


    @Test
    void initAdd() {
        List<IrrigationCanal> unSed= irrigationStock.getUnUsedCanal();
        assertEquals(unSed.size(),26);
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
        assertEquals(uSed.size(),0);
        board.add(hex);
        board.add(hex2);
        irrigationStock.primordialCanal(board);
        uSed= irrigationStock.getUsedCanal();
        assertEquals(uSed.size(),2);
    }

    @Test
    void primordialCanal() {
        List<IrrigationCanal> unSed= irrigationStock.getUnUsedCanal();
        assertEquals(unSed.size(),26);
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
        assertEquals(uSed.size(),0);
        board.add(hex);
        board.add(hex2);
        board.add(hex3);
        board.add(hex4);
        irrigationStock.primordialCanal(board);
        uSed= irrigationStock.getUsedCanal();
        assertEquals(uSed.size(),3);
    }
    @Test
    void exist() {
        List<IrrigationCanal> unSed= irrigationStock.getUnUsedCanal();
        assertEquals(unSed.size(),26);
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
        assertEquals(uSed.size(),0);
        board.add(hex);
        board.add(hex2);
        board.add(hex3);
        board.add(hex4);
        irrigationStock.primordialCanal(board);
        uSed= irrigationStock.getUsedCanal();
        assertEquals(uSed.size(),3);
        assertTrue(irrigationStock.exist(new IrrigationCanal(new HexPlot(),new HexPlot(0,-1,1))));
    }
    @Test
    void test3() {
        List<IrrigationCanal> unSed= irrigationStock.getUnUsedCanal();
        assertEquals(unSed.size(),26);
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
        System.out.println("RIIIIIIIIIIIIIIIIIIIIIIEEEEEEEEEENG");
        Optional<IrrigationCanal> canal= irrigationStock.getOneUnused();
        System.out.println("-----------------------------------------------");
        irrigationStock.add(canal.get(),hex2,hex4,board);
        unSed= irrigationStock.getUnUsedCanal();
        List<IrrigationCanal> uSed= irrigationStock.getUsedCanal();
        assertEquals(uSed.size(),3);
        assertFalse(hex4.isIrrigated());
        assertEquals(hex4.getBamboos().size(),3);
    }
    @Test
    void test4() {
        List<IrrigationCanal> unSed= irrigationStock.getUnUsedCanal();
        assertEquals(unSed.size(),26);
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
        System.out.println("RIIIIIIIIIIIIIIIIIIIIIIEEEEEEEEEENG");
        Optional<IrrigationCanal> canal= irrigationStock.getOneUnused();
        System.out.println("-----------------------------------------------");
        irrigationStock.add(canal.get(),hex3,hex4,board);
        unSed= irrigationStock.getUnUsedCanal();
        List<IrrigationCanal> uSed= irrigationStock.getUsedCanal();
        assertEquals(uSed.size(),3);
        assertFalse(hex4.isIrrigated());
        assertEquals(hex4.getBamboos().size(),3);
    }
    @Test
    void test5() {
        List<IrrigationCanal> unSed= irrigationStock.getUnUsedCanal();
        assertEquals(unSed.size(),26);
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
        System.out.println("RIIIIIIIIIIIIIIIIIIIIIIEEEEEEEEEENG");
        Optional<IrrigationCanal> canal= irrigationStock.getOneUnused();
        System.out.println("-----------------------------------------------");
        irrigationStock.add(canal.get(),hex2,hex3,board);
        unSed= irrigationStock.getUnUsedCanal();
        List<IrrigationCanal> uSed= irrigationStock.getUsedCanal();
        System.out.println("-----------------------------------------------");
        System.out.println("---------------cheeeeeeeeeeem-----------------"+uSed+"\n**********");
        assertEquals(uSed.size(),4);
        assertTrue(hex3.isIrrigated());
        assertEquals(hex3.getBamboos().size(),2);
        assertFalse(hex4.isIrrigated());
        assertEquals(hex4.getBamboos().size(),3);
        irrigationStock.add(canal.get(),hex3,hex4,board);
        unSed= irrigationStock.getUnUsedCanal();
        uSed= irrigationStock.getUsedCanal();
        assertEquals(uSed.size(),5);
        System.out.println(uSed);
        assertTrue(hex4.isIrrigated());
        assertEquals(hex4.getBamboos().size(),4);
    }
}