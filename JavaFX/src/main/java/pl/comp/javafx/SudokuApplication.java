package pl.comp.javafx;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SudokuApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Locale l_pl = new Locale("pl", "PL");
        Locale l_en = new Locale("en", "EN");
        ResourceBundle b_en = ResourceBundle.getBundle("Lang", l_en);

        FXMLLoader fxmlLoader = new FXMLLoader(
                SudokuApplication.class.getResource("hello-view.fxml"), b_en);
        Scene scene = new Scene(fxmlLoader.load(), 320, 320);
        stage.setTitle("Sudoku");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}