package peggame;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Testing the methods from the Location class
 */
public class LocationTest {
    @Test
    public void testToString() {
        Location location = new Location(1, 2);

        assertEquals("1,2", location.toString());
    }

    @Test
    public void testGetRow() {
        Location location = new Location(3, 4);
        assertEquals(3, location.getRow());
    }

    @Test
    public void testGetCol() {
        Location location = new Location(3, 4);
        assertEquals(4, location.getColumn());
    }
}
