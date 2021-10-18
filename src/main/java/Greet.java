public class Greet {
    public static void main(String[] args) {
        SudokuBoard board = new SudokuBoard();
        board.fillBoard();
        if (board.isValid()) {
            System.out.println("Sudoku board is valid");
            System.out.println(displayBoard(board));
        } else {
            System.out.println("Sudoku board is not valid");
        }

    }

    private static String displayBoard(SudokuBoard board) {
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
                ret += " " + board.get(i, j) + " ";
            }
            ret += "│\n";
        }
        ret += "└─────────┴─────────┴─────────┘\n";
        return ret;
    }
}
