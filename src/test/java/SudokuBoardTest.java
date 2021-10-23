import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {
    private SudokuSolver solver0 = new BacktrackingSudokuSolver();
    private SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
    private SudokuBoard board2 = new SudokuBoard(new BacktrackingSudokuSolver());

    /**
     * checks if board is initialised with zeros
     */
    @Test
    void constructorTest() {
        SudokuBoard board0 = new SudokuBoard(solver0);
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                assertEquals(0, board0.get(i, j));
            }
        }
    }

    /**
     * Basic setter test
     */
    @Test
    void setterTest() {
        board.set(0, 0, 5);
        assertEquals(5, board.get(0, 0));
    }


    /**
     * SudokuBoard.solveGame() test that checks if the board generated correctly
     */
    @Test
    void testsolveGame() {
        board.solveGame();
        assertTrue(board.isValid());
    }

    /**
     * isValid method check, default for correctly generated board
     */
    @Test
    void isValid_true_Test() {
        SudokuBoard board0 = new SudokuBoard(solver0);
        board0.solveGame();
        assertTrue(board0.isValid());
    }

    /**
     * checks if board loses its validity on same values in a row
     */
    @Test
    void isValid_SameRow_Test() {
        SudokuBoard board0 = new SudokuBoard(solver0);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board0.set(i, j, i+1);
            }
        }
        assertFalse(board0.isValid());
    }

    /**
     * checks if board loses its validity on same values in a column
     */
    @Test
    void isValid_SameCol_Test() {
        SudokuBoard board0 = new SudokuBoard(solver0);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board0.set(i, j, j+1);
            }
        }
        assertFalse(board0.isValid());
    }

    /**
     * checks if board loses its validity on same values in a block
     */
    @Test
    void isValid_SameBlock_Test() {
        SudokuBoard board0 = new SudokuBoard(solver0);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board0.set(i, j, (i + j) % 9 + 1);
            }
        }
        assertFalse(board0.isValid());
    }

    /**
     * checks if board loses its validity on inserting number
     * greater than 9 or lesser than 1
     */
    @Test
    void isValid_OutOfRange_Test() {
        SudokuBoard board0 = new SudokuBoard(solver0);
        board0.solveGame();
        board0.set(0, 0, 10);
        assertFalse(board0.isValid());
        board0.set(0, 0, -1);
        assertFalse(board0.isValid());
    }

    /**
     * basic test if toString returns something
     */
    @Test
    void toStringTest() {
        SudokuBoard board0 = new SudokuBoard(solver0);
        board0.solveGame();
        assertFalse(Objects.equals(board0.toString(), ""));
    }

    /**
     * checks if equals function works correctly.
     * Also makes sure that two boards have different values in them
     */
    @Test
    void equalsTest() {
        board.solveGame();
        board2.solveGame();
        assertTrue(board.equals(board));
        assertFalse(board.equals(board2));
    }
}