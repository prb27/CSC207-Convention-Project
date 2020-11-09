import java.util.ArrayList;

public class MessageManager {
    private ArrayList<Message> allMessages;

    public Message sendMessageSingle(String senderId, String recipientId, String content, int convoNumber){
        Message message = new Message(senderId, recipientId, content, convoNumber);
        allMessages.add(message);
        return message;
    }

    public Message sendMessageMulti(String senderId, ArrayList<String> recipientIds, String content, int convoNumber){
        Message message = new Message(senderId, recipientIds, content, convoNumber);
        allMessages.add(message);
        return message;
    }

    public ArrayList<Message> getAllMessages(){
        return allMessages;
    }

}
