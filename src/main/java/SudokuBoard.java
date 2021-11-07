/*
sources used when writing:
    https://www.w3schools.com/java
    https://www.codegrepper.com/code-examples/java/how+to+fill+a+2d+array+with+0+in+java
    https://github.com/sfuhrm/sudoku
    https://javadoc.io/doc/de.sfuhrm/sudoku/latest/index.html

    https://docs.oracle.com/javase/7/docs/api/java/util/Collection.html
    http://www.java2s.com/Tutorial/Java/0140__Collections/Createanemptycollectionobject.htm
 */

import java.util.Arrays;

public class SudokuBoard {
    /**
     * SudokuSolver interface, used to fill board with values.
     */
    private SudokuSolver sudokuSolver;

    /**
     * Array containing game board.
     * Stores object instead of just ints.
     */
    private SudokuField[][] board = new SudokuField[9][9];

    /**
     * Constructor. Initializes board with zeros and sets valid to false.
     * @param solver instance of SudokuSolver
     */
    public SudokuBoard(SudokuSolver solver) {
        sudokuSolver = solver;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = new SudokuField(0);
            }
        }
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
     *
     * @param x first coordinate
     * @param y second coordinate
     * @return int of range 0-9 representing value in given cell
     */
    public int get(int x, int y) {
        return board[x][y].getFieldValue();
    }

    /**
     * Sets value of a cell in sudoku board.
     *
     * @param x first coordinate
     * @param y second coordinate
     * @param value int of range 1-9
     */
    public void set(int x, int y, int value) {
        board[x][y].setFieldValue(value);
    }

    /**
     * Verifies if whole board fulfils rules of sudoku game.
     *
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
     *
     * @param y which row to get (0-8)
     * @return SudokuRow
     */
    public SudokuRow getRow(int y) {
        SudokuField[] fields = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            fields[i] = board[i][y];
        }
        SudokuRow row = new SudokuRow(fields);
        return row.getRow();
    }

    /**
     * Gets column at given position.
     *
     * @param x which column to get (0-8)
     * @return SudokuColumn
     */
    public SudokuColumn getColumn(int x) {
        SudokuField[] fields = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            fields[i] = board[x][i];
        }
        SudokuColumn column = new SudokuColumn(fields);
        return column.getColumn();
    }

    /**
     * Gets values from given box.
     *
     * @param x horizontal coordinate
     * @param y vertical coordinate
     * @return SudokuBox
     */
    public SudokuBox getBox(int x, int y) {
        SudokuField[] fields = new SudokuField[9];
        int k = 0;
        for (int i = x * 3; i < x * 3 + 3; i++) {
            for (int j = y * 3; j < y * 3 + 3; j++) {
                fields[k] = board[i][j];
                k++;
            }
        }
        SudokuBox box = new SudokuBox(fields);
        return box.getBox();
    }

    /**
     * Checks if different board has exactly same values.
     *
     * @param board another board to check values against this one
     * @return true if boards are the same.
     */
    public boolean equals(SudokuBoard board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.get(i, j) != board.get(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Used for displaying board in command line. has lots of unnecessary characters.
     *
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
}