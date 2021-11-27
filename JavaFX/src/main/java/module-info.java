module pl.comp.javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens pl.comp.javafx to javafx.fxml;
    exports pl.comp.javafx;
}