import java.util.ArrayList;
import java.util.HashMap;

public class EventManager {

    private ArrayList <Event> EventList = new ArrayList<>();

    public ArrayList<Event> getEventList() {
        return EventList;
    }

    public void addEvent(String eventName, String eventTime, String roomNumber, ArrayList<User>attendeeList, String speakerName, HashMap<String, String> listOfTalks){
        //Will use the following of Speakermanager object is passed
        //HashMap<String, String> listOfTalks = null;
        //listOfTalks = speaker.getListOfTalks();

        boolean check = false;
            if (listOfTalks.containsKey(eventTime)){
                System.out.println("Speaker time conflict; cannot reserve another talk");

            }

            else{
                for (Event event: EventList ){
                    if(event.getRoomNumber().equals(roomNumber) && event.getEventTime().equals(eventTime)){
                        check = true;
                    }
                }
                if (!check){
                    Event newEvent = new Event(eventName, speakerName, eventTime, roomNumber, attendeeList);
                    EventList.add(newEvent);
                    listOfTalks.put(eventTime, eventName);
                }
            }

    }

    public void removeEvent(Event event, HashMap<String, String> listOfTalks ){

        EventList.remove(event);
        listOfTalks.remove(event.getEventTime(),event.getSpeakerName());
    }

    public void reserveAttendee(Event event, Attendee attendee){
        //Need to check if attendee is not already registered for event at this time
    }

    public void removeAttendee(Event event, Attendee attendee){
        event.getAttendeeList().remove(attendee);

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





