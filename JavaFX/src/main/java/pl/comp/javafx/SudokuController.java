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
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pl.comp.model.BacktrackingSudokuSolver;
import pl.comp.model.Difficulty;
import pl.comp.model.SudokuBoard;

public class SudokuController implements Initializable {

    SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());

    private Locale locale;

    @FXML
    private Label labelTitle;

    @FXML
    private Label labelChoose;

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private Button btn3;

    @FXML
    private MenuItem polishItem;

    @FXML
    private MenuItem englishItem;

    @FXML
    private Label preamble;

    @FXML
    private Label a1;

    @FXML
    private Label a2;

    @FXML
    private HBox GameHBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        locale = resourceBundle.getLocale();
        setText();
    }

    protected void setText() {
        ResourceBundle labelBundle = ResourceBundle.getBundle("Lang", locale);
        ResourceBundle authorsBundle = ResourceBundle.getBundle("pl.i18n.authors.AuthorsBundle", locale);

        labelTitle.setFont(new Font("Arial", 30));
        labelTitle.setText(labelBundle.getString("title"));
        labelChoose.setText(labelBundle.getString("menu.choose"));
        btn1.setText(labelBundle.getString("difficulty.easy"));
        btn2.setText(labelBundle.getString("difficulty.medium"));
        btn3.setText(labelBundle.getString("difficulty.hard"));
        preamble.setText(authorsBundle.getString("preamble"));
        a1.setText(authorsBundle.getString("a1"));
        a2.setText(authorsBundle.getString("a2"));
    }

    @FXML
    protected void onLanguageSelected(ActionEvent event) {
        if (event.getSource() == polishItem) {
            locale = new Locale("pl", "PL");
        } else if (event.getSource() == englishItem) {
            locale = new Locale("en", "EN");
        }
        setText();
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

        ResourceBundle labelBundle = ResourceBundle.getBundle("Lang", locale);
        FXMLLoader fxmlLoader = new FXMLLoader(
                SudokuApplication.class.getResource("game-view.fxml"), labelBundle);
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

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
    }
}