package com.app;

import java.lang.Math;

public class Robot {
    // Current robot position in room
    private int robotRow, robotCol;

    // com.app.Robot pen position as either up or down
    // Up (False), Down (True)
    private boolean isPenDown;

    // com.app.Robot pen direction
    // Up/North (0), Right/East (1), Down/South (2), Left/West (3)
    // Note: Vertical is Even, Horizontal is Odd
    private int robotDirection;

    public Robot() {
        // com.app.Robot starts at (0, 0)
        this.robotRow = 0;
        this.robotCol = 0;

        // Pen initially up and north
        this.isPenDown = false;
        this.robotDirection = 0;
    }

    // Move up (+y) and down (-y)
    public void incrementRobotRow(int inc) {
        this.robotRow += inc;
    }

    // Move left (-x) and right (+x)
    public void incrementRobotCol(int inc) {
        this.robotCol += inc;
    }

    // Update current col
    public void setRobotCol(int col) {
        this.robotCol = col;
    }

    public void setRobotRow(int row) {
        this.robotRow = row;
    }

    public void setIsPenDown(boolean state) {
        this.isPenDown = state;
    }

    public void setRobotDirection(int rotateDirection) {
        // Update direction based on rotation direction
        // Rotate left (-1) and right (+1)
        this.robotDirection = Math.floorMod(this.robotDirection + rotateDirection, 4);
    }
    
    public int getRobotCol() {
        return this.robotCol;
    }

    public int getRobotRow() {
        return this.robotRow;
    }

    public boolean isPenDown() {
        return this.isPenDown;
    }

    public int getRobotDirection() {
        return this.robotDirection;
    }

    // format: (row, col)
    public String getRobotDescription() {
        return this.robotRow + ", " + this.robotCol;
    }

    public String getPenPosition() {
        return this.isPenDown ? "down" : "up";
    }

    public String getRobotDirDescription() {
        if(this.robotDirection == 0)
            return "north";
        else if(this.robotDirection == 1)
            return "east";
        else if(this.robotDirection == 2)
            return "south";
        else
            return "west";
    }
}

