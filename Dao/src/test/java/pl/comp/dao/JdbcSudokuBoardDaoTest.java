package pl.comp.dao;

import org.junit.jupiter.api.Test;
import pl.comp.exceptions.model.NoSolverException;
import pl.comp.exceptions.model.OutOfRangeCoordsException;
import pl.comp.exceptions.model.UnfilledBoardException;
import pl.comp.exceptions.model.dao.SudokuFileException;
import pl.comp.model.BacktrackingSudokuSolver;
import pl.comp.model.Difficulty;
import pl.comp.model.SudokuBoard;
import pl.comp.model.SudokuBoardRepository;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JdbcSudokuBoardDaoTest {
    SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
    SudokuBoardRepository repository = new SudokuBoardRepository(new BacktrackingSudokuSolver());
    @Test
    void read() throws Exception {
        SudokuBoard board = repository.createInstance();
        board.solveGame();
        board.createPuzzle(Difficulty.Easy);

        try (Dao<SudokuBoard> dbDao = factory.getDbDao("testBoard")) {
            dbDao.write(board);
            SudokuBoard boardRead = dbDao.read();

            assertEquals(board, boardRead);
        } catch (SudokuFileException | IOException e) {
            e.printStackTrace();
        }
    }
}