package pl.comp.model;

import org.junit.jupiter.api.Test;
import pl.comp.exceptions.OutOfRangeCoordsException;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardRepositoryTest {

    @Test
    void mainTest() throws CloneNotSupportedException, OutOfRangeCoordsException {
        SudokuBoardRepository repository = new SudokuBoardRepository(new BacktrackingSudokuSolver());
        SudokuBoard board = repository.createInstance();
        board.solveGame();
        assertTrue(board.checkBoard());
    }

}