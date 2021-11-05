import java.util.Arrays;

abstract public class SudokuElement {

    private SudokuField[] fields;

    boolean verify(){
        boolean[] checkedFields = new boolean[9];
        Arrays.fill(checkedFields, false);
        for (int j = 0; j < 9; j++) {
            if (checkedFields[j]) {
                return false;
            } else {
                checkedFields[j] = true;
            }
        }
        return true;
    }

}
