package pl.comp.javafx;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Locale;
import java.util.ResourceBundle;

class BundleTest {
    @Test
    void getBundleContentsTest() {
        Locale l_pl = new Locale("pl", "PL");
        Locale l_en = new Locale("en", "EN");
        ResourceBundle b_pl = ResourceBundle.getBundle("Bundle", l_pl);
        ResourceBundle b_en = ResourceBundle.getBundle("Bundle", l_en);
        ResourceBundle b_def = ResourceBundle.getBundle("Bundle");

        assertEquals("Zagraj w Sudoku!", b_pl.getString("Title"));
        assertEquals("Play Sudoku!", b_en.getString("Title"));
        assertEquals("Sudoku", b_def.getString("Title"));
    }
}
