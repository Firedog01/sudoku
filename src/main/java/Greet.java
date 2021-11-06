public class Greet {
    public static void main(String[] args) {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();
        SudokuRow row = board.getRow(0);
        for (int i = 0; i < 9; i++) {
            System.out.println(row.get(i));
        }
        System.out.println(board);
    }
}
