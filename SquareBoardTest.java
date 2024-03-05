package peggame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Testing the getGameState() and getPossibleMoves() from the SquareBoard class
 */
public class SquareBoardTest {
    @Test
    public void testGetGameState() throws PegGameException {
        SquareBoard board = new SquareBoard(4, 4); 

        assertEquals(GameState.NOT_STARTED, board.getGameState()); 

        board.firstMove(new Location(0, 0)); 
        assertEquals(GameState.IN_PROGRESS, board.getGameState()); 
    }

    @Test
    public void testGetPossibleMoves() {
        SquareBoard board = new SquareBoard(4, 4); 

        board.firstMove(new Location(0, 0)); 

        List<Move> moves = new ArrayList<>(board.getPossibleMoves()); 

        assertEquals(2, moves.size()); // [(0,2,0,0),(2,0,0,0)]

        assertTrue(moves.contains(new Move(new Location(0, 2), new Location(0, 0))));
        assertTrue(moves.contains(new Move(new Location(2, 0), new Location(0, 0)))); 
  

        assertEquals(moves, board.getPossibleMoves());
    }
}
