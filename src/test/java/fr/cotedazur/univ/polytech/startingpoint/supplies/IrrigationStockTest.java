package fr.cotedazur.univ.polytech.startingpoint.supplies;

import fr.cotedazur.univ.polytech.startingpoint.tools.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static fr.cotedazur.univ.polytech.startingpoint.tools.Color.YELLOW;
import static org.junit.jupiter.api.Assertions.*;

class IrrigationStockTest {
    IrrigationStock irrigationStock;
    Board board;

    BambooStock bamboos;

    @BeforeEach
    void setUp() {
        irrigationStock=new IrrigationStock();
        board=new Board();
        bamboos= new BambooStock();
    }

    @Test
    void test1() {
        List<IrrigationCanal> unSed= irrigationStock.getUnUsedCanal();
        assertEquals(unSed.size(),20);
        HexPlot hex =new HexPlot(0,1,-1,YELLOW);
        hex.getBamboos().add(new Bamboo(YELLOW));
        hex.getBamboos().add(new Bamboo(YELLOW));
        hex.getBamboos().add(new Bamboo(YELLOW));
        hex.getBamboos().add(new Bamboo(YELLOW));
        board.add(hex);
        Optional<IrrigationCanal> canal= irrigationStock.getUnUsed();
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
        assertEquals(unSed.size(),20);
        HexPlot hex =new HexPlot(0,1,-1,YELLOW);
        hex.getBamboos().add(new Bamboo(YELLOW));
        hex.getBamboos().add(new Bamboo(YELLOW));
        hex.getBamboos().add(new Bamboo(YELLOW));
        hex.getBamboos().add(new Bamboo(YELLOW));
        board.add(hex);
        Optional<IrrigationCanal> canal= irrigationStock.getUnUsed();
        irrigationStock.add(canal.get(),new HexPlot(),hex,board);
        unSed= irrigationStock.getUnUsedCanal();
        List<IrrigationCanal> uSed= irrigationStock.getUsedCanal();
        assertEquals(uSed.size(),1);
        Set<HexPlot> set =irrigationStock.getAllHexplotFrom();
        assertEquals(set.size(),2);

    }

    @Test
    void add() {
        List<IrrigationCanal> unSed= irrigationStock.getUnUsedCanal();
        assertEquals(unSed.size(),20);
        HexPlot hex =new HexPlot(0,1,-1,YELLOW);
        hex.getBamboos().add(new Bamboo(YELLOW));
        hex.getBamboos().add(new Bamboo(YELLOW));
        hex.getBamboos().add(new Bamboo(YELLOW));
        hex.getBamboos().add(new Bamboo(YELLOW));
        board.add(hex);
        Optional<IrrigationCanal> canal= irrigationStock.getUnUsed();
        System.out.println(canal);
        irrigationStock.add(canal.get(),new HexPlot(),hex,board);
        System.out.println(canal);

        Set<HexPlot> set =irrigationStock.getAllHexplotFrom();
        assertTrue(hex.isIrrigated());
        assertTrue(set.contains(hex));
         unSed= irrigationStock.getUnUsedCanal();
        List<IrrigationCanal> uSed= irrigationStock.getUsedCanal();
        assertEquals(uSed.size(),1);
        System.out.println(canal.get().getCanalId());
        System.out.println(uSed.get(0).getCanalId());
        assertTrue(uSed.get(0).equals(canal.get()));
        assertFalse(unSed.contains(canal));
    }

    @Test
    void getUnUsed() {
        Optional<IrrigationCanal> canal = irrigationStock.getUnUsed();
        assertTrue(canal.isPresent());
    }


}