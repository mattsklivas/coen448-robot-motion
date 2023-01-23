package com.app;

import java.io.InputStream;
import java.io.FileInputStream;
import org.junit.jupiter.api.Test;

public class runnerTest {
    @Test
    public void testRunner() throws Exception {
        final InputStream defaultIS = System.in;
        // get file directory ../test_inputs
        String currDir = System.getProperty("user.dir");

        final FileInputStream fileIS = new FileInputStream(currDir + "/src/test/java/com/app/test_inputs/test_inputs_main.txt");
        System.setIn(fileIS);
        runner.main(null);
        System.setIn(defaultIS);
    }
}
