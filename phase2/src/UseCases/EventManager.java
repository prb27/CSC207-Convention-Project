package UseCases;

import Entities.Event;
import Entities.Events.MultiSpeakerEvent;
import Entities.Events.NoSpeakerEvent;
import Entities.Events.SingleSpeakerEvent;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * This is a use-case class that interacts with the event entity, and performs various tasks related to events.
 * This class does the following functionalities:
 * - returns a list of all event names
 * - add an event object
 * - remove an event object
 * - reserve a user for an event
 * - remove a user for an event
 * - get the object of an event
 * - get the fields of an event which include (event name, speaker name, room number, time, attendee list)
 * - displays a hashtable of an event with its information
 * @author aribshaikh
 */
public class EventManager implements Serializable {

    private ArrayList <Event> EventList = new ArrayList<>();


    /**
     * Returns a list of all event objects
     * @return : ArrayList<Entities.Event>
     */

    public ArrayList<Event> getEventList() {

        return EventList;
    }


    /**
     * Returns all the event names of the current list of events
     * @return ArrayList<String></String>
     */
    public ArrayList<String> getEventNamesList(){
        ArrayList<String> eventNamesList = new ArrayList<String>();
        for(Event event: EventList){
            eventNamesList.add(event.getEventName());
        }
        return eventNamesList;
    }
    /** Initiates a new event object, and adds it to the list of events
     *
      * @param eventName: name of event
     * @param roomNumber: roomnumber of event
     * @param speakerName: speakername of event
     * EAE - Entities.Event Already Exist
     * @return String
     */
    public String addEvent(String eventName, String startTime, int duration, String roomNumber, int eventCapacity, ArrayList<String> speakerName){

        ArrayList<String> attendeeList = new ArrayList<>();
        for (Event event: EventList){
            if(event.getEventName().equals(eventName)){
                return "EAE";
            }
        }
        Event newEvent = new Event(eventName, speakerName, startTime, duration, roomNumber, eventCapacity, attendeeList);
        EventList.add(newEvent);
        return "YES";

    }

    /** Removes the event object from the list of events
     *
     * @param eventName: name of event
     */
    public void removeEvent(String eventName){
        if(isEvent(eventName)){
            Event event = getEvent(eventName);
            EventList.remove(event);

        }
    }

    /** Reserves a spot for the attendee of the given event, adds it to the attendeelist
     *
     * @param eventName : name of event
     * @param UserId : userID of attendee
     *
     * @return : "EDE" - Entities.Event Doesn't Exist
     *           "YES" - Request Successful
     */
    public String reserveAttendee(String eventName, String UserId){
        //Need to check if attendee is not already registered for this event
        Event event = getEvent(eventName);
        if(event != null)
        event.getAttendeeList().add(UserId);
        return "YES";

    }
    /**
     * Removes the Entities.User spot from eventList, if user is attending this event
     * @param eventName: name of event
     * @param UserId: UserID of a user who is attending this event
     */
    public void removeAttendee(String eventName, String UserId){

        Event event = getEvent(eventName);
        if(event != null)
        event.getAttendeeList().remove(UserId);

    }

    /**
     * Returns event object
     * @param eventName: name of event
     * @return : Entities.Event
     */
    private Event getEvent(String eventName){

        for(Event event: EventList){
            if(event.getEventName().equals(eventName)) {
                return event;
            }
        }
        return null;

    }

    /**
     * Checks to see if event is valid, and is in the list of events
     * @param eventName: name of event
     * @return : boolean
     */
    public boolean isEvent(String eventName){
        return EventList.contains(getEvent(eventName));
    }

    /**
     * Returns the speaker of event given the event name
     * @param eventName: name of event
     * @return : speakerName (param_type: String)
     */
    public ArrayList<String> getSpeakerEvent(String eventName){
        Event event = getEvent(eventName);
        return event.getSpeakerName();
    }

    /**
     * Returns the time of event given the event name
     * @param eventName: name of event
     * @return : String
     */
    public String getStartTime(String eventName){
        Event event = getEvent(eventName);
        return event.getStartTime();
    }
    public int getDuration(String eventName){
        Event event = getEvent(eventName);
        return event.getDuration();

    }

    /**
     * Returns the room number of event given the event name
     * @param eventName: name of event
     * @return : String
     */
    public String getRoomNumber(String eventName){
        Event event = getEvent(eventName);
        return event.getRoomNumber();
    }

    /**
     * Returns the list of attendees/organizers who are attending given the event name
     * @param eventName : name of event
     * @return : ArrayList<String>
     */
    public ArrayList<String> getAttendeeList(String eventName){
        Event event = getEvent(eventName);
        return event.getAttendeeList();
    }

    /** Returns an arraylist of an event in the order of speaker name, event time, room number
     *
     * @param eventName: title of event
     * @return ArrayList
     */
    public List<String> getEventInfo(String eventName){
        Event event = getEvent(eventName);
        List eventInfo = null;

        if(EventList.contains(event)){
            eventInfo.add(event.getSpeakerName());
            eventInfo.add(event.getStartTime());
            eventInfo.add(event.getDuration());
            eventInfo.add(event.getRoomNumber());
            return eventInfo;
        }
        else{
            return null;
        }


    }

    /**
     * Returns a hashtable of all events; with eventName as the key, and the value as a list of event info
     * @return : Hashtable<String, ArrayList<String>>
     */
    public Hashtable<String, List<String> > getAllEventsWithInfo(){
        Hashtable<String, List<String>> AllEventsWithInfo = new Hashtable<>();

        for(Event event: EventList){
            String eventName = event.getEventName();
            List eventInfo = getEventInfo(eventName);

            if (eventInfo!=null){
                AllEventsWithInfo.put(eventName, eventInfo);
            }


        }
        return AllEventsWithInfo;
    }

    /**
     * Returns an ArrayList of all event titles
     * @return : String
     */
    public ArrayList<String> getAllEventTitles(){

        ArrayList<String> eventTitles = new ArrayList<>();
        for(Event event: EventList){
            eventTitles.add(event.getEventName());
        }
        return eventTitles;
    }
    /**
     * Returns the event capacity
     * @param eventName
     * @return
     */
    public int getEventCapacity(String eventName) {
        Event event = getEvent(eventName);
        return event.getEventCapacity();
    }

    /**
     * Returns the list of empty events
     * @author Khoa Pham
     * @return ArrayList<String> empty events (events without attendees)
     */
    public ArrayList<String> getEmptyEvents() {
        ArrayList<String> emptyEvents = new ArrayList<>();
        for (Event e : EventList) {
            if (e.getAttendeeList().isEmpty()) {
                emptyEvents.add(e.getEventName());
            }
        }
        return emptyEvents;
    }
    public ArrayList<String> getAllowedTimes(){
        ArrayList<String> allowedTimes = new ArrayList<String>();
        allowedTimes.add("9");
        allowedTimes.add("10");
        allowedTimes.add("11");
        allowedTimes.add("12");
        allowedTimes.add("1");
        allowedTimes.add("2");
        allowedTimes.add("3");
        allowedTimes.add("4");
        allowedTimes.add("5");
        return allowedTimes;

    }
}



