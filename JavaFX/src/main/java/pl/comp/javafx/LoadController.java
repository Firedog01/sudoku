package pl.comp.javafx;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import pl.comp.dao.Dao;
import pl.comp.dao.DbHelper;
import pl.comp.dao.SudokuBoardDaoFactory;
import pl.comp.exceptions.model.OutOfRangeCoordsException;
import pl.comp.model.SudokuBoard;

public class LoadController implements Initializable {

    private SudokuBoard board;

    private GameController gameController;

    @FXML
    private MenuButton menuBoard;

    public void initData(SudokuBoard board, GameController gameController) {
        this.board = board;
        this.gameController = gameController;
    }

    @FXML
    protected void load(ActionEvent event) throws OutOfRangeCoordsException {
        String name = menuBoard.getText();
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        try (Dao<SudokuBoard> jdbcDao = factory.getDbDao(name)) {
            board = jdbcDao.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
        gameController.setBoard(board);
        Stage stage = (Stage) menuBoard.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DbHelper helper = new DbHelper();
        List<String> boardNames = helper.getBoardNames();
        for (String name : boardNames) {
            MenuItem menuItem = new MenuItem();
            menuItem.setText(name);
            menuItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    menuBoard.setText(menuItem.getText());
                }
            });
            menuBoard.getItems().add(menuItem);
        }
    }

}
