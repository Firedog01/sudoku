package pl.comp.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import pl.comp.model.SudokuBoard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//two-way binding
//https://developer.android.com/topic/libraries/data-binding/two-way#java
public class GameController implements Initializable {

    private SudokuBoard board;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    protected void load(ActionEvent event) throws IOException {

    }

    @FXML
    protected void save(ActionEvent event) throws IOException {

    }


}
