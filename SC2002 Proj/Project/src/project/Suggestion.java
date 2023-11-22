package project;

import java.util.*;

public class Suggestion {
	private Camp camp;
	private boolean approved;
	private boolean suggestionProcessed;
	private String suggestion;
	
	public Suggestion(Camp camp, String suggestion) {
		this.camp = camp;
		this.suggestion = suggestion;
		this.suggestionProcessed = false;
		this.approved = false;
	}
	
	public void viewSuggestion(String suggestion) {
		System.out.println("The suggestion is: " + suggestion);
	}
	
	public String editSuggestion(String suggestion) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter new suggestion: ");
		String newSuggestion = sc.nextLine();
		return newSuggestion;
	}
	
	public Camp getCamp() {
		return camp;
	}
	
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	
	public String getSuggestion() {
		return suggestion;
	}
	
	public void setProcessed(boolean approval) {
		this.suggestionProcessed = true;
		this.approved = approval;
	}
	public boolean isSuggestionProcessed() {
		return suggestionProcessed;
	}
	
	public boolean isSuggestionApproved() {
		return this.approved;
	}
}
