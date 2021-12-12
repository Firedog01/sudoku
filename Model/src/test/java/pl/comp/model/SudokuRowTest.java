package pl.comp.model;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class SudokuRowTest {
    @Test
    void getRow_constructorCorrect() {
        List<SudokuField> fields = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            fields.add(new SudokuField(i));
        }
        SudokuRow row = new SudokuRow(fields);
        assertTrue(row.isValid());
    }

    @Test
    void cloneTest() throws CloneNotSupportedException {
        List<SudokuField> fields = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            fields.add(new SudokuField(i));
        }
        SudokuRow row = new SudokuRow(fields);
        SudokuRow clone = row.clone();

        assertEquals(row, clone);
    }
}