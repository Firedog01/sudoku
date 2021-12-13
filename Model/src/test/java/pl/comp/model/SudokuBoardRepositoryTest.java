package pl.comp.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardRepositoryTest {

    @Test
    void mainTest() throws CloneNotSupportedException {
        SudokuBoardRepository repository = new SudokuBoardRepository(new BacktrackingSudokuSolver());
        SudokuBoard board = repository.createInstance();
        board.solveGame();
        assertTrue(board.checkBoard());
    }

}