package project;

public class Enquiry {

    private String enquiry = null;    //sentence; array of Str
    private String reply = null;
    private boolean processed = false;


    public Enquiry(){}  //default constructor
    public void setEnquiry(String enquiry){ this.enquiry = enquiry; }


    public void replyEnquiry(String reply){
        this.reply = reply;
        processed = true;   //enquiry processed once replied by either staff or committee member
    }


    public void viewEnquiry(){  //view enquiry and any corresponding reply
        System.out.println("Enquiry : " + enquiry);
        System.out.println("Reply   : " + reply);
    }
    public String getEnquiry() {
    	return this.enquiry;
    }


    public boolean getProcessed(){ return this.processed; }
}
