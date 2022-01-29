package pl.comp.javafx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.StringProperty;
import javafx.beans.property.adapter.JavaBeanStringPropertyBuilder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.comp.model.SudokuBoard;

public class GameController implements Initializable {

    private SudokuBoard board;

    private TextField[][] fields = new TextField[9][9];

    private StringProperty[][] fieldsProperty = new StringProperty[9][9];

    private ResourceBundle bundle;

    @FXML
    private GridPane grid = new GridPane();

    protected void initData(SudokuBoard board) {
        grid.getChildren().removeAll();
        this.board = board;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                fields[i][j] = new TextField();
                fields[i][j].setMinSize(40, 40);
                fields[i][j].setMaxSize(40, 40);
                fields[i][j].setText("");
                try {
                    fieldsProperty[i][j] = JavaBeanStringPropertyBuilder.create()
                            .bean(new SudokuFieldHelper(this.board, i, j)).name("Field").build();
                } catch (NoSuchMethodException E) {
                    E.printStackTrace();
                }
                TextField thisField = fields[i][j];

                thisField.textProperty().bindBidirectional(fieldsProperty[i][j]);
                thisField.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observableValue, String old_val, String new_val) {
                        if (!((new_val.matches("^[1-9]$")) || (new_val.equals("")))) {
                            thisField.setText(old_val);
                        }
                    }
                });
                grid.add(fields[i][j], i, j);
            }
        }
        grid.setGridLinesVisible(true);
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
