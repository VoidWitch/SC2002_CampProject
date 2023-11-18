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
		//can use printCamp in allCamp??
		for(Camp camp : openCamps){
           System.out.println(camp.getCampName()); }
		
		
	}
	
	public void addDates(List<Integer> newDate) {
		for(Integer date: newDate) {
			dates.add(date);
		}
		
	}
	
	public boolean checkDateClash(List<Integer> campDates) { //check if enrolled camp clashes, if no clash return true, continue with reg
		for(Camp enrolledCamp: enrolledCamps) {
			List<Integer> enrolledCampDates = enrolledCamp.getCampDates();
			for(Integer date: enrolledCampDates) {
				if(campDates.contains(date)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void viewRemainingSlots(Camp camp) {
		
		for(Camp opCamp: openCamps) {
			System.out.printf("Remaining Slots for " + opCamp.getCampName() + ": " + camp.getRemainingSlots());
			//no getRemainingSlots method
		}
		
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
				
				if(selectedCamp.getRemainingSlots() == 0) {
					System.out.println("No more slots. Registration unsuccessful.");
					return;
				}
				
				if (selectedCamp.getRegistrationClosingDate() < System.currentTimeMillis()){
					System.out.println("Registration date over. ");
					return;
				}
				
				
				if(selectedCamp != null) {
						if(!checkDateClash(selectedCamp.getCampDates()) && !checkCampBlacklist(selectedCamp)) {
							addDates(selectedCamp.getCampDates());
							selectedCamp.addAttendee(this); //??? do i add
							enrolledCamps.add(selectedCamp);
							int currentSlots = camp.getRemainingSlots(); //get and set not in UML
							camp.setRemainingSlots(currentSlots-1);
							System.out.println("Successfully registered for " + selectedCamp.getCampName() + " as an attendee.");
						}
						else
							System.out.println("Date clash! Registration failed. ");
					
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
				
				if(chosenCamp.getRemainingSlots() == 0) {
					System.out.println("No more slots. Registration unsuccessful.");
					return;
				}
				
				if (chosenCamp.getRegistrationClosingDate() < System.currentTimeMillis()){
					System.out.println("Registration date over. ");
					return;
				}
				if(chosenCamp != null) {
					if(!checkDateClash(chosenCamp.getCampDates())&& !checkCampBlacklist(chosenCamp)) {
					addDates(chosenCamp.getCampDates());
					chosenCamp.addAttendee(this); //??? do i add
					enrolledCamps.add(chosenCamp);
					this.isCampCommitteeMember = true;
					int currentSlots = camp.getCampCommitteeSlots(); //not in UML
					camp.setCampCommitteeSlots(currentSlots-1);
					System.out.println("Successfully registered for " + chosenCamp.getCampName() + " as camp committee member.");
					}
					else
						System.out.println("Date clash! Registration unsuccessfully.");
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
	
	public void editEnquiry(Enquiry enquiry) {
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
	
	public static void main(String[] args) {
		List <Camp> allcamp = allCamp.getAllCamps();
		Student student = (Student) User.loggedInUser;
		Scanner sc = new Scanner(System.in);
		int choice;
		
		while(true) {
            System.out.println("\n--- Student Menu ---");
            System.out.println("1. View Open Camps");
            System.out.println("2. Register for a Camp");
            System.out.println("3. View Registered Camps");
            System.out.println("4. Submit Enquiry");
            System.out.println("5. View Enquiries");
            System.out.println("6. Withdraw from a Camp");
            System.out.println("7. Change password");
            System.out.println("8. Log Out");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            
            try {
            	choice = sc.nextInt();
            	
            	switch(choice) {
            	case 1:
            		student.viewOpenCamps();
            		break;
            	case 2:
            		student.viewOpenCamps();
            		System.out.println("Enter the camp you want to register: ");
            		String campName = sc.nextLine();
            		Camp campToRegister = allCamp.getCamp(campName);
            		student.registerCamp(campToRegister);
            		break;
            	case 3:
            		student.viewRegisteredCamps();
            		break;
            	case 4:
            		System.out.println("Enter the camp you want to enquire: ");
            		campName = sc.nextLine();
            		Camp campToEnquire = allCamp.getCamp(campName);
            		student.submitEnquiry(campToEnquire);
            		break;
            	case 5:
            		student.viewEnquiry();
            		break;
            	case 6:
            		System.out.println("Enter the camp you want to withdraw: ");
            		campName = sc.nextLine();
            		Camp campToWithdraw = allCamp.getCamp(campName);
            		student.withdrawCamp(campToWithdraw);
            		break;
            	case 7:
            		User.changePassword(student);
            		break;
            	case 8:
            		User.loggedInUser = null;
            		User.main(null);
            		return;
            	case 9:
            		System.out.println("Exiting...");
            		System.exit(0);
            		break;
            	default:
            		System.out.println("Invalid choice. Try again.");
            }
		}catch (NumberFormatException e) {
			System.out.println("Please enter a valid number.");
			}
		}
	}
}

