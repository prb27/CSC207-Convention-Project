import java.util.ArrayList;

public class MessageSystem {
    ConversationManager convoManager;
    MessageManager messageManager;
    AttendeeManager attendeeManager;
    OrganizerManager organizerManager;
    SpeakerManager speakerManager;
    EventManager eventManager;

    public MessageSystem(ConversationManager cManager, MessageManager mManager,
                         AttendeeManager aManager, OrganizerManager oManager, SpeakerManager sManager,
                         EventManager eManager){
        this.convoManager = cManager;
        this.messageManager = mManager;
        this.attendeeManager = aManager;
        this.organizerManager = oManager;
        this.speakerManager = sManager;
        this.eventManager = eManager;
    }

    //helper: sends a message with single recipient
    Conversation singleMessage(String senderId, String recipientId, String content){
        ArrayList<String> p = new ArrayList<>();
        p.add(senderId);
        p.add(recipientId);

        Conversation conversation = convoManager.createNewConversation(p);
        Message message = messageManager.sendMessageSingle(senderId, recipientId, content, conversation.getId());
        conversation.setConvoRoot(message.getId());

        return conversation;
    }

    //helper: sends a message with multiple recipients
    Conversation multiMessage(String senderId, ArrayList<String> recipientIds, String content){
        ArrayList<String> p = new ArrayList<>();
        p.add(senderId);
        p.addAll(recipientIds);

        Conversation conversation = convoManager.createNewConversation(p);
        Message message = messageManager.sendMessageMulti(senderId, recipientIds, content, conversation.getId());
        conversation.setConvoRoot(message.getId());

        return conversation;
    }
}
