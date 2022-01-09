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
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pl.comp.exceptions.OutOfRangeCoordsException;
import pl.comp.exceptions.UnfilledBoardException;
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
    private MenuButton langChoose;

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
        langChoose.setText(labelBundle.getString("language.language"));
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
    protected void onButtonClick(ActionEvent event)
            throws IOException, UnfilledBoardException, OutOfRangeCoordsException {
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
        Scene scene = new Scene(fxmlLoader.load(), 440, 360);
        stage.setScene(scene);
        GameController controller = fxmlLoader.getController();
        controller.initData(board);
        stage.show();
    }
}