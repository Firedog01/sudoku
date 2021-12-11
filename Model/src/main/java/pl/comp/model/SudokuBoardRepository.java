package pl.comp.model;

public class SudokuBoardRepository {

    SudokuBoard main_board;

    public SudokuBoardRepository(SudokuSolver solver) {
        main_board = new SudokuBoard(solver);
    }

    public SudokuBoard createInstance() {
        return main_board.clone();
    }
}
