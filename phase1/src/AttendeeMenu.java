import java.util.ArrayList;

public class AttendeeMenu {
    private final AttendeeManager attendeeManager = new AttendeeManager();
    private MessageManager messageManager;


    public AttendeeMenu(MessageManager messageManager) {
        this.messageManager = messageManager;
    }

    public void messAllContacts(String a, String content, int convoNumber) {
        Attendee attendee = attendeeManager.getAttendee(a);
        messageManager.sendMessageMulti(a, attendee.getContacts(), content, convoNumber);
    }

    public void messSome(String a, ArrayList<String> receivers, String content, int convoNumber) {
        messageManager.sendMessageMulti(a, receivers, content, convoNumber);
    }

    public void messOne(String a, String b, String content, int convoNumber) {
        messageManager.sendMessageSingle(a, b, content, convoNumber);
    }
}
