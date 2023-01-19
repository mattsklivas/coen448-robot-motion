public class Room {
    // Robot walks in room on floor
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

    // Turn robot left (L/l) or right (R/r)
    public void turnRobot(int rotateDirection) {
        robot.setRobotDirection(rotateDirection);
    }

    // Move robot forward s spaces (M s/m s)
    // TODO: Add check for s > 0
    // Better ways to do this tbh based on the diretion should just do a bfs/dfs and mark the path
    // this will handle running up to the wall etc
    public void moveRobot(int s) {
        // Get current direction
        int robDir = robot.getRobotDirection();

        // Vertical move (0 - Up, 2 - Down)
        if(robDir == 0 || robDir == 2) {
            // Get and update robot column index by s spaces
            // Moving up (0) and down (2) by increasing and decreasing col index respectively
            int currCol = robot.getRobotCol();
            currCol = (robDir == 0) ? currCol + s : currCol - s;

            // Check new column index to remain in range
            if(currCol >= floorSize) {
                System.out.println("Error - Invalid Vertical Move!");
            } else {
                robot.setRobotCol(currCol);
            }
        // Horizontal move (1 - Right, 3 - Left)
        } else {
            // Get and update robot row index by s spaces
            // Moving right (1) and left (3) by increasing and decreasing row index respectively
            int currRow = robot.getRobotCol();
            currRow = (robDir == 1) ? currRow + s : currRow - s;

            // Check new row index to remain in range
            if (currRow >= floorSize) {
                System.out.println("Error - Invalid Horizontal Move!");
            } else {
                robot.setRobotRow(currRow);
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
                // Robot passed over floor tile (1 as *)
                if(floor[i][j] == 1)
                    System.out.print("* ");
                // Robot has NOT visited floor tile yet (0 as blank)
                else
                    System.out.print("  ");
            }

            System.out.println();
        }

        // Print col indices
        for(int k = 0; k < floorSize; k++) {
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
