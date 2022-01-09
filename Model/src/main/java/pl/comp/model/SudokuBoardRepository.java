package pl.comp.model;

import pl.comp.exceptions.SudokuCloneException;

public class SudokuBoardRepository {

    private SudokuBoard mainBoard;

    public SudokuBoardRepository(SudokuSolver solver) {
        mainBoard = new SudokuBoard(solver);
    }

    public SudokuBoard createInstance() throws CloneNotSupportedException {
        return mainBoard.clone();
    }
}
