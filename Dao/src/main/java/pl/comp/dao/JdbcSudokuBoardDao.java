package pl.comp.dao;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.sql.Connection;
import pl.comp.model.SudokuBoard;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard>
{
    private String saveName;
    private Connection con = null;

    public JdbcSudokuBoardDao(String saveName) {
        this.saveName = saveName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/baza_kompo", "root", "");
        } catch(ClassNotFoundException e) {
            //nothing
        } catch(SQLException e) {
            //another nothing
        }
    }

    @Override
    public SudokuBoard read() throws IOException {
        return null;
    }

    @Override
    public void write(SudokuBoard obj) throws IOException {
        try (Statement stmt = con.createStatement()) {
            String query = String.format("INSERT into game (board_name) values ('%s');", saveName);
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        con.close();
    }
}
