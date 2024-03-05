package peggame;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Testing the methods from the Move class
 */
public class MoveTest {
    @Test
    public void testGetFrom() {
        Location from = new Location(3, 5);
        Location to = new Location(1, 2); 
        Move move = new Move(from, to); 
        assertEquals(from, move.getFrom());
    }

    @Test
    public void testGetTo() {
        Location from = new Location(3, 5);
        Location to = new Location(1, 2); 
        Move move = new Move(from, to); 
        assertEquals(to, move.getTo());
    }

    @Test public void testToString() { 
        Location from = new Location(2, 3); 
        Location to = new Location(5, 6); 
        Move move = new Move(from, to); 
        assertEquals("(2,3,5,6)", move.toString()); 
    } 
}

