import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import pl.comp.model.BacktrackingSudokuSolver;
import pl.comp.model.SudokuBoard;
import pl.comp.model.SudokuSolver;

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

    @Test
    void setterTest() {
        board.set(0, 0, 5);
        assertEquals(5, board.get(0, 0));
    }

    @Test
    void testSolveGame() {
        board.solveGame();
        assertTrue(board.checkBoard());
    }

    @Test
    void checkBoard_true_Test() {
        SudokuBoard board0 = new SudokuBoard(solver0);
        board0.solveGame();
        assertTrue(board0.checkBoard());
    }

    @Test
    void checkBoard_SameRow_Test() {
        SudokuBoard board0 = new SudokuBoard(solver0);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board0.set(i, j, i+1);
            }
        }
        assertFalse(board0.checkBoard());
    }

    @Test
    void checkBoard_SameCol_Test() {
        SudokuBoard board0 = new SudokuBoard(solver0);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board0.set(i, j, j+1);
            }
        }
        assertFalse(board0.checkBoard());
    }

    @Test
    void checkBoard_SameBlock_Test() {
        SudokuBoard board0 = new SudokuBoard(solver0);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board0.set(i, j, (i + j) % 9 + 1);
            }
        }
        assertFalse(board0.checkBoard());
    }

    @Test
    void checkBoard_OutOfRange_Test() {
        SudokuBoard board0 = new SudokuBoard(solver0);
        board0.solveGame();
        board0.set(0, 0, 10);
        assertFalse(board0.checkBoard());
        board0.set(0, 0, -1);
        assertFalse(board0.checkBoard());
    }

    @Test
    void toStringTest() {
        SudokuBoard board0 = new SudokuBoard(solver0);
        board0.solveGame();
        assertNotEquals("", board0.toString());
    }

    @Test
    void equalsTest() {
        board.solveGame();
        board2.solveGame();
        assertTrue(board.equals(board));
        assertFalse(board.equals(board2));
        assertFalse(board.equals(null));
        assertFalse(board.equals(2));
    }

    @Test
    void hashCodeTest() {
        board.solveGame();
        board2.solveGame();

        assertNotEquals(board.hashCode(), board2.hashCode());
    }

    @Test
    void cloneTest() {
        SudokuBoard clone = board.clone();

        assertEquals(board, clone);
    }
}