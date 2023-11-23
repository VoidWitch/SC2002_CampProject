import java.util.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Student class represents a {@code Student} user in the system, inheriting from the {@link User} class.
 * It includes methods for viewing open camps, registering for camps, submitting enquiries, and more.
 * 
 * @author Felicia Poh, Tey Cia Meng, Gao Linyue
 */
public class Student extends User { //extends from User
    private List <Camp> enrolledCamps;
    private List <Camp> withdrawnCamps ;
    private List <Camp> openCamps ; //i created new one
    private List <Enquiry> ownEnquiries;
    private CampCommitteeMembers CommitteeMember;
    private boolean isCampCommitteeMember;
   
    /**
     * Constructs a new Student with the specified name, email, and faculty.
     * @param name    The name of the student.
     * @param email   The email of the student.
     * @param faculty The faculty of the student.
     * @param isCampCommitteeMember Whether student is a committee member.
     */
    public Student(String name, String email, String faculty, boolean isCampCommitteeMember){
        super(name,email,faculty,false);
        this.isCampCommitteeMember = false; //set attendee by default
        enrolledCamps = new ArrayList<>();
        withdrawnCamps =  new ArrayList<>() ;
        openCamps = new ArrayList<>(); //i created new one
        ownEnquiries = new ArrayList<>();
    }
   
    /**
     * Updates the camps available to the student to list of open camps.
     * Checks for previously registered camps, and blacklists students that have withdrawn from the camp before.
	 * Checks for faculty camps.
	 * Checks visibility of other faculty camps that are open to the whole of NTU.
     */
    public void openCamps() {
        List <Camp> allCamps = allCamp.getAllCamps();//contains all camps
        //List <Camp> openCamps = new ArrayList<>();
        //openCamps.clear();
       
        for(Camp camp: allCamps) {
            //filtering faculty
            if(checkCampBlacklist(camp)) {
                continue;
            }
            if(super.getFaculty().equalsIgnoreCase(camp.getCampFaculty())== false){ //case-insensitive string comparison
                //UML diagram dont have getCampFaculty in Camp Class
                continue;
            }
            //filtering visibility
            if(camp.getVisibility() == false) {
                //UML diagram dont have getVisibility
                continue;
            }  
            if(openCamps.contains(camp)) {
                continue;
            } 
            this.openCamps.add(camp);
        }
    }
   
    /**
     * View the list of open camps available for registration.
     */
    public void viewOpenCamps() {
        for(Camp camp : openCamps){
           camp.viewCampInfo();
           System.out.println();          
           }
    }
   
    /**
     * Gets the list of open camps.
     * @return List of open camps.
     */
    public List<Camp> getOpenCamps() {
        return this.openCamps;
    }

    /**
     * Checks for date clash with registered camps.
     * @param startDate The start window of camp.
     * @param endDate The end window of camp.
     * @return {@code true} if there is date clash, otherwise {@code false}.
     */
    public boolean checkDateClash(int startDate, int endDate) { //check if enrolled camp clashes, if no clash return true, continue with reg
        for(Camp enrolledCamp: enrolledCamps) {
            if(startDate<=enrolledCamp.getCampEndDate() && endDate>=enrolledCamp.getCampStartDate()) {
                return true;
            }
        }
        return false;
    }
   
    /**
	 * Prints remaining open slots for list of {@code Camp} objects.
	 */
    public void viewRemainingSlots() {
        for(Camp opCamp: openCamps) {
            System.out.println("Remaining Slots for " + opCamp.getCampName() + ": " + opCamp.getRemainingSlots());
        }   
    }
   
