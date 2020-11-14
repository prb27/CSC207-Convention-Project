package Messaging;

import java.util.ArrayList;

public class ConversationManager {
    private ArrayList<Conversation> allConversations = new ArrayList<>();

    private ArrayList<String> participants(Message message){
        ArrayList<String> participantList = new ArrayList<>();
        participantList.add(message.getSender());
        participantList.addAll(message.getRecipients());

        return participantList;
    }

    //creates a conversation via a message
    public Conversation createNewConversation(Message originalMessage){
        ArrayList<String> participants = participants(originalMessage);

        Conversation convo = new Conversation(participants, originalMessage.getId());

        allConversations.add(convo);

        return convo;
    }

    //creates a conversation via participants
    public Conversation createNewConversation(ArrayList<String> participants){

        Conversation convo = new Conversation(participants, "");

        allConversations.add(convo);

        return convo;
    }

    //either returns an existing convo with participants or creates a new one with those participants
    public Conversation getConvoByParticipants(ArrayList<String> participants){
        for(Conversation c: allConversations){
            if(c.getParticipants().equals(participants)){
                return c;
            }
        }
        return createNewConversation(participants);
    }
}
