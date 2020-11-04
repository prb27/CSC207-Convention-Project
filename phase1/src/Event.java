import java.util.ArrayList;

public class Event {
    private double eventTime;
    private ArrayList<Event> attendeeList;
    private String speakerName;

    public Event(String speakerName, double eventTime, ArrayList<Event>attendeeList){
        this.speakerName = speakerName;
        this.eventTime = eventTime;
        this.attendeeList = attendeeList;

    }

    public String getSpeakerName() {
        return speakerName;
    }

    public double getEventTime() {
        return eventTime;
    }

    public ArrayList<Event> getAttendeeList() {
        return attendeeList;
    }
}
