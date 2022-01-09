package pl.comp.model;
/*
sources:
    https://www.w3schools.com/java
    https://www.codegrepper.com/code-examples/java/how+to+fill+a+2d+array+with+0+in+java
    https://github.com/sfuhrm/sudoku
    https://javadoc.io/doc/de.sfuhrm/sudoku/latest/index.html
    https://docs.oracle.com/javase/7/docs/api/java/util/Collection.html
    http://www.java2s.com/Tutorial/Java/0140__Collections/Createanemptycollectionobject.htm
 */

import java.io.Serializable;
import java.util.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.comp.exceptions.OutOfRangeCoordsException;
import pl.comp.exceptions.SudokuCloneException;
import pl.comp.exceptions.UnfilledBoardException;

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

    private static Logger logger = LoggerFactory.getLogger(SudokuBoard.class);

    private ResourceBundle bundle = ResourceBundle.getBundle("Lang",Locale.getDefault());

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

    public void setSolver(SudokuSolver solver) {
        sudokuSolver = solver;
    }

    /**
     * Call to sudoku solver. Fills whole board with values
     */
    public void solveGame() throws OutOfRangeCoordsException{
        sudokuSolver.solve(this);
    }

    /**
     * Cuts some of fields depedning on difficulty.
     *  Easy - 33
     *  Medium - 25
     *  Hard - 17
     * @param d enum describing difficulty.
     * @throws RuntimeException if board is not filled
     */
    public void createPuzzle(Difficulty d) throws UnfilledBoardException, OutOfRangeCoordsException {
        if (!checkBoard()) {
            throw new UnfilledBoardException("exception.unfilled");
        }
        deleteFields(81 - d.value);
    }

    /**
     * Deletes (sets to 0) given number of fields in board.
     * Does not check if value of field before deletion was set.
     * @param n number of fields to delete
     */
    private void deleteFields(int n) throws OutOfRangeCoordsException {
        Set<List<Integer>> coords = new HashSet<>();
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            List<Integer> newCoord;
            do {
                Integer x = (Integer) rand.nextInt(9);
                Integer y = (Integer) rand.nextInt(9);
                newCoord = Arrays.asList(x, y);
            } while (coords.contains(newCoord));
            coords.add(newCoord);
        }
        for (List<Integer> coord: coords) {
            int x = coord.get(0);
            int y = coord.get(1);
            set(x, y, 0);
        }
    }

    /**
     * Returns value of a cell in sudoku board.
     * If function returns 0 means that given cell is empty.
     * @param x first coordinate
     * @param y second coordinate
     * @return int of range 0-9 representing value in given cell
     */
    public int get(int x, int y) throws OutOfRangeCoordsException {
        if (x > 8 || x < 0 || y > 8 || y < 0) {
            throw new OutOfRangeCoordsException("exception.coord");
        }
        return board.get(x).get(y).getFieldValue();
    }

    /**
     * Sets value of a cell in sudoku board.
     * @param x first coordinate
     * @param y second coordinate
     * @param value int of range 1-9
     */
    public void set(int x, int y, int value) throws OutOfRangeCoordsException{
        if (x > 8 || x < 0 || y > 8 || y < 0) {
            throw new OutOfRangeCoordsException("exception.coord");
        }
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
                try{
                    ret.append(" ").append(this.get(i, j)).append(" ");
                } catch (OutOfRangeCoordsException e) {
                    logger.info(bundle.getString("log.coord"));
                }
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

    public SudokuBoard clone() throws CloneNotSupportedException{
        try {
            SudokuSolver solverClone = sudokuSolver.clone();
            SudokuBoard clone = new SudokuBoard(solverClone);
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 9; y++) {
                    clone.set(x, y, this.get(x, y));
                }
            }
            return clone;
        } catch (CloneNotSupportedException e) {
            logger.info(bundle.getString("log.cloneEx"));
            throw new SudokuCloneException("exception.clone", e);
        } catch (OutOfRangeCoordsException e) {
            logger.info(bundle.getString("exception.coord"));
            throw new SudokuCloneException("exception.clone", e);
        }
    }
}