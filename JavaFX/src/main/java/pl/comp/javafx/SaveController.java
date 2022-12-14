package pl.comp.javafx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.comp.dao.Dao;
import pl.comp.dao.SudokuBoardDaoFactory;
import pl.comp.model.SudokuBoard;

public class SaveController implements Initializable {

    private SudokuBoard board;

    protected void initData(SudokuBoard board) {
        this.board = board;
    }

    @FXML
    private TextField boardName;

    @FXML
    protected void save(ActionEvent event) {
        String name = boardName.getText();
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        try (Dao<SudokuBoard> jdbcDao = factory.getDbDao(name)) {
            jdbcDao.write(board);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) boardName.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
