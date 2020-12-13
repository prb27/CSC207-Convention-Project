package Controllers;

import UseCases.*;

import javax.sound.sampled.AudioSystem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrganizerMenuController implements Serializable {

    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private AdminManager adminManager;
    private AccountHandler accountHandler;
    private EventManager eventManager;
    private MessageController messageController;
    private ConversationManager conversationManager;
    private UserEventController userEventController;
    private RoomManager roomManager;


    public OrganizerMenuController(AttendeeManager attendeeManager, OrganizerManager organizerManager, SpeakerManager speakerManager, AdminManager adminManager, AccountHandler accountHandler, EventManager eventManager, MessageController messageController, ConversationManager conversationManager, UserEventController userEventController, RoomManager roomManager){

        this.attendeeManager = attendeeManager;
        this.organizerManager = organizerManager;
        this.speakerManager = speakerManager;
        this.adminManager = adminManager;
        this.accountHandler = accountHandler;
        this.eventManager = eventManager;
        this.messageController = messageController;
        this.conversationManager = conversationManager;
        this.userEventController = userEventController;
        this.roomManager = roomManager;

    }

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
        //if(type.equals("admin") || type.equals("all")){
        //            users.addAll(adminManager.getAllAdminIds());
        //        }
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

    public String checkIfSpeakerFreeAt(String speakerName, String time) {

        if (!speakerManager.isSpeaker(speakerName)) {
            return "SDE"; //Refer to TextUserInterface
        }
        boolean free = speakerManager.isSpeakerFreeAtTime(speakerName, time);
        if (free) {
            return "NO";
        } else {
            return "YES";
        }

    }

    // ------// These 2 functionalities can be handled GUI -------

    //ui.present("Please enter the new organizer's username");
    //String username = scanner.nextLine();
    //ui.present("Please enter the password for this new organizer");
    //String password = scanner.nextLine();
    //public void createNewOrganizerAccount(String username, String password){

        //boolean err = accountHandler.signup(username, password, "organizer");
        //        if(err){
        //            ui.present("Successful");//Need an equivalent
        //        }
        //        else{
        //            ui.present("The username already exists");//Need an equivalent
        //        }

    //}

    //ui.present("Please enter new speaker's username");
    //                    String speakerUsername = scanner.nextLine();
    //                    ui.present("Please enter password for this speaker");
    //                    String speakerPassword = scanner.nextLine();
    public String createNewSpeakerAccount(String username, String password){

        if(accountHandler.signup(username, password, "speaker")){
            return "UC"; //Refer to TextUserInterface
        }
        else {
            return "SF"; //Refer to TextUserInterface
        }

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


    // This requires can be performed by calling three functions from controller classes.
    //
    // public String changeSpeakerForEventThroughOrganizer(String username, String eventName, List<String> speakerNames){
    //
    //        if(!eventManager.isEvent(eventName)){
    //            return "EDE"; //Refer to TextUserInterface
    //        }
    //        for(String speakerName: speakerNames) {
    //            if (!speakerManager.isSpeaker(speakerName)) {
    //                return "SDE"; //Refer to TextUserInterface
    //            }
    //        }
    //        String eventStartTime = eventManager.getStartTime(eventName);
    //        int eventDuration = eventManager.getDuration(eventName);
    //        int eventCapacity = eventManager.getEventCapacity(eventName);
    //        userEventController.removeCreatedEvent(username, eventName);
    //        String err = userEventController.createEventInRoom(username, eventName, eventStartTime, eventDuration, eventCapacity, speakerNames);
    //        if (!err.equals("YES"))
    //            return err; //Refer to TextUserInterface
    //        else {
    //            return "Successful"; //Refer to TextUserInterface
    //        }
    //
    //    }

    // case 10
    //ui.present("Please enter the event name");
    //        String eventName = scanner.nextLine();
    //        ui.present("Please enter a new start time for the event");
    //        String eventTime = scanner.nextLine();


    // The implementation of this functionality would change
    //
    // public String changeEventTime(String username, String eventName, String eventTime){
    //
    //        if(eventManager.isEvent(eventName)) {
    //            List<String> speakerNames = eventManager.getSpeakerEvent(eventName);
    //            int eventDuration = eventManager.getDuration(eventName);
    //            int eventCapacity = eventManager.getEventCapacity(eventName);
    //            String roomId = eventManager.getRoomNumber(eventName);
    //            userEventController.removeCreatedEvent(username, eventName);
    //            for (String speakerName : speakerNames) {
    //                if (!speakerManager.isSpeaker(speakerName)) {
    //                    return "SDE";
    //                }
    //            }
    //            String err = userEventController.createEventInRoom(username, eventName, eventTime, eventDuration, eventCapacity, speakerNames, roomId);
    //            if (err.equals("YES")) {
    //                return "Successful";
    //            } else {
    //                return err;
    //            }
    //        }
    //        else{
    //            return "EDE";
    //        }
    //
    //    }

    // case 11
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


    //case "13": {
    //                    ui.present("Please enter the event's name");
    //                    String eventName = scanner.nextLine();
    //                    userEventController.cancelSeatForUser(username, eventName);
    //                    ui.present("You are no longer attending " + eventName);
    //                    break;
    //                }
    // Can be done by the GUI


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

}
