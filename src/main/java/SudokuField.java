public class SudokuField{
    private int value;

    private SudokuBoard board;
    private SudokuRow row;
    private SudokuColumn column;
    private SudokuBox box;

    public SudokuField(SudokuBoard newBoard, SudokuRow newRow, SudokuColumn newColumn, SudokuBox newBox) {
        board = newBoard;
        row = newRow;
        column = newColumn;
        box = newBox;
    }

    public int getFieldValue(){ return value; }
    public void setFieldValue(int value){ this.value = value; }
}
