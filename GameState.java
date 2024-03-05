package peggame;

public enum GameState {
    NOT_STARTED, // returned if no moves have been made yet
    IN_PROGRESS, // returned if atleast one move has been made on ie in progress and game not over
    STALEMATE, // returned if 2 or more pegs remain and no more invalid moves
    WON; // returned when only 1 peg is remaining ie Victory
}
