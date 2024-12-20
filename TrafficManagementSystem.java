package com.mycompany.trafficmanagementsystem;

import java.util.ArrayList;
import java.util.Scanner;

public class TrafficManagementSystem {

private ArrayList<String> trafficLights = new ArrayList<>();
    private ArrayList<String> vehicles = new ArrayList<>();
    private ArrayList<String> owners = new ArrayList<>();
    private ArrayList<String> officers = new ArrayList<>();
    private ArrayList<String> zones = new ArrayList<>();
    private ArrayList<String> violations = new ArrayList<>();

    // Method to save data to files
    public void saveData() {
        FileHandler.writeData("trafficlights.txt", trafficLights);
        FileHandler.writeData("vehicles.txt", vehicles);
        FileHandler.writeData("owners.txt", owners);
        FileHandler.writeData("officers.txt", officers);
        FileHandler.writeData("zones.txt", zones);
        FileHandler.writeData("violations.txt", violations);
    }

}
