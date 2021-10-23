public class Greet {
    public static void main(String[] args) {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();
        if (board.isValid()) {
            System.out.println("Sudoku board is valid");
            System.out.println(board.toString());
        } else {
            System.out.println("Sudoku board is not valid");
        }
    }
}
