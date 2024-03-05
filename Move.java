package peggame;

/**
 * This class represnts a move made by a peg from one location to another
 * location.
 * This class does not store the location of the peg being jumped
 */
public class Move {
    private Location from; // the current position of the peg
    private Location to; // the position where the peg is moving to

    /**
     * Constructor of the Move class
     * 
     * @param from the current position of the peg
     * @param to   the position where the peg is moving to
     */
    public Move(Location from, Location to) {
        this.from = from;
        this.to = to;
    }

    /**
     * This method returns the location of the peg's current position
     * 
     * @return location of the peg initially
     */
    public Location getFrom() {
        return from;
    }

    /**
     * This method returns the location to where the peg is being moved
     * 
     * @return the new location of the peg
     */
    public Location getTo() {
        return to;
    }

    /**
     * @return the From and To Locations in String format (r1,c1,r2,c2)
     */
    @Override
    public String toString() {
        return "(" + this.from + "," + this.to + ")";
    }

    /**
     * @return a boolean value; true if they are equal and false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof Move) {
            Move move = (Move) obj;
            return from.equals(move.from) && to.equals(move.to);
        }
        return false;
    }
}
