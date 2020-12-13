package Controllers;

import UseCases.ConversationManager;
import UseCases.MessageManager;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class ConversationMenuController {

    public String conversationInformation;
    public String currentConversationID;
    private AttendeeMessagingDashboardMenuController attendeeMessagingDashboardMenuController;
    private SpeakerMessagingDashboardMenuController speakerMessagingDashboardMenuController;
    private ConversationManager conversationManager;
    private MessageManager messageManager;

    public ConversationMenuController(AttendeeMessagingDashboardMenuController attendeeMessagingDashboardMenuController,
                                      SpeakerMessagingDashboardMenuController speakerMessagingDashboardMenuController,
                                      ConversationManager conversationManager, MessageManager messageManager){
        this.conversationInformation = "";
        this.attendeeMessagingDashboardMenuController = attendeeMessagingDashboardMenuController;
        this.speakerMessagingDashboardMenuController = speakerMessagingDashboardMenuController;
        this.conversationManager = conversationManager;
        this.messageManager = messageManager;
    }
    public void setConversationInformation(String information){
        this.conversationInformation = information;
    }
    public String getConversationInformation(){
        return conversationInformation;
    }
    public void setCurrentConversationID(String conversationID){
        this.currentConversationID = conversationID;
    }
    public String getCurrentConversationID(){
        return currentConversationID;
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
