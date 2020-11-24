package Entities.Events;

import java.util.ArrayList;

public class NoSpeakerEvent extends AbstractEvent{
    /**
     * A constructor to create the event object
     *
     * @param eventName    : name of event
     * @param eventTime    : time of event
     * @param roomNumber   : room number
     * @param attendeeList : list of attendees
     */
    public NoSpeakerEvent(String eventName, String eventTime, String roomNumber, ArrayList<String> attendeeList) {
        super(eventName, eventTime, roomNumber, attendeeList);
    }

    @Override
    public ArrayList<String> getSpeakerNames() {
        return null;
    }

    @Override
    public void setSpeakerNames(ArrayList<String> speakerNames) {
        return;
    }


}
