package pl.comp.dao;

import pl.comp.model.SudokuBoard;

import java.io.FileNotFoundException;

public class SudokuBoardDaoFactory implements SudokuBoardDaoAbstractFactory {

    @Override
    public Dao<SudokuBoard> getFileDao(String fileName) throws FileNotFoundException {
        Dao<SudokuBoard> dao = new FileSudokuBoardDao(fileName);
        return dao;
    }
}
