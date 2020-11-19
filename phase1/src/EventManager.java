import java.util.ArrayList;

/**
 * This is a use-case class that interacts with the event entity, and performs various tasks related to events.
 * @author aribshaikh
 */
public class EventManager {

    private ArrayList <Event> EventList = new ArrayList<>();

    public ArrayList<Event> getEventList() {
        return EventList;
    }

    /** Initiates a new event object, and adds it to the list of events
     *
     * @param eventName: name of event
     * @param eventTime: time of event
     * @param roomNumber: roomnumber of event
     * @param speakerName: speakername of event
     * @return String
     */
    public String addEvent(String eventName, String eventTime, String roomNumber, String speakerName){

        ArrayList<String> attendeeList = new ArrayList<>();
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
     * @param eventName: name of event
     * @param UserId: userID of attendee
     * @return : String
     */
    public String reserveAttendee(String eventName, String UserId, ArrayList<String> eventsAttending){
        //Need to check if attendee is not already registered for event at this time
        Event event = getEvent(eventName);
        if (event.getAttendeeList().size() <2 && event!=null && !eventsAttending.contains(eventName)){
            event.getAttendeeList().add(UserId);
            return "YES";

        }
        else if(event.getAttendeeList().size() >=2){
            return("FCE");
        }
        else if(event == null){
            return("EDE");
        }
        return null;

    }
    /**
     * Removes the attendee spot from eventList
     * @param eventName: name of event
     * @param UserId: UserID of attendee
     * @return String
     */
    public String removeAttendee(String eventName, String UserId, ArrayList<String> eventsAttending){
        Event event = getEvent(eventName);
        if(event.getAttendeeList().contains(UserId) && event!=null){
            event.getAttendeeList().remove(UserId);
            eventsAttending.remove(eventName);
            return "YES";

        }
        else{
            return("ADE");
        }

    }

    /**
     * Returns event object
     * @param eventName: name of event
     * @return : Event
     */
    public Event getEvent(String eventName){
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
        if (EventList.contains(eventName)) {

            return true;
        }
        else{
            return false;
        }
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

}