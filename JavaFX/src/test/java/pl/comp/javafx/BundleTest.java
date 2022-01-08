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
        ResourceBundle b_pl = ResourceBundle.getBundle("Lang", l_pl);
        ResourceBundle b_en = ResourceBundle.getBundle("Lang", l_en);

        assertEquals("Zagraj w Sudoku!", b_pl.getString("title"));
        assertEquals("Play Sudoku!", b_en.getString("title"));
    }
}
