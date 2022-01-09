package pl.comp.model;

import de.sfuhrm.sudoku.Creator;
import de.sfuhrm.sudoku.GameMatrix;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import pl.comp.exceptions.OutOfRangeCoordsException;

public class BacktrackingSudokuSolver implements SudokuSolver {
    /**
     * Function that generates filled pl.comp.model.SudokuBoard with use of sfuhrm.sudoku library.
     * For more info go to <a href="https://javadoc.io/doc/de.sfuhrm/sudoku/latest/index.html>this location</a>.
     * @param board pl.comp.model.SudokuBoard to solve
     */
    public void solve(SudokuBoard board) throws OutOfRangeCoordsException {
        GameMatrix matrix = Creator.createFull();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board.set(i, j, matrix.get(i, j));
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        return new EqualsBuilder()
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(13, 47)
                .toHashCode();
    }

    @Override
    public BacktrackingSudokuSolver clone() throws CloneNotSupportedException {
        return (BacktrackingSudokuSolver) super.clone();
    }
}
