package Controllers;

import NewUI.OrganizerPresenterTextUI;
import UseCases.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class contains methods that are specific to actions that an Organizer is allowed to do. The methods in this class
 * collaborate with UseCase classes.
 * @author Ashwin
 */
public class OrganizerMenuController {

    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private AdminManager adminManager;
    private AccountHandler accountHandler;
    private EventManager eventManager;
    private UserEventController userEventController;
    private RoomManager roomManager;
    private ConversationManager conversationManager;
    private MessengerMenuController messengerMenuController;
    private ConversationMenuController conversationMenuController;
    private OrganizerPresenterTextUI organizerPresenterTextUI;


    public OrganizerMenuController(AttendeeManager attendeeManager, OrganizerManager organizerManager,
                                   SpeakerManager speakerManager, AdminManager adminManager,
                                   AccountHandler accountHandler, EventManager eventManager,
                                   UserEventController userEventController, RoomManager roomManager,
                                   OrganizerPresenterTextUI organizerPresenterTextUI,
                                   MessengerMenuController messengerMenuController, ConversationManager conversationManager,
                                   ConversationMenuController conversationMenuController){

        this.attendeeManager = attendeeManager;
        this.organizerManager = organizerManager;
        this.speakerManager = speakerManager;
        this.adminManager = adminManager;
        this.accountHandler = accountHandler;
        this.eventManager = eventManager;
        this.userEventController = userEventController;
        this.roomManager = roomManager;
        this.organizerPresenterTextUI = organizerPresenterTextUI;
        this.messengerMenuController = messengerMenuController;
        this.conversationManager = conversationManager;
        this.conversationMenuController = conversationMenuController;

    }


    /**
     * Private helper method that takes in the "organizer type" and handles the HALF the command
     * given by the user by calling the appropriate method using the appropriate controller
     * @param username: username of the currently logged in user
     */
    public void organizerFunctionalities(String option, String username) {

        Scanner scanner = new Scanner(System.in);
        switch (option) {
            case "1": {
                organizerPresenterTextUI.askForUserType();
                String userType = scanner.nextLine();
                organizerPresenterTextUI.listOfUsers(userType);
                }
                break;
            case "2": {
                organizerPresenterTextUI.promptForSpeakerUsername();
                String speakerName = scanner.nextLine();
                organizerPresenterTextUI.promptForTime();
                String time = scanner.nextLine();
                if (!speakerManager.isSpeaker(speakerName)) {
                    organizerPresenterTextUI.notASpeaker();
                    break;
                }
                boolean free = speakerManager.isSpeakerFreeAtTime(speakerName, time);
                if (free) {
                    organizerPresenterTextUI.messageForSpeakerFreeIfFree(time);
                } else {
                    organizerPresenterTextUI.messageForSpeakerFreeIfNotFree(time);
                }
                break;
            }
            case "3": {
                organizerPresenterTextUI.askForUserType();
                String userType = scanner.nextLine();
                organizerPresenterTextUI.askForUsername();
                String newUsername = scanner.nextLine();
                organizerPresenterTextUI.askForPassword();
                String password = scanner.nextLine();
                boolean err = accountHandler.signup(newUsername, password, userType);
                if (err) {
                    organizerPresenterTextUI.success();
                } else {
                    organizerPresenterTextUI.usernameAlreadyExists();
                }
                break;
            }
            case "4": {
                String err = organizerAddNewRoom(username);
                if (!err.equals("YES")) {
                    organizerPresenterTextUI.showError(err);
                } else {
                    organizerPresenterTextUI.success();
                }
                break;
            }
            case "5": {
                createEventThroughOrganizer(username);
            }
            case "6": {
                organizerPresenterTextUI.promptForEventName();
                String eventName = scanner.nextLine();
                userEventController.removeCreatedEvent(username, eventName);
            }
            case "7": {
                String err = changeSpeakerForEventThroughOrganizer(username);;
                if (!err.equals("YES")) {
                    organizerPresenterTextUI.showError(err);
                }
                else {
                    organizerPresenterTextUI.success();
                }
                break;
            }
            case "8": {
                String err = changeEventStartTime(username);
                if(err.equals("YES")) {
                    organizerPresenterTextUI.success();
                }
                else{
                    organizerPresenterTextUI.showError(err);
                }
                break;
            }
            case "9": {
                List<String> eventsNotSignedUpFor = userEventController.getOrganizerEventsNotAttending(username);
                organizerPresenterTextUI.presentEvents(eventsNotSignedUpFor);
            }
            case "10": {
                organizerPresenterTextUI.promptEventWantToAttend();
                String eventName = scanner.nextLine();
                String err = userEventController.enrolUserInEvent(username, eventName);
                if (!err.equals("YES")) {
                    organizerPresenterTextUI.showError(err);
                } else {
                    organizerPresenterTextUI.success();
                }
                break;
            }
            case "11": {
                organizerPresenterTextUI.promptForEventName();
                String eventName = scanner.nextLine();
                userEventController.cancelSeatForUser(username, eventName);
                organizerPresenterTextUI.presentNoLongerAttendingEvent(eventName);
                break;
            }
            case "12": {
                organizerPresenterTextUI.presentEvents(organizerManager.getEventsAttending(username));
                break;
            }
            case "13": {
                organizerPresenterTextUI.promptAttendeeID();
                String attendeeID = scanner.nextLine();
                organizerPresenterTextUI.messageprompt();
                String content = scanner.nextLine();
                boolean err = messengerMenuController.organizerSendMessageToSingle(username, attendeeID, content, "attendee");
                if (err) {
                    organizerPresenterTextUI.success();
                } else {
                    organizerPresenterTextUI.failure();
                }
                break;
            }
            case "14": {
                organizerPresenterTextUI.messageprompt();
                String content = scanner.nextLine();
                messengerMenuController.organizerSendMessageToAll(username, content, "attendee");
                break;
            }
            case "15": {
                organizerPresenterTextUI.promptForSpeakerUsername();
                String speakerName = scanner.nextLine();
                organizerPresenterTextUI.messageprompt();
                String content = scanner.nextLine();
                messengerMenuController.organizerSendMessageToSingle(username, speakerName, content, "speaker");
                break;
            }
            case "16": {
                organizerPresenterTextUI.messageprompt();
                String content = scanner.nextLine();
                messengerMenuController.organizerSendMessageToAll(username, content, "speaker");
                break;
            }
            case "17": {
                viewAndReplyInConversations(username);
                break;
            }
            case "18": {
                organizerPresenterTextUI.promptForEventName();
                String eventName = scanner.nextLine();
                organizerPresenterTextUI.messageprompt();
                String message = scanner.nextLine();
                if (!eventManager.isEvent(eventName)) {
                    organizerPresenterTextUI.showError("EDE");
                    break;
                }
                boolean messageByEvent = messengerMenuController.organizerMessageByEvent(username, eventName, message);
                if (messageByEvent) {
                    organizerPresenterTextUI.success();
                    break;
                }
                organizerPresenterTextUI.failure();
                break;
            }
        }
    }


