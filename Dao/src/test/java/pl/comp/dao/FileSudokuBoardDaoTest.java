package pl.comp.dao;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.comp.exceptions.model.OutOfRangeCoordsException;
import pl.comp.model.BacktrackingSudokuSolver;
import pl.comp.model.Greet;
import pl.comp.model.SudokuBoard;
import pl.comp.model.SudokuSolver;

import static org.junit.jupiter.api.Assertions.*;

class FileSudokuBoardDaoTest {

    @Test
    void read_write_correct() throws Exception {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();

        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();

        try (Dao<SudokuBoard> fileDao = factory.getFileDao("src/test/test")) {
            fileDao.write(board);
            SudokuBoard boardRead = fileDao.read();

            assertEquals(board, boardRead);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}