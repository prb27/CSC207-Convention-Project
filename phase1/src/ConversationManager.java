import java.io.Serializable;
import java.util.ArrayList;

public class ConversationManager implements Serializable {
    private ArrayList<Conversation> allConversations = new ArrayList<>();

    private ArrayList<String> participants(Message message){
        ArrayList<String> participantList = new ArrayList<>();
        participantList.add(message.getSender());
        participantList.addAll(message.getRecipients());

        return participantList;
    }

    //creates a conversation via participants
    public String createNewConversation(ArrayList<String> participants){

        Conversation convo = new Conversation(participants, "");

        allConversations.add(convo);

        return convo.getId();
    }

    public String getConvoRoot(String convoId){
        for(Conversation c: allConversations){
            if(c.getId().equals(convoId)){
                return c.getConvoRoot();
            }
        }
        return null;
    }

    public void setConvoRoot(String convoId, String messageId) {
        for(Conversation c: allConversations){
            if(c.getId() == convoId){
                c.setConvoRoot(messageId);
            }
        }
    }

    //VLAD ADDED
    private Conversation getConversation(String convoId){
        for(Conversation convo: allConversations){
            if (convo.getId().equals(convoId)){
                return convo;
            }
        }
        return null;
    }

    public ArrayList<String> getConvoParticipants(String convoId){
        for(Conversation c: allConversations){
            if(c.getId().equals(convoId)){
                return c.getParticipants();
            }
        }
        return null;
    }

    public boolean isConversation(String convoId){
        return getConversation(convoId) != null;
    }
}
