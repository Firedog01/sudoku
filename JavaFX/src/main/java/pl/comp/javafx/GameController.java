package pl.comp.javafx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.StringProperty;
import javafx.beans.property.adapter.JavaBeanStringPropertyBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.comp.dao.SudokuBoardDaoFactory;
import pl.comp.exceptions.model.OutOfRangeCoordsException;
import pl.comp.model.SudokuBoard;

//two-way binding
//https://developer.android.com/topic/libraries/data-binding/two-way#java
public class GameController implements Initializable {

    private static Logger logger = LoggerFactory.getLogger(GameController.class);

    private SudokuBoard board;

    SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();

    private TextField[][] fields = new TextField[9][9];

    private StringProperty[][] fieldsProperty = new StringProperty[9][9];

    private ResourceBundle bundle;

    @FXML
    private GridPane grid = new GridPane();

    @FXML
    private VBox sideVbox;

    protected void initData(SudokuBoard board) throws OutOfRangeCoordsException {
        this.board = board;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                fields[i][j] = new TextField();
                fields[i][j].setMinSize(40, 40);
                fields[i][j].setMaxSize(40, 40);
                fields[i][j].setText("");
                try {
                    fieldsProperty[i][j] = JavaBeanStringPropertyBuilder.create().bean(fields[i][j]).name("value").build();
                } catch (NoSuchMethodException E) {
                    //joe
                }

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

                if (matcher.find()) { 
                    field.setText(matcher.group());
                } else {
                    field.setText("");
                }
            }
            updateBoard();
            if (board.checkBoard()) {
                Label winLabel = new Label();
                winLabel.setText(bundle.getString("game.win"));
                sideVbox.getChildren().add(winLabel);
            }
        }
    }

    private TextField getFocusedField() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (fields[i][j].isFocused()) {
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
                String snumber = fields[i][j].getText();
                int inumber = 0;
                if (snumber != "") {
                    try {
                        inumber = Integer.parseInt(snumber);
                    } catch (NumberFormatException e) {
                        logger.info("log.fx.parse");
                    }
                }
                board.set(i, j, inumber);
            }
        }
    }

    public void setBoard(SudokuBoard board) throws OutOfRangeCoordsException {
        this.board = board;
        updateFields();
    }

    @FXML
    protected void dialog_save(ActionEvent event) throws IOException {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(grid.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader(
                SudokuApplication.class.getResource("save-dialog.fxml"), bundle);
        Scene saveScene = new Scene(fxmlLoader.load());
        dialog.setScene(saveScene);
        SaveController controller = fxmlLoader.getController();
        controller.initData(board);
        dialog.show();
    }

    @FXML
    protected void dialog_load(ActionEvent event) throws IOException {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(grid.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader(
                SudokuApplication.class.getResource("load-dialog.fxml"), bundle);
        Scene saveScene = new Scene(fxmlLoader.load());
        dialog.setScene(saveScene);

        LoadController controller = fxmlLoader.getController();
        controller.initData(board, this);
        dialog.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bundle = resourceBundle;
    }
}
