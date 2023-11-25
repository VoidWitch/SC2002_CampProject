package proj3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class filterVisibility extends CampFilter{
  
    private List<Camp> filteredCamps = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public List<Camp> filter(List<Camp> list){
        filteredCamps.clear();
        System.out.printf("Filter by:\n" + 
                            "1. Visible to whole of NTU\n" +
                            "2. Not Visible to whole of NTU\n");
        int choice = sc.nextInt();
        switch(choice){
            case 1:
                for(Camp camp : list){
                    if(camp.getVisibility()==true){
                        filteredCamps.add(camp);
                    }
                }break;
            case 2:
                for(Camp camp : list){
                    if(camp.getVisibility()==false){
                        filteredCamps.add(camp);
                    }
                }break;
            default: break;
        }
        return filteredCamps;
    }
}