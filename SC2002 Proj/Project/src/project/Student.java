package project;

import java.util.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class Student extends User { //extends from User

	
	private List <Camp> enrolledCamps = new ArrayList<>();
	private List <Camp> withdrawnCamps = new ArrayList<>();
	private List <Camp> openCamps = new ArrayList<>(); //i created new one
	private List <Enquiry> ownEnquiries = new ArrayList<>();
	private List <Integer> dates = new ArrayList<>(); //int list containing dates student is occupied alr
	private CampCommitteeMembers CommitteeMember;
	private boolean isCampCommitteeMember;
	
	public Student(String name, String email, String faculty, boolean isCampCommitteeMember){
		super(name,email,faculty,false);
		this.isCampCommitteeMember = isCampCommitteeMember; //set attendee by default
	}
	
	public void viewOpenCamps() {
		
		List <Camp> allCamps = allCamp.getAllCamps();//contains all camps
		List <Camp> openCamps = new ArrayList<>();
		openCamps.clear();
		
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
			if(!camp.getVisibility() == false) {
				//UML diagram dont have getVisibility
				continue;
			}
			
			openCamps.add(camp);
	}
		
		for(Camp camp : openCamps){
           camp.viewCampInfo();
           System.out.println();           
           }
	
		
	}
	
	public List<Camp> getOpenCamps() {
		return this.openCamps;
	}
	/*
	public void addDates(List<Integer> newDate) {
		for(Integer date: newDate) {
			dates.add(date);
		}
		
	}
	*/
	public boolean checkDateClash(int startDate, int endDate) { //check if enrolled camp clashes, if no clash return true, continue with reg
		for(Camp enrolledCamp: enrolledCamps) {
			if(startDate<=enrolledCamp.getCampEndDate() && endDate>=enrolledCamp.getCampStartDate()) {
				return true;
			}
		}
		return false;
	}
	
	public void viewRemainingSlots(Camp opCamp) {
		
		System.out.printf("Remaining Slots for " + opCamp.getCampName() + ": " + opCamp.getRemainingSlots());
		//no getRemainingSlots method
		
		
	}
	
	public void registerCamp(Camp camp) { //update enrolled camps, boolean if campcomm
		
		int choice;
		System.out.println("Enter your choice: ");
		System.out.println("[1] Register as camp attendee");
		System.out.println("[2] Register as camp committee");
		Scanner sc = new Scanner(System.in);
		choice = sc.nextInt();
		
		switch(choice) {
			case 1:
				int campNumber = 1;
				int campOption;
				System.out.println("Choose camp to register for: ");
				
				for(Camp availCamp: openCamps) {
					System.out.println("[" + campNumber + "]" + availCamp.getCampName());
					campNumber++;
				}
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
                    if (selectedCamp.getRegistrationClosingDate() < System.currentTimeMillis()){
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
                    if (chosenCamp.getRegistrationClosingDate() < System.currentTimeMillis()){
                        System.out.println("Registration date over. ");
                        break;
                    }
                    if(chosenCamp.getRemainingCommitteeSlots() == 0) {
                        System.out.println("No more slots. Registration unsuccessful.");
                        break;
                    }
                    this.isCampCommitteeMember = true;  //set bool to true
                    CommitteeMember = new CampCommitteeMembers(super.getName(), super.getEmail(), super.getFaculty(), isCampCommitteeMember, chosenCamp
);   //designate camp to committee member
                    chosenCamp.addCampCommitteeMember(CommitteeMember); //add member to camp member list, auto update slots
                    enrolledCamps.add(chosenCamp);
                    System.out.println("Successfully registered for " + chosenCamp.getCampName() + " as camp committee member.");

				}
				
				
				else
					System.out.println("Invalid camp option.");
				
				break;
				
			}
			
			
	}
	
	public Camp getCampOption(int campOption) { //new method i created
		if(campOption >= 1 && campOption <= openCamps.size()) {
			return openCamps.get(campOption-1);
		}
		return null;
	}
	
	public void viewCampCommitteeSlots(Camp camp) {
		for(Camp opCamp: openCamps) {
			System.out.printf("Remaining Slots for " + opCamp.getCampName() + ": " + camp.getCampCommitteeSlots());
			//no getCampCommitteeSlots method
		}
	}
	
	public void submitEnquiry(Camp camp) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your enquiry for " + camp.getCampName() + ": ");
		String enquiry = scanner.nextLine();
		
		Enquiry newEnquiry = new Enquiry();
		newEnquiry.setEnquiry(enquiry);
		ownEnquiries.add(newEnquiry);
		System.out.println("Enquiry submitted successfully");
	}
	
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
		
	     if (choice > 0 && choice <= ownEnquiries.size() && !ownEnquiries.get(choice - 1).getProcessed()) {
	    	 Enquiry selectedEnquiry = ownEnquiries.get(choice - 1);
	    	 Scanner editScanner = new Scanner(System.in);
	         System.out.println("Enter the updated enquiry (enter 'done' when finished) :");
	         String editEnquiry = scanner.nextLine();
	         String input;
	         
	            selectedEnquiry.setEnquiry(editEnquiry);
	            System.out.println("Enquiry updated successfully:");
	           
	       }
	    
	     else {
	            System.out.println("Unsuccessful.");
	        }
	    }
		
	
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
	
	public void viewProfile() {//override from user class print out camp committee status
		super.viewProfile();
		System.out.println("Camp committee status: " + (isCampCommitteeMember ? "Yes" : "No"));
	}
	
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
	
	public void withdrawCamp(Camp camp) {//if is camp comm, cannot withdraw,else update withdrawncamps, enrolled camps, and attendee list in specified camp removeAttendee()
		if(isCampCommitteeMember) {
			System.out.println("Camp committee member are not allowed to withdraw from the camp");
			return;
		}
		
		withdrawnCamps.add(camp);
		enrolledCamps.remove(camp);
		camp.removeAttendee(this);
		
	}
	
	public boolean checkCampBlacklist(Camp camp) {
		return withdrawnCamps.contains(camp);
	}
	
    public Camp getRegisteredCampOption(int campOption) {   //this is a new method to return registeredCamp obj
        return enrolledCamps.get(campOption-1);
    }
    
    public CampCommitteeMembers getCommitteeMember() {
    	return this.CommitteeMember;
    }
    
    public boolean getIsCampCommitteeMember() {
    	return this.isCampCommitteeMember;
    }
	

}

