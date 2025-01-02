package com.example.RobotSimulatorProject.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.stream.Collectors;

public class FileUtils {

    //Converting multipart file content to string for processing
    //throws IO exception if any I/O error occurs
    public static String convertFileToString(MultipartFile file) throws IOException {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream())))
        {return reader.lines().collect(Collectors.joining("\n"));
        }
    }
}
