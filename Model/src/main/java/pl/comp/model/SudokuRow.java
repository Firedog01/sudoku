package pl.comp.model;

import java.util.List;

public class SudokuRow extends SudokuElement {
    /**
     * Constructor. Requires array of 9 SudokuFields,
     * otherwise will throw IllegalArgumentException.
     * @param values array of length 9.
     */
    SudokuRow(List<SudokuField> values) {
        setFields(values);
    }
}
