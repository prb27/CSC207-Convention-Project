package Messaging;

import java.util.ArrayList;

public class MessageManager {
    private ArrayList<Message> allMessages;

    public Message sendMessageSingle(String senderId, String recipientId, String content, String convoID){
        Message message = new Message(senderId, recipientId, content, convoID);
        allMessages.add(message);
        return message;
    }

    public Message sendMessageMulti(String senderId, ArrayList<String> recipientIds, String content, String convoID){
        Message message = new Message(senderId, recipientIds, content, convoID);
        allMessages.add(message);
        return message;
    }

    public ArrayList<Message> getAllMessages(){
        return allMessages;
    }

}
