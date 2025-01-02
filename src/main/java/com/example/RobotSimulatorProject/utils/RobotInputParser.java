package com.example.RobotSimulatorProject.utils;

import com.example.RobotSimulatorProject.controller.RobotInput;
import com.example.RobotSimulatorProject.exceptions.InvalidInputException;
import com.example.RobotSimulatorProject.model.MovementCommands;
import com.example.RobotSimulatorProject.model.Orientation;

import java.util.ArrayList;
import java.util.List;

public class RobotInputParser {

    public static RobotInput parseInput(String input) throws InvalidInputException {
        input = input.trim();  // trim the unwanted spaces in the start/end of the string values
        String[] lines = input.split("\n");
        List<String> filteredLines = new ArrayList<String>();
        //filter out the empty lines
        for (String line : lines) {
            line = line.trim();
            if (!line.isEmpty()) {
                filteredLines.add(line);
            }
        }
        if (filteredLines.size() !=3) {
            throw new InvalidInputException("Invalid input should be exactly 3 lines");
        }

        //parse the grid size
        String[] gridSize= filteredLines.get(0).split(" ");
        if (gridSize.length != 2) {
            throw new InvalidInputException("GriD Size should be exactly 2 Numbers");
        }
        int rowSize,columnSize;
        try{
            rowSize = Integer.parseInt(gridSize[0]);
            columnSize = Integer.parseInt(gridSize[1]);
            if(rowSize<=0 || columnSize<=0) {
                throw new InvalidInputException("Grid Size should be greater than 0");
            }
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Grid Size should be valid  integer numbers");
        }
        String[] initialPosition = filteredLines.get(1).split(" ");
        if (initialPosition.length != 3) {
            throw new InvalidInputException("Initial position should be exactly 3 Numbers");
        }

        int initialRowPosition,initialColumnPosition;
        try{
            initialRowPosition = Integer.parseInt(initialPosition[0]);
            initialColumnPosition = Integer.parseInt(initialPosition[1]);
            if(initialRowPosition<=0 || initialColumnPosition<=0) {
                throw new InvalidInputException("Invalid : Initial row and initial column position both should be greater than 0");
            }

        }catch (NumberFormatException e) {
            throw new InvalidInputException("Initial position should be valid  integer numbers");
        }
        Orientation initialOrientation = Orientation.getOrientation(initialPosition[2].charAt(0));
        if(initialOrientation == null){
            throw new InvalidInputException("Initial orientation/Direction cannot be null");
        }

        //parsing Commands
        String Commands=filteredLines.get(2).toUpperCase();
        List<MovementCommands> movementCommands= new ArrayList<>();
        for(char command: Commands.toCharArray()){
            MovementCommands movementCommand =  MovementCommands.getMovementCommand(command);
            if(movementCommand == null){
                throw new InvalidInputException("Movement command cannot be null");
            }
            movementCommands.add(movementCommand);
        }

        RobotInput robotInput= new RobotInput();
        robotInput.setGridRowSize(rowSize);
        robotInput.setGridColSize(columnSize);
        robotInput.setInitialRowPosition(initialRowPosition);
        robotInput.setInitialColPosition(initialColumnPosition);
        robotInput.setMovementCommands(movementCommands);
        robotInput.setInitialOrientation(initialOrientation);
        return robotInput;

    }
}
