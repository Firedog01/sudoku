package pl.comp.dao;

import java.io.FileNotFoundException;

import pl.comp.exceptions.model.dao.SudokuFileException;
import pl.comp.model.SudokuBoard;

public class SudokuBoardDaoFactory {
    public Dao<SudokuBoard> getFileDao(String fileName) throws SudokuFileException {
        Dao<SudokuBoard> dao = new FileSudokuBoardDao(fileName);
        return dao;
    }
}
