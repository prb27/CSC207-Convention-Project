package NewUI;

import UseCases.*;
import com.sun.org.apache.xpath.internal.operations.String;

public class AdminTextUI extends TextUI {
    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private AdminManager adminManager;
    private EventManager eventManager;
    private RoomManager roomManager;
    private ConversationManager conversationManager;
    private MessageManager messageManager;


    public AdminTextUI(AttendeeManager attendeeManager, OrganizerManager organizerManager, SpeakerManager speakerManager,
                  AdminManager adminManager, MessageManager messageManager, ConversationManager conversationManager,
                  EventManager eventManager, RoomManager roomManager) {
        super(attendeeManager, organizerManager, speakerManager, adminManager, messageManager, conversationManager, eventManager, roomManager);
    }
    public void emptyEventsDeleted() {
        System.out.println("all events without attendees were deleted");
    }

    public void messageID() {
        System.out.println("Please enter the message id you want to delete");
    }

    public void messageDeleted(String message) {
        System.out.println("message with id "+message+" was deleted");
    }
}
