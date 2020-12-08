package Controllers;

import Presenters.AttendeeMenuPresenter;
import UseCases.AttendeeManager;
import UseCases.EventManager;

import java.util.List;
import java.util.Hashtable;

public class AttendeeEventMenuController {

    private AttendeeManager attendeeManager;
    private EventManager eventManager;

    public AttendeeEventMenuController(AttendeeManager attendeeManager, EventManager eventManager){
        this.attendeeManager = attendeeManager;
        this.eventManager = eventManager;
    }

    public Hashtable<String, List<String>> seeAttendableEvents(String attendee) {
        List<String> eventIdsAttending = attendeeManager.getEventsAttending(attendee);
        List<String> eventIdsAll = eventManager.getEventNamesList();
        Hashtable<String, List<String>> eventsAttendable = new Hashtable<>();
        for (String eventId : eventIdsAll) {
            if (!eventIdsAttending.contains(eventId)) {
                eventsAttendable.put(eventId, eventManager.getEventInfo(eventId));
            }
        }
        return eventsAttendable;
    }

    public List<String> getListOfAllAttendedEvents(String attendeeUsername){
        return attendeeManager.getEventsAttending(attendeeUsername);
    }


}
