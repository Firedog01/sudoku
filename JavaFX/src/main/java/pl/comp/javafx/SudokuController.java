package pl.comp.javafx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pl.comp.model.BacktrackingSudokuSolver;
import pl.comp.model.Difficulty;
import pl.comp.model.SudokuBoard;

public class SudokuController implements Initializable {

    SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    protected void onButtonClick(ActionEvent event){
        board.solveGame();
        if (event.getSource() == btn1) {
            board.createPuzzle(Difficulty.Easy);
        } else if (event.getSource() == btn2) {
            board.createPuzzle(Difficulty.Medium);
        } else {
            board.createPuzzle(Difficulty.Hard);
        }
        Stage stage;
        stage = (Stage) btn1.getScene().getWindow();
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
        Scene scene = new Scene(grid);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}