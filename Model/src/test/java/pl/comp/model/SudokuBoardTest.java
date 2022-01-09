package pl.comp.model;

import static org.junit.jupiter.api.Assertions.*;
import static pl.comp.model.Difficulty.*;

import org.junit.jupiter.api.Test;
import pl.comp.exceptions.OutOfRangeCoordsException;
import pl.comp.exceptions.SudokuCloneException;
import pl.comp.exceptions.UnfilledBoardException;
import pl.comp.model.*;

import java.util.List;
import java.util.Set;

class SudokuBoardTest {
    private SudokuSolver solver0 = new BacktrackingSudokuSolver();
    private SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
    private SudokuBoard board2 = new SudokuBoard(new BacktrackingSudokuSolver());

    private int countZeros(SudokuBoard board) throws OutOfRangeCoordsException {
        int count = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.get(i, j) == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    @Test
    void constructorTest() throws OutOfRangeCoordsException {
        SudokuBoard board0 = new SudokuBoard(solver0);
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                assertEquals(0, board0.get(i, j));
            }
        }
    }

    @Test
    void setterTest() throws OutOfRangeCoordsException {
        board.set(0, 0, 5);
        assertEquals(5, board.get(0, 0));
    }

    @Test
    void testSolveGame() throws OutOfRangeCoordsException {
        board.solveGame();
        assertTrue(board.checkBoard());
    }

    @Test
    void checkBoard_true_Test() throws OutOfRangeCoordsException {
        SudokuBoard board0 = new SudokuBoard(solver0);
        board0.solveGame();
        assertTrue(board0.checkBoard());
    }

    @Test
    void checkBoard_SameRow_Test() throws OutOfRangeCoordsException {
        SudokuBoard board0 = new SudokuBoard(solver0);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board0.set(i, j, i+1);
            }
        }
        assertFalse(board0.checkBoard());
    }

    @Test
    void checkBoard_SameCol_Test() throws OutOfRangeCoordsException {
        SudokuBoard board0 = new SudokuBoard(solver0);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board0.set(i, j, j+1);
            }
        }
        assertFalse(board0.checkBoard());
    }

    @Test
    void checkBoard_SameBlock_Test() throws OutOfRangeCoordsException {
        SudokuBoard board0 = new SudokuBoard(solver0);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board0.set(i, j, (i + j) % 9 + 1);
            }
        }
        assertFalse(board0.checkBoard());
    }

    @Test
    void checkBoard_OutOfRange_Test() throws OutOfRangeCoordsException {
        SudokuBoard board0 = new SudokuBoard(solver0);
        board0.solveGame();
        board0.set(0, 0, 10);
        assertFalse(board0.checkBoard());
        board0.set(0, 0, -1);
        assertFalse(board0.checkBoard());
    }

    @Test
    void toStringTest() throws OutOfRangeCoordsException {
        SudokuBoard board0 = new SudokuBoard(solver0);
        board0.solveGame();
        assertNotEquals("", board0.toString());
    }

    @Test
    void equalsTest() throws OutOfRangeCoordsException {
        board.solveGame();
        board2.solveGame();
        assertTrue(board.equals(board));
        assertFalse(board.equals(board2));
        assertFalse(board.equals(null));
        assertFalse(board.equals(2));
    }

    @Test
    void hashCodeTest() throws OutOfRangeCoordsException {
        board.solveGame();
        board2.solveGame();

        assertNotEquals(board.hashCode(), board2.hashCode());
    }

    @Test
    void cloneTest() throws CloneNotSupportedException, OutOfRangeCoordsException{
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard originalBoard = new SudokuBoard(solver);
        SudokuBoard clone = originalBoard.clone();

        assertEquals(originalBoard, clone);
        assertFalse(originalBoard == clone);

        solver = null;
        originalBoard.setSolver(null);
        assertThrows(NullPointerException.class, () -> {
            originalBoard.solveGame();
        });

        clone.solveGame();
        assertTrue(clone.checkBoard());
    }

    @Test
    void createPuzzleTest() throws UnfilledBoardException, OutOfRangeCoordsException {
        board.solveGame();
        board.createPuzzle(Easy);
        assertEquals(81 - 33, countZeros(board));

        board2.solveGame();
        board2.createPuzzle(Medium);
        assertEquals(81 - 25, countZeros(board2));

        SudokuBoard board3 = new SudokuBoard(new BacktrackingSudokuSolver());
        board3.solveGame();
        board3.createPuzzle(Hard);
        assertEquals(81 - 17, countZeros(board3));

        SudokuBoard board4 = new SudokuBoard(new BacktrackingSudokuSolver());
        assertThrows(UnfilledBoardException.class, () -> {
            board4.createPuzzle(Easy);
        });
    }
}