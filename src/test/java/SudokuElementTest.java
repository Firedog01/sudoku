import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SudokuElementTest {

    @Test
    void verify_false() {
        List<SudokuField> fields = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            fields.add(new SudokuField(1));
        }
        SudokuColumn col = new SudokuColumn(fields);
        assertFalse(col.isValid());
    }

    @Test
    void incorrect_array_length() {
        List<SudokuField> fields = new ArrayList<>();
        for (int i = 1; i < 9; i++) {
            fields.add(new SudokuField(i));
        }
        Exception exception = assertThrows(RuntimeException.class, () -> {
            SudokuColumn column = new SudokuColumn(fields);
        });

        String expectedMessage = "Array must be of length = 9";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}