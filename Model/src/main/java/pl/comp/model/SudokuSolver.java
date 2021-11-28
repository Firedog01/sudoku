package pl.comp.model;

import java.io.Serializable;

public interface SudokuSolver extends Serializable {
    /**
     * Function to fill board with values.
     * @param sudokuBoard board to fill.
     */
    void solve(SudokuBoard sudokuBoard);
}
