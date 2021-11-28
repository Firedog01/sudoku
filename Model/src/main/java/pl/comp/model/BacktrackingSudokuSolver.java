package pl.comp.model;

import de.sfuhrm.sudoku.Creator;
import de.sfuhrm.sudoku.GameMatrix;

public class BacktrackingSudokuSolver implements SudokuSolver {
    /**
     * Function that generates filled pl.comp.model.SudokuBoard with use of sfuhrm.sudoku library.
     * For more info go to <a href="https://javadoc.io/doc/de.sfuhrm/sudoku/latest/index.html>this location</a>.
     * @param board pl.comp.model.SudokuBoard to solve
     */
    public void solve(SudokuBoard board) {
        GameMatrix matrix = Creator.createFull();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board.set(i, j, matrix.get(i, j));
            }
        }
    }
}
