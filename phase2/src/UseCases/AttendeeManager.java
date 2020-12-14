package UseCases;

import Entities.Attendee;
import Entities.Organizer;
import Gateways.IAttendeeDatabase;
import Gateways.IOrganizerDatabase;
import org.bson.Document;

import java.io.Serializable;
import java.util.*;

/**
 * this class manages (stores and updates) all Entities.Attendee objects
 * these functionalities include:
 *      - creating new Entities.Attendee object
 *      - allow an Entities.Attendee to connects another Entities.User
 *      - add a new conversation to an Entities.Attendee's list of participating conversations
 *      - get the list of contacts from a given Entities.Attendee
 *      - get an Entities.Attendee given their username
 *      - get all Attendees
 *      - check the password of a given Entities.Attendee
 * @author Khoa Pham
 * @see Attendee
 */
public class AttendeeManager implements Serializable {
    private final Hashtable<String, Attendee> attendees = new Hashtable<>();

    /**
     * create an Entities.Attendee given a pair of username and password
     * if the username is used, return false!
     * else create an Entities.Attendee and update the list of all attendees <attendees> to reflect this creation
     * also returns true in that case!
     * @param username: the username to be assigned to this new Entities.Attendee (param_type: String)
     * @param password: the password to be assigned to this new Entities.Attendee (param_type: String)
     * @return boolean
     */
    public boolean createAttendee(String username, String password) {
        if (attendees.containsKey(username)) {
            return false;
        }
        attendees.put(username, new Attendee(username, password));
        return true;
    }

    /**
     * add an Entities.User (String) to an Entities.Attendee's contact list
     * if the Entities.Attendee does not exist, return an appropriate message
     * ASSUMPTION: String <b> is valid and <a> can add <b> to its contact list!    // check in Controllers.MasterSystem
     * @param a: the Entities.Attendee whose contact list will be added to (param_type: String)
     * @param b: the Entities.User to be added to the Entities.Attendee's contact list (param_type: String)
     * @return String
     * ADE - Entities.Attendee Doesn't Exist
     * No - <b> is already in <a>'s contact list
     * Yes - successfully added <b> to <a>'s contact list
     */
    public String aAddContactB(String a, String b) {
        if (!attendees.containsKey(a)) {
            return "ADE";
        }
        Attendee attendee = attendees.get(a);
        List<String> contacts = attendee.getContacts();
        if (contacts.contains(b)) {
            return "No";
        }
        contacts.add(b);
        attendee.setContacts(contacts);
        return "Yes";
    }

    /**
     * add a conversation to an Entities.Attendee's list of participating conversations
     * if the Entities.Attendee does not exist, return an appropriate message
     * Notice: String <conversation> is valid because it is generated within the program
     * @param attendee: the Entities.Attendee whose conversation list will be added to (param_type: String)
     * @param conversation: the Entities.Conversation to be added to the Entities.Attendee's conversation list (param_type: String)
     * @return String
     * ADE - Entities.Attendee Doesn't Exist
     * No - <conversation> is already in <attendee>'s conversation list
     * Yes - successfully added <conversation> to <attendee>'s conversation list
     */
    public String addConversation(String attendee, String conversation) {
        if (!attendees.containsKey(attendee)) {
            return "ADE";
        }
        Attendee a = attendees.get(attendee);
        List<String> conversations = a.getConversations();
        if (conversations.contains(conversation)) {
            return "No";
        }
        conversations.add(conversation);
        a.setConversations(conversations);
        return "Yes";
    }

    /**
     * return the list of all contacts that an Entities.Attendee connects to
     * @param a: the Entities.Attendee whose contact list is returned (param_type: String)
     * @return List<String> list of contacts
     * empty list if Entities.Attendee doesn't exist
     */
    public List<String> getMessagableUsers(String a) {
        Attendee attendee = attendees.get(a);
        if (attendee == null) {
            return new ArrayList<>();
        }
        return attendee.getContacts();
    }

    /**
     * lookup and return an Entities.Attendee by their username
     * @param a: the username to look up an Entities.Attendee (param_type: String)
     * @return Entities.Attendee attendee
     * null if that Entities.Attendee doesn't exist
     */
    public Attendee getAttendee(String a) {
        return attendees.get(a);
    }

