import java.util.ArrayList;

public class UserMessageController {

    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private EventManager eventManager;
    private RoomManager roomManager;
    private UserEventController userEventController;
    private ConversationManager conversationManager;
    private MessageManager messageManager;
    private AttendeeMessageSystem attendeeMessageSystem;
    private OrganizerMessageSystem organizerMessageSystem;
    private SpeakerMessageSystem speakerMessageSystem;

    public UserMessageController(){

        attendeeManager = new AttendeeManager();
        organizerManager = new OrganizerManager();
        speakerManager = new SpeakerManager();
        eventManager = new EventManager();
        roomManager = new RoomManager();
        userEventController = new UserEventController(attendeeManager, organizerManager, speakerManager, eventManager, roomManager);
        conversationManager = new ConversationManager();
        messageManager = new MessageManager();
        attendeeMessageSystem = new AttendeeMessageSystem(conversationManager, messageManager, attendeeManager, organizerManager, speakerManager, eventManager);
        organizerMessageSystem = new OrganizerMessageSystem(conversationManager, messageManager, attendeeManager, organizerManager, speakerManager, eventManager);
        speakerMessageSystem = new SpeakerMessageSystem(conversationManager, messageManager, attendeeManager, organizerManager, speakerManager, eventManager);


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


    /**
     * allow an Attendee to send a message with a given content to all of their contacts
     * call messageSystem to perform!
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
     * @param a: the username of an Attendee who will be the sender of a message (param_type: String)
     * @param b: the recipient of the message (param_type: String)
     * @param content: the actual message text to be sent (param_type: String)
     * @return void
     */
    public void messOne(String a, String b, String content) {
        attendeeMessageSystem.singleMessage(a, b, content);
    }

}
