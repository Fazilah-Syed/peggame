package peggame;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Testing the readingFile() and setFileName() from the Board class
 */
public class BoardTest {
    @Test
    public void testReadingFile() {
        Board board = new Board(); 
        board.setFilename("C:/Users/Fazila Syed/Documents/COLLEGE/RIT/COURSES/SEM2/GCIS124/Assignments/Assignment1/src/peggame/gamefile.txt"); 
        assertEquals(5, Board.readingFile());
    }

    @Test
    public void testSetFilename() {
        Board board = new Board(); 
        board.setFilename("C:/Users/Fazila Syed/Documents/COLLEGE/RIT/COURSES/SEM2/GCIS124/Assignments/Assignment1/src/peggame/gamefile.txt"); 
        assertEquals("C:/Users/Fazila Syed/Documents/COLLEGE/RIT/COURSES/SEM2/GCIS124/Assignments/Assignment1/src/peggame/gamefile.txt", Board.filename);
    }
}
