package pl.comp.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.log4j.BasicConfigurator;
import pl.comp.exceptions.OutOfRangeCoordsException;


public class Greet {
    private static Logger logger = LoggerFactory.getLogger(Greet.class);

    public static void main(String[] args) throws OutOfRangeCoordsException {
//        BasicConfigurator.configure();
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();
        //System.out.println(board);
        logger.info(board.toString());
    }
}
