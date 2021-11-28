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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import pl.comp.model.SudokuBoard;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {
    private final String fileName;

    FileSudokuBoardDao(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
    }

    @Override
    public SudokuBoard read() throws IOException {
        SudokuBoard board = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            board = (SudokuBoard) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return board;
    }

    @Override
    public void write(SudokuBoard obj) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(obj);
        }
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing!");
    }
}
