package com.example.RobotSimulatorProject.model;

public enum MovementCommands {
    MOVE_FORWARD('M'),
    ROTATE_NINETY_DEGREES_LEFT('L'),
    ROTATE_NINETY_DEGREES_RIGHT('R');
    //New commands can be added in future and in turn the functionalities can also be extended accordingly

    private final char command;
    MovementCommands(char command) {
        this.command = command;
    }
    public char getCommand() {
        return command;
    }

    public static MovementCommands getMovementCommand(char command) {
        for (MovementCommands movementCommand : MovementCommands.values()) {
            if (movementCommand.getCommand() == command) {
                return movementCommand;
            }
        }
        return null;
    }
}
