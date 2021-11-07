public class SudokuRow extends SudokuElement {
    SudokuRow(SudokuField[] values) {
        setFields(values);
    }
    public SudokuRow getRow() {
        return this;
    }
}
