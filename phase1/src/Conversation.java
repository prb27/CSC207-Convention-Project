import java.util.ArrayList;

public class Conversation {
    private ArrayList<String> participants;
    private ArrayList<String> messages;
    private String id;

    private String generateID(){
        long timestamp = System.currentTimeMillis();
        return "c" + timestamp;
    }

    public Conversation(ArrayList<String> participants, ArrayList<String> messages){
        this.participants = participants;
        this.messages = messages;
        id = generateID();
    }

    public ArrayList<String> getParticipants(){ return participants; }

    public ArrayList<String> getMessages(){ return messages; }

    public String getId(){ return id; }
}
