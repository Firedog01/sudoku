package pl.i18n.authors;

import java.util.ListResourceBundle;

public class AuthorsBundle_pl_PL extends ListResourceBundle {
    private final Object[][] resources = {
            {"preamble", "Gra stworzona przez:"},
            {"a1", "Krystian Baraniecki"},
            {"a2", "Jan Rubacha"}
    };

    @Override
    protected Object[][] getContents() {
        return resources;
    }
}
