package com.app;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RobotTest {

    @Test
    public void testUninitializedRobot() {
        Robot testRobot = new Robot();
        assertEquals(false, testRobot.isPenDown());
    }
}
