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

    private SudokuField[][] fields;

    /**
     * Constructor. Initializes board with zeros
     * @param solver instance of SudokuSolver
     */
    public SudokuBoard(SudokuSolver solver) {
        sudokuSolver = solver;

        for (int[] row: board) {
            Arrays.fill(row, 0);
        }

        SudokuRow[] newRows = new SudokuRow[9];
        SudokuColumn[] newColumns = new SudokuColumn[9];
        SudokuBox[] newBoxes = new SudokuBox[9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                fields[i][j] = new SudokuField(this, newRows[i],
                        newColumns[j], newBoxes[i / 3 + (j / 3) * 3]);
                newRows[i].addField(j, fields[i][j]);
                newColumns[j].addField(i, fields[i][j]);
                newBoxes[i / 3 + (j / 3) * 3].addField(i % 3 + (j % 3) * 3, fields[i][j]);
            }
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
        return fields[x][y].getFieldValue();
    }

    /**
     * Sets value of a cell in sudoku board.
     *
     * @param x first coordinate
     * @param y second coordinate
     * @param value int of range 1-9
     */
    public void set(int x, int y, int value) {
        fields[x][y].setFieldValue(value);
    }

    /**
     * Gets row at given position.
     *
     * @param y which row to get (0-8)
     * @return SudokuRow
     */
    public SudokuRow getRow(int y) {
        return fields[y][0].getRow();
    }

    /**
     * Gets column at given position.
     *
     * @param x which column to get (0-8)
     * @return SudokuColumn
     */
    public SudokuColumn getColumn(int x) {
        return fields[0][x].getColumn();
    }

    /**
     * Gets values from one box.
     *
     * @param x horizontal coordinate
     * @param y vertical coordinate
     * @return SudokuBox
     */
    public SudokuBox getBox(int x, int y) {
        return fields[x * 3][y * 3].getBox();
    }

    public boolean isValid() {
        for (int i = 0; i < 9; i++) { // Check if out of range
            for (int j = 0; j < 9; j++) {
                if (get(i, j) < 1 || get(i, j) > 9) {
                    return false;
                }
            }
        }

        for (int i = 0; i < 9; i++) { // Check row and cols
            boolean[] checkedRow = new boolean[9];
            boolean[] checkedCol = new boolean[9];
            Arrays.fill(checkedRow, false);
            Arrays.fill(checkedCol, false);
            for (int j = 0; j < 9; j++) {
                int rowNum = get(i, j);
                int colNum = get(j, i);
                if (checkedRow[rowNum - 1] || checkedCol[colNum - 1]) {
                    return false;
                } else {
                    checkedRow[rowNum - 1] = true;
                    checkedCol[colNum - 1] = true;
                }
            }
        }

        for (int n = 0; n < 3; n++) { //Check blocks
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