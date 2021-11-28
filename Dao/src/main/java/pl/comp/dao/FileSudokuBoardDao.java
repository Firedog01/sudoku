package pl.comp.dao;

/*
sources:
    https://docs.oracle.com/javase/7/docs/api/java/io/FileInputStream.html
    https://javarevisited.blogspot.com/2014/10/right-way-to-close-inputstream-file-resource-in-java.html
    https://docs.oracle.com/javase/8/docs/api/java/lang/AutoCloseable.html
    https://www.baeldung.com/java-finalize
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import pl.comp.model.BacktrackingSudokuSolver;
import pl.comp.model.SudokuBoard;
import pl.comp.model.SudokuSolver;

public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {
    private final FileInputStream fis;
    private final FileOutputStream fos;

    FileSudokuBoardDao(String fileName) throws FileNotFoundException {
        fis = new FileInputStream(fileName);
        fos = new FileOutputStream(fileName);
    }

    @Override
    public SudokuBoard read() throws IOException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int val = fis.read();
                board.set(i, j, val);
            }
        }
        return board;
    }

    @Override
    public void write(SudokuBoard obj) throws IOException {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int current = obj.get(i, j);
                fos.write(current);
            }
        }
    }

    @Override
    public void close() throws IOException {
        fis.close();
        fos.close();
    }
}
