import java.util.ArrayList;

public class ConversationManager {
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

    public void setConvoRoot(String convoId, String messageId) {
        for(Conversation c: allConversations){
            if(c.getId() == convoId){
                c.setConvoRoot(messageId);
            }
        }
    }
}
