package project;

import java.util.*;
import java.io.*;

public class User {
	private String userID;
	private String name;
	private String email;
	private String password;
	private String faculty;
	private boolean isStaff;
	
	protected static List<User> users = new ArrayList<>();
    protected static User loggedInUser = null;
	
	public User(String name, String email, String faculty, boolean isStaff) {
		this.userID = email.split("@")[0];
		this.name = name;
		this.email = email;
		this.password = "password";
		this.faculty = faculty;
		this.isStaff = isStaff;
	}
	
	public static boolean login() {
		System.out.println("LOG IN PAGE");
		System.out.println("Please enter your UserID & Password.");
		System.out.println("You only have 3 attempts.");
		
			int attempt=0;
			while(attempt<3) {
				System.out.println("Enter your UserID: ");
				String enteredUserID = AppCAMS.sc.nextLine();
				System.out.println("Enter your password: ");
				String enteredPassword = AppCAMS.sc.nextLine();
				boolean isLoginSuccessful = false;
				for(User user : users) {
					if(user.userID.equals(enteredUserID) && user.password.equals(enteredPassword)) {
						if(enteredPassword.equals("password")) {
							System.out.println("Your current password is by default. Please change your password as soon as possible.");
						}
						loggedInUser = user;
						return true;
					
					}
				}
				System.out.println("Wrong credentials! You have " + (2-attempt) + " attempts left.");
				attempt++;
					
				}
			
			System.out.println("You have exceeded number of attempts.");
			return false;
			
	}
	
	public void viewProfile() {
		System.out.println("Name: " + this.name);
		System.out.println("UserID: " + this.userID);
		System.out.println("Email: " + this.email);
		System.out.println("Faculty: " + this.faculty);
	}
	
	public static void changePassword(User user) {
		
			String oldPassword;
			do {
				System.out.println("Enter your old password (Enter <Quit> to exit): ");
				oldPassword = AppCAMS.sc.nextLine();
				if(user.password.equals(oldPassword)) {
					System.out.println("Enter your new password: ");
					String newPassword = AppCAMS.sc.nextLine();
					System.out.println("Confirm your new password: ");
					String newPassword2 = AppCAMS.sc.nextLine();
					if (newPassword2.equals(newPassword)) {
						user.password = newPassword;
						System.out.println("Password successfully changed.");
						return;
					}
					else if("Quit".equals(oldPassword)) {
						return;
					}
					else {
						System.out.println("The new password does not match.");
					}
					
				}
				else {
					System.out.println("The password you entered is incorrect.");
				}
			}while(true);
		

	}
	
	public String getName() {
		return this.name;
	}
	
	public String getFaculty() {
		return this.faculty;
	}
	
	public boolean getIsStaff() {
		return this.isStaff;
	}
	
	public String getEmail() {
		return this.email;
	}
	/*
	public static void main(String[] args) {
        // Assuming student_list.txt and staff_list.txt are in the current directory.
        try {
            BufferedReader studentReader = new BufferedReader(new FileReader("student_list.txt"));
            BufferedReader staffReader = new BufferedReader(new FileReader("staff_list.txt"));

            String line;
            while ((line = studentReader.readLine()) != null) {
                String[] parts = line.split(",");
                User student = new User(parts[0], parts[1], parts[2],true);
                users.add(student);
            }

            while ((line = staffReader.readLine()) != null) {
                String[] parts = line.split(",");
                User user = new User(parts[0], parts[1], parts[2],false);
                Staff staff = new Staff(parts[0], parts[1], parts[2]);
                users.add(staff);
            }

            studentReader.close();
            staffReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("");
        	System.out.println("Welcome to CAMS!");
            System.out.println("1. Log in");
            System.out.println("2. Log Out");
            System.out.println("3. Exit");
            System.out.println("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Logging in...");
                    if (User.login()) {
                        System.out.println("Login Successful.");
                        if(loggedInUser.isStaff) {
                        	Staff.main(null);
                        }
                        else {
                        	Student.main(null);
                        }
                        }
                    else {
                    	System.out.println("Login Failed.");
                        }
             
                    break;


                case 2:
                    System.out.println("Logging out...");
                    loggedInUser = null;
                    break;
                  
                case 3:
                	System.out.println("Exiting...");
                	System.exit(0);

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
    */
}
