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
     * Array containing sudoku board.
     * Before calling method fillBoard() every cell in table is set to zero.
     */
    private int[][] board = new int[9][9];
    private SudokuSolver sudokuSolver;

    /**
     * Constructor. Initializes board with zeros
     * @param solver instance of SudokuSolver
     */
    public SudokuBoard(SudokuSolver solver) {
        for (int[] row: board) {
            Arrays.fill(row, 0);
        }
        sudokuSolver = solver;
    }

    /**
     * Function that generates filled sudoku board with use of sfuhrm.sudoku library.
     * For more info go to <a href="https://javadoc.io/doc/de.sfuhrm/sudoku/latest/index.html>this location</a>.
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
        return board[x][y];
    }

    /**
     * Sets value of a cell in sudoku board.
     *
     * @param x first coordinate
     * @param y second coordinate
     * @param value int of range 1-9
     */
    public void set(int x, int y, int value) {
        board[x][y] = value;
    }

    /**
     * Checks if board is valid.
     * If function returns 0 means that board is invalid.
     *
     * @return boolean
     */
    public boolean isValid() {
        // Check if out of range
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (get(i, j) < 1 || get(i, j) > 9) {
                    return false;
                }
            }
        }

        // Check rows
        for (int i = 0; i < 9; i++) {
            // Array stores information if a number was already found in row
            boolean[] checked = new boolean[9];
            Arrays.fill(checked, false);
            for (int j = 0; j < 9; j++) {
                int number = get(i, j);
                // Return false if number repeats
                if (checked[number - 1]) {
                    return false;
                } else { // It's valid, mark as checked
                    checked[number - 1] = true;
                }
            }
        }

        //Check columns
        for (int i = 0; i < 9; i++) {
            boolean[] checked = new boolean[9];
            Arrays.fill(checked, false);
            for (int j = 0; j < 9; j++) {
                int number = get(j, i);
                if (checked[number - 1]) {
                    return false;
                } else {
                    checked[number - 1] = true;
                }
            }
        }

        //Check blocks
        for (int n = 0; n < 3; n++) {
            for (int m = 0; m < 3; m++) {
                // For each block:
                boolean[] checked = new boolean[9];
                Arrays.fill(checked, false);
                for (int k = 0; k < 9; k++) {
                    int number = get(n * 3 + k % 3, m * 3 + k / 3);
                    if (checked[number - 1]) {
                        return false;
                    } else {
                        checked[number - 1] = true;
                    }
                }
            }
        }
        return true;
    }

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

    public String toString() {
        String ret = "┌─────────┬─────────┬─────────┐\n";
        String bar = "├─────────┼─────────┼─────────┤\n";
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) {
                ret += bar;
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0) {
                    ret += "│";
                }
                ret += " " + this.get(i, j) + " ";
            }
            ret += "│\n";
        }
        ret += "└─────────┴─────────┴─────────┘\n";
        return ret;
    }
}