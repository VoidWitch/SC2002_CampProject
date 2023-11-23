/**
 * The {@code Generate} interface defines methods for generating various components related to a camp.
 * Implementing classes should provide concrete implementations for these methods.
 * @author Gao Linyue
 */ 
public interface Generate {
    /**
     * Generates attendee-related components for the specified camp.
     *
     * @param camp The camp for which attendee components are generated.
     */
    public abstract void generateAttendee(Camp camp);

    /**
     * Generates camp committee-related components for the specified camp.
     *
     * @param camp The camp for which camp committee components are generated.
     */
    public abstract void generateCampCommittee(Camp camp);

    /**
     * Generates components without applying any specific filter for the specified camp.
     *
     * @param camp The camp for which components without filter are generated.
     */
    public abstract void generateNoFilter(Camp camp);

    /**
     * Generates performance-related components for the specified camp.
     *
     * @param camp The camp for which performance components are generated.
     */
    public abstract void generatePerformance(Camp camp);
}
