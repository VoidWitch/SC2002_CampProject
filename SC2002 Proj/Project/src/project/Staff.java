package project;

import java.util.*;


public class Staff extends User{ //inherit user class

    private List<Camp> campsCreated;
    Scanner sc = new Scanner(System.in);
   
    public Staff(String name, String email, String faculty){ 
    	super(name,email,faculty,true);
    	this.campsCreated = new ArrayList<>();
    }
   
    //public void changePassword(){ super.changePassword(); }


    public void createCamp(){
        //camp tied to staffIC and their faculty
        Camp newCamp = new Camp(super.getName(), super.getFaculty());
        campsCreated.add(newCamp);  //update to campsCreated list by staff


        //allCamp list is a static attribute -> just have to declare an allCamp obj in main
        allCamp.addCamp(newCamp);   //update to allCamp
    }


    public void deleteCamp(Camp camp){
    	campsCreated.remove(camp);  //remove from campsCreated list
        allCamp.deleteCamp(camp);   //remove from allCamp
    }

    public void viewCreatedCamps() { //view created camp names only - click in to view more
    	if(campsCreated.isEmpty()) {
    		System.out.println("No camps have been created.");
    		return;
    	}
    	System.out.println("Camps created:");
    	for(Camp camp : campsCreated) { //iterate thru created camps only
    		System.out.println(camp.getCampName());
    	}
    }
    
    public Camp getCreatedCamp(String campName){   //ask for camp name
        for(Camp camp: campsCreated){    //check if camp is within list of camps created
            if(campName.toLowerCase().equals(camp.getCampName().toLowerCase())){
                return camp; }
        }
        return null;
    }
    public void viewCamp(Camp camp){    //click into camp to get more details
        camp.viewCampInfo();    //view camp details
        camp.viewAttendeeList();    //view attendee list
        camp.viewCampCommitteeMemberList(); //view committee member list
    }


    public void viewAllCamps(){  //view camp names only - click in to view more
        for(Camp camp : allCamp.getAllCamps()){     //iterate thru allcamps
            System.out.println(camp.getCampName());
        }
    }

    public void viewCampEnquiries(Camp camp){
        camp.viewEnquiries();   //view all enquiries - number the enquiries when printing out
    }


    public void replyCampEnquiries(Enquiry enquiry){    //provide option to reply enquiries
        //take in enquiry no. and retrieve from enquiry list using index from user input
        System.out.println("Reply: ");
        String reply = sc.nextLine();
        enquiry.replyEnquiry(reply);
    }


    public void viewSuggestions(Camp camp){
        camp.viewSuggestions();
    }


    public void approveSuggestion(Suggestion suggestion, CampCommitteeMembers committeemember){
        suggestion.setProcessed();  //process suggestion
        committeemember.addPoints(1); //add points for approval
    }


    public Camp getCamp(String campName){   //main will get user input for camp choice
        return allCamp.getCamp(campName);
    }


    public void editCamp(Camp camp){
        System.out.println("Edit(choose an option): "); //order according to format in CAMPINFO
        int choice = sc.nextInt();
        switch (choice){
            case 1:
                System.out.print("Enter new camp name: ");
                camp.setCampName(sc.nextLine()); break;
            case 2:
                while(true){
                    System.out.print("1. Add date:\n" +
                                    "2. Remove date:\n" +
                                    "3. Quit\n");
                    int input = sc.nextInt();
                    if(input==1){
                        System.out.print("Enter date: ");
                        camp.addCampDate(sc.nextInt());
                        System.out.println("Dates: " + camp.getCampDates()); continue;    //view updated dates
                    }
                    if(input==2){
                        System.out.print("Enter date: ");
                        camp.removeCampDate(sc.nextInt());
                        System.out.println("Dates: " + camp.getCampDates()); continue;    //view updated dates
                    }
                    break;
                } break;
            case 3:
                System.out.print("Enter new registration closing date: ");
                camp.setRegistrationClosingDate(sc.nextInt()); break;
            case 4:
            	System.out.println("Do you want this camp to be visible? (Y/N");
            	String visible = sc.nextLine();
            	if(visible=="Y") {
            		camp.setVisibility(true);
            	}
            	else if(visible=="N") {
            		camp.setVisibility(false);
            	}
            	 break;  //toggle boolean
            case 5:
                System.out.print("Enter new location: ");
                camp.setLocation(sc.nextLine()); break;
            case 6:
                System.out.print("Enter new total slots: ");
                camp.setTotalSlots(sc.nextInt()); break;
            case 7:
                System.out.print("Enter new camp committee slots: ");
                camp.setCampCommitteeSlots(sc.nextInt()); break;
            case 8:
                System.out.print("Enter new description: ");
                camp.setDescription(sc.nextLine()); break;
        }
    }


