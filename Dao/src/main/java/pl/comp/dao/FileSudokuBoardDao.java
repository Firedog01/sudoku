package pl.comp.dao;

/*
sources:
    https://docs.oracle.com/javase/7/docs/api/java/io/FileInputStream.html
    https://javarevisited.blogspot.com/2014/10/right-way-to-close-inputstream-file-resource-in-java.html
    https://docs.oracle.com/javase/8/docs/api/java/lang/AutoCloseable.html
    https://www.baeldung.com/java-finalize
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.comp.exceptions.model.dao.SudokuFileException;
import pl.comp.exceptions.model.dao.SudokuIOexception;
import pl.comp.model.SudokuBoard;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {
    private static Logger logger = LoggerFactory.getLogger(FileSudokuBoardDao.class);
    private final ResourceBundle bundle = ResourceBundle.getBundle("Lang", Locale.getDefault());
    private final String fileName;

    FileSudokuBoardDao(String fileName) throws SudokuFileException {
        this.fileName = fileName;
    }

    @Override
    public SudokuBoard read() throws SudokuIOexception {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            SudokuBoard board = (SudokuBoard) ois.readObject();
            return board;
        } catch (IOException e) {
            logger.info(bundle.getString("log.dao.ioEx"));
            throw new SudokuIOexception("exception.io", e);
        } catch (Exception e) {
            logger.info(bundle.getString("log.ex"));
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void write(SudokuBoard obj) throws SudokuIOexception {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(obj);
        } catch (IOException e) {
            logger.info(bundle.getString("log.dao.ioEx"));
            throw new SudokuIOexception("exception.io", e);
        }
    }

    @Override
    public void close() {
        logger.info(bundle.getString("log.dao.closing"));
    }
}
