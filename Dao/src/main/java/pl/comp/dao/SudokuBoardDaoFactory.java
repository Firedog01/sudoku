package pl.comp.dao;

import pl.comp.exceptions.model.dao.SudokuFileException;
import pl.comp.model.SudokuBoard;

public class SudokuBoardDaoFactory {
    public Dao<SudokuBoard> getFileDao(String fileName) throws SudokuFileException {
        Dao<SudokuBoard> dao = new FileSudokuBoardDao(fileName);
        return dao;
    }

    public Dao<SudokuBoard> getDbDao(String saveName) throws SudokuFileException {
        Dao<SudokuBoard> dao = new JdbcSudokuBoardDao(saveName);
        return dao;
    }
}
