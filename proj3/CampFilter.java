package proj3;

import java.util.List;
import java.util.Scanner;

public abstract class CampFilter implements Filter {

    private List<Camp> filteredcamps;

    public abstract List<Camp> filter(List<Camp> list);//implementing interface fn

    //this is to filter all camps and provide filter options then, call filter methods
    public List<Camp> filterCamps(List<Camp> list){
        Scanner sc = new Scanner(System.in);
        System.out.printf("Filter options:\n" + 
                            "1. Camp Name\n" + 
                            "2. Camp Dates\n" + //will provide start, end, registration date filter options
                            "3. Camp Visibility Status\n" +
                            "4. Camp location\n" + 
                            "5. Camp Slots\n" + //will provide total/committee slot option
                            "6. Staff IC\n" + 
                            "7. Attendee\n" +
                            "8. Committee Member\n" + 
                            "9. Faculty\n");
        int choice = sc.nextInt();
        switch (choice){
            case 1: 
                CampFilter fname = new filterCampName();
                this.filteredcamps = fname.filter(list); break;
            case 2:
                CampFilter fdate = new filterCampDates();
                this.filteredcamps =  fdate.filter(list); break;
            case 3:
                CampFilter vs = new filterVisibility();
                this.filteredcamps =  vs.filter(list); break;
            case 4:
                CampFilter loc = new filterLocation();
                this.filteredcamps =  loc.filter(list); break;
            case 5:
                CampFilter slots = new filterVisibility();
                this.filteredcamps =  slots.filter(list); break;
            case 6:
                CampFilter IC = new filterStaffIC();
                this.filteredcamps =  IC.filter(list); break;
            case 7:
                CampFilter a = new filterAttendee();
                this.filteredcamps =  a.filter(list); break;
            case 8:
                CampFilter ccm = new filterCampCommittee();
                this.filteredcamps =  ccm.filter(list); break;
            default: break;
        }
        return this.filteredcamps;
    }
}
