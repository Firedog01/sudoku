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

    @Test
    void toStringTest() {
        List<SudokuField> fields = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            fields.add(new SudokuField(i));
        }
        SudokuColumn col = new SudokuColumn(fields);

        assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9]\n", col.toString());
    }

    @Test
    void equalsTest() {
        List<SudokuField> fields_ord = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            fields_ord.add(new SudokuField(i));
        }
        List<SudokuField> fields_rev_ord = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            fields_rev_ord.add(new SudokuField(10 - i));
        }
        SudokuColumn col_ord = new SudokuColumn(fields_ord);
        SudokuColumn col_ord_another = new SudokuColumn(fields_ord);
        SudokuColumn col_rev_ord = new SudokuColumn(fields_rev_ord);

        assertTrue(col_ord.equals(col_ord_another));
        assertFalse(col_ord.equals(col_rev_ord));
        assertFalse(col_ord.equals(null));
        assertFalse(col_ord.equals(fields_ord));
    }

    @Test
    void hashCodeTest() {
        List<SudokuField> fields_ord = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            fields_ord.add(new SudokuField(i));
        }
        List<SudokuField> fields_rev_ord = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            fields_rev_ord.add(new SudokuField(10 - i));
        }
        SudokuColumn col_ord = new SudokuColumn(fields_ord);
        SudokuColumn col_ord_another = new SudokuColumn(fields_ord);
        SudokuColumn col_rev_ord = new SudokuColumn(fields_rev_ord);

        assertEquals(col_ord.hashCode(), col_ord_another.hashCode());
        assertNotEquals(col_ord.hashCode(), col_rev_ord.hashCode());
    }
}