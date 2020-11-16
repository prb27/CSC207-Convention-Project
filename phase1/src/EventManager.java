import java.util.ArrayList;
import java.util.HashMap;

public class EventManager {

    private ArrayList <Event> EventList = new ArrayList<>();

    public ArrayList<Event> getEventList() {
        return EventList;
    }

    public boolean addEvent(String eventName, String eventTime, String roomNumber, ArrayList<String>attendeeList, String speakerName, HashMap<String, String> listOfTalks){
        //Will use the following of Speakermanager object is passed
        //HashMap<String, String> listOfTalks = null;
        //listOfTalks = speaker.getListOfTalks();

        boolean trigger = true;
        boolean check = false;
        if (listOfTalks.containsKey(eventTime)){
                System.out.println("Speaker time conflict; cannot reserve another talk");

            }

        else{
            for (Event event: EventList ){
                if(event.getRoomNumber().equals(roomNumber) && event.getEventTime().equals(eventTime)){
                    trigger = false;
                }
            }
            if (trigger){
                Event newEvent = new Event(eventName, speakerName, eventTime, roomNumber, attendeeList);
                EventList.add(newEvent);
                listOfTalks.put(eventTime, eventName);
                check = true;
            }
        }
        return check;


    }

    public boolean removeEvent(Event event, HashMap<String, String> listOfTalks ){
        //Need to still remove it from attendee's list of events
        boolean check = false;
        if (EventList.contains(event)){
            EventList.remove(event);
            listOfTalks.remove(event.getEventTime(),event.getSpeakerName());
            check = true;
        }
        else{
            System.out.println("Event does not exist; cannot remove event");
        }
        return check;
    }

    public boolean reserveAttendee(Event event, Attendee attendee){
        //Need to check if attendee is not already registered for event at this time
        boolean check = false;
        if (event.getAttendeeList().size() <2){
            event.getAttendeeList().add(attendee.getUserId());
            check = true;

        }
        else{
            System.out.println("Cannot reserve spot due to full capacity of event.");
        }
        return check;
    }

    public boolean removeAttendee(Event event, Attendee attendee){

        boolean check = false;
        if(event.getAttendeeList().contains(attendee.getUserId())){
            event.getAttendeeList().remove(attendee.getUserId());
            check = true;

        }
        else{
            System.out.println("Error: Cannot find user at this event");
        }
        return check;

    }

    public Event getEvent(String eventName){

        for(Event event: EventList){
            if(event.getEventName().equals(eventName)) {
                return event;
            }
        }

        return null;

    }

}





