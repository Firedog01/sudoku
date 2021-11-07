import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SudokuField {
    /**
     * value of this field.
     */
    private int value;

    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /**
     * Constructor, by default sets value to one given.
     * @param x value of a field.
     */
    public SudokuField(int x) {
        value = x;
    }

    public void addListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
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
        pcs.firePropertyChange("value_changed", this.value, value);
        this.value = value;
    }
}
/*
source:
    https://docs.oracle.com/javase/7/docs/api/java/beans/PropertyChangeSupport.html
 */
