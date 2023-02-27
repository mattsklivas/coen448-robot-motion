package com.app;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.*;


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
    // Description : Room initialized to size N specified by first checking if it has been initialized and then comparing value of size N specified (2D) to actual room size.
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
    // Description : Determine if a created robot is initialized to all the correct values while a room at size N specified.
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

    // Test Function #25
    // Test type : Functional, blackbox
    // Input : <Room(n) 10 printRobotState() setIsPenDown true moveRobot(moves) 4 setRobotDirection(dir) 1 printRobotState()>.
    // Description : Print default and changed robot state after robot move and rotation with pen down
    // Expected output : < Default robot state, Modified robot state >
    // Tester : Nicholas Kawwas
    // Date : 26th February
    @Test
    public void testPrintRobotState() throws IOException {
        // Save default output stream
        PrintStream defaultPrint = System.out;

        // Capture output stream
        ByteArrayOutputStream outStreamCap = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStreamCap));

        int n = 10;
        int moveNorth = 4;
        int rotateClockwise = 1;

        int currCol = 0;

        Room testRoom = new Room(n);
        Robot testRobot = testRoom.getRobot();

        // Get default robot state
        testRoom.printRobotState();
        assertEquals("Position: " + currCol + ", 0 - Pen: up - Facing: north", outStreamCap.toString().replaceAll("\r|\n", ""));

        // Pen down, moved north then rotated east
        testRobot.setIsPenDown(true);
        testRoom.moveRobot(moveNorth);
        currCol += moveNorth;
        testRobot.setRobotDirection(rotateClockwise);

        // Reset stream to compare next print
        outStreamCap.reset();

        // Get print state after robot move and rotation with pen change
        testRoom.printRobotState();
        assertEquals("Position: " + currCol + ", 0 - Pen: down - Facing: east", outStreamCap.toString().replaceAll("\r|\n", ""));

        // Reset output stream
        System.setOut(defaultPrint);
        outStreamCap.close();
    }


    /*
     Test Function #26
     Test type : Functional, blackbox
     Input : <Room(n) 10 printFloor() movePen() true moveRobot(movesNorth) 4 setRobotDirection(dir) 1 moveRobot(movesEast) 2 printFloor()>.
     Description : Print default and changed room floor line by line
     Expected output : < Default empty room floor, Modified room floor >
     Tester : Nicholas Kawwas
     Date : 26th February
     */
    @Test
    public void testPrintRoomState() throws IOException {
        // Save default output stream
        PrintStream defaultPrint = System.out;

        // Capture output stream
        ByteArrayOutputStream outStreamCap = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStreamCap));

        int n = 10;
        int moveNorth = 4;
        int moveEast = 2;
        int rotateClockwise = 1;

        // Prepare constants for empty line, final line
        int outIndex = n;
        String finalLine = "\\";
        String emptyLine = "  ";
        for (int i = 0; i < n; i++) {
            finalLine += "  " + i;
            emptyLine += "  " + " ";
        }
        finalLine += "  ";

        // Prepare constants for moves up and east
        String upMoveLine = " ";
        String eastMoveLine = " ";
        for (int i = 0; i < n; i++) {
            if(i == 0) {
                upMoveLine += " * " + " ";
            } else {
                upMoveLine += "  " + " ";
            }

            if(i < moveEast) {
                eastMoveLine += " * ";
            } else if (i == moveEast) {
                eastMoveLine += " * " + " ";
            } else {
                eastMoveLine += "  " + " ";
            }
        }


        Room testRoom = new Room(n);
        Robot testRobot = testRoom.getRobot();

        // Get default floor
        testRoom.printFloor();

        BufferedReader br = new BufferedReader(
                new StringReader(outStreamCap.toString())
        );

        // Test printing empty room
        String expectedOut;
        String actualOut;
        while ((actualOut = br.readLine()) != null) {
            outIndex--;
            if(outIndex < 0) {
                expectedOut =  finalLine;
            } else {
                expectedOut =  "" + outIndex + emptyLine;
            }

            assertEquals(expectedOut, actualOut);
        }

        // Reset stream to compare next print
        outStreamCap.reset();

        // Pen down, moved north then rotated east
        testRoom.movePen(true);
        testRoom.moveRobot(moveNorth);
        testRobot.setRobotDirection(rotateClockwise);
        testRoom.moveRobot(moveEast);

        // Get floor with moves and rotation
        testRoom.printFloor();

        // Test printing traversed room
        br = new BufferedReader(
                new StringReader(outStreamCap.toString())
        );
        outIndex = n;
        while ((actualOut = br.readLine()) != null) {
            outIndex--;
            if(outIndex < 0) {
                expectedOut =  finalLine;
            } else {
                expectedOut = "" + outIndex;
                if(outIndex == moveNorth) {
                    expectedOut += eastMoveLine;
                } else if(outIndex < moveNorth) {
                    expectedOut += upMoveLine;
                } else {
                    expectedOut += emptyLine;
                }
            }

            assertEquals(expectedOut, actualOut);
        }

        // Reset output stream
        System.setOut(defaultPrint);
        outStreamCap.close();
    }
}

