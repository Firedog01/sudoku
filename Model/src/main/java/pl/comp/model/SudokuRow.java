package pl.comp.model;

import pl.comp.exceptions.SudokuCloneException;

import java.util.List;

public class SudokuRow extends SudokuElement {
    /**
     * Constructor. Requires array of 9 SudokuFields,
     * otherwise will throw IllegalArgumentException.
     * @param values array of length 9.
     */
    public SudokuRow(List<SudokuField> values) {
        setFields(values);
    }

    @Override
    public SudokuRow clone() throws CloneNotSupportedException {
        try{
            return new SudokuRow(cloneFields());
        } catch (CloneNotSupportedException e) {
            throw new SudokuCloneException("exception.cloneEx", e);
        }
    }
}
