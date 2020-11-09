import java.util.ArrayList;
import java.util.Hashtable;

public class AttendeeManager {
    private final Hashtable<String, Attendee> attendees = new Hashtable<>();

    public void createAttendee(String username, String password) {
        // for now only have one type of attendee -> create an instance of that type
        attendees.put(username, new Attendee(username, password));
    }

    public void aAddContactB(String a, String b) {
        if (!attendees.containsKey(a)) {
            System.out.println("user with userID " + a + " not found");
            return;
        }
        // check to see if a can add b in controller level
        Attendee attendee = attendees.get(a);
        ArrayList<String> contacts = attendee.getContacts();
        contacts.add(b);
        attendee.setContacts(contacts);
    }

    public ArrayList<String> getMessagableUsers(String a) {
        return attendees.get(a).getContacts();
    }

    public Attendee getAttendee(String a) {
        return attendees.get(a);
    }



//  this is for looking up an attendee by a username
//    public scratch.IAttendee lookUpAttendee(String username) {
//        scratch.IAttendee target = null;
//        for (scratch.IAttendee a : attendees)
//            if (a.getUserId().equals(username))
//                target = a;
//        return target;
//    }
}




// can attendees delete their account?