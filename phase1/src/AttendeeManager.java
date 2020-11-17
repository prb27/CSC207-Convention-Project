import java.util.ArrayList;
import java.util.Hashtable;

/**
 * this class manages (stores and updates) all Attendee objects
 * these functionalities include:
 *      - creating new Attendee object
 *      - allow an Attendee to connects another User
 *      - add a new conversation to an Attendee's list of participating conversations
 *      - get the list of contacts from a given Attendee
 *      - get an Attendee given their username
 *      - get all Attendees
 *      - check the password of a given Attendee
 * @author Khoa Pham
 * @see Attendee
 */
public class AttendeeManager {
    private final Hashtable<String, Attendee> attendees = new Hashtable<>();
    /**
     * create an Attendee given a pair of username and password
     * if the username is used, return false!
     * else create an Attendee and update the list of all attendees <attendees> to reflect this creation
     * also returns true in that case!
     * @param username: the username to be assigned to this new Attendee (param_type: String)
     * @param password: the password to be assigned to this new Attendee (param_type: String)
     * @return boolean
     */
    public boolean createAttendee(String username, String password) {
        if (attendees.containsKey(username)) {
            //System.out.println("username "+ username +" is taken!");
            // raise Exception
            return false;
        }
        attendees.put(username, new Attendee(username, password));
        return true;
    }

    /**
     * add an User (String) to an Attendee's contact list
     * if the Attendee does not exist, print a message and do nothing!
     * ASSUMPTION: String <b> is valid and <a> can add <b> to its contact list!    // check in controller?
     * @param a: the Attendee whose contact list will be added to (param_type: String)
     * @param b: the User to be added to the Attendee's contact list (param_type: String)
     * @return void
     */
    public void aAddContactB(String a, String b) {
        if (!attendees.containsKey(a)) {
            //System.out.println("user with userID " + a + " not found");
            return;
        }
        Attendee attendee = attendees.get(a);
        ArrayList<String> contacts = attendee.getContacts();
        contacts.add(b);
        attendee.setContacts(contacts);
    }

    /**
     * add a conversation to an Attendee's list of participating conversations
     * if the Attendee does not exist, print a message and do nothing!
     * ASSUMPTION: String <conversation> is valid!  // check in controller?
     * @param attendee: the Attendee whose conversation list will be added to (param_type: String)
     * @param conversation: the Conversation to be added to the Attendee's conversation list (param_type: String)
     * @return void
     */
    public void addConversation(String attendee, String conversation) {
        if (!attendees.containsKey(attendee)) {
            //System.out.println("user with userID " + attendee + " not found");
            return;
        }
        Attendee a = attendees.get(attendee);
        ArrayList<String> conversations = a.getConversations();
        conversations.add(conversation);
        a.setConversations(conversations);
    }

    /**
     * return the list of all contacts that an Attendee connects to
     * @param a: the Attendee whose contact list is returned (param_type: String)
     * @return ArrayList<String> list of contacts
     */
    public ArrayList<String> getMessagableUsers(String a) {
        return attendees.get(a).getContacts();
    }

    /**
     * lookup and return an Attendee by their username
     * @param a: the username to look up an Attendee (param_type: String)
     * @return Attendee attendee
     */
    public Attendee getAttendee(String a) {
        return attendees.get(a);
    }

    /**
     * return the list of all Attendees
     * @return ArrayList<Attendee> Attendees
     */
    public ArrayList<Attendee> getAllAttendees() {
        ArrayList<Attendee> allAttendees = new ArrayList<>();
        for (String username: attendees.keySet()) {
            allAttendees.add(attendees.get(username));
        }
        return allAttendees;
    }

    /**
     * this method checks if the username and password are correct
     *    * check if username exists:
     *      - if no, return false
     *      - if yes, check if the password matches the one corresponding with the username
     * @param username: the username to be checked (param_type: String)
     * @param password: the password to be checked (param_type: String)
     * @return boolean whether username and password are correct
     */
    public boolean checkPassword(String username, String password){
        if (!attendees.containsKey(username)) {
            return false;
        }
        Attendee attendee = attendees.get(username);
        return attendee.getPassword().equals(password);
    }

    /**
     * add an event to an Attendee's list of participating events
     * if the Attendee does not exist, print a message and do nothing!
     * ASSUMPTION: String <event> is valid!  // check in controller?
     * @param attendee: the Attendee whose participating-events list will be added to (param_type: String)
     * @param event: the desired event to be added (param_type: String)
     * @return void
     */
    public void addAttendingEvent(String attendee, String event){
        if (!attendees.containsKey(attendee)) {
            //System.out.println("user with userID " + attendee + " not found");
            return;
        }
        Attendee a = attendees.get(attendee);
        ArrayList<String> participatingEvents = a.getEventsAttending();
        // if <event> not in participatingEvents, add it in. If it's in, then do nothing!
        if (!participatingEvents.contains(event)) {
            participatingEvents.add(event);
        }
        a.setEventsAttending(participatingEvents);
    }

    /**
     * remove an event from an Attendee's list of participating events
     * if the Attendee does not exist, print a message and do nothing!
     * if the event is not in this Attendee's list of participating events, do nothing!
     * ASSUMPTION: String <event> is valid!  // check in controller?
     * @param attendee: the Attendee whose participating-events list will be removed from (param_type: String)
     * @param event: the desired event to be removed (param_type: String)
     * @return void
     */
    public void removeAttendingEvent(String attendee, String event){
        if (!attendees.containsKey(attendee)) {
            //System.out.println("user with userID " + attendee + " not found");
            return;
        }
        Attendee a = attendees.get(attendee);
        ArrayList<String> participatingEvents = a.getEventsAttending();
        // if <event> not in participatingEvents, do nothing!
        // if in, remove it
        participatingEvents.remove(event);
        a.setEventsAttending(participatingEvents);
    }

}