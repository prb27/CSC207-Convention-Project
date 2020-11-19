import java.util.ArrayList;

public class UserMessageController {

    private final AttendeeManager attendeeManager;
    private final OrganizerManager organizerManager;
    private final SpeakerManager speakerManager;

    private final EventManager eventManager;

    private AttendeeMessageSystem attendeeMessageSystem;
    private final OrganizerMessageSystem organizerMessageSystem;
    private SpeakerMessageSystem speakerMessageSystem;

    public UserMessageController(AttendeeManager attendeeManager, OrganizerManager organizerManager, SpeakerManager speakerManager, EventManager eventManager, ConversationManager conversationManager, MessageManager messageManager){

        this.attendeeManager = attendeeManager;
        this.organizerManager = organizerManager;
        this.speakerManager = speakerManager;

        this.eventManager = eventManager;

        this.attendeeMessageSystem = new AttendeeMessageSystem(conversationManager, messageManager, this.attendeeManager, this.organizerManager, this.speakerManager, eventManager);
        this.organizerMessageSystem = new OrganizerMessageSystem(conversationManager, messageManager, this.attendeeManager, this.organizerManager, this.speakerManager, eventManager);
        this.speakerMessageSystem = new SpeakerMessageSystem(conversationManager, messageManager, this.attendeeManager, this.organizerManager, this.speakerManager, eventManager);


    }

    public boolean organizerSendMessageToAll(String organizerId, String content, String userType){

        if(organizerManager.isOrganizer(organizerId)){
            organizerMessageSystem.organizerToAll(organizerId, content, userType);
            return true;
        }
        return false;

    }

    public boolean organizerSendMessageToSingle(String organizerId, String recipientId, String content, String userType){

        if(userType.equals("attendee")){
            if(organizerManager.isOrganizer(organizerId) && attendeeManager.isAttendee(recipientId)){
                organizerMessageSystem.organizerToSingle(organizerId, recipientId, content, userType);
                return true;
            }
            return false;
        }
        if(userType.equals("organizer")){
            if(organizerManager.isOrganizer(organizerId) && organizerManager.isOrganizer(recipientId)){
                organizerMessageSystem.organizerToSingle(organizerId, recipientId, content, userType);
                return true;
            }
            return false;
        }
        if(userType.equals("speaker")){
            if(organizerManager.isOrganizer(organizerId) && speakerManager.isSpeaker(recipientId)){
                organizerMessageSystem.organizerToSingle(organizerId, recipientId, content, userType);
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
                    speakerMessageSystem.speakerByTalk(speakerId, eventName, content);
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
                        speakerMessageSystem.speakerByTalk(speakerId, eventName, content);
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
     * allow an Attendee to send a message with a given content to all of their contacts
     * call messageSystem to perform!
     * @author Khoa Pham
     * @param a: the username of an Attendee who will be the sender of a message (param_type: String)
     * @param content: the actual message text to be sent (param_type: String)
     * @return void
     */
    public void messAllContacts(String a, String content) {
        // check <a> ??
        Attendee attendee = attendeeManager.getAttendee(a);
        attendeeMessageSystem.multiMessage(a, attendee.getContacts(), content);
    }

    /**
     * allow an Attendee to send a message with a given content to some of their contacts
     * call messageSystem to perform!
     * @author Khoa Pham
     * @param a: the username of an Attendee who will be the sender of a message (param_type: String)
     * @param receivers: the list of the message's recipients (param_type: ArrayList<String>)
     * @param content: the actual message text to be sent (param_type: String)
     * @return void
     */
    public void messSome(String a, ArrayList<String> receivers, String content) {
        attendeeMessageSystem.multiMessage(a, receivers, content);
    }

    /**
     * allow an Attendee to send a message with a given content to one of their contacts
     * call messageSystem to perform!
     * @author Khoa Pham
     * @param a: the username of an Attendee who will be the sender of a message (param_type: String)
     * @param b: the recipient of the message (param_type: String)
     * @param content: the actual message text to be sent (param_type: String)
     * @return void
     */
    public void messOne(String a, String b, String content) {
        attendeeMessageSystem.singleMessage(a, b, content);
    }

}
