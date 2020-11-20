import java.io.Serializable;
import java.util.ArrayList;

public class MessageManager implements Serializable {
    private ArrayList<Message> allMessages;

    public MessageManager(){
        allMessages = new ArrayList<>();
    }

    public String sendMessageSingle(String senderId, String recipientId, String content, String convoID){
        Message message = new Message(senderId, recipientId, content, convoID);
        allMessages.add(message);
        return message.getId();
    }

    public String sendMessageMulti(String senderId, ArrayList<String> recipientIds, String content, String convoID){
        Message message = new Message(senderId, recipientIds, content, convoID);
        allMessages.add(message);
        return message.getId();
    }

    public boolean addReply(String senderId, ArrayList<String> recipientIds, String content, String convoId, String messageId){
        Message message = getMessage(messageId);
        if(message == null){
            return false;
        }
        Message newMessage = new Message(senderId, recipientIds, content, convoId);
        message.setReply(newMessage.getId());
        return true;
    }
    public boolean removeMessage(String id){
        Message message = null;
        for(Message m: allMessages){
            if(m.getId().equals(id)){
                message = m;
            }
        }
        if(message != null){
            allMessages.remove(message);
            return true;
        }
        return false;
    }

    public ArrayList<Message> getAllMessages(){
        return allMessages;
    }

    public Message getMessage(String messageId){
        for(Message m: allMessages){
            if(m.getId().equals(messageId)){
                return m;
            }
        }
        return null;
    }

    public String getReply(String messageId){
        return getMessage(messageId).getReply();
    }

    public String getSender(String messageId){
        return getMessage(messageId).getSender();
    }
    public String getContent(String messageId){
        return getMessage(messageId).getContent();
    }

    public String getTime(String messageId){
        return getMessage(messageId).getTime().toString();
    }
}
