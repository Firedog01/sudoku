package pl.comp.javafx;

import javafx.util.StringConverter;

public class SudokuFieldConverter extends StringConverter<Integer> {
    @Override
    public String toString(Integer integer) {
        if(integer > 9 || integer < 1) {
            return "";
        } else {
            return String.valueOf(integer);
        }
    }

    @Override
    public Integer fromString(String s) {
        if (s.matches("^[1-9]$")) {
            return Integer.valueOf(s);
        }
        else {
            return 0;
        }
    }
}
