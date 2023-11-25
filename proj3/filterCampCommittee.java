package proj3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class filterCampCommittee extends CampFilter{
    private List<Camp> filteredCamps = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public List<Camp> filter(List<Camp> list){
        filteredCamps.clear();
        System.out.println("Input the name of camp committee member to filter by: ");
        String ccm = sc.nextLine();
        for(Camp camp : list){
            for(CampCommitteeMembers c : camp.getCampCommitteeMembers()){
                if(c.getName().equalsIgnoreCase(ccm)){
                    filteredCamps.add(camp); break;
                }
            }
        }
        return filteredCamps;
    }
}