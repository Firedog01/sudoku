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
        column.verify();//
        assertTrue(column.isValid());
        column.getColumn();
    }

    @Test
    void getColumn_constructorIncorrect() {
        SudokuField[] fields = new SudokuField[8];
        for (int i = 1; i < 9; i++) {
            fields[i - 1] = new SudokuField(i);
        }
        Exception exception = assertThrows(RuntimeException.class, () -> {
            SudokuColumn column = new SudokuColumn(fields);
        });

        String expectedMessage = "Array must be of length = 9";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
/*
source:
    https://www.baeldung.com/junit-assert-exception
 */