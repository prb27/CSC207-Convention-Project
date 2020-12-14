package Entities.Events;

import java.util.ArrayList;

public class MultiSpeakerEvent extends AbstractEvent{

    private ArrayList<String> speakerNames;

    /**
     * A constructor to create the event object
     *
     * @param eventName    : name of event
     * @param speakerNames : names of speakers
     * @param eventTime    : time of event
     * @param roomNumber   : room number
     * @param attendeeList : list of attendees
     */
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
