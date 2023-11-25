package proj3;

import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The {@code Staff} class represents a staff member in the system, inheriting from the {@link User} class.
 * It includes methods for creating, managing, and viewing camps, as well as handling camp-related tasks.
 * 
 * @author Gao Linyue, Tey Cia Meng
 */

public class Staff extends User {

    private Camp campCreated;
    Scanner sc = new Scanner(System.in);
    
    /**
     * Constructs a new Staff with the specified name, email, and faculty.
     * @param name    The name of the staff member.
     * @param email   The email of the staff member.
     * @param faculty The faculty of the staff member.
     */
    public Staff(String name, String email, String faculty){
        super(name,email,faculty,true);
        this.campCreated = null;
    }
    
    /**
     * Creates a new camp with user input for camp details.
	 * Updates to the list of camps created and the list of all camps.
     */
    public void createCamp(){
        //camp tied to staffIC and their faculty
        int date = 1;
        int no = 99;
        int campCommitteeSlots = 0;
        boolean visibility = true;
        boolean open = true;
        boolean cName = true;
        boolean same = false;
        String campName = "Default";
        
        do {
        	same = false;
        	System.out.println("Enter the name for the camp: ");
            String name = sc.nextLine();
            for(Camp camp : allCamp.getAllCamps()) {
            	if(camp.getCampName().equals(name)) {
            		System.out.println("\nThe camp name '" + name + "' has already been taken. Please try another name.\n");
            		same = true;
            		break;
            	}
            }
            if(same!=true) {
            	campName = name;
            	cName = false;
            }
        }while(cName == true);
        
        System.out.println("Enter the start date for the camp: (YYYYMMDD) ");
        int startDate = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the last date for the camp: (YYYYMMDD) ");
        int endDate = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the registration closing date for the camp: (YYYYMMDD)");
        int registrationClosingDate = sc.nextInt();
        sc.nextLine();
        System.out.println("Is the camp open to whole NTU: (Y/N)");
    	String openC = sc.nextLine();
		do {
			
			if(openC.equals("Y")) {
				open = true;
				break;
			}
			else if(openC.equals("N")) {
				open = false;
				break;
			}
			else {
				System.out.println("Invalid input. Please enter 'Y' or 'N'. ");
				openC = sc.nextLine();
			}
		}while(!openC.equals("Y") && !openC.equals("N"));
        System.out.println("Enter the location for the camp: ");
        String location = sc.nextLine();
        System.out.println("Enter the participant slots for the camp: ");
        int participantSlots = sc.nextInt();
        sc.nextLine();
        do {
            System.out.println("Enter the camp committee slots for the camp: (No more than 10)");
            no = sc.nextInt();
            sc.nextLine();
            if(no<=10 & no>0) {
                campCommitteeSlots =  no;
                break;
            }
            else {
                System.out.println("The camp committee slots should be less than 10.\n");
            }
        }while(no>10);
        System.out.println("Enter the description for the camp: ");
        String campDescription = sc.nextLine();
        System.out.println("Do you want this camp to be visible to students? (Y/N) ");
        String answer = sc.nextLine();
            do {   
                if(answer.equals("Y")) {
                    visibility = true;
                    break;
                }
                else if(answer.equals("N")) {
                    visibility = false;
                    break;
                }
                else {
                    System.out.println("Invalid input. Please enter 'Y' or 'N'. ");
                    answer = sc.nextLine();
                }
            }while(!answer.equals("Y") && !answer.equals("N"));
        
        Camp newCamp = new Camp(campName, startDate, endDate, registrationClosingDate, open, location, participantSlots, campCommitteeSlots, campDescription,
                super.getName(), visibility,super.getFaculty());
        campCreated = newCamp;  //update to campsCreated list by staff
        //allCamp list is a static attribute -> just have to declare an allCamp obj in main
        allCamp.addCamp(newCamp);   //update to allCamp
        System.out.println("\nCamp '" + campName + "' is created successfully.\n");
    }

    /**
     * Deletes a {@code Camp} object, removing it from the staff's created camps list and the system's all camps list.
     * @param camp The camp to be deleted
     */
    public void deleteCamp(Camp camp){
        campCreated = null;  //remove from campsCreated list
        allCamp.deleteCamp(camp);   //remove from allCamp
    }

    /**
     * Prints the names of camps created by the staff member.
     */
    public void viewCreatedCamps() { //view created camp names only - click in to view more
        if(campCreated == null) {
            System.out.println("No camps have been created.");
            return;
        }
        System.out.println("-----------------------");
        System.out.println("Camps created: " +campCreated.getCampName());

    }
    
    /**
	 * Gets the {@code Camp} object created by the staff accessing its name.
	 * @param campName The name of the camp.
	 * @return The camp selected.
	 */
    

    /**
	 * Prints out camp information of a specified {@code Camp} object.
	 * @param camp The camp specified.
	 */
    public void viewCamp(Camp camp){    //click into camp to get more details
        
    	camp.viewCampInfo();    //view camp details
        //camp.viewAttendeeList();    //view attendee list
        //camp.viewCampCommitteeMemberList(); //view committee member list
    }

