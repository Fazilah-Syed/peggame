package peggame;

import java.io.IOException;
import java.util.Scanner;

/**
 * PART 7
 * This class declares a main method where the game is played
 * 
 */
public class Project1Main {
    public static void main(String[] args) throws IOException, PegGameException {

        Scanner scan = new Scanner(System.in); // scan the input provided by the user 
        System.out.print("Enter the name of the file: "); // Note: enter the path of the gamefile.txt 
        // "C:\Users\Fazila Syed\Documents\COLLEGE\RIT\COURSES\SEM2\GCIS124\Assignments\Assignment1\src\peggame\gamefile.txt"
        String fileName = scan.nextLine(); // obtain the input and save it in the fileName variable

        Board gameBoard = new Board(); // creating a Board instance 
        gameBoard.setFilename(fileName); // setting the filename obtained

        PegGameMoves gameMoves = new PegGameMoves(); // creating an instance of PegGameMoves class 
        PegGame game = gameMoves.getBoard(); // calling the getBoard() on the gameMoves object and assigning it to the PegGame class instance - game
        PegGameMoves.play(game); // calling the play method by passing the game object as an argument

        scan.close(); // close the scanner
    }
}
