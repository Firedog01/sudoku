package pl.comp.javafx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import pl.comp.dao.Dao;
import pl.comp.dao.SudokuBoardDaoFactory;
import pl.comp.exceptions.OutOfRangeCoordsException;
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
    private GridPane grid = new GridPane();

    protected void initData(SudokuBoard board) {
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

    @FXML
    protected void onFieldChanged(KeyEvent event) {
        TextField field = getFocusedField();
        KeyCode code = event.getCode();

        String inputText = event.getText();
        int inputNum = -1;
        try {
            inputNum = Integer.parseInt(inputText);
        } catch(NumberFormatException e) {
            System.out.println(e);
        }

        System.out.println("key: " + inputText);
        if (field != null) {
            if(inputNum > 0 && inputNum < 9) {
                field.setText(inputText);
            } else if(inputText == null) {
                System.out.println("null");
            }
        }
    }

    private TextField getFocusedField() {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(fields[i][j].isFocused()) {
                    return fields[i][j];
                }
            }
        }
        return null;
    }

    @FXML
    protected void load(ActionEvent event) {
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
