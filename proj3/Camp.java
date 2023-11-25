package proj3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Camp} class represents a camp with participants comprising of attendees and committee members.
 * It holds camp-specific enquiries, suggestions, and other relevant camp details.
 * It allows the managing of camp details, attendees, and committee members, and the handling of enquiries and suggestions.
 *
 * <p>Example usage:
 * <pre>
 * Camp myCamp = new Camp("Summer Camp", 20230101, 20231231, 20221201, "Camp Location", 100, 5, "Description", "Staff Name", true, "Faculty");
 * myCamp.addAttendee(new Student("John Doe", "123456789"));
 * myCamp.viewCampInfo();
 * </pre>
 * 
 * @author Zixin, Tey Cia Meng
 */

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
    private boolean open;
    private String location;
    private int totalSlots; //inclusive of committee slots
    private int participantSlots;
    private int numberOfParticipants;
    private int campCommitteeSlots; // max 10
    private int numberOfCommitteeMembers;
    private String campDescription;
    private String campStaffIC;
    private boolean visibility;
    private String faculty;

    /**
     * Constructs a new {@code Camp} with specified details.
     * @param campName                  Name of the camp.
     * @param startDate                 Start date of the camp.
     * @param endDate                   End date of the camp.
     * @param registrationClosingDate   Closing date for camp registration, after which no signups are valid.
     * @param open                      Open to whole NTU or specific faculty only
     * @param location                  Location of the camp.
     * @param totalSlots                Total number of available slots, inclusive of committee slots.
     * @param campCommitteeSlots        Number of committee slots (max 10).
     * @param campDescription           A description of the camp.
     * @param name                      Name of the staff-in-charge of the camp created.
     * @param visibility                Visibility status of the camp to whole NTU, with false being visible only to faculty.
     * @param faculty                   Faculty associated with the camp.
     */
    public Camp(String campName, int startDate, int endDate, int registrationClosingDate, boolean open, String location, int participantSlots, int campCommitteeSlots, String campDescription
            , String name, boolean visibility, String faculty) {
        this.campName = campName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.registrationClosingDate = registrationClosingDate;
        this.open = open;
        this.location = location;
        this.participantSlots = participantSlots;
        this.campCommitteeSlots = campCommitteeSlots;
        this.totalSlots = participantSlots + campCommitteeSlots;
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
        this.approvedSuggestions = new ArrayList<>();
        
    }

    /**
     * Sets the name of the camp.
     * @param newname The new name of the camp.
     */
    public void setCampName(String newname) {
        this.campName = newname;
    }
    
    /**
     * Gets the start date of the camp.
     * @return The start date of the camp.
     */
    public int getCampStartDate() {
        return startDate;
    }

    /**
     * Gets the end date of the camp.
     * @return The end date of the camp.
     */
    public int getCampEndDate() {
        return endDate;
    }

    /**
     * Adds a new {@code Student} object, attendee(role) to the camp after checking for slot availability.
     * Updates attendee list and remaining slots upon successful attendee registration for the camp.
     * @param newAttendee The attendee to be added.
     */
    public void addAttendee(Student newAttendee) {
        if(newAttendee == null) {
            System.out.println("Invalid attendee. Cannot add null");
            return;
        }
        if (this.getRemainingParticipantSlots() > 0) {            
            attendee.add(newAttendee);
            numberOfParticipants++;
            System.out.println("\nAttendee added successfully!");
            System.out.println("Remaining Participant Slots: " + this.getRemainingParticipantSlots());
        } else {
            System.out.println("Sorry, the camp is already full. Cannot add more attendees.");
        }
    }

    /**
     * Removes existing {@code Student} object, attendee(role) from the camp.
     * Updates attendee list and remaining slots upon successful withdrawal from the camp.
     * @param student The student/attendee to be removed.
     */
    public void removeAttendee(Student student) {
        if (attendee.contains(student)) {
            attendee.remove(student);
            numberOfParticipants--;
            System.out.println("Attendee removed successfully.");
            System.out.println("Remaining Participant Slots: " + this.getRemainingParticipantSlots());
        } else {
            System.out.println("Student is not registered as an attendee.");
        }
    }

    /**
     * Adds a new {@code Enquiry} object, to the list of enquiries specific to the camp.
     * @param enquiry The enquiry to be added.
     */
    public void addEnquiry(Enquiry enquiry) {
        enquiries.add(enquiry);
        //System.out.println("Enquiry added successfully.");
    }

    /**
     * Removes existing {@code Enquiry} object, from the list of enquiries specific to the camp.
     * @param enquiry The enquiry to be removed.
     */
    public void removeEnquiry(Enquiry enquiry) {
        if (enquiries.contains(enquiry)) {
            enquiries.remove(enquiry);
            System.out.println("Enquiry removed successfully.");
        } else {
            System.out.println("Enquiry not found in the list.");
        }
    }

    /**
     * Adds a new {@code Suggestion} object, to the list of suggestions specific to the camp.
     * @param suggestion The suggestion to be added.
     */
    public void addSuggestion(Suggestion suggestion) {
        suggestions.add(suggestion);
        // System.out.println("Suggestion added successfully.");
    }

    /**
     * Removes existing {@code Suggestion} object, from the list of suggestions specific to the camp.
     * @param suggestion The suggestion to be removed.
     */
    public void removeSuggestion(Suggestion suggestion) {
        if (suggestions.contains(suggestion)) {
            suggestions.remove(suggestion);
            //System.out.println("Suggestion removed successfully.");
        } else {
            System.out.println("Suggestion not found in the list.");
        }
    }

    /**
     * Prints all existing enquiries and their corresponding replies regarding the camp with indexing.
     */
    public void viewEnquiries() {   //edited implementation
        if(enquiries.isEmpty()) {
        	System.out.println("No enquiries found.");
        	return;
        }
        else{
            System.out.println("Enquiries:");
            int i = 1;
            for (Enquiry enquiry : enquiries) {
                if(enquiry.getProcessed()) {
                	System.out.println("[" + i + "] "+ enquiry.getEnquiry() + " (replied)");
                }
                else {
                	System.out.println("[" + i + "] "+ enquiry.getEnquiry() + " (unprocessed)");
                }
            	
                i++;
            }
        }
    }
    
    /**
     * Gets the {@code Enquiry} object by accessing its index from list of enquiries for the camp.
     * @param enquirynumber The enquiry index for {@code Enquiry} object.
     * @return The {@code Enquiry} object at enquiry index.
     */
    public Enquiry getEnquiryObj(int enquirynumber){    //new method
        int index = enquirynumber - 1;
        return enquiries.get(index);
    }
    
    /**
     * Prints all existing suggestions regarding the camp with indexing.
     */
    public void viewSuggestions() {     //edited implementation
        if (suggestions.isEmpty()) {
        	System.out.println("No suggestions found.");
        	return;
        }
        else{
            System.out.println("Suggestions:");
            int i = 1;
            for (Suggestion suggestion : suggestions) {
                if(suggestion.isSuggestionProcessed()) {
                	if(suggestion.isSuggestionApproved()) {
                		System.out.println("[" +i+ "] " + suggestion.getSuggestion()+ " (approved)");
                	}
                	else {
                		System.out.println("[" +i+ "] " + suggestion.getSuggestion()+ " (disapproved)");
                	}
                }
                else {
                	System.out.println("[" +i+ "] " + suggestion.getSuggestion()+ " (unprocessed)");
                }
                i++;
            }
        }
        System.out.println("");
    }

    /**
     * Gets the {@code Suggestion} object by accessing its index from list of suggestions for the camp.
     * @param suggestionnumber The suggestion index for {@code Suggestion} object.
     * @return The {@code Suggestion} object at suggestion index.
     */
    public Suggestion getSuggestionObj(int suggestionnumber) {  //new method
        int index = suggestionnumber - 1;
        return suggestions.get(index);
    }

    /**
     * Gets list of attendees for the camp.
     * @return The list of {@code Student} objects registered as attendees for the camp.
     */
    public List<Student> getAttendees() {
        return attendee;
    }

    /**
     * Gets list of camp committee members for the camp.
     * @return The list of {@code CampCommitteeMembers} objects registered as committee members for the camp.
     */
    public List<CampCommitteeMembers> getCampCommitteeMembers() {
        return member;
    }

    /**
     * Prints all camp information visible to everyone.
     */
    public void viewCampInfo() {
        System.out.println("Camp Information:");
        System.out.println("Name: " + campName);
        System.out.println("Start Date: " + getDateInString(startDate));
        System.out.println("End Date: " + getDateInString(endDate));
        System.out.println("Registration Closing Date: " + getDateInString(registrationClosingDate));
        if(this.open) {
        	System.out.println("Open to: Whole NTU");
        }
        else {
        	System.out.println("Open to: "+ faculty +" only");
        }
        System.out.println("Location: " + location);
        System.out.println("Total Slots: " + getTotalSlots());
        System.out.println("Participant Slots: " + participantSlots);
        System.out.println("Number of Participants: " + numberOfParticipants);
        System.out.println("Camp Committee Slots: " + campCommitteeSlots);
        System.out.println("Number of Committee Members: " + numberOfCommitteeMembers);
        System.out.println("Description: " + campDescription);
        System.out.println("Staff IC: " + campStaffIC);
        if(this.visibility) {
        	System.out.println("Visibility: Visible to student");
        }
        else {
        	System.out.println("Visibility: Not visible to student");
        }
        System.out.println("Faculty: " + faculty);
    }

    /**
     * Prints list of attendees in the camp.
     */
    public void viewAttendeeList(){     //added new method
        if(attendee.isEmpty()) System.out.println("No attendees.");
        else{
            System.out.println("Attendee List:");
            for (Student attendee : attendee){
                System.out.println(attendee.getName());
            }
        }
    }

    /**
     * Checks if camp is visible to whole of NTU or to faculty only.
     * @return {@code true} if the camp is visibile to whole of NTU, {@code false} otherwise.
     */
    public boolean getVisibility() {
        return this.visibility;
    }

    /**
     * Sets camp visibility
     * @param visible visibility of camp to whole of NTU
     */
    public void setVisibility(boolean visible) {
        this.visibility = visible;
    }
    
    /**
     * Gets faculty associated with the camp.
     * @return The faculty associated with the camp.
     */
    public String getCampFaculty() {
        return this.faculty;
    }
    
    /**
     * Gets camp name.
     * @return The name of the camp.
     */
    public String getCampName() {
        return this.campName;
    }

    /**
     * Gets remaining open slots, which is the total slots less existing participants(attendees) and camp committee members.
     * @return The number of open slots for the camp.
     */
    public int getRemainingSlots() {
        return this.totalSlots - (this.numberOfParticipants + this.campCommitteeSlots);
    }

    /**
     * Gets registration closing date for camp.
     * @return The camp registration closing date
     */
    public int getRegistrationClosingDate() {
        return this.registrationClosingDate;
    }

    /**
     * Gets number of attendees for the camp, not inclusive of camp committee members.
     * @return The number of attendees.
     */
    public int numberOfParticipants() {     //new method
        return this.numberOfParticipants;
    }

    /**
     * Gets the number of committee members in the camp.
     * @return The number of camp committee members.
     */
    public int getnumberOfCommitteeMembers() {  //new method
        return this.numberOfCommitteeMembers;
    }

    /**
     * Gets the remaining slots for camp committee members, which is the total camp committee slots less existing committee members.
     * @return The number of committee members.
     */
    public int getRemainingCommitteeSlots() {   //new method
        return this.campCommitteeSlots - this.numberOfCommitteeMembers;
    }

    /**
     * Adds approved {@code Suggestion} object to the list of approved suggestions for the camp.
     * @param suggestion The suggestion that has been approved.
     */
    public void addApprovedSuggestion(Suggestion suggestion) {
        approvedSuggestions.add(suggestion);
        System.out.println("Approved suggestion added successfully.");
    }
    
    /**
     * Set location of the camp.
     * @param location The new location of the camp.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the location of the camp.
     * @return The location of the camp.
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * Sets the closing date for camp registration.
     * @param date The deadline for camp registration, after which registrations are no longer valid.
     */
    public void setRegistrationClosingDate(int date) {
        this.registrationClosingDate = date;
    }

    /**
     * Sets total slots available for the camp, inclusive of attendees and camp committee members.
     * @param slot The total number of people that can register for the camp.
     */
    public void setTotalSlots(int slot) {
        this.totalSlots = slot;
    }

    /**
     * Gets the total slots for the camp.
     * @return The total number of people that can register for the camp.
     */
    public int getTotalSlots() {
        return this.totalSlots;
    }

    /**
     * Gets the number of committee member slots for the camp.
     * @return The number of committee member slots for the camp.
     */
    public int getCampCommitteeSlots() {
        return this.campCommitteeSlots;
    }

    /**
     * Sets the total number of committee members that can register for the camp.
     * @param slot The number of committee members that can register for the camp.
     */
    public void setCampCommitteeSlots(int slot) {
        this.campCommitteeSlots = slot;
    }

    /**
     * Sets the description for the camp.
     * @param description The camp description.
     */
    public void setDescription(String description) {
        this.campDescription = description;
    }

    /**
     * Gets the description for the camp.
     * @return The camp description.
     */
    public String getDescription() {
        return this.campDescription ;
    }

    /**
     * Gets the name of the staff who created and is in charge of the camp.
     * @return The name of staff in charge of the camp.
     */
    public String getCampStaffIC() {
        return this.campStaffIC;
    }

    /**
     * Sets the duration window for the camp.
     * @param startDate The starting date for the camp.
     * @param endDate The ending date for the camp.
     */
    public void setCampDates(int startDate, int endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    /**
     * Adds new {@code CampCommitteeMembers} object to list of committee members for the camp.
     * Updates existing number of committee members.
     * @param newCommitteeMember The committee member to be added to the camp.
     */
    public void addCampCommitteeMember(CampCommitteeMembers newCommitteeMember) {       //added new method
        if(newCommitteeMember == null) {
            System.out.println("Invalid attendee. Cannot add null");
            return;
        }
        if (this.getRemainingCommitteeSlots() > 0) {            
        	member.add(newCommitteeMember);
            numberOfCommitteeMembers++;
            //remainingSlots--;
            System.out.println("\nCommittee member added successfully!");
            System.out.println("Remaining Committee Slots: " + this.getRemainingCommitteeSlots());
        } else {
            System.out.println("Sorry, the camp is already full. Cannot add more attendees.");
        }
    	
    	
        
    }

    /**
     * Prints list of committee members in the camp.
     */
    public void viewCampCommitteeMemberList() {
        if(member.isEmpty()) System.out.println("No committee members.");
        else{
            System.out.println("Camp committee members:");
            for (CampCommitteeMembers committeemember : member){
                System.out.println(committeemember.getName());
            }
        }
    }
    
    public int getParticipantSlots() {
    	return participantSlots;
    }
    
    public void setParticipantSlots(int slot) {
    	this.participantSlots = slot;
    }
    
    public boolean getOpen() {
    	return open;
    }
    
    public String getDateInString(int date) {
    	String dateString = Integer.toString(date);

        DateTimeFormatter sourceFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter targetFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate Date = LocalDate.parse(dateString, sourceFormat);
        String StringDate = Date.format(targetFormat);
        return StringDate;
    }
    
    public List<Enquiry> getEnquiry() {
    	return this.enquiries;
    }
    
    public List<Suggestion> getSuggestion(){
    	return this.suggestions;
    }
    
    public int getRemainingParticipantSlots() {
        return (this.participantSlots - this.numberOfParticipants) ;
    }
}

