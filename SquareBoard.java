package peggame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * PART 4
 * This class implements the PegGame interface
 * for a version of the game that is played on a square board
 */
public class SquareBoard implements PegGame {

    private int rows; // number of rows on the board
    private int columns; // number of columns on the board
    private int pegs; // number of pegs on the board = row * column
    private int holes; // number of holes on the board
    private Location[][] board; // a 2D array for each Peg's location

    private static final char PEGS = 'o'; // Pegs are denoted by 'o'
    private static final char HOLES = '-'; // Empty spaces/Holes are denoted by '-'

    /**
     * Constructor of the SquareBoard class
     * 
     * @param rows    number of rows on the board
     * @param columns number of columns on the board
     */
    public SquareBoard(int rows, int columns) {
        this.rows = rows; // assume 4 rows
        this.columns = columns; // assume 4 columns
        this.pegs = rows * columns; // then number of pegs = rows * columns ie 16 pegs
        this.holes = 0; // initially the number of empty spaces is 0
        this.board = new Location[rows][columns]; // a 2D array for each Peg's location

        // creating the board of size rows * columns and assigning a Location for each
        // peg
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                board[row][col] = new Location(row, col); // giving each peg a Location(int row, int col)
            }
        }
    }

    /**
     * This method verfies if the chosen peg from inital Location(r1,c1) can move to
     * its final Location(r2,c2)
     * 
     * @param r1 row value of the peg's initial location
     * @param c1 column value of the peg's initial location
     * @param r2 row value of the peg's final location
     * @param c2 column value of the peg's final location
     * @return a boolean value
     */
    private boolean canJump(int r1, int c1, int r2, int c2) {
        if (r2 >= 0 && r2 < rows && c2 >= 0 && c2 < columns) {
            // if the given values for r2 and c2 fit within the range of the board
            Location jumpOver = new Location(r1 + (r2 - r1) / 2, c1 - (c1 - c2) / 2);
            if (board[r1][c1] != null && board[r2][c2] != board[r1][c1] && board[r2][c2] == null
                    && board[jumpOver.getRow()][jumpOver.getColumn()] != null) {
                // check if (r1,c1) has a peg in the first place that can be jumped
                // AND (r2,c2) should not be equal to (r1,c1)
                // AND if (r2,c2) is empty
                return true;
            }
        }
        return false; // if there is no peg in (r1,c1) or if there is already a peg at (r2,c2)
    }

    /**
     * This method verifies if a move is valid or not
     * 
     * @param moveFrom the initial Location of the peg ie (r1,c1)
     * @return a boolean value
     */
    private boolean canMakeMove(Location moveFrom, Location moveTo) {

        // if there are less than 2 pegs on the board, then a move cannot be made
        if (pegs < 2) {
            return false;
        }

        // keeps count of the number of moves possible
        int numMoves = 0;

        // checks Row-wise ie r1 == r2
        if (moveFrom.getRow() == moveTo.getRow()) {
            if (canJump(moveFrom.getRow(), moveFrom.getColumn(), moveTo.getRow(), moveTo.getColumn())) {
                numMoves++; // increments if it can make a move across the row
            }
        }
        // checks column-wise ie c1 == c2
        if (moveFrom.getColumn() == moveTo.getColumn()) {
            if (canJump(moveFrom.getRow(), moveFrom.getColumn(), moveTo.getRow(), moveTo.getColumn())) {
                numMoves++; // increments if it can make a move along the column
            }
        }

        return numMoves > 0; // if it is greater than 0, ie there are moves which can be made --> returns
                             // true
    }

    /**
     * This method is used to get a Collection of possible moves that the user can
     * make using makeMove()
     * 
     * @return a Collection of all possible Moves
     */
    @Override
    public Collection<Move> getPossibleMoves() {

        List<Move> moves = new ArrayList<>(); // creates a list for the possible moves

        // iterates through the whole board to check for possibilities
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {

                Location location = board[row][col];
                if (location != null) {
                    // Check for possible moves in all four directions
                    if (canJump(row, col, row - 2, col)) {
                        moves.add(new Move(location, new Location(row - 2, col))); // TOP
                    }
                    if (canJump(row, col, row + 2, col)) {
                        moves.add(new Move(location, new Location(row + 2, col))); // BOTTOM
                    }
                    if (canJump(row, col, row, col + 2)) {
                        moves.add(new Move(location, new Location(row, col + 2))); // RIGHT
                    }
                    if (canJump(row, col, row, col - 2)) {
                        moves.add(new Move(location, new Location(row, col - 2))); // LEFT
                    }

                }

            }
        }

        return moves;
    }

    /**
     * Note: The board is assumed to be initially filled with all pegs
     * This method makes the first move, which allows the user to remove one peg
     * from any Location to start the game
     * 
     * @param location the peg's Location which will be removed first eg:
     *                 board[0][0]
     */
    @Override
    public void firstMove(Location location) {
        System.out.println("First move: ");
        // the specified location is assigned null, so when the toString() is called,
        // the values with null are filled with HOLES ie '-'
        board[location.getRow()][location.getColumn()] = null;
        pegs--; // decrementing the number of pegs
        holes++; // incrementing the number of empty spaces
    }

    /**
     * This method attempts to make a move on the board ie jumping over a peg from
     * one Location to another Location
     * 
     * @param move
     */
    @Override
    public void makeMove(Move move) throws PegGameException {
        Location from = move.getFrom(); // gets the Location of the From coordinates ie (r1,c1)
        Location to = move.getTo(); // gets the Location of the To coordinates ie (r2,c2)

        // if the From OR To Locations are empty OR if you cannot make a move OR you
        // cannot jump a peg
        if (from == null || board[to.getRow()][to.getColumn()] != null || !canMakeMove(from, to)
                || !canJump(from.getRow(), from.getColumn(), to.getRow(), to.getColumn())) {
            throw new PegGameException("Invalid Move!"); // throws an Exception for an Invalid move.
        } else if (from != null && board[to.getRow()][to.getColumn()] == null
                && canJump(from.getRow(), from.getColumn(), to.getRow(), to.getColumn())) {
            // if From location is not empty AND the To location is not empty AND you can
            // jump a peg
            int row = from.getRow(); // r1 value
            int col = from.getColumn(); // c1 value

            Location jumpOver = new Location(row + (to.getRow() - row) / 2, col - (col - to.getColumn()) / 2); // The
                                                                                                               // location
                                                                                                               // where
                                                                                                               // it
                                                                                                               // is
                                                                                                               // being
                                                                                                               // jumped
                                                                                                               // over

            if (board[jumpOver.getRow()][jumpOver.getColumn()] != null && board[to.getRow()][to.getColumn()] == null) {
                // since the peg can only jump over another peg, we have the above condition
                board[to.getRow()][to.getColumn()] = to; // the To coordinates are on the new Location ie (r2,c2)
                board[row][col] = null; // the From coordinates, ie the peg being removed will become empty after
                                        // removal
                board[jumpOver.getRow()][jumpOver.getColumn()] = null; // removing the peg over which From peg is being
                // moved to
                // To peg
            } else if (board[jumpOver.getRow()][jumpOver.getColumn()] == null
                    && (board[to.getRow()][to.getColumn()] == null || board[to.getRow()][to.getColumn()] != null)) {
                throw new PegGameException("Invalid Move!"); // throws an Exception for an Invalid move.
            }
            pegs--; // decrement the number of pegs
            holes++; // incrementing the number of empty spaces
        }
    }

    /**
     * This method returns the enum GameState based on the current state of the Game
     */
    @Override
    public GameState getGameState() {
        if (pegs == 1 && holes == (rows * columns) - 1) {
            return GameState.WON; // if only one peg remains, then the you Win the game
        } else if (pegs == rows * columns) {
            return GameState.NOT_STARTED; // if the same number of pegs as the initial number are found, then the game
                                          // hasn't started
        } else if (pegs > 1 && getPossibleMoves().isEmpty()) {
            return GameState.STALEMATE; // if there is more than 1 peg remaining and no possible moves remain, then
                                        // the game ends in stalemate
        } else {
            return GameState.IN_PROGRESS; // if none of the conditions apply, then the game is in progress
        }
    }

    /**
     * This method is used to return the board as a String
     * It will print the new layout of the board after each move has been played
     */
    @Override
    public String toString() {
        // StringBuilder method makes a String mutable, allowing us to append, insert
        // characters, etc...
        StringBuilder str = new StringBuilder();
        // iterating over the board based on the number of rows and columns specified
        // when the board was created
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                Location location = board[row][col]; // value of each Loction on the board
                if (location == null) {
                    str.append(HOLES); // if the location is null, then it is filled with HOLES ie '-'
                } else {
                    str.append(PEGS); // if the location is not null, then it is filled with PEGS ie 'o'
                }
                str.append(" "); // space between each consequent PEG/HOLE
            }
            str.append("\n"); // newline after each row
        }
        return str.toString(); // converting into a String before returning
    }

    /**
     * Main method for testing the code manually
     * 
     * @param args
     * @throws PegGameException
     */
    public static void main(String[] args) throws PegGameException {
        SquareBoard game = new SquareBoard(4, 4);
        System.out.println(game);
        System.out.println(game.getPossibleMoves());
        System.out.println(game.getGameState());

        game.firstMove(new Location(0, 0));
        System.out.println(game);
        System.out.println(game.getPossibleMoves());
        System.out.println(game.getGameState());

        System.out.println(game.canJump(0, 2, 0, 0));
        System.out.println(game.canMakeMove(new Location(0, 2), new Location(0, 0)));
        System.out.println(game.getPossibleMoves());

        game.makeMove(new Move(new Location(0, 2), new Location(0, 0)));
        System.out.println(game);
        System.out.println(game.getPossibleMoves());
        System.out.println(game.getGameState());

    }

}
