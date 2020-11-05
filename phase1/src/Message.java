import java.time.LocalDateTime;
import java.util.ArrayList;


public class Message {
    private String sender;
    private ArrayList<String> recipients = new ArrayList<>();
    private String content;

    private ArrayList<Message> replies = new ArrayList<>();
    private String id;
    private LocalDateTime time;

    public Message(String sender, String recipient, String content, String id){
        this.sender = sender;
        this.recipients.add(recipient);
        this.content = content;
        this.id = id;
        this.time = LocalDateTime.now();
    }

    public Message(String sender, ArrayList<String> recipients, String content, String id){
        this.sender = sender;
        this.recipients.addAll(recipients);
        this.content = content;
        this.id = id;
        this.time = LocalDateTime.now();
    }

    public String getSender(){ return sender;
    }

    public ArrayList<String> getRecipients(){ return recipients; }

    public String getContent(){ return content; }

    public ArrayList<Message> getReplies (){
        return replies;
    }

    public String getId(){ return id; }

    public LocalDateTime getTime(){ return time; }
}
