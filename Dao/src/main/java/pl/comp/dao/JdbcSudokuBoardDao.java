package pl.comp.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.comp.model.Greet;
import pl.comp.model.SudokuBoard;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard>
{
    private static Logger logger = LoggerFactory.getLogger(JdbcSudokuBoardDao.class);
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
    public void write(SudokuBoard obj)  {
        try (Statement stmt = con.createStatement()) {
            //start transaction
            String query = String.format("INSERT into game (board_name) values ('%s');", saveName);
            stmt.execute(query);
            String getIdQuery = "Select max(id) as id from game limit 1;";
            try (ResultSet rs = stmt.executeQuery(getIdQuery)) {
                rs.next();
                int id = rs.getInt("id");
                logger.debug(String.valueOf(id));
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        int value = obj.get(i, j);
                        String cellQuery = String.format("Inster into cell_value (board_id, value, x, y) " +
                                "values (%d, %d, %d, %d)", id, value, i, j);
                        stmt.execute(cellQuery);
                    }
                }
            //end transaction
            } catch (Exception e) {
                logger.debug(e.toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        con.close();
    }
}
