import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is responsible to compute all the user interactions and messaging operations needed
 * The following responsibilities include:
 * - allows an organizer to send a message to all user types
 * - allows an organizer to send a message to a single user type
 * - allows a speaker to send a message to all attendees of a talk
 * - allows a speaker to send a message to all attendees of multiple talks
 * - allows an attendee to send a message to another attendee
 *
 */
public class UserMessageController implements Serializable {

    private final AttendeeManager attendeeManager;
    private final OrganizerManager organizerManager;
    private final SpeakerManager speakerManager;

    private final EventManager eventManager;

    private final ConversationManager convoManager;
    private final MessageManager messageManager;

    public UserMessageController(AttendeeManager attendeeManager, OrganizerManager organizerManager, SpeakerManager speakerManager, EventManager eventManager, ConversationManager conversationManager, MessageManager messageManager){

        this.attendeeManager = attendeeManager;
        this.organizerManager = organizerManager;
        this.speakerManager = speakerManager;

        this.eventManager = eventManager;

        this.convoManager = conversationManager;
        this.messageManager = messageManager;

    }

    /**
     * Allows the organizer to send a message to any user
     * @param organizerId : ID of organizer
     * @param content : content of message
     * @param userType : the user an organizer wishes to send a message to
     * @return true if message is sent, false otherwise
     */
    public boolean organizerSendMessageToAll(String organizerId, String content, String userType){

        if(organizerManager.isOrganizer(organizerId)){
            if(userType.equals("attendee")) {
                ArrayList<String> attendeeIDs = attendeeManager.getAllAttendeeIds();
                organizerToAll(organizerId, content, userType, attendeeIDs);
                return true;
            }
            if(userType.equals("organizer")) {
                ArrayList<String> organizerIDs = organizerManager.getAllOrganizerIds();
                organizerToAll(organizerId, content, userType, organizerIDs);
                return true;
            }
            if(userType.equals("speaker")) {
                ArrayList<String> speakerIds = speakerManager.getAllSpeakerIds();
                organizerToAll(organizerId, content, userType, speakerIds);
                return true;
            }
        }
        return false;

    }

    /**
     * Allows an organizer to send a message to a single yser.
     * @param organizerId : id of organizer
     * @param recipientId : id of recipient
     * @param content : content of message
     * @param userType : type of user (speaker, organizer, attendee)
     * @return true if message is sent, false otherwise
     */
    public boolean organizerSendMessageToSingle(String organizerId, String recipientId, String content, String userType){

        if(userType.equals("attendee")){
            if(organizerManager.isOrganizer(organizerId) && attendeeManager.isAttendee(recipientId)){
                organizerToSingle(organizerId, recipientId, content, userType);
                return true;
            }
            return false;
        }
        if(userType.equals("organizer")){
            if(organizerManager.isOrganizer(organizerId) && organizerManager.isOrganizer(recipientId)){
                organizerToSingle(organizerId, recipientId, content, userType);
                return true;
            }
            return false;
        }
        if(userType.equals("speaker")){
            if(organizerManager.isOrganizer(organizerId) && speakerManager.isSpeaker(recipientId)){
                organizerToSingle(organizerId, recipientId, content, userType);
                return true;
            }
            return false;
        }
        return false;

    }

    /**
     * Checks if speaker, event is valid in order to send a message by speaker to a talk
     * @param speakerId : id of speaker
     * @param eventName : name of event
     * @param content : content of message
     * @return "SDE" - Speaker Doesn't Exist
     *         "EDE" - Event Doesn't Exist
     *         "SEC" - Speaker Event Conflict
     *         "YES" - Request Successful
     * @author Vladimir Caterov
     */
    public String speakerMessageByTalk(String speakerId, String eventName, String content){
        HashMap<String, String> selectedTalk = new HashMap<>();
        selectedTalk.put(eventManager.getEventTime(eventName), eventName);

        if(speakerManager.isSpeaker(speakerId)){
            if (eventManager.isEvent(eventName)){
                if (speakerManager.getListOfTalks(speakerId).contains(selectedTalk)){
                    speakerByTalk(speakerId, eventName, content);
                    return "YES";
                }
                return "SEC";
            }
            return "EDE";
        }
        return "SDE";
    }

