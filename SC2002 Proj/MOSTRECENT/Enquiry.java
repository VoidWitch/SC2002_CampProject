/**
 * The {@code Enquiry} class represents an enquiry with an optional reply.
 * It tracks the content of the enquiry, any reply associated with it, and whether the enquiry has been processed.
 * Enquiries can be set, replied to, and viewed.
 *
 * <p> The class includes methods for setting the inquiry, replying to the inquiry,
 * viewing the enquiry and its reply, and retrieving the enquiry content.
 *
 * <p>An enquiry is considered processed once it has been replied to by either staff or committee members.
 *
 * <p> Example usage:
 * <pre>
 * Enquiry inquiry = new Enquiry();
 * inquiry.setEnquiry("Can I get more information about the upcoming event?");
 * inquiry.replyEnquiry("Sure! Here are the details you requested.");
 * inquiry.viewEnquiry();
 * </pre>
 * 
 * @author Gao Linyue
 */
public class Enquiry {
    private String enquiry = null;    //sentence; array of Str
    private String reply = null;
    private boolean processed = false;

    /**
     * Constructs a new {@code Enquiry} object with default values.
     * The enquiry content, reply, and processed status are initially set to {@code null}, {@code null}, and {@code false} respectively.
     */
    public Enquiry(){}  //default constructor
    public void setEnquiry(String enquiry){ this.enquiry = enquiry; }

    /**
     * Replies to the content of the enquiry and set processed as {@code true}.
     * @param reply The reply to the enquiry.
     */
    public void replyEnquiry(String reply){
        this.reply = reply;
        processed = true;   //enquiry processed once replied by either staff or committee member
    }

    /**
     * Prints the content of the enquiry and any corresponding reply to the console.
     * If no reply is available, only the enquiry content is printed.
     */
    public void viewEnquiry(){  //view enquiry and any corresponding reply
        System.out.println("Enquiry : " + enquiry);
        System.out.println("Reply   : " + reply);
    }

    /**
     * Prints the content of the enquiry and any corresponding reply to the console.
     * If no reply is available, only the enquiry content is printed.
     */
    public String getEnquiry() {
        return this.enquiry;
    }
    
    /**
     * Checks if the enquiry has been processed.
     * @return {@code true} if the enquiry has been processed, {@code false} otherwise.
     */
    public boolean getProcessed(){ return this.processed; }
}




