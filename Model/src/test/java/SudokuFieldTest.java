import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import pl.comp.model.SudokuField;

class SudokuFieldTest {
    @Test
    void getFieldValue() {
        SudokuField field = new SudokuField(3);
        assertEquals(3, field.getFieldValue());
    }

    @Test
    void setFieldValue() {
        SudokuField field = new SudokuField(3);
        assertEquals(3, field.getFieldValue());
        field.setFieldValue(5);
        assertEquals(5, field.getFieldValue());
    }

    @Test
    void toStringTest() {
        SudokuField field = new SudokuField(2);
        assertEquals("2", field.toString());
    }

    @Test
    void equalsTest() {
        SudokuField field1 = new SudokuField(1);
        SudokuField field1_another = new SudokuField(1);
        SudokuField field2 = new SudokuField(2);
        Integer int1 = 1;

        assertTrue(field1.equals(field1_another));
        assertFalse(field1.equals(field2));
        assertFalse(field1.equals(null));
        assertFalse(field1.equals(int1));
    }

    @Test
    void hashCodeTest() {
        SudokuField field1 = new SudokuField(1);
        SudokuField field1_another = new SudokuField(1);
        SudokuField field2 = new SudokuField(2);

        assertEquals(field1.hashCode(), field1_another.hashCode());
        assertNotEquals(field1.hashCode(), field2.hashCode());
    }

    @Test
    void cloningTest() throws CloneNotSupportedException {
        SudokuField f1 = new SudokuField(3);
        SudokuField f2 = f1.clone();
        assertEquals(f1, f2);
    }

    @Test
    void comparingTest() {
        SudokuField f1 = new SudokuField(3);
        SudokuField f2 = new SudokuField(5);
        SudokuField f3 = new SudokuField(3);

        assertTrue(f1.compareTo(f2) < 0);
        assertTrue(f2.compareTo(f1) > 0);
        assertTrue(f1.compareTo(f3) == 0);
        assertThrows(NullPointerException.class, () -> {
            f1.compareTo(null);
        });

    }
}