import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuElementTest {

    @Test
    void verify_false() {
        SudokuField[] fields = new SudokuField[9];
        for (int i = 1; i < 10; i++) {
            fields[i - 1] = new SudokuField(1);
        }
        SudokuColumn col = new SudokuColumn(fields);
        assertFalse(col.isValid());
    }

    @Test
    void addRemoveListener() {
        SudokuField[] fields = new SudokuField[9];
        for (int i = 1; i < 10; i++) {
            fields[i - 1] = new SudokuField(1);
        }
        SudokuColumn col = new SudokuColumn(fields);
        assertFalse(col.isValid());
        for (int i = 0; i < 9; i++) {
            fields[i].removeListener(col);
            fields[i].setFieldValue(i+1);
        }
        assertFalse(col.isValid());
    }
}