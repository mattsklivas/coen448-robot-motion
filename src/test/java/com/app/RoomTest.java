package com.app;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;


public class RoomTest {

    @Test
    public void testUninitializedRoom() {
        Room testRoom = new Room();
        assertFalse(testRoom.isInitialized());
    }

    // todo: change to parametrized and add more tests cases
    @Test
    public void testRoomInitialization() {
        int n = 10;
        Room testRoom = new Room(n);
        assertTrue(testRoom.isInitialized());
        assertEquals(n, testRoom.getFloorSize());
    }

    @Test
    public void testRobotCreation() {
        int n = 10;
        Room testRoom = new Room(n);

        Robot testRobot = testRoom.getRobot();

        assertTrue(testRoom.isInitialized());
        assertEquals(n, testRoom.getFloorSize());

        assertEquals(0, testRobot.getRobotRow());
        assertEquals(0, testRobot.getRobotCol());
        assertEquals("north", testRobot.getRobotDirDescription());
        assertFalse(testRobot.isPenDown());
    }

    @Test
    public void testRobotMovement() {
        int n = 10;
        Room testRoom = new Room(n);

        Robot testRobot = testRoom.getRobot();

        assertTrue(testRoom.isInitialized());
        assertEquals(n, testRoom.getFloorSize());

        assertEquals(0, testRobot.getRobotRow());
        assertEquals(0, testRobot.getRobotCol());
        assertEquals("north", testRobot.getRobotDirDescription());
        assertFalse(testRobot.isPenDown());

        testRoom.moveRobot(1);
        assertEquals(1, testRobot.getRobotRow());
        assertEquals(0, testRobot.getRobotCol());
        assertEquals("north", testRobot.getRobotDirDescription());
        assertFalse(testRobot.isPenDown());

        testRobot.setRobotDirection(1);
        testRoom.moveRobot(1);
        assertEquals(1, testRobot.getRobotRow());
        assertEquals(1, testRobot.getRobotCol());
        assertEquals("east", testRobot.getRobotDirDescription());
        assertFalse(testRobot.isPenDown());
        testRoom.printRobotState();

        
        int[][] floorArr = testRoom.getFloor();
        for (int i = 0; i < testRoom.getFloorSize(); i++) {
            assertArrayEquals(new int[n], floorArr[i]);
        }

        testRoom.printFloor();
        // testRoom.moveRobot(100);
        // assertEquals(0, testRobot.getRobotRow());
        // assertEquals(2, testRobot.getRobotCol());
        // assertEquals("north", testRobot.getRobotDirDescription());
        // assertFalse(testRobot.isPenDown());

        // testRoom.moveRobot(4);
        // assertEquals(0, testRobot.getRobotRow());
        // assertEquals(2, testRobot.getRobotCol());
        // assertEquals("west", testRobot.getRobotDirDescription());
        // assertFalse(testRobot.isPenDown());

        // testRoom.moveRobot(10);
        // assertEquals(0, testRobot.getRobotRow());
        // assertEquals(1, testRobot.getRobotCol());
        // assertEquals("west", testRobot.getRobotDirDescription());
        // assertFalse(testRobot.isPenDown());

        // testRoom.moveRobot(10);
        // assertEquals(0, testRobot.getRobotRow());
        // assertEquals(1, testRobot.getRobotCol());
        // assertEquals("north", testRobot.getRobotDirDescription());
        // assertFalse(testRobot.isPenDown());

        // testRoom.moveRobot(10);
        // assertEquals(0, testRobot.getRobotRow());
        // assertEquals(2, testRobot.getRobotCol());
        // assertEquals("north", testRobot.getRobotDirDescription());
        // assertFalse(testRobot.isPenDown());

        // testRoom.moveRobot(10);
        // assertEquals(0, testRobot.getRobotRow());
        // assertEquals(2, testRobot.getRobotCol());
        // assertEquals("east", testRobot.getRobotDirDescription());
        // assertFalse(testRobot.isPenDown());

        // testRoom.moveRobot(10);
        // assertEquals(0, testRobot.getRobotRow());
        // assertEquals(3, testRobot.getRobotCol());
        // assertEquals("east", testRobot.getRobotDirDescription());
        // assertFalse(testRobot.isPenDown());
    }

    @Test
    public void testMovePen() {
        int n = 10;
        Room testRoom = new Room(n);

        Robot testRobot = testRoom.getRobot();

        assertTrue(testRoom.isInitialized());
        assertFalse(testRobot.isPenDown());

        testRoom.movePen(true);
        assertTrue(testRobot.isPenDown());

        testRoom.movePen(false);
        assertFalse(testRobot.isPenDown());
    }
}
