package com.app;

import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class runnerTest {
    @Test
    public void testRunner() throws Exception {
        final InputStream defaultIS = System.in;
        String currDir = System.getProperty("user.dir");
        String testDir = "/src/test/java/com/app/test_inputs/";

        final FileInputStream fileIS = new FileInputStream(currDir + testDir + "test_inputs_main.txt");
        System.setIn(fileIS);
        runner.main(null);
        System.setIn(defaultIS);
    }

    @ParameterizedTest
    @ValueSource(strings = {"q", "u ", "q  ", "q ", "q", "q "})
    public void testParseCommand(String command) {
        command = command.toLowerCase().strip();
        boolean expected = command == "q";
        boolean actual = runner.parseCommand(command);
        assertEquals(expected, actual);

    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "a ", "q  ", " ", "m", "m    "})
    public void testGetOption(String command) {
        command = command.toLowerCase().strip();
        char expected = command.length() <= 0 ? ' ' : command.charAt(0);
        char actual = runner.getOption(command);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"i 10", "i 100 ", " ", " m  ", "m 10", "100"})
    public void testGetParam(String command) {
        command = command.toLowerCase().strip();
        String expected = command.length() <= 1 ? null : command.substring(1).strip();
        String actual = runner.getParam(command);
        if (expected == null) {
            assertNull(expected, actual);
        } else {
            assertEquals(expected, actual, "expected: " + expected + " actual: " + actual);
        }
        assertEquals(expected, actual, "expected: " + expected + " actual: " + actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"u", "D", "R", "R 10", "c", "Q", "q 10", "P p", "p"})
    public void testValidateSingletonCommand(String command) {
        command = command.toLowerCase().strip();
        boolean expected = command.length() == 1;
        boolean actual = runner.validateSingletonCommand(command);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(chars = {'a', 'A', 'q', 'Q', 'd', 'q', 'U', 'r', 't'})
    public void testCheckForExit(char option) {
        option = Character.toLowerCase(option);
        boolean expected = Character.toLowerCase(option) == 'q';
        boolean actual = runner.checkForExit(option);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(chars = {'a', 'a', ' ', ' ', 'q', 'Q', 'M', 'n', ' '})
    public void testCheckForEmptyOption(char option) {
        boolean expected = option == ' ';
        boolean actual = runner.checkForEmptyOption(option);
        assertEquals(expected, actual);
    }


    @ParameterizedTest
    @ValueSource(strings = {"3", "-100", "100", "10000", " ", "45 ", "-45", "3.4", "-2.3", "hello"})
    public void validateParamFormat(String param) {
        final String paramStrip = param.strip();

        try {
            Integer paramInt = Integer.parseInt(paramStrip);
            boolean expected = paramStrip.matches("[0-9]+") && paramInt <= 100;
            boolean actual = runner.validateParamFormat(paramStrip);
            assertEquals(expected, actual);
        } catch (NumberFormatException e) {
            Exception exception = assertThrows(NumberFormatException.class, () -> {
                Integer.parseInt(paramStrip);
            });
            assertEquals(exception.getMessage(), e.getMessage());
        }
    }
}
