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

public class RoomTest {

    // Test Function #11
    // Test type : Functional, blackbox
    // Input : No input.
    // Description : Room has been created but not initialized yet.
    // Expected output : <isInitialized() false>
    // Tester : Nicholas Harris
    // Date : 9th February
    @Test
    public void testUninitializedRoom() {
        Room testRoom = new Room();
        assertFalse(testRoom.isInitialized());
    }

    // Test Function #12
    // Test type : Functional, blackbox
    // Input : size = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}
    // Description : Room initialized to size N specified by first checking if it has been initialized and then comparing value of size N specified (2D) to actual room size
    // Expected output : <isInitialized() true getFloorSize() size>
    // Tester : Nicholas Harris
    // Date : 9th February
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    public void testRoomInitialization(int size) {
        Room testRoom = new Room(size);
        assertTrue(testRoom.isInitialized());
        assertEquals(size, testRoom.getFloorSize(), String.format("Room size entered: %d is not equal to room size returned: %d", size, testRoom.getFloorSize()));
    }

    // Test Function #13
    // Test type : Functional, blackbox
    // Input : size = {7, 8, 1, 3, 5, 10, 8} <Room(n) size>
    // Description : Determine if a created robot is initialized to all the correct values while a room at size N specified
    // Expected output : <isInitialized() true getFloorSize() size getRobotRow() 0 getRobotCol() 0 getRobotDirDescription() north isPenDown() false>
    // Tester : Nicholas Harris
    // Date : 9th February
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

    // Test Function #14
    // Test type : Functional, blackbox
    // Input : moves = {7, 8, 1, 3, 5, 10, 8}, <Room(n) 10 moveRobot() moves setRobotDirection() 1 moveRobot() moves>
    // Description : Robot can move on the floor accurately depending on which direction and number of moves are provided.
    // Expected output : <isInitialized() true getFloorSize() 10 getRobotRow() 0 getRobotCol 0 getRobotDirDescription north isPenDown() false ...
    // getRobotRow() n-1 getRobotCol 0 getRobotDirDescription north isPenDown() false ...
    // getRobotRow() n-1 getRobotCol n-1 getRobotDirDescription east isPenDown() false floorArr[i] int[n]>
    // Tester : Nicholas Harris
    // Date : 9th February
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

    // Test Function #15
    // Test type : Functional, blackbox
    // Input : moves = {4, 2, 3} <Room(n) 10 setRobotDirection() -1 moveRobot() moves>
    // Description : Robot doesn't go out of bounds (beyond the wall of room) when given instructions to.
    // Robot at initial position of 0,0 turned west and moved by N moves. Should remain at 0,0.
    // Expected output : <getRobotRow() 0 getRobotCol() 0 getRobotDirDescription() west>
    // Tester : Ali Turkman
    // Date : 9th February
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

    // Test Function #16
    // Test type : Functional, blackbox
    // Input : <Room(n) 10 movePen() true false>.
    // Description : Pen can be turned up and down when asserted.
    // Expected output : <isPenDown() false true false >
    // Tester : Nicholas Harris
    // Date : 9th February
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

