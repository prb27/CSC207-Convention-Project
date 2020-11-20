import java.io.Serializable;
import java.util.ArrayList;

/**
 * this class stores all info relating to an Attendee
 * and provides getters to extract those info
 * Attendee is a subclass of User
 * @author Khoa Pham
 * @see User
 * A few notes to consider:
 *    * disallows changes in username, password for now
 */
public class Attendee extends User implements Serializable {
    private ArrayList<String> eventsAttending = new ArrayList<>();

    /**
     * a constructor to create an Attendee object
     *
     * @param username: the username of this Attendee
     * @param password: the password of this Attendee
     * @return an Attendee object
     */
    public Attendee(String username, String password) {
        super(username, password);
    }

    /**
     * return the list of all events that this Attendee is participating
     * @return ArrayList<String> participating events
     */
    public ArrayList<String> getEventsAttending() {
        return eventsAttending;
    }

    /**
     * update the list of all events that this Attendee is participating
     * @param eventsAttending: the new list of all participating events (param_type: ArrayList<String>)
     * @return void
     */
    public void setEventsAttending(ArrayList<String> eventsAttending) {
        this.eventsAttending = eventsAttending;
    }

}
