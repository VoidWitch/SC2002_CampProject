package project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CampInfo {

    private String campName;
    private String dates;
    private String registrationClosingDate;
    private String userGroup;
    private String location;
    private int totalSlots;
    private int campCommitteeSlots;
    private String description;
    private String staffInCharge; // Automatically tied to the staff who created it

    public  CampInfo(String campName, String dates, String registrationClosingDate, String userGroup, String location, int totalSlots, int campCommitteeSlots, String description, String staffInCharge) {
        this.campName = campName;
        this.dates = dates;
        this.registrationClosingDate = registrationClosingDate;
        this.userGroup = userGroup;
        this.location = location;
        this.totalSlots = totalSlots;
        this.campCommitteeSlots = campCommitteeSlots;
        this.description = description;
        this.staffInCharge = staffInCharge;
    }
    public CampInfo() {
    	
    }

    public String toString() {
        return "Camp Name: " + campName + "\nDates: " + dates + "\nRegistration Closing Date: " + registrationClosingDate
                + "\nUser Group: " + userGroup + "\nLocation: " + location + "\nTotal Slots: " + totalSlots
                + "\nCamp Committee Slots: " + campCommitteeSlots + "\nDescription: " + description
                + "\nStaff in Charge: " + staffInCharge;
    }
}

class CampManager {
    private List<CampInfo> camps = new ArrayList<>();

    public void addCamp(CampInfo camp) {
        camps.add(camp);
    }

    public void viewCamps() {
        if (camps.isEmpty()) {
            System.out.println("No camps available.");
        } else {
            int campNumber = 1;
            for (CampInfo camp : camps) {
                System.out.println("Camp " + campNumber + ":\n" + camp + "\n");
                campNumber++;
            }
        }
    }

    
    public static void main(String[] args) {
        CampManager campManager = new CampManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Add a new camp");
            System.out.println("2. View all camps");
            System.out.println("3. Quit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (choice == 1) {
                System.out.print("Enter Camp Name: ");
                String campName = scanner.nextLine();
                System.out.print("Enter Dates: ");
                String dates = scanner.nextLine();
                System.out.print("Enter Registration Closing Date: ");
                String registrationClosingDate = scanner.nextLine();
                System.out.print("Enter User Group (Own School/Whole NTU): ");
                String userGroup = scanner.nextLine();
                System.out.print("Enter Location: ");
                String location = scanner.nextLine();
                System.out.print("Enter Total Slots: ");
                int totalSlots = scanner.nextInt();
                System.out.print("Enter Camp Committee Slots (max 10): ");
                int campCommitteeSlots = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                System.out.print("Enter Description: ");
                String description = scanner.nextLine();
                System.out.println("Enter Staff in Charge: ");
                String staffInCharge = scanner.nextLine();
              //  String staffInCharge = "Automatically tied to the staff who created it"; // try again
                CampInfo camp = new CampInfo(campName, dates, registrationClosingDate, userGroup, location, totalSlots, campCommitteeSlots, description, staffInCharge);
                campManager.addCamp(camp);
                System.out.println("Camp added successfully!");
            } else if (choice == 2) {
                campManager.viewCamps();
            } else if (choice == 3) {
                break;
            }
        }
    
    }
}
/*
public class Main {
    public static void main(String[] args) {
        CampManager campManager = new CampManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Add a new camp");
            System.out.println("2. View all camps");
            System.out.println("3. Quit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (choice == 1) {
                System.out.print("Enter Camp Name: ");
                String campName = scanner.nextLine();
                System.out.print("Enter Dates: ");
                String dates = scanner.nextLine();
                System.out.print("Enter Registration Closing Date: ");
                String registrationClosingDate = scanner.nextLine();
                System.out.print("Enter User Group (Own School/Whole NTU): ");
                String userGroup = scanner.nextLine();
                System.out.print("Enter Location: ");
                String location = scanner.nextLine();
                System.out.print("Enter Total Slots: ");
                int totalSlots = scanner.nextInt();
                System.out.print("Enter Camp Committee Slots (max 10): ");
                int campCommitteeSlots = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                System.out.print("Enter Description: ");
                String description = scanner.nextLine();
              //  String staffInCharge = "Automatically tied to the staff who created it"; // try again
                campManager.addCamp(camp);
                System.out.println("Camp added successfully!");
            } else if (choice == 2) {
                campManager.viewCamps();
            } else if (choice == 3) {
                break;
            }
        }
    }
}
*/