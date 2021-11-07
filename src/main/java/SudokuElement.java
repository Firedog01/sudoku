/*
PropertyChangeListener:
https://cr.openjdk.java.net/~iris/se/17/build/latest/api/java.desktop/java/beans/package-summary.html
https://www.baeldung.com/java-observer-pattern
 */


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Arrays;

public abstract class SudokuElement implements PropertyChangeListener {
    /**
     * Array of 9 objects SudokuField representing contents of one element.
     */
    private SudokuField[] fields = new SudokuField[9];

    /**
     * Contains result of last call of verify method.
     */
    private boolean valid;

    /**
     * Sets fields, requires array of 9 SudokuFields to initialise correctly.
     *
     * @param values array of SudokuFields, must be of length 9.
     * @throws IllegalArgumentException when array length not equal 9.
     */
    protected void setFields(SudokuField[] values) {
        if (values.length != 9) {
            throw new IllegalArgumentException("Array must be of length = 9");
        }

        for (int i = 0; i < 9; i++) {
            fields[i] = values[i];
        }
    }

    /**
     * Verifies if one element has unique values in it.
     *
     * @return true if one every value is unique.
     */
    public boolean verify() {
        boolean[] checkedFields = new boolean[9];
        Arrays.fill(checkedFields, false);
        for (int j = 0; j < 9; j++) {
            if (checkedFields[j]) {
                valid = false;
                return false;
            } else {
                checkedFields[j] = true;
            }
        }
        valid = true;
        return true;
    }

    /**
     * Basic getter of valid.
     * @return state of valid.
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * Sets value of field at given index.
     *
     * @param index position in array.
     * @param newField new value of field.
     */
    public void setField(int index, SudokuField newField) {
        fields[index] = newField;
    }

    /**
     * On field changed. Verifies if values are correct.
     * @param evt event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        verify();
    }
}
