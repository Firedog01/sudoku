package pl.comp.model;

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
}
