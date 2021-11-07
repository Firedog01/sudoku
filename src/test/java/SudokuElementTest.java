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
    void incorrect_array_length() {
        SudokuField[] fields = new SudokuField[8];
        for (int i = 1; i < 9; i++) {
            fields[i - 1] = new SudokuField(i);
        }
        Exception exception = assertThrows(RuntimeException.class, () -> {
            SudokuColumn column = new SudokuColumn(fields);
        });

        String expectedMessage = "Array must be of length = 9";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}