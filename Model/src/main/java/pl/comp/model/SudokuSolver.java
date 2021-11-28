package pl.comp.model;

public interface SudokuSolver {
    /**
     * Function to fill board with values.
     * @param sudokuBoard board to fill.
     */
    void solve(SudokuBoard sudokuBoard);
}
