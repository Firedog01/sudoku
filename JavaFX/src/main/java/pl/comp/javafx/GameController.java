package pl.comp.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.comp.dao.Dao;
import pl.comp.dao.SudokuBoardDaoFactory;
import pl.comp.exceptions.model.OutOfRangeCoordsException;
import pl.comp.model.Greet;
import pl.comp.model.SudokuBoard;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//two-way binding
//https://developer.android.com/topic/libraries/data-binding/two-way#java
public class GameController implements Initializable {

    private static Logger logger = LoggerFactory.getLogger(GameController.class);

    private SudokuBoard board;

    private SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();

    private TextField[][] fields = new TextField[9][9];

    private ResourceBundle bundle;

    @FXML
    private GridPane grid = new GridPane();

    @FXML
    private VBox SideVBox;

    protected void initData(SudokuBoard board) throws OutOfRangeCoordsException {
        this.board = board;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                fields[i][j] = new TextField();
                fields[i][j].setMinSize(40, 40);
                fields[i][j].setMaxSize(40, 40);
                fields[i][j].setText("");
                grid.add(fields[i][j], i, j);
            }
        }
        grid.setGridLinesVisible(true);
        updateFields();
    }



    @FXML
    protected void onFieldChanged(KeyEvent event) throws OutOfRangeCoordsException {
        TextField field = getFocusedField();
        int code = event.getCode().getCode();
        // Backspace - 8
        //         0 - 48
        //         9 - 57
        //    Delete - 127
        if (field != null) {
            if (code > 48 && code <= 57) {
                field.setText(event.getText());
            } else if (code == 8 || code == 127) {
                field.setText("");
            } else {
                String originalText = field.getText();
                Pattern compiledPattern = Pattern.compile("\\d");
                Matcher matcher = compiledPattern.matcher(originalText);

                if(matcher.find()) {
                    field.setText(matcher.group());
                } else {
                    field.setText("");
                }
            }
            updateBoard();
            if (board.checkBoard()) {
                Label winLabel = new Label();
                winLabel.setText(bundle.getString("game.win"));
                SideVBox.getChildren().add(winLabel);
            }
        }
    }

    private TextField getFocusedField() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(fields[i][j].isFocused()) {
                    return fields[i][j];
                }
            }
        }
        return null;
    }

    private void updateFields() throws OutOfRangeCoordsException {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.get(i, j) != 0) {
                    fields[i][j].setText(Integer.toString(board.get(i, j)));
                } else if (board.get(i, j) == 0) {
                    fields[i][j].setText("");
                }
            }
        }
    }

    private void updateBoard() throws OutOfRangeCoordsException {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String sNumber = fields[i][j].getText();
                int iNumber = 0;
                try {
                    iNumber = Integer.parseInt(sNumber);
                } catch (NumberFormatException e) {
                    logger.info("log.fx.parse");
                }
                board.set(i, j, iNumber);
            }
        }
    }

    @FXML
    protected void load(ActionEvent event) throws OutOfRangeCoordsException {
        try (Dao<SudokuBoard> fileDao = factory.getFileDao("board")) {
            board = fileDao.read();
        } catch (Exception e){
            e.printStackTrace();
        }
        updateFields();
    }

    @FXML
    protected void save(ActionEvent event) {
        try (Dao<SudokuBoard> fileDao = factory.getFileDao("board")) {
            fileDao.write(board);
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
