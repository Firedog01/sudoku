package pl.comp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.comp.exceptions.model.OutOfRangeCoordsException;
import pl.comp.exceptions.model.SudokuCloneException;
import pl.comp.exceptions.model.dao.SudokuSqlException;
import pl.comp.model.BacktrackingSudokuSolver;
import pl.comp.model.SudokuBoard;
import pl.comp.model.SudokuBoardRepository;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard>
{
    private static Logger logger = LoggerFactory.getLogger(JdbcSudokuBoardDao.class);
    private String saveName;
    private Connection con = null;

    public JdbcSudokuBoardDao(String saveName) throws SudokuSqlException {
        this.saveName = saveName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/baza_kompo", "root", "");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        } catch(SQLException e) {
            throw new SudokuSqlException("exception.sql", e);
        }
    }

    @Override
    public SudokuBoard read() {
        SudokuBoardRepository repository = new SudokuBoardRepository(new BacktrackingSudokuSolver());
        try {
            SudokuBoard retBoard = repository.createInstance();
            try (Statement stmt = con.createStatement()) {
                String getCellsQuery = String.format("select value, x, y from cell_value c " +
                        "inner join game g on c.board_id = g.id " +
                        "where g.board_name = '%s';", saveName);
                try (ResultSet rs = stmt.executeQuery(getCellsQuery)) {
                    while (rs.next()) {
                        int value = rs.getInt("value");
                        int x = rs.getInt("x");
                        int y = rs.getInt("y");
                        retBoard.set(x, y, value);
                    }
                    return retBoard;
                } catch (OutOfRangeCoordsException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                SudokuSqlException se = new SudokuSqlException("excepton.sql", e);
                se.printStackTrace();
            }

        } catch(CloneNotSupportedException e) {
            SudokuCloneException se = new SudokuCloneException("exception.clone", e);
            se.printStackTrace();
        }
        return null;
    }

    @Override
    public void write(SudokuBoard obj) {
        try (Statement stmt = con.createStatement()) {
            //start transaction
            stmt.execute("set autocommit = 0;");
            stmt.execute("start transaction;");

            String getIdQuery = String.format("select id from game where board_name = '%s' limit 1;", saveName);
            try (ResultSet rs = stmt.executeQuery(getIdQuery)) {
                int id;
                if (!rs.next()) {
                    String gameQuery = String.format("insert into game (board_name) values ('%s');", saveName);
                    stmt.execute(gameQuery);
                    try (ResultSet rs_ = stmt.executeQuery(getIdQuery)) {
                        rs_.next();
                        id = rs_.getInt("id");
                    }

                } else {
                    id = rs.getInt("id");
                }

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
            stmt.execute("commit;");

            } catch (OutOfRangeCoordsException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            SudokuSqlException se = new SudokuSqlException("exception.sql", e);
            se.printStackTrace();
        }
    }

    @Override
    public void close() throws SudokuSqlException {
        try {
            con.close();
        } catch (SQLException e) {
            throw new SudokuSqlException("exception.sql", e);
        }
    }
}
