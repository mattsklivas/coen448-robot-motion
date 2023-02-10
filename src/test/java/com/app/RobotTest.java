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

public class RobotTest {

    // Test Function #01
    // Test type : Functional, blackbox
    // Input : No input.
    // Description : Confirms robot has been initialized by verifying pen is up at initialization.
    // Expected output : <isPenDown() false>
    // Tester : Nicholas Harris
    // Date : 9th February
    @Test
    public void testInitializedRobot() {
        Robot testRobot = new Robot();
        assertFalse(testRobot.isPenDown());
    }

    // Test Function #02
    // Test type : Functional, blackbox
    // Input : <incrementRobotRow() 1 incrementRobotRow() 1>
    // Description : Robot can move in a position direction (up and right).
    // Expected output : <getRobotRow() 1 getRobotCol() 1>
    // Tester : Nicholas Harris
    // Date : 9th February
    @Test
    public void testRobotMovementUp() {
        Robot testRobot = new Robot();
        testRobot.incrementRobotRow(1);
        assertEquals(1, testRobot.getRobotRow());
        testRobot.incrementRobotCol(1);
        assertEquals(1, testRobot.getRobotCol());
    }

    // Test Function #03
    // Test type : Functional, blackbox
    // Input : <incrementRobotRow() 1 -1>
    // Description : Robot can move in a southern direction/down, does so by first moving up then down.
    // Expected output : <getRobotRow() 0>
    // Tester : Nicholas Harris
    // Date : 9th February
    @Test
    public void testRobotMovementDown() {
        Robot testRobot = new Robot();
        testRobot.incrementRobotRow(1);
        assertEquals(1, testRobot.getRobotRow());
        testRobot.incrementRobotRow(-1);
        assertEquals(0, testRobot.getRobotRow());
    }

    // Test Function #04
    // Test type : Functional, blackbox
    // Input : <setRobotCol 4>
    // Description : Robot can move to a certain column with the setRobotCol() function.
    // Expected output : <getRobotCol() 4>
    // Tester : Nicholas Harris
    // Date : 9th February
    @Test
    public void testSetRobotCol() {
        Robot testRobot = new Robot();
        assertEquals(0, testRobot.getRobotCol());

        testRobot.setRobotCol(4);
        assertEquals(4, testRobot.getRobotCol());
    }

    // Test Function #05
    // Test type : Functional, blackbox
    // Input : <setRobotRow 4>
    // Description : Robot can move to a certain row with the setRobotRow() function.
    // Expected output : <getRobotRow() 4>
    // Tester : Nicholas Harris
    // Date : 9th February
    @Test
    public void testSetRobotRow() {
        Robot testRobot = new Robot();
        assertEquals(0, testRobot.getRobotRow());

        testRobot.setRobotRow(4);
        assertEquals(4, testRobot.getRobotRow());
    }

    // Test Function #06
    // Test type : Functional, blackbox
    // Input : <setIsPenDown() true>.
    // Description : Robot can move pen down.
    // Expected output : <isPenDown() true>
    // Tester : Nicholas Harris
    // Date : 9th February
    @Test
    public void testRobotPenDown() {
        Robot testRobot = new Robot();
        testRobot.setIsPenDown(true);
        assertTrue(testRobot.isPenDown());
    }

    // Test Function #07
    // Test type : Functional, blackbox
    // Input : <setIsPenDown() false>.
    // Description : Robot keeps pen up even after trying to move it up again.
    // Expected output : <isPenDown false false>
    // Tester : Nicholas Harris
    // Date : 9th February
    @Test
    // write a method to move pen up
    public void testRobotPenUp() {
        Robot testRobot = new Robot();
        assertFalse(testRobot.isPenDown());

        testRobot.setIsPenDown(false);
        assertFalse(testRobot.isPenDown());
    }

    // Test Function #08
    // Test type : Functional, blackbox
    // Input : <setRobotDirection 1 1 1 -1 1 1>
    // Description : Robot can rotate in all directions, including clockwise and counter-clockwise. getRobotDirDirection also returns correction direction value.
    // Expected output : <getRobotDirDescription() north east south west south west north getRobotDirection() 3 2>
    // Tester : Nicholas Harris
    // Date : 9th February
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

    // Test Function #09
    // Test type : Functional, blackbox
    // Input : <setIsPenDown() true false false>
    // Description : Robot can move pen up and down, and getPenPosition() can accurately determine if pen is up or down.
    // Expected output : <getPenPosition() up down up up>
    // Tester : Nicholas Harris
    // Date : 9th February
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

    // Test Function #10
    // Test type : Functional, blackbox
    // Input : <setRobotDirection() 1 1 1 1 1 1 1 1>
    // Description : Robot's direction can be accurately determined.
    // Expected output : <getRobotDirection() 0 1 2 3 0 1 2 3 0>
    // Tester : Nicholas Harris
    // Date : 9th February
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
