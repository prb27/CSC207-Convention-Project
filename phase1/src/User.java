import java.io.Serializable;
import java.util.ArrayList;

/**
 * this class stores all info relating to an User
 * and provides getters to extract those info
 * User is an abstract class -- no instantiation is allowed
 * User serves as a parent for a few other classes
 * @author Khoa Pham
 * A few notes to consider:
 *    * disallows changes in username, password for now
 *    * all IDs are stored as strings!
 *    * getters and setters for username, password?
 *    * can attendee remove someone from their contacts list?
 *    * can attendees delete their account?
 */
public abstract class User implements Serializable {
    private final String username; // disallows changes in username for now
    private final String password; // disallows changes in password for now
    private ArrayList<String> contacts = new ArrayList<>();
    private ArrayList<String> conversations = new ArrayList<>();

    /**
     * a constructor for subclasses to call
     * creating a User object is DISALLOWED!
     * @param username: the username of this User
     * @param password: the password of this User
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * update the list of conversation that this User participates in
     * @param conversations: the new list of conversations to update to (param_type: ArrayList<String>)
     */
    public void setConversations(ArrayList<String> conversations) {
        this.conversations = conversations;
    }

    /**
     * return the list of conversations this User participates in
     * @return ArrayList<String> of conversations
     */
    public ArrayList<String> getConversations() {
        return conversations;
    }



//  This code is for allowing users to change their username and/or password
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

    /**
     * return the password used by this User
     * @return String password
     */
    public String getPassword() {
        return password;
    }

    /**
     * return the list of contacts this User has already connected to
     * @return ArrayList<String> contacts
     */
    public ArrayList<String> getContacts() {
        return contacts;
    }

    /**
     * return the username (userId) of this User
     * @return String username
     */
    public String getUserId() {
        return username;
    }


    /**
     * update the list of contacts that this User connects to
     * @param contacts: the new list of conversations to update to (param_type: ArrayList<String>)
     */
    public void setContacts(ArrayList<String> contacts) {
        this.contacts = contacts;
    }


}
