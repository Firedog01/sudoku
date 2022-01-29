package pl.comp.javafx;

import pl.comp.exceptions.model.OutOfRangeCoordsException;
import pl.comp.model.SudokuBoard;
import pl.comp.model.SudokuField;

public class SudokuFieldHelper {
    private SudokuBoard board;
    private int rowIdx;
    private int colIdx;

    public SudokuFieldHelper(SudokuBoard board, int rowIdx, int colIdx) {
        this.board = board;
        this.rowIdx = rowIdx;
        this.colIdx = colIdx;
    }

    public String getField() throws OutOfRangeCoordsException {
        int ret = board.get(rowIdx, colIdx);
        if (ret != 0) {
            return String.valueOf(ret);
        } else {
            return "";
        }
    }

    public void setField(String val) throws OutOfRangeCoordsException {
        if(val.matches("^[1-9]$")) {
            board.set(rowIdx, colIdx, Integer.parseInt(val));
        }
    }
}
