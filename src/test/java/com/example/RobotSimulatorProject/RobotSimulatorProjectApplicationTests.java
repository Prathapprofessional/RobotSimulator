package com.example.RobotSimulatorProject;

import com.example.RobotSimulatorProject.controller.RobotInput;
import com.example.RobotSimulatorProject.exceptions.InvalidInputException;
import com.example.RobotSimulatorProject.exceptions.InvalidPositionException;
import com.example.RobotSimulatorProject.model.MovementCommands;
import com.example.RobotSimulatorProject.model.Orientation;
import com.example.RobotSimulatorProject.service.RobotTableGridNavigationSimulator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class RobotSimulatorProjectApplicationTests {

	private RobotTableGridNavigationSimulator simulator;

	@BeforeEach
	void setUp() {
		simulator = new RobotTableGridNavigationSimulator();
	}

	@Test
	void contextLoads() {
	}

	//Testing the simulate robot Movement functionalities which also tests the update grid functionality of Grid
	@Test
	void testSimulateRobotMovement_combinationofMoves() throws InvalidInputException {
		RobotInput robotInput =createRobotInput(5,5,2,2,Orientation.NORTH,
				Arrays.asList(MovementCommands.MOVE_FORWARD,MovementCommands.ROTATE_NINETY_DEGREES_RIGHT));
		String result= simulator.simulateRobotMovement(robotInput);
		assertEquals("1 2 E",result);
	}
	@Test
	void testSimulateRobotMovement_repeatedMoves() throws InvalidPositionException{
		RobotInput robotInput =createRobotInput(5,5,2,2,Orientation.NORTH,
				Arrays.asList(MovementCommands.MOVE_FORWARD,MovementCommands.MOVE_FORWARD));
		String result= simulator.simulateRobotMovement(robotInput);
		assertEquals("0 2 N",result);
	}

	@Test
	void testSimulateRobotMovement_multipleRotations() throws InvalidPositionException{
		RobotInput robotInput =createRobotInput(5,5,2,2,Orientation.NORTH,
				Arrays.asList(MovementCommands.ROTATE_NINETY_DEGREES_LEFT,MovementCommands.ROTATE_NINETY_DEGREES_LEFT));
		String result= simulator.simulateRobotMovement(robotInput);
		assertEquals("2 2 S",result);
	}

	@Test
	void testSimulateRobotMovement_outOfBounds() {
		RobotInput robotInput =createRobotInput(5,5,1,2,Orientation.NORTH,
				Arrays.asList(MovementCommands.ROTATE_NINETY_DEGREES_LEFT,MovementCommands.MOVE_FORWARD,MovementCommands.ROTATE_NINETY_DEGREES_LEFT,
						MovementCommands.MOVE_FORWARD,MovementCommands.MOVE_FORWARD,MovementCommands.ROTATE_NINETY_DEGREES_RIGHT,MovementCommands.MOVE_FORWARD,MovementCommands.MOVE_FORWARD,
						MovementCommands.ROTATE_NINETY_DEGREES_LEFT,MovementCommands.ROTATE_NINETY_DEGREES_RIGHT,MovementCommands.MOVE_FORWARD));

		String result=simulator.simulateRobotMovement(robotInput);


		assertEquals("Command MOVE_FORWARD caused the robot to move to the invalid position (3,-1) which is out of bounds", result);
	}

	@Test
	void testSimulateRobotMovement_invalidInitialPosition() throws InvalidPositionException {
		RobotInput robotInput =createRobotInput(5,5,-1,-1,Orientation.NORTH,
				Arrays.asList(MovementCommands.MOVE_FORWARD));
		Exception exception =assertThrows(IllegalArgumentException.class,()->simulator.simulateRobotMovement(robotInput));
		assertEquals("Invalid : Initial row and initial column position both should be greater than 0",exception.getMessage());
	}

	@Test
	void testSimulateRobotMovement_nullInitialmovement() throws InvalidPositionException {
		RobotInput robotInput =createRobotInput(5,5,1,1,null,
				Arrays.asList(MovementCommands.MOVE_FORWARD));
		Exception exception =assertThrows(IllegalArgumentException.class,()->simulator.simulateRobotMovement(robotInput));
		assertEquals("Invalid : Initial orientation cannot be null",exception.getMessage());
	}


	@Test
	void testSimulateRobotMovement_nullMovementCommand() throws InvalidPositionException {
		RobotInput robotInput =createRobotInput(5,5,2,2,Orientation.NORTH,
				Arrays.asList(MovementCommands.MOVE_FORWARD,null));
		String result= simulator.simulateRobotMovement(robotInput);

		assertEquals("Movement command cannot be null",result);
	}

	@Test
	void testSimulateRobotMovement_invalidGrid() throws InvalidInputException {
		RobotInput robotInput =createRobotInput(0,0,2,2,Orientation.NORTH,
				Arrays.asList(MovementCommands.MOVE_FORWARD));
		Exception exception =assertThrows(InvalidInputException.class,()->simulator.simulateRobotMovement(robotInput));

		assertEquals("Grid Size should be greater than 0",exception.getMessage());
	}


	private RobotInput createRobotInput(int rows, int columns, int initialRow, int initialColumn, Orientation orientation,
										List<MovementCommands> commands) throws InvalidPositionException{
	RobotInput input = new RobotInput();
	input.setGridRowSize(rows);
	input.setGridColSize(columns);
	input.setInitialRowPosition(initialRow);
	input.setInitialColPosition(initialColumn);
	input.setInitialOrientation(orientation);
	input.setMovementCommands(commands);
	return input;
	}

}
