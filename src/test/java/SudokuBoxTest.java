import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoxTest {

    @Test
    void getRow_constructorCorrect() {
        SudokuField[] fields = new SudokuField[9];
        for (int i = 1; i < 10; i++) {
            fields[i - 1] = new SudokuField(i);
        }
        SudokuBox box = new SudokuBox(fields);
        assertTrue(box.isValid());
    }
}