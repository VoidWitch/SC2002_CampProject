package proj3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class filterLocation extends CampFilter{
    private List<Camp> filteredCamps = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public List<Camp> filter(List<Camp> list){
        filteredCamps.clear();
        System.out.println("Input the camp location to filter by: ");
        String location = sc.nextLine();
        for(Camp camp : list){
            if(camp.getLocation().equalsIgnoreCase(location)){
                filteredCamps.add(camp);
            }
        }
        return filteredCamps;
    }
}