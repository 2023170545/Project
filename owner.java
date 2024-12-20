package Project;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;

import java.util.ArrayList;

public class Owner extends User {
    private ArrayList<Vehicle> vehicles; // List of vehicles owned by the owner

    // Default constructor
    public Owner() {
        this.vehicles = new ArrayList<>();
    }

    // Parameterized constructor
    public Owner(String ID, String name, String email, String password, String contactInfo) {
        super(ID, name, email, password, contactInfo);
        this.vehicles = new ArrayList<>();
    }

    // Getter for Vehicles
    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    // Add a vehicle to the owner's list
    public void addVehicle(String id, String type, String licensePlate) {
        Vehicle newVehicle = new Vehicle(id, type, licensePlate, this.getID());
        vehicles.add(newVehicle);
        System.out.println("Vehicle added: " + newVehicle);
    }

    // Remove a vehicle from the owner's list
    public void removeVehicle(String vehicleID) {
        vehicles.removeIf(vehicle -> vehicle.getId().equals(vehicleID));
        System.out.println("Vehicle with ID " + vehicleID + " has been removed.");
    }

    // View all vehicles owned by the user
    public void viewVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles registered.");
        } else {
            System.out.println("Registered Vehicles:");
            for (Vehicle vehicle : vehicles) {
                System.out.println("ID: " + vehicle.getId() + ", Type: " + vehicle.getType() + ", License Plate: " + vehicle.getLicensePlate());
            }
        }
    }

    // Check if a vehicle has violations
    public void checkVehicleStatus(String vehicleID, ArrayList<String> violationList) {
        boolean hasViolations = false;
        System.out.println("Checking status for Vehicle ID: " + vehicleID);
        for (String record : violationList) {
            String[] attributes = record.split(",");
            if (attributes.length >= 2 && attributes[1].equals(vehicleID)) {
                hasViolations = true;
                System.out.println("Violation Found: ID: " + attributes[0] + ", Type: " + attributes[2] + ", Date: " + attributes[3]);
            }
        }
        if (!hasViolations) {
            System.out.println("No violations found for Vehicle ID: " + vehicleID);
        }
    }

    // Pay fines for a specific vehicle
    public void payFines(String vehicleID, ArrayList<String> violationList) {
        boolean paidAnyFines = false;
        ArrayList<String> remainingViolations = new ArrayList<>();

        for (String record : violationList) {
            String[] attributes = record.split(",");
            if (attributes.length >= 2 && attributes[1].equals(vehicleID)) {
                System.out.println("Paying fine for Violation ID: " + attributes[0] + ", Amount: " + attributes[4]);
                paidAnyFines = true;
            } else {
                remainingViolations.add(record);
            }
        }

        if (paidAnyFines) {
            violationList.clear();
            violationList.addAll(remainingViolations);
            System.out.println("All fines for Vehicle ID: " + vehicleID + " have been paid.");
        } else {
            System.out.println("No fines found for Vehicle ID: " + vehicleID);
        }
    }

    // Load vehicles from file (for this owner)
    public void loadVehiclesFromList(ArrayList<String> vehicleList) {
        vehicles.clear();
        for (String record : vehicleList) {
            Vehicle vehicle = Vehicle.fromCSV(record);
            if (vehicle.getOwnerID().equals(this.getID())) {
                vehicles.add(vehicle);
            }
        }
    }

    // Save vehicles to file list (returns CSV records of all owned vehicles)
    public ArrayList<String> saveVehiclesToList() {
        ArrayList<String> vehicleRecords = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            vehicleRecords.add(vehicle.toCSV());
        }
        return vehicleRecords;
    }
}


