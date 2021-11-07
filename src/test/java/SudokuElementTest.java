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
        assertFalse(col.verify());
        assertFalse(col.isValid());
    }
}