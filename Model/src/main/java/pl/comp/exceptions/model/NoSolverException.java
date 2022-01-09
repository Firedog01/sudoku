package pl.comp.exceptions.model;

import java.util.Locale;
import java.util.ResourceBundle;

public class NoSolverException extends SudokuException {
    private ResourceBundle bundle;

    public NoSolverException(String message) {
        this(message, new Exception(), Locale.getDefault());
    }

    public NoSolverException(String message, Throwable cause) {
        this(message, cause, Locale.getDefault());
    }

    public NoSolverException(String message, Throwable cause, Locale locale) {
        super(message, cause);
        bundle = ResourceBundle.getBundle("Lang", locale);
    }

    @Override
    public String getLocalizedMessage() {
        return bundle.getString(getMessage());
    }
}
