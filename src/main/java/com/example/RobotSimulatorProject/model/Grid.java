package com.example.RobotSimulatorProject.model;

import com.example.RobotSimulatorProject.exceptions.InvalidPositionException;

//Grid is made up of rows and columns
public class Grid {
    int rowSize;
    int columnSize;
    int currentRowPosition;
    int currentColumnPosition;
    Orientation currentOrientation;

    //Constructor- creates a grid of given rowSize and columnSize
    public Grid(int rowSize, int columnSize) {
        this.rowSize = rowSize;
        this.columnSize = columnSize;
    }

    //updates the grid position to specific rowPosition and columnPosition
    //grid co-ordinates start from (0,0)
    //throws InvalidPositionException when the position values are out of the grid
    public void updateGridPosition(int rowPosition, int columnPosition) throws InvalidPositionException{
        if(rowPosition<0 || rowPosition>=rowSize || columnPosition<0 || columnPosition>=columnSize) {
            throw new InvalidPositionException("Invalid position as the position: (" + rowPosition + "," + columnPosition + ") is out of the grid bounds");
        }
        currentRowPosition = rowPosition;
        currentColumnPosition = columnPosition;
    }

    //updates the row position to specific rowPosition
    public void updateRowPosition(int rowPosition) {
        currentRowPosition = rowPosition;
  }
    //updates the column position to specific columnPosition
    public void updateColumnPosition(int columnPosition) {
        currentColumnPosition = columnPosition;
}

    public Orientation getCurrentOrientation() {
        return currentOrientation;
    }

    // getting the current column position
    public int getCurrentColumnPosition() {
        return currentColumnPosition;
    }

    // getting the current row position
    public int getCurrentRowPosition() {
        return currentRowPosition;
    }

    public void setOrientation(Orientation orientation) {
        currentOrientation = orientation;
    }


}