    public void viewAndReplyInConversations(String username){

        Scanner scanner = new Scanner(System.in);
        Integer i = 1;
        for (String conversationId : organizerManager.getConversations(username)) {
            List<String> recipientsOfConversation = conversationManager.getConvoParticipants(conversationId);
            StringBuilder recipients = new StringBuilder();
            organizerPresenterTextUI.convoNumUniqueId(i.toString(), conversationId);
            for (String recipient : recipientsOfConversation) {
                recipients.append(recipient);
                recipients.append(", ");
            }
            organizerPresenterTextUI.presentRecipients(recipients);
            i += 1;
        }
        if (organizerManager.getConversations(username).isEmpty()) {
            organizerPresenterTextUI.noConvo();
            return;
        }
        organizerPresenterTextUI.promptConvoNumber();
        String conversationNumber = scanner.nextLine();
        String conversationIdFinal = organizerManager.getConversations(username).get(Integer.parseInt(conversationNumber) - 1);
        List<String> messagesInThisConversation = conversationMenuController.orderedMessagesInConvo(conversationIdFinal);
        organizerPresenterTextUI.presentMessageInConvo(messagesInThisConversation);
        organizerPresenterTextUI.promptToReply();
        String reply = scanner.nextLine();
        if (!reply.equals("r")) {
            return;
        }
        organizerPresenterTextUI.messageprompt();
        String content = scanner.nextLine();
        conversationMenuController.reply(username, conversationIdFinal, content);

    }


