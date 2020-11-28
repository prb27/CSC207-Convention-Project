package Controllers;

import UseCases.*;

import java.io.Serializable;
import java.util.ArrayList;

public class OrganizerMenuController implements Serializable {

    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private EventManager eventManager;
    private RoomManager roomManager;
    private final ConversationManager conversationManager;
    private final MessageManager messageManager;


    public OrganizerMenuController(AttendeeManager attendeeManager, OrganizerManager organizerManager, SpeakerManager speakerManager, EventManager eventManager, RoomManager roomManager, ConversationManager conversationManager, MessageManager messageManager){

        this.attendeeManager = attendeeManager;
        this.organizerManager = organizerManager;
        this.speakerManager = speakerManager;
        this.eventManager = eventManager;
        this.roomManager = roomManager;
        this.conversationManager = conversationManager;
        this.messageManager = messageManager;

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

    /**
     * Allows the organizer to send a message to any user
     * @param organizerId : ID of organizer
     * @param content : content of message
     * @param userType : the user an organizer wishes to send a message to
     * @return true if message is sent, false otherwise
     */
    public boolean organizerSendMessageToAll(String organizerId, String content, String userType){

        if(organizerManager.isOrganizer(organizerId)){
            if(userType.equals("attendee")) {
                ArrayList<String> attendeeIDs = attendeeManager.getAllAttendeeIds();
                organizerToAll(organizerId, content, userType, attendeeIDs);
                return true;
            }
            if(userType.equals("organizer")) {
                ArrayList<String> organizerIDs = organizerManager.getAllOrganizerIds();
                organizerToAll(organizerId, content, userType, organizerIDs);
                return true;
            }
            if(userType.equals("speaker")) {
                ArrayList<String> speakerIds = speakerManager.getAllSpeakerIds();
                organizerToAll(organizerId, content, userType, speakerIds);
                return true;
            }
        }
        return false;

    }

    /**
     * Allows an organizer to send a message to a single yser.
     * @param organizerId : id of organizer
     * @param recipientId : id of recipient
     * @param content : content of message
     * @param userType : type of user (speaker, organizer, attendee)
     * @return true if message is sent, false otherwise
     */
    public boolean organizerSendMessageToSingle(String organizerId, String recipientId, String content, String userType){

        if(userType.equals("attendee")){
            if(organizerManager.isOrganizer(organizerId) && attendeeManager.isAttendee(recipientId)){
                organizerToSingle(organizerId, recipientId, content, userType);
                return true;
            }
            return false;
        }
        if(userType.equals("organizer")){
            if(organizerManager.isOrganizer(organizerId) && organizerManager.isOrganizer(recipientId)){
                organizerToSingle(organizerId, recipientId, content, userType);
                return true;
            }
            return false;
        }
        if(userType.equals("speaker")){
            if(organizerManager.isOrganizer(organizerId) && speakerManager.isSpeaker(recipientId)){
                organizerToSingle(organizerId, recipientId, content, userType);
                return true;
            }
            return false;
        }
        return false;

    }

}
