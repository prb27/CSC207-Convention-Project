import java.util.ArrayList;

public class MessageSystem {
    private ConversationManager convoManager;
    private MessageManager messageManager;

    public MessageSystem(ConversationManager convoManager, MessageManager messageManager){
        this.convoManager = convoManager;
        this.messageManager = messageManager;
    }

    public void singleMessage(String senderId, String recipientId, String content){
        ArrayList<String> p = new ArrayList<>();
        p.add(senderId);
        p.add(recipientId);

        Conversation conversation = convoManager.getConvoByParticipants(p);
        String id = conversation.getId();

        messageManager.sendMessageSingle(senderId, recipientId, content, id);

    }
    public void multiMessage(String senderId, ArrayList<String> recipientIds, String content){
        ArrayList<String> p = new ArrayList<>();
        p.add(senderId);
        p.addAll(recipientIds);

        Conversation conversation = convoManager.getConvoByParticipants(p);
        String id = conversation.getId();

        messageManager.sendMessageMulti(senderId, recipientIds, content, id);

    }
}
