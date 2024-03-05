package peggame;

import java.lang.Exception;

/**
 * This class throws an Exception when an invalid move is attempted
 * 
 */
public class PegGameException extends Exception{

    public PegGameException(String message) {
        super(message); // calling the constructor of the Exception class to print the Error message
    }
    
}
