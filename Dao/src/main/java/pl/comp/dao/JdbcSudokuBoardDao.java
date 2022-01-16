package pl.comp.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.comp.model.BacktrackingSudokuSolver;
import pl.comp.model.Greet;
import pl.comp.model.SudokuBoard;
import pl.comp.model.SudokuBoardRepository;

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
        SudokuBoardRepository repository = new SudokuBoardRepository(new BacktrackingSudokuSolver());
        try {
            SudokuBoard ret_board = repository.createInstance();
            try (Statement stmt = con.createStatement()) {
                //start transaction
                String autocommitQuery = "set autocommit = 0;";
                stmt.execute(autocommitQuery);
                String beginTransaction = "START TRANSACTION;";
                stmt.execute(beginTransaction);

                //end transaction
                String commitTransaction = "commit;";
                stmt.execute(commitTransaction);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return ret_board;
        } catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void write(SudokuBoard obj)  {
        try (Statement stmt = con.createStatement()) {
            //start transaction
            String autocommitQuery = "set autocommit = 0;";
            stmt.execute(autocommitQuery);
            String beginTransaction = "start transaction;";
            stmt.execute(beginTransaction);

            String gameQuery = String.format("INSERT into game (board_name) values ('%s');", saveName);
            stmt.execute(gameQuery);

            String getIdQuery = String.format("Select id from game where board_name = '%s' limit 1;", saveName);
            try (ResultSet rs = stmt.executeQuery(getIdQuery)) {
                rs.next();
                int id = rs.getInt("id");
                logger.debug(String.valueOf(id));

                String deleteQuery = String.format("delete from cell_value where board_id = %d;", id);
                stmt.execute(deleteQuery);

                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        int value = obj.get(i, j);
                        if (value != 0) {
                            String cellQuery = String.format("insert into cell_value (board_id, value, x, y) " +
                                    "values (%d, %d, %d, %d);", id, value, i, j);
                            stmt.execute(cellQuery);
                        }
                    }
                }
            //end transaction
            String commitTransaction = "commit;";
            stmt.execute(commitTransaction);

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
