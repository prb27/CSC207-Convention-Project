package Controllers;

import UseCases.*;

import java.util.*;

public class MessengerMenuController {

    private MessageManager messageManager;
    private ConversationManager convoManager;
    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;

    private EventManager eventManager;
    private AccountHandler accountHandler;
    private AdminManager adminManager;
    private List<String> eligibleContacts;

    public MessengerMenuController(MessageManager messageManager, AttendeeManager attendeeManager,
                                   OrganizerManager organizerManager, SpeakerManager speakerManager,
                                   EventManager eventManager, AccountHandler accountHandler, ConversationManager convoManager, AdminManager adminManager){

        this.messageManager = messageManager;
        this.convoManager = convoManager;

        this.attendeeManager = attendeeManager;
        this.organizerManager = organizerManager;
        this.speakerManager = speakerManager;
        this.eventManager = eventManager;

        this.accountHandler = accountHandler;

        this.eligibleContacts = new ArrayList<>();
        this.adminManager = adminManager;

    }

    /**
     * Helper Method: Sends a message to a single recipient
     * @param senderId : ID of sender
     * @param recipientId : ID of recipient
     * @param content : content of message
     * @return ID of the conversation made between a sender and recipient (param_type: String)
     */


    public String singleMessage(String senderId, String recipientId, String content){
        List<String> p = new ArrayList<>();
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
    public String multiMessage(String senderId, List<String> recipientIds, String content){
        List<String> p = new ArrayList<>();
        p.add(senderId);
        p.addAll(recipientIds);

        String convoId = convoManager.createNewConversation(p);
        String messageId = messageManager.sendMessageMulti(senderId, recipientIds, content, convoId);

        convoManager.setConvoRoot(convoId, messageId);

        return convoId;
    }

    public boolean organizerSendMessageToAll(String organizerId, String content, String userType){

        if(organizerManager.isOrganizer(organizerId)){
            if(userType.equals("attendee")) {
                List<String> attendeeIDs = attendeeManager.getAllAttendeeIds();
                organizerToAll(organizerId, content, userType, attendeeIDs);
                return true;
            }
            if(userType.equals("organizer")) {
                List<String> organizerIDs = organizerManager.getAllOrganizerIds();
                organizerToAll(organizerId, content, userType, organizerIDs);
                return true;
            }
            if(userType.equals("speaker")) {
                List<String> speakerIds = speakerManager.getAllSpeakerIds();
                organizerToAll(organizerId, content, userType, speakerIds);
                return true;
            }
        }
        return false;

    }

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
     *
     * @param username
     * @param eventName
     * @param message
     * @return
     */
    public String organizerSendMessageByEvent(String username, String eventName, String message){

        if(!eventManager.isEvent(eventName)){
            return "EDE";
        }
        boolean messageByEvent = organizerMessageByEvent(username, eventName, message);
        if(messageByEvent){
            return "Successful";
        }
        return "Something went wrong";

    }

    public boolean organizerMessageByEvent(String organizerId, String eventName, String content){

        List<String> recipientIds;
        if (eventManager.isEvent(eventName)){
            recipientIds = new ArrayList<>(eventManager.getAttendeeList(eventName));
        } else {
            return false;
        }
        String convoId = multiMessage(organizerId, recipientIds, content);
        for(String id: recipientIds){
            if(organizerManager.isOrganizer(id)){
                organizerManager.addConversation(id, convoId);
            } else if(attendeeManager.isAttendee(id)){
                attendeeManager.addConversation(id, convoId);
            }
        }
        return true;
    }

    /**
     * Allows an attendee to send a message to another attendee or speaker
     * @param username: id of attendee sending the message
     * @param recipientId: id of the recipient
     * @param content: content of the message
     * @param userType: designates whether message is being sent to attendee or speaker
     * @return true if the message could be sent, false if the message was not sent
     */
    public boolean attendeeSendMessage(String username, String recipientId, String content, String userType) {
        if(userType.equals("attendee")){
            if(attendeeManager.isAttendee(username) && attendeeManager.isAttendee(recipientId)){
                attendeeToSingle(username, recipientId, content, userType);
                return true;
            }
            return false;
        }
        if(userType.equals("speaker")){
            if(attendeeManager.isAttendee(username) && speakerManager.isSpeaker(recipientId)){
                attendeeToSingle(username, recipientId, content, userType);
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * Allows an attendee to send a message to a single speaker, or a fellow attendee
     * @param attendeeId : ID of attendee
     * @param recipientId : ID of recipient
     * @param content : content of message
     * @param userType : type of user
     */
    public void attendeeToSingle(String attendeeId, String recipientId, String content, String userType){
        String convoId = singleMessage(attendeeId, recipientId, content);

        switch(userType){
            case "attendee":
                attendeeManager.addConversation(recipientId, convoId);
            case "speaker":
                speakerManager.addConversation(recipientId, convoId);
        }
        attendeeManager.addConversation(attendeeId, convoId);
    }
    /**
     * Checks if speaker, event is valid in order to send a message by speaker to a talk
     * @param speakerId : id of speaker
     * @param eventName : name of event
     * @param content : content of message
     * @return "SDE" - Entities.Speaker Doesn't Exist
     *         "EDE" - Entities.Event Doesn't Exist
     *         "SEC" - Entities.Speaker Entities.Event Conflict
     *         "YES" - Request Successful
     * @author Vladimir Caterov
     */
    public String speakerMessageByTalk(String speakerId, String eventName, String content){

        if(speakerManager.isSpeaker(speakerId)){
            if (eventManager.isEvent(eventName)){
                if (speakerManager.getListOfTalks(speakerId).containsValue(eventName)){
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
     * @return "SDE" - Entities.Speaker Doesn't Exist
     *         "EDE" - Entities.Event Doesn't Exist
     *         "SEC" - Entities.Speaker Entities.Event Conflict
     *         "YES" - Request Successful
     * @author Vladimir Caterov
     */
    public String speakerMessageByMultiTalks(String speakerId, List<String> eventNames, String content){

        if(speakerManager.isSpeaker(speakerId)) {
            for (String eventName : eventNames) {
                if (eventManager.isEvent(eventName)) {
                    if (speakerManager.getListOfTalks(speakerId).containsValue(eventName)) {
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

    /**
     * Allows as speaker to message a specific attendee of an event they speak at
     * @param speakerId: id of the speaker sending the message
     * @param eventNames: the list of events the speaker speaks at
     * @param recipientId: the id of the recipient of the message
     * @param content: the content of the message
     * @return true if message was sent, false otherwise
     */
    public boolean speakerMessageAttendee(String speakerId, List<String> eventNames, String recipientId, String content){
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
    /**
     * Correctly creates the conversations and sends the messages to enable a speaker to send a message to everyone in a talk
     * @param speakerId : ID of speaker
     * @param eventName : name of event
     * @param content : content of message
     */
    public void speakerByTalk(String speakerId, String eventName, String content){

        List<String> recipientIds = new ArrayList<>(eventManager.getAttendeeList(eventName));
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
    public void speakerByMultiTalks(String speakerId, List<String> eventNames, String content){
        List<String> recipientIds = new ArrayList<>();

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
    public void organizerToAll(String organizerId, String content, String userType, List<String> recipientIds){

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

    /**
     * Allows an organizer to send a message to a single user
     * @param organizerId : ID of organizer
     * @param recipientId : ID of recipient/user
     * @param content : content of message
     * @param userType : type of user
     */
    public void organizerToSingle(String organizerId, String recipientId, String content, String userType){

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
     * Allows an admin to send a message to a user
     * @param adminID : ID of attendee
     * @param recipientId : ID of recipient
     * @param content : content of message
     * @param userType : type of user
     */
    public void adminToSingle(String adminID, String recipientId, String content, String userType){
        String convoId = singleMessage(adminID, recipientId, content);
        switch(userType){
            case "attendee":
                attendeeManager.addConversation(recipientId, convoId);
            case "speaker":
                speakerManager.addConversation(recipientId, convoId);
            case "organizer":
                organizerManager.addConversation(recipientId, convoId);
        }
    }

    /**
     * Allows an admin to send a message to another user
     * @param username: id of attendee sending the message
     * @param recipientId: id of the recipient
     * @param content: content of the message
     * @param userType: designates whether message is being sent to attendee or speaker
     * @return true if the message could be sent, false if the message was not sent
     */
    public boolean adminSendMessage(String username, String recipientId, String content, String userType) {
        if(userType.equals("attendee")){
            if(adminManager.isAdmin(username) && attendeeManager.isAttendee(recipientId)){
                adminToSingle(username, recipientId, content, userType);
                return true;
            }
            return false;
        }
        if(userType.equals("speaker")){
            if(adminManager.isAdmin(username) && speakerManager.isSpeaker(recipientId)){
                adminToSingle(username, recipientId, content, userType);
                return true;
            }
            return false;
        }
        if(userType.equals("organizer")){
            if(adminManager.isAdmin(username) && organizerManager.isOrganizer(recipientId)){
                adminToSingle(username, recipientId, content, userType);
                return true;
            }
            return false;
        }

        return false;
    }

}
