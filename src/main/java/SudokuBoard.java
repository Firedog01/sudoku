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
    private BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();

    /**
     * Constructor. Initializes board with zeros
     */
    public SudokuBoard() {
        for (int[] row: board) {
            Arrays.fill(row, 0);
        }
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
    public void set(int x, int y, int value) { board[x][y] = value; }

    /**
     * Checks if board is valid.
     * If function returns 0 means that board is invalid.
     *
     * @return boolean
     */
    public boolean isValid() {

        // Check rows
        for (int[] row: board) {
            // Array stores information if a number was already found in row
            boolean[] checked = new boolean[9];
            Arrays.fill(checked, false);
            for (int number : row) {
                // Has the number repeated and is it in range 1-9?
                if (!checked[number - 1] && number >= 1 && number <= 9) {
                    // Row is valid so far
                    checked[number - 1] = true;
                } else {
                    // Board is invalid
                    return false;
                }
            }
        }

        //Check columns
        for (int i = 0; i < 9; i++) {
            boolean[] checked = new boolean[9];
            Arrays.fill(checked, false);
            for (int j = 0; j < 9; j++) {
                int number = get(j, i);
                if (!checked[number - 1] && number >= 1 && number <= 9) {
                    checked[number - 1] = true;
                } else {
                    return false;
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
                    if (!checked[number - 1] && number >= 1 && number <= 9) {
                        checked[number - 1] = true;
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}