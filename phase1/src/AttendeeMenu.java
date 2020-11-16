import java.util.ArrayList;

public class AttendeeMenu {
    private final AttendeeManager attendeeManager = new AttendeeManager();
    private EventManager eventManager;
    private MessageSystem messageSystem;


    public AttendeeMenu(MessageSystem messageSystem, EventManager eventManager) {
        this.eventManager = eventManager;
        this.messageSystem = messageSystem;
    }

    public void messAllContacts(String a, String content) {
        Attendee attendee = attendeeManager.getAttendee(a);
        messageSystem.multiMessage(a, attendee.getContacts(), content);
    }

    public void messSome(String a, ArrayList<String> receivers, String content) {
        messageSystem.multiMessage(a, receivers, content);
    }

    public void messOne(String a, String b, String content) {
        messageSystem.singleMessage(a, b, content);
    }

    public void signUp(String attendee, String event) {
        eventManager.reserveAttendee(event, attendee);
    }

    public void cancel(String attendee, String event) {
        eventManager.removeAttendee(event, attendee);
    }

    public ArrayList<Event> seeEventList() {
        return eventManager.getEventList();
    }
}
