package pl.comp.model;

import java.util.List;
import pl.comp.exceptions.model.SudokuCloneException;

public class SudokuColumn extends SudokuElement {
    /**
     * Constructor. Requires array of 9 SudokuFields,
     * otherwise will throw IllegalArgumentException.
     * @param values array of length 9.
     */
    public SudokuColumn(List<SudokuField> values) {
        setFields(values);
    }

    @Override
    public SudokuColumn clone() throws CloneNotSupportedException {
        try {
            return new SudokuColumn(cloneFields());
        } catch (CloneNotSupportedException e) {
            throw new SudokuCloneException("exception.cloneEx", e);
        }
    }
}