    public void generateCSV(Camp camp){
        System.out.print("1. Generate all participant list\n" +
                            "2. Generate attendee list\n" +
                            "3. Generate committee member list\n" +
                            "4. Generate committee member performance list\n");
        int choice = sc.nextInt();
        switch (choice){
            case 1: GenerateCSV.generateNoFilter(camp);
            case 2: GenerateCSV.generateAttendee(camp);
            case 3: GenerateCSV.generateCampCommittee(camp);
            case 4: GenerateCSV.generatePerformance(camp);
        }
    }


    public void generateTXT(Camp camp){
        System.out.print("1. Generate all participant list\n" +
                            "2. Generate attendee list\n" +
                            "3. Generate committee member list\n" +
                            "4. Generate committee member performance list\n");
        int choice = sc.nextInt();
        switch (choice){
            case 1: GenerateTXT.generateNoFilter(camp);
            case 2: GenerateTXT.generateAttendee(camp);
            case 3: GenerateTXT.generateCampCommiteeMember(camp);
            case 4: GenerateTXT.generatePerformance(camp);
        }
    }


    public List<Student> getAttendee(Camp camp){
        return camp.getAttendees();
    }


    public List<CampCommitteeMembers> getCampCommitteeMembers(Camp camp){
        return camp.getCampCommitteeMembers();
    }
    