    /**
     * Registers the student for a camp.
	 * Gives option to register as attendee or committee member.
     */
    public void registerCamp() { //update enrolled camps, boolean if campcomm
        int choice;
        System.out.println("Enter your choice: ");
        System.out.println("[1] Register as camp attendee");
        System.out.println("[2] Register as camp committee");
        Scanner sc = new Scanner(System.in);
        choice = sc.nextInt();
       
        LocalDate currentDate = LocalDate.now();
        // Define a formatter for the YYYYMMDD pattern
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        // Format the date
        String formattedDate = currentDate.format(formatter);
        // Convert the formatted date to an integer
        int dateInt = Integer.parseInt(formattedDate);
       
        switch(choice) {
            case 1:
                int campNumber = 1;
                int campOption;
                for(Camp availCamp: openCamps) {
                    System.out.println("[" + campNumber + "]" + availCamp.getCampName());
                    campNumber++;
                }
                System.out.println("Choose camp to register for: ");
                     
                campOption = sc.nextInt();
                Camp selectedCamp = getCampOption(campOption);
      
                if(selectedCamp != null) {
                    if(checkCampBlacklist(selectedCamp)){
                        System.out.println("Blacklisted! Registration failed.");
                        break;
                    }
                    if(checkDateClash(selectedCamp.getCampStartDate(),selectedCamp.getCampEndDate())){
                        System.out.println("Date Clash! Registration failed.");
                        break;
                    }
                    if (selectedCamp.getRegistrationClosingDate() < dateInt){
                        System.out.println("Registration date over. ");
                        break;
                    }
                    if(selectedCamp.getRemainingSlots() == 0) {
                        System.out.println("No more slots. Registration unsuccessful.");
                        break;
                    }
                    selectedCamp.addAttendee(this); //add attendee auto updates no. of participants
                    enrolledCamps.add(selectedCamp);
                    System.out.println("Successfully registered for " + selectedCamp.getCampName() + " as an attendee.");     
                }      
                else
                    System.out.println("Invalid camp option.");
               
                break;
           
            case 2:
                if(isCampCommitteeMember) {
                    System.out.println("Only allowed to be in camp committee for one camp.");
                    return;
                }      
                int campNo = 1;
                int campOpt;
                System.out.println("Choose camp to register for: ");
               
                for(Camp availCamp: openCamps) {
                    System.out.println("[" + campNo + "]" + availCamp.getCampName());
                    campNo++;
                }
                campOpt = sc.nextInt();
                Camp chosenCamp = getCampOption(campOpt);
               
                if(chosenCamp != null) {
                    if(checkCampBlacklist(chosenCamp)){
                        System.out.println("Blacklisted! Registration failed.");
                        break;
                    }
                    if(checkDateClash(chosenCamp.getCampStartDate(),chosenCamp.getCampEndDate())){
                        System.out.println("Date Clash! Registration failed.");
                        break;
                    }
                    if (chosenCamp.getRegistrationClosingDate() < dateInt){
                        System.out.println("Registration date over. ");
                        break;
                    }
                    if(chosenCamp.getRemainingCommitteeSlots() == 0) {
                        System.out.println("No more slots. Registration unsuccessful.");
                        break;
                    }
                    this.isCampCommitteeMember = true;  //set bool to true
                    CommitteeMember = new CampCommitteeMembers(super.getName(), super.getEmail(), super.getFaculty(), isCampCommitteeMember, chosenCamp);   //designate camp to committee member
                    chosenCamp.addCampCommitteeMember(CommitteeMember); //add member to camp member list, auto update slots
                    enrolledCamps.add(chosenCamp);
                    System.out.println("Successfully registered for " + chosenCamp.getCampName() + " as camp committee member.");
                }
                    
                else
                    System.out.println("Invalid camp option.");     
                break;    
        }       
    }
   
    /**
	 * Gets the {@code Camp} object at the specified index of the list of all camps.
	 * @param campOption The camp selected.
	 * @return The camp selected.
	 */
    public Camp getCampOption(int campOption) { //new method i created
        if(campOption >= 1 && campOption <= openCamps.size()) {
            return openCamps.get(campOption-1);
        }
        return null;
    }
   
    /**
	 * Prints the number of camp committee slots for the camp.
	 * @param camp The camp to view camp committee slots.
	 */
    public void viewCampCommitteeSlots(Camp camp) {
        for(Camp opCamp: openCamps) {
            System.out.printf("Remaining Slots for " + opCamp.getCampName() + ": " + camp.getCampCommitteeSlots());
            //no getCampCommitteeSlots method
        }
    }
   
    /**
	 * Submits new {@code Enquiry} obj for a specific camp.
	 * @param camp The camp to submit enquiry.
	 */
    public void submitEnquiry(Camp camp) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your enquiry for " + camp.getCampName() + ": ");
        String enquiry = scanner.nextLine();
       
