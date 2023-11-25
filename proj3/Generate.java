package proj3;


/**
 * The {@code Generate} interface defines methods for generating various components related to a camp.
 * Implementing classes should provide concrete implementations for these methods.
 * @author Gao Linyue
 */ 


import java.util.List;

public interface Generate {
    public abstract void generate(List<Camp> list);
    public abstract void generatePerformance(Camp camp);
}
