import java.util.ArrayList;

public class UserEventController {

    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private EventManager eventManager;
    private RoomManager roomManager;

    public UserEventController(){

        attendeeManager = new AttendeeManager();
        organizerManager = new OrganizerManager();
        speakerManager = new SpeakerManager();
        eventManager = new EventManager();
        roomManager = new RoomManager();

    }

    public UserEventController(AttendeeManager attendeeManager, OrganizerManager organizerManager, SpeakerManager speakerManager, EventManager eventManager, RoomManager roomManager){

        this.attendeeManager = attendeeManager;
        this.organizerManager = organizerManager;
        this.speakerManager = speakerManager;
        this.eventManager = eventManager;
        this.roomManager = roomManager;

    }

    public String enrolOrganizerInEvent(String username, String eventName){
        // If event with eventname and organizer with username exist, then check room capacity and enrol organizer in event.
        // ODE - Organizer doesn't exist
        // EDE - Event doesn't exist
        // EFC - Event at full capacity
        if (organizerManager.isOrganizer(username) && eventManager.getEvent(eventName) != null) {
            String roomId = eventManager.getEvent(eventName).getRoomNumber();
            int capacity = roomManager.getCapacityOfRoom(roomId);
            ArrayList<String> attendeesOfEvent = eventManager.getEvent(eventName).getAttendeeList();
            if (attendeesOfEvent.size() < capacity) {
                String erMessage = eventManager.reserveAttendee(eventName, username, organizerManager.getEventsAttending(username));
                if (erMessage.equals("YES")) {
                    organizerManager.addAttendingEvent(username, eventName);
                    return "YES";
                }
                return erMessage;
            }
            return "EFC";
        }
        return "ODE/EDE";
    }

    public void cancelSeatForUser(String username, String eventName){

        if(eventManager.isEvent(eventName)){
            if(attendeeManager.isAttendee(username)){
                attendeeManager.removeAttendingEvent(username, eventName);
                eventManager.removeAttendee(eventName, username, attendeeManager.getEventsAttending(username));
            }
            else if(organizerManager.isOrganizer(username)){
                organizerManager.removeAttendingEvent(username, eventName);
                eventManager.removeAttendee(eventName, username, organizerManager.getEventsAttending(username));
            }
        }

    }

    public String organizerAddNewRoom(String organizerUsername, String roomId, int capacity){
        // RAE - room already exists
        if(organizerManager.isOrganizer(organizerUsername)){
            if(roomManager.isRoom(roomId)){
                return "RAE";
            }
            roomManager.createRoom(roomId, capacity);
            return "YES";
        }
        return "ODE";
    }

    public String createEvent(String organizerName, String eventName, String eventTime, String speakerName){
        if(organizerManager.isOrganizer(organizerName)){
            if(speakerManager.isSpeakerFreeAtTime(speakerName, eventTime)){
                String roomNumber = roomManager.occupyRoomFreeAt(eventTime);

                if(!roomNumber.equals("-")){
                    speakerManager.addTalkToListOfTalks(speakerName,eventTime,eventName);
                    eventManager.addEvent(eventName,eventTime,roomNumber,speakerName);
                    roomManager.occupyRoomAt(roomNumber,eventTime);
                    return "YES";
                }
                else{
                    return "RO";
                }

            }
            else{
                return "STC";
            }

        }
        else{
            return "ODE";
        }

    }

    public String removeCreatedEvent(String organizerName,String eventName) {
        if (organizerManager.isOrganizer(organizerName)) {
            if (eventManager.isEvent(eventName)) {
                String speakerUserName = eventManager.getSpeakerEvent(eventName);
                String eventTime = eventManager.getEventTime(eventName);
                String roomId = eventManager.getRoomNumber(eventName);

                eventManager.removeEvent(eventName);
                speakerManager.removeTalkFromListofTalks(speakerUserName, eventTime, eventName);
                roomManager.freeRoomAt(roomId, eventTime);
                return "YES";

            } else {
                return "EDE";
            }
        } else {
            return "ODE";
        }
    }

}
