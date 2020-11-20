import java.io.Serializable;
import java.util.ArrayList;

public class MessageManager implements Serializable {
    private ArrayList<Message> allMessages;

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

    public boolean removeMessage(String id){
        Message message = null;
        for(Message m: allMessages){
            if(m.getId() == id){
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

}
