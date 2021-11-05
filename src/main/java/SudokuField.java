public class SudokuField{
    private int value;

    private SudokuBoard board;
    private SudokuRow row;
    private SudokuColumn column;
    private SudokuBox box;

    public int getFieldValue(){ return value; }
    public void setFieldValue(int value){ this.value = value; }
}
