package proj3;

import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;

/**
 * The {@code allCamp} class represents a collection of camps in a camp management system.
 * It provides functionality to manage and interact with the list of camps, including adding,
 * deleting, and sorting camps based on various criteria.
 * 
 * <p><b>Usage Example:</b></p>
 * <pre>{@code
 * //creating camp instances
 * Camp camp1 = new Camp("Camp A", "LocationA", 20230101, 20230110, 20221201, 50, 5, true);
 * Camp camp2 = new Camp("Camp B", "LocationB", 20230201, 20230210, 20221215, 30, 3, false);
 * 
 * //adding camps
 * allCamp.addCamp(camp1);
 * allCamp.addCamp(camp2);
 * 
 * //sorting camps and printing them
 * System.out.println("\nSorted by Camp Name:");
 * allCamp.sortAllCamps();
 * allCamp.printCamps();
 * }</pre>
 * 
 * @author Gao Linyue
 */
public class allCamp {

    /** The static list containing all camps. */
    private static List<Camp> allcamp = new ArrayList<>();     //array of Camps
   
    /**
     * Default constructor for the {@code allCamp} class.
     */
    public allCamp(){}    //default constructor

    /**
     * Prints the names of all camps in the collection.
     * Used for viewing all camps.
     */
    public static void printCamps(){
        for(Camp camp : allcamp){
            System.out.printf(camp.getCampName() + "\n"); }
    }

    /**
     * Adds a new {@code Camp} object to the collection of all camps.
     * @param camp The camp to be added.
     */
    public static void addCamp(Camp camp){ allcamp.add(camp); }    //update whenever new camp is created

    /**
     * Deletes an existing {@code Camp} object from the collection of all camps.
     * @param camp The camp to be deleted.
     */
    public static void deleteCamp(Camp camp){ allcamp.remove(camp); }  //update whenever existing camp is removed

    /**
     * Gets the list of all camps in the collection.
     * @return The list of all camps.
     */
    public static List<Camp> getAllCamps(){ return allcamp; }
   
    /**
     * Gets a specific {@code Camp} object from the collection based on its name.
     * @param campName The name of the camp to retrieve.
     * @return The camp object, or null if not found.
     */
    public static Camp getCamp(String campName){   //ask for camp name
        for(Camp camp: allcamp){    //check if camp is within list of camps created
            if(campName.toLowerCase().equals(camp.getCampName().toLowerCase())){
                return camp; }
        }
        return null;
    }
    public static void setAllCamps(List<Camp> allCamp) {
    	allcamp = allCamp;
    }
}