    /**
	 * Prints out all camps with the option of filtering.
	 */
    public void viewAllCamps(){  //view camp names only - click in to view more
    	//allCamp.sortAllCamps();
        System.out.println("---------------------");
        System.out.println("All Camps:\n");
        int i=1;
        for(Camp camp : allCamp.getAllCamps()){     //iterate thru allcamps
            //camp.viewCampInfo();
            System.out.println(i+". " + camp.getCampName());
            i++;
        }
    }

    /**
	 * Prints out all the enquiries of the specified {@code Camp} object.
	 * @param camp The camp specified.
	 */
    public void viewCampEnquiries(Camp camp){
        camp.viewEnquiries();   //view all enquiries - number the enquiries when printing out
    }

    /**
	 * Reply to {@code Enquiry} object.
	 * @param enquiry The enquiry to be replied to.
	 */
    public void replyCampEnquiries(Enquiry enquiry){    //provide option to reply enquiries
        //take in enquiry no. and retrieve from enquiry list using index from user input
        System.out.println("Reply: ");
        String reply = sc.nextLine();
        enquiry.replyEnquiry(reply);
    }

    /**
	 * View all suggestions for specific {@code Camp} object.
	 * @param camp The camp to view suggestions.
	 */
    public void viewSuggestions(Camp camp){
        camp.viewSuggestions();
    }

    /**
	 * Get {@code Camp} object by specifying its name.
	 * @param campName The name of the camp
	 * @return The camp specified.
	 */
    public Camp getCamp(String campName){   //main will get user input for camp choice
        return allCamp.getCamp(campName);
    }

    /**
	 * Provide options to edit relevant camp details.
	 * @param camp The camp to edit information.
	 */
    public void editCamp(Camp camp){
            boolean vEdit = true;
            while(vEdit) {
                System.out.println("\n--- Edit Menu ---");
                System.out.println("[1] Camp Name");
                System.out.println("[2] Camp Date");
                System.out.println("[3] Registration Closing Date");
                System.out.println("[4] Toggle Camp Visibility");
                System.out.println("[5] Location");
                System.out.println("[6] Participant Slots");
                System.out.println("[7] Camp Comittee Slots");
                System.out.println("[8] Camp description");
                System.out.println("[9] Return to Created Camp Menu");
                System.out.println("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice){
                    case 1:
                        String oldCampName = camp.getCampName();
                        System.out.println("Current camp name: " + oldCampName);
                        System.out.println("Enter new camp name: ");
                        String newCampName = sc.nextLine();
                        camp.setCampName(newCampName);
                        System.out.println("Camp name has been changed from '" + oldCampName + "' to '" + camp.getCampName() + "'.");
                        break;
                    case 2:
                        System.out.println("Current start Date: " + camp.getDateInString(camp.getCampStartDate()));
                        System.out.println("Current end Date: " + camp.getDateInString(camp.getCampEndDate()));
                        System.out.println("Enter new start date for Camp '" + camp.getCampName() + "': (YYYYMMDD)");
                        int newStartDate = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Enter new end date for Camp '" + camp.getCampName() + "': (YYYYMMDD)");
                        int newEndDate = sc.nextInt();
                        sc.nextLine();
                        camp.setCampDates(newStartDate,newEndDate);
                        System.out.println("Camp date of Camp '" + camp.getCampName() + "' has been changed to " + camp.getDateInString(camp.getCampStartDate()) + " - " + camp.getDateInString(camp.getCampEndDate()) + ".");
                        break;
                    case 3:
                        int oldDate = camp.getRegistrationClosingDate();
                        System.out.println("Current registration closing date: " + camp.getDateInString(oldDate));
                        System.out.println("Enter new registration closing date: (YYYYMMDD)");
                        int newRegistrationClosingDate = sc.nextInt();
                        camp.setRegistrationClosingDate(newRegistrationClosingDate);
                        
                        System.out.println("Registration closing date of Camp '" + camp.getCampName() + "' has been changed from " + camp.getDateInString(oldDate) + " to " + camp.getDateInString(camp.getRegistrationClosingDate()) +".");
                        break;
                    case 4:
                        if(!camp.getAttendees().isEmpty() || !camp.getCampCommitteeMembers().isEmpty()) {
                        	boolean oldVis = camp.getVisibility();
                            if(oldVis) {
                                System.out.println("\nCamp '" + camp.getCampName() + "' is currently open for students.");
                            }
                            else {
                                System.out.println("\nCamp '" + camp.getCampName() + "' is currently NOT open for students.");
                            }
                            System.out.println("Do you want to toggle its visibility? (Y/N)");
                            String ans = sc.nextLine();
                            if(ans.equals("Y")) {
                                if(oldVis) {
                                    camp.setVisibility(false);
                                    System.out.println("Camp '" + camp.getCampName() + "' has been changed to NOT open for students.");
                                }
                                else if (!oldVis) {
                                    camp.setVisibility(true);
                                    System.out.println("Camp '" + camp.getCampName() + "' has been changed to open for students.");
                                }
                                
                            }
                            else if(ans.equals("N")) {
                                if(oldVis) {
                                    System.out.println("Camp '" + camp.getCampName() + "' remains open for students.");
                                }
                                else if (!oldVis) {
                                    System.out.println("Camp '" + camp.getCampName() + "' remains NOT open for students.");
                                }
                            }
                        }
                        else {
                        	System.out.println("\nVisibility of Camp '" + camp.getCampName() + "' can't be toggled as students has registered for the camp.\n");
                        }

                        break;  //toggle boolean
                    case 5:
                        String oldLocation = camp.getLocation();
                        System.out.println("Current location for Camp '" + camp.getCampName() + "': " + oldLocation);
                        System.out.println("Enter new location: ");
                        String newLocation = sc.nextLine();
                        camp.setLocation(newLocation);
                        System.out.println("Camp '" + camp.getCampName() + "' location has been changed from '" + oldLocation + "' to '" + camp.getLocation() + "'.");
                        break;
                    case 6:
                        int oldSlots = camp.getParticipantSlots();
                        System.out.println("The currect participant slots for Camp '" + camp.getCampName() + "': " + oldSlots);
                        System.out.println("Enter new participant slots: ");
                        int newSlots = sc.nextInt();
                        sc.nextLine();
                        camp.setParticipantSlots(newSlots);
                        System.out.println("The pasticipant slots for Camp '" + camp.getCampName() + "' has been changed from " + oldSlots + " to " + camp.getParticipantSlots() + ".");
                        break;
                    case 7:
                        int temp = 11;
                        int oldCMSlots = camp.getCampCommitteeSlots();
                        System.out.println("The currect Committee slots for Camp '" + camp.getCampName() + "': " + oldCMSlots);
                        do {
                            System.out.println("Enter new camp committee member slots: (No more than 10)");
                            temp = sc.nextInt();
                            sc.nextLine();
                            if(temp<=10 & temp>0) {
                                int newCMSlots = temp;
                                camp.setCampCommitteeSlots(newCMSlots);
                                System.out.println("The Committee slots for Camp '" + camp.getCampName() + "' has been changed from " + oldCMSlots + " to " + camp.getCampCommitteeSlots() + ".");
                                break;
                            }
                            else {
                                System.out.println("The camp committee slots should be less than 10.\n");
                            }
                        }while(temp>10);
                    
                    
                        break;
                    case 8:
                        String oldDesc = camp.getDescription();
                        System.out.println("The current description for Camp '" + camp.getCampName() + "': '" + oldDesc +"'");
                        System.out.println("Enter new description: ");
                        String newDesc = sc.nextLine();
                        camp.setDescription(newDesc);
                        System.out.println("Camp '" + camp.getCampName() + "' description has been changed from '" + oldDesc + "' to '" + camp.getDescription() + "'.");
                        break;
                    case 9:
                        vEdit = false;
                        break;
                    default:
                        System.out.println("Invalid Input.");
                }
        }
    }

