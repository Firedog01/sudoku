package pl.comp.exceptions;

import java.util.Locale;

public class BSSCloneException extends SudokuException {
    public BSSCloneException(String message) {
        super(message);
    }

    public BSSCloneException(String message, Throwable cause) {
        super(message, cause);
    }

    public BSSCloneException(String message, Throwable cause, Locale locale) {
        super(message, cause, locale);
    }
}