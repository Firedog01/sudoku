open module ModelProject {
    requires org.apache.commons.lang3;
    requires java.desktop;
    requires sudoku;
    requires org.slf4j;
    requires log4j;

    exports pl.comp.model;
    exports pl.i18n.authors;
    exports pl.comp.exceptions;
}