package Entities.Events;

import java.util.ArrayList;
import java.util.Collections;

public class SingleSpeakerEvent extends AbstractEvent{
    /**
     * A constructor to create the event object
     *
     * @param eventName    : name of event
     * @param speakerName  : name of speaker
     * @param eventTime    : time of event
     * @param roomNumber   : room number
     * @param attendeeList : list of attendees
     */
    private ArrayList<String> speakerName;

    public SingleSpeakerEvent(String eventName, ArrayList<String> speakerName, String eventTime, String roomNumber, ArrayList<String> attendeeList) {
        super(eventName, eventTime, roomNumber, attendeeList);
        this.speakerName = speakerName;
    }

    @Override
    public ArrayList<String> getSpeakerNames() {
        return speakerName;
    }

    @Override
    public void setSpeakerNames(ArrayList<String> speakerName) {
        this.speakerName = speakerName;
    }
}
