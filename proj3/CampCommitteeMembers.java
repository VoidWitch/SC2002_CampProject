package proj3;

import java.util.*;

/**
 * The {@code CampCommitteeMembers} class represents members of the camp committee in a camp management system.
 * It extends the functionality of the {@link Student} class and provides additional features specific to
 * committee members, such as submitting suggestions, viewing camp information, replying to camp enquiries,
 * managing suggestions, generating reports, and tracking points.
 * 
 * <p><b>Example Usage:</b></p>
 * <pre>{@code
 * //creating an instance of CampCommitteeMembers
 * Camp camp = new Camp("Camp X", "LocationX", 20230101, 20230110, 20221201, 50, 5, true);
 * CampCommitteeMembers committeeMember = new CampCommitteeMembers("John Doe", "john@example.com", "FacultyX", true, camp);
 * 
 * //submitting a suggestion
 * committeeMember.submitSuggestions(camp);
 * 
 * //viewing own suggestions
 * committeeMember.viewSuggestions();
 * 
 * //generating a report
 * committeeMember.generateReport();
 * }</pre>
 * 
 * @author Mu Hao, Tey Cia Meng
 */

public class CampCommitteeMembers extends Student{

    private Camp camp;
    private List<Suggestion> ownSuggestions;
    private int points;
    Scanner sc = new Scanner(System.in);

    /**
     * Constructs a new {@code CampCommitteeMembers} object.
     * Initializes committee member points to 0.
     * @param name The name of the committee member.
     * @param email The email of the committee member.
     * @param faculty The faculty of the committee member.
     * @param isCampCommitteeMember The boolean indicating whether the member is part of the camp committee.
     * @param camp The camp associated with the committee member.
     */
    public CampCommitteeMembers(String name, String email, String faculty, boolean isCampCommitteeMember, Camp camp)
    {
        super(name, email,faculty, isCampCommitteeMember);
        this.camp = camp;
        this.ownSuggestions = new ArrayList<>();
        this.points = 0;
    }

    /**
     * Submits a suggestion for the associated camp.
     * Updates the suggestion list of the camp and own list of suggestions submitted.
     * @param camp The camp for which the suggestion is submitted.
     */
    public void submitSuggestions(Camp camp) {
        System.out.println("What is your suggestion for Camp '" + camp.getCampName()+ "'?");
        String sug = sc.nextLine();
        Suggestion suggestion = new Suggestion(camp, sug, this);
        camp.addSuggestion(suggestion);
        this.addOwnSuggestion(suggestion);
        addPoints(1);
    }

    /**
     * Prints out relevant {@code Camp} object information.
     * @param camp The camp to view information.
     */
    public void viewCampInfo(Camp camp) {
        camp.viewCampInfo();
    }

    /**
     * Prints out list of enquiries specific to {@code Camp} object.
     * @param camp The camp to view enquiries.
     */
    public void viewEnquiries(Camp camp) {
        camp.viewEnquiries();
    }

    /**
     * Replies the enquiry within {@code Enquiry} object.
     * @param enq The enquiry to reply to.
     */
    public void replyCampEnquiries(Enquiry enq) {
        if (enq.getProcessed()) {
        	System.out.println("This enquiry has been replied.");
        }
        else {
        	System.out.println("Your reply to this enquiry:");
            String userInputString =sc.nextLine();
            enq.replyEnquiry(userInputString);
            addPoints(1);
            System.out.println("Your reply has been saved.");
        }
    	
    }

    /**
     * Prints out list of own suggestions with indexing.
     */
    public void viewSuggestions() {
    	System.out.println("");
    	System.out.println("Own Suggestions:");
        int i = 1;
        for (Suggestion suggestion : ownSuggestions) {
            System.out.println("[" + i + "] " + suggestion.getSuggestion());
            i++;
        }
    }
   
