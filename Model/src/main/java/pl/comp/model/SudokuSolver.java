package pl.comp.model;

import java.io.Serializable;
import pl.comp.exceptions.model.OutOfRangeCoordsException;

public interface SudokuSolver extends Serializable, Cloneable {
    /**
     * Function to fill board with values.
     * @param sudokuBoard board to fill.
     * @throws OutOfRangeCoordsException if internal error occurs
     */
    void solve(SudokuBoard sudokuBoard) throws OutOfRangeCoordsException;

    SudokuSolver clone() throws CloneNotSupportedException;
}
