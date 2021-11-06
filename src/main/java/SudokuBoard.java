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
//    /**
//     * Array containing sudoku board.
//     * Before calling method fillBoard() every cell in table is set to zero.
//     */
//    private int[][] board = new int[9][9];

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
     * Constructor. Initializes board with zeros
     * @param solver instance of SudokuSolver
     */
    public SudokuBoard(SudokuSolver solver) {
        sudokuSolver = solver;

        SudokuRow[] newRows = new SudokuRow[9];
        SudokuColumn[] newColumns = new SudokuColumn[9];
        SudokuBox[][] newBoxes = new SudokuBox[3][3];

        for(int i = 0; i < 9; i++) {
            newRows[i] = new SudokuRow();
        }
        for(int i = 0; i < 9; i++) {
            newColumns[i] = new SudokuColumn();
        }
        for(int i = 0; i < 9; i++) {
            newBoxes[i / 3][i % 3] = new SudokuBox();
        }

        /*
          0 1 2
        0   |
        1   |
        2 - +

        row: 2
        col: 1
        board[1][2]
         */
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = new SudokuField();
                board[i][j].setRow(newRows[j]);
                board[i][j].setColumn(newColumns[i]);
                board[i][j].setBox(newBoxes[i / 3][j / 3]);

                newRows[i].setField(j, board[i][j]);
                newColumns[j].setField(i, board[i][j]);
                newBoxes[i / 3 ][j / 3].setField(i % 3 + (j % 3) * 3, board[i][j]);
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j].getRow().get(j));
            }
            System.out.print("\n");
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
     * Gets row at given position.
     *
     * @param y which row to get (0-8)
     * @return SudokuRow
     */
    public SudokuRow getRow(int y) {
        return board[0][y].getRow();
    }

    /**
     * Gets column at given position.
     *
     * @param x which column to get (0-8)
     * @return SudokuColumn
     */
    public SudokuColumn getColumn(int x) {
        return board[x][0].getColumn();
    }

    /**
     * Gets values from one box.
     *
     * @param x horizontal coordinate
     * @param y vertical coordinate
     * @return SudokuBox
     */
    public SudokuBox getBox(int x, int y) {
        return board[x * 3][y * 3].getBox();
    }

    /**
     * Verifies if whole board fulfils rules of sudoku game
     *
     * @return true if it is valid.
     */
    public boolean isValid() {
        boolean check;
        for (int i = 0; i < 9; i++) {
            if (!getRow(i).verify()) {
                return false;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (getColumn(i).verify()) {
                return false;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (getBox(i / 3, i % 3).verify()) {
                return false;
            }
        }
        return true;
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