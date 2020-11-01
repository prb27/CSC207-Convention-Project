import java.util.ArrayList;

public class Message {
    private String sender;
    private String recipient;
    private ArrayList<Message> replies;
    private String id;

    public Message(String sender, String recipient, String id){
        this.sender = sender;
        this.recipient = recipient;
        this.id = id;
    }

    public String getSender(){
        return sender;
    }

    public String getRecipient(){
        return recipient;
    }

    public ArrayList<Message> getReplies (){
        return replies;
    }

    public String getId(){
        return id;
    }
}
