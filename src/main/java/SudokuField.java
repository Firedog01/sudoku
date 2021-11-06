public class SudokuField {
    /**
     * value of this field
     */
    private int value;

    //why all this?
    private SudokuBoard board;
    private SudokuRow row;
    private SudokuColumn column;
    private SudokuBox box;

    /**
     *
     * @param newBoard
     * @param newRow
     * @param newColumn
     * @param newBox
     */
    public SudokuField(SudokuBoard newBoard, SudokuRow newRow,
                       SudokuColumn newColumn, SudokuBox newBox) {
        board = newBoard;
        row = newRow;
        column = newColumn;
        box = newBox;
    }

    public SudokuRow getRow() {
        return row;
    }

    public SudokuColumn getColumn() {
        return column;
    }

    public SudokuBox getBox() {
        return box;
    }

    /**
     * Basic getter.
     * @return value of field.
     */
    public int getFieldValue() {
        return value;
    }

    /**
     * Basic setter.
     * @param value new value of a field
     */
    public void setFieldValue(int value) {
        this.value = value;
    }
}
