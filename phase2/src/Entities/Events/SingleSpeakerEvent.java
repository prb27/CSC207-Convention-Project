package Entities.Events;

import java.util.List;

public class SingleSpeakerEvent extends AbstractEvent{

    private List<String> speakerName;

    /**
     * A constructor to create the event object
     *
     * @param eventName    : name of event
     * @param speakerName  : name of speaker
     * @param eventTime    : time of event
     * @param roomNumber   : room number
     * @param attendeeList : list of attendees
     */
    public SingleSpeakerEvent(String eventName, List<String> speakerName, String eventTime, String roomNumber, List<String> attendeeList) {
        super(eventName, eventTime, roomNumber, attendeeList);
        this.speakerName = speakerName;
    }

    @Override
    public List<String> getSpeakerNames() {
        return speakerName;
    }

    @Override
    public void setSpeakerNames(List<String> speakerName) {
        this.speakerName = speakerName;
    }
}
