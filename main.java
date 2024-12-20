package Project;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userRole = "invalid";

        System.out.println("Welcome to the Traffic Management System");
        System.out.println("--------------------------------------");

        while (true) {
            System.out.println("1. Register a new user");
            System.out.println("2. Login as an existing user");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 3.");
                scanner.nextLine(); // Clear invalid input
                continue;
            }

            if (choice == 1) {
                // Register a new user
                Registration.registerNewUser();
            } else if (choice == 2) {
                // Login as an existing user
                userRole = Registration.loginUser();

                if (!userRole.equals("invalid")) {
                    System.out.println("Welcome, " + userRole + "!");
                    handleUserFunctions(userRole);
                }
            } else if (choice == 3) {
                System.out.println("Exiting the system. Goodbye!");
                return;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleUserFunctions(String userRole) {
        while (true) {
            switch (userRole) {
                case "admin":
                    if (!handleAdminFunctions()) return;
                    break;
                case "owner":
                    if (!handleOwnerFunctions()) return;
                    break;
                case "officer":
                    if (!handleOfficerFunctions()) return;
                    break;
                default:
                    System.out.println("Unexpected error occurred.");
                    return;
            }
        }
    }

    private static boolean handleAdminFunctions() {
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin();
        ArrayList<String> zones = admin.loadZonesFromFile("zones.txt");
        ArrayList<String> trafficLights = new ArrayList<>();

        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add a Zone");
            System.out.println("2. Remove a Zone");
            System.out.println("3. Add a Traffic Light");
            System.out.println("4. Update a Traffic Light");
            System.out.println("5. Delete a Traffic Light");
            System.out.println("6. Save Zones to File");
            System.out.println("7. Log Out");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Zone ID: ");
                    String zoneID = scanner.nextLine();
                    System.out.print("Enter Zone Name: ");
                    String zoneName = scanner.nextLine();
                    System.out.print("Enter Zone Location: ");
                    String zoneLocation = scanner.nextLine();
                    admin.addZone(zoneID, zoneName, zoneLocation, zones);
                    break;
                case 2:
                    System.out.print("Enter Zone ID to Remove: ");
                    String removeZoneID = scanner.nextLine();
                    admin.removeZone(removeZoneID, zones);
                    break;
                case 3:
                    System.out.print("Enter Traffic Light ID: ");
                    String trafficLightID = scanner.nextLine();
                    System.out.print("Enter Location: ");
                    String location = scanner.nextLine();
                    System.out.print("Enter Status (e.g., Red, Green): ");
                    String status = scanner.nextLine();
                    System.out.print("Enter Duration (in seconds): ");
                    int duration = scanner.nextInt();
                    scanner.nextLine();
                    admin.addTrafficLight(trafficLightID, location, status, duration, trafficLights);
                    break;
                case 4:
                    System.out.print("Enter Traffic Light ID to Update: ");
                    String updateID = scanner.nextLine();
                    System.out.print("Enter New Status: ");
                    String newStatus = scanner.nextLine();
                    System.out.print("Enter New Duration: ");
                    int newDuration = scanner.nextInt();
                    scanner.nextLine();
                    admin.updateTrafficLight(updateID, newStatus, newDuration, trafficLights);
                    break;
                case 5:
                    System.out.print("Enter Traffic Light ID to Delete: ");
                    String deleteID = scanner.nextLine();
                    admin.deleteTrafficLight(deleteID, trafficLights);
                    break;
                case 6:
                    admin.saveZonesToFile("zones.txt", zones);
                    System.out.println("Zones saved to file successfully.");
                    break;
                case 7:
                    System.out.println("Logging out...");
                    return false;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static boolean handleOwnerFunctions() {
        Scanner scanner = new Scanner(System.in);
        Owner owner = new Owner();
        ArrayList<String> vehicles = new ArrayList<>();

        while (true) {
            System.out.println("\nOwner Menu:");
            System.out.println("1. View Vehicles");
            System.out.println("2. Add a Vehicle");
            System.out.println("3. Remove a Vehicle");
            System.out.println("4. Log Out");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    owner.viewVehicles();
                    break;
                case 2:
                    System.out.print("Enter Vehicle ID: ");
                    String vehicleID = scanner.nextLine();
                    System.out.print("Enter Vehicle Type: ");
                    String vehicleType = scanner.nextLine();
                    System.out.print("Enter License Plate: ");
                    String licensePlate = scanner.nextLine();
                    owner.addVehicle(vehicleID, vehicleType, licensePlate);
                    break;
                case 3:
                    System.out.print("Enter Vehicle ID to Remove: ");
                    String removeVehicleID = scanner.nextLine();
                    owner.removeVehicle(removeVehicleID);
                    break;
                case 4:
                    System.out.println("Logging out...");
                    return false;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static boolean handleOfficerFunctions() {
        Scanner scanner = new Scanner(System.in);
        Traffic_Officer officer = new Traffic_Officer();
        ArrayList<String> violations = new ArrayList<>();

        while (true) {
            System.out.println("\nOfficer Menu:");
            System.out.println("1. Record a Violation");
            System.out.println("2. View Violations");
            System.out.println("3. Log Out");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Vehicle ID: ");
                    String vehicleID = scanner.nextLine();
                    System.out.print("Enter Violation Type: ");
                    String violationType = scanner.nextLine();
                    System.out.print("Enter Date (YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    officer.recordViolation(vehicleID, violationType, date, violations);
                    break;
                case 2:
                    officer.viewViolations(violations);
                    break;
                case 3:
                    System.out.println("Logging out...");
                    return false;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

