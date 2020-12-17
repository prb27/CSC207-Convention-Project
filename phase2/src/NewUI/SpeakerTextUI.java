package NewUI;

import UseCases.*;

import java.util.List;

public class SpeakerTextUI extends TextUI{
    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private AdminManager adminManager;
    private EventManager eventManager;
    private RoomManager roomManager;
    private ConversationManager conversationManager;
    private MessageManager messageManager;


    public SpeakerTextUI(AttendeeManager attendeeManager, OrganizerManager organizerManager, SpeakerManager speakerManager,
                       AdminManager adminManager, MessageManager messageManager, ConversationManager conversationManager,
                       EventManager eventManager, RoomManager roomManager) {
        super(attendeeManager, organizerManager, speakerManager, adminManager, messageManager, conversationManager, eventManager, roomManager);
    }

    public void eventDisplay(String event, List<String> info) {
        System.out.println(event);
        for (String i: info)
            System.out.println(i);
    }

    public void seeEventList(List<String> eventList){
        for (String e: eventList)
            System.out.println(e);
    }

    public void getAttendeeToMess() {
        System.out.println("Please enter the username of the Attendee you wish to message:");
    }

    public void rep() {
        System.out.println("Enter \"r\" to reply in this conversation. [Any other input will exit this menu]");
    }

}
