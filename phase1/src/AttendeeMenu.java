public class AttendeeMenu {
    private final AttendeeManager attendeeManager = new AttendeeManager();
    public void messAllContacts(String a, String content) {
        Attendee attendee = attendeeManager.getAttendee(a);
        //MessageManager.sendMessageMulti(a, attendee.getContacts(), content);
    }
}
