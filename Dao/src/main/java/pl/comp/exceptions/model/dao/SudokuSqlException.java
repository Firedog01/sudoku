package pl.comp.exceptions.model.dao;

import pl.comp.exceptions.model.SudokuException;

import java.util.Locale;

public class SudokuSqlException extends SudokuException {
    public SudokuSqlException(String message) {
        super(message);
    }

    public SudokuSqlException(String message, Throwable cause) {
        super(message, cause);
    }

    public SudokuSqlException(String message, Throwable cause, Locale locale) {
        super(message, cause, locale);
    }
}
