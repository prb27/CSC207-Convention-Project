import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

public class Attendee implements IAttendee {
    private final String username; // disallows changes in username for now
    private final String password; // disallows changes in password for now
    private ArrayList<String> contacts = new ArrayList<>();
    private final Hashtable<String, Integer> received = new Hashtable<>();
    private final Hashtable<String, Integer> sent = new Hashtable<>();

    public Attendee(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private ArrayList<Integer> getMessagesIds(String mess_type) {
        ArrayList<Integer> messagesList = new ArrayList<>();
        Hashtable<String, Integer> messages;
        if (mess_type.equals("received")){
            messages = received;
        } else {
            messages = sent;
        }
        Set<String> users = messages.keySet();
        for (String user : users) {
            messagesList.add(messages.get(user));
        }
        return messagesList;
    }

    public ArrayList<Integer> getReceivedMessagesIds() {
        return getMessagesIds("received");
    }

    public ArrayList<Integer> getSentMessagesIds() {
        return getMessagesIds("sent");
    }

//  This code is for allowing attendees to change their username and/or password
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

    public String getPassword() {
        return password;
    }

    public ArrayList<String> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<String> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String getUserId() {
        return username;
    }

    // wanna make organizer, speaker inherits from attendee?
    // getters and setters for username, password?
    // can attendee remove someone from their contacts list?
    // for now, no changes in username and password allowed?
    // Getter for list of message ids -- both sent and received??
    // List of messages that have been sent to them and that they have sent -- need to store senders/receivers?
    // which stores the senders/receivers of a message? as of now story as dict in Attendee
    // IDs are all strings!
}
