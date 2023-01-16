public class Robot {
    // Current robot position in room
    private int currCol, currRow;

    // Robot pen position as either up or down
    // Up (False), Down (True)
    private boolean isPenDown;

    // Robot pen direction
    // Up/North (0), Right/East (1), Down/South (2), Left/West (3)
    // Note: Vertical is Even, Horizontal is Odd
    private int penDirection;

    public Robot() {
        // Robot starts at (0, 0)
        currCol = 0;
        currRow = 0;

        // Pen initially up and north
        isPenDown = false;
        penDirection = 0;
    }

    public void setRobotCol(int newCol) {
        currCol = newCol;
    }

    public void setRobotRow(int newRow) {
        currRow = newRow;
    }

    public void setPenPosition(boolean isPenStillDown) {
        isPenDown = isPenStillDown;
    }

    public void setPenDirection(int rotateDirection) {
        // Update direction based on rotation direction
        // Rotate left (-1) and right (+1)
        penDirection = (penDirection + rotateDirection) % 4;
    }

    public int getRobotCol() {
        return currCol;
    }

    public int getRobotRow() {
        return currRow;
    }

    public boolean getPenPosition() {
        return isPenDown;
    }

    public int getPenDirection() {
        return penDirection;
    }

    public String getRobotDescription() {
        return currRow + ", " + currCol;
    }

    public String getPenPosDescription() {
        return isPenDown ? "down" : "up";
    }

    public String getPenDirDescription() {
        if(penDirection == 0)
            return "north";
        else if(penDirection == 1)
            return "east";
        else if(penDirection == 2)
            return "south";
        else
            return "west";
    }
}

