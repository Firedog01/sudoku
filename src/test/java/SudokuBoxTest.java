import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

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
}