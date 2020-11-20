import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
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
public class AttendeeManager implements Serializable {
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
     * if the Attendee does not exist, return an appropriate message
     * ASSUMPTION: String <b> is valid and <a> can add <b> to its contact list!    // check in MasterSystem
     * @param a: the Attendee whose contact list will be added to (param_type: String)
     * @param b: the User to be added to the Attendee's contact list (param_type: String)
     * @return String
     * ADE - Attendee Doesn't Exist
     * No - <b> is already in <a>'s contact list
     * Yes - successfully added <b> to <a>'s contact list
     */
    public String aAddContactB(String a, String b) {
        if (!attendees.containsKey(a)) {
            return "ADE";
        }
        Attendee attendee = attendees.get(a);
        ArrayList<String> contacts = attendee.getContacts();
        if (contacts.contains(b)) {
            return "No";
        }
        contacts.add(b);
        attendee.setContacts(contacts);
        return "Yes";
    }

    /**
     * add a conversation to an Attendee's list of participating conversations
     * if the Attendee does not exist, return an appropriate message
     * Notice: String <conversation> is valid because it is generated within the program
     * @param attendee: the Attendee whose conversation list will be added to (param_type: String)
     * @param conversation: the Conversation to be added to the Attendee's conversation list (param_type: String)
     * @return String
     * ADE - Attendee Doesn't Exist
     * No - <conversation> is already in <attendee>'s conversation list
     * Yes - successfully added <conversation> to <attendee>'s conversation list
     */
    public String addConversation(String attendee, String conversation) {
        if (!attendees.containsKey(attendee)) {
            //System.out.println("user with userID " + attendee + " not found");
            return "ADE";
        }
        Attendee a = attendees.get(attendee);
        ArrayList<String> conversations = a.getConversations();
        if (conversations.contains(conversation)) {
            return "No";
        }
        conversations.add(conversation);
        a.setConversations(conversations);
        return "Yes";
    }

    /**
     * return the list of all contacts that an Attendee connects to
     * @param a: the Attendee whose contact list is returned (param_type: String)
     * @return ArrayList<String> list of contacts
     * empty list if Attendee doesn't exist
     */
    public ArrayList<String> getMessagableUsers(String a) {
        Attendee attendee = attendees.get(a);
        if (attendee == null) {
            return new ArrayList<>();
        }
        return attendee.getContacts();
    }

    /**
     * lookup and return an Attendee by their username
     * @param a: the username to look up an Attendee (param_type: String)
     * @return Attendee attendee
     * null if that Attendee doesn't exist
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
     * if the Attendee does not exist, return an appropriate message
     * ASSUMPTION: String <event> is valid!  // check in controller?
     * @param attendee: the Attendee whose participating-events list will be added to (param_type: String)
     * @param event: the desired event to be added (param_type: String)
     * @return String
     * ADE - Attendee Doesn't Exist
     * No - <event> is already in <attendee>'s attending events list
     * Yes - successfully added <event> to <attendee>'s attending events list
     */
    public String addAttendingEvent(String attendee, String event){
        if (!attendees.containsKey(attendee)) {
            //System.out.println("user with userID " + attendee + " not found");
            return "ADE";
        }
        Attendee a = attendees.get(attendee);
        ArrayList<String> participatingEvents = a.getEventsAttending();
        // if <event> not in participatingEvents, add it in. If it's in, then do nothing!
        if (participatingEvents.contains(event)) {
            return "No";
        }
        participatingEvents.add(event);
        a.setEventsAttending(participatingEvents);
        return "Yes";
    }

    /**
     * remove an event from an Attendee's list of participating events
     * if the Attendee does not exist, return an appropriate message
     * if the event is not in this Attendee's list of participating events, do nothing!
     * ASSUMPTION: String <event> is valid!  // check in controller?
     * @param attendee: the Attendee whose participating-events list will be removed from (param_type: String)
     * @param event: the desired event to be removed (param_type: String)
     * @return String
     * ADE - Attendee Doesn't Exist
     * Yes - <attendee>'s attending events list now doesn't contain <event>
     */
    public String removeAttendingEvent(String attendee, String event){
        if (!attendees.containsKey(attendee)) {
            //System.out.println("user with userID " + attendee + " not found");
            return "ADE";
        }
        Attendee a = attendees.get(attendee);
        ArrayList<String> participatingEvents = a.getEventsAttending();
        // if <event> not in participatingEvents, do nothing!
        // if in, remove it
        participatingEvents.remove(event);
        a.setEventsAttending(participatingEvents);
        return "Yes";
    }

    /**
     * check if an username is an Attendee
     * @param username: the username to be checked if it's an Attendee's username (param_type: String)
     * @return bool
     */
    public boolean isAttendee(String username) {
        return attendees.containsKey(username);
    }

    /**
     * check if an Attendee is attending an event
     * if the Attendee does not exist, return false!
     * ASSUMPTION: String <event> is valid!  // check in controller?
     * @param attendee: the Attendee (param_type: String)
     * @param event: the desired event (param_type: String)
     * @return boolean
     * false -- user with userID <attendee> not found or Attendee is Not attending <event>
     * true -- <attendee> is attending <event>
     */
    public boolean isAttending(String attendee, String event) {
        if (!attendees.containsKey(attendee)) {
            return false;
        }
        return attendees.get(attendee).getEventsAttending().contains(event);
    }

    /**
     * return the list of all events that an Attendee is participating
     * @param attendee: the username of the Attendee to get the according list of participating events
     * @return ArrayList<String> participating events
     * empty list if Attendee doesn't exist
     */
    public ArrayList<String> getEventsAttending(String attendee) {
        Attendee a = attendees.get(attendee);
        if (a == null) {
            return new ArrayList<>();
        }
        return a.getEventsAttending();
    }

    /**
     * return the list of all conversations that an Attendee is participating
     * @param attendee: the username of the Attendee to get the according list of participating conversations
     * @return ArrayList<String> conversations' id
     * empty list if Attendee doesn't exist
     */
    public ArrayList<String> getConversations(String attendee) {
        Attendee a = attendees.get(attendee);
        if (a == null) {
            return new ArrayList<>();
        }
        return a.getConversations();
    }

    /**
     * return the list of all Attendees' ids
     * @return ArrayList<String> ids
     */
    public ArrayList<String> getAllAttendeeIds() {
        return Collections.list(attendees.keys());
    }

}