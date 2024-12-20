package com.mycompany.trafficmanagementsystem;

public class Vehicle {
    private String id;
    private String type; // Changed from array to a single type (e.g., Car, Truck, Bike)
    private String licensePlate;
    private String ownerID;

    // Default constructor
    public Vehicle() {
    }

    // Parameterized constructor
    public Vehicle(String id, String type, String licensePlate, String ownerID) {
        this.id = id;
        this.type = type;
        this.licensePlate = licensePlate;
        this.ownerID = ownerID;
    }

    // Getter and Setter for ID
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getter and Setter for Type
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Getter and Setter for License Plate
    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    // Getter and Setter for Owner ID
    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    // Method to convert to CSV format
    public String toCSV() {
        return id + "," + type + "," + licensePlate + "," + ownerID;
    }

    // Method to initialize from CSV format
    public static Vehicle fromCSV(String csvLine) {
        String[] attributes = csvLine.split(",");
        if (attributes.length == 4) {
            return new Vehicle(attributes[0], attributes[1], attributes[2], attributes[3]);
        } else {
            throw new IllegalArgumentException("Invalid CSV format for Vehicle: " + csvLine);
        }
    }
}

