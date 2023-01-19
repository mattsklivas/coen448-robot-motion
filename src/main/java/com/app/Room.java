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
    }

    private void dfs(int pos, int offset, int initialPos, int spaces) {
        if (pos == this.floorSize - 1) {
            return;
        }

        if (pos < 0) {
            return;
        }

        if (robot.isPenDown()) {
            this.floor[pos][robot.getRobotCol()] = 1;
        }

        if (pos >= initialPos + spaces * offset) {
            return;
        }

        robot.incrementRobotRow(offset);

        dfs(pos + offset, offset, initialPos, spaces);
    }

    private void bfs(int pos, int offset, int initialPos, int spaces) {
        if (pos == this.floorSize - 1) {
            return;
        }

        if (pos < 0) {
            return;
        }

        if (robot.isPenDown()) {
            this.floor[robot.getRobotRow()][pos] = 1;
        }

        if (pos >= initialPos + spaces * offset) {
            return;
        }


        robot.incrementRobotCol(offset);

        bfs(pos + offset, offset, initialPos, spaces);
    }


    // Move robot forward s spaces (M s/m s)
    public void moveRobot(int spaces) {
        // Get current direction
        int robotDir = robot.getRobotDirection();
        int offset = robotDir < 2 ? 1 : -1;
        
        // method: 0 -> dfs, 1 -> bfs
        boolean type = robotDir % 2 != 0 ? true : false;

        // Get initial position: vertical move -> col, horizontal move -> row
        int initialPos = type ? robot.getRobotCol() : robot.getRobotRow();

        if (!type) {
            if (initialPos < this.floorSize && initialPos >= 0) {
                if (robot.isPenDown()) {
                    this.floor[initialPos][robot.getRobotCol()] = 1;
                }

                robot.incrementRobotRow(offset);

                dfs(initialPos + offset, offset, initialPos, spaces);
            } else {
                System.out.println("com.app.Robot is at the edge of the room");
            }
        } else {
            if (initialPos < this.floorSize && initialPos >= 0) {
                if (robot.isPenDown()) {
                    this.floor[robot.getRobotRow()][initialPos] = 1;
                }

                robot.incrementRobotCol(offset);

                bfs(initialPos + offset, offset, initialPos, spaces);
            } else {
                System.out.println("com.app.Robot is at the edge of the room");
            }
        }
    }

    // Print pen position and direction (C/c)
    public void printRobotState() {
        System.out.println(String.format("Position: %s - Pen: %s - Facing: %s", 
                        robot.getRobotDescription(), robot.getPenPosition(), robot.getRobotDirDescription()));
    }

    // Print floor matrix with indices (P/p)
    public void printFloor() {
        for(int i = floorSize - 1; i >= 0; i--) {
            // Print row indices
            System.out.print(i + " ");

            for(int j = 0; j < floorSize; j++) {
                // com.app.Robot passed over floor tile (1 as *)
                if(floor[i][j] == 1)
                    System.out.print("* ");
                // com.app.Robot has NOT visited floor tile yet (0 as blank)
                else
                    System.out.print("  ");
            }

            System.out.println();
        }

        // Print col indices
        for(int k = 0; k < floorSize; k++) {
            if (k == 0) {
                System.out.print("\\ ");
            }
            System.out.print(k + " ");
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
