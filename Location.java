package peggame;

/**
 * This class creates a row and column location on the game board
 */
public class Location {
    private int row; // a single row in the board
    private int col; // a single column in the board

    /**
     * Constructor the Location class
     * 
     * @param row a single row in the board
     * @param col a single column in the board
     */
    public Location(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Accessor - gets the row
     * 
     * @return a single row
     */
    public int getRow() {
        return row;
    }

    /**
     * Accessor - gets the column
     * 
     * @return a single column
     */
    public int getColumn() {
        return col;
    }

    /**
     * @return the row and column in String format row,col
     */
    @Override
    public String toString() {
        return row + "," + col;
    }

    /**
     * @return a boolean value; true if they are equal and false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof Location) {
            Location location = (Location) obj;
            return row == location.row && col == location.col;
        }
        return false;

    }
}
