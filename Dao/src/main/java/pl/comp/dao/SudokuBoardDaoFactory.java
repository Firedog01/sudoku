package pl.comp.dao;

import java.io.FileNotFoundException;
import pl.comp.model.SudokuBoard;

public class SudokuBoardDaoFactory {
    public Dao<SudokuBoard> getFileDao(String fileName) throws FileNotFoundException {
        Dao<SudokuBoard> dao = new FileSudokuBoardDao(fileName);
        return dao;
    }
}
