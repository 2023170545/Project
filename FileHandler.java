package com.mycompany.trafficmanagementsystem;

import java.util.*;
import java.io.*;

public class FileHandler {
    public static ArrayList<String> readData(String filePath) {
        ArrayList<String> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return data;
    }
    public static void writeData(String filePath, ArrayList<String> data) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
        for (String line : data) {
            writer.write(line);
            writer.newLine();
        }
    } catch (IOException e) {
        System.out.println("Error writing to file: " + e.getMessage());
    }
}
}
