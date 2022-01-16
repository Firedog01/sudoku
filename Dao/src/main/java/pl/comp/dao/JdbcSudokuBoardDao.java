package pl.comp.dao;

import pl.comp.model.SudokuBoard;

import java.io.IOException;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard>
{
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
