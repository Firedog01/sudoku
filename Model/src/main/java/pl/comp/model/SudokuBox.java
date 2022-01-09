package pl.comp.model;

import pl.comp.exceptions.SudokuCloneException;

import java.util.List;

public class SudokuBox extends SudokuElement {
    /**
     * Constructor. Requires array of 9 SudokuFields,
     * otherwise will throw IllegalArgumentException.
     * @param values array of length 9.
     */
    public SudokuBox(List<SudokuField> values) {
        setFields(values);
    }

    @Override
    public SudokuBox clone() throws CloneNotSupportedException {
        try {
            return new SudokuBox(cloneFields());
        } catch (CloneNotSupportedException e) {
            throw new SudokuCloneException("exception.cloneEx", e);
        }
    }
}
