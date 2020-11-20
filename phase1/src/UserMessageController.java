import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class UserMessageController {

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

    public boolean organizerSendMessageToAll(String organizerId, String content, String userType, ArrayList<String> recipients){

        if(organizerManager.isOrganizer(organizerId)){
            organizerToAll(organizerId, content, userType, recipients);
            return true;
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

    public String speakerMessageByMultiTalks(String speakerId, ArrayList<String> eventNames, String content){
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

    //helper: sends a message with a single
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
    private String multiMessage(String senderId, ArrayList<String> recipientIds, String content){
        ArrayList<String> p = new ArrayList<>();
        p.add(senderId);
        p.addAll(recipientIds);

        String convoId = convoManager.createNewConversation(p);
        String messageId = messageManager.sendMessageMulti(senderId, recipientIds, content, convoId);

        convoManager.setConvoRoot(convoId, messageId);

        return convoId;
    }

    private void reply(){

    }
    private void speakerByTalk(String speakerId, String eventName, String content){
        Event talk = eventManager.getEvent(eventName);

        ArrayList<String> recipientIds = new ArrayList<>(talk.getAttendeeList());
        String convoId = multiMessage(speakerId, recipientIds, content);
        for(String id: recipientIds){
            if(organizerManager.isOrganizer(id)){
                organizerManager.addConversation(id, convoId);
            } else if(attendeeManager.isAttendee(id)){
                attendeeManager.addConversation(id, convoId);
            }
        }
    }

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



    //
//    /**
//     * allow an Attendee to send a message with a given content to all of their contacts
//     * call messageSystem to perform!
//     * @author Khoa Pham
//     * @param a: the username of an Attendee who will be the sender of a message (param_type: String)
//     * @param content: the actual message text to be sent (param_type: String)
//     * @return void
//     */
//    public void messAllContacts(String a, String content) {
//        // check <a> ??
//        Attendee attendee = attendeeManager.getAttendee(a);
//        attendeeMessageSystem.multiMessage(a, attendee.getContacts(), content);
//    }
//
//    /**
//     * allow an Attendee to send a message with a given content to some of their contacts
//     * call messageSystem to perform!
//     * @author Khoa Pham
//     * @param a: the username of an Attendee who will be the sender of a message (param_type: String)
//     * @param receivers: the list of the message's recipients (param_type: ArrayList<String>)
//     * @param content: the actual message text to be sent (param_type: String)
//     * @return void
//     */
//    public void messSome(String a, ArrayList<String> receivers, String content) {
//        attendeeMessageSystem.multiMessage(a, receivers, content);
//    }
//
//    /**
//     * allow an Attendee to send a message with a given content to one of their contacts
//     * call messageSystem to perform!
//     * @author Khoa Pham
//     * @param a: the username of an Attendee who will be the sender of a message (param_type: String)
//     * @param b: the recipient of the message (param_type: String)
//     * @param content: the actual message text to be sent (param_type: String)
//     * @return void
//     */
//
//    public void messOne(String a, String b, String content) {
//        attendeeMessageSystem.singleMessage(a, b, content);
//    }
    //helper: sends a message with single recipient
}
