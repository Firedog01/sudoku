public class SudokuBox extends SudokuElement {
    SudokuBox(SudokuField[] values) {
        setFields(values);
    }
    public SudokuBox getBox() {
        return this;
    }
}
