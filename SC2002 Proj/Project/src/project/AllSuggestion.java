package project;

import java.util.*;

public class AllSuggestion {
	private List<Suggestion> suggestionList;
	
	public AllSuggestion() {
		this.suggestionList = new ArrayList<>();
	}
	
	public void showAllSuggestions() {
		if(suggestionList.isEmpty()) {
			System.out.println("No suggestions available.");
		}
		else {
			for(Suggestion suggestion : suggestionList) {
				System.out.println("Camp: " + suggestion.getCamp());
				System.out.println("Suggestion: " + suggestion.getSuggestion());
				System.out.println("Processed: " + suggestion.isSuggestionProcessed());
			}
		}
	}
}
