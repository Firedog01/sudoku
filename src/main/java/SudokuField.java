import java.beans.PropertyChangeSupport;

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

    private PropertyChangeSupport support;


    public SudokuField() {
        value = 0;
        support = new PropertyChangeSupport(this);
        support.addPropertyChangeListener(row);
        support.addPropertyChangeListener(column);
        support.addPropertyChangeListener(box);
    }

    public void setRow(SudokuRow _row) {
        row = _row;
    }

    public SudokuRow getRow() {
        return row;
    }

    public void setColumn(SudokuColumn _column) {
        column = _column;
    }

    public SudokuColumn getColumn() {
        return column;
    }

    public void setBox(SudokuBox _box) {
        box = _box;
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
        support.firePropertyChange("value", this.value, value);
        this.value = value;
    }

}
