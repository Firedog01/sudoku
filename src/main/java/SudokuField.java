import java.beans.PropertyChangeSupport;

public class SudokuField {
    /**
     * value of this field.
     */
    private int value;

    private PropertyChangeSupport support;


    /**
     * Constructor, by default sets value to one given.
     * @param x value of a field.
     */
    public SudokuField(int x) {
        value = x;
        support = new PropertyChangeSupport(this);
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
