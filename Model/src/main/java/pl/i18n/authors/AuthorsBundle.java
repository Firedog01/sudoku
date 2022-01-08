package pl.i18n.authors;

import java.util.ListResourceBundle;

public class AuthorsBundle extends ListResourceBundle {
    private final Object[][] resources = {
            {"preamble", "Game created by:"},
            {"a1", "Krystian Baraniecki"},
            {"a2", "Jan Rubacha"}
    };

    @Override
    protected Object[][] getContents() {
        return resources;
    }

}
