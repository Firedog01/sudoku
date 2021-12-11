package pl.comp.model;/*
sources:
    https://www.w3schools.com/java
    https://www.codegrepper.com/code-examples/java/how+to+fill+a+2d+array+with+0+in+java
    https://github.com/sfuhrm/sudoku
    https://javadoc.io/doc/de.sfuhrm/sudoku/latest/index.html
    https://docs.oracle.com/javase/7/docs/api/java/util/Collection.html
    http://www.java2s.com/Tutorial/Java/0140__Collections/Createanemptycollectionobject.htm
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class SudokuBoard implements Serializable, Cloneable {
    /**
     * pl.comp.model.SudokuSolver interface, used to fill board with values.
     */
    private SudokuSolver sudokuSolver;

    /**
     * Array containing game board.
     * Stores object instead of just ints.
     */
    private List<List<SudokuField>> board;

    /**
     * Constructor. Initializes board with zeros and sets valid to false.
     * @param solver instance of pl.comp.model.SudokuSolver
     */
    public SudokuBoard(SudokuSolver solver) {
        sudokuSolver = solver;
        List<List<SudokuField>> newBoard = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            newBoard.add(new ArrayList<>());
            for (int j = 0; j < 9; j++) {
                newBoard.get(i).add(new SudokuField(0));
            }
        }
        board = List.copyOf(newBoard);
    }

    public String getSolver() {
        return String.valueOf(sudokuSolver);
    }

    /**
     * Call to sudoku solver. Fills whole board with values
     */
    public void solveGame() {
        sudokuSolver.solve(this);
    }

    /**
     * Returns value of a cell in sudoku board.
     * If function returns 0 means that given cell is empty.
     * @param x first coordinate
     * @param y second coordinate
     * @return int of range 0-9 representing value in given cell
     */
    public int get(int x, int y) {
        return board.get(x).get(y).getFieldValue();
    }

    /**
     * Sets value of a cell in sudoku board.
     * @param x first coordinate
     * @param y second coordinate
     * @param value int of range 1-9
     */
    public void set(int x, int y, int value) {
        board.get(x).get(y).setFieldValue(value);
    }

    /**
     * Verifies if whole board fulfils rules of sudoku game.
     * @return true if it is valid.
     */
    public boolean checkBoard() {
        for (int i = 0; i < 9; i++) {
            if (!getRow(i).isValid()) {
                return false;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (!getColumn(i).isValid()) {
                return false;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (!getBox(i / 3, i % 3).isValid()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gets row at given position.
     * @param y which row to get (0-8)
     * @return pl.comp.model.SudokuRow
     */
    public SudokuRow getRow(int y) {
        List<SudokuField> fields = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            fields.add(board.get(i).get(y));
        }
        return new SudokuRow(fields);
    }

    /**
     * Gets column at given position.
     * @param x which column to get (0-8)
     * @return pl.comp.model.SudokuColumn
     */
    public SudokuColumn getColumn(int x) {
        List<SudokuField> fields = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            fields.add(board.get(x).get(i));
        }
        return new SudokuColumn(fields);
    }

    /**
     * Gets values from given box.
     * @param x horizontal coordinate
     * @param y vertical coordinate
     * @return pl.comp.model.SudokuBox
     */
    public SudokuBox getBox(int x, int y) {
        List<SudokuField> fields = new ArrayList<>();
        for (int i = x * 3; i < x * 3 + 3; i++) {
            for (int j = y * 3; j < y * 3 + 3; j++) {
                fields.add(board.get(i).get(j));
            }
        }
        return new SudokuBox(fields);
    }

    /**
     * Used for displaying board in command line. has lots of unnecessary characters.
     * @return neatly formatted board
     */
    public String toString() {
        StringBuilder ret = new StringBuilder("┌─────────┬─────────┬─────────┐\n");
        String bar = "├─────────┼─────────┼─────────┤\n";
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) {
                ret.append(bar);
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0) {
                    ret.append("│");
                }
                ret.append(" ").append(this.get(i, j)).append(" ");
            }
            ret.append("│\n");
        }
        ret.append("└─────────┴─────────┴─────────┘\n");
        return ret.toString();
    }

    /**
     * Checks if different board has exactly same values.
     * @param obj another board to check values against this one
     * @return true if boards are the same.
     */
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        SudokuBoard rhs = (SudokuBoard) obj;
        return new EqualsBuilder()
                .append(sudokuSolver, rhs.sudokuSolver)
                .append(board, rhs.board)
                .isEquals();
    }

    /**
     * Returns hashcode of pl.comp.model.SudokuBoard object.
     * @return hashcode.
     */
    public int hashCode() {
        return new HashCodeBuilder(13, 99)
                .append(sudokuSolver)
                .append(board)
                .toHashCode();
    }

    public SudokuBoard clone() {
        SudokuBoard clone = new SudokuBoard(sudokuSolver);
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                clone.set(x, y, this.get(x, y));
            }
        }
        return clone;
    }
}