package NewUI;

import UseCases.*;

public class AdminPresenterTextUI extends TextUI {
    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private AdminManager adminManager;
    private EventManager eventManager;
    private RoomManager roomManager;
    private ConversationManager conversationManager;
    private MessageManager messageManager;


    public AdminPresenterTextUI(AttendeeManager attendeeManager, OrganizerManager organizerManager, SpeakerManager speakerManager,
                                AdminManager adminManager, MessageManager messageManager, ConversationManager conversationManager,
                                EventManager eventManager, RoomManager roomManager) {
        super(attendeeManager, organizerManager, speakerManager, adminManager, messageManager, conversationManager, eventManager, roomManager);
    }

    public void adminmenu(String username) {
        System.out.println("\n\nHello " + username + "!");
        System.out.println("What would you like to do?");

        System.out.println("1: Delete empty events");
        System.out.println("2: Report User");
        System.out.println("\n0: Sign-out");
    }

    public void emptyEventsDeleted() {
        System.out.println("All events without attendees were deleted");
    }

    public void messageID() {
        System.out.println("Please enter the message id you want to delete");
    }

    public void messageDeleted(String messageID) {
        System.out.println("message with id " + messageID + " was deleted");
    }
    public void promptForUsername(){
        System.out.println("Please enter the username of the user you wish to report");
    }
}
