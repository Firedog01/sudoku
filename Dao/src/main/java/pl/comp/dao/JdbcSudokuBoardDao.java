package pl.comp.dao;

import java.io.IOException;
import java.util.Properties;

import pl.comp.model.SudokuBoard;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard>
{
    private String saveName;

    public JdbcSudokuBoardDao(String saveName) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // jdbc:mysql://localhost:3306/baza_kompo
        } catch(ClassNotFoundException e) {
            //nothing
        }

        this.saveName = saveName;
    }

    @Override
    public SudokuBoard read() throws IOException {
        return null;
    }

    @Override
    public void write(SudokuBoard obj) throws IOException {

    }

    @Override
    public void close() throws Exception {

    }
}
