package pl.comp.exceptions;

import java.util.Locale;

public class UnfilledBoardException extends SudokuException {
    public UnfilledBoardException(String message) {
        super(message);
    }

    public UnfilledBoardException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnfilledBoardException(String message, Throwable cause, Locale locale) {
        super(message, cause, locale);
    }
}