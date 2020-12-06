package Controllers;

import UseCases.*;

import java.util.*;

public class MessengerMenuController {

    private MessageManager messageManager;
    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private AdminManager adminManager;
    private EventManager eventManager;
    private List<String> eligibleContacts;

    public MessengerMenuController(MessageManager messageManager, AttendeeManager attendeeManager,
                                   OrganizerManager organizerManager, SpeakerManager speakerManager,
                                   AdminManager adminManager, EventManager eventManager){

        this.messageManager = messageManager;
        this.attendeeManager = attendeeManager;
        this.speakerManager = speakerManager;
        this.adminManager = adminManager;
        this.eventManager = eventManager;
        this.eligibleContacts = new ArrayList<>();

    }

    public List<String> getUsersToMessage(String username){

        eligibleContacts.add(adminManager.getAdminName());
        if(attendeeManager.isAttendee(username)){
            ArrayList<String> eventsAttending = attendeeManager.getEventsAttending(username);
            for (String eventName: eventsAttending){
                eligibleContacts.addAll(eventManager.getSpeakerEvent(eventName));
                eligibleContacts.addAll(eventManager.getAttendeeList(eventName));
            }
        }

        if(organizerManager.isOrganizer(username)){

        }
        if (speakerManager.isSpeaker(username)) {
            HashMap<String, String> events = speakerManager.getListOfTalks(username);
            for (Map.Entry<String, String> event: events.entrySet()){
                String eventName = event.getValue();
                eligibleContacts.addAll(eventManager.getAttendeeList(eventName));
            }
        }
        return eligibleContacts;

    }

    public List<String> getEventsToMessage(String username){
        if(attendeeManager.isAttendee(username)){
            return attendeeManager.getEventsAttending(username);
        }
        if (organizerManager.isOrganizer(username)){
            return organizerManager.getEventsAttending(username);
        }
        if (speakerManager.isSpeaker(username)) {
        }
        return null;
    }
}
