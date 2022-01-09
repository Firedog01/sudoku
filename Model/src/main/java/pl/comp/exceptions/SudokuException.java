package pl.comp.exceptions;

import java.util.Locale;
import java.util.ResourceBundle;

public class SudokuException extends Exception {
    private ResourceBundle bundle;

    public SudokuException(String message) {
        this(message, new Exception(), Locale.getDefault());
    }

    public SudokuException(String message, Throwable cause) {
        this(message, cause, Locale.getDefault());
    }

    public SudokuException(String message, Throwable cause, Locale locale) {
        super(message, cause);
        bundle = ResourceBundle.getBundle("Lang", locale);
    }

    @Override
    public String getLocalizedMessage() {
        return bundle.getString(getMessage());
    }
}