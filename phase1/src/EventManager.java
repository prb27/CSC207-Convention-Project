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
                    System.out.println("Error: Event has the following conflict:");
                    System.out.println("Event Name: " + event.getEventName());
                    System.out.println("Event Time: " + event.getEventTime());
                    System.out.println("Room Number: " + event.getRoomNumber());
                    break;
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

    public boolean removeEvent(String eventName, HashMap<String, String> listOfTalks ){
        //Need to still remove it from attendee's list of events
        Event event = getEvent(eventName);
        boolean check = false;
        if (EventList.contains(event) && event !=null){
            EventList.remove(event);
            listOfTalks.remove(event.getEventTime(),event.getSpeakerName());
            check = true;
        }
        else{
            System.out.println("Event does not exist; cannot remove event");
        }
        return check;
    }

    public boolean reserveAttendee(String eventName, String UserId){
        //Need to check if attendee is not already registered for event at this time
        Event event = getEvent(eventName);
        boolean check = false;
        if (event.getAttendeeList().size() <2 && event!=null){
            event.getAttendeeList().add(UserId);
            check = true;

        }
        else{
            System.out.println("Cannot reserve spot due to full capacity of event.");
        }
        return check;
    }

    public boolean removeAttendee(String eventName, String UserId){
        Event event = getEvent(eventName);
        boolean check = false;
        if(event.getAttendeeList().contains(UserId) && event!=null){
            event.getAttendeeList().remove(UserId);
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





