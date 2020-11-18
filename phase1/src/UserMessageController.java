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

}
