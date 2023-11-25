package proj3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class filterAttendee extends CampFilter{
 
    private List<Camp> filteredCamps = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public List<Camp> filter(List<Camp> list){
        filteredCamps.clear();
        System.out.println("Input the name of camp attendee to filter by: ");
        String attendee = sc.nextLine();
        for(Camp camp : list){
            for(Student s : camp.getAttendees()){
                if(s.getName().equalsIgnoreCase(attendee)){
                    filteredCamps.add(camp); break;
                }
            }
        }
        return filteredCamps;
    }
}