package pl.comp.javafx;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import pl.comp.model.SudokuBoard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//two-way binding
//https://developer.android.com/topic/libraries/data-binding/two-way#java
public class GameController implements Initializable {

    private SudokuBoard board;

    private TextField[][] fields = new TextField[9][9];

    private StringProperty[][] value = new SimpleStringProperty[9][9];

    @FXML
    private HBox GameHBox;

    protected void initData(SudokuBoard board) {
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = new TextField();
                if (board.get(i, j) != 0) {
                    textField.setText(Integer.toString(board.get(i, j)));
                }
                //textField.textProperty().bindBidirectional();
                textField.setMinSize(40, 40);
                textField.setMaxSize(40, 40);
                grid.add(textField, i, j);
            }
        }
        GameHBox.getChildren().add(grid);
    }

    @FXML
    protected void onFieldChanged(ActionEvent event) {

    }

    @FXML
    protected void load(ActionEvent event) throws IOException {

    }

    @FXML
    protected void save(ActionEvent event) throws IOException {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
