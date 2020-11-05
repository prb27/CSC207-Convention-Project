import java.util.ArrayList;

public class AttendeeManager {
    private final ArrayList<IAttendee> attendees = new ArrayList<>();

    public void createAttendee(String username, String password) {
        // for now only have one type of attendee -> create an instance of that type
        attendees.add(new Attendee(username, password));
    }

    public void aAddContactsb(IAttendee a, String b) {
        ArrayList<String> contacts = a.getContacts();
        contacts.add(b);
        a.setContacts(contacts);
    }

    public ArrayList<String> getMessagableUsers(IAttendee a) {
        return a.getContacts();
    }

//  this is for looking up an attendee by a username
//    public IAttendee lookUpAttendee(String username) {
//        IAttendee target = null;
//        for (IAttendee a : attendees)
//            if (a.getUserId().equals(username))
//                target = a;
//        return target;
//    }
}




// can attendees delete their account?