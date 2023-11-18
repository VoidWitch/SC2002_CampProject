package project;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GenerateTXT {
    public static void generateAttendee(Camp camp){
        String textFileName = camp.getCampName() + "_Attendee_Report.txt";


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(textFileName))) {
            // Write camp information
            writer.write("Camp Information (Attendee filter):\n");
            writer.write("Camp Name: " + camp.getCampName() + "\n");
            writer.write("Camp description: " + camp.getDescription() + "\n");
            writer.write("Registration closing date: " + camp.getRegistrationClosingDate() + "\n");
            writer.write("Visibility to whole NTU " + camp.getVisibility() + "\n");
            writer.write("Location: " + camp.getLocation() + "\n");
            writer.write("Total slots: " + camp.getTotalSlots() + "\n");
            writer.write("Camp committee slots: " + camp.getCampCommitteeSlots() + "\n");
            writer.write("Staff IC: " + camp.getCampStaffIC() + "\n");


            // Write attendees' information
            writer.write("Attendees:\n");
            for (Student student : camp.getAttendees()) {
                writer.write("Name: " + student.getName() + "\n");
            }


            System.out.println("Camp report saved as " + textFileName);
        } catch (IOException e) {
        e.printStackTrace();}
    }


    public static void generateCampCommiteeMember(Camp camp){
        String textFileName = camp.getCampName() + "CampCommitteeMember_Report.txt";


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(textFileName))) {
            // Write camp information
            writer.write("Camp Information (Camp Committer filter):\n");
            writer.write("Camp Name: " + camp.getCampName() + "\n");
            writer.write("Camp description: " + camp.getDescription() + "\n");
            writer.write("Registration closing date: " + camp.getRegistrationClosingDate() + "\n");
            writer.write("Visibility to whole NTU " + camp.getVisibility() + "\n");
            writer.write("Location: " + camp.getLocation() + "\n");
            writer.write("Total slots: " + camp.getTotalSlots() + "\n");
            writer.write("Camp committee slots: " + camp.getCampCommitteeSlots() + "\n");
            writer.write("Staff IC: " + camp.getCampStaffIC() + "\n");


            // Write committee members' information
            writer.write("Camp Committee Members:\n");
            for (CampCommitteeMembers committeemember : camp.getCampCommitteeMembers()) {
                writer.write("Name: " + committeemember.getName() + "\n");
            }


            System.out.println("Camp report saved as " + textFileName);
        } catch (IOException e) {
        e.printStackTrace();}
    }


    public static void generateNoFilter(Camp camp){
        String textFileName = camp.getCampName() + "_Report.txt";


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(textFileName))) {
            // Write camp information
            writer.write("Camp Information:\n");
            writer.write("Camp Name: " + camp.getCampName() + "\n");
            writer.write("Camp description: " + camp.getDescription() + "\n");
            writer.write("Registration closing date: " + camp.getRegistrationClosingDate() + "\n");
            writer.write("Visibility to whole NTU " + camp.getVisibility() + "\n");
            writer.write("Location: " + camp.getLocation() + "\n");
            writer.write("Total slots: " + camp.getTotalSlots() + "\n");
            writer.write("Camp committee slots: " + camp.getCampCommitteeSlots() + "\n");
            writer.write("Staff IC: " + camp.getCampStaffIC() + "\n");


            // Write committee members' information
            writer.write("Camp Committee Members:\n");
            for (CampCommitteeMembers committeemember : camp.getCampCommitteeMembers()) {
                writer.write("Name: " + committeemember.getName() + "\n");
            }


            // Write attendees' information
            writer.write("Attendees:\n");
            for (Student student : camp.getAttendees()) {
                writer.write("Name: " + student.getName() + "\n");
            }


            System.out.println("Camp report saved as " + textFileName);
        } catch (IOException e) {
        e.printStackTrace();}
    }


    public static void generatePerformance(Camp camp){
        String textFileName = "Performance_report.txt";


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(textFileName))) {
            // Write camp information
            writer.write("Camp Name: " + camp.getCampName() + "\n");


            // Write committee members' names and points
            for (CampCommitteeMembers committeemember : camp.getCampCommitteeMembers()) {
                writer.write("Name: " + committeemember.getName() + committeemember.getPoints() + "\n");
            }


            System.out.println("Camp report saved as " + textFileName);
        } catch (IOException e) {
        e.printStackTrace();}
    }
}

