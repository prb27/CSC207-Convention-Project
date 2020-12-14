package Entities.Events;

import java.util.List;

public class MultiSpeakerEvent extends AbstractEvent{

    private List<String> speakerNames;

    /**
     * A constructor to create the event object
     *
     * @param eventName    : name of event
     * @param speakerNames : names of speakers
     * @param eventTime    : time of event
     * @param roomNumber   : room number
     * @param attendeeList : list of attendees
     */
    public MultiSpeakerEvent(String eventName, List<String> speakerNames, String eventTime, String roomNumber, List<String> attendeeList) {
        super(eventName, eventTime, roomNumber, attendeeList);
        this.speakerNames = speakerNames;
    }

    @Override
    public List<String> getSpeakerNames() {
        return speakerNames;
    }

    @Override
    public void setSpeakerNames(List<String> speakerNames) {
        this.speakerNames = speakerNames;
    }
}
