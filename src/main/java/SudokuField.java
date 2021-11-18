import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

    /**
     * Returns value of field as string.
     * @return string
     */
    public String toString() {
        return String.valueOf(value);
    }

    /**
     * Checks if different SudokuField has exactly same values.
     *
     * @param obj another SudokuField to check values against this one
     * @return true if SudokuField are the same.
     */
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == null) { return true; }
        if (obj.getClass() != getClass()) {
            return false;
        }
        SudokuField rhs = (SudokuField) obj;
        return new EqualsBuilder()
                .append(value, rhs.value)
                .isEquals();
    }

    /**
     * Returns hashcode of SudokuElement object.
     *
     * @return hashcode.
     */
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(value)
                .toHashCode();
    }
}
/*
source:
    https://docs.oracle.com/javase/7/docs/api/java/beans/PropertyChangeSupport.html
 */
