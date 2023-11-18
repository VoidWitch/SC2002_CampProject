package project;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GenerateCSV {

    public static void generateAttendee(Camp camp){
        String csvFileName = camp.getCampName() + "_Attendee_Report.csv";


        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFileName))) {
            // Writing camp information
            String[] campInfo = {"Camp Name", "Camp description", "Dates", "Registration closing date",
                                "Open to NTU", "Location", "Total slots", "Camp committee slots",
                                "Description", "Staff-in-charge"};
            writer.writeNext(campInfo);


            String[] campData = {camp.getCampName(), camp.getDescription(),
                                String.valueOf(camp.getRegistrationClosingDate()), String.valueOf(camp.getVisibility()),camp.getLocation(),
                                String.valueOf(camp.getTotalSlots()), String.valueOf(camp.getCampCommitteeSlots()), camp.getCampStaffIC()};
            writer.writeNext(campData);


            // Writing attendees' information
            String[] attendeesHeader = { "Attendees"};
            writer.writeNext(attendeesHeader);


            //getAttendees returns list of attendees
            for (Student student : camp.getAttendees()) {
                String[] attendeeData = {student.getName()};
                writer.writeNext(attendeeData);
            }


            System.out.println("Camp report saved as " + csvFileName);
        } catch (IOException e) {
            e.printStackTrace(); }
    }


    public static void generateCampCommittee(Camp camp){
        String csvFileName = camp.getCampName() + "_CampCommitteeMember_Report.csv";


        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFileName))) {
            // Writing camp information
            String[] campInfo = {"Camp Name", "Camp description", "Dates", "Registration closing date",
                                "Open to NTU", "Location", "Total slots", "Camp committee slots",
                                "Description", "Staff-in-charge"};
            writer.writeNext(campInfo);


            String[] campData = {camp.getCampName(), camp.getDescription(),
                    String.valueOf(camp.getRegistrationClosingDate()), String.valueOf(camp.getVisibility()),camp.getLocation(),
                    String.valueOf(camp.getTotalSlots()), String.valueOf(camp.getCampCommitteeSlots()), camp.getCampStaffIC()};
            writer.writeNext(campData);


            // Writing camp comm members' information
            String[] campCommHeader = { "Camp Committee Members"};
            writer.writeNext(campCommHeader);


            //getCampCommitteeMembers returns list of committee members
            for (CampCommitteeMembers committeeMember : camp.getCampCommitteeMembers()) {
                String[] committeeMemberData = {committeeMember.getName()};
                writer.writeNext(committeeMemberData);
            }


            System.out.println("Camp report saved as " + csvFileName);
        } catch (IOException e) {
            e.printStackTrace(); }
    }


    public static void generateNoFilter(Camp camp){
        String csvFileName = camp.getCampName() + "_Report.csv";


        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFileName))) {
            // Writing camp information
            String[] campInfo = {"Camp Name", "Camp description", "Dates", "Registration closing date",
                                "Open to NTU", "Location", "Total slots", "Camp committee slots",
                                "Description", "Staff-in-charge"};
            writer.writeNext(campInfo);


            String[] campData = {camp.getCampName(), camp.getDescription(),
                    String.valueOf(camp.getRegistrationClosingDate()), String.valueOf(camp.getVisibility()),camp.getLocation(),
                    String.valueOf(camp.getTotalSlots()), String.valueOf(camp.getCampCommitteeSlots()), camp.getCampStaffIC()};
            writer.writeNext(campData);
           
            // Writing camp comm members' information
            String[] campCommHeader = { "Camp Committee Members"};
            writer.writeNext(campCommHeader);


            //getCampCommitteeMembers returns list of committee members
            for (CampCommitteeMembers committeeMember : camp.getCampCommitteeMembers()) {
                String[] committeeMemberData = {committeeMember.getName()};
                writer.writeNext(committeeMemberData);
            }


            // Writing attendees' information
            String[] attendeesHeader = { "Attendees"};
            writer.writeNext(attendeesHeader);


            //getAttendees returns list of attendees
            for (Student student : camp.getAttendees()) {
                String[] attendeeData = {student.getName()};
                writer.writeNext(attendeeData);
            }


            System.out.println("Camp report saved as " + csvFileName);
        } catch (IOException e) {
            e.printStackTrace(); }
    }


    public static void generatePerformance(Camp camp){
        String csvFileName = camp.getCampName() + "_Performance_Report.csv";


        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFileName))) {
            // Writing camp information
            String[] campInfo = {"Camp Name"};
            writer.writeNext(campInfo);


            String[] campData = {camp.getCampName()};
            writer.writeNext(campData);


            // Writing committee members' information
            String[] PerformanceHeader = { "Camp Committee Member", "Total Points"};
            writer.writeNext(PerformanceHeader);


            for (CampCommitteeMembers committeemember : camp.getCampCommitteeMembers()) {
                String[] performance = {committeemember.getName(), String.valueOf(committeemember.getPoints())};
                writer.writeNext(performance);
            }


            System.out.println("Camp report saved as " + csvFileName);
        } catch (IOException e) {
            e.printStackTrace(); }
    }
}
