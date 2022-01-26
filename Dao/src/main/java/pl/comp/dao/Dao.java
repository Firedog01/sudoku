package pl.comp.dao;

import pl.comp.exceptions.model.dao.SudokuIOexception;

public interface Dao<T> extends AutoCloseable {

    T read() throws SudokuIOexception;

    void write(T obj) throws SudokuIOexception;
}
