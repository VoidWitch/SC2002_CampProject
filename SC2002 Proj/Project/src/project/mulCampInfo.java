package project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class mulCampInfo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<CampInfo> campList = new ArrayList<>();

        while (true) {
            CampInfo camp = new CampInfo();

            // Prompt the user for Camp Name
            System.out.print("Enter Camp Name: ");
            camp.setcampName(scanner.nextLine());

            // Prompt the user for Dates
            System.out.print("Enter Dates: ");
            camp.setDates(scanner.nextLine());

            // Prompt the user for Registration Closing Date
            System.out.print("Enter Registration Closing Date: ");
            camp.setRegistrationClosingDate(scanner.nextLine());

            // Prompt the user for User Group
            System.out.print("Enter User Group (own school or whole NTU): ");
            camp.setUserGroup(scanner.nextLine());

            // Prompt the user for Location
            System.out.print("Enter Location: ");
            camp.setLocation(scanner.nextLine());

            // Prompt the user for Total Slots
            System.out.print("Enter Total Slots: ");
            camp.setTotalSlots(scanner.nextInt());
            scanner.nextLine(); // Consume the newline character

            // Prompt the user for Camp Committee Slots
            System.out.print("Enter Camp Committee Slots (max 10): ");
            camp.setCommitteeSlots(scanner.nextInt());
            scanner.nextLine(); // Consume the newline character

            // Prompt the user for Description
            System.out.print("Enter Description: ");
            camp.setDescription(scanner.nextLine());

            campList.add(camp);

            System.out.print("Do you want to add another camp (yes/no)? ");
            String response = scanner.nextLine().toLowerCase();
            if (!response.equals("yes")) {
                break;
            }
        }

        // Close the scanner when you're done with it
        scanner.close();

        // Display the collected information for all camps
        for (int i = 0; i < campList.size(); i++) {
            System.out.println("Camp " + (i + 1) + " Details:");
            CampInfo camp = campList.get(i);
            System.out.println("Camp Name: " + camp.getCampName());
            System.out.println("Dates: " + camp.getDates());
            System.out.println("Registration Closing Date: " + camp.getRegistrationClosingDate());
            System.out.println("User Group: " + camp.getUserGroup());
            System.out.println("Location: " + camp.getLocation());
            System.out.println("Total Slots: " + camp.getTotalSlots());
            System.out.println("Camp Committee Slots: " + camp.getCommitteeSlots());
            System.out.println("Description: " + camp.getDescription());
            System.out.println();
        }
    }
}
