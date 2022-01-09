package pl.comp.exceptions.model;

import java.util.Locale;
import java.util.ResourceBundle;

public class SudokuCloneException extends CloneNotSupportedException {
    private ResourceBundle bundle;

    public SudokuCloneException(String message) {
        this(message, new CloneNotSupportedException(), Locale.getDefault());
    }

    public SudokuCloneException(String message, Throwable cause) {
        this(message, cause, Locale.getDefault());
    }

    public SudokuCloneException(String message, Throwable cause, Locale locale) {
        super(message);
        initCause(cause);
        bundle = ResourceBundle.getBundle("Lang", locale);
    }

    @Override
    public String getLocalizedMessage() {
        return bundle.getString(getMessage());
    }
}