    public static void main(String[] args) {
    	Staff staff = (Staff) User.loggedInUser;
    	Scanner sc = new Scanner(System.in);
    	int choice;
    	
    	while(true) {
    		System.out.println("\n--- Staff Menu ---");
            System.out.println("1. Create Camp");
            System.out.println("2. Edit Camp");
            System.out.println("3. Delete Camp");
            System.out.println("4. View Camp");
            System.out.println("5. View Created Camps");
            System.out.println("6. View All Camps");
            System.out.println("7. Toggle Camp Visibility");
            System.out.println("8. View Camp Enquiries");
            System.out.println("9. Reply to Camp Enquiries");
            System.out.println("10. View Suggestions");
            System.out.println("11. Approve Suggestion");
            System.out.println("12. Generate CSV Report");
            System.out.println("13. Generate TXT Report");
            System.out.println("14. Change Password");
            System.out.println("15. Log Out");
            System.out.println("16. Exit");
            System.out.print("Enter your choice: ");
            
            try {
            	choice = sc.nextInt();
            	
            	switch(choice) {
            	case 1:
            		staff.createCamp();
            		break;
            	case 2:
            		System.out.println("Enter the camp you want to toggle its visibility to students: ");
            		String campName = sc.nextLine();
            		Camp campToEdit = staff.getCreatedCamp(campName);
            		if(campToEdit != null) {
            			staff.editCamp(campToEdit);
            		}
            		else {
            			System.out.println("You dont have a camp created called " + campName +" .");
            		}
            		break;
            	case 3:
            		staff.viewCreatedCamps();
            		if(staff.campsCreated.isEmpty()) {
            			System.out.println("No camp is deleted.");
            		}
            		else {
            	    	System.out.println("Enter the name of the camp you want to delete: ");
            	    	campName = sc.nextLine();
            	    	Camp campToDelete = staff.getCreatedCamp(campName); 
            	    	if(campToDelete!=null) {
            	    		staff.deleteCamp(campToDelete);
            	    		System.out.println("Camp " + campName + " is deleted successfully.");
            	    	}
            	    	else {
            	    		System.out.println("Camp not found.");
            	    	}
            		}
            		break;
            	case 4:
            		System.out.println("Enter the camp you want to view: ");
            		campName = sc.nextLine();
            		Camp campToView = allCamp.getCamp(campName);
            		if(campToView != null) {
            			staff.viewCamp(campToView);
            		}
            		else {
            			System.out.println("No such camp.");
            		}
            		break;
            	case 5:
            		staff.viewCreatedCamps();
            		break;
            	case 6:
            		staff.viewAllCamps();
            		break;
            	case 7:
            		System.out.println("Enter the camp you want to toggle its visibility to students: ");
            		campName = sc.nextLine();
            		Camp campToToggle = allCamp.getCamp(campName);
            		if(campToToggle != null) {
            			if(campToToggle.getVisibility()== true) {
            				System.out.println("Camp " + campName + " is visible to students.");
            			}
            			else  {
            				System.out.println("Camp " + campName + " is not visible to students.");
            			}
            			System.out.println("Do you want to toggle its visibility?(Y/N)");
            			String answer = sc.nextLine();
            			do {
            				if(answer == "Y") {
                				campToToggle.toggleVisibility();
                				System.out.println("Camp " + campName + " visibility is toggled.");
                				break;
                			}
                			else if(answer == "N") {
                				System.out.println("Camp " + campName + " visibility is not toggled.");
                				break;
                			}
                			else {
                				System.out.println("Invalid input.");
                				continue;
                			}
            			}while(answer!="Y" & answer!="N");
            		}
            		else {
            			System.out.println("Camp " + campName + " is not found.");
            		}
            	case 8:
            		staff.viewCreatedCamps();
            		System.out.println("Enter the camp that you want to view enquiries: ");
            		campName = sc.nextLine();
            		Camp campToViewEnq = staff.getCreatedCamp(campName);
            		if(campToViewEnq!=null) {
            			campToViewEnq.viewEnquiries();
            		}
            		else {
            			System.out.println("There is no Camp " + campName + " created.");
            		}
            		break;
            	case 9:
            		System.out.println("Enter the camp: ");
            		campName = sc.nextLine();
            		Camp campReply = staff.getCreatedCamp(campName);
            		if(campReply!=null) {
            			campReply.viewEnquiries();
            			System.out.println("Enter the enquiry you want to reply: ");
                		String enquiryName = sc.nextLine();
                		Enquiry enquiryToReply = campReply.getEnquiry(enquiryName);
                		if(enquiryToReply != null) {
                			System.out.println(enquiryToReply.getEnquiry());
                			System.out.println("Enter your reply for this enquiry: ");
                			String reply = sc.nextLine();
                			enquiryToReply.replyEnquiry(reply);
                			enquiryToReply.viewEnquiry();
                		}
                		else {
                			System.out.println("There is no such enquiry.");
                		}
            		}
            		else {
            			System.out.println("There is no Camp " + campName + " created.");
            		}
            		
            		break;
            	case 10:
            		staff.viewCreatedCamps();
            		System.out.println("Enter the camp that you want to view suggestion: ");
            		campName = sc.nextLine();
            		Camp campToViewSug = staff.getCreatedCamp(campName);
            		if(campToViewSug!=null) {
            			campToViewSug.viewSuggestions();
            		}
            		else {
            			System.out.println("There is no Camp " + campName + " created.");
            		}
            		break;
            	case 11:
            		System.out.println("Enter the camp: ");
            		campName = sc.nextLine();
            		Camp campSug = staff.getCreatedCamp(campName);
            		if(campSug!=null) {
            			campSug.viewSuggestions();
            			System.out.println("Enter the suggestion you want to process: ");
                		String sugName = sc.nextLine();
                		Suggestion sugToProcessed = campSug.getSuggestion(sugName);
                		if(sugToProcessed != null) {
                			System.out.println(sugToProcessed.getSuggestion());
                			System.out.println("Do you want to approve this suggestion? (Y/N) ");
                			String reply = sc.nextLine();
                			do {
                				if(reply == "Y") {
                    				sugToProcessed.setProcessed();
                    				campSug.addApprovedSuggestion(sugToProcessed);
                    				System.out.println("The suggestion is approved.");
                    				break;
                    			}
                    			else if(reply == "N") {
                    				System.out.println("The suggestion is not approved yet.");
                    				break;
                    			}
                    			else {
                    				System.out.println("Invalid input.");
                    				continue;
                    			}
                			}while(reply!="Y" & reply!="N");
                		}
                		else {
                			System.out.println("There is no such enquiry.");
                		}
            		}
            		else {
            			System.out.println("There is no Camp " + campName + " created.");
            		}
            		
            		break;
            	case 12:
            		System.out.println("Enter the name of the camp you want to generate CSV file: ");
        	    	campName = sc.nextLine();
        	    	Camp campCSV = staff.getCreatedCamp(campName); 
        	    	if(campCSV!=null) {
        	    		staff.generateCSV(campCSV);
        	    		System.out.println("CVS file for Camp " + campName + " is generated successfully.");
        	    	}
        	    	else {
        	    		System.out.println("Camp not found.");
        	    	}
            		
            		break;
            	case 13:
            		System.out.println("Enter the name of the camp you want to generate TXT file: ");
        	    	campName = sc.nextLine();
        	    	Camp campTXT = staff.getCreatedCamp(campName); 
        	    	if(campTXT!=null) {
        	    		staff.generateTXT(campTXT);
        	    		System.out.println("TXT file for Camp " + campName + " is generated successfully.");
        	    	}
        	    	else {
        	    		System.out.println("Camp not found.");
        	    	}
            		break;
            	case 14:
            		User.changePassword(staff);
            		break;
            	case 15:
            		User.loggedInUser = null;
            		User.main(null);
            		return;
            	case 16:
            		System.out.println("Exiting...");
            		System.exit(0);
            		break;
            	default:
            		System.out.println("Invalid choice. Try again");
            	}
            }catch(NumberFormatException e) {
            	System.out.println("Please enter a valid number.");
            }
    	}
    }
}