    /**
     * Checks if the speaker with <speakerName> is free at <time>
     * @param speakerName username of the speaker involved (param_type: String)
     * @param time time at which we want to check availability of the speaker (param_type: String)
     * @param duration duration for which the speaker is expected to be free (param_type: int)
     * @return String:
     * "SDE" - Speaker Doesn't Exist
     * "NO" - Speaker is not free at the time
     * "YES" - Speaker is free at that time
     */
    public String checkIfSpeakerFreeAtTimeFor(String speakerName, String time, int duration){

        if (!speakerManager.isSpeaker(speakerName)) {
            return "SDE"; //Refer to TextUserInterface
        }
        int k = 0;
        int availabilityFlag = 0;
        for(int i = 1; i < 13; i++){
            if(Integer.toString(i).equals(time)){
                k = i;
                break;
            }
        }
        for(int i = 0; i < duration; i++) {
            if(speakerManager.isSpeakerFreeAtTime(speakerName, Integer.toString(k))){
                availabilityFlag++;
            }
            k++;
        }
        if(k==duration){
            return "YES";
        }
        else{
            return "NO";
        }

    }

    /**
     * By the end of the execution of this method, the Entities.Organizer with username <username> would have
     * created a room if there was no roomId conflict.
     * @author Ashwin Karthikeyan
     * @param username: the username of the Entities.Organizer who wants to create a new room (param_type: String)
     * @return : "RAE" - room already exists
     *           "ODE" - organizer doesn't exist
     */
    public String organizerAddNewRoom(String username){

        Scanner scanner = new Scanner(System.in);
        organizerPresenterTextUI.promptForRoomID();
        String roomId = scanner.nextLine();
        organizerPresenterTextUI.promptForRoomCapacity();
        int capacity = scanner.nextInt();

        organizerPresenterTextUI.promptForProjectorExist();
        String proj = scanner.nextLine();
        boolean hasProjector = proj.equals("Y");

        organizerPresenterTextUI.askForAudioSystem();
        String answer = scanner.nextLine();
        boolean hasAudioSystem = answer.equals("Y");

        organizerPresenterTextUI.askForPowerSockets();
        int powerSockets = scanner.nextInt();
        // RAE - room already exists
        if(organizerManager.isOrganizer(username)){
            if(!roomManager.createRoom(roomId, capacity, hasProjector, hasAudioSystem, powerSockets)){
                return "RAE";
            }
            return "YES";
        }
        return "ODE";
    }


    /**
     * Change speaker for an Event. The Event will be removed and then a new event will be created. This means attendees
     * should register the event again once this method has been called. (This is to make sure that the attendees
     * are aware of the speaker change and still choose to attend the event)
     * @param username username of the organizer through whom this change is happening
     * @return String
     * "EDE" - Event Doesn't Exist
     * "SDE" - Speaker Doesn't Exist
     * "NO" - A speaker was not free at the event's time
     *  -- createEventInRoom return Strings --
     * "ARO" - All Rooms Occupied
     * "STC" - Entities.Speaker Time Conflict
     * "TNA" - Time not allowed
     * "ODE" - Entities.Organizer Doesn't Exist
     * "ESOT" - Event Scheduling Over Time - the event cannot be scheduled at this time because it would have to
     *                   run past 5PM.
     * "YES" - change was successful
     */
    public String changeSpeakerForEventThroughOrganizer(String username){

        Scanner scanner = new Scanner(System.in);
        organizerPresenterTextUI.eventnameprompt();
        String eventName = scanner.nextLine();
        List<String> speakerNames = new ArrayList<>();

        organizerPresenterTextUI.numOfSpeakers();
        int num = scanner.nextInt();

        organizerPresenterTextUI.promptForEventSpeakers();
        for (int i = 0; i<num; i++) {
            String speaker = scanner.nextLine();
            speakerNames.add(speaker);
        }

        String eventTime = eventManager.getStartTime(eventName);
        int duration = eventManager.getDuration(eventName);

        eventSpec(eventTime, duration);

        organizerPresenterTextUI.promptForRoomID();
        String roomId = scanner.nextLine();

        String speakerErr;
        if(!eventManager.isEvent(eventName)){
            return "EDE"; //Refer to TextUserInterface
        }
        String eventStartTime = eventManager.getStartTime(eventName);
        int eventDuration = eventManager.getDuration(eventName);
        int eventCapacity = eventManager.getEventCapacity(eventName);
        String subjectLine = eventManager.getEventSubjectLine(eventName);
        for(String speakerName: speakerNames) {
            speakerErr = checkIfSpeakerFreeAtTimeFor(speakerName, eventStartTime, eventDuration);
            if(!speakerErr.equals("YES")){
                return speakerErr;
            }
        }
        userEventController.removeCreatedEvent(username, eventName);
        String err = userEventController.createEventInRoom(username, eventName, eventStartTime, eventDuration, eventCapacity, speakerNames, roomId, subjectLine);
        if (!err.equals("YES"))
            return err; //Refer to TextUserInterface
        else {
            return "YES"; //Refer to TextUserInterface
        }

    }

