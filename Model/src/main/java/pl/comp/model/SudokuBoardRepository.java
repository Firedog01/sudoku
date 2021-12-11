package pl.comp.model;

public class SudokuBoardRepository {

    private SudokuBoard mainBoard;

    public SudokuBoardRepository(SudokuSolver solver) {
        mainBoard = new SudokuBoard(solver);
    }

    public SudokuBoard createInstance() {
        return mainBoard.clone();
    }
}
