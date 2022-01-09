package pl.comp.exceptions.model.dao;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class SudokuIOException extends IOException {
    private ResourceBundle bundle;

    public SudokuIOException(String message) {
        this(message, new CloneNotSupportedException(), Locale.getDefault());
    }

    public SudokuIOException(String message, Throwable cause) {
        this(message, cause, Locale.getDefault());
    }

    public SudokuIOException(String message, Throwable cause, Locale locale) {
        super(message, cause);
        bundle = ResourceBundle.getBundle("Lang", locale);
    }

    @Override
    public String getLocalizedMessage() {
        return bundle.getString(getMessage());
    }
}
