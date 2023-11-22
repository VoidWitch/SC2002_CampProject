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
    	int date = 1;
    	int no = 99;
    	int campCommitteeSlots = 0;
    	boolean visibility = true;
    	
    	System.out.println("Enter the name for the camp: ");
    	String campName = sc.nextLine();
    	System.out.println("Enter the start date for the camp: ");
    	int startDate = sc.nextInt();
    	sc.nextLine();
    	System.out.println("Enter the last date for the camp: ");
    	int endDate = sc.nextInt();
    	sc.nextLine();
    	System.out.println("Enter the registration closing date for the camp: ");
    	int registrationClosingDate = sc.nextInt();
    	sc.nextLine();
    	System.out.println("Enter the location for the camp: ");
    	String location = sc.nextLine();
    	System.out.println("Enter the total participant slots for the camp: ");
    	int totalSlots = sc.nextInt();
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
        		System.out.println("The camp committee slots should be more than 10.");
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
    	
    	Camp newCamp = new Camp(campName, startDate, endDate, registrationClosingDate, location, totalSlots, campCommitteeSlots, campDescription,
    			super.getName(), visibility,super.getFaculty());

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
        //camp.viewAttendeeList();    //view attendee list
        //camp.viewCampCommitteeMemberList(); //view committee member list
    }


    public void viewAllCamps(){  //view camp names only - click in to view more
        allCamp.sortAllCamps();
    	for(Camp camp : allCamp.getAllCamps()){     //iterate thru allcamps
            camp.viewCampInfo();
    		System.out.println();
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
        suggestion.setProcessed(true);  //process suggestion
        committeemember.addPoints(1); //add points for approval
    }


    public Camp getCamp(String campName){   //main will get user input for camp choice
        return allCamp.getCamp(campName);
    }


    public void editCamp(Camp camp){
		boolean vEdit = true;
		while(vEdit) {
			System.out.println("\n--- Edit Menu ---");
	        System.out.println("[1] Camp Name");
			System.out.println("[2] Camp Date");
	        System.out.println("[3] Registration Closing Date");
	        System.out.println("[4] Toggle Camp Visibility");
	        System.out.println("[5] Location");
	        System.out.println("[6] Total Slots");
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
	                System.out.println("Camp name has been changed from " + oldCampName + " to " + camp.getCampName() + " .");
	                break;
	            case 2:
	            	System.out.println("Current start Date: " + camp.getCampStartDate());
	            	System.out.println("Current end Date: " + camp.getCampEndDate());
	            	System.out.println("Enter new start date for Camp " + camp.getCampName() + " : ");
	            	int newStartDate = sc.nextInt();
	            	sc.nextLine();
	            	System.out.println("Enter new end date for Camp " + camp.getCampName() + " : ");
	            	int newEndDate = sc.nextInt();
	            	sc.nextLine();
	            	camp.setCampDates(newStartDate,newEndDate);
	            	System.out.println("Camp date of Camp " + camp.getCampName() + " has been changed to " + camp.getCampStartDate() + " - " + camp.getCampEndDate() + ".");
	                break;
	            case 3:
	                int oldDate = camp.getRegistrationClosingDate();
	                System.out.println("Current registration closing date: " + oldDate);
	                System.out.println("Enter new registration closing date: ");
	                int newRegistrationClosingDate = sc.nextInt();
	                camp.setRegistrationClosingDate(newRegistrationClosingDate);
	                System.out.println("Registration closing date of Camp " + camp.getCampName() + " has been changed from " + oldDate + " to " + camp.getRegistrationClosingDate() +".");
	                break;
	            case 4:
	            	boolean oldVis = camp.getVisibility();
	            	if(oldVis) {
	            		System.out.println("Camp " + camp.getCampName() + " is currently open for students.");
	            		System.out.println("Do you want to toggle its visibility? (Y/N)");
	            	}
	            	else {
	            		System.out.println("Camp " + camp.getCampName() + " is currently NOT open for students.");
	            	}
	            	System.out.println("Do you want to toggle its visibility? (Y/N)");
	            	String ans = sc.nextLine();
	            	if(ans.equals("Y")) {
	            		if(oldVis) {
	            			camp.setVisibility(false);
	            			System.out.println("Camp " + camp.getCampName() + " has been changed to NOT open for students.");
	            		}
	            		else if (!oldVis) {
	            			camp.setVisibility(true);
	            			System.out.println("Camp " + camp.getCampName() + " has been changed to open for students.");
	            		}
	            		
	            	}
	            	else if(ans.equals("N")) {
	            		if(oldVis) {
	            			System.out.println("Camp " + camp.getCampName() + " remains open for students.");
	            		}
	            		else if (!oldVis) {
	            			System.out.println("Camp " + camp.getCampName() + " remains NOT open for students.");
	            		}
	            	}
	            	 break;  //toggle boolean
	            case 5:
	                String oldLocation = camp.getLocation();
	                System.out.println("Current location for Camp " + camp.getCampName() + ": " + oldLocation);
	                System.out.println("Enter new location: ");
	            	String newLocation = sc.nextLine();
	                camp.setLocation(newLocation);
	                System.out.println("Camp " + camp.getCampName() + " location has been changed from " + oldLocation + " to " + camp.getLocation() + ".");
	                break;
	            case 6:
	                int oldSlots = camp.getTotalSlots();
	                System.out.println("The currect total slots for Camp " + camp.getCampName() + ": " + oldSlots);
	            	System.out.println("Enter new total slots: ");
	            	int newSlots = sc.nextInt();
	            	sc.nextLine();
	                camp.setTotalSlots(newSlots); 
	                System.out.println("The total slots for Camp " + camp.getCampName() + " has been changed from " + oldSlots + " to " + camp.getTotalSlots() + ".");
	                break;
	            case 7:
	                int temp = 11;
	            	int oldCMSlots = camp.getCampCommitteeSlots();
	                System.out.println("The currect Committee slots for Camp " + camp.getCampName() + ": " + oldCMSlots);
	                do {
	                	System.out.println("Enter new camp committee member slots: (No more than 10)");
	                	temp = sc.nextInt();
	                	sc.nextLine();
	                	if(temp<=10 & temp>0) {
	                		int newCMSlots = temp;
	                		camp.setCampCommitteeSlots(newCMSlots); 
	                        System.out.println("The  for Camp " + camp.getCampName() + " has been changed from " + oldCMSlots + " to " + camp.getTotalSlots() + ".");
	                		break;
	                	}
	                	else {
	                		System.out.println("The camp committee slots should be more than 10.");
	                	}
	            	}while(temp>10);
	                
	                
	                break;
	            case 8:
	            	String oldDesc = camp.getDescription();
	                System.out.println("The current description for Camp " + camp.getCampName() + ": " + oldDesc);
	                System.out.println("Enter new description: ");
	            	String newDesc = sc.nextLine();
	                camp.setLocation(newDesc);
	                System.out.println("Camp " + camp.getCampName() + " description has been changed from " + oldDesc + " to " + camp.getDescription() + ".");
	            	break;
	            case 9:
	            	vEdit = false;
	            	break;
	            default:
	            	System.out.println("Invalid Input.");
	        }
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
    
    public List<Camp> getCampsCreated(){
    	return this.campsCreated;
    }
}






