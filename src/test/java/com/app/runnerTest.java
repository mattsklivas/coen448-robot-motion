package com.app;

import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class runnerTest {
    @Test
    public void testRunner() throws Exception {
        final InputStream defaultIS = System.in;
        // get file directory ../test_inputs
        String currDir = System.getProperty("user.dir");
        String testDir = "/src/test/java/com/app/test_inputs/";

        final FileInputStream fileIS = new FileInputStream(currDir + testDir + "test_inputs_main.txt");
        System.setIn(fileIS);
        runner.main(null);
        System.setIn(defaultIS);
    }

    // @ParameterizedTest
    // @ValueSource(strings = {"I ", "M ", "i", "m"})
    // public void testInvalidInputInitialize(String input) throws Exception {
    //     String expected = "Error: Numerical parameter required for the provided command. Please try again...";

    //     final FileOutputStream fileOS = new FileOutputStream("test_outputs.txt");
    //     PrintStream defaultPS = System.out;
    //     PrintStream filePS = new PrintStream(fileOS);
    //     System.setOut(filePS);

    //     InputStream defaultIS = System.in;
    //     InputStream varIS = new InputStream() {
    //         @Override
    //         public int read() {
    //             return input.charAt(0);
    //         }
    //     };
    //     System.setIn(varIS);

    //     // do stuff
    //     runner.main(null);

    //     assertEquals(expected, System.out.toString(), "Error: Invalid input not handled correctly: " + System.out.toString());

    //     System.setOut(defaultPS);
    //     System.setIn(defaultIS);
        
    // }
}
