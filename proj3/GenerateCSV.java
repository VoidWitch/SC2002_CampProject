package proj3;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVWriter;

public class GenerateCSV implements Generate{
    Scanner sc = new Scanner(System.in);

    public void generate(List<Camp> list){
        String csvFileName = "Created_Camps_Report.csv";
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFileName))) {
            for(Camp camp : list){
                // Writing camp information
                String[] campInfo = {"Camp Name", "Camp description", "Start date", "End Date", "Registration closing date",
                                    "Open to NTU", "Location", "Total slots", "Camp committee slots", "Staff-in-charge", "Faculty"};
                writer.writeNext(campInfo);

                String[] campData = {camp.getCampName(), camp.getDescription(), String.valueOf(camp.getCampStartDate()), String.valueOf(camp.getCampEndDate()),
                                    String.valueOf(camp.getRegistrationClosingDate()), String.valueOf(camp.getVisibility()),camp.getLocation(),
                                    String.valueOf(camp.getTotalSlots()), String.valueOf(camp.getCampCommitteeSlots()), camp.getCampStaffIC(), camp.getCampFaculty()};
                writer.writeNext(campData);

                // Writing attendees' information
                String[] attendeesHeader = { "Attendees"};
                writer.writeNext(attendeesHeader);

                //getAttendees returns list of attendees
                for (Student student : camp.getAttendees()) {
                    String[] attendeeData = {student.getName()};
                    writer.writeNext(attendeeData);
                }
                
                // Writing committees' information
                String[] committeeheader = { "Committee Members"};
                writer.writeNext(committeeheader);

                //getCommittee returns list of committee
                for (CampCommitteeMembers ccm : camp.getCampCommitteeMembers()) {
                    String[] attendeeData = {ccm.getName()};
                    writer.writeNext(attendeeData);
                }
            }
            System.out.println("Camp report saved as " + csvFileName);
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }

    public void generatePerformance(Camp camp){
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
