package Controllers;

import Presenters.AttendeeMenuPresenter;
import UseCases.AttendeeManager;
import UseCases.EventManager;

import java.util.ArrayList;

public class AttendeeEventMenuController {

    private AttendeeManager attendeeManager;
    private EventManager eventManager;

    public AttendeeEventMenuController(AttendeeManager attendeeManager, EventManager eventManager){
        this.attendeeManager = attendeeManager;
        this.eventManager = eventManager;
    }

    public ArrayList<String> getListOfAllEvents(){
        return eventManager.getAllEventTitles();
    }

    public ArrayList<String> getListOfAllAttendedEvents(String attendeeUsername){
        return attendeeManager.getEventsAttending(attendeeUsername);
    }

}
