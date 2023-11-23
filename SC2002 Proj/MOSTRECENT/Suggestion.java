import java.util.*;

/**
 * The Suggestion class represents a suggestion made by a camp committee member for a specific camp.
 * It includes methods for viewing, editing, and processing suggestions.
 * @author Tey Cia Meng
 */
public class Suggestion {
    private Camp camp;
    private boolean approved;
    private boolean suggestionProcessed;
    private String suggestion;
    private CampCommitteeMembers member;
   
    /**
     * Constructor for creating a new suggestion.
     * @param camp            The camp associated with the suggestion.
     * @param suggestion      The content of the suggestion.
     * @param member          The committee member who made the suggestion.
     */
    public Suggestion(Camp camp, String suggestion, CampCommitteeMembers member) {
        this.camp = camp;
        this.member = member;
        this.suggestion = suggestion;
        this.suggestionProcessed = false;
        this.approved = false;
    }
   
    /**
     * View the content of the suggestion.
     * @param suggestion The suggestion to view.
     */
    public void viewSuggestion(String suggestion) {
        System.out.println("The suggestion is: " + suggestion);
    }

    /**
     * Get the camp associated with the suggestion.
     * @return The camp associated with the suggestion.
     */
    public Camp getCamp() {
        return camp;
    }
   
    /**
     * Set the content of the suggestion.
     * @param suggestion The new content of the suggestion.
     */
    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }
   
    /**
     * Get the content of the suggestion.
     * @return The content of the suggestion.
     */
    public String getSuggestion() {
        return suggestion;
    }
   
    /**
     * Set the processed status, approval status of the suggestion, and add points to the committee member.
     * @param approval {@code true} if the suggestion is approved, {@code false} otherwise.
     */
    public void setProcessed(boolean approval) {
        this.suggestionProcessed = true;
        this.approved = approval;
        this.member.addPoints(1);
    }

    /**
     * Check if the suggestion has been processed.
     * @return {@code true} if the suggestion has been processed, {@code true} otherwise.
     */
    public boolean isSuggestionProcessed() {
        return suggestionProcessed;
    }
   
    /**
     * Check if the suggestion has been approved.
     * @return {@code true} if the suggestion is approved, {@code true} otherwise.
     */
    public boolean isSuggestionApproved() {
        return this.approved;
    }

    /**
	 * Gets {@code CampCommitteeMembers} object who submitted this suggestion.
	 * @return The camp committee member who submitted the suggestion.
	 */
    public CampCommitteeMembers getMember() {
        return member;
    }
}





