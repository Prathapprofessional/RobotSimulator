package com.example.RobotSimulatorProject.model;

public enum Orientation {
    NORTH('N'),SOUTH('S'),EAST('E'),WEST('W');
    private final char OrientationCode;
    Orientation(char OrientationCode) {
        this.OrientationCode = OrientationCode;
    }
    public static Orientation getOrientation(char Orientation) {
        for (Orientation o : values()) {
            if (o.OrientationCode == Orientation) {
                return o;
            }
        }
            return null;
        }

    public char getOrientationCode() {
        return OrientationCode;
    }
}
