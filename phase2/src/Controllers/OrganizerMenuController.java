package Controllers;

import NewUI.OrganizerPresesnterTextUI;
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
    private OrganizerPresesnterTextUI organizerPresesnterTextUI;


    public OrganizerMenuController(AttendeeManager attendeeManager, OrganizerManager organizerManager, SpeakerManager speakerManager, AdminManager adminManager, AccountHandler accountHandler, EventManager eventManager, UserEventController userEventController, RoomManager roomManager, OrganizerPresesnterTextUI organizerPresesnterTextUI){

        this.attendeeManager = attendeeManager;
        this.organizerManager = organizerManager;
        this.speakerManager = speakerManager;
        this.adminManager = adminManager;
        this.accountHandler = accountHandler;
        this.eventManager = eventManager;
        this.userEventController = userEventController;
        this.roomManager = roomManager;
        this.organizerPresesnterTextUI = organizerPresesnterTextUI;

    }


    /**
     * Private helper method that takes in the "organizer type" and handles the HALF the command
     * given by the user by calling the appropriate method using the appropriate controller
     * @param option: option selected by the user
     * @param username: username of the currently logged in user
     */
    private void oUCH1(String option, String username) {

        Scanner scanner = new Scanner(System.in);
        switch (option) {
            case "1": {
                organizerPresesnterTextUI.askForUserType();
                String userType = scanner.nextLine();
                List<String> users = listOfUsers(userType);
                }
                break;
            case "2": {
                //ui.present("Please enter the speaker's username");
                String speakerName = scanner.nextLine();
                //ui.present("Please enter the time");
                String time = scanner.nextLine();
                if (!speakerManager.isSpeaker(speakerName)) {
                    //ui.present("Not a speaker"); We can prompt the "SDE" error
                    break;
                }
                boolean free = speakerManager.isSpeakerFreeAtTime(speakerName, time);
                if (free) {
                    //ui.present("No, the speaker doesn't have an event at " + time);
                } else {
                    //ui.present("Yes, the speaker is talking at an event at " + time);
                }
                break;
            }
            case "3": {
                organizerPresesnterTextUI.askForUserType();
                String userType = scanner.nextLine();
                organizerPresesnterTextUI
                String organizerUsername = scanner.nextLine();
                //ui.present("Please enter the password for this new organizer");
                String organizerPassword = scanner.nextLine();
                boolean err = createNewAccount(organizerUsername, organizerPassword, "organizer");
                if (err) {
                    //ui.present("Successful");
                } else {
                    //ui.present("The username already exists");
                }
                break;
            }
            case "6": {
                ui.present("Please enter new speaker's username");
                String speakerUsername = scanner.nextLine();
                ui.present("Please enter password for this speaker");
                String speakerPassword = scanner.nextLine();
                if (accountHandler.signup(speakerUsername, speakerPassword, "speaker")) {
                    ui.showPrompt("UC");
                } else {
                    ui.showPrompt("SF");
                }
                break;
            }
            case "7": {
                ui.present("Please enter roomID:");
                String room = scanner.nextLine();
                ui.present("Please enter room capacity");
                int capacity = scanner.nextInt();

                ui.present("Please enter whether the room has a projector (Y/N)");
                String proj = scanner.nextLine();
                boolean hasProjector = false;
                if (proj.equals("Y"))
                    hasProjector = true;

                ui.present("Please enter whether the room has an audioSystem (Y/N)");
                String answer = scanner.nextLine();
                boolean hasAudioSystem = false;
                if (answer.equals("Y"))
                    hasAudioSystem = true;

                ui.present("Please enter the number of powerSockets");
                int powerSockets = scanner.nextInt();
                String err = organizerMenuController.organizerAddNewRoom(username, room, capacity, hasProjector,
                        hasAudioSystem, powerSockets);
                if (!err.equals("YES")) {
                    ui.showError(err);
                } else {
                    ui.present("Successful");
                }
                break;
            }
            case "8": {
                ui.present("Please enter event name");
                String eventName = scanner.nextLine();
                ui.present("Please enter event time");
                String eventTime = scanner.nextLine();
                ui.present("Please enter duration");
                int duration = scanner.nextInt();
                ui.present("Please enter event capacity");
                int capacity = scanner.nextInt();
                ui.present("Please enter room number");
                String roomNum = scanner.nextLine();
                ui.present("Please enter subject line");
                String subject = scanner.nextLine();
                List<String> speakers = new ArrayList<>();
                while (!scanner.nextLine().equals("Over")) {
                    ui.present("Please enter the speakers' username (enter after each name and type Over when done)");
                    speakers.add(scanner.nextLine());
                }
                String err = userEventController.createEventInRoom(username, eventName, eventTime, duration, capacity, speakers, roomNum, subject);
                if (!err.equals("YES"))
                    ui.showError(err);
                else {
                    ui.present("Successful");
                }
            }
            case "9": {
                ui.present("Please enter the event name.");
                String eventName = scanner.nextLine();
                if(!eventManager.isEvent(eventName)){
                    ui.showError("EDE");
                    break;
                }
                List<String> speakers = new ArrayList<>();
                boolean stop = false;
                while (!scanner.nextLine().equals("Over")) {
                    ui.present("Please enter the new speakers' username (enter after each name and type Over when done)");
                    String speaker = scanner.nextLine();
                    if (!speakerManager.isSpeaker(speaker)) {
                        ui.present("SDE");
                        stop = true;
                        break;
                    }
                    speakers.add(speaker);
                }
                if (stop)
                    break;
                String eventTime = eventManager.getStartTime(eventName);
                int duration = eventManager.getDuration(eventName);
                String room = eventManager.getRoomNumber(eventName);
                String subject = eventManager.getSubjectLine(eventName);
                int capacity = eventManager.getEventCapacity(eventName);
                userEventController.removeCreatedEvent(username, eventName);
                String err = userEventController.createEventInRoom(username, eventName, eventTime, duration, capacity, speakers, room, subject);
                if (!err.equals("YES"))
                    ui.showError(err);
                else {
                    ui.present("Successful");
                }
                break;
            }
            case "10": {
                ui.present("Please enter the event name");
                String eventName = scanner.nextLine();
                ui.present("Please enter a new time for the event");
                String eventTime = scanner.nextLine();
                List<String> speakers = eventManager.getSpeakerEvent(eventName);
                int duration = eventManager.getDuration(eventName);
                int capacity = eventManager.getEventCapacity(eventName);
                String roomNum = eventManager.getRoomNumber(eventName);
                String subject = eventManager.getSubjectLine(eventName);
                userEventController.removeCreatedEvent(username, eventName);
                for (String speaker : speakers) {
                    if(!speakerManager.isSpeaker(speaker))
                        ui.showError("EDE");
                    break;
                }
                String err = userEventController.createEventInRoom(username, eventName, eventTime, duration, capacity, speakers, roomNum, subject);
                if(err.equals("YES")) {
                    ui.present("Successful");
                }
                else{
                    ui.showError(err);
                }
                break;
            }
        }
    }

    private void oUCH2(Scanner scanner, String option, String username) {
        switch (option) {
            case "11": {
                List<String> eventsNotSignedUpFor = userEventController.getOrganizerEventsNotAttending(username);
                for (String event : eventsNotSignedUpFor)
                    ui.present("Event Title: " + event + "\nTime: " + eventManager.getStartTime(event) + "\nRoom: " + eventManager.getRoomNumber(event) + "\nSpeaker: " + eventManager.getSpeakerEvent(event) + "\n");
                break;
            }
            case "12": {
                ui.present("Please enter the title of the event you want to attend (exactly as it appears on the list of titles displayed)");
                String eventName = scanner.nextLine();
                String err = userEventController.enrolUserInEvent(username, eventName);
                if (!err.equals("YES")) {
                    ui.showError(err);
                } else {
                    ui.present("Successful");
                }
                break;
            }
            case "13": {
                ui.present("Please enter the event's name");
                String eventName = scanner.nextLine();
                userEventController.cancelSeatForUser(username, eventName);
                ui.present("You are no longer attending " + eventName);
                break;
            }
            case "14": {
                for (String event : organizerManager.getEventsAttending(username))
                    ui.present("Event Title: " + event + "\nTime: " + eventManager.getStartTime(event) + "\nRoom: " + eventManager.getRoomNumber(event) + "\nSpeaker: " + eventManager.getSpeakerEvent(event) + "\n");
                break;
            }
            case "15": {
                ui.present("Please enter attendee ID");
                String attendeeID = scanner.nextLine();
                ui.present("Please enter the message that you want to send");
                String content = scanner.nextLine();
                boolean err = messengerMenuController.organizerSendMessageToSingle(username, attendeeID, content, "attendee");
                if (err) {
                    ui.present("Successful");
                } else {
                    ui.present("Something went wrong");
                }
                break;
            }
            case "16": {
                ui.present("Please enter the message that you want to send");
                String content = scanner.nextLine();
                messengerMenuController.organizerSendMessageToAll(username, content, "attendee");
                break;
            }
            case "17": {
                ui.present("Please enter the speaker's username");
                String speakerName = scanner.nextLine();
                ui.present("Please enter the message that you want to send");
                String content = scanner.nextLine();
                messengerMenuController.organizerSendMessageToSingle(username, speakerName, content, "speaker");
                break;
            }
            case "18": {
                ui.present("Please enter the message that you want to send");
                String content = scanner.nextLine();
                messengerMenuController.organizerSendMessageToAll(username, content, "speaker");
                break;
            }
            case "19": {
                Integer i = 1;
                for (String conversationId : organizerManager.getConversations(username)) {
                    List<String> recipientsOfConversation = conversationManager.getConvoParticipants(conversationId);
                    StringBuilder recipients = new StringBuilder();
                    ui.present("Conversation Number " + i.toString() + "\n" + "Uniqueness Identifier: " + conversationId);
                    for (String recipient : recipientsOfConversation) {
                        recipients.append(recipient);
                        recipients.append(", ");
                    }
                    ui.present("Recipients: " + recipients);
                    i += 1;
                }
                if (organizerManager.getConversations(username).isEmpty()) {
                    ui.present("You have no conversations");
                    break;
                }
                ui.present("Choose a Entities.Conversation Number");
                String conversationNumber = scanner.nextLine();
                String conversationIdFinal = organizerManager.getConversations(username).get(Integer.parseInt(conversationNumber) - 1);
                List<String> messagesInThisConversation = conversationMenuController.orderedMessagesInConvo(conversationIdFinal);
                for (String s : messagesInThisConversation) {
                    ui.present(s);
                }
                ui.present("Enter \"r\" to reply in this conversation. [Any other input will exit this menu]");
                String reply = scanner.nextLine();
                if (!reply.equals("r")) {
                    break;
                }
                ui.present("Please enter the message you want to send");
                String content = scanner.nextLine();
                conversationMenuController.reply(username, conversationIdFinal, content);
                break;
            }
            case "20": {
                ui.present("Please enter the event name");
                String eventName = scanner.nextLine();
                ui.present("Please enter the message that you want to send");
                String message = scanner.nextLine();
                if (!eventManager.isEvent(eventName)) {
                    ui.showError("EDE");
                    break;
                }
                boolean messageByEvent = messengerMenuController.organizerMessageByEvent(username, eventName, message);
                if (messageByEvent) {
                    ui.present("Sent");
                    break;
                }
                ui.present("Something went wrong");
                break;
            }
        }
    }

    /**
     * Returns a List of usernames of all Users in this conference of the user type provided in the parameter.
     * @param type Type of user (options: "attendee", "organizer", "speaker" and "all") (param_type: String)
     * @return List<String> of all usernames of the provided type
     */
    public List<String> listOfUsers(String type){

        List<String> users = new ArrayList<>();
        if(type.equals("attendee") || type.equals("all")){
            users.addAll(attendeeManager.getAllAttendeeIds());
        }
        if(type.equals("organizer") || type.equals("all")){
            users.addAll(organizerManager.getAllOrganizerIds());
        }
        if(type.equals("speaker") || type.equals("all")){
            users.addAll(speakerManager.getAllSpeakerIds());
        }
        if(type.equals("admin") || type.equals("all")){
                users.add(adminManager.getAdminName());
        }
        return users;

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
     * Create a new account through organizer
     * @param username username of the new account
     * @param password password of the new account
     * @param accountType type of account
     * @return boolean reflecting the result
     */
    public boolean createNewAccount(String username, String password, String accountType){
        return accountHandler.signup(username, password, accountType);
    }

    // -----

    /**
     * By the end of the execution of this method, the Entities.Organizer with username </organizerUsername> would have
     * created a room with Id </roomId> and capacity </capacity>
     * @author Ashwin Karthikeyan
     * @param username: the username of the Entities.Organizer who wants to create a new room (param_type: String)
     * @param roomId: an ID for the new room that the Entities.Organizer wants to create (param_type: String)
     * @param capacity: the capacity of the new room being created (param_type: int)
     * @return : "RAE" - room already exists
     *           "ODE" - organizer doesn't exist
     */
    public String organizerAddNewRoom(String username, String roomId, int capacity, boolean hasProjector, boolean hasAudioSystem, int powerSockets){
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
     * @param eventName name of the event
     * @param speakerNames updated names of the speakers who are are talking at this event
     * @param roomId room Id of the room at which this event has to happen
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
    public String changeSpeakerForEventThroughOrganizer(String username, String eventName, List<String> speakerNames, String roomId){

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
     * @param eventName name of the event
     * @param eventTime New start time of the event
     * @param roomId New room at which this event is expected to be scheduled in
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
    public String changeEventStartTime(String username, String eventName, String eventTime, String roomId){

        String speakerErr;
        if(eventManager.isEvent(eventName)) {
            List<String> speakerNames = eventManager.getSpeakerEvent(eventName);
            int eventDuration = eventManager.getDuration(eventName);
            int eventCapacity = eventManager.getEventCapacity(eventName);
            String subjectLine = eventManager.getEventSubjectLine(eventName);
            userEventController.removeCreatedEvent(username, eventName);
            for (String speakerName : speakerNames) {
                speakerErr = checkIfSpeakerFreeAtTimeFor(speakerName, eventTime, eventDuration);
                if(!speakerErr.equals("YES")){
                    return speakerErr;
                }
            }
            String err = userEventController.createEventInRoom(username, eventName, eventTime, eventDuration, eventCapacity, speakerNames, roomId, subjectLine);
            if (err.equals("YES")) {
                return "YES";
            }
            else {
                return err;
            }
        }
        else{
            return "EDE";
        }

    }


    // case 11

    /**
     * Returns a List of titles of events this organizer isn't attending
     * @param username username of the organizer
     * @return List</String>
     */
    public List<String> organizerEventsNotAttending(String username){

        List<String> printableEventsInfo = new ArrayList<>();
        List<String> eventsNotSignedUpFor = userEventController.getOrganizerEventsNotAttending(username);
        for (String event : eventsNotSignedUpFor)
            printableEventsInfo.add("Event Title: " + event + "\nTime: " + eventManager.getStartTime(event) + "(" + eventManager.getDuration(event) + " hours)" + "\nRoom: " + eventManager.getRoomNumber(event) + "\nSpeaker: " + eventManager.getSpeakerEvent(event) + "\n");
        return printableEventsInfo;

    }

    /**
     * Sign up for event
     * @param eventName name of event
     * @param organizerId username of the Organizer
     */
    public void signUpAsOrganizer(String eventName, String organizerId){
        userEventController.enrolUserInEvent(organizerId,eventName);
    }

    /**
     * Cancels the spot for this organizer in event
     * @param eventName name of the event
     * @param organizerId username of the organizer
     */
    public void cancelSpotAsOrganizer(String eventName, String organizerId){
        userEventController.cancelSeatForUser(organizerId,eventName);
    }

    /**
     * Returns List of event titles of events that this organizer is attending
     * @param username username of the organizer
     * @return List</String>
     */
    public List<String> organizerEventsAttending(String username){

        List<String> printableEventsInfo = new ArrayList<>();
        for (String event: organizerManager.getEventsAttending(username))
            printableEventsInfo.add("Event Title: " + event + "\nTime: " + eventManager.getStartTime(event) + "(" + eventManager.getDuration(event) + " hours)" + "\nRoom: " + eventManager.getRoomNumber(event) + "\nSpeaker: " + eventManager.getSpeakerEvent(event) + "\n");
        return printableEventsInfo;

    }
}