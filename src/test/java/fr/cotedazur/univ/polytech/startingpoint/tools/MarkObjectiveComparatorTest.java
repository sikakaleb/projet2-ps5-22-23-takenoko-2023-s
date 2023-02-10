package fr.cotedazur.univ.polytech.startingpoint.tools;

import fr.cotedazur.univ.polytech.startingpoint.objectives.MarkObjective;
import fr.cotedazur.univ.polytech.startingpoint.objectives.PlotObjective;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static fr.cotedazur.univ.polytech.startingpoint.tools.PlotObjectiveConfiguration.TRIANGULARSAMEPLOTS;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MarkObjectiveComparatorTest {
    private MarkObjectiveComparator comparator;
    private MarkObjective m1;
    private MarkObjective m2;
    @BeforeEach
    void setup(){
        comparator= new MarkObjectiveComparator();
        m1=new MarkObjective(new PlotObjective(5,TRIANGULARSAMEPLOTS));
        m2 =new MarkObjective(new PlotObjective(8,TRIANGULARSAMEPLOTS));
        m1.setMark(25);
        m2.setMark(22);

    }
    @Test
    void test1(){
        assertEquals(comparator.compare(m1,m2),1);
    }
    @Test
    void test2(){
        assertEquals(comparator.compare(m2,m1),-1);
    }
    @Test
    void test3(){
        assertEquals(comparator.compare(m2,m2),0);
    }

}