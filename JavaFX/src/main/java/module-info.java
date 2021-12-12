module pl.comp.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires ModelProject;


    opens pl.comp.javafx to javafx.fxml;
    exports pl.comp.javafx;
}