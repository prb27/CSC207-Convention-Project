package Controllers;

import Gateways.ProgramGenerator;
import Presenters.*;
import Presenters.Attendee.*;
import Presenters.Organizer.*;
import Presenters.Speaker.*;

import UseCases.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


/**
 * Class that stores the instances of use case and controller classes and controls
 * the flow of the program by getting user input and and using other controllers to
 * execute tasks and display results using the UI.
 * @author Akshat Ayush
 */
public class MasterSystem {



    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private AdminManager adminManager;

    private EventManager eventManager;
    private RoomManager roomManager;

    private ConversationManager conversationManager;
    private MessageManager messageManager;

    private SceneHandler sceneHandler;


    private AccountHandler accountHandler;

    private AttendeeEventMenuController attendeeEventMenuController;
    private AttendeeMessagingDashboardMenuController attendeeMessagingDashboardMenuController;
    private ConversationMenuController conversationMenuController;
    private EventMenuController eventMenuController;
    private EventsSearchEngine eventsSearchEngine;
    private MessengerMenuController messengerMenuController;
    private OrganizerMenuController organizerMenuController;
    private SpeakerMenuController speakerMenuController;
    private SpeakerMessagingDashboardMenuController speakerMessagingDashboardMenuController;
    private OrganizerMessagingDashboardMenuController organizerMessagingDashboardMenuController;
    private LoginMenuController loginMenuController;
    private SignUpMenuController signUpMenuController;
    private AdminMenuController adminMenuController;

    private UserEventController userEventController;
    private ProgramGenerator programGenerator;


    /**
     * Constructor method to initialize a new MasterSystem instance with the instances of the use case classes
     * with data loaded from the database
     * @param attendeeManager: instance of AttendeeManager
     * @param organizerManager: instance of OrganizerManager
     * @param speakerManager: instance of SpeakerManager
     * @param adminManager: instance of AdminManager
     * @param messageManager: instance of MessageManager
     * @param conversationManager: instance of ConversationManager
     * @param eventManager: instance of EventManager
     * @param roomManager: instance of RoomManager
     */
    public MasterSystem(AttendeeManager attendeeManager, OrganizerManager organizerManager, SpeakerManager speakerManager,
                        AdminManager adminManager, MessageManager messageManager, ConversationManager conversationManager,
                        EventManager eventManager, RoomManager roomManager, ProgramGenerator programGenerator) {
        this.attendeeManager = attendeeManager;
        this.organizerManager = organizerManager;
        this.speakerManager = speakerManager;
        this.adminManager = adminManager;
        this.messageManager = messageManager;
        this.conversationManager = conversationManager;
        this.eventManager = eventManager;
        this.roomManager = roomManager;
        this.programGenerator = programGenerator;
    }


