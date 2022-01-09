package pl.comp.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.comp.exceptions.model.NoSolverException;
import pl.comp.exceptions.model.OutOfRangeCoordsException;


public class Greet {
    private static Logger logger = LoggerFactory.getLogger(Greet.class);

    public static void main(String[] args) throws OutOfRangeCoordsException, NoSolverException {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();
        logger.info(board.toString());
    }
}
