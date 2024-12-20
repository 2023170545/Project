package com.mycompany.trafficmanagementsystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Admin extends User {

    // Default constructor
    public Admin() {
    }

    // Parameterized constructor
    public Admin(String ID, String name, String email, String password, String contactInfo) {
        super(ID, name, email, password, contactInfo);
    }

    // Add Traffic Light
    public void addTrafficLight(String id, String location, String status, int duration, ArrayList<String> trafficLightList) {
        String trafficLightRecord = id + "," + location + "," + status + "," + duration;
        trafficLightList.add(trafficLightRecord);
        System.out.println("Traffic light added: " + trafficLightRecord);
    }

    // Update Traffic Light
    public void updateTrafficLight(String id, String newStatus, int newDuration, ArrayList<String> trafficLightList) {
        boolean updated = false;
        for (int i = 0; i < trafficLightList.size(); i++) {
            String[] attributes = trafficLightList.get(i).split(",");
            if (attributes[0].equals(id)) {
                trafficLightList.set(i, id + "," + attributes[1] + "," + newStatus + "," + newDuration);
                updated = true;
                System.out.println("Traffic light updated: " + trafficLightList.get(i));
                break;
            }
        }
        if (!updated) {
            System.out.println("Traffic light with ID " + id + " not found.");
        }
    }

    // Delete Traffic Light
    public void deleteTrafficLight(String id, ArrayList<String> trafficLightList) {
        boolean removed = trafficLightList.removeIf(record -> record.split(",")[0].equals(id));
        if (removed) {
            System.out.println("Traffic light with ID " + id + " has been removed.");
        } else {
            System.out.println("Traffic light with ID " + id + " not found.");
        }
    }

    // Add Zone
    public void addZone(String id, String name, String location, ArrayList<String> zoneList) {
        String zoneRecord = id + "," + name + "," + location;
        zoneList.add(zoneRecord);
        System.out.println("Zone added: " + zoneRecord);
    }

    // Remove Zone
    public void removeZone(String id, ArrayList<String> zoneList) {
        boolean removed = zoneList.removeIf(record -> record.split(",")[0].equals(id));
        if (removed) {
            System.out.println("Zone with ID " + id + " has been removed.");
        } else {
            System.out.println("Zone with ID " + id + " not found.");
        }
    }

    // Load Zones from File
    public ArrayList<String> loadZonesFromFile(String filePath) {
        ArrayList<String> zoneList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                zoneList.add(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Error loading zones from file: " + e.getMessage());
        }
        return zoneList;
    }

    // Save Zones to File
    public void saveZonesToFile(String filePath, ArrayList<String> zoneList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String record : zoneList) {
                writer.write(record);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving zones to file: " + e.getMessage());
        }
    }

    // View Violations by Vehicle
    public void viewViolationsByVehicle(String vehicleID, ArrayList<String> violationList) {
        boolean found = false;
        System.out.println("Violations for Vehicle ID: " + vehicleID);
        for (String record : violationList) {
            String[] attributes = record.split(",");
            if (attributes[1].equals(vehicleID)) {
                System.out.println("Violation ID: " + attributes[0] + ", Type: " + attributes[2] + ", Date: " + attributes[3] + ", Fine: " + attributes[4]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No violations found for Vehicle ID: " + vehicleID);
        }
    }

    // View Violations by Zone
    public void viewViolationsByZone(String zoneID, ArrayList<String> violationList, ArrayList<String> trafficLightList) {
        boolean found = false;
        System.out.println("Violations in Zone ID: " + zoneID);
        for (String violation : violationList) {
            for (String trafficLight : trafficLightList) {
                String[] trafficLightAttributes = trafficLight.split(",");
                if (trafficLightAttributes[0].equals(zoneID) && violation.contains(trafficLightAttributes[0])) {
                    System.out.println("Violation: " + violation);
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("No violations found in Zone ID: " + zoneID);
        }
    }

    // Generate Traffic Reports
    public void generateTrafficReport(ArrayList<String> violationList, ArrayList<String> trafficCountList) {
        System.out.println("Traffic Report:");
        System.out.println("---------------------------------");

        // Find high-density zone
        String highDensityZone = null;
        int maxTrafficCount = 0;

        for (String record : trafficCountList) {
            String[] attributes = record.split(",");
            String zoneID = attributes[0];
            int trafficCount = Integer.parseInt(attributes[1]);

            if (trafficCount > maxTrafficCount) {
                maxTrafficCount = trafficCount;
                highDensityZone = zoneID;
            }
        }

        System.out.println("High-Density Zone: " + (highDensityZone != null ? highDensityZone + " (Traffic Count: " + maxTrafficCount + ")" : "None"));

        // Find frequent violations
        ArrayList<String> violationTypes = new ArrayList<>();
        ArrayList<Integer> violationCounts = new ArrayList<>();

        for (String record : violationList) {
            String[] attributes = record.split(",");
            String violationType = attributes[2];

            if (!violationTypes.contains(violationType)) {
                violationTypes.add(violationType);
                violationCounts.add(1);
            } else {
                int index = violationTypes.indexOf(violationType);
                violationCounts.set(index, violationCounts.get(index) + 1);
            }
        }

        System.out.println("Frequent Violations:");
        for (int i = 0; i < violationTypes.size(); i++) {
            System.out.println("Violation Type: " + violationTypes.get(i) + ", Count: " + violationCounts.get(i));
        }

        System.out.println("---------------------------------");
    }
}


