package com.app;

import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegressionTest {


    /*
     * What changed:
     * - parseCommand -> replayingHistory added, commandHistory.add, added condition if (param == null || !validateParamFormat(param)),
     * - add condtion if (option == 'h') replayHistory();
     * - formatKnownCommands -> added alpha.add('h');
     * -- added replayHistory() method
     */


    // Test Function #27
    // Test type : Regression
    // Input :
    // Description :
    // Expected output :
    // Tester : Nicholas Harris
    // Date : 4th April
    @Test
    @Order(4)
    public void testReplayFunction() throws Exception {
        final InputStream defaultIS = System.in;
        final PrintStream defaultPS = System.out;

        String currDir = System.getProperty("user.dir");
        String testDir = "/src/test/java/com/app/test_inputs";
        final FileInputStream fileIS = new FileInputStream(currDir + testDir + "/test_help.txt");
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        String expected = "Command history: \np\ni i\n\nf\nm 10\ni 5\nm 10\nd 10\nd\nu\nD\nl\nr\np\nc\nh";
        // Set custom I/O
        System.setIn(fileIS);
        System.setOut(new PrintStream(outContent));

        runner.main(null);
        String output = outContent.toString().replaceAll("\r", "");
        assertTrue(output.contains(expected), output);

        // Set default I/O
        System.setIn(defaultIS);
        System.setOut(defaultPS);
    }

    // Test Function #28
    // Test type : Regression
    // Input :
    // Description :
    // Expected output :
    // Tester : Nicholas Harris
    // Date : 4th April
    @Test
    @Order(5)
    public void testReplayFunctionUpper() throws Exception {
        final InputStream defaultIS = System.in;
        final PrintStream defaultPS = System.out;

        String currDir = System.getProperty("user.dir");
        String testDir = "/src/test/java/com/app/test_inputs";
        final FileInputStream fileIS = new FileInputStream(currDir + testDir + "/test_H.txt");
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        String expected = "Command history: \np\ni i\n\nf\nm 10\ni 5\nm 10\nd 10\nd\nu\nD\nl\nr\np\nc\nH";
        // Set custom I/O
        System.setIn(fileIS);
        System.setOut(new PrintStream(outContent));

        runner.main(null);
        String output = outContent.toString().replaceAll("\r", "");
        assertTrue(output.contains(expected), output);

        // Set default I/O
        System.setIn(defaultIS);
        System.setOut(defaultPS);
    }
}
