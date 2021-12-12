package pl.comp.model;/*
source:
    https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
 */

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import pl.comp.model.Greet;

class GreetTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    /**
     * default call of main. All should be valid
     */
    @Test
    void mainTest() {
        System.setOut(new PrintStream(outContent));
        String[] args= {""};
        Greet greet = new Greet();
        greet.main(args);
        assertNotEquals(outContent.toString(), "");
        System.setOut(originalOut);
    }
}