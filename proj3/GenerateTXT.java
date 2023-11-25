package proj3;

import java.io.BufferedWriter;  //if change to interface, javadoc needs to regenerate
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class GenerateTXT implements Generate{  //might change to implement interface
    Scanner sc = new Scanner(System.in);
    
    public void generate(List<Camp> list){
        String textFileName = "Created_Camps_Report.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(textFileName))) {
            for(Camp camp : list){
                // Write camp information
                writer.write("Camp Information:\n");
                writer.write("Camp Name: " + camp.getCampName() + "\n");
                writer.write("Camp description: " + camp.getDescription() + "\n");
                writer.write("Camp starting date: " + camp.getCampStartDate() + "\n");
                writer.write("Camp ending date: " + camp.getCampEndDate() + "\n");
                writer.write("Registration closing date: " + camp.getRegistrationClosingDate() + "\n");
                writer.write("Visibility to whole NTU " + camp.getVisibility() + "\n");
                writer.write("Location: " + camp.getLocation() + "\n");
                writer.write("Total slots: " + camp.getTotalSlots() + "\n");
                writer.write("Camp committee slots: " + camp.getCampCommitteeSlots() + "\n");
                writer.write("Staff IC: " + camp.getCampStaffIC() + "\n");
                writer.write("Faculty: " + camp.getCampFaculty() + "\n");
               
                //attendees' info
                writer.write("Attendees:\n");
                for (Student student : camp.getAttendees()) {
                    writer.write("Name: " + student.getName() + "\n");
                }
                //committee members' info
                writer.write("Committee Members:\n");
                for (CampCommitteeMembers ccm : camp.getCampCommitteeMembers()) {
                    writer.write("Name: " + ccm.getName() + "\n");
                }
            }
            System.out.println("Camp report saved as " + textFileName);
        } catch (IOException e) {
            e.printStackTrace();}
    }
    
    public void generatePerformance(Camp camp){
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

