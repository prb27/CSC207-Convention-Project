package Entities;

import java.io.Serializable;
import java.util.List;

/**
 * This is the Entities.Event entity class, creates an Entities.Event object and initiates all values of event. Responsible for the getters and setters of event.
 * @author aribshaikh
 */
public class Event implements Serializable {
    private String eventName;
    private List<String> speakerName;
    private String startTime;
    private int duration;
    private String roomNumber;
    private int eventCapacity;
    private final List<String> attendeeList;

    /**
     * A constructor to create the event object
     * @param eventName: name of event
     * @param speakerName: name of speaker
     * @param roomNumber: room number
     * @param attendeeList: list of attendees
     */
    public Event(String eventName, List<String> speakerName, String startTime, int duration, String roomNumber, int eventCapacity, List<String>attendeeList){
        this.eventName = eventName;
        this.speakerName = speakerName;
        this.startTime = startTime;
        this.duration = duration;
        this.roomNumber = roomNumber;
        this.eventCapacity = eventCapacity;
        this.attendeeList = attendeeList;

    }

    /**
     * gets the name of event
     * @return eventName
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * returns the name of speaker
     * @return speakerName
     */
    public List<String> getSpeakerName() {
        return speakerName;
    }

    /**
     * returns the time of event
     * @return eventTime
     */
    public String getStartTime() {
        return startTime;
    }

    public int getDuration() {
        return duration;
    }

    /**
     * returns the room number of event
     * @return roomNumber
     */
    public String getRoomNumber() {
        return roomNumber;
    }

    public int getEventCapacity() {
        return eventCapacity;
    }

    /**
     * returns the list of attendees
     * @return attendeeList
     */
    public List<String> getAttendeeList() {
        return attendeeList;
    }

    /**
     * Changes value of the name of event
     * @param eventName : name of event
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * Changes value for the speaker name of event
     * @param speakerName
     */
    public void setSpeakerName(List<String> speakerName) {
        this.speakerName = speakerName;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Changes value for the room number
     * @param roomNumber
     */
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setEventCapacity(int eventCapacity) {
        this.eventCapacity = eventCapacity;
    }
}
