package proj3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class filterStaffIC extends CampFilter {
    
    private List<Camp> filteredCamps = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public List<Camp> filter(List<Camp> list){
        filteredCamps.clear();
        System.out.println("Input the name of camp staff IC to filter by: ");
        String IC = sc.nextLine();
        for(Camp camp : list){
            if(camp.getCampStaffIC().equalsIgnoreCase(IC)){
                filteredCamps.add(camp);
            }
        }
        return filteredCamps;
    }
}