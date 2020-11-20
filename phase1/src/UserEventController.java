import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class UserEventController {

    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private EventManager eventManager;
    private RoomManager roomManager;


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
        if (organizerManager.isOrganizer(username)) {
            if (eventManager.isEvent(eventName)){
                if (organizerManager.isAttending(username, eventName).equals("YES")) {
                    return "AE";
                }
                String roomId = eventManager.getRoomNumber(eventName);
                int capacity = roomManager.getCapacityOfRoom(roomId);
                ArrayList<String> attendeesOfEvent = eventManager.getAttendeeList(eventName);
                if (attendeesOfEvent.size() < capacity) {
                    String erMessage = eventManager.reserveAttendee(eventName, username);
                    if (erMessage.equals("YES")) {
                        organizerManager.addAttendingEvent(username, eventName);
                        return "YES";
                    }
                    return erMessage;
                }
                return "EFC";
            }
            return "EDE";
        }
        return "ODE";
    }

    public void cancelSeatForUser(String username, String eventName){

        if(eventManager.isEvent(eventName)){
            if(attendeeManager.isAttendee(username)){
                attendeeManager.removeAttendingEvent(username, eventName);
                eventManager.removeAttendee(eventName, username);
            }
            else if(organizerManager.isOrganizer(username)){
                organizerManager.removeAttendingEvent(username, eventName);
                eventManager.removeAttendee(eventName, username);
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
     * Allows an organizer to create an event, by adding it to the list of events, as well as the list of speaker talks
     * @param organizerName: name of organizer
     * @param eventName: name of event
     * @param eventTime: time of event
     * @param speakerName: name of speaker
     * "RO" - Room Occupied
     * "STC" - Speaker Time Conflict
     * "ETC" - Event Time Conflict
     * "ODE" - Organizer Doesn't Exist
     * @return Strings of the values listed above
     */
    public String createEvent(String organizerName, String eventName, String eventTime, String speakerName){
        ArrayList<String> allowedTimes = new ArrayList<String>();
        allowedTimes.add("9");
        allowedTimes.add("10");
        allowedTimes.add("11");
        allowedTimes.add("12");
        allowedTimes.add("1");
        allowedTimes.add("2");
        allowedTimes.add("3");
        allowedTimes.add("4");
        allowedTimes.add("5");

        if(organizerManager.isOrganizer(organizerName)){

            if(allowedTimes.contains(eventTime)){

                if(speakerManager.isSpeakerFreeAtTime(speakerName, eventTime)){
                    String roomNumber = roomManager.occupyRoomFreeAt(eventTime);

                    if(!roomNumber.equals("-")){
                        speakerManager.addTalkToListOfTalks(speakerName,eventTime,eventName);
                        roomManager.occupyRoomAt(roomNumber,eventTime);
                        return eventManager.addEvent(eventName,eventTime,roomNumber,speakerName);
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
                return "ETC";
            }

        }
        else{
            return "ODE";
        }

    }

    /**
     * Allows an organizer to remove a created event, also removes it from the list of talks of the speaker
     * @param organizerName: name of organizer
     * @param eventName: name of event
     * "EDE" - Event Doesn't Exist
     * "ODE" - Organizer Doesn't Exist
     * "YES" - Request Successful
     * @return String of the values listed above
     */
    public String removeCreatedEvent(String organizerName,String eventName) {
        if (organizerManager.isOrganizer(organizerName)) {
            if (eventManager.isEvent(eventName)) {
                String speakerUserName = eventManager.getSpeakerEvent(eventName);
                String eventTime = eventManager.getEventTime(eventName);
                String roomId = eventManager.getRoomNumber(eventName);

                eventManager.removeEvent(eventName);
                speakerManager.removeTalkFromListOfTalks(speakerUserName, eventTime, eventName);
                roomManager.freeRoomAt(roomId, eventTime);
                return "YES";

            } else {
                return "EDE";
            }
        } else {
            return "ODE";
        }
    }


    /**
     * allow an Attendee to signup to a particular event
     * call eventManager to perform!
     * @author Khoa Pham
     * @param attendee: the username of an Attendee who will attend this event (param_type: String)
     * @param event: the intended event (param_type: String)
     * @return void
     */
    public void signUp(String attendee, String event) {
        attendeeManager.addAttendingEvent(attendee, event);
        eventManager.reserveAttendee(event, attendee);
    }

    /**
     * allow an Attendee to cancel their reservation to a particular event
     * call eventManager to perform!
     * @author Khoa Pham
     * @param attendee: the username of an Attendee who wants to cancel
     *                reservation for an event (param_type: String)
     * @param event: the intended event (param_type: String)
     * @return void
     */
    public void cancel(String attendee, String event) {
        attendeeManager.removeAttendingEvent(attendee, event);
        eventManager.removeAttendee(event, attendee);
    }

    /**
     * return the list of all events
     * call eventManager to perform!
     * @author Khoa Pham
     * @return ArrayList<Event> events
     */
    public ArrayList<Event> seeAllEvents() {
        return eventManager.getEventList();
    }

    /**
     * allow an Attendee to see the list of all their participating events
     * call eventManager to perform!
     * @author Khoa Pham
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

    /**
     * enroll an Attendee with <username> to an Event <eventName>
     * If event with <eventName> and Attendee with <username> exist,
     * check room capacity and enrol that Attendee to that Event
     * Notes:
     * ADE - Attendee doesn't exist
     * EDE - Event doesn't exist
     * EFC - Event at full capacity
     * @author Khoa Pham
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
                String erMessage = eventManager.reserveAttendee(eventName, username);
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

}