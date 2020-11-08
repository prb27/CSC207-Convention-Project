import java.util.ArrayList;

public class MessageManager {
    private ArrayList<Message> allMessages;

    public Message sendMessageSingle(String senderId, String recipientId, String content){
        return new Message(senderId, recipientId, content, "0");
    }

    public Message sendMessageMulti(String senderId, ArrayList<String> recipientIds, String content){
        return new Message(senderId, recipientIds, content, "0");
    }

    public void addReply(Message originalMessage, Message reply){
        originalMessage.getReplies().add(reply);
    }

}
