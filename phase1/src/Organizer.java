import java.io.Serializable;
import java.util.ArrayList;

public class Organizer extends User implements Serializable {

    private ArrayList<String> eventsAttending;

    public Organizer(String username, String password){

        super(username, password);
        eventsAttending = new ArrayList<>();

    }

    public ArrayList<String> getEventsAttending() {
        return eventsAttending;
    }

    public void setEventsAttending(ArrayList<String> eventsAttending) {
        this.eventsAttending = eventsAttending;
    }

}