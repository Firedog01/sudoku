/*
sources:
    https://www.baeldung.com/junit-assert-exception
 */

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class SudokuColumnTest {
    @Test
    void getColumn_constructorCorrect() {
        List<SudokuField> fields = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            fields.add(new SudokuField(i));
        }
        SudokuColumn column = new SudokuColumn(fields);
        assertTrue(column.isValid());
    }
}
