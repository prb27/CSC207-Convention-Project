package Controllers;

import UseCases.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class OrganizerMenuController implements Serializable {

    private AccountHandler accountHandler;
    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private AdminManager adminManager;
    private EventManager eventManager;
    private RoomManager roomManager;
    private MessageController messageController;


    public OrganizerMenuController(AttendeeManager attendeeManager, OrganizerManager organizerManager, SpeakerManager speakerManager, AdminManager adminManager, AccountHandler accountHandler, EventManager eventManager, RoomManager roomManager, MessageController messageController, ConversationManager conversationManager, MessageManager messageManager, String currentUsername){

        this.attendeeManager = attendeeManager;
        this.organizerManager = organizerManager;
        this.speakerManager = speakerManager;
        this.adminManager = adminManager;
        this.accountHandler = accountHandler;
        this.eventManager = eventManager;
        this.roomManager = roomManager;
        this.messageController = messageController;

    }

    public ArrayList<String> listOfUsers(String type){

        ArrayList<String> users = new ArrayList<>();
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
            users.addAll(adminManager.getAllAdminIds());
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

    public void checkIfSpeakerFreeAt(String speakerName, String time) {

        if (!speakerManager.isSpeaker(speakerName)) {
            ui.present("Not a speaker"); // Need an equivalent
            return;
        }
        boolean free = speakerManager.isSpeakerFreeAtTime(speakerName, time);
        if (free) {
            ui.present("No, the speaker doesn't have an event at " + time); //Need an equivalent
        } else {
            ui.present("Yes, the speaker is talking at an event at " + time); //Need an equivalent
        }

    }
------// These 2 methods don't make sense -------

    //ui.present("Please enter the new organizer's username");
    //String username = scanner.nextLine();
    //ui.present("Please enter the password for this new organizer");
    //String password = scanner.nextLine();
    public void createNewOrganizerAccount(String username, String password){

        boolean err = accountHandler.signup(username, password, "organizer");
        if(err){
            ui.present("Successful");//Need an equivalent
        }
        else{
            ui.present("The username already exists");//Need an equivalent
        }

    }

    //ui.present("Please enter new speaker's username");
    //                    String speakerUsername = scanner.nextLine();
    //                    ui.present("Please enter password for this speaker");
    //                    String speakerPassword = scanner.nextLine();
    public void createNewSpeakerAccount(String username, String password){

        if(accountHandler.signup(username, password, "speaker")){
            ui.showPrompt("UC"); //Need an equivalent
        }
        else {
            ui.showPrompt("SF"); //Need an equivalent
        }

    }

-----

    // EVENT FUNCTIONS


    //ui.present("Please enter roomID:");
    //                    String roomID = scanner.nextLine();
    //                    ui.present("Please enter room capacity");
    //                    int capacity = scanner.nextInt();
    public void organizerCreateNewRoom

    public void organizerCommandHandler(String username, String option){
        Scanner scanner = new Scanner(System.in);
        if(organizerManager.isOrganizer(username)) {
            switch (option) {
                case "7": {
                    String err = organizerAddNewRoom(username, roomID, capacity);
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
                    ui.present("Please enter the speaker's username");
                    String speakerName = scanner.nextLine();
                    String err = userEventController.createEvent(username, eventName, eventTime, speakerName);
                    if (!err.equals("YES"))
                        ui.showError(err);
                    else {
                        ui.present("Successful");
                    }
                    break;
                }
                case "9": {
                    ui.present("Please enter the event name.");
                    String eventName = scanner.nextLine();
                    ui.present("Please enter new speaker's username");
                    String speakerName = scanner.nextLine();
                    if(!eventManager.isEvent(eventName)){
                        ui.showError("EDE");
                        break;
                    }
                    if(!speakerManager.isSpeaker(speakerName)){
                        ui.showError("SDE");
                        break;
                    }
                    String eventTime = eventManager.getEventTime(eventName);
                    userEventController.removeCreatedEvent(username, eventName);
                    String err = userEventController.createEvent(username, eventName, eventTime, speakerName);
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
                    ArrayList<String> speakerName = eventManager.getSpeakerEvent(eventName);
                    userEventController.removeCreatedEvent(username, eventName);
                    if(speakerManager.isSpeaker(speakerName)) {
                        String err = userEventController.createEvent(username, eventName, eventTime, speakerName);
                        if(err.equals("YES")) {
                            ui.present("Successful");
                        }
                        else{
                            ui.showError(err);
                        }
                    }
                    else{
                        ui.showError("EDE");
                    }
                    break;
                }
                case "11": {
                    ArrayList<String> eventsNotSignedUpFor = userEventController.getOrganizerEventsNotAttending(username);
                    for (String event : eventsNotSignedUpFor)
                        ui.present("Event Title: " + event + "\nTime: " + eventManager.getEventTime(event) + "\nRoom: " + eventManager.getRoomNumber(event) + "\nSpeaker: " + eventManager.getSpeakerEvent(event) + "\n");
                    break;
                }
                case "12": {
                    ui.present("Please enter the title of the event you want to attend (exactly as it appears on the list of titles displayed)");
                    String eventName = scanner.nextLine();
                    String err = userEventController.enrolUserInEvent(username, eventName);
                    if(!err.equals("YES")){
                        ui.showError(err);
                    }
                    else{
                        ui.present("Successful");
                    }
                    break;
                }
                case "13": {
                    ui.present("Please enter the event's name");
                    String eventName = scanner.nextLine();
                    cancelSeatForOrganizer(username, eventName);
                    ui.present("You are no longer attending " + eventName);
                    break;
                }
                case "14": {
                    for (String event: organizerManager.getEventsAttending(username))
                        ui.present("Event Title: " + event + "\nTime: " + eventManager.getEventTime(event) + "\nRoom: " + eventManager.getRoomNumber(event) + "\nSpeaker: " + eventManager.getSpeakerEvent(event) + "\n");
                    break;
                }
                case "15": {
                    ui.present("Please enter attendee ID");
                    String attendeeID = scanner.nextLine();
                    ui.present("Please enter the message that you want to send");
                    String content = scanner.nextLine();
                    boolean err = organizerSendMessageToSingle(username, attendeeID, content, "attendee");
                    if(err){
                        ui.present("Successful");
                    }
                    else{
                        ui.present("Something went wrong");
                    }
                    break;
                }
                case "16": {
                    ui.present("Please enter the message that you want to send");
                    String content = scanner.nextLine();
                    organizerSendMessageToAll(username, content, "attendee");
                    break;
                }
                case "17": {
                    ui.present("Please enter the speaker's username");
                    String speakerName = scanner.nextLine();
                    ui.present("Please enter the message that you want to send");
                    String content = scanner.nextLine();
                    organizerSendMessageToSingle(username, speakerName, content, "speaker");
                    break;
                }
                case "18": {
                    ui.present("Please enter the message that you want to send");
                    String content = scanner.nextLine();
                    organizerSendMessageToAll(username, content, "speaker");
                    break;
                }
                case "19": {
                    Integer i = 1;
                    for(String conversationId: organizerManager.getConversations(username)) {
                        ArrayList<String> recipientsOfConversation = conversationManager.getConvoParticipants(conversationId);
                        StringBuilder recipients = new StringBuilder();
                        ui.present("Conversation Number " + i.toString() + "\n" + "Uniqueness Identifier: " + conversationId);
                        for (String recipient: recipientsOfConversation){
                            recipients.append(recipient);
                            recipients.append(", ");
                        }
                        ui.present("Recipients: " + recipients);
                        i += 1;
                    }
                    if(organizerManager.getConversations(username).isEmpty()){
                        ui.present("You have no conversations");
                        break;
                    }
                    ui.present("Choose a Entities.Conversation Number");
                    String conversationNumber = scanner.nextLine();
                    String conversationIdFinal = organizerManager.getConversations(username).get(Integer.parseInt(conversationNumber) - 1);
                    ArrayList<String> messagesInThisConversation = messageController.orderedMessagesInConvo(conversationIdFinal);
                    for (String s : messagesInThisConversation) {
                        ui.present(s);
                    }
                    ui.present("Enter \"r\" to reply in this conversation. [Any other input will exit this menu]");
                    String reply = scanner.nextLine();
                    if(!reply.equals("r")){
                        break;
                    }
                    ui.present("Please enter the message you want to send");
                    String content = scanner.nextLine();
                    messageController.reply(username, conversationIdFinal, content);
                    break;
                }
                case "20":{
                    ui.present("Please enter the event name");
                    String eventName = scanner.nextLine();
                    ui.present("Please enter the message that you want to send");
                    String message = scanner.nextLine();
                    if(!eventManager.isEvent(eventName)){
                        ui.showError("EDE");
                        break;
                    }
                    boolean messageByEvent = messageController.organizerMessageByEvent(username, eventName, message);
                    if(messageByEvent){
                        ui.present("Sent");
                        break;
                    }
                    ui.present("Something went wrong");
                    break;
                }
                default: {
                    ui.showError("INO");
                }
            }
        }
    }

}
