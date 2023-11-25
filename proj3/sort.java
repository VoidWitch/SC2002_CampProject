package proj3;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class sort {

    public static List<Camp> sortcamps(List<Camp> list){
        Collections.sort(list, Comparator.comparing(Camp::getCampName));
        return list;
    }
}
