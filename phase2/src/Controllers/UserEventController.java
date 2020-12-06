package Controllers;

import UseCases.*;

import java.io.Serializable;
import java.util.*;

/**
 * This class is responsible for taking input and implementing all logic/actions related to a user and events.
 * The following manipulations a user can work on are:
 * - enrol an Entities.Organizer in an event
 * - enrol an Entities.Attendee in an event
 * - enrol a Entities.User in event
 * - cancel enrolment for Entities.User
 * - view list of available events
 * - create an event
 * - remove an event
 * @author Ashwin Karthikeyan, Arib Shaikh, Khoa Pham, Vladimir
 *
 */
public class UserEventController implements Serializable {

    private final AttendeeManager attendeeManager;
    private final OrganizerManager organizerManager;
    private final SpeakerManager speakerManager;
    private final EventManager eventManager;
    private final RoomManager roomManager;


    public UserEventController(AttendeeManager attendeeManager, OrganizerManager organizerManager, SpeakerManager speakerManager, EventManager eventManager, RoomManager roomManager){

        this.attendeeManager = attendeeManager;
        this.organizerManager = organizerManager;
        this.speakerManager = speakerManager;
        this.eventManager = eventManager;
        this.roomManager = roomManager;

    }

    /**
     * enroll an Entities.Attendee with </username> to an Entities.Event </eventName>
     * If event with </eventName> and Entities.Attendee with </username> exist,
     * check room capacity and enrol that Entities.Attendee to that Entities.Event
     * @author Khoa Pham
     * @param username: the username of an Entities.Attendee to be enrolled in an event (param_type: String)
     * @param eventName: the intended event (param_type: String)
     * @return : "AE" - Already attending event
     *           "EDE" - Entities.Event doesn't exist
     *           "EFC" - Entities.Event at full capacity
     *           "YES" - Entities.Attendee has newly been registered for this event
     */
    private String enrolAttendeeInEvent(String username, String eventName) {
        if (eventManager.isEvent(eventName)) {
            if (attendeeManager.isAttending(username, eventName)) {
                return "AE";
            }
            String roomId = eventManager.getRoomNumber(eventName);
//            int capacity = roomManager.getCapacityOfRoom(roomId);
            int capacity = eventManager.getEventCapacities(eventName);
            ArrayList<String> attendeesOfEvent = eventManager.getAttendeeList(eventName);
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
        return "EDE";
    }

    /**
     * enroll Entities.User (Entities.Organizer/Entities.Attendee) with </username> to an Entities.Event </eventName>
     * by calling enrolOrganizerInEvent() or enrolAttendeeInEvent()
     * @author Ashwin Karthikeyan
     * @param username: the username of a Entities.User (Entities.Organizer/Entities.Attendee) to be enrolled in an event (param_type: String)
     * @param eventName: the intended event (param_type: String)
     * @return : "AE" - Already attending event
     *           "EDE" - Entities.Event doesn't exist
     *           "EFC" - Entities.Event at full capacity
     *           "YES" - Entities.Attendee has newly been registered for this event
     */
    public String enrolUserInEvent(String username, String eventName){

        if(organizerManager.isOrganizer(username)){
            return enrolOrganizerInEvent(username, eventName);
        }
        else if(attendeeManager.isAttendee(username)){
            return enrolAttendeeInEvent(username, eventName);
        }
        return "UDE";

    }

    /**
     * By the end of the execution of this method, the Entities.User (Entities.Organizer/Entities.Attendee) with username </username> is no longer
     * attending the event with title </eventName>.
     * @author Khoa Pham, Ashwin Karthikeyan
     * @param username: the username of the Entities.User who wants to cancel
     *                  reservation for an event (param_type: String)
     * @param eventName: the intended event (param_type: String)
     */
    public void cancelSeatForUser(String username, String eventName){

        if(eventManager.isEvent(eventName)){
            if(attendeeManager.isAttendee(username)){
                if(attendeeManager.isAttending(username, eventName)) {
                    attendeeManager.removeAttendingEvent(username, eventName);
                    eventManager.removeAttendee(eventName, username);
                }
            }
            else if(organizerManager.isOrganizer(username)){
                if(organizerManager.isAttending(username, eventName).equals("YES")) {
                    organizerManager.removeAttendingEvent(username, eventName);
                    eventManager.removeAttendee(eventName, username);
                }
            }
        }

    }

    /**
     * Allows an organizer to create an event, by adding it to the list of events, as well as the list of speaker talks
     * @param organizerName: name of organizer
     * @param eventName: name of event
     * @param startTime: time of event
     * @param speakerName: name of speaker
     * "ARO" - All Rooms Occupied
     * "STC" - Entities.Speaker Time Conflict
     * "TNA" - Time not allowed
     * "ODE" - Entities.Organizer Doesn't Exist
     * @return Strings of the values listed above
     */


    /**
     * return the list of all events
     * call eventManager to perform!
     * @author Khoa Pham
     * @return Hashtable<String, ArrayList<String>> eventsWithInfo
     */
    public Hashtable<String, List<String>> seeAllEventsWithInfo() {
        return eventManager.getAllEventsWithInfo();
    }

    /**
     * allow an Entities.Attendee to see the list of all their participating events
     * call eventManager to perform!
     * @author Khoa Pham
     * @param attendee: the username of an Entities.Attendee whose list of
     *                participating events is returned (param_type: String)
     * @return Hashtable<String, List<String>> eventsWithInfo
     */
    public Hashtable<String, List<String>> seeParticipatingEvents(String attendee) {
        ArrayList<String> eventIdsAttending = attendeeManager.getEventsAttending(attendee);
        Hashtable<String, List<String>> events = new Hashtable<>();
        for (String eventId : eventIdsAttending) {
            events.put(eventId, eventManager.getEventInfo(eventId));
        }
        return events;
    }


    /**
     * allow an Entities.Attendee to see the list of all their available to signup events
     * call eventManager to perform!
     * @author Khoa Pham
     * @param attendee: the username of an Entities.Attendee whose list of
     *          all their available to signup events is returned (param_type: String)
     * @return Hashtable<String, List<String>> eventsWithInfo
     */
    public Hashtable<String, List<String>> seeAttendableEvents(String attendee) {
        ArrayList<String> eventIdsAttending = attendeeManager.getEventsAttending(attendee);
        ArrayList<String> eventIdsAll = eventManager.getEventNamesList();
        Hashtable<String, List<String>> eventsAttendable = new Hashtable<>();
        for (String eventId : eventIdsAll) {
            if (!eventIdsAttending.contains(eventId)) {
                eventsAttendable.put(eventId, eventManager.getEventInfo(eventId));
            }
        }
        return eventsAttendable;
    }

    /**
     * Allows the speaker to see the list of events they are hosting with event name and time
     * @param speakerUsername : username of speaker
     * @return : Returns the list of events they are hosting (param_type: ArrayList<String>)
     */
    public ArrayList<String> seeListOfEventsForSpeaker(String speakerUsername){
        HashMap<String, String> listOfTalks = speakerManager.getListOfTalks(speakerUsername);

        ArrayList<String> masterList = new ArrayList<>();


       for(Map.Entry<String, String> event: listOfTalks.entrySet()){
           String eventTime = eventManager.getStartTime(event.getKey());
           masterList.add("(Entities.Event Name: " + event.getValue() + ", " + "Entities.Event Time: " + eventTime + ")");
       }


        return masterList;
    }

    /**
     * Can see the event information
     * @param speakerUsername : name of speaker
     * @return : list of talks for the speaker (param_type: ArrayList<HashMap<String, String>>)
     */
    private HashMap<String, String> seeAllEventsForSpeaker(String speakerUsername){
        return speakerManager.getListOfTalks(speakerUsername);
    }

    /**
     * Can see all the event names for the speaker
     * @param speakerUsername : name of speaker
     * @return list of all event names of talks (param_type: ArrayList<String>)
     */
    public ArrayList<String> seeAllEventNamesForSpeaker(String speakerUsername){
//        ArrayList<HashMap<String, String>> listOfTalks = seeAllEventsForSpeaker(speakerUsername);
//        ArrayList<String> listOfNamedTalks = new ArrayList<>();
//        for(HashMap<String, String> talk: listOfTalks){
//            Collection<String> talkname1 =  talk.values();
//            for (String talk3: talkname1){
//                listOfNamedTalks.add(talk3);
//            }
//        }
//        return listOfNamedTalks;
        HashMap<String, String> listOfTalks = speakerManager.getListOfTalks(speakerUsername);

        ArrayList<String> masterList = new ArrayList<>();

        for(Map.Entry<String, String> event: listOfTalks.entrySet()){
            masterList.add("(Entities.Event Name: " +  event.getValue() + ")");
        }


        return masterList;
    }

    /**
     * allow an Admin to delete events with no Attendees
     * call eventManager to perform!
     * This option should only be accessible from inside the Admin login!!!
     * Only one Admin can perform this task
     * @author Khoa Pham
     */
    public void deleteEventWithoutAttendee() {
        ArrayList<String> allEmptyEvents = eventManager.getEmptyEvents();
        for (String event : allEmptyEvents) {
            eventManager.removeEvent(event);
        }
    }


}