    /**
     * Check if speaker, event is valid to allow a speaker to send message for multiple talks
     * @param speakerId
     * @param eventNames
     * @param content
     * @return "SDE" - Speaker Doesn't Exist
     *         "EDE" - Event Doesn't Exist
     *         "SEC" - Speaker Event Conflict
     *         "YES" - Request Successful
     * @author Vladimir Caterov
     */
    public String speakerMessageByMultiTalks(String speakerId, ArrayList<String> eventNames, String content){

        if(speakerManager.isSpeaker(speakerId)) {
            for (String eventName : eventNames) {
                if (eventManager.isEvent(eventName)) {
                    HashMap<String, String> selectedTalk = new HashMap<>();
                    selectedTalk.put(eventManager.getEventTime(eventName), eventName);
                    if (speakerManager.getListOfTalks(speakerId).contains(selectedTalk)) {
                        speakerByTalk(speakerId, eventName, content);
                        return "YES";
                    }
                    return "SEC";
                }
                return "EDE";
            }
        }
        return "SDE";
    }

    public boolean speakerMessageAttendee(String speakerId, ArrayList<String> eventNames, String recipientId, String content){
        if (speakerManager.isSpeaker(speakerId)){
            for(String eventName: eventNames){
                if(attendeeManager.isAttending(recipientId, eventName)){
                    singleMessage(speakerId, recipientId, content);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean reply(String senderId, String convoId, String content){
        if(!convoManager.isConversation(convoId)){
            return false;
        }
        String current = convoManager.getConvoRoot(convoId);
        while(messageManager.getReply(current) != null){
            current = messageManager.getReply(current);
        }
        ArrayList<String> recipients = convoManager.getConvoParticipants(convoId);
        recipients.remove(senderId);
        messageManager.addReply(senderId, recipients, content, convoId, current);
        return true;
    }

    public ArrayList<String> orderedMessagesInConvo(String convoId){
        ArrayList<String> rawMessages = new ArrayList<>();
        String current = convoManager.getConvoRoot(convoId);
        while(messageManager.getReply(current) != null){
            rawMessages.add(current);
            current = messageManager.getReply(current);
        }
        ArrayList<String> formattedMessages = new ArrayList<>();
        for(String messageId: rawMessages){
            String message = messageManager.getSender(messageId) + ": " + messageManager.getContent(messageId) + " (" + messageManager.getTime(messageId) + ")";
            formattedMessages.add(message);
        }
        return formattedMessages;
    }

    /**
     * Helper Method: Sends a message to a single recipient
     * @param senderId : ID of sender
     * @param recipientId : ID of recipient
     * @param content : content of message
     * @return ID of the conversation made between a sender and recipient (param_type: String)
     */
    private String singleMessage(String senderId, String recipientId, String content){
        ArrayList<String> p = new ArrayList<>();
        p.add(senderId);
        p.add(recipientId);

        String convoId = convoManager.createNewConversation(p);
        String messageId = messageManager.sendMessageSingle(senderId, recipientId, content, convoId);

        convoManager.setConvoRoot(convoId, messageId);

        return convoId;
    }

    //helper: sends a message with multiple recipients

    /**
     * Helper Method: Sends a message to multiple recipients
     * @param senderId : ID of sender
     * @param recipientIds : ID of recipient
     * @param content : content of message
     * @return ID of the conversation made between the sender and multiple recipient (param_type: String)
     */
    private String multiMessage(String senderId, ArrayList<String> recipientIds, String content){
        ArrayList<String> p = new ArrayList<>();
        p.add(senderId);
        p.addAll(recipientIds);

        String convoId = convoManager.createNewConversation(p);
        String messageId = messageManager.sendMessageMulti(senderId, recipientIds, content, convoId);

        convoManager.setConvoRoot(convoId, messageId);

        return convoId;
    }


    /**
     * Correctly creates the conversations and sends the messages to enable a speaker to send a message to everyone in a talk
     * @param speakerId : ID of speaker
     * @param eventName : name of event
     * @param content : content of message
     */
    private void speakerByTalk(String speakerId, String eventName, String content){

        ArrayList<String> recipientIds = new ArrayList<>(eventManager.getAttendeeList(eventName));
        String convoId = multiMessage(speakerId, recipientIds, content);
        for(String id: recipientIds){
            if(organizerManager.isOrganizer(id)){
                organizerManager.addConversation(id, convoId);
            } else if(attendeeManager.isAttendee(id)){
                attendeeManager.addConversation(id, convoId);
            }
        }
    }

    /**
     * sends the messages to enable a speaker to send a message to everyone in multiple talks
     * @param speakerId : speaker ID
     * @param eventNames : name of event
     * @param content : content of message
     */
    private void speakerByMultiTalks(String speakerId, ArrayList<String> eventNames, String content){
        ArrayList<String> recipientIds = new ArrayList<>();

        for(String eventName: eventNames){
            recipientIds.addAll(eventManager.getAttendeeList(eventName));
        }
        //do the below 3 lines to remove duplicate attendees so they dont get messaged multiple times
        Set<String> set = new HashSet<>(recipientIds);
        recipientIds.clear();
        recipientIds.addAll(set);

        String convoId = multiMessage(speakerId, recipientIds, content);

        if(!recipientIds.isEmpty()){
            for(String id: recipientIds){
                if(organizerManager.isOrganizer(id)){
                    organizerManager.addConversation(id, convoId);
                } else if(attendeeManager.isAttendee(id)){
                    attendeeManager.addConversation(id, convoId);
                }
            }
        }
    }

    /**
     * Allows to send a message from an organizer to all of one user type
     * @param organizerId : id of organizer
     * @param content : content of message
     * @param userType : type of user
     * @param recipientIds : IDs of all users
     */
    private void organizerToAll(String organizerId, String content, String userType, ArrayList<String> recipientIds){

        String convoId = multiMessage(organizerId, recipientIds, content);
        switch(userType){
            case "attendee":
                for(String id: recipientIds){
                    attendeeManager.addConversation(id, convoId);
                }
            case "organizer":
                for(String id: recipientIds){
                    organizerManager.addConversation(id, convoId);
                }
            case "speaker":
                for(String id: recipientIds){
                    speakerManager.addConversation(id, convoId);
                }
        }
        organizerManager.addConversation(organizerId, convoId);
    }

    //handles phase 1 specification that says organizers can message a single attendee

    /**
     * Allows an organizer to send a message to a single user
     * @param organizerId : ID of organizer
     * @param recipientId : ID of recipient/user
     * @param content : content of message
     * @param userType : type of user
     */
    private void organizerToSingle(String organizerId, String recipientId, String content, String userType){

        String convoId = singleMessage(organizerId, recipientId, content);

        switch(userType){
            case "attendee":
                attendeeManager.addConversation(recipientId, convoId);
            case "organizer":
                organizerManager.addConversation(recipientId, convoId);
            case "speaker":
                speakerManager.addConversation(recipientId, convoId);
        }
        organizerManager.addConversation(organizerId, convoId);
    }

    /**
     * Allows an attendee to send a message to a single speaker, or a fellow attendee
     * @param attendeeId : ID of attendee
     * @param recipientId : ID of recipient
     * @param content : content of message
     * @param userType : type of user
     */
    private void attendeeToSingle(String attendeeId, String recipientId, String content, String userType){
        String convoId = singleMessage(attendeeId, recipientId, content);

        switch(userType){
            case "attendee":
                attendeeManager.addConversation(recipientId, convoId);
            case "speaker":
                speakerManager.addConversation(recipientId, convoId);
        }
        attendeeManager.addConversation(attendeeId, convoId);
    }
}
