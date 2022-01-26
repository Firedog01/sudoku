package pl.comp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.comp.exceptions.model.dao.SudokuSqlException;

public class DbHelper {

    private static Logger logger = LoggerFactory.getLogger(JdbcSudokuBoardDao.class);
    Connection con = null;

    public DbHelper () {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/baza_kompo", "root", "");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        } catch(SQLException e) {
            SudokuSqlException se = new SudokuSqlException("exception.sql", e);
            se.printStackTrace();
        }
    }

    public List<String> getBoardNames () {
        List<String> names = new ArrayList<String>();
        try (Statement stmt = con.createStatement()) {
            String getCellsQuery = String.format("select board_name from game;");
            ResultSet rs = stmt.executeQuery(getCellsQuery);
            while (rs.next()) {
                names.add(rs.getString("board_name"));
            }
            return names;
        } catch (SQLException e) {
            SudokuSqlException se = new SudokuSqlException("exception.sql", e);
            se.printStackTrace();
        }

        return null;
    }

}
