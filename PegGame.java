package peggame;

import java.util.Collection;

/**
 * An interface which is to be implemented for any version of the Peg Game
 * 
 */
public interface PegGame {
    public Collection<Move> getPossibleMoves(); // returns a Collection of all valid moves a player can make except the invalid moves
    public GameState getGameState(); // returns the current state of the game
    public void makeMove(Move move) throws PegGameException; // this method is used to move the peg; throws an Exception message if an Invalid move is made
    public void firstMove(Location location); // this method is used to remove the first peg from the board, following which the rest of the game can be played
}