    /**
     * return the list of all Attendees
     * @return List<Entities.Attendee> Attendees
     */
    public List<Attendee> getAllAttendees() {
        List<Attendee> allAttendees = new ArrayList<>();
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
     * add an event to an Entities.Attendee's list of participating events
     * if the Entities.Attendee does not exist, return an appropriate message
     * ASSUMPTION: String <event> is valid!  // check in controller?
     * @param attendee: the Entities.Attendee whose participating-events list will be added to (param_type: String)
     * @param event: the desired event to be added (param_type: String)
     * @return String
     * ADE - Entities.Attendee Doesn't Exist
     * No - <event> is already in <attendee>'s attending events list
     * Yes - successfully added <event> to <attendee>'s attending events list
     */
    public String addAttendingEvent(String attendee, String event){
        if (!attendees.containsKey(attendee)) {
            //System.out.println("user with userID " + attendee + " not found");
            return "ADE";
        }
        Attendee a = attendees.get(attendee);
        List<String> participatingEvents = a.getEventsAttending();
        // if <event> not in participatingEvents, add it in. If it's in, then do nothing!
        if (participatingEvents.contains(event)) {
            return "No";
        }
        participatingEvents.add(event);
        a.setEventsAttending(participatingEvents);
        return "Yes";
    }

    /**
     * remove an event from an Entities.Attendee's list of participating events
     * if the Entities.Attendee does not exist, return an appropriate message
     * if the event is not in this Entities.Attendee's list of participating events, do nothing!
     * ASSUMPTION: String <event> is valid!  // check in controller?
     * @param attendee: the Entities.Attendee whose participating-events list will be removed from (param_type: String)
     * @param event: the desired event to be removed (param_type: String)
     * @return String
     * ADE - Entities.Attendee Doesn't Exist
     * Yes - <attendee>'s attending events list now doesn't contain <event>
     */
    public String removeAttendingEvent(String attendee, String event){
        if (!attendees.containsKey(attendee)) {
            //System.out.println("user with userID " + attendee + " not found");
            return "ADE";
        }
        Attendee a = attendees.get(attendee);
        List<String> participatingEvents = a.getEventsAttending();
        // if <event> not in participatingEvents, do nothing!
        // if in, remove it
        participatingEvents.remove(event);
        a.setEventsAttending(participatingEvents);
        return "Yes";
    }

    /**
     * check if an username is an Entities.Attendee
     * @param username: the username to be checked if it's an Entities.Attendee's username (param_type: String)
     * @return bool
     */
    public boolean isAttendee(String username) {
        return attendees.containsKey(username);
    }

    /**
     * check if an Entities.Attendee is attending an event
     * if the Entities.Attendee does not exist, return false!
     * ASSUMPTION: String <event> is valid!  // check in controller?
     * @param attendee: the Entities.Attendee (param_type: String)
     * @param event: the desired event (param_type: String)
     * @return boolean
     * false -- user with userID <attendee> not found or Entities.Attendee is Not attending <event>
     * true -- <attendee> is attending <event>
     */
    public boolean isAttending(String attendee, String event) {
        if (!attendees.containsKey(attendee)) {
            return false;
        }
        return attendees.get(attendee).getEventsAttending().contains(event);
    }

    /**
     * return the list of all events that an Entities.Attendee is participating
     * @param attendee: the username of the Entities.Attendee to get the according list of participating events
     * @return List<String> participating events
     * empty list if Entities.Attendee doesn't exist
     */
    public List<String> getEventsAttending(String attendee) {
        Attendee a = attendees.get(attendee);
        if (a == null) {
            return new ArrayList<>();
        }
        return a.getEventsAttending();
    }

    /**
     * return the list of all conversations that an Entities.Attendee is participating
     * @param attendee: the username of the Entities.Attendee to get the according list of participating conversations
     * @return List<String> conversations' id
     * empty list if Entities.Attendee doesn't exist
     */
    public List<String> getConversations(String attendee) {
        Attendee a = attendees.get(attendee);
        if (a == null) {
            return new ArrayList<>();
        }
        return a.getConversations();
    }

    /**
     * return the list of all Attendees' ids
     * @return List<String> ids
     */
    public List<String> getAllAttendeeIds() {
        return Collections.list(attendees.keys());
    }


    /**
     * load the relevant data from the database and store the relevant data inside relevant entities which are then
     * stored inside a data structure
     *
     */
    IAttendeeDatabase attendeeDatabase;
    public AttendeeManager(IAttendeeDatabase attendeeDatabase){
        this.attendeeDatabase = attendeeDatabase;
    }


    public void loadFromDatabase() {
        List<Map<String, List<String>>> listOfAttendees = attendeeDatabase.getAttendees();

        for(Map<String, List<String>> attendee: listOfAttendees){
            List<String> listOfEventsAttending = attendee.get("eventsAttending");
            List<String> listOfContacts = attendee.get("listOfContacts");
            List<String> listOfConversations = attendee.get("listOfConversations");
            String username = attendee.get("credentials").get(0);
            String password = attendee.get("credentials").get(1);
            Attendee newAttendee =  new Attendee(username, password);
            newAttendee.setEventsAttending(listOfEventsAttending);
            newAttendee.setContacts(listOfContacts);
            newAttendee.setConversations(listOfConversations);
            attendees.put(username, newAttendee);
        }

    }

    public List<Map<String, List<String>>> saveToDatabase() {



    }

}