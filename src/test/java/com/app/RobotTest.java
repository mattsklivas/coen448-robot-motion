package com.app;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class RobotTest {

    @Test
    public void testUninitializedRobot() {
        Robot testRobot = new Robot();
        assertFalse(testRobot.isPenDown());
    }
}
