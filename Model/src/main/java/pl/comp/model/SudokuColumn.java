package pl.comp.model;

import java.util.ArrayList;
import java.util.List;

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
        return new SudokuColumn(cloneFields());
    }
}
