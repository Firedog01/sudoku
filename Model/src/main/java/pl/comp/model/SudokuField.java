package pl.comp.model;/*
source:
    https://docs.oracle.com/javase/7/docs/api/java/beans/PropertyChangeSupport.html
 */

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class SudokuField implements Serializable, Comparable<SudokuField>, Cloneable {
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
     * Checks if different pl.comp.model.SudokuField has exactly same values.
     *
     * @param obj another pl.comp.model.SudokuField to check values against this one
     * @return true if pl.comp.model.SudokuField are the same.
     */
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        SudokuField rhs = (SudokuField) obj;
        return new EqualsBuilder()
                .append(value, rhs.value)
                .isEquals();
    }

    /**
     * Returns hashcode of pl.comp.model.SudokuElement object.
     *
     * @return hashcode.
     */
    public int hashCode() {
        return new HashCodeBuilder(21, 37)
                .append(value)
                .toHashCode();
    }

    @Override
    public int compareTo(SudokuField o) throws NullPointerException {
        return value - o.value;
    }

    @Override
    public SudokuField clone() throws CloneNotSupportedException {
        return (SudokuField) super.clone();
    }
}

