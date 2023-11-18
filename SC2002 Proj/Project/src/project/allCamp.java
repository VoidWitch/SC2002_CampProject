package project;

import java.util.ArrayList;
import java.util.List;

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
}



