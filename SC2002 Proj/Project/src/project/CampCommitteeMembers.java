package project;

import java.util.*;

public class CampCommitteeMembers extends Student{

    private Camp camp;
    private List<Suggestion> ownSuggestions;
    private int points;
    Scanner sc = new Scanner(System.in);

    public CampCommitteeMembers(String name, String email, String faculty, boolean isCampCommitteeMember)
    {
        super(name, email,faculty, isCampCommitteeMember);
        this.camp = camp;
        this.ownSuggestions = new ArrayList<>();
        this.points = 0;
    }


    public void submitSuggestions(Camp camp) {
        System.out.println("What is your suggestion for this camp?");
        String sug = sc.nextLine();
        Suggestion suggestion = new Suggestion(camp, sug);
    	camp.addSuggestion(suggestion);
        this.addOwnSuggestion(suggestion);
        addPoints(1);
    }


    public void viewCampInfo(Camp camp) {
        camp.viewCampInfo();
    }


    public void viewEnquiries(Camp camp) {
        camp.viewEnquiries();
    }


    public void replyCampEnquiries(Enquiry enq) {
        System.out.println("Do type down your reply:");
        
        String userInputString = sc.nextLine();
        enq.replyEnquiry(userInputString);
        addPoints(1);
    }


    public void viewSuggestions() {
        System.out.println("Own Suggestions:");
        int i = 1;
        for (Suggestion suggestion : ownSuggestions) {
            System.out.println(i + ": " + suggestion.getSuggestion());
            i++;
        }
    }


    public void editSuggestion(Suggestion suggestions) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type in your edited suggestion:");
        String userInputString = scanner.nextLine();
        //ownSuggestions.setSuggestion(userInputString);
        suggestions.setSuggestion(userInputString);
    }


    public void deleteSuggestion(Camp camp, Suggestion suggestion) {
        camp.removeSuggestion(suggestion);
        this.removeOwnSuggestion(suggestion);
        addPoints(-1);
    }
    public void addOwnSuggestion(Suggestion suggestion) {
        ownSuggestions.add(suggestion);
        System.out.println("Suggestion added successfully.");
    }
    public void removeOwnSuggestion(Suggestion suggestion) {
        if (ownSuggestions.contains(suggestion)) {
            ownSuggestions.remove(suggestion);
            System.out.println("Suggestion removed successfully.");
        } else {
            System.out.println("Suggestion not found in the list.");
        }
    }


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
                txtGenerator.generateAttendee(camp);
            }
            else if(choice == 2)
            {
                txtGenerator.generateCampCommiteeMember(camp);
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


    public void addPoints(int added) {
        this.points += added;
    }


    public int getPoints() {
        return points;
    }


    public String getName() {
        return super.getName();
    }
}

