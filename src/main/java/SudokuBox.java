public class SudokuBox extends SudokuElement {
    /**
     * Constructor. Requires array of 9 SudokuFields,
     * otherwise will throw IllegalArgumentException.
     * @param values array of length 9.
     */
    SudokuBox(SudokuField[] values) {
        setFields(values);
    }

    /**
     * Standard getter.
     * @return this object.
     */
    public SudokuBox getBox() {
        return this;
    }
}
