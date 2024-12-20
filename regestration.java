package Project;

import java.io.*;
import java.util.*;

public class Registration {
    private static int adminCount = 0;
    private static int ownerCount = 0;
    private static int trafficOfficerCount = 0;
    private static final String ADMIN_SERIAL = "@ismaela7med";
    private static final String FILE_PATH = "users.txt";

    public static void registerNewUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select the type of user to register: \n1. Admin\t2. Vehicle Owner\t3. Officer");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                System.out.print("Enter the special serial code to register as an Admin: ");
                String serialCode = scanner.nextLine();
                if (!serialCode.equals(ADMIN_SERIAL)) {
                    System.out.println("Invalid serial code. Registration denied.");
                    return;
                }
            }

            String userID = generateUserID(choice);
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Email: ");
            String email = scanner.nextLine();

            String password;
            while (true) {
                System.out.print("Enter Password: ");
                password = scanner.nextLine();

                System.out.print("Confirm Password: ");
                String confirmPassword = scanner.nextLine();

                if (!password.equals(confirmPassword)) {
                    System.out.println("Passwords do not match. Try again.");
                } else {
                    break;
                }
            }

            System.out.print("Enter Contact Info: ");
            String contactInfo = scanner.nextLine();

            User user = new User(userID, name, email, password, contactInfo);
            saveUserToFile(user, choice);

            System.out.println("User registered successfully! User ID: " + userID);
        } catch (Exception e) {
            System.out.println("An error occurred during registration: " + e.getMessage());
        }
    }

    private static String generateUserID(int choice) {
        String userID = "";
        switch (choice) {
            case 1:
                adminCount++;
                userID = "A" + adminCount;
                break;
            case 2:
                ownerCount++;
                userID = "O" + ownerCount;
                break;
            case 3:
                trafficOfficerCount++;
                userID = "T" + trafficOfficerCount;
                break;
            default:
                throw new IllegalArgumentException("Invalid user type.");
        }
        return userID;
    }

    private static void saveUserToFile(User user, int userType) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(user.getID() + "," + user.getName() + "," + user.getEmail() + "," + user.getPassword() + "," + user.getContactInfo() + "," + userType);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving user to file: " + e.getMessage());
        }
    }

    public static String loginUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select your user type to log in: \n1. Admin\t2. Vehicle Owner\t3. Officer");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter Email: ");
            String email = scanner.nextLine();

            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            if (validateLoginFromFile(email, password, choice)) {
                System.out.println("Login successful!");
                return getUserRole(choice);
            } else {
                System.out.println("Invalid email or password.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred during login: " + e.getMessage());
        }
        return "invalid";
    }

    private static boolean validateLoginFromFile(String email, String password, int userType) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length >= 6 && details[2].equalsIgnoreCase(email.trim()) && details[3].equals(password.trim()) && Integer.parseInt(details[5]) == userType) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading user data: " + e.getMessage());
        }
        return false;
    }

    private static String getUserRole(int choice) {
        switch (choice) {
            case 1: return "admin";
            case 2: return "owner";
            case 3: return "officer";
            default: return "unknown";
        }
    }
}
