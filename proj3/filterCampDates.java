package proj3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class filterCampDates extends CampFilter{
 
    private List<Camp> filteredCamps = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public List<Camp> filter(List<Camp> list){
        filteredCamps.clear();
        System.out.printf("Filter by:\n" +
                                "1. Starting Date\n" + 
                                "2. Ending Date\n" + 
                                "3. Registration Date\n");
        int choice = sc.nextInt();
        switch(choice){
            case 1:
                System.out.println("Input starting date to filter camps by: ");
                int start = sc.nextInt();
                for(Camp camp : list){
                    if(camp.getCampStartDate()==start){
                        filteredCamps.add(camp);
                    }
                }break;
            case 2:
                System.out.println("Input ending date to filter camps by: ");
                int end = sc.nextInt();
                for(Camp camp : list){
                    if(camp.getCampEndDate()==end){
                        filteredCamps.add(camp);
                    }
                }break;
            case 3:
                System.out.println("Input registration closing date to filter camps by: ");
                int reg = sc.nextInt();
                for(Camp camp : list){
                    if(camp.getRegistrationClosingDate()==reg){
                        filteredCamps.add(camp);
                    }
                }break;
            default: break;
        }
        return filteredCamps;
    }
}
