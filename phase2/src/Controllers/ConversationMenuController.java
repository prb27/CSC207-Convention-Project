package Controllers;

import Controllers.UnusedControllers.AttendeeMessagingDashboardMenuController;
import Controllers.UnusedControllers.SpeakerMessagingDashboardMenuController;
import UseCases.ConversationManager;
import UseCases.MessageManager;

import java.util.ArrayList;
import java.util.List;

public class ConversationMenuController {

    private final ConversationManager conversationManager;
    private final MessageManager messageManager;

    public ConversationMenuController(ConversationManager conversationManager, MessageManager messageManager){
        this.conversationManager = conversationManager;
        this.messageManager = messageManager;
    }

    public boolean reply(String senderId, String convoId, String content){
        if(!conversationManager.isConversation(convoId)){
            return false;
        }
        String current = conversationManager.getConvoRoot(convoId);
        while(messageManager.getReply(current) != null){
            current = messageManager.getReply(current);
        }
        List<String> recipients = conversationManager.getConvoParticipants(convoId);
        recipients.remove(senderId);
        messageManager.addReply(senderId, recipients, content, current);
        return true;
    }

    /**
     * Returns an List of all the messages in a conversation, formatted for display
     * @param convoId: the id of the convo
     * @return the List of formatted strings
     */
    public List<String> orderedMessagesInConvo(String convoId) {
        List<String> rawMessages = new ArrayList<>();
        String current = conversationManager.getConvoRoot(convoId);
        rawMessages.add(current);
        while (messageManager.getReply(current) != null) {
            current = messageManager.getReply(current);
            rawMessages.add(current);
        }
        List<String> formattedMessages = new ArrayList<>();
        for (String messageId : rawMessages) {
            String message = messageManager.getSender(messageId) + ": " + messageManager.getContent(messageId) + " (" + messageManager.getTime(messageId) + ")";
            formattedMessages.add(message);
        }
        return formattedMessages;

    }

}