    /**
     * Returns a list of event Details
     * @param eventName name of the event
     * @return List</String>
     */
    public List<String> getEventDetails(String eventName){
        List<String> details = new ArrayList<>();
        details.add(eventManager.getStartTime(eventName));
        details.add(Integer.toString(eventManager.getDuration(eventName)));
        details.add(Integer.toString(eventManager.getEventCapacity(eventName)));
        details.add(eventManager.getRoomNumber(eventName));
        details.addAll(eventManager.getSpeakerEvent(eventName));
        return details;
    }

    /**
     * Changes the start time of an event.
     * @param username Username of the organizer through whom this change is happening
     * @return String
     * "EDE" - Event Doesn't Exist
     * "YES" - Successful
     * "SDE" - Speaker Doesn't Exist
     *  -- createEventInRoom return Strings --
     * "ARO" - All Rooms Occupied
     * "STC" - Entities.Speaker Time Conflict
     * "TNA" - Time not allowed
     * "ODE" - Entities.Organizer Doesn't Exist
     * "ESOT" - Event Scheduling Over Time - the event cannot be scheduled at this time because it would have to
     *                   run past 5PM.
     */
    public String changeEventStartTime(String username) {

        Scanner scanner = new Scanner(System.in);
        organizerPresenterTextUI.eventnameprompt();
        String eventName = scanner.nextLine();
        organizerPresenterTextUI.newTimeForEvent();
        String eventTime = scanner.nextLine();
        int duration = eventManager.getDuration(eventName);

        eventSpec(eventTime, duration);

        organizerPresenterTextUI.promptForRoomID();
        String roomId = scanner.nextLine();
        String speakerErr;
        if (eventManager.isEvent(eventName)) {
            List<String> speakerNames = eventManager.getSpeakerEvent(eventName);
            int eventDuration = eventManager.getDuration(eventName);
            int eventCapacity = eventManager.getEventCapacity(eventName);
            String subjectLine = eventManager.getEventSubjectLine(eventName);
            userEventController.removeCreatedEvent(username, eventName);
            for (String speakerName : speakerNames) {
                speakerErr = checkIfSpeakerFreeAtTimeFor(speakerName, eventTime, eventDuration);
                if (!speakerErr.equals("YES")) {
                    return speakerErr;
                }
            }
            String err = userEventController.createEventInRoom(username, eventName, eventTime, eventDuration, eventCapacity, speakerNames, roomId, subjectLine);
            if (err.equals("YES")) {
                return "YES";
            } else {
                return err;
            }
        } else {
            return "EDE";
        }

    }

    public void createEventThroughOrganizer(String username){

        Scanner scanner = new Scanner(System.in);
        organizerPresenterTextUI.eventnameprompt();
        String eventName = scanner.nextLine();
        organizerPresenterTextUI.promptForEventTime();
        String eventTime = scanner.nextLine();
        organizerPresenterTextUI.promptForEventDuration();
        int duration = scanner.nextInt();
        organizerPresenterTextUI.promptForEventCapacity();
        int capacity = scanner.nextInt();

        eventSpec(eventTime, duration);

        organizerPresenterTextUI.promptForRoomID();
        String roomNum = scanner.nextLine();
        organizerPresenterTextUI.promptForSubjectLine();
        String subject = scanner.nextLine();
        List<String> speakers = new ArrayList<>();

        organizerPresenterTextUI.numOfSpeakers();
        int num = scanner.nextInt();

        organizerPresenterTextUI.promptForEventSpeakers();
        for(int i = 0; i<num; i++) {
            speakers.add(scanner.nextLine());
        }
        String err = userEventController.createEventInRoom(username, eventName, eventTime, duration, capacity, speakers, roomNum, subject);
        if (!err.equals("YES")) {
            organizerPresenterTextUI.showError(err);
        }
        else {
            organizerPresenterTextUI.success();
        }

    }

    private void eventSpec(String eventTime, int duration){

        Scanner scanner = new Scanner(System.in);
        organizerPresenterTextUI.askForSpecifications();
        String audioSys = scanner.nextLine();
        boolean hasAudioSystem = audioSys.equals("YES");
        String projector = scanner.nextLine();
        boolean hasProjector = projector.equals("YES");
        int powerSockets = scanner.nextInt();

        List<String> rooms = roomManager.roomsWithRequirements(hasAudioSystem, hasProjector, powerSockets, eventTime, duration);
        if(rooms != null) {
            organizerPresenterTextUI.roomMatchingSpecificationsAre(rooms);
        }
        else {
            organizerPresenterTextUI.noRoomMatch();
        }
    }

}