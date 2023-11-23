import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *<h1>AppCAMS</h1>
 * The AppCAMS program is the implementation for a Camp Application and Management System(CAMS). 
 * CAMs is an application for staff and students to manage, view and register for camps within NTU.
 * The application will act as a centralized hub for all staff and students. 
 * It provides functionality for user authentication and navigation through different menus
 * based on the user's role (staff or student).
 * <p>
 * 
 * @author Tey Cia Meng
 */
public class AppCAMS {
    protected static List<User> users = new ArrayList<>();
    protected static User loggedInUser = null;
    protected static Scanner sc = new Scanner(System.in) ;

    /**
     * The main method is the entry point of the CAMS application.
     * It handles user authentication and navigation through different menus.
     * @param args The command-line arguments.
     */   
    public static void main(String[] args) {
        loadUsers(); // Load users from text files
        //Scanner sc = new Scanner(System.in);
        //sc.useDelimiter(System.lineSeparator());
        while (true) {
            System.out.println("\n--- Welcome to CAMS ---");
            System.out.println("1. Log in");
            System.out.println("2. Exit");
            System.out.println("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline
              switch (choice) {
                case 1:
                    System.out.println("Logging in...");
                    if (User.login()) {
                        System.out.println("Login Successful.");
                        if (User.loggedInUser.getIsStaff()) {
                            staffMenu(loggedInUser);
                        } else {
                            studentMenu(loggedInUser);
                        }
                    } else {
                        System.out.println("Login Failed.");
                    }
                    break;

                case 2:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    /**
     * The staffMenu method provides menu options and functionality for a staff member.
     * It includes options to view profile, created, edit, delete camps, viewing of created and all camps, and log out.
     * @param loggedInUser The currently logged-in user.
     */ 
    public static void staffMenu(User loggedInUser) {
        Staff staff = (Staff) User.loggedInUser;
        //Scanner sc = new Scanner(System.in);
        int choice;
        String campName;
        while(true) {
            System.out.println("\n--- Staff Menu ---");
            System.out.println("[1] View Profile");
            System.out.println("[2] View Create Camp");
            System.out.println("[3] View All Camps");
            System.out.println("[4] Log Out");
            System.out.println("Enter your choice: ");
       
            choice = sc.nextInt();
            sc.nextLine();
        
            switch(choice) {
            case 1:
                boolean vProfile = true;
                while (vProfile) {
                    staff.viewProfile();
                    System.out.println("\n--- Profile Menu---");
                    System.out.println("[1] Change Password");
                    System.out.println("[2] Return to Staff Menu");
                    System.out.println("Enter your choice: ");
                    int choiceProfile = sc.nextInt();
                    sc.nextLine();
                   
                    switch(choiceProfile) {
                    case 1:
                        User.changePassword(staff);
                        break;
                    case 2:
                        vProfile = false;
                        break;
                    default:
                        System.out.println("Invalid Input.");
                    }      
                }
                break;
            case 2:
                boolean vCreatedCamp = true;
                while(vCreatedCamp) {
                    staff.viewCreatedCamps();
                    System.out.println("\n-- Created Camp Menu --");
                    System.out.println("[1] Create Camp");
                    System.out.println("[2] Delete Camp");
                    System.out.println("[3] View Specific Camp");
                    System.out.println("[4] Return to Staff Menu");
                    System.out.println("Enter your choice: ");
                    int choiceCCamps = sc.nextInt();
                    sc.nextLine();
                    switch(choiceCCamps) {
                    case 1:
                        staff.createCamp();
                        break;
                    case 2:
                        if(staff.getCampsCreated().isEmpty()) {
                            System.out.println("You have not created any camp.");
                        }
                        else {
                            staff.viewCreatedCamps();
                            System.out.println("Enter the name of the camp you want to delete: ");
                            campName = sc.nextLine();
                            Camp campToDelete = staff.getCreatedCamp(campName);
                            if(campToDelete!=null) {
                                staff.deleteCamp(campToDelete);
                                System.out.println("Camp " + campName + " is deleted successfully.");
                            }
                            else {
                                System.out.println("Camp not found. No camp is deleted.");
                            }
                        }
                        break;
                 
                    case 3:
                        if(staff.getCampsCreated().isEmpty()) {
                            System.out.println("You have not created any camp.");
                        }
                        else {
                            staff.viewCreatedCamps();
                            System.out.println("Enter the camp you want to view: ");
                            campName = sc.nextLine();
                            Camp campToView = allCamp.getCamp(campName);
                            if(campToView != null) {
                                boolean vCamp = true;
                                while(vCamp) {
                                    System.out.println("\n----- Camp Menu -----");
                                    System.out.println("Camp: " + campToView.getCampName());
                                    System.out.println("[1] View Camp Info");
                                    System.out.println("[2] Edit Camp");
                                    System.out.println("[3] View Attendee List" );
                                    System.out.println("[4] View Committee Members List " );
                                    System.out.println("[5] View Enquiries"  );
                                    System.out.println("[6] View Suggestions  " );
                                    System.out.println("[7] Generate CSV Report  " );
                                    System.out.println("[8] Generate TXT Report  " );
                                    System.out.println("[9] Return to Created Camp Menu");
                                    System.out.println("Enter your choice: ");
                                    int choiceCamp = sc.nextInt();
                                    sc.nextLine();
                                    switch(choiceCamp) {
                                    case 1:
                                        staff.viewCamp(campToView);
                                        break;
                                    case 2:
                                        staff.editCamp(campToView);
                                        break;
                                    case 3:
                                        campToView.viewAttendeeList();
                                        break;
                                    case 4:
                                        campToView.viewCampCommitteeMemberList();
                                        break;
                                    case 5:
                                        campToView.viewEnquiries();
                                        System.out.println("Do you want to reply to any enquiries? (Y/N)");
                                        String replyEnq = sc.nextLine();
                                        do {
                                            if(replyEnq.equals("Y")) {
                                                System.out.println("Enter the enquiry you want to reply: ");
                                                int enquiryName = sc.nextInt();
                                                Enquiry enquiryToReply = campToView.getEnquiryObj(enquiryName);
                                                if(enquiryToReply != null) {
                                                    System.out.println(enquiryToReply.getEnquiry());
                                                    System.out.println("Enter your reply for this enquiry: ");
                                                    String reply = sc.nextLine();
                                                    enquiryToReply.replyEnquiry(reply);
                                                    enquiryToReply.viewEnquiry();
                                                    break;
                                                }
                                                else {
                                                    System.out.println("There is no such enquiry.");
                                                }
                                            }
                                            else if(replyEnq.equals("N")) {
                                                break;
                                            }
                                            else {
                                                System.out.println("Invalid input. Please enter 'Y' or 'N'. ");
                                                replyEnq = sc.nextLine();
                                            }
                                        }while(!replyEnq.equals("Y") && !replyEnq.equals("N"));
                                        break;
                                    case 6:
                                        campToView.viewSuggestions();
                                        System.out.println("Do you want to process to any sugesstions? (Y/N)");
                                        String procSug = sc.nextLine();
                                        do {
                                            if(procSug.equals("Y")) {
                                                System.out.println("Enter the suggestion you want to process: ");
                                                int sugInt = sc.nextInt();
                                                sc.nextLine();
                                                Suggestion sugToProcessed = campToView.getSuggestionObj(sugInt);
                                                if(sugToProcessed != null) {
                                                    System.out.println(sugToProcessed.getSuggestion());
                                                    System.out.println("Do you want to approve this suggestion? (Y/N) ");
                                                    String reply = sc.nextLine();
                                                    do {
                                                        if(reply == "Y") {
                                                            sugToProcessed.setProcessed(true);
                                                            campToView.addApprovedSuggestion(sugToProcessed);
                                                            System.out.println("The suggestion is approved.");
                                                            break;
                                                        }
                                                        else if(reply == "N") {
                                                            sugToProcessed.setProcessed(false);
                                                            System.out.println("The suggestion is not approved.");
                                                            break;
                                                        }
                                                        break;
                                                    }while(!reply.equals("Y") && !reply.equals("N"));
                                                }
                                                else if(procSug.equals("N")) {
                                                    break;
                                                }
                                            }
                                            else {
                                                System.out.println("Invalid input. Please enter 'Y' or 'N'. ");
                                                procSug = sc.nextLine();
                                            }
                                        }while(!procSug.equals("Y") && !procSug.equals("N"));
                                        break;
                                    case 7:
                                        staff.generateCSV(campToView);
                                        System.out.println("CVS file for Camp " + campName + " is generated successfully.");
                                        break;
                                    case 8:
                                        staff.generateTXT(campToView);
                                        System.out.println("TXT file for Camp " + campName + " is generated successfully.");
                                        break;
                                    case 9:
                                        vCamp = false;
                                        break;
                                    default:
                                        System.out.println("Invalid input");
                                    }
                                }
                            }
                            else {
                                System.out.println("No such camp.");
                            }
                        }
                        break;
                    case 4:
                        vCreatedCamp = false;
                        break;
                    default:
                        System.out.println("Invalid Input.");
                       
                    }
                }  
                break;
            case 3:
                boolean vAllCamp = true;
                while(vAllCamp) {
                    if(allCamp.getAllCamps()!=null) {
                        staff.viewAllCamps();
                        System.out.println("\n-- All Camps Menu --");
                        System.out.println("[1] View Specific Camp");
                        System.out.println("[2] Return to Staff Menu");
                        System.out.println("Enter your choice: ");
                        int choiceAllCamp = sc.nextInt();
                        sc.nextLine();
                        switch(choiceAllCamp) {
                        case 1:
                            System.out.println("Enter the camp you want to view: ");
                            campName = sc.nextLine();
                            Camp campToView = allCamp.getCamp(campName);
                            if(campToView != null) {
                                staff.viewCamp(campToView);
                            }
                            else {
                                System.out.println("No such camp.");
                            }
                            break;
                        case 2:
                            vAllCamp = false;
                            break;
                        default:
                            System.out.println("Invalid Input.");
                        }
                    }
                    else {
                        System.out.println("No camp has been created yet.");
                    }
                }
                break;

            case 4:
                User.loggedInUser = null;
                AppCAMS.main(null);
                return;

            default:
                System.out.println("Invalid choice. Try again");
            }
        }
    }
   
    /**
     * The loadUsers method reads user information from text files and creates User objects.
     * It populates the 'users' list with Student or Staff objects.
     */
    private static void loadUsers() {
        // Add the file reading and user creation logic from User class's main method here
        try {
            BufferedReader studentReader = new BufferedReader(new FileReader("student_list.txt"));
            BufferedReader staffReader = new BufferedReader(new FileReader("staff_list.txt"));

            String line;
            while ((line = studentReader.readLine()) != null) {
                String[] parts = line.split(",");
                User user = new User(parts[0], parts[1], parts[2], false);
                Student student = new Student(parts[0], parts[1], parts[2],false);
                User.users.add(student);
            }

            while ((line = staffReader.readLine()) != null) {
                String[] parts = line.split(",");
                User user = new User(parts[0], parts[1], parts[2], true);
                Staff staff = new Staff(parts[0], parts[1], parts[2]);
                User.users.add(staff);
            }

            studentReader.close();
            staffReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
    /**
     * The studentMenu method provides menu options and functionality for a student.
     * It includes options to view profile, register camps, view registered camps and open camps, and log out.
     * @param loggedInUser The currently logged-in user.
     */
    public static void studentMenu(User loggedInUser) {
        List <Camp> allcamp = allCamp.getAllCamps();
        Student student = (Student) User.loggedInUser;
        Scanner sc = new Scanner(System.in);
        int choice;
        int option=0;
        String campName;
       
        while(true){
            System.out.println("\n--- Student Menu ---");
            System.out.println("[1] View Profile");
            System.out.println("[2] View Registered Camps");
            System.out.println("[3] View Open Camps");
            System.out.println("[4] Log Out");
            System.out.println("Enter your choice: ");

            try {
                choice = sc.nextInt();
                sc.nextLine();
                switch(choice) {
                    case 1: //display user info with option to change pw
                        boolean vProfile = true;
                        while(vProfile){
                            student.viewProfile();
                            System.out.println("\n--- Profile Menu---");
                            System.out.println("[1] Change your password");
                            System.out.println("[2] Return to Student Menu");
                            System.out.printf("Enter your choice: ");
                            try {
                                int choiceProfile = sc.nextInt();
                               
                                switch(choiceProfile) {
                                case 1:
                                    User.changePassword(student);
                                    break;
                                case 2:
                                    vProfile = false;
                                    break;
                                default:
                                    System.out.println("Invalid Input.");
                                }
                               
                            }catch (NumberFormatException e) {
                                System.out.println("Please enter a valid number.");
                            }
                        }
                        break;

                    case 2: //view registered camps
                        boolean vRegisterCamp = true ;
                        while(vRegisterCamp){
                            System.out.println("\n--- Registered Camps ---");
                            student.viewRegisteredCamps();      //print list of registered camps with roles
                            System.out.println("\n --Register Camp Menu--");
                            System.out.println("[1] Withdraw from camp");
                            System.out.println("[2] Submit enquiries");
                            System.out.println("[3] View own enquiries");
                            System.out.println("[4] Edit own enquiries");
                            System.out.println("[5] Delete own enquiries");
                            System.out.println("[6] View camp enquiries");   //option to reply enquiries
                            System.out.println("[7] Submit suggestions");
                            System.out.println("[8] View own suggestions");  //option to edit/delete
                            System.out.println("[9] View camp information");
                            System.out.println("[10] Generate camp report (for Camp Comittee Only)");
                            System.out.println("[11] Return to Student menu");
                            System.out.printf("Enter your choice: ");
                            try {
                                option = sc.nextInt();
                                sc.nextLine();
                                switch(option){
                                    case 1:
                                        System.out.println("Enter the camp you want to withdraw: ");
                                        campName = sc.nextLine();
                                        Camp campToWithdraw = allCamp.getCamp(campName);
                                        student.withdrawCamp(campToWithdraw);
                                        break;
                                    case 2:
                                        System.out.println("Enter the camp you want to enquire: ");
                                        campName = sc.nextLine();
                                        Camp campToEnquire = allCamp.getCamp(campName);
                                        student.submitEnquiry(campToEnquire);
                                        break;
                                    case 3:
                                        student.viewEnquiry(); break;
                                    case 4:
                                        student.editEnquiry(); break;
                                    case 5:
                                        System.out.println("Enter the camp you want to delete an enquiry from: ");
                                        campName = sc.nextLine();
                                        Camp campToDeleteEnquiry = allCamp.getCamp(campName);
                                        student.deleteEnquiry(campToDeleteEnquiry);
                                        break;
                                    case 6:
                                        System.out.println("Enter the camp you want to view all enquiries: ");
                                        campName = sc.nextLine();
                                        Camp campToViewAllEnquiry = allCamp.getCamp(campName);
                                        //if the student is a committee member of chosen camp, he is allowed to view all enquiries
                                        if(campToViewAllEnquiry.getCampCommitteeMembers().contains(student.getCommitteeMember())){
                                            student.getCommitteeMember().viewEnquiries(campToViewAllEnquiry);
                                            System.out.printf("1. Reply enquiries");
                                            if(sc.nextInt()==1){
                                                System.out.println("Enter the enquiry to reply to: ");
                                                Enquiry enquiryToReply = campToViewAllEnquiry.getEnquiryObj(sc.nextInt());
                                                student.getCommitteeMember().replyCampEnquiries(enquiryToReply); //auto add points per reply
                                            }
                                        }
                                        else System.out.println("Function unavailable for non-committee members.");
                                        break;
                                    case 7:
                                        System.out.println("Enter the camp you want to submit suggestion: ");
                                        campName = sc.nextLine();
                                        Camp campToSubmitSuggestion = allCamp.getCamp(campName);
                                        //if the student is a committee member of chosen camp, he is allowed to submit suggestion
                                        if(campToSubmitSuggestion.getCampCommitteeMembers().contains(student.getCommitteeMember())){
                                            student.getCommitteeMember().submitSuggestions(campToSubmitSuggestion);  //auto updates points
                                        }
                                        else System.out.println("Function unavailable for non-committee members.");
                                        break;
                                    case 8: //if student is a committee member, they will have suggestions
                                        if(student.getIsCampCommitteeMember()){
                                            student.getCommitteeMember().viewSuggestions();
                                            System.out.printf("1. Edit suggestion\n2. Delete suggestions\n");
                                            int select = sc.nextInt();
                                            if(select==1){
                                                System.out.println("Enter the suggestion to edit: ");
                                                Suggestion suggestionToEdit = student.getCommitteeMember().getSuggestionObj(sc.nextInt());
                                                student.getCommitteeMember().editSuggestion(suggestionToEdit);
                                            }
                                            if(select==2){
                                                System.out.println("Enter the suggestion to delete: ");
                                                Suggestion suggestionToDelete = student.getCommitteeMember().getSuggestionObj(sc.nextInt());
                                                student.getCommitteeMember().deleteSuggestion(suggestionToDelete.getCamp(),suggestionToDelete);
                                            }
                                        }
                                        else System.out.println("Function unavailable for non-committee members.");
                                        break;
                                    case 9: //view camp info
                                        System.out.println("Enter the camp you want to view camp information: ");
                                        campName = sc.nextLine();
                                        Camp campToViewInfo = allCamp.getCamp(campName);
                                        campToViewInfo.viewCampInfo();
                                        break;
                                    case 10:    //generate camp report
                                        System.out.println("Enter the camp you want to generate a report of: ");
                                        campName = sc.nextLine();
                                        Camp campToGenerateReport = allCamp.getCamp(campName);
                                        //if the student is a committee member of chosen camp, he is allowed to generate report
                                        if(campToGenerateReport.getCampCommitteeMembers().contains(student.getCommitteeMember())){
                                            student.getCommitteeMember().generateReport();
                                        }
                                        else System.out.println("Function unavailable for non-committee members.");
                                        break;
                                    case 11: vRegisterCamp = false; break;
                                    default: System.out.println("Invalid choice. Try again.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Please enter a valid number."); continue;
                            }
                        }  
                        break;
                   
                    case 3: //view open camps
                        boolean vOpenCamp = true;
                        student.openCamps();
                        allCamp.sortAllCamps(); //allows student to filter open camps
                        while(vOpenCamp){
                           
                            System.out.println("\n--- Open Camps ---");
                            student.viewOpenCamps();      //print list of open camps
                            System.out.println("\n ---Open Camp Menu---");
                            System.out.println("[1] View remaining slots for camps");
                            System.out.println("[2] Register for camps");
                            System.out.println("[3] Return back to main menu");
                            System.out.printf("Enter your choice: ");
                            try{
                                int option2 = sc.nextInt();
                                sc.nextLine();
                                switch(option2){
                                    case 1:
                                        /*
                                        for(Camp camp : student.getOpenCamps()) {
                                            student.viewRemainingSlots(camp);
                                        }
                                        */
                                        student.viewRemainingSlots();
                                        break;
                                       
                                    case 2:
                                        //System.out.println("Enter the camp you want to register: ");
                                        //campName = sc.nextLine();
                                        //Camp campToRegister = allCamp.getCamp(campName);
                                        student.registerCamp();
                                        break;
                                    case 3: vOpenCamp = false; break;
                                    default: System.out.println("Invalid choice. Try again.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Please enter a valid number."); continue;
                            }
                        }
                        break;

                    case 4:
                        User.loggedInUser = null;
                        AppCAMS.main(null);
              
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
