package pl.comp.model;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import pl.comp.model.SudokuBox;
import pl.comp.model.SudokuColumn;
import pl.comp.model.SudokuField;

class SudokuBoxTest {
    @Test
    void getRow_constructorCorrect() {
        List<SudokuField> fields = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
             fields.add(new SudokuField(i));
        }
        SudokuBox box = new SudokuBox(fields);
        assertTrue(box.isValid());
    }

    @Test
    void cloneTest() throws CloneNotSupportedException {
        List<SudokuField> fields = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            fields.add(new SudokuField(i));
        }
        SudokuBox box = new SudokuBox(fields);
        SudokuBox clone = box.clone();

        assertEquals(box, clone);
    }
}