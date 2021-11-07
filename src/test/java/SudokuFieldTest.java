import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
}