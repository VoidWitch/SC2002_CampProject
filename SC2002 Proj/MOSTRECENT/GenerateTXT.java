import java.io.BufferedWriter;  //if change to interface, javadoc needs to regenerate
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The {@code GenerateTXT} class provides methods to generate various reports in TXT format
 * for a camp management system. It includes methods for generating reports filtered by attendee,
 * camp committee member, no filter, and performance.
 * @author Gao Linyue
 */
public class GenerateTXT implements Generate{  //might change to implement interface
    Scanner sc = new Scanner(System.in);
    /**
     * Generates a report in TXT format filtered by attendees for the specified {@code Camp} object.
     * @param camp The camp to generate list.
     */
    public void generateAttendee(Camp camp){
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

    /**
     * Generates a report in TXT format filtered by camp committee members for the specified {@code Camp} object.
     * @param camp The camp to generate list.
     */
    public void generateCampCommittee(Camp camp){
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

    /**
     * Generates a report in TXT format with no filters for the specified {@code Camp} object.
     * @param camp The camp to generate list.
     */
    public void generateNoFilter(Camp camp){
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

    /**
     * Generates a performance report in TXT format for the specified {@code Camp} object.
     * @param camp The camp to generate list.
     */
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

