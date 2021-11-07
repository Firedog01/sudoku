import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuRowTest {

    @Test
    void getRow_constructorCorrect() {
        SudokuField[] fields = new SudokuField[9];
        for (int i = 1; i < 10; i++) {
            fields[i - 1] = new SudokuField(i);
        }
        SudokuRow row = new SudokuRow(fields);
        row.verify();//
        assertTrue(row.isValid());
        row.getRow();
    }

    @Test
    void getRow_constructorIncorrect() {
        SudokuField[] fields = new SudokuField[8];
        for (int i = 1; i < 9; i++) {
            fields[i - 1] = new SudokuField(i);
        }
        Exception exception = assertThrows(RuntimeException.class, () -> {
            SudokuRow row = new SudokuRow(fields);
        });

        String expectedMessage = "Array must be of length = 9";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}