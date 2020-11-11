import java.util.ArrayList;

public class Event {
    private String eventName;
    private String speakerName;
    private double eventTime;
    private int roomNumber;
    private ArrayList<User> attendeeList;

    public Event(String eventName, String speakerName, double eventTime, int roomNumber, ArrayList<User>attendeeList){
        this.eventName = eventName;
        this.speakerName = speakerName;
        this.eventTime = eventTime;
        this.roomNumber = roomNumber;
        this.attendeeList = attendeeList;

    }

    public String getEventName() {
        return eventName;
    }

    public String getSpeakerName() {
        return speakerName;
    }

    public double getEventTime() {
        return eventTime;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public ArrayList<User> getAttendeeList() {
        return attendeeList;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setSpeakerName(String speakerName) {
        this.speakerName = speakerName;
    }

    public void setEventTime(double eventTime){
        this.eventTime = eventTime;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }


}
