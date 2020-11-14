import java.util.ArrayList;

public class MessageSystem {
    private ConversationManager convoManager;
    private MessageManager messageManager;
    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;


    public MessageSystem(ConversationManager cManager, MessageManager mManager,
                         AttendeeManager aManager, OrganizerManager oManager, SpeakerManager sManager){
        this.convoManager = cManager;
        this.messageManager = mManager;
        this.attendeeManager = aManager;
        this.organizerManager = oManager;
        this.speakerManager = sManager;
    }

    //helper: sends a message with single recipient
    private void singleMessage(String senderId, String recipientId, String content){
        ArrayList<String> p = new ArrayList<>();
        p.add(senderId);
        p.add(recipientId);

        src.Messaging.Message message = messageManager.sendMessageSingle(senderId, recipientId, content, "");
        src.Messaging.Conversation conversation = convoManager.createNewConversation(message);

        message.setConvoID(conversation.getId());


    }

    //helper: sends a message with multiple recipients
    private void multiMessage(String senderId, ArrayList<String> recipientIds, String content){
        ArrayList<String> p = new ArrayList<>();
        p.add(senderId);
        p.addAll(recipientIds);

        src.Messaging.Message message = messageManager.sendMessageMulti(senderId, recipientIds, content, "");
        src.Messaging.Conversation conversation = convoManager.createNewConversation(message);

        message.setConvoID(conversation.getId());

    }

    //helper: send a reply to a message
    private void sendReplySingle(String senderID, ArrayList){

    }

}
