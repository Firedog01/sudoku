package pl.comp.dao;

/*
sources:
    https://www.baeldung.com/java-abstract-factory-pattern
 */

import java.io.FileNotFoundException;
import pl.comp.model.SudokuBoard;

public interface SudokuBoardDaoAbstractFactory {
    public Dao<SudokuBoard> getFileDao(String fileName) throws FileNotFoundException;
}
