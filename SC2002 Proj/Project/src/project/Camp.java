package project;

import java.util.ArrayList;
import java.util.List;

public class Camp {

 private List<Student> attendee;
 private List<CampCommitteeMembers> member;
 private List<Enquiry> enquiries;
 private List<Suggestion> suggestions;
 private List<Suggestion> approvedSuggestions;
 private String campName;
 private List<Integer> campDates;
 private int registrationClosingDate;
 private String location;
 private int totalSlots;
 private int remainingSlots;
 private int campCommitteeSlots;
 private String campDescription;
 private String campStaffIC;
 private boolean visibility;
 private String faculty;
 // Constructor
 public Camp(String name, String faculty) {
     this.campStaffIC = name;
     this.faculty = faculty;
     this.attendee = new ArrayList<>();
     this.member = new ArrayList<>();
     this.enquiries = new ArrayList<>();
     this.suggestions = new ArrayList<>();
     this.campDates = new ArrayList<>();
 }
 // Methods to set and get camp info
 public void setCampName(String newname) {
     this.campName = newname;
 }
 // Add other methods to set and get camp info
 public List<Integer> getCampDates() {
     return campDates;
 }
 public void removeCampDate(int date) {
     campDates.remove(Integer.valueOf(date));
 }
 public void addCampDate(int date) {
     campDates.add(date);
 }
 public void addAttendee(Student newAttendee) {
     if(newAttendee == null) {
    	 System.out.println("Invalid attendee. Cannot add null");
    	 return;
     }
	 if (remainingSlots > 0) {            
         attendee.add(newAttendee);
         remainingSlots--;
         System.out.println("Attendee added successfully!");
         System.out.println("Remaining Slots: " + remainingSlots);
     } else {
         System.out.println("Sorry, the camp is already full. Cannot add more attendees.");
     }
 }

 public void removeAttendee(Student student) {
     if (attendee.contains(student)) {
         attendee.remove(student);
         remainingSlots++;
         System.out.println("Attendee removed successfully.");
     } else {
         System.out.println("Student is not registered as an attendee.");
     }
 }
 public void addEnquiry(Enquiry enquiry) {
     enquiries.add(enquiry);
     System.out.println("Enquiry added successfully.");
 }
 public void removeEnquiry(Enquiry enquiry) {
     if (enquiries.contains(enquiry)) {
         enquiries.remove(enquiry);
         System.out.println("Enquiry removed successfully.");
     } else {
         System.out.println("Enquiry not found in the list.");
     }
 }
 public void addSuggestion(Suggestion suggestion) {
     suggestions.add(suggestion);
     System.out.println("Suggestion added successfully.");
 }
 public void removeSuggestion(Suggestion suggestion) {
     if (suggestions.contains(suggestion)) {
         suggestions.remove(suggestion);
         System.out.println("Suggestion removed successfully.");
     } else {
         System.out.println("Suggestion not found in the list.");
     }
 }
 public void viewEnquiries() {
     System.out.println("Enquiries:");
     for (Enquiry enquiry : enquiries) {
         System.out.println(enquiry.toString());
     }
 }
 public void viewSuggestions() {
     System.out.println("Suggestions:");
     for (Suggestion suggestion : suggestions) {
         System.out.println(suggestion.toString());
     }
 }
 public List<Student> getAttendees() {
     return attendee;
 }
 public List<CampCommitteeMembers> getCampCommitteeMembers() {
     return member;
 }
 public void viewCampInfo() {
     System.out.println("Camp Information:");
     System.out.println("Name: " + campName);
     System.out.println("Dates: " + campDates);
     System.out.println("Registration Closing Date: " + registrationClosingDate);
     System.out.println("Location: " + location);
     System.out.println("Total Slots: " + totalSlots);
     System.out.println("Remaining Slots: " + remainingSlots);
     System.out.println("Camp Committee Slots: " + campCommitteeSlots);
     System.out.println("Description: " + campDescription);
     System.out.println("Staff IC: " + campStaffIC);
     System.out.println("Visibility: " + visibility);
     System.out.println("Faculty: " + faculty);
 }
 public void viewAttendeeList() {
     System.out.println("Attendee List:");
     for (Student attendee : attendee) {
         System.out.println(attendee.toString());
     }
 }
 public void viewCampCommitteeMemberList() {
     System.out.println("Camp Committee Members:");
     for (CampCommitteeMembers member : member) {
         System.out.println(member.toString());
     }
 }
 
 public boolean getVisibility() {
	 return this.visibility;
 }
 public void setVisibility(boolean visible) {
	 this.visibility = visible;
 }
 public void toggleVisibility() {
	 if(this.visibility==true) {
		 this.visibility = false;
	 }
	 else {
		 this.visibility = true;
	 }
 }
 
 public String getCampFaculty() {
	 return this.faculty;
 }
 
 public String getCampName() {
	 return this.campName;
 }
 public int getRemainingSlots() {
	 return this.remainingSlots;
 }
 public int getRegistrationClosingDate() {
	 return this.registrationClosingDate;
 }
 public void setRemainingSlots(int n) {
	 this.remainingSlots = n;
 }
 public Enquiry getEnquiry(String enquiryName) {
	 for(Enquiry enquiry: enquiries){    //check if camp is within list of camps created
         if(enquiryName.toLowerCase().equals(enquiry.getEnquiry().toLowerCase())){
             return enquiry; }
     }
     return null;
 }
 
 public Suggestion getSuggestion(String sugName) {
	 for(Suggestion suggestion: suggestions){    //check if camp is within list of camps created
         if(sugName.toLowerCase().equals(suggestion.getSuggestion().toLowerCase())){
             return suggestion; }
     }
     return null;
 }
 public void addApprovedSuggestion(Suggestion suggestion) {
     approvedSuggestions.add(suggestion);
     System.out.println("Approved suggestion added successfully.");
 }
 
 public void setLocation(String location) {
	 this.location = location;
 }
 public String getLocation() {
	 return this.location;
 }

 public void setRegistrationClosingDate(int date) {
	 this.registrationClosingDate = date;
 }
 public void setTotalSlots(int slot) {
	 this.totalSlots = slot;
 }
 public int getTotalSlots() {
	 return this.totalSlots;
 }
 public int getCampCommitteeSlots() {
	 return this.campCommitteeSlots;
 }
 public void setCampCommitteeSlots(int slot) {
	 this.campCommitteeSlots = slot;
 }
 public void setDescription(String description) {
	 this.campDescription = description;
 }
 public String getDescription() {
	 return this.campDescription ;
 }
 public String getCampStaffIC() {
	 return this.campStaffIC;
 }
}

