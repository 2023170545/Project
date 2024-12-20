package com.mycompany.trafficmanagementsystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Traffic_Officer extends User {
   
    private String assignedZone;
    private ArrayList<Traffic_Violation> violations;

    public Traffic_Officer() {
        this.violations = new ArrayList<>();
    }

    public Traffic_Officer(String ID, String name, String email, String password, String contactInfo, String assignedZone) {
        super(ID, name, email, password, contactInfo);
        this.assignedZone = assignedZone;
        this.violations = new ArrayList<>();
    }

    public String getAssignedZone() {
        return assignedZone;
    }

    public void setAssignedZone(String assignedZone) {
        this.assignedZone = assignedZone;
    }

    public static void displayMenu() {
        System.out.println("1. Record Violation");
        System.out.println("2. View Recorded Violations");
        System.out.println("3. Exit");
    }

    // Record a new violation and add it to the list
    public void recordViolation(String vehicleID, String violationType, String date, ArrayList<String> violationList) {
        String violationID = "V" + (violationList.size() + 1); // Generate unique ID based on list size
        Traffic_Violation violation = new Traffic_Violation(violationID, vehicleID, violationType, date);
        violationList.add(violation.toCSV()); // Add to the ArrayList instead of writing directly to file
        System.out.println("Violation recorded: " + violation);
    }

    // View violations from the list
    public void viewViolations(ArrayList<String> violationList) {
        if (violationList.isEmpty()) {
            System.out.println("No violations found.");
            return;
        }

        System.out.println("\nRecorded Violations:");
        System.out.println("---------------------------------");
        for (String record : violationList) {
            String[] attributes = record.split(",");
            if (attributes.length == 4) {
                System.out.println("Violation ID: " + attributes[0]);
                System.out.println("Vehicle ID: " + attributes[1]);
                System.out.println("Violation Type: " + attributes[2]);
                System.out.println("Date: " + attributes[3]);
                System.out.println("---------------------------------");
            } else {
                System.out.println("Invalid record: " + record);
            }
        }
    }
}

   

