package com.example.RobotSimulatorProject.service;

import com.example.RobotSimulatorProject.controller.RobotInput;
import com.example.RobotSimulatorProject.exceptions.InvalidInputException;
import com.example.RobotSimulatorProject.exceptions.InvalidPositionException;
import com.example.RobotSimulatorProject.model.Grid;
import com.example.RobotSimulatorProject.model.MovementCommands;
import com.example.RobotSimulatorProject.model.Orientation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RobotTableGridNavigationSimulator {
    private static final Logger logger= LoggerFactory.getLogger(RobotTableGridNavigationSimulator.class);
    Grid grid;
    public String simulateRobotMovement(RobotInput robotInput)  {
       try {
           if(robotInput.getGridRowSize()<=0 || robotInput.getGridColSize()<=0)
           {
               throw new InvalidInputException("Grid Size should be greater than 0");
           }

           //Grid Initialization
           grid = new Grid(robotInput.getGridRowSize(), robotInput.getGridColSize());
           grid.updateGridPosition(robotInput.getInitialRowPosition(), robotInput.getInitialColPosition());
           grid.setOrientation(robotInput.getInitialOrientation());
           logger.debug("Initial Position and Orientation: {} {} {} ",grid.getCurrentRowPosition(),grid.getCurrentColumnPosition(),grid.getCurrentOrientation());
           for (MovementCommands command : robotInput.getMovementCommands()) {
               if( command==null)
               {
                   throw new InvalidPositionException("Movement command cannot be null");
               }

               logger.debug("Command: {}", command);
               navigate(command);
               logger.debug("Current Position and Orientation: {} {} {} ",grid.getCurrentRowPosition(),grid.getCurrentColumnPosition(),grid.getCurrentOrientation());
               if(grid.getCurrentRowPosition()<0 || grid.getCurrentRowPosition() >=robotInput.getGridRowSize()||grid.getCurrentColumnPosition()<0 || grid.getCurrentColumnPosition() >=robotInput.getGridColSize())
               {
                   throw new InvalidPositionException("Command "+command+" caused the robot to move to the invalid position ("+grid.getCurrentRowPosition()+","+grid.getCurrentColumnPosition()+") which is out of bounds");
               }
           }


       } catch (InvalidPositionException e) {
           logger.error("Invalid Position:{}",e.getMessage());
           return e.getMessage();
       }
   return String.format("%d %d %s",grid.getCurrentRowPosition(),grid.getCurrentColumnPosition(),grid.getCurrentOrientation().getOrientationCode());
    }


    private void navigate(MovementCommands command) throws InvalidPositionException {

        switch (command) {
            case MOVE_FORWARD -> moveForward();
            case ROTATE_NINETY_DEGREES_LEFT -> rotateLeft();
            case ROTATE_NINETY_DEGREES_RIGHT -> rotateRight();
        }
    }
    // M or MOVE_FORWARD functionality
    private void moveForward() throws InvalidPositionException {
        switch(grid.getCurrentOrientation()){
            case NORTH ->grid.updateRowPosition(grid.getCurrentRowPosition()-1);
            case SOUTH ->grid.updateRowPosition(grid.getCurrentRowPosition()+1);
            case EAST ->grid.updateColumnPosition(grid.getCurrentColumnPosition()+1);
            case WEST ->grid.updateColumnPosition(grid.getCurrentColumnPosition()-1);

        }

    }

    // L or ROTATE_NINETY_DEGREES_LEFT functionality
    private void rotateLeft() throws InvalidPositionException {
        switch(grid.getCurrentOrientation()){
            case NORTH ->grid.setOrientation(Orientation.WEST);
            case EAST -> grid.setOrientation(Orientation.NORTH);
            case SOUTH ->grid.setOrientation(Orientation.EAST);
            case WEST -> grid.setOrientation(Orientation.SOUTH);
        }
    }

    // R or ROTATE_NINETY_DEGREES_RIGHT functionality
    private void rotateRight() throws InvalidPositionException {
        switch(grid.getCurrentOrientation()){
            case NORTH ->grid.setOrientation(Orientation.EAST);
            case EAST -> grid.setOrientation(Orientation.SOUTH);
            case SOUTH ->grid.setOrientation(Orientation.WEST);
            case WEST -> grid.setOrientation(Orientation.NORTH);
        }
// if new commands are extended their corresponding functionalities can be added here
    }
}
