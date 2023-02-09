package com.app;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/*
Robot Test Cases:

Methods:
- 1 test per method
- Robot()
- setRobotCol()
- setRobotRow()
- setIsPenDown()
- getRobotCol()
- getRobotRow()
- isPenDown()
- getRobotDirection()
- getRobotDescription()
- getPenPosition()
- getRobotDirDescription()

Note: 4 for setRobotDirection
- +1, -1
- Boundaries: -1 (3), 4 (0)

Note: 2 each for incrementRobotRow and incrementRobotCol
- +1, -1 and +2, -2

Variables
- robotRow and robotCol domain: (-n, n)
- isPenDown domain: (False, True)
- robotDirection domain: (0,3)
 */

//TODO: Add test case id and description for each
public class RobotTest {
    @Test
    public void testUninitializedRobot() {
        Robot testRobot = new Robot();
        assertFalse(testRobot.isPenDown());
    }


    @Test
    public void testRobotMovementUp() {
        Robot testRobot = new Robot();
        testRobot.incrementRobotRow(1);
        assertEquals(1, testRobot.getRobotRow());
        testRobot.incrementRobotCol(1);
        assertEquals(1, testRobot.getRobotCol());
    }


    @Test
    public void testRobotMovementDown() {
        Robot testRobot = new Robot();
        testRobot.incrementRobotRow(1);
        assertEquals(1, testRobot.getRobotRow());
        testRobot.incrementRobotRow(-1);
        assertEquals(0, testRobot.getRobotRow());
    }


    @Test
    public void testSetRobotCol() {
        Robot testRobot = new Robot();
        assertEquals(0, testRobot.getRobotCol());

        testRobot.setRobotCol(4);
        assertEquals(4, testRobot.getRobotCol());
    }


    @Test
    public void testSetRobotRow() {
        Robot testRobot = new Robot();
        assertEquals(0, testRobot.getRobotRow());

        testRobot.setRobotRow(4);
        assertEquals(4, testRobot.getRobotRow());
    }


    @Test
    // write a method to move pen down
    public void testRobotPenDown() {
        Robot testRobot = new Robot();
        testRobot.setIsPenDown(true);
        assertTrue(testRobot.isPenDown());
    }


    @Test
    // write a method to move pen up
    public void testRobotPenUp() {
        Robot testRobot = new Robot();
        assertFalse(testRobot.isPenDown());

        testRobot.setIsPenDown(false);
        assertFalse(testRobot.isPenDown());
    }


    @Test
    public void tesSetRobotDirection() {
        Robot testRobot = new Robot();
        assertEquals("north", testRobot.getRobotDirDescription());

        testRobot.setRobotDirection(1);
        assertEquals("east", testRobot.getRobotDirDescription());

        testRobot.setRobotDirection(1);
        assertEquals("south", testRobot.getRobotDirDescription());

        testRobot.setRobotDirection(1);
        assertEquals(3, testRobot.getRobotDirection());
        assertEquals("west", testRobot.getRobotDirDescription());

        testRobot.setRobotDirection(-1);
        assertEquals(2, testRobot.getRobotDirection());
        assertEquals("south", testRobot.getRobotDirDescription());

        testRobot.setRobotDirection(1);
        assertEquals("west", testRobot.getRobotDirDescription());

        testRobot.setRobotDirection(1);
        assertEquals("north", testRobot.getRobotDirDescription());
    }


    @Test
    public void testGetPenPosition() {
        Robot testRobot = new Robot();
        assertEquals("up", testRobot.getPenPosition());

        testRobot.setIsPenDown(true);
        assertEquals("down", testRobot.getPenPosition());

        testRobot.setIsPenDown(false);
        assertEquals("up", testRobot.getPenPosition());

        testRobot.setIsPenDown(false);
        assertEquals("up", testRobot.getPenPosition());
    }

    @Test
    public void testGetRobotDirection() {
        Robot testRobot = new Robot();
        assertEquals(0, testRobot.getRobotDirection());

        testRobot.setRobotDirection(1);
        assertEquals(1, testRobot.getRobotDirection());

        testRobot.setRobotDirection(1);
        assertEquals(2, testRobot.getRobotDirection());

        testRobot.setRobotDirection(1);
        assertEquals(3, testRobot.getRobotDirection());

        testRobot.setRobotDirection(1);
        assertEquals(0, testRobot.getRobotDirection());

        testRobot.setRobotDirection(1);
        assertEquals(1, testRobot.getRobotDirection());

        testRobot.setRobotDirection(1);
        assertEquals(2, testRobot.getRobotDirection());

        testRobot.setRobotDirection(1);
        assertEquals(3, testRobot.getRobotDirection());

        testRobot.setRobotDirection(1);
        assertEquals(0, testRobot.getRobotDirection());
    }

}
