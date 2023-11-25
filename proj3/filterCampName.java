package proj3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class filterCampName extends CampFilter {
    private List<Camp> filteredCamps = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public List<Camp> filter(List<Camp> list){
        filteredCamps.clear();
        System.out.println("Input the camp name to filter by: ");
        String name = sc.nextLine();
        for(Camp camp : list){
            if(camp.getCampName().equalsIgnoreCase(name)){
                filteredCamps.add(camp);
            }
        }
        return filteredCamps;
    }
}