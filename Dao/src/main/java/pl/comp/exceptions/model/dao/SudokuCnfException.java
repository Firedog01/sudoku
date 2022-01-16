package pl.comp.exceptions.model.dao;

import pl.comp.exceptions.model.SudokuException;

import java.util.Locale;

public class SudokuCnfException extends SudokuException {
    public SudokuCnfException(String message) {
        super(message);
    }

    public SudokuCnfException(String message, Throwable cause) {
        super(message, cause);
    }

    public SudokuCnfException(String message, Throwable cause, Locale locale) {
        super(message, cause, locale);
    }
}
