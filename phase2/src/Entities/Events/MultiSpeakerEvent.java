package Entities.Events;

import java.util.ArrayList;

public class MultiSpeakerEvent extends AbstractEvent{
    /**
     * A constructor to create the event object
     *
     * @param eventName    : name of event
     * @param speakerName  : name of speaker
     * @param eventTime    : time of event
     * @param roomNumber   : room number
     * @param attendeeList : list of attendees
     */

    private ArrayList<String> speakerNames;
    public MultiSpeakerEvent(String eventName, ArrayList<String> speakerNames, String eventTime, String roomNumber, ArrayList<String> attendeeList) {
        super(eventName, eventTime, roomNumber, attendeeList);
        this.speakerNames = speakerNames;
    }

    @Override
    public ArrayList<String> getSpeakerNames() {
        return speakerNames;
    }

    @Override
    public void setSpeakerNames(ArrayList<String> speakerNames) {
        this.speakerNames = speakerNames;
    }
}
