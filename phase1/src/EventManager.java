import java.util.ArrayList;
import java.util.HashMap;

public class EventManager {

    private ArrayList <Event> EventList = new ArrayList<>();

    public ArrayList<Event> getEventList() {
        return EventList;
    }

    public String addEvent(String eventName, String eventTime, String roomNumber, ArrayList<String>attendeeList, String speakerName, HashMap<String, String> listOfTalks){
        //Will use the following of Speakermanager object is passed
        //HashMap<String, String> listOfTalks = null;
        //listOfTalks = speaker.getListOfTalks();

        boolean trigger = true;
        if (listOfTalks.containsKey(eventTime)){
                return("STC");

            }

        else{
            for (Event event: EventList ){
                if(event.getRoomNumber().equals(roomNumber) && event.getEventTime().equals(eventTime)){
                    trigger = false;
                    return "EC";
                }
            }
            if (trigger){
                Event newEvent = new Event(eventName, speakerName, eventTime, roomNumber, attendeeList);
                EventList.add(newEvent);
                listOfTalks.put(eventTime, eventName);
                return "YES";
            }
        }
        return null;


    }

    public String removeEvent(String eventName, HashMap<String, String> listOfTalks ){
        //Need to still remove it from attendee's list of events
        Event event = getEvent(eventName);
        if (EventList.contains(event) && event !=null){
            EventList.remove(event);
            listOfTalks.remove(event.getEventTime(),event.getSpeakerName());
            return "YES";
        }
        else{
            return("EDE");
        }
    }

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

    public Event getEvent(String eventName){

        for(Event event: EventList){
            if(event.getEventName().equals(eventName)) {
                return event;
            }
        }
        return null;

    }
    public boolean isEvent(String eventName){
        if (EventList.contains(eventName)) {

            return true;
        }
        else{
            return false;
        }
    }

}





