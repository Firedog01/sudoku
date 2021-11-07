public class SudokuRow extends SudokuElement {
    /**
     * Constructor. Requires array of 9 SudokuFields,
     * otherwise will throw IllegalArgumentException.
     * @param values array of length 9.
     */
    SudokuRow(SudokuField[] values) {
        setFields(values);
    }
}