    /**
     * A method that is responsible for the flow of the program by taking user input,
     * using controllers to execute actions and displaying the result using the UI.
     */
    public void run(){
            this.accountHandler = new AccountHandler(attendeeManager, organizerManager, speakerManager, adminManager);
            this.attendeeEventMenuController = new AttendeeEventMenuController(attendeeManager, eventManager);
            this.attendeeMessagingDashboardMenuController = new AttendeeMessagingDashboardMenuController(attendeeManager,
                    conversationManager);
            this.conversationMenuController = new ConversationMenuController(attendeeMessagingDashboardMenuController,
                    speakerMessagingDashboardMenuController, conversationManager, messageManager);
            this.eventMenuController = new EventMenuController();
            this.eventsSearchEngine = new EventsSearchEngine(eventManager);


            this.loginMenuController = new LoginMenuController(accountHandler);
            this.signUpMenuController = new SignUpMenuController(accountHandler);

            this.messengerMenuController = new MessengerMenuController(messageManager, attendeeManager,
                    organizerManager, speakerManager, eventManager, accountHandler, conversationManager);
            this.organizerMenuController = new OrganizerMenuController(attendeeManager, organizerManager,
                    speakerManager,adminManager, accountHandler, eventManager, userEventController, roomManager);
            this.speakerMenuController = new SpeakerMenuController(attendeeManager, organizerManager, speakerManager,
                    eventManager, roomManager, conversationManager, messageManager, "");
            this.speakerMessagingDashboardMenuController = new SpeakerMessagingDashboardMenuController(speakerManager,
                    conversationManager);
            this.organizerMessagingDashboardMenuController = new OrganizerMessagingDashboardMenuController(organizerManager,
                    conversationManager);
            this.userEventController = new UserEventController(attendeeManager, organizerManager,
                    speakerManager, eventManager, roomManager);

            this.adminMenuController = new AdminMenuController(attendeeManager, speakerManager, organizerManager, conversationManager,
                conversationMenuController, eventManager, messageManager);

            this.sceneHandler = new SceneHandler();


            /* Create an organizer account when a new Controllers.MasterSystem object is created
             * to allow for the conference to have at least one organizer*/
            accountHandler.signup("organizer1", "organizer1", "organizer");

            /* Create an admin, since there is no way to sign up an admin account*/
            accountHandler.signup("admin", "admin", "admin");
        }
        public LoginMenuController getLoginMenuController(){
            return this.loginMenuController;
        }
        public ProgramGenerator getProgramGenerator(){
            return this.programGenerator;
        }
        public SignUpMenuController getSignUpMenuController(){
            return this.signUpMenuController;
        }
        public AttendeeEventMenuController getAttendeeEventMenuController(){
            return this.attendeeEventMenuController;
        }
        public AttendeeMessagingDashboardMenuController getAttendeeMessagingDashboardMenuController(){
            return this.attendeeMessagingDashboardMenuController;
        }
        public ConversationMenuController getConversationMenuController(){
            return this.conversationMenuController;
        }
        public EventMenuController getEventMenuController(){
            return this.eventMenuController;
        }
        public EventsSearchEngine getEventsSearchEngine(){
            return this.eventsSearchEngine;
        }
        public MessengerMenuController getMessengerMenuController(){
            return this.messengerMenuController;
        }
        public OrganizerMenuController getOrganizerMenuController(){
            return this.organizerMenuController;
        }
        public SpeakerMenuController getSpeakerMenuController(){
            return this.speakerMenuController;
        }
        public SpeakerMessagingDashboardMenuController getSpeakerMessagingDashboardMenuController(){
            return this.speakerMessagingDashboardMenuController;
        }
        public UserEventController getUserEventController(){
            return this.userEventController;
        }
        public SceneHandler getSceneHandler(){
            return this.sceneHandler;
        }
        public AccountHandler getAccountHandler(){
            return this.accountHandler;
        }
        public RoomManager getRoomManager(){
            return this.roomManager;
        }

        public OrganizerMessagingDashboardMenuController getOrganizerMessagingDashboardController() {
            return organizerMessagingDashboardMenuController;
        }

        public AdminMenuController getAdminMenuController() {
            return adminMenuController;
        }

    // Everything below is from Phase 1. We will be scraping the GUI and only implementing the TextUI now.

    /**
     *
     * @return the eventManager
     */
    public EventManager getEventManager() {
        return eventManager;
    }

