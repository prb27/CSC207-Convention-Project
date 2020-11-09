import java.time.LocalDateTime;
import java.util.ArrayList;


public class Message {
    private String sender; //the id of the User who sent
    private ArrayList<String> recipients = new ArrayList<>(); //list of Ids of Users who received messages
    private String content; //the actual content of the message

    private String id;
    private LocalDateTime time;

    private int convoNumber;

    private String generateID(){
        long timestamp = System.currentTimeMillis();
        return "m" + timestamp;
    }

    public Message(String sender, String recipient, String content, int convoNumber){
        this.sender = sender;
        this.recipients.add(recipient);
        this.content = content;
        id = generateID();
        this.time = LocalDateTime.now();
        this.convoNumber = convoNumber;

    }

    public Message(String sender, ArrayList<String> recipients, String content, int convoNumber){
        this.sender = sender;
        this.recipients.addAll(recipients);
        this.content = content;
        id = generateID();
        this.time = LocalDateTime.now();
        this.convoNumber = convoNumber;
    }

    public String getSender(){ return sender;
    }

    public ArrayList<String> getRecipients(){ return recipients; }

    public String getContent(){ return content; }

    public String getId(){ return id; }

    public LocalDateTime getTime(){ return time; }

    public int getConvoNumber(){ return convoNumber; }

}
