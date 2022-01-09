package pl.comp.exceptions.model.dao;

import pl.comp.exceptions.model.SudokuException;

import java.util.Locale;

public class SudokuFileException extends SudokuException {
    public SudokuFileException(String message) {
        super(message);
    }

    public SudokuFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public SudokuFileException(String message, Throwable cause, Locale locale) {
        super(message, cause, locale);
    }
}
