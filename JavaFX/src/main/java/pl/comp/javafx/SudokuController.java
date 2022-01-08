package pl.comp.javafx;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.comp.model.BacktrackingSudokuSolver;
import pl.comp.model.Difficulty;
import pl.comp.model.SudokuBoard;

public class SudokuController implements Initializable {

    SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());

    ResourceBundle bundle;

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private MenuItem polishItem;

    @FXML
    private HBox GameHBox;

    @FXML
    protected void onLanguageSelected(ActionEvent event) {
        if (event.getSource() == polishItem) {

        } else {

        }
    }

    @FXML
    protected void onButtonClick(ActionEvent event) throws IOException {
        board.solveGame();
        if (event.getSource() == btn1) {
            board.createPuzzle(Difficulty.Easy);
        } else if (event.getSource() == btn2) {
            board.createPuzzle(Difficulty.Medium);
        } else {
            board.createPuzzle(Difficulty.Hard);
        }
        Stage stage = (Stage) btn1.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(
                SudokuApplication.class.getResource("game-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        System.out.println("aaaaa");
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = new TextField();
                if (board.get(i, j) != 0) {
                    textField.setText(Integer.toString(board.get(i, j)));
                }
                textField.setMinSize(40, 40);
                textField.setMaxSize(40, 40);
                grid.add(textField, i, j);
            }
        }
        stage.setScene(scene);
        stage.show();
        //GameHBox.getChildren().add(grid);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bundle = resourceBundle;

    }
}