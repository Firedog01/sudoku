/*
PropertyChangeListener:
https://cr.openjdk.java.net/~iris/se/17/build/latest/api/java.desktop/java/beans/package-summary.html
https://www.baeldung.com/java-observer-pattern
 */


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.List;

public abstract class SudokuElement implements PropertyChangeListener {
    /**
     * Array of 9 objects SudokuField representing contents of one element.
     */
    private List<SudokuField> fields;

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
    protected void setFields(List<SudokuField> values) {
        if (values.size() != 9) {
            throw new IllegalArgumentException("Array must be of length = 9");
        }
        fields = List.copyOf(values);
        for (int i = 0; i < 9; i++) {
            fields.get(i).addListener(this);
        }
        verify();
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
            int i = fields.get(j).getFieldValue() - 1;
            if (i > 8 || i < 0) {
                return false; //out of bounds
            }
            if (checkedFields[i]) {
                valid = false;
                return false;
            } else {
                checkedFields[i] = true;
            }
        }
        valid = true;
        return true;
    }

    /**
     * Basic getter of valid.
     * You do not need verify to redo calculations.
     *
     * @return state of valid.
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * On field changed. Verifies if values are correct.
     * @param evt event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        verify();
    }

    /**
     * returns element in following form [1, 2, 3, 4, 5].
     * @return string
     */
    public String toString() {
        String ret = "[";
        ret += fields.get(0);
        for (int i = 1; i < 9; i++) {
            ret += ", ";
            ret += fields.get(i);
        }
        ret += "]\n";
        return ret;
    }
}