    /**
     * Gets the {@code Suggestion} object by accessing its index from list of own suggestions.
     * @param suggestionnumber The suggestion index for own {@code Suggestion} object.
     * @return The {@code Suggestion} object at suggestion index.
     */
    public Suggestion getSuggestionObj(int suggestionnumber) {
        return ownSuggestions.get(suggestionnumber -1);
    }

    /**
     * Sets suggestion for own {@code Suggestion} object if suggestion yet to be processed.
     * @param suggestions The suggestion class to edit suggestion.
     */
    public void editSuggestion(Suggestion suggestions) {
        Scanner scanner = new Scanner(System.in);
        //if suggestion is processed cannot edit alrdy
        if(suggestions.isSuggestionProcessed()){
            System.out.println("\nSuggestion processed! Not open to editing.");
        }
        else {
            System.out.println("\nType in your edited suggestion:");
            String userInputString = scanner.nextLine();
            //ownSuggestions.setSuggestion(userInputString);
            suggestions.setSuggestion(userInputString);
            System.out.println("\nSuggestion has been updated.");
        }
    }

    /**
     * Removes {@code Suggestion} object from a camp and removing points added to committee member.
     * @param camp The camp to remove the suggestion from.
     * @param suggestion The suggestion to be removed.
     */
    public void deleteSuggestion(Camp camp, Suggestion suggestion) {
        camp.removeSuggestion(suggestion);
        this.removeOwnSuggestion(suggestion);
        addPoints(-1);
    }

    /**
     * Adds a {@code Suggestion} object to the list of own suggestions.
     * @param suggestion The suggestion to be added.
     */
    public void addOwnSuggestion(Suggestion suggestion) {
        ownSuggestions.add(suggestion);
        System.out.println("\nSuggestion added successfully.");
    }

    /**
     * Removes a {@code Suggestion} object from the list of own suggestions.
     * @param suggestion The suggestion to be removed.
     */
    public void removeOwnSuggestion(Suggestion suggestion) {
        if (ownSuggestions.contains(suggestion)) {
            ownSuggestions.remove(suggestion);
            System.out.println("\nSuggestion removed successfully.");
        } else {
            System.out.println("Suggestion not found in the list.");
        }
    }

    /**
     * Generates a report in either CSV or TXT format based on user preferences.
     * The report can be for attendees or camp committee members.
     */
    public void generateReport() {
        System.out.println("Which format of report would you like?");
        System.out.println("[1] CSV");
        System.out.println("[2] TXT");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if(choice == 1)
        {
            GenerateTXT txtGenerator = new GenerateTXT();
            System.out.println("Which report would you like?");
            System.out.println("[1] Attendee");
            System.out.println("[2] Camp Committee Member");
            choice = scanner.nextInt();
            if(choice == 1)
            {
                txtGenerator.generate(camp);
            }
            else if(choice == 2)
            {
                txtGenerator.generateCampCommittee(camp);
            }
        }
        else if(choice == 2)
        {
            GenerateCSV csvGenerator = new GenerateCSV();
            System.out.println("Which report would you like?");
            System.out.println("[1] Attendee");
            System.out.println("[2] Camp Committee Member");
            choice = scanner.nextInt();
            if(choice == 1)
            {
                csvGenerator.generateAttendee(camp);
            }
            else if(choice == 2)
            {
                csvGenerator.generateCampCommittee(camp);
            }
        }
    }

    /**
     * Add points to camp committee member.
     * @param added The number of points added.
     */
    public void addPoints(int added) {
        this.points += added;
    }

    /**
     * Get the points awareded to the camp committee member.
     * @return The number of points awarded to the camp committee member.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Gets the name of the camp committee member.
     * @return The name of camp committee member
     */
    public String getName() {
        return super.getName();
    }
    
    public Camp getCamp() {
    	return camp;
    }
    
    public List<Suggestion> getOwnSuggestions(){
    	return this.ownSuggestions;
    }
}








