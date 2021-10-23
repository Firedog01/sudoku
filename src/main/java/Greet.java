public class Greet {
    public static void main(String[] args) {

//        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
//        board.solveGame();
//        if (board.isValid()) {
//            System.out.println("Sudoku board is valid");
//            System.out.println(board.toString());
//        } else {
//            System.out.println("Sudoku board is not valid");
//        }

        SudokuBoard board0 = new SudokuBoard(new BacktrackingSudokuSolver());
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board0.set(i, j, i+1);
            }
        }
        System.out.println(board0);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board0.set(i, j, j+1);
            }
        }
        System.out.println(board0);
    }
}
