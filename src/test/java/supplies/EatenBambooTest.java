package supplies;

import objectives.EatenBamboos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tools.Color;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static tools.Color.*;

public class EatenBambooTest {

    private EatenBamboos eatenBamboos;

    @BeforeEach
    public void init(){
        eatenBamboos = new EatenBamboos();
    }

    @Test
    public void count(){
        assertEquals(eatenBamboos.count(Color.GREEN), 0);
        assertEquals(eatenBamboos.count(Color.YELLOW), 0);
        assertEquals(eatenBamboos.count(Color.PINK), 0);
        eatenBamboos.add(new Bamboo(Color.YELLOW));
        assertEquals(eatenBamboos.count(Color.YELLOW), 1);
        assertEquals(eatenBamboos.count(Color.GREEN), 0);
        assertEquals(eatenBamboos.count(Color.PINK), 0);
    }
    
    @Test
    public void addMultiple(){
        eatenBamboos.addMultiple(2, YELLOW);
        assertEquals(eatenBamboos.size(), 2);
        eatenBamboos.addMultiple(6, PINK);
        assertEquals(eatenBamboos.size(), 8);
    }

    @Test
    public void remove(){
        eatenBamboos.addMultiple(2, YELLOW);
        eatenBamboos.remove(YELLOW);
        assertEquals(eatenBamboos.size(), 1);
        eatenBamboos.remove(PINK);
        assertEquals(eatenBamboos.size(), 1);
    }

    @Test
    public void removeTwoYellow() {
        eatenBamboos.removeTwoYellow();
        assertEquals(eatenBamboos.size(), 0);
        eatenBamboos.addMultiple(2, YELLOW);
        eatenBamboos.removeTwoYellow();
        assertEquals(eatenBamboos.size(), 0);
    }

    @Test
    public void removeThreeGreen() {
        eatenBamboos.addMultiple(3, GREEN);
        eatenBamboos.removeThreeGreen();
        assertEquals(eatenBamboos.size(), 0);
    }

    @Test
    public void removeOneOfEach() {
        eatenBamboos.addMultiple(2, YELLOW);
        eatenBamboos.addMultiple(2, GREEN);
        eatenBamboos.addMultiple(1, PINK);
        eatenBamboos.removeOneOfEach();
        assertEquals(eatenBamboos.size(), 2);
        assertEquals(eatenBamboos.count(YELLOW), 1);
        assertEquals(eatenBamboos.count(GREEN), 1);
        assertEquals(eatenBamboos.count(PINK), 0);
    }

}
