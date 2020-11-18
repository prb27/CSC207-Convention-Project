import java.util.ArrayList;

public class EventManager {

    private ArrayList <Event> EventList = new ArrayList<>();

    public ArrayList<Event> getEventList() {
        return EventList;
    }

    public String addEvent(String eventName, String eventTime, String roomNumber, String speakerName){

        ArrayList<String> attendeeList = new ArrayList<>();
        Event newEvent = new Event(eventName, speakerName, eventTime, roomNumber,attendeeList);
        EventList.add(newEvent);
        return "YES";
        }



    public void removeEvent(String eventName){
        //Need to still remove it from attendee's list of events
        if(isEvent(eventName)){
            Event event = getEvent(eventName);
            EventList.remove(event);

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
    public String getSpeakerEvent(String eventName){
        Event event = getEvent(eventName);
        return event.getSpeakerName();
    }
    public String getEventTime(String eventName){
        Event event = getEvent(eventName);
        return event.getEventTime();
    }
    public String getRoomNumber(String eventName){
        Event event = getEvent(eventName);
        return event.getRoomNumber();
    }

}





