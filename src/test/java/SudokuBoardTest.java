import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {
    SudokuBoard board = new SudokuBoard();
    SudokuBoard board2 = new SudokuBoard();

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
     * SudokuBoard.fillBoard() test that checks if the board generated correctly
     */
    @Test
    void testFillBoard() {
        board.fillBoard();
        assertEquals(true, board.isValid());
    }

    /**
     * SudokuBoard test that checks if fillBoard() generates two different boards
     */
    @Test
    void testSudokuBoard() {
        board.fillBoard();
        board2.fillBoard();
        assertEquals(false, isSame(board, board2));
    }
}