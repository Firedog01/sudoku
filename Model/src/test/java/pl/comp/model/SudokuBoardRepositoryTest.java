package pl.comp.model;

import org.junit.jupiter.api.Test;
import pl.comp.exceptions.model.NoSolverException;
import pl.comp.exceptions.model.OutOfRangeCoordsException;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardRepositoryTest {

    @Test
    void mainTest() throws CloneNotSupportedException, OutOfRangeCoordsException, NoSolverException {
        SudokuBoardRepository repository = new SudokuBoardRepository(new BacktrackingSudokuSolver());
        SudokuBoard board = repository.createInstance();
        board.solveGame();
        assertTrue(board.checkBoard());
    }

}