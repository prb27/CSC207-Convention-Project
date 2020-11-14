import java.util.ArrayList;

public class Event {
    private String eventName;
    private String speakerName;
    private String eventTime;
    private String roomNumber;
    private ArrayList<User> attendeeList;

    public Event(String eventName, String speakerName, String eventTime, String roomNumber, ArrayList<User>attendeeList){
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

    public String getEventTime() {
        return eventTime;
    }

    public String getRoomNumber() {
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

    public void setEventTime(String eventTime){
        this.eventTime = eventTime;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }


}
