package Controllers;

import UseCases.*;

import java.util.ArrayList;
import java.util.List;

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
    private MessageController messageController;
    private UserEventController userEventController;
    private RoomManager roomManager;


    public OrganizerMenuController(AttendeeManager attendeeManager, OrganizerManager organizerManager, SpeakerManager speakerManager, AdminManager adminManager, AccountHandler accountHandler, EventManager eventManager, MessageController messageController, UserEventController userEventController, RoomManager roomManager){

        this.attendeeManager = attendeeManager;
        this.organizerManager = organizerManager;
        this.speakerManager = speakerManager;
        this.adminManager = adminManager;
        this.accountHandler = accountHandler;
        this.eventManager = eventManager;
        this.messageController = messageController;
        this.userEventController = userEventController;
        this.roomManager = roomManager;

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
                users.add("Admin: " + adminManager.getAdminName());
        }
        return users;

    }

    // This can be handled at the GUI level
    //ui.present("Please enter the speaker's username");
    //        String speakerName = scanner.nextLine();
    //        ui.present("Please enter the time");
    //        String time = scanner.nextLine();
    // Drop down menu offers only the following times
    //        allowedTimes.add("9");
    //        allowedTimes.add("10");
    //        allowedTimes.add("11");
    //        allowedTimes.add("12");
    //        allowedTimes.add("1");
    //        allowedTimes.add("2");
    //        allowedTimes.add("3");
    //        allowedTimes.add("4");
    //        allowedTimes.add("5");
    //

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

    public boolean createNewAccount(String username, String password, String accountType){
        return accountHandler.signup(username, password, accountType);
    }

    // -----


    // EVENT FUNCTIONS


    //case 7
    //ui.present("Please enter roomID:");
    //                    String roomID = scanner.nextLine();
    //                    ui.present("Please enter room capacity");
    //                    int capacity = scanner.nextInt();
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


    // contact Arib or Ashwin for details on how to create an Event
    // case "8": {
    //                    ui.present("Please enter event name");
    //                    String eventName = scanner.nextLine();
    //                    ui.present("Please enter event time");
    //                    String eventTime = scanner.nextLine();
    //                    ui.present("Please enter event duration");
    //                    String speakerName = scanner.nextLine();
    //                    String err = userEventController.createEvent(username, eventName, eventTime, speakerName);
    //                    if (!err.equals("YES"))
    //                        ui.showError(err);
    //                    else {
    //                        ui.present("Successful");
    //                    }
    //                    break;
    //                }
    // This can also be handled by the GUI (Note: you will have to handle the new changes made to createEvent.)

    // case 9
    //ui.present("Please enter the event name.");
    //        String eventName = scanner.nextLine();
    //        ui.present("Please enter new speaker's username"); You should allow multiple speaker names to be entered
    //        String speakerName = scanner.nextLine();


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
        for(String speakerName: speakerNames) {
            speakerErr = checkIfSpeakerFreeAtTimeFor(speakerName, eventStartTime, eventDuration);
            if(!speakerErr.equals("YES")){
                return speakerErr;
            }
        }
        userEventController.removeCreatedEvent(username, eventName);
        String err = userEventController.createEventInRoom(username, eventName, eventStartTime, eventDuration, eventCapacity, speakerNames, roomId);
        if (!err.equals("YES"))
            return err; //Refer to TextUserInterface
        else {
            return "YES"; //Refer to TextUserInterface
        }

    }

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
            userEventController.removeCreatedEvent(username, eventName);
            for (String speakerName : speakerNames) {
                speakerErr = checkIfSpeakerFreeAtTimeFor(speakerName, eventTime, eventDuration);
                if(!speakerErr.equals("YES")){
                    return speakerErr;
                }
            }
            String err = userEventController.createEventInRoom(username, eventName, eventTime, eventDuration, eventCapacity, speakerNames, roomId);
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
     *
     * @param username
     * @return
     */
    public List<String> organizerEventsNotAttending(String username){

        List<String> printableEventsInfo = new ArrayList<>();
        List<String> eventsNotSignedUpFor = userEventController.getOrganizerEventsNotAttending(username);
        for (String event : eventsNotSignedUpFor)
            printableEventsInfo.add("Event Title: " + event + "\nTime: " + eventManager.getStartTime(event) + "(" + eventManager.getDuration(event) + " hours)" + "\nRoom: " + eventManager.getRoomNumber(event) + "\nSpeaker: " + eventManager.getSpeakerEvent(event) + "\n");
        return printableEventsInfo;

    }

    // case 12
    //public String OrganizerSignUpForEvent(){
    //
    //        ui.present("Please enter the title of the event you want to attend (exactly as it appears on the list of titles displayed)");
    //        String eventName = scanner.nextLine();
    //        String err = userEventController.enrolUserInEvent(username, eventName);
    //        if(!err.equals("YES")){
    //            ui.showError(err);
    //        }
    //        else{
    //            ui.present("Successful");
    //        }
    //
    //    }
    // Can be done by the GUI

    public void signUpAsOrganizer(String eventName, String organizerId){
        eventManager.reserveAttendee(eventName, organizerId);
    }

    //case "13": {
    //                    ui.present("Please enter the event's name");
    //                    String eventName = scanner.nextLine();
    //                    userEventController.cancelSeatForUser(username, eventName);
    //                    ui.present("You are no longer attending " + eventName);
    //                    break;
    //                }
    // Can be done by the GUI

    public void cancelSpotAsOrganizer(String eventName, String organizerId){
        eventManager.removeAttendee(eventName, organizerId);
    }
    /**
     *
     * @param username
     * @return
     */
    public List<String> organizerEventsAttending(String username){

        List<String> printableEventsInfo = new ArrayList<>();
        for (String event: organizerManager.getEventsAttending(username))
            printableEventsInfo.add("Event Title: " + event + "\nTime: " + eventManager.getStartTime(event) + "(" + eventManager.getDuration(event) + " hours)" + "\nRoom: " + eventManager.getRoomNumber(event) + "\nSpeaker: " + eventManager.getSpeakerEvent(event) + "\n");
        return printableEventsInfo;

    }


    //case "15": {
    //                    ui.present("Please enter attendee ID");
    //                    String attendeeID = scanner.nextLine();
    //                    ui.present("Please enter the message that you want to send");
    //                    String content = scanner.nextLine();
    //                    boolean err = messageController.organizerSendMessageToSingle(username, attendeeID, content, "attendee");
    //                    if(err){
    //                        ui.present("Successful");
    //                    }
    //                    else{
    //                        ui.present("Something went wrong");
    //                    }
    //                    break;
    //                }
    // Can be handled by GUI


    //case "16": {
    //                    ui.present("Please enter the message that you want to send");
    //                    String content = scanner.nextLine();
    //                    messageController.organizerSendMessageToAll(username, content, "attendee");
    //                    break;
    //                }
    // Can be handled by GUI


    //case "17": {
    //                    ui.present("Please enter the speaker's username");
    //                    String speakerName = scanner.nextLine();
    //                    ui.present("Please enter the message that you want to send");
    //                    String content = scanner.nextLine();
    //                    messageController.organizerSendMessageToSingle(username, speakerName, content, "speaker");
    //                    break;
    //                }
    // Can be handled by GUI


    //case "18": {
    //                    ui.present("Please enter the message that you want to send");
    //                    String content = scanner.nextLine();
    //                    messageController.organizerSendMessageToAll(username, content, "speaker");
    //                    break;
    //                }
    // Can be handled by GUI


    // case 20
    //ui.present("Please enter the event name");
    //        String eventName = scanner.nextLine();
    //        ui.present("Please enter the message that you want to send");
    //        String message = scanner.nextLine();

    /**
     *
     * @param username
     * @param eventName
     * @param message
     * @return
     */
    public String organizerSendMessageByEvent(String username, String eventName, String message){

        if(!eventManager.isEvent(eventName)){
            return "EDE";
        }
        boolean messageByEvent = messageController.organizerMessageByEvent(username, eventName, message);
        if(messageByEvent){
            return "Successful";
        }
        return "Something went wrong";

    }

    /**
     * Method that enables an organizer to add an Attendee account.
     * @param username: username of the attendee
     * @param password: password of the attendee
     * @return "YES" - Successful signup of the attendee,
     *         "AAE" - Attendee already exists
     */
    public String  organizerCreateAttendee(String username, String password) {
        if(accountHandler.signup(username, password, "attendee"))
            return "YES";
        else
            return "AAE";
    }

    /**
     * Method that enables an organizer to add an Organizer account.
     * @param username: username of the Organizer
     * @param password: password of the Organizer
     * @return "YES" - Successful signup of the organizer,
     *         "OAE" - Organizer already exists
     */
    public String organizerCreateOrganizer(String username, String password) {
        if(accountHandler.signup(username, password, "organizer"))
            return "YES";
        else
            return "OAE";
    }

    /**
     * Method that enables an organizer to add an Speaker account.
     * @param username: username of the Speaker
     * @param password: password of the Speaker
     * @return "YES" - Successful signup of the speaker,
     *         "SAE" - Speaker already exists
     */
    public String organizerCreateSpeaker(String username, String password) {
        if(accountHandler.signup(username, password, "speaker"))
            return "YES";
        else
            return "SAE";
    }

}


    //public void organizerCommandHandler(String username, String option){
    //        Scanner scanner = new Scanner(System.in);
    //        if(organizerManager.isOrganizer(username)) {
    //            switch (option) {
    //                case "19": {
    //                    Integer i = 1;
    //                    for(String conversationId: organizerManager.getConversations(username)) {
    //                        List<String> recipientsOfConversation = conversationManager.getConvoParticipants(conversationId);
    //                        StringBuilder recipients = new StringBuilder();
    //                        ui.present("Conversation Number " + i.toString() + "\n" + "Uniqueness Identifier: " + conversationId);
    //                        for (String recipient: recipientsOfConversation){
    //                            recipients.append(recipient);
    //                            recipients.append(", ");
    //                        }
    //                        ui.present("Recipients: " + recipients);
    //                        i += 1;
    //                    }
    //                    if(organizerManager.getConversations(username).isEmpty()){
    //                        ui.present("You have no conversations");
    //                        break;
    //                    }
    //                    ui.present("Choose a Entities.Conversation Number");
    //                    String conversationNumber = scanner.nextLine();
    //                    String conversationIdFinal = organizerManager.getConversations(username).get(Integer.parseInt(conversationNumber) - 1);
    //                    List<String> messagesInThisConversation = messageController.orderedMessagesInConvo(conversationIdFinal);
    //                    for (String s : messagesInThisConversation) {
    //                        ui.present(s);
    //                    }
    //                    ui.present("Enter \"r\" to reply in this conversation. [Any other input will exit this menu]");
    //                    String reply = scanner.nextLine();
    //                    if(!reply.equals("r")){
    //                        break;
    //                    }
    //                    ui.present("Please enter the message you want to send");
    //                    String content = scanner.nextLine();
    //                    messageController.reply(username, conversationIdFinal, content);
    //                    break;
    //                }
    //            }
    //        }
    //    }