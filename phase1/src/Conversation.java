import java.io.Serializable;
import java.util.ArrayList;

public class Conversation implements Serializable {
    private ArrayList<String> participants;
    private String convoRoot;
    private String id;

    private String generateID(){
        long timestamp = System.currentTimeMillis();
        return "c" + timestamp;
    }

    public Conversation(ArrayList<String> participants, String convoRoot){
        this.participants = participants;
        this.convoRoot = convoRoot;
        id = generateID();
    }

    public ArrayList<String> getParticipants(){ return participants; }

    public String getConvoRoot(){ return convoRoot; }

    public void setConvoRoot(String id){ convoRoot = id; }

    public String getId(){ return id; }
}
