package Controllers;

import Presenters.AttendeeMenuPresenter;
import UseCases.AttendeeManager;
import UseCases.EventManager;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class AttendeeEventMenuController {

    private AttendeeManager attendeeManager;
    private EventManager eventManager;

    public AttendeeEventMenuController(AttendeeManager attendeeManager, EventManager eventManager){
        this.attendeeManager = attendeeManager;
        this.eventManager = eventManager;
    }

    public Hashtable<String, List<String>> seeAttendableEvents(String attendee) {
        ArrayList<String> eventIdsAttending = attendeeManager.getEventsAttending(attendee);
        ArrayList<String> eventIdsAll = eventManager.getEventNamesList();
        Hashtable<String, List<String>> eventsAttendable = new Hashtable<>();
        for (String eventId : eventIdsAll) {
            if (!eventIdsAttending.contains(eventId)) {
                eventsAttendable.put(eventId, eventManager.getEventInfo(eventId));
            }
        }
        return eventsAttendable;
    }

    public ArrayList<String> getListOfAllAttendedEvents(String attendeeUsername){
        return attendeeManager.getEventsAttending(attendeeUsername);
    }


}
