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

    /**
     * enroll an Attendee with <username> to an Event <eventName>
     * If event with <eventName> and Attendee with <username> exist,
     * check room capacity and enrol that Attendee to that Event
     * Notes:
     * ADE - Attendee doesn't exist
     * EDE - Event doesn't exist
     * EFC - Event at full capacity
     * @param username: the username of an Attendee to be enrolled in an event (param_type: String)
     * @param eventName: the intended event (param_type: String)
     * @return String
     */
    public String enrolAttendeeInEvent(String username, String eventName) {
        // ent.

        if (attendeeManager.isAttendee(username) && eventManager.getEvent(eventName) != null) {
            String roomId = eventManager.getEvent(eventName).getRoomNumber();
            int capacity = roomManager.getCapacityOfRoom(roomId);
            ArrayList<String> attendeesOfEvent = eventManager.getEvent(eventName).getAttendeeList();
            if (attendeesOfEvent.size() < capacity) {
                String erMessage = eventManager.reserveAttendee(eventName, username,
                        attendeeManager.getEventsAttending(username));
                if (erMessage.equals("YES")) {
                    attendeeManager.addAttendingEvent(username, eventName);
                    return "YES";
                }
                return erMessage;
            }
            return "EFC";
        }
        return "ADE/EDE";
    }


    /**
     * allow an Attendee to signup to a particular event
     * call eventManager to perform!
     * @param attendee: the username of an Attendee who will attend this event (param_type: String)
     * @param event: the intended event (param_type: String)
     * @return void
     */
    public void signUp(String attendee, String event) {
        ArrayList<String> participatingEvents = attendeeManager.getAttendee(attendee).getEventsAttending();
        attendeeManager.addAttendingEvent(attendee, event);
        eventManager.reserveAttendee(event, attendee, participatingEvents);
    }

    /**
     * allow an Attendee to cancel their reservation to a particular event
     * call eventManager to perform!
     * @param attendee: the username of an Attendee who wants to cancel
     *                reservation for an event (param_type: String)
     * @param event: the intended event (param_type: String)
     * @return void
     */
    public void cancel(String attendee, String event) {
        ArrayList<String> participatingEvents = attendeeManager.getAttendee(attendee).getEventsAttending();
        attendeeManager.removeAttendingEvent(attendee, event);
        eventManager.removeAttendee(event, attendee, participatingEvents);
    }

    /**
     * return the list of all events
     * call eventManager to perform!
     * @return ArrayList<Event> events
     */
    public ArrayList<Event> seeAllEvents() {
        return eventManager.getEventList();
    }

    /**
     * allow an Attendee to see the list of all their participating events
     * call eventManager to perform!
     * @param attendee: the username of an Attendee whose list of
     *                participating events is returned (param_type: String)
     * @return ArrayList<Event> events
     */
    public ArrayList<Event> seeParticipatingEvents(String attendee) {
        ArrayList<String> eventIds = attendeeManager.getAttendee(attendee).getEventsAttending();
        ArrayList<Event> eventObjs = new ArrayList<>();
        for (String id: eventIds) {
            eventObjs.add(eventManager.getEvent(id));
        }
        return eventObjs;
    }

}
