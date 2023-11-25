package proj3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class filterSlots extends CampFilter{

    private List<Camp> filteredCamps = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public List<Camp> filter(List<Camp> list){
        filteredCamps.clear();
        System.out.printf("Filter by:\n" + 
                            "1. Total slots\n" +
                            "2. Camp committee slots\n");
        int choice = sc.nextInt();
        switch(choice){
            case 1:
                System.out.println("Input total number of slots to filter camps by: ");
                int total = sc.nextInt();
                for(Camp camp : list){
                    if(camp.getTotalSlots()==total){
                        filteredCamps.add(camp);
                    }
                }break;
            case 2:
                System.out.println("Input number of camp committee slots to filter camps by: ");
                int ccm = sc.nextInt();
                for(Camp camp : list){
                    if(camp.getTotalSlots()==ccm){
                        filteredCamps.add(camp);
                    }
                }break;
            default: break;
        }
        return filteredCamps;
    }
}