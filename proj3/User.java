package proj3;

import java.util.*;
import java.io.*;

/**
 * The {@code User} class represents a user in the system.
 * It includes methods for user login, viewing profiles, and changing passwords.
 * @author Tey Cia Meng
 */

public class User {

    private String userID;
    private String name;
    private String email;
    private String password;
    private String faculty;
    private boolean isStaff;
   
    /** List of all users in the system */
    protected static List<User> users = new ArrayList<>();
    protected static User loggedInUser = null;
   
    /**
     * Constructs a new {@code User} object with the specified name, email, faculty, and staff status.
     * @param name     The name of the user.
     * @param email    The email of the user.
     * @param faculty  The faculty of the user.
     * @param isStaff  The staff status of the user.
     */
    public User(String name, String email, String faculty, boolean isStaff) {
        this.userID = email.split("@")[0];
        this.name = name;
        this.email = email;
        this.password = "password";
        this.faculty = faculty;
        this.isStaff = isStaff;
    }
   
    /**
     * Attempts to log in a user by validating the entered credentials.
     * @return {@code true} if the login is successful, {@code false} otherwise
     */
    public static boolean login() {
        System.out.println("LOG IN PAGE\n");
        System.out.println("Please enter your UserID & Password.");
        System.out.println("You only have 3 attempts.\n");
       
            int attempt=0;
            while(attempt<3) {
                System.out.println("Enter your UserID: ");
                String enteredUserID = AppCAMS.sc.nextLine();
                System.out.println("Enter your password: ");
                String enteredPassword = AppCAMS.sc.nextLine();
                //boolean isLoginSuccessful = false;
                for(User user : users) {
                    if(user.userID.equals(enteredUserID) && user.password.equals(enteredPassword)) {
                    	System.out.println("\nLogin Successful.");
                    	if(enteredPassword.equals("password")) {
                            System.out.println("Your current password is by default. Please change your password as soon as possible.");
                        }
                        loggedInUser = user;
                        return true;  
                    }
                }
                System.out.println("\nWrong credentials! You have " + (2-attempt) + " attempts left.\n");
                attempt++;      
                }  
            System.out.println("You have exceeded number of attempts.");
            return false;   
    }
   
    /**
     * Displays the profile information of the user.
     */
    public void viewProfile() {
        System.out.println("Name: " + this.name);
        System.out.println("UserID: " + this.userID);
        System.out.println("Email: " + this.email);
        System.out.println("Faculty: " + this.faculty);
    }
   
    /**
     * Allows the user to change their password.
     * @param user The user whose password is being changed.
     */
    public static void changePassword(User user) {
        String oldPassword;
        do {
            System.out.println("\nEnter your old password (Enter <Quit> to exit): ");
            oldPassword = AppCAMS.sc.nextLine();
            if("Quit".equals(oldPassword)) {
                return;
            }
            if(user.password.equals(oldPassword)) {
                System.out.println("Enter your new password: ");
                String newPassword = AppCAMS.sc.nextLine();
                System.out.println("Confirm your new password: ");
                String newPassword2 = AppCAMS.sc.nextLine();
                if (newPassword2.equals(newPassword)) {
                	for (User u : users) {
                        if (u.userID.equals(user.userID)) {
                            u.setPassword(newPassword);
                            break;
                        }
                    }
                    System.out.println("");
                    System.out.println("Password successfully changed.");
                    return;
                }
                else {
                	System.out.println("");
                	System.out.println("The new password does not match.\n");
                }            
            }
            else {
                System.out.println("\nThe password you entered is incorrect.\n");
            }
        }while(true);
    }
    
	/**
     * Gets the name of the user.
     * @return The name of the user.
     */
    public String getName() {
        return this.name;
    }
   
    /**
     * Gets the faculty of the user.
     * @return The faculty of the user.
     */
    public String getFaculty() {
        return this.faculty;
    }
   
    /**
     * Gets the staff status of the user.
     * @return {@code true} if the user is staff, {@code false} otherwise.
     */
    public boolean getIsStaff() {
        return this.isStaff;
    }
   
    /**
     * Gets the email of the user.
     * @return The email of the user.
     */
    public String getEmail() {
        return this.email;
    }
    
    public void setPassword(String password) {
    	this.password = password;
    }
}