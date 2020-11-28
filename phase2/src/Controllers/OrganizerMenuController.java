package Controllers;

import UseCases.EventManager;
import UseCases.OrganizerManager;
import UseCases.RoomManager;

import java.io.Serializable;
import java.util.ArrayList;

public class OrganizerMenuController implements Serializable {

    private OrganizerManager organizerManager;
    private EventManager eventManager;
    private RoomManager roomManager;


    public OrganizerMenuController(OrganizerManager organizerManager, EventManager eventManager, RoomManager roomManager){

        this.organizerManager = organizerManager;
        this.eventManager = eventManager;
        this.roomManager = roomManager;

    }

    /**
     * enroll an Entities.Organizer with </username> to an Entities.Event </eventName>
     * If event with </eventName> exists,
     * check room capacity and enrol that Entities.Attendee to that Entities.Event
     * @author Ashwin Karthikeyan
     * @param username: the username of an Entities.Organizer to be enrolled in an event (param_type: String)
     * @param eventName: the intended event (param_type: String)
     * @return "AE" - Already attending the event
     *         "EDE" - Entities.Event doesn't exist
     *         "EFC" - Entities.Event at full capacity
     *         "YES" - Entities.Organizer has newly been registered for this event
     */
    private String enrolOrganizerInEvent(String username, String eventName){

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

    /**
     * By the end of the execution of this method, the Entities.Organizer with username </username> is no longer
     * attending the event with title </eventName>.
     * @author Ashwin Karthikeyan
     * @param username: the username of the Entities.Organizer who wants to cancel
     *                  reservation for an event (param_type: String)
     * @param eventName: the intended event (param_type: String)
     */
    public void cancelSeatForOrganizer(String username, String eventName){

        if(organizerManager.isOrganizer(username)){
            if(organizerManager.isAttending(username, eventName).equals("YES")) {
                organizerManager.removeAttendingEvent(username, eventName);
                eventManager.removeAttendee(eventName, username);
            }
        }
    }


    /**
     * By the end of the execution of this method, the Entities.Organizer with username </organizerUsername> would have
     * created a room with Id </roomId> and capacity </capacity>
     * @author Ashwin Karthikeyan
     * @param organizerUsername: the username of the Entities.Organizer who wants to create a new room (param_type: String)
     * @param roomId: an ID for the new room that the Entities.Organizer wants to create (param_type: String)
     * @param capacity: the capacity of the new room being created (param_type: int)
     * @return : "RAE" - room already exists
     *           "ODE" - organizer doesn't exist
     */
    public String organizerAddNewRoom(String organizerUsername, String roomId, int capacity){
        // RAE - room already exists
        if(organizerManager.isOrganizer(organizerUsername)){
            if(!roomManager.createRoom(roomId, capacity)){
                return "RAE";
            }
            return "YES";
        }
        return "ODE";
    }

    /**
     * If Entities.Organizer with given username </username> exists, then this method returns the list of event titles that
     * @param username username of Entities.Organizer
     * @return List of Events that this Entities.Organizer is not attending
     */
    public ArrayList<String> getOrganizerEventsNotAttending(String username) {

        ArrayList<String> eventsNotSignedUpFor = new ArrayList<>();
        for(String event: eventManager.getAllEventTitles()){
            eventsNotSignedUpFor.add(event);
        }
        if(organizerManager.isOrganizer(username)) {
            if (organizerManager.getEventsAttending(username) != null) {
                for (String event : organizerManager.getEventsAttending(username)) {
                    eventsNotSignedUpFor.remove(event);
                }
            }
        }
        return eventsNotSignedUpFor;
    }

}