    /**
     * Generates CSV reports based on user input for a specific camp.
     * @param camp The camp for which reports are generated.
     */
    public void generateCSV(Camp camp){
		GenerateCSV csv = new GenerateCSV();
        System.out.print("1. Generate all participant list\n" +
                            "2. Generate attendee list\n" +
                            "3. Generate committee member list\n" +
                            "4. Generate committee member performance list\n");
        int choice = sc.nextInt();
        switch (choice){
            case 1: csv.generateNoFilter(camp);
            case 2: csv.generateAttendee(camp);
            case 3: csv.generateCampCommittee(camp);
            case 4: csv.generatePerformance(camp);
        }
    }

    /**
     * Generates TXT reports based on user input for a specific camp.
     * @param camp The camp for which reports are generated
     */
    public void generateTXT(Camp camp){
		GenerateTXT txt = new GenerateTXT();
        System.out.print("1. Generate all participant list\n" +
                            "2. Generate attendee list\n" +
                            "3. Generate committee member list\n" +
                            "4. Generate committee member performance list\n");
        int choice = sc.nextInt();
        switch (choice){
            case 1: txt.generateNoFilter(camp);
            case 2: txt.generateAttendee(camp);
            case 3: txt.generateCampCommittee(camp);
            case 4: txt.generatePerformance(camp);
        }
    }

    /**
     * Gets the list of attendees for a specific camp.
     * @param camp The camp for which attendees are retrieved.
     * @return The list of attendees.
     */
    public List<Student> getAttendee(Camp camp){
        return camp.getAttendees();
    }

    /**
     * Gets the list of camp committee members for a specific camp.
     * @param camp The camp for which committee members are retrieved.
     * @return The list of committee members.
     */
    public List<CampCommitteeMembers> getCampCommitteeMembers(Camp camp){
        return camp.getCampCommitteeMembers();
    }
    
    /**
     * Gets the list of camps created by the staff member.
     * @return The list of created camps.
     */
    public Camp getCampCreated(){
        return this.campCreated;
    }
}

