import de.sfuhrm.sudoku.Creator;
import de.sfuhrm.sudoku.GameMatrix;

public class BacktrackingSudokuSolver implements SudokuSolver{
    public void solve(SudokuBoard board){
        GameMatrix matrix = Creator.createFull();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board.set(i, j, matrix.get(i, j));
            }
        }
    }
}
