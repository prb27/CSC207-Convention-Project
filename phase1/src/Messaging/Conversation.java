package Messaging;

import java.util.ArrayList;

public class Conversation {
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

    public String getId(){ return id; }
}
