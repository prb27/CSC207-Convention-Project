import java.util.ArrayList;
import java.util.Hashtable;

/**
 * This is a use-case class that interacts with the event entity, and performs various tasks related to events.
 * @author aribshaikh
 */
public class EventManager {

    private ArrayList <Event> EventList = new ArrayList<>();

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
     * @param eventTime: time of event
     * @param roomNumber: roomnumber of event
     * @param speakerName: speakername of event
     * EAE - Event Already Exist
     * @return String
     */
    public String addEvent(String eventName, String eventTime, String roomNumber, String speakerName){

        ArrayList<String> attendeeList = new ArrayList<>();
        for (Event event: EventList){
            if(event.getEventName().equals(eventName)){
                return "EAE";
            }
        }
        Event newEvent = new Event(eventName, speakerName, eventTime, roomNumber,attendeeList);
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
     * EDE - Event Doesn't Exist
     * @return : String
     */
    public String reserveAttendee(String eventName, String UserId){
        //Need to check if attendee is not already registered for this event
        Event event = getEvent(eventName);
        if(event != null)
        event.getAttendeeList().add(UserId);
        return "YES";

    }
    /**
     * Removes the User spot from eventList, if user is attending this event
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
     * @return : Event
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
     * @return : String
     */
    public String getSpeakerEvent(String eventName){
        Event event = getEvent(eventName);
        return event.getSpeakerName();
    }

    /**
     * Returns the time of event given the event name
     * @param eventName: name of event
     * @return : String
     */
    public String getEventTime(String eventName){
        Event event = getEvent(eventName);
        return event.getEventTime();
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
    public ArrayList<String> getEventInfo(String eventName){
        Event event = getEvent(eventName);
        ArrayList<String> eventInfo = new ArrayList<String>();

        if(EventList.contains(event)){
            eventInfo.add(event.getSpeakerName());
            eventInfo.add(event.getEventTime());
            eventInfo.add(event.getRoomNumber());
            return eventInfo;
        }
        else{
            return null;
        }


    }

    /**
     * Returns a hashtable of the hashtable of all events
     * @return :
     */
    public Hashtable<String, ArrayList<String> > getAllEventsWithInfo(){
        Hashtable<String, ArrayList<String>> AllEventsWithInfo = new Hashtable<>();

        for(Event event: EventList){
            String eventName = event.getEventName();
            ArrayList<String> eventInfo = getEventInfo(eventName);

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

}



