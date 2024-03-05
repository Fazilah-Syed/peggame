package peggame;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Testing the GameState enum
 */
public class GameStateTest {
    @Test
    public void testToString() {
        assertEquals("NOT_STARTED", GameState.NOT_STARTED.toString()); 
        assertEquals("IN_PROGRESS", GameState.IN_PROGRESS.toString()); 
        assertEquals("STALEMATE", GameState.STALEMATE.toString()); 
        assertEquals("WON", GameState.WON.toString());
    }
}
