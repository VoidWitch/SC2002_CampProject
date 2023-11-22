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
 private int startDate;
 private int endDate;
 private int registrationClosingDate;
 private String location;
 private int totalSlots; //inclusive of committee slots
 private int numberOfParticipants;
 private int campCommitteeSlots; // max 10
 private int numberOfCommitteeMembers;
 private String campDescription;
 private String campStaffIC;
 private boolean visibility;
 private String faculty;
 // Constructor
 public Camp(String campName, int startDate, int endDate, int registrationClosingDate, String location, int totalSlots, int campCommitteeSlots, String campDescription
		 , String name, boolean visibility, String faculty) {
	 this.campName = campName;
	 this.startDate = startDate;
	 this.endDate = endDate;
	 this.registrationClosingDate = registrationClosingDate;
	 this.location = location;
	 this.totalSlots = totalSlots;
	 this.campCommitteeSlots = campCommitteeSlots;
	 this.campDescription = campDescription;
	 this.campStaffIC = name;
	 this.visibility = visibility;
     this.faculty = faculty;
     this.numberOfParticipants = 0;
     this.numberOfCommitteeMembers = 0;
     this.attendee = new ArrayList<>();
     this.member = new ArrayList<>();
     this.enquiries = new ArrayList<>();
     this.suggestions = new ArrayList<>();
     
 }
 // Methods to set and get camp info
 public void setCampName(String newname) {
     this.campName = newname;
 }
 // Add other methods to set and get camp info

 public int getCampStartDate() {
     return startDate;
 }
  public int getCampEndDate() {
     return endDate;
 }

 public void addAttendee(Student newAttendee) {
     if(newAttendee == null) {
    	 System.out.println("Invalid attendee. Cannot add null");
    	 return;
     }
	 if (this.getRemainingSlots() > 0) {            
         attendee.add(newAttendee);
         numberOfParticipants++;
         //remainingSlots--;
         System.out.println("Attendee added successfully!");
         System.out.println("Remaining Slots: " + this.getRemainingSlots());
     } else {
         System.out.println("Sorry, the camp is already full. Cannot add more attendees.");
     }
 }

 public void removeAttendee(Student student) {
     if (attendee.contains(student)) {
         attendee.remove(student);
         //remainingSlots++;
         numberOfParticipants--;
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
 public void viewEnquiries() {   //edited implementation
     if(enquiries.isEmpty()) System.out.println("No enquiries found.");
     else{
         System.out.println("Enquiries:");
         int i = 1;
         for (Enquiry enquiry : enquiries) {
             System.out.print(i + enquiry.getEnquiry());
             i++;
         }
     }
 }
 
 public Enquiry getEnquiryObj(int enquirynumber){    //new method
     int index = enquirynumber - 1;
     return enquiries.get(index);
 }


 public void viewSuggestions() {     //edited implementation
     if (suggestions.isEmpty()) System.out.println("No suggestions found.");
     else{
         System.out.println("Suggestions:");
         int i = 1;
         for (Suggestion suggestion : suggestions) {
             System.out.println(i + suggestion.getSuggestion());
             i++;
         }
     }


 }
 public Suggestion getSuggestionObj(int suggestionnumber) {  //new method
     int index = suggestionnumber - 1;
     return suggestions.get(index);
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
     System.out.println("Start Date: " + startDate);
     System.out.println("End Date: " + endDate);
     System.out.println("Registration Closing Date: " + registrationClosingDate);
     System.out.println("Location: " + location);
     System.out.println("Total Slots: " + totalSlots);
     System.out.println("Number of Participants: " + numberOfParticipants);
     System.out.println("Camp Committee Slots: " + campCommitteeSlots);
     System.out.println("Number of Committee Members: " + numberOfCommitteeMembers);
     System.out.println("Description: " + campDescription);
     System.out.println("Staff IC: " + campStaffIC);
     System.out.println("Visibility: " + visibility);
     System.out.println("Faculty: " + faculty);
 }
 public void viewAttendeeList(){     //added new method
     if(attendee.isEmpty()) System.out.println("No attendees.");
     else{
         System.out.println("Attendee List:");
         for (Student attendee : attendee){
             System.out.println(attendee.getName());
         }
     }
 }

 public boolean getVisibility() {
	 return this.visibility;
 }
 public void setVisibility(boolean visible) {
	 this.visibility = visible;
 }
 
 public String getCampFaculty() {
	 return this.faculty;
 }
 
 public String getCampName() {
	 return this.campName;
 }
 public int getRemainingSlots() {
	 return this.totalSlots - this.numberOfParticipants;
 }
 public int getRegistrationClosingDate() {
	 return this.registrationClosingDate;
 }
 public int numberOfParticipants() {     //new method
     return this.numberOfParticipants;
 }
 public int getnumberOfCommitteeMembers() {  //new method
     return this.numberOfCommitteeMembers;
 }
 public int getRemainingCommitteeSlots() {   //new method
     return this.campCommitteeSlots - this.numberOfCommitteeMembers;
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
 public void setCampDates(int startDate, int endDate) {
	 this.startDate = startDate;
	 this.endDate = endDate;
 }
 
 public void addCampCommitteeMember(CampCommitteeMembers newCommitteeMember) {       //added new method
     member.add(newCommitteeMember);
     numberOfCommitteeMembers++;
     System.out.println("Committee member added successfully!");
 }
 public void removeCampCommitteeMember(CampCommitteeMembers newCommitteeMember) {        //added new method
     member.remove(newCommitteeMember);
     numberOfCommitteeMembers--;
     System.out.println("Committee member removed successfully.");
 }
 public void viewCampCommitteeMemberList() {
     if(member.isEmpty()) System.out.println("No committee members.");
     else{
         System.out.print("Camp committee members:");
         for (CampCommitteeMembers committeemember : member){
             System.out.println(committeemember.getName());
         }
     }


 }

}

