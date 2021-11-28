package pl.comp.dao;

/*
sources:
    https://www.baeldung.com/java-abstract-factory-pattern
 */

import pl.comp.model.SudokuBoard;
import java.io.FileNotFoundException;

public interface SudokuBoardDaoAbstractFactory {
    public Dao<SudokuBoard> getFileDao(String fileName) throws FileNotFoundException;
}
