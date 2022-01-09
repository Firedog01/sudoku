package pl.comp.javafx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import pl.comp.dao.Dao;
import pl.comp.dao.SudokuBoardDaoFactory;
import pl.comp.model.SudokuBoard;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

//two-way binding
//https://developer.android.com/topic/libraries/data-binding/two-way#java
public class GameController implements Initializable {

    private SudokuBoard board;

    private SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();

    private TextField[][] fields = new TextField[9][9];

    private StringProperty[][] value = new SimpleStringProperty[9][9];

    @FXML
    private HBox GameHBox;

    @FXML
    private GridPane grid = new GridPane();

    protected void initData(SudokuBoard board) {
        this.board = board;

        grid.setGridLinesVisible(true);
        updateFields();
    }

    private void updateFields() {
        grid.getChildren().removeAll();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = new TextField();
                textField.setMinSize(40, 40);
                textField.setMaxSize(40, 40);

                if (board.get(i, j) != 0) {
                    textField.setText(Integer.toString(board.get(i, j)));
                }
                grid.add(textField, i, j);
            }
        }
    }

    @FXML
    protected void onFieldChanged(KeyEvent event) {
        System.out.println(event.getText());
    }

    @FXML
    protected void load(ActionEvent event) throws IOException {
        try (Dao<SudokuBoard> fileDao = factory.getFileDao("board")) {
            board = fileDao.read();
        } catch (Exception e){
            e.printStackTrace();
        }
        updateFields();
    }

    @FXML
    protected void save(ActionEvent event) throws IOException {
        try (Dao<SudokuBoard> fileDao = factory.getFileDao("board")) {
            fileDao.write(board);
            SudokuBoard board2 = fileDao.read();
            System.out.println(board2);
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
