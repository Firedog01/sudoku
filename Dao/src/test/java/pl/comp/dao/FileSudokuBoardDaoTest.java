package pl.comp.dao;

import org.junit.jupiter.api.Test;
import pl.comp.model.BacktrackingSudokuSolver;
import pl.comp.model.SudokuBoard;
import pl.comp.model.SudokuSolver;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileSudokuBoardDaoTest {

    @Test
    void read_write_correct() throws IOException {
        System.out.println(System.getProperty("user.dir"));
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        Dao<SudokuBoard> fileDao = factory.getFileDao("src/test/test");

        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();
        try (Dao<SudokuBoard> fd = factory.getFileDao("src/test/test")) {
            fileDao.write(board);
        } catch (Exception e){
            e.printStackTrace();
        }


        SudokuBoard boardRead = fileDao.read();
        assertEquals(board.toString(), boardRead.toString());
    }

    @Test
    void close() throws IOException {

    }
}