        Enquiry newEnquiry = new Enquiry();
        newEnquiry.setEnquiry(enquiry);
        ownEnquiries.add(newEnquiry);
        System.out.println("Enquiry submitted successfully");
    }
   
    /**
	 * Prints out list of own enquiries with processed status.
	 */
    public void viewEnquiry() { //print private attribute ownEnquiries
        if(ownEnquiries.isEmpty()) {
            System.out.println("No enquiries found");
            return;
        }
        else {
            System.out.println("Your enquiries: ");
            for(Enquiry enquiry: ownEnquiries) {
                if(!enquiry.getProcessed()) {
                    enquiry.viewEnquiry(); //viewEnquiry from Enquiry class
                }
                else
                    System.out.println("Sorry, enquiry already processed.");
            }
        }
    }
   
    /**
	 * Edits enquiry in {@code Enquiry} object when it is still unprocessed.
	 */
    public void editEnquiry() {
        if (ownEnquiries.isEmpty()) {
           System.out.println("No enquiries found.");
           return;
        }
        //print enquiry menu
        System.out.println("Select an enquiry to edit:");
        for (int i = 0; i < ownEnquiries.size(); i++) {
            System.out.println("[" + (i + 1) + "] ");
            ownEnquiries.get(i).viewEnquiry();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of the enquiry to edit:");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice > 0 && choice <= ownEnquiries.size() && !ownEnquiries.get(choice - 1).getProcessed()) {
            Enquiry selectedEnquiry = ownEnquiries.get(choice - 1);
            Scanner editScanner = new Scanner(System.in);
            System.out.println("Enter the updated enquiry:");
            String editEnquiry = scanner.nextLine();
            String input;
                
            selectedEnquiry.setEnquiry(editEnquiry);
            System.out.println("Enquiry updated successfully:");      
        }  
        else {
            System.out.println("Unsuccessful.");
        }
    }
       
    /**
	 * Deletes {@code Enquiry} object from the list of enquiries within the camp.
	 * @param camp The camp to delete enquiry from.
	 */
    public void deleteEnquiry(Camp camp) { //removeEnquiry in camp class, need update private attribute ownEnquiries list
        if (ownEnquiries.isEmpty()) {
            System.out.println("No enquiries to delete.");
            return;
        }
       
        System.out.println("Select an enquiry to delete: ");
        for (int i = 0; i < ownEnquiries.size(); i++) {
            System.out.println("[" + (i + 1) + "] ");
            ownEnquiries.get(i).viewEnquiry();
        }
       
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        if (choice > 0 && choice <= ownEnquiries.size() && !ownEnquiries.get(choice - 1).getProcessed()) {
            Enquiry selectedEnquiry = ownEnquiries.get(choice - 1);
            camp.removeEnquiry(selectedEnquiry); //remove from camp enquiries
            ownEnquiries.remove(selectedEnquiry); //remove from ownEnquiries
            System.out.println("Enquiry deleted successfully.");
        } else {
            System.out.println("Invalid choice.");
        }
    }
   
    /**
     * View the profile of the student, including camp committee status.
     */
	@Override
    public void viewProfile() {//override from user class print out camp committee status
        super.viewProfile();
        System.out.println("Camp committee status: " + (isCampCommitteeMember ? "Yes" : "No"));
    }
   
    /**
     * View the camps the student is registered for, along with their roles.
     */
    public void viewRegisteredCamps() {//get attendee list for each enrolled camp. Iterate thru, for (Student student : attendeeList){ if student.getName().equalsto(super.getName()) then just print attendee role. If not print camp comm role, then the rest can dont loop just print student role(can just create a in-method bool var or smth)
        if (enrolledCamps.isEmpty()) {
            System.out.println("No registered camps.");
            return;
        }
       
        for(Camp camp: enrolledCamps) {
            System.out.println("Camp Name: " + camp.getCampName());
           
            if(camp.getAttendees().contains(this)) {
                System.out.println("Role: Attendee");
            }
            if(camp.getCampCommitteeMembers().contains(this)) {
                System.out.println("Role: Camp Committee Member");
            }
        }
        return;
    }
   
    /**
     * Withdraw students only from a registered camp.
	 * Updates list of camps that the student is blacklisted from registering again.
     * @param camp The camp to withdraw from.
     */
    public void withdrawCamp(Camp camp) {//if is camp comm, cannot withdraw,else update withdrawncamps, enrolled camps, and attendee list in specified camp removeAttendee()
        if(isCampCommitteeMember) {
            System.out.println("Camp committee member are not allowed to withdraw from the camp");
            return;
        }
        withdrawnCamps.add(camp);
        enrolledCamps.remove(camp);
        camp.removeAttendee(this);
    }
   
    /**
     * Checks if specified camp is blacklisted for the student.
     * @param camp The camp to check.
     * @return {@code true} if the camp is blacklisted, {@code false} otherwise.
     */
    public boolean checkCampBlacklist(Camp camp) {
        return withdrawnCamps.contains(camp);
    }
   
    /**
	 * Gets the {@code Camp} object for camp registered by accessing its index from list of registered camps.
	 * @param campOption The index of the camp selected.
	 * @return The camp selected.
	 */
    public Camp getRegisteredCampOption(int campOption) {   //this is a new method to return registeredCamp obj
        return enrolledCamps.get(campOption-1);
    }
   
    /**
	 * Gets the {@code CampCommitteeMembers} object attributed to the student.
	 * @return The camp committee member object attributed to the student.
	 */
    public CampCommitteeMembers getCommitteeMember() {
        return this.CommitteeMember;
    }
   
    /**
	 * Checks if the student is a camp committee member for any camps.
	 * @return {@code true} if student is an instance of camp committee member, otherwise {@code false}.
	 */
    public boolean getIsCampCommitteeMember() {
        return this.isCampCommitteeMember;
    }
}
