package pl.comp.model;

import pl.comp.exceptions.model.OutOfRangeCoordsException;

import java.io.Serializable;

public interface SudokuSolver extends Serializable, Cloneable {
    /**
     * Function to fill board with values.
     * @param sudokuBoard board to fill.
     */
    void solve(SudokuBoard sudokuBoard) throws OutOfRangeCoordsException;

    SudokuSolver clone() throws CloneNotSupportedException;
}
