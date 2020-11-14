import java.util.ArrayList;
import Messaging.*;

public class AttendeeMenu {
    private final AttendeeManager attendeeManager = new AttendeeManager();
    private MessageSystem messageSystem;


    public AttendeeMenu(MessageSystem messageSystem) {
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
}