    /**
     * A method that is responsible for the flow of the program by taking user input,
     * using controllers to execute actions and displaying the result using the UI.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String currentUsername = null;
        String currentAccountType = null;
        boolean loggedIn = false;

        while(!loggedIn) {

            String tempUsername;
            String tempPassword;
            String tempAccountType;

            ui.landingmenu();
            String landingOption = scanner.nextLine();

            switch (landingOption) {
                case "0":
                    programGenerator.saveToFile(this, "conference_system");
                    return;
                case "1":
                    ui.usernameprompt();
                    tempUsername = scanner.nextLine();
                    ui.passwordprompt();
                    tempPassword = scanner.nextLine();
                    tempAccountType = accountHandler.login(tempUsername, tempPassword);

                    if (tempAccountType != null) {
                        currentUsername = tempUsername;
                        currentAccountType = tempAccountType;
                        loggedIn = true;
                    } else {
                        ui.showPrompt("LF");
                    }
                    break;

                case "2":
                    ui.signupmenu();
                    ui.usernameprompt();
                    tempUsername = scanner.nextLine();
                    ui.passwordprompt();
                    tempPassword = scanner.nextLine();
                    if (accountHandler.signup(tempUsername, tempPassword, "attendee")) {
                        ui.showPrompt("UC");
                    } else {
                        ui.showPrompt("SF");
                    }
                    break;

                default:
                    ui.showError("INO");
            }

            while(loggedIn) {

                switch(currentAccountType) {
                    case "attendee":
                        ui.attendeemenu(currentUsername);
                        break;
                    case "organizer":
                        ui.organizermenu(currentUsername);
                        break;
                    case "speaker":
                        ui.speakermenu(currentUsername);
                        break;
                }

                String option = scanner.nextLine();
                if (option.equals("0")) {
                    loggedIn = false;
                    currentUsername = null;
                    programGenerator.saveToFile(this, "conference_system");
                } else {
                    userCommandHandler(option, currentUsername, currentAccountType);
                }
            }
        }

    }


    /**
     * Private helper method that takes in the "admin type" and handles the command
     * given by the user by calling the appropriate method using the appropriate controller
     * @param option: option selected by the user
     * @param username: username of the currently logged in user
     */
    private void adminUserCommandHandler(String option, String username) {
        Scanner scanner = new Scanner(System.in);
        switch(option) {
            case "1":
                ui.present("Please confirm that you want to delete all events without attendees (Y/N)");
                String answer = scanner.nextLine();
                if (answer.equals("Y")) {
                    adminMenuController.deleteEventWithoutAttendee();
                    ui.present("all events without attendees were deleted");
                }
                ui.present("\n\n");
                break;
            case "2":
                ui.present("Please enter the message id you want to delete");
                String message = scanner.nextLine();
                adminMenuController.deleteMessage(message);
                ui.present("message with id "+message+" was deleted");
                ui.present("\n\n");
                break;
        }
    }

