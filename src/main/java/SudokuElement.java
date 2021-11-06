/*
PropertyChangeListener:
https://cr.openjdk.java.net/~iris/se/17/build/latest/api/java.desktop/java/beans/package-summary.html
https://www.baeldung.com/java-observer-pattern
 */


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

public abstract class SudokuElement implements PropertyChangeListener{ //constructor xxx?
    /**
     * Array of 9 objects SudokuField representing contents of one element
     */
    private SudokuField[] fields;

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
                return false;
            } else {
                checkedFields[j] = true;
            }
        }
        return true;
    }

    /** xxx setField? maybe obsolete
     * Sets value of field at given index
     *
     * @param index position in array.
     * @param newField new value of field.
     */
    public void addField(int index, SudokuField newField) {
        fields[index] = newField;
    }

    public void propertyChange(PropertyChangeEvent evt) {
        verify();
    }

}
