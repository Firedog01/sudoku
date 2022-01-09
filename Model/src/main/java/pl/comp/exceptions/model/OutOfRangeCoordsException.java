package pl.comp.exceptions.model;

import java.util.Locale;

public class OutOfRangeCoordsException extends SudokuException {
    public OutOfRangeCoordsException(String message) {
        super(message);
    }

    public OutOfRangeCoordsException(String message, Throwable cause) {
        super(message, cause);
    }

    public OutOfRangeCoordsException(String message, Throwable cause, Locale locale) {
        super(message, cause, locale);
    }
}