    /**
     * Private helper method that takes in the "attendee type" and handles the command
     * given by the user by calling the appropriate method using the appropriate controller
     * @param option: option selected by the user
     * @param username: username of the currently logged in user
     */
    private void attendeeUserCommandHandler(String option, String username) {
        Scanner scanner = new Scanner(System.in);
        switch(option) {
            case "1":
                Map<String, List<String>> eventsToSignUpWithInfo = userEventController.seeAttendableEvents(username);
                for (String event : eventsToSignUpWithInfo.keySet()) {
                    ui.present(event);
                    for (String info : eventsToSignUpWithInfo.get(event))
                        ui.present(info);
                }
                ui.present("\n\n");
                break;
            case "2":
                ui.present("Please enter the event title you want to attend (exactly as it appears on the event list)");
                String eventName = scanner.nextLine();
                String err = userEventController.enrolUserInEvent(username, eventName);
                if (!err.equals("YES")) {
                    ui.showError(err);
                } else {
                    ui.present("Successful");
                }
                break;
            case "3":
                ui.present("Please enter the event title you wish to cancel reservation");
                String eventname = scanner.nextLine();
                userEventController.cancelSeatForUser(username, eventname);
                ui.present("You are no longer attending " + eventname);
                break;
            case "4":
                for (String event : attendeeManager.getEventsAttending(username))
                    ui.present("Event Title: " + event + "\nTime: " + eventManager.getStartTime(event) + "\nRoom: "
                            + eventManager.getRoomNumber(event) + "\nSpeaker: " + eventManager.getSpeakerEvent(event)
                            + "\nDuration: " + eventManager.getDuration(event));
                break;
            case "5":
                ui.present("Please enter attendee ID");
                String attendeeID = scanner.nextLine();
                ui.present("Please enter the message that you want to send");
                String content = scanner.nextLine();
                boolean error = messengerMenuController.attendeeSendMessage(username, attendeeID, content, "attendee");
                if (error) {
                    ui.present("Successful");
                } else {
                    ui.present("Something went wrong");
                }
                break;
            case "6":
                ui.present("Please enter the speaker's username");
                String speakerName = scanner.nextLine();
                ui.present("Please enter the message that you want to send");
                String message = scanner.nextLine();
                boolean error1 = messengerMenuController.attendeeSendMessage(username, speakerName, message, "speaker");
                if (error1) {
                    ui.present("Successful");
                } else {
                    ui.present("Something went wrong");
                }
                break;
            case "7":
                Integer i = 1;
                for (String conversationId : attendeeManager.getConversations(username)) {
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
                if (attendeeManager.getConversations(username).isEmpty()) {
                    ui.present("You have no conversations");
                    break;
                }
                ui.present("Choose a Entities.Conversation Number");
                String conversationNumber = scanner.nextLine();
                String conversationIdFinal = attendeeManager.getConversations(username).get(Integer.parseInt(conversationNumber) - 1);
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
                String contents = scanner.nextLine();
                conversationMenuController.reply(username, conversationIdFinal, contents);
                break;
            case "8":
                ui.present("Please enter the name of the attendee to be added");
                String friendName = scanner.nextLine();
                if (!attendeeManager.isAttendee(friendName)) {
                    ui.showError("UDE");
                    break;
                }
                String errorCode = attendeeManager.aAddContactB(username, friendName);
                if(errorCode.equals("No"))
                    ui.present("Attendee "+friendName+" already exist in the contact list");
                else
                    ui.present("Success!");
                break;
            default: {
                ui.showError("INO");
            }
        }
    }

    /**
     * Private helper method that takes in the "organizer type" and handles the HALF the command
     * given by the user by calling the appropriate method using the appropriate controller
     * @param option: option selected by the user
     * @param username: username of the currently logged in user
     */
    private void oUCH1(Scanner scanner, String option, String username) {
        switch (option) {
            case "1": {
                for (String attendee : attendeeManager.getAllAttendeeIds()) {
                    ui.present(attendee);
                }
                break;
            }
            case "2": {
                for (String organizer : organizerManager.getAllOrganizerIds()) {
                    ui.present(organizer);
                }
                break;
            }
            case "3": {
                for (String speaker : speakerManager.getAllSpeakerIds()) {
                    ui.present(speaker);
                }
                break;
            }
            case "4": {
                ui.present("Please enter the speaker's username");
                String speakerName = scanner.nextLine();
                ui.present("Please enter the time");
                String time = scanner.nextLine();
                if (!speakerManager.isSpeaker(speakerName)) {
                    ui.present("Not a speaker");
                    break;
                }
                List<String> allowedTimes = new ArrayList<String>();
                allowedTimes.add("9");
                allowedTimes.add("10");
                allowedTimes.add("11");
                allowedTimes.add("12");
                allowedTimes.add("1");
                allowedTimes.add("2");
                allowedTimes.add("3");
                allowedTimes.add("4");
                allowedTimes.add("5");
                if (!allowedTimes.contains(time)) {
                    ui.present("Please enter an allowed time");
                    break;
                }
                boolean free = speakerManager.isSpeakerFreeAtTime(speakerName, time);
                if (free) {
                    ui.present("No, the speaker doesn't have an event at " + time);
                } else {
                    ui.present("Yes, the speaker is talking at an event at " + time);
                }
                break;
            }
            case "5": {
                ui.present("Please enter the new organizer's username");
                String organizerUsername = scanner.nextLine();
                ui.present("Please enter the password for this new organizer");
                String organizerPassword = scanner.nextLine();
                boolean err = accountHandler.signup(organizerUsername, organizerPassword, "organizer");
                if (err) {
                    ui.present("Successful");
                } else {
                    ui.present("The username already exists");
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
     * Private helper method that takes in the "organizer type" and handles the command
     * given by the user by calling the appropriate method using the appropriate controller
     * @param option: option selected by the user
     * @param username: username of the currently logged in user
     */
    private void organizerUserCommandHandler(String option, String username) {
        if(!organizerManager.isOrganizer(username))
            return;
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        String[] slot1 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] slot2 = {"11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
        for (String slot : slot1) {
            if (slot.equals(option)) {
                choice = 1;
                break;
            }
        }
        if (choice == 0) {
            for (String slot : slot2) {
                if (slot.equals(option)) {
                    choice = 2;
                    break;
                }
            }
        }
        if (choice == 1)
            oUCH1(scanner, option, username);
        else if (choice == 2)
            oUCH2(scanner, option, username);
        else {
            ui.showError("INO");
        }
    }


    /**
     * Private helper method that takes in the "speaker type" of the user and and handles the command
     * given by the user by calling the appropriate method using the appropriate controller
     * @param option: option selected by the user
     * @param username: username of the currently logged in user
     */
    private void speakerUserCommandHandler(String option, String username) {
        Scanner scanner = new Scanner(System.in);
        switch(option) {
            case "1":
                ui.present(userEventController.seeListOfEventsForSpeaker(username).toString());
                break;
            case "2":
                ui.eventnameprompt();
                String eventName = scanner.nextLine();
                ui.messageprompt();
                String content = scanner.nextLine();
                messengerMenuController.speakerMessageByTalk(username, eventName, content);
                ui.showPrompt("MS");
                break;
            case "3":
                List<String> listOfTalkNames = userEventController.seeAllEventNamesForSpeaker(username);
                ui.messageprompt();
                String content1 = scanner.nextLine();
                messengerMenuController.speakerMessageByMultiTalks(username, listOfTalkNames, content1);
                ui.showPrompt("MMS");
                break;
            case "4":
                ui.present("Please enter the username of the Attendee you wish to message:");
                String attendeeUsername = scanner.nextLine();
                ui.messageprompt();
                String message = scanner.nextLine();
                List<String> listOfTalkNames1 = userEventController.seeAllEventNamesForSpeaker(username);
                boolean err = messengerMenuController.speakerMessageAttendee(username, listOfTalkNames1, attendeeUsername, message);
                if(err){
                    ui.present("Successful");
                }
                else{
                    ui.present("Something went wrong");
                }
                break;
            case "5":
                Integer i = 1;
                for(String conversationId: speakerManager.getConversations(username)) {
                    List<String> recipientsOfConversation = conversationManager.getConvoParticipants(conversationId);
                    StringBuilder recipients = new StringBuilder();
                    ui.present("Conversation Number " + i.toString() + "\n" + "Uniqueness Identifier: " + conversationId);
                    for (String recipient: recipientsOfConversation){
                        recipients.append(recipient);
                        recipients.append(", ");
                    }
                    ui.present("Recipients: " + recipients);
                    i += 1;
                }
                if(speakerManager.getConversations(username).isEmpty()){
                    ui.present("You have no conversations");
                    break;
                }
                ui.present("Choose a Conversation Number");
                String conversationNumber = scanner.nextLine();
                String conversationIdFinal = speakerManager.getConversations(username).get(Integer.parseInt(conversationNumber) - 1);
                List<String> messagesInThisConversation = conversationMenuController.orderedMessagesInConvo(conversationIdFinal);
                for (String s : messagesInThisConversation) {
                    ui.present(s);
                }
                ui.present("Enter \"r\" to reply in this conversation. [Any other input will exit this menu]");
                String reply = scanner.nextLine();
                if(!reply.equals("r")){
                    break;
                }
                ui.present("Please enter the message you want to send");
                String contents = scanner.nextLine();
                conversationMenuController.reply(username, conversationIdFinal, contents);
                break;
            default: {
                ui.showError("INO");
            }
        }
    }

    /**
     * Private helper method that takes in the account type of the user and and handles the command
     * given by the user by calling the appropriate method using the appropriate controller
     *
     * @param option: option selected by the user
     * @param username: username of the currently logged in user
     * @param userType: account type of the currently logged in user ("attendee", "organizer", "speaker")
     */
    private void userCommandHandler(String option, String username, String userType) {
        switch (userType) {
            case "admin":
                adminUserCommandHandler(option, username);
                break;
            case "attendee":
                attendeeUserCommandHandler(option, username);
                break;
            case "organizer":
                organizerUserCommandHandler(option, username);
                break;
            case "speaker":
                speakerUserCommandHandler(option, username);
                break;
        }
    }

}