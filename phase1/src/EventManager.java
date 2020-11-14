import java.util.ArrayList;

public class EventManager {

    private ArrayList <Event> EventList = new ArrayList<>();

    public ArrayList<Event> getEventList() {
        return EventList;
    }

    public void addEvent(String eventName, String speakerName, double eventTime, int roomNumber, ArrayList<User>attendeeList){
        //First check if speakername is available with giventime
        //Check roomNumber and Time
        //Create new event
        //Add it to EventList
        //Add it to SpeakerTalk

    }

    public void removeEvent(String eventName, String speakerName, double eventTime, int roomNumber, ArrayList<User>attendeeList){
        //Iterate through eventlist, remove object
        //Remove it from speaker talk
    }

    public void reserveAttendee(){

    }

    public void removeAttendee(){

    }


}



