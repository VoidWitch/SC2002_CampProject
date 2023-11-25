package proj3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class filterFaculty extends CampFilter{

    private List<Camp> filteredCamps = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public List<Camp> filter(List<Camp> list){
        filteredCamps.clear();
        System.out.println("Input the faculty to filter by: ");
        String faculty = sc.nextLine();
        for(Camp camp : list){
            if(camp.getCampFaculty().equalsIgnoreCase(faculty)){
                filteredCamps.add(camp);
            }
        }
        return filteredCamps;
    }
}