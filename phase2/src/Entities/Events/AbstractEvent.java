package Entities.Events;

import java.util.ArrayList;

public abstract class AbstractEvent {
    private String eventName;
    private String eventTime;
    private String roomNumber;
    private final ArrayList<String> attendeeList;

    /**
     * A constructor to create the event object
     * @param eventName: name of event
     * @param eventTime: time of event
     * @param roomNumber: room number
     * @param attendeeList: list of attendees
     */
    public AbstractEvent(String eventName, String eventTime, String roomNumber, ArrayList<String>attendeeList){
        this.eventName = eventName;
        this.eventTime = eventTime;
        this.roomNumber = roomNumber;
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
    public abstract ArrayList<String> getSpeakerNames();

    /**
     * returns the time of event
     * @return eventTime
     */
    public String getEventTime() {
        return eventTime;
    }

    /**
     * returns the room number of event
     * @return roomNumber
     */
    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     * returns the list of attendees
     * @return attendeeList
     */
    public ArrayList<String> getAttendeeList() {
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
     * @param speakerNames
     */
    public abstract void setSpeakerNames(ArrayList<String> speakerNames);

    /**
     * Changes value for the event time
     * @param eventTime
     */
    public void setEventTime(String eventTime){
        this.eventTime = eventTime;
    }

    /**
     * Changes value for the room number
     * @param roomNumber
     */
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }


}

