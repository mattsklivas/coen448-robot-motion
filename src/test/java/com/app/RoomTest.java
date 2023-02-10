package com.app;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


/*
Room Test Cases:

Methods:
- 1 test per method
- Room()
- printRobotState()
- getRobot()
- getFloor()
- getFloorSize()
- isInitialized()

Note: 4 for Room(n)
- -1 (neg), +1(base), +100 (large)
- Boundary: 0

Note: 2 for movePen
- True->False, False->True

Note: 10 for moveRobot
- 1 (left, Pen down), 2 (right, Pen up), 2 (up, Pen down), 1 (down, Pen up), x > n (left, right), y > n (up, down)
- Boundaries: 0 (down, col), 0 (left, row), n (up, col), n(right, row)

Note: 2 for printFloor
- empty, w/ stars

Variables
- floor[][] domain: (n by n)
- floorSize domain: n
- isInitialized domain: (False, True)
 */

//TODO: Add test case id and description for each
public class RoomTest {
    @Test
    public void testUninitializedRoom() {
        Room testRoom = new Room();
        assertFalse(testRoom.isInitialized());
    }

    // todo: change to parametrized and add more tests cases
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    public void testRoomInitialization(int size) {
        Room testRoom = new Room(size);
        assertTrue(testRoom.isInitialized());
        assertEquals(size, testRoom.getFloorSize(), String.format("Room size entered: %d is not equal to room size returned: %d", size, testRoom.getFloorSize()));
    }

    @ParameterizedTest
    @ValueSource(ints = {7, 8, 1, 3, 5, 10, 8})
    public void testRobotCreation(int size) {
        Room testRoom = new Room(size);

        Robot testRobot = testRoom.getRobot();

        assertTrue(testRoom.isInitialized());
        assertEquals(size, testRoom.getFloorSize());

        assertEquals(0, testRobot.getRobotRow(), String.format("Robot row expected: %d is not equal to robot row returned: %d", 0, testRobot.getRobotRow()));
        assertEquals(0, testRobot.getRobotCol(), String.format("Robot col expected: %d is not equal to robot col returned: %d", 0, testRobot.getRobotCol()));
        assertEquals("north", testRobot.getRobotDirDescription(), String.format("Robot direction expected: %s is not equal to robot direction returned: %s", "north", testRobot.getRobotDirDescription()));
        assertFalse(testRobot.isPenDown());
    }

    @ParameterizedTest
    @ValueSource(ints = {7, 8, 1, 3, 5, 10, 8})
    public void testRobotMovement(int moves) {
        int n = 10;
        Room testRoom = new Room(n);

        Robot testRobot = testRoom.getRobot();

        assertTrue(testRoom.isInitialized());
        assertEquals(n, testRoom.getFloorSize());

        assertEquals(0, testRobot.getRobotRow(), String.format("Robot row expected: %d is not equal to robot row returned: %d", 0, testRobot.getRobotRow()));
        assertEquals(0, testRobot.getRobotCol(), String.format("Robot col expected: %d is not equal to robot col returned: %d", 0, testRobot.getRobotCol()));
        assertEquals("north", testRobot.getRobotDirDescription(), String.format("Robot direction expected: %s is not equal to robot direction returned: %s", "north", testRobot.getRobotDirDescription()));
        assertFalse(testRobot.isPenDown());

        testRoom.moveRobot(moves);
        assertEquals(moves < n ? moves : n - 1, testRobot.getRobotRow(), String.format("Robot row expected: %d is not equal to robot row returned: %d", moves < n ? moves : n, testRobot.getRobotRow()));
        assertEquals(0, testRobot.getRobotCol(), String.format("Robot col expected: %d is not equal to robot col returned: %d", 0, testRobot.getRobotCol()));
        assertEquals("north", testRobot.getRobotDirDescription(), String.format("Robot direction expected: %s is not equal to robot direction returned: %s", "north", testRobot.getRobotDirDescription()));
        assertFalse(testRobot.isPenDown());

        testRobot.setRobotDirection(1);
        testRoom.moveRobot(moves);
        assertEquals(moves < n ? moves : n - 1, testRobot.getRobotRow(), String.format("Robot row expected: %d is not equal to robot row returned: %d", moves < n ? moves : n, testRobot.getRobotRow()));
        assertEquals(moves < n ? moves : n - 1, testRobot.getRobotCol(), String.format("Robot col expected: %d is not equal to robot col returned: %d", moves < n ? moves : n, testRobot.getRobotCol()));
        assertEquals("east", testRobot.getRobotDirDescription(), String.format("Robot direction expected: %s is not equal to robot direction returned: %s", "east", testRobot.getRobotDirDescription()));
        assertFalse(testRobot.isPenDown());
        testRoom.printRobotState();

        int[][] floorArr = testRoom.getFloor();
        for (int i = 0; i < testRoom.getFloorSize(); i++) {
            assertArrayEquals(new int[n], floorArr[i]);
        }

        testRoom.printFloor();
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 2, 3})
    public void testRobotBoundary(int moves) {
        int n = 10;
        Room testRoom = new Room(n);

        Robot testRobot = testRoom.getRobot();

        assertTrue(testRoom.isInitialized());
        assertEquals(n, testRoom.getFloorSize());

        testRoom.movePen(true);
        assertTrue(testRobot.isPenDown());

        assertEquals(0, testRobot.getRobotRow(), String.format("Robot row expected: %d is not equal to robot row returned: %d", 0, testRobot.getRobotRow()));
        assertEquals(0, testRobot.getRobotCol(), String.format("Robot col expected: %d is not equal to robot col returned: %d", 0, testRobot.getRobotCol()));
        assertEquals("north", testRobot.getRobotDirDescription(), String.format("Robot direction expected: %s is not equal to robot direction returned: %s", "north", testRobot.getRobotDirDescription()));


        testRobot.setRobotDirection(-1);
        assertEquals("west", testRobot.getRobotDirDescription(), String.format("Robot direction expected: %s is not equal to robot direction returned: %s", "west", testRobot.getRobotDirDescription()));

        testRoom.moveRobot(moves);
        assertEquals(0, testRobot.getRobotRow(), String.format("Robot row expected: %d is not equal to robot row returned: %d", 0, testRobot.getRobotRow()));
        assertEquals(0, testRobot.getRobotCol(), String.format("Robot col expected: %d is not equal to robot col returned: %d", 0, testRobot.getRobotCol()));
        assertEquals("west", testRobot.getRobotDirDescription(), String.format("Robot direction expected: %s is not equal to robot direction returned: %s", "west", testRobot.getRobotDirDescription()));

        testRoom.printRobotState();


        testRoom.printFloor();
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

