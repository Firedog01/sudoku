package pl.comp.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.comp.exceptions.model.OutOfRangeCoordsException;
import pl.comp.model.SudokuBoard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbHelper {

    private static Logger logger = LoggerFactory.getLogger(JdbcSudokuBoardDao.class);
    Connection con = null;

    public DbHelper () {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/baza_kompo", "root", "");
        } catch(ClassNotFoundException e) {
            //nothing pakujemy
        } catch(SQLException e) {
            //another nothing
        }
    }

    public List<String> getBoardNames () {
        List<String> names = new ArrayList<String>();
        try (Statement stmt = con.createStatement()) {
            String getCellsQuery = String.format("select board_name from game;");
            try (ResultSet rs = stmt.executeQuery(getCellsQuery)) {
                while (rs.next()) {
                    names.add(rs.getString("board_name"));
                }
                return names;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
