package project;

import java.util.*;

public class Suggestion {
	private Camp camp;
	private boolean suggestionProcessed;
	private String suggestion;
	
	public Suggestion(Camp camp, String suggestion) {
		this.camp = camp;
		this.suggestion = suggestion;
		this.suggestionProcessed = false;
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
	
	public void setProcessed() {
		this.suggestionProcessed = true;
	}
	public boolean isSuggestionProcessed() {
		return suggestionProcessed;
	}
}
