package project;

import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;

public class allCamp {

    private static List<Camp> allcamp = new ArrayList<>();     //array of Camps
   
    public allCamp(){}    //default constructor


    /*iterate thru all camps, print out camp name -> to view all camps
     * when staff or committee member want to view camp, they have to type in the camp name and will
       be redirected to another page with all info specific to camp chosen
     *give option to edit/quit
    */
    public static void printCamps(){
        for(Camp camp : allcamp){
            System.out.printf(camp.getCampName() + "\n"); }
    }


    public static void addCamp(Camp camp){ allcamp.add(camp); }    //update whenever new camp is created


    public static void deleteCamp(Camp camp){ allcamp.remove(camp); }  //update whenever existing camp is removed


    public static List<Camp> getAllCamps(){ return allcamp; }
   
    public static Camp getCamp(String campName){   //ask for camp name
        for(Camp camp: allcamp){    //check if camp is within list of camps created
            if(campName.toLowerCase().equals(camp.getCampName().toLowerCase())){
                return camp; }
        }
        return null;
    }
    
    public static void sortAllCamps(){
        int choice = 0;
        do {
            Scanner sc = new Scanner(System.in);                                     //ADD NEW
            System.out.println("Display Camps (Filter By): ");
            System.out.println("1. Camp Name (A-Z)");     //a-z
            System.out.println("2. Starting Date (earliest -> latest)");     //earliest - latest
            System.out.println("3. Registration Closing Date (earliest -> latest)");     //earliest - latest
            System.out.println("4. Visibility (Open to faculty -> Open to NTY)");    //open to sch only - open to NTU
            System.out.println("5. Location (A-Z)");    //a-z
            System.out.println("6. Total Slots (least -> most)");   //smallest - largest
            System.out.println("7. Camp Committee Slots (least -> most)");  //smallest - largest
            try{
                choice = sc.nextInt();
                switch(choice){
                    case 1:
                        Collections.sort(allcamp, Comparator.comparing(Camp::getCampName)); break;
                    case 2:
                        Collections.sort(allcamp, Comparator.comparingInt(Camp::getCampStartDate));
                    case 3:
                        Collections.sort(allcamp, Comparator.comparingInt(Camp::getRegistrationClosingDate));
                    case 4:
                        Collections.sort(allcamp, Comparator.comparing(Camp::getVisibility).reversed());
                    case 5:
                        Collections.sort(allcamp, Comparator.comparing(Camp::getLocation)); break;
                    case 6:
                        Collections.sort(allcamp, Comparator.comparingInt(Camp::getTotalSlots));
                    case 7:
                        Collections.sort(allcamp, Comparator.comparingInt(Camp::getCampCommitteeSlots));
                    default: System.out.println("Invalid choice. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number."); continue;
            }
        } while (choice<1 || choice >7);
    }

}



