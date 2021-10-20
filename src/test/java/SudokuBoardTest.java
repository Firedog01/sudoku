import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {
    SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
    SudokuBoard board2 = new SudokuBoard(new BacktrackingSudokuSolver());

    /**
     * Function check if two SudokuBoards are the same.
     */
    public boolean isSame(SudokuBoard board, SudokuBoard board2) {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(board.get(i, j) != board2.get(i, j)) {
                    return false;
                }
            }
        }
        return true;
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
        assertTrue(isSame(board, board2));
    }
}