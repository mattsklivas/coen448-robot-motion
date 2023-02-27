package com.app;

public class Room {
    // com.app.Robot walks in room on floor
    private Robot robot;

    // Floor contains robot trace path
    private int floor[][];
    private int floorSize;
    private boolean isInitialized;

    public Room() {
        robot = new Robot();
        isInitialized = false;
    }

    // Initialize the system - robot and floor (I n/i n)
    public Room(int n) {
        // Initialize robot
        robot = new Robot();

        // Initialize floor
        floor = new int[n][n];
        floorSize = n;
        isInitialized = true;
    }

    // Move pen up (U/u) or down (D/d)
    public void movePen(boolean isPenDown) {
        robot.setIsPenDown(isPenDown);

        // Set current tile to 1 when putting pen down
        if (robot.isPenDown())
            this.floor[robot.getRobotRow()][robot.getRobotCol()] = 1;
    }

    // Move robot forward s spaces (M s/m s)
    public void moveRobot(int spaces) {
        // Get current direction
        int robotDir = robot.getRobotDirection();

        // Moving down (2) and left (3) with negative offset
        // Moving up (0) and right (1) with positive offset
        int offset = robotDir < 2 ? 1 : -1;

        // Moving vertical (up 0, down 2) or horizontal (right 1, left 3)
        boolean isHorizontal = robotDir % 2 != 0 ? true : false;

        // Get initial position: vertical move -> col, horizontal move -> row
        int initialPos = isHorizontal ? robot.getRobotCol() : robot.getRobotRow();

        int newPos = 0;
        for(int i = 1; i <= spaces; i++) {
            newPos = initialPos + i*offset;
            if (newPos >= floorSize || newPos < 0) {
                System.out.println("Robot is at the edge of the room");
                break;
            }

            // Modify row if moving horizontal
            if(isHorizontal)
                robot.incrementRobotCol(offset);
            // Modify col otherwise (moving vertical)
            else
                robot.incrementRobotRow(offset);

            // Draw if pen down
            if (robot.isPenDown())
                this.floor[robot.getRobotRow()][robot.getRobotCol()] = 1;
        }
    }

    // Print pen position and direction (C/c)
    public void printRobotState() {
        System.out.printf("Position: %s - Pen: %s - Facing: %s%n",
                        robot.getRobotDescription(), robot.getPenPosition(), robot.getRobotDirDescription());
    }

    // Print floor matrix with indices (P/p)
    public void printFloor() {
        for(int i = floorSize - 1; i >= 0; i--) {
            // Print row indices
            System.out.printf("%-3d", i);

            for(int j = 0; j < floorSize; j++) {
                // com.app.Robot passed over floor tile (1 as *)
                if(floor[i][j] == 1)
                    System.out.printf("%-3s", "*");
                // com.app.Robot has NOT visited floor tile yet (0 as blank)
                else
                    System.out.printf("%-3s", " ");
            }

            System.out.println();
        }

        // Print col indices
        for(int k = 0; k < floorSize; k++) {
            if (k == 0) {
                System.out.printf("%-3s", "\\");
            }
            System.out.printf("%-3d", k);
        }

        System.out.println();
    }

    public Robot getRobot() {
        return this.robot;
    }

    public int[][] getFloor() {
        return floor;
    }

    public int getFloorSize() {
        return floorSize;
    }

    public boolean isInitialized() {
        return isInitialized;
    }
}
