package pl.comp.dao;

import java.io.IOException;

public interface Dao<T> extends AutoCloseable {

    T read() throws IOException;

    void write(T obj) throws IOException;
}
