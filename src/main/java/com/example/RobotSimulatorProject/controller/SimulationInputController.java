package com.example.RobotSimulatorProject.controller;

import com.example.RobotSimulatorProject.utils.FileUtils;
import com.example.RobotSimulatorProject.utils.RobotInputParser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.example.RobotSimulatorProject.service.RobotTableGridNavigationSimulator;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;

@RestController
@Tag(name ="Simulation Input Controller", description="handles input from the robot simulator")
public class SimulationInputController {

    //String input type
    @PostMapping("/inputString")
    @Operation(summary="Handle String input", description="Accepts a text input for simulation:    Input should be 3 lines of format(with example): \n\n"+
            "5 5"+"\n" +
            "\n1 2 S"+"\n" +
            "\nMRMLM"+"\n\n"+
            "5 5// format: row column// Grid Size  \n"+
            "1 2 S// format: initialRowPosition initialColumnPosition initialOrientation// Initial Values  \n"+
            "MRMLM// format: movementCommands// M-MoveForward L-Rotate left 90degrees R--Rotate right 90degrees  \n\n"+
            "Provide movement commands in single String without spaces  \n"+
            "DELETE THE INSTRUCTIONS AND REPLACE WITH 3 LINES OF INPUT OF ABOVE FORMAT")
    public String handleStringInput(@RequestBody @Parameter String input) {
        return processInput(input);
    }

    //File input type
    @PostMapping(value="/inputFile",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary="Handle File input", description="Accepts a text file input for simulation click on "+ "  Choose file button to upload the simulation input file(.txt file should be created with input in the format mentioned in previous option)")
    public String handleFileInput(@Parameter(description="Text file to be uploaded",required=true,content=@Content(mediaType="multipart/form-data"))@RequestParam("file") MultipartFile file) {
        try{
            String InputString= FileUtils.convertFileToString(file);
            return processInput(InputString);
        }catch(IOException e){
           return "Error reading the fie " + e.getMessage();
        }
    }

    private String processInput(String input) {
        try{
            RobotInput robotInput= RobotInputParser.parseInput(input);
            RobotTableGridNavigationSimulator simulator=new RobotTableGridNavigationSimulator();
            return simulator.simulateRobotMovement(robotInput) ;
        } catch (Exception e) {
            return "Error processing input in the fie " + e.getMessage();
        }
    }


}
