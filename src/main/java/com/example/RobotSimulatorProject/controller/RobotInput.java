package com.example.RobotSimulatorProject.controller;

import com.example.RobotSimulatorProject.model.MovementCommands;
import com.example.RobotSimulatorProject.model.Orientation;

import java.util.ArrayList;
import java.util.List;

public class RobotInput {
    private int gridRowSize;
    private int gridColSize;
    private int initialRowPosition;
    private int initialColPosition;
    private Orientation initialOrientation;
    private List<MovementCommands> movementCommands;

    //Getters and Setters
    public int getGridRowSize() {
        return gridRowSize;
    }

    public void setGridRowSize(int gridRowSize) {
        this.gridRowSize = gridRowSize;
    }

    public int getGridColSize() {
        return gridColSize;
    }

    public void setGridColSize(int gridColSize) {
        this.gridColSize = gridColSize;
    }

    public int getInitialRowPosition() {
        if (initialRowPosition <0) {
            throw new IllegalArgumentException("Invalid : Initial row and initial column position both should be greater than 0");
        }
        return initialRowPosition;
    }

    public void setInitialRowPosition(int initialRowPosition) {
        this.initialRowPosition = initialRowPosition;
    }

    public int getInitialColPosition() {
        if (initialColPosition <0) {
            throw new IllegalArgumentException("Invalid : Initial row and initial column position both should be greater than 0");
        }
        return initialColPosition;
    }

    public void setInitialColPosition(int initialColPosition) {
        this.initialColPosition = initialColPosition;
    }

    public Orientation getInitialOrientation() {
        if (initialOrientation ==null) {
            throw new IllegalArgumentException("Invalid : Initial orientation cannot be null");
        }
        return initialOrientation;
    }

    public void setInitialOrientation(Orientation initialOrientation) {
        this.initialOrientation = initialOrientation;
    }

    public List<MovementCommands> getMovementCommands() {
        return movementCommands;
    }
    public void setMovementCommands(List<MovementCommands> movementCommands)
    {
        this.movementCommands = movementCommands;
    }
}
