package Entities.Events;

import java.util.List;

public class NoSpeakerEvent extends AbstractEvent{
    /**
     * A constructor to create the event object
     *
     * @param eventName    : name of event
     * @param eventTime    : time of event
     * @param roomNumber   : room number
     * @param attendeeList : list of attendees
     */
    public NoSpeakerEvent(String eventName, String eventTime, String roomNumber, List<String> attendeeList) {
        super(eventName, eventTime, roomNumber, attendeeList);
    }

    @Override
    public List<String> getSpeakerNames() {
        return null;
    }

    @Override
    public void setSpeakerNames(List<String> speakerNames) {
        return;
    }


}
