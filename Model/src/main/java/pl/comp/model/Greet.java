package pl.comp.model;

public class Greet {
    public static void main(String[] args) {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();
        System.out.println(board);
    }
}
