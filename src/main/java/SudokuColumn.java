public class SudokuColumn extends SudokuElement {
    SudokuColumn(SudokuField[] values) {
        setFields(values);
    }
    public SudokuColumn getColumn() {
        return this;
    }
}
