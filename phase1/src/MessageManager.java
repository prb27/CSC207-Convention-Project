import java.util.ArrayList;

public class MessageManager {
    private ArrayList<Message> allMessages;

    public void sendMessageSingle(String senderId, String recipientId, String content){
        Message message = new Message(senderId, recipientId, content, "0");
    }

    public void sendMessageMulti(String senderId, ArrayList<String> recipientIds, String content){
        Message message = new Message(senderId, recipientIds, content, "0");
    }

    public void addReply(Message originalMessage, Message reply){
        originalMessage.getReplies().add(reply);
    }
}
