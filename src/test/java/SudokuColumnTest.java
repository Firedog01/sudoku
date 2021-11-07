import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuColumnTest {

    @Test
    void getColumn_constructorCorrect() {
        SudokuField[] fields = new SudokuField[9];
        for (int i = 1; i < 10; i++) {
            fields[i - 1] = new SudokuField(i);
        }
        SudokuColumn column = new SudokuColumn(fields);
        assertTrue(column.isValid());
    }
}
/*
source:
    https://www.baeldung.com/junit-assert-exception
 */