import java.util.Arrays;

public abstract class SudokuElement {

    private SudokuField[] fields;

    public boolean verify() {
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

    public void addField(int index, SudokuField newField) {
        fields[index] = newField;
    }

}
