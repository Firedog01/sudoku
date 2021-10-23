import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {
    private SudokuSolver solver0 = new BacktrackingSudokuSolver();
    private SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
    private SudokuBoard board2 = new SudokuBoard(new BacktrackingSudokuSolver());

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
     * SudokuBoard.solveGame() test that checks if the board generated correctly
     */
    @Test
    void testsolveGame() {
        board.solveGame();
        assertTrue(board.isValid());
    }

    /**
     * SudokuBoard test that checks if solveGame() generates two different boards
     */
    @Test
    void testSudokuBoard() {
        board.solveGame();
        board2.solveGame();
        assertFalse(board.equals(board2));
    }
}