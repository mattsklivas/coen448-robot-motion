package com.app;

import java.io.InputStream;
import java.io.FileInputStream;
import org.junit.Test;

public class runnerTest {
    @Test
    public void testUninitializedRoom() throws Exception {
        final InputStream defaultIS = System.in;
        final FileInputStream fileIS = new FileInputStream("/Users/nich/Documents/programming/coen448-robot-motion/src/test/java/com/app/test_inputs/test_inputs_main.txt");
        System.setIn(fileIS);
        runner.main(null);
        System.setIn(defaultIS);
    }
}
