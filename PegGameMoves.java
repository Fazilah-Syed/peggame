package peggame;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * PART 6
 * This class allows the user to play the peg game using simple commands from
 * the command line interface. The moves are made accordingly.
 */
public class PegGameMoves {

    private SquareBoard board; // declaring an attribute board of type SquareBoard

    public SquareBoard getBoard() {
        // getter for board
        return board;
    }

    // Declaring a constructor for PegGameMoves class
    public PegGameMoves() {
        int rows = Board.readingFile(); // assigning the number of rows read from the file into variable rows
        int columns = Board.readingFile(); // assigning the number of columns read from the file as variable columns
        this.board = new SquareBoard(rows, columns); // intialising the game board
    }

    /**
     * This method allows the user to play the peg game .
     * It allows the player to make moves using commands until the game is finished.
     * @param game It is the PegGame objcet representing the game
     * @throws PegGameException Made to handle any error that occurs during the game
     */
    public static void play(PegGame game) throws PegGameException {
        Scanner scanner = new Scanner(System.in); // Scanner object to take the user input

        int calls = 0; //initialising an integer varible calls to 0, to track the number of moves 

        while (true) {
            System.out.println(game); // print the game board

            // if it is the first move
            if (calls == 0) {
                // prompting the user to enter the location of the first peg being taken out or quit
                System.out.print("Enter your first move (move r1 c1 / quit): ");
                String command = scanner.next(); // reads the user input

                //if user wishes to quit
                if (command.equals("quit")) {
                    System.out.println("Goodbye!"); // prints goodbye message
                    break; // comes out of the loop

                } else if (command.equals("move")) { // if the user enters "move"
                    int r1, c1; //declaring two ineter vraibles r1 and c1

                    try {
                        r1 = scanner.nextInt(); // reads the row coordinate
                        c1 = scanner.nextInt(); // reads the column coordinate
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid command format."); //prints error message if try block encounters an error
                        continue;
                    }

                    game.firstMove(new Location(r1, c1)); // executing the first move made by the user
                    System.out.println(game); // prints the board
                    System.out.println(game.getGameState()); // prints the game state
                    System.out.println(game.getPossibleMoves()); // prints the possible moves the user can make
                    calls = 1; // setting the value of calls as 1

                } else {
                    System.out.println("Invalid command.");
                    continue;
                }

            }
            // if calls is 1 ie after the firt move has been made
            if (calls == 1) {
                while (true) {

                    System.out.print("Enter command (move r1 c1 r2 c2 / quit): "); //prompting the user to enter the coordinate for the next move or quit

                    String command = scanner.next(); // reading the user input

                    //if user wishes to quit
                    if (command.equals("quit")) {
                        System.out.println("Goodbye!"); //prints goodbye message
                        break; //comes out of the loop

                    } else if (command.equals("move")) { //if user wishes to move the peg
                        int r1, c1, r2, c2; // declaring integer variables r1,c1,r2,c2
                        //r1,c1 is the current position of the peg
                        //r2,c2 is the location where the user wants to move the peg

                        try {
                            r1 = scanner.nextInt(); //reading the starting row coordinate
                            c1 = scanner.nextInt(); //reading the starting column coordinate
                            r2 = scanner.nextInt(); //reading the ending row coordinate
                            c2 = scanner.nextInt(); //reading the ending column coordinate
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid command format."); //prints error message if try block encounters an error
                            continue;
                        }

                        try {
                            game.makeMove(new Move(new Location(r1, c1), new Location(r2, c2))); // executing the move from r1,c1 to r2,c2
                            System.out.println(game); // print the game
                            System.out.println(game.getPossibleMoves()); // printing the possible moves available to the user
                            
                            GameState state = game.getGameState(); // declaring a variable called state which takes the GameState and stores its value
                            System.out.println(game.getGameState()); // printing the current state of the game

                            if (state == GameState.WON) { // if user wins the game
                                System.out.println("You Won the Game!"); //prints a message that the game has been won
                                break;
                            } else if (state == GameState.STALEMATE || (state != GameState.IN_PROGRESS && game.getPossibleMoves().isEmpty())) {
                                // if the current state of the game is Stalemate OR (the game is no longer in progress AND no more moves remain)
                                System.out.println("The Game has ended with no Moves remaining!"); // printing the message that the game has ended with no more moves remaining
                                break;
                            }

                        } catch (PegGameException e) {
                            System.out.println(e.getMessage()); //prints error message if try block encounters an error 
                        }

                    } else {
                        // if an invalid command is typed, then the below message is printed
                        System.out.println("Invalid command.");
                        continue;
                    }
                }
                break; // break out of the while loop
            }
        }

        scanner.close(); // close the scanner class
    }

    /**
     * Main method to test the methods manually
     * @param args
     * @throws PegGameException excpetion thrown by the PegGame class
     */
    public static void main(String[] args) throws PegGameException {
        PegGameMoves gameMoves = new PegGameMoves();
        PegGame game = gameMoves.getBoard();
        play(game);
    }
}