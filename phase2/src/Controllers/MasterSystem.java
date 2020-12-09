package Controllers;

import Gateways.ProgramGenerator;
import UI.TextUserInterface;
import UseCases.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

/**
 * Class that stores the instances of use case and controller classes and controls
 * the flow of the program by getting user input and and using other controllers to
 * execute tasks and display results using the UI.
 * @author Akshat Ayush
 */
public class MasterSystem implements Serializable {

    private final TextUserInterface ui;

    private final AttendeeManager attendeeManager;
    private final OrganizerManager organizerManager;
    private final SpeakerManager speakerManager;
    private final AdminManager adminManager;

    private final EventManager eventManager;
    private final RoomManager roomManager;

    private final ConversationManager conversationManager;
    private final MessageManager messageManager;

    private final AccountHandler accountHandler;

    private final LoginMenuController loginMenuController;
    private final SignUpMenuController signUpMenuController;

    private final MessageController messageController;
    private final UserEventController userEventController;

    private final ProgramGenerator programGenerator;

    /**
     * Constructor method to initialize a new Controllers.MasterSystem instance in case
     * deserializing from the file fails. A newly created Controllers.MasterSystem adds an
     * organizer with username: admin and password: admin to the conference as
     * users can't create organizer accounts themselves.
     */
    public MasterSystem() {
        this.ui = new TextUserInterface();
        this.attendeeManager = new AttendeeManager();
        this.organizerManager = new OrganizerManager();
        this.speakerManager = new SpeakerManager();
        this.adminManager = new AdminManager();

        this.eventManager = new EventManager();
        this.roomManager = new RoomManager();
        this.conversationManager = new ConversationManager();
        this.messageManager = new MessageManager();
        this.accountHandler = new AccountHandler(attendeeManager, organizerManager, speakerManager, adminManager);

        this.loginMenuController = new LoginMenuController(accountHandler);
        this.signUpMenuController = new SignUpMenuController(accountHandler);

        this.messageController = new MessageController(attendeeManager, organizerManager,
                speakerManager, eventManager, conversationManager, messageManager);
        this.userEventController = new UserEventController(attendeeManager, organizerManager,
                speakerManager, eventManager, roomManager);
        this.programGenerator = new ProgramGenerator();

        /* Create an organizer account when a new Controllers.MasterSystem object is created
        * to allow for the conference to have at least one organizer*/
        accountHandler.signup("admin", "admin", "organizer");
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
     * Private helper method that takes in the account type of the user and and handles the command
     * given by the user by calling the appropriate method using the appropriate controller
     *
     * @param option: option selected by the user
     * @param username: username of the currently logged in user
     * @param userType: account type of the currently logged in user ("attendee", "organizer", "speaker")
     */
    private void userCommandHandler(String option, String username, String userType) {

        Scanner scanner = new Scanner(System.in);
        switch(userType) {
            case "attendee":
                switch(option) {
                    case "1":
                        Hashtable<String, List<String>> eventsNotSignedUpFor = userEventController.seeAttendableEvents(username);
                        for (String event : eventsNotSignedUpFor.keySet()) {
                            ui.present(event);
                            for (String eventInfo : eventsNotSignedUpFor.get(event))
                                ui.present(eventInfo);
                        }
                        ui.present("\n\n");
                        break;
                    case "2":
                        ui.present("Please enter the title of the event you want to attend (exactly as it appears on the list of titles displayed)");
                        String eventName = scanner.nextLine();
                        String err = userEventController.enrolUserInEvent(username, eventName);
                        if (!err.equals("YES")) {
                            ui.showError(err);
                        } else {
                            ui.present("Successful");
                        }
                        break;
                    case "3":
                        ui.present("Please enter the event's name");
                        String eventname = scanner.nextLine();
                        userEventController.cancelSeatForUser(username, eventname);
                        ui.present("You are no longer attending " + eventname);
                        break;
                    case "4":
                        for (String event : attendeeManager.getEventsAttending(username))
                            ui.present("Event Title: " + event + "\nTime: " + eventManager.getStartTime(event) + "(" + eventManager.getDuration(event) + " hours)" + "\nRoom: " + eventManager.getRoomNumber(event) + "\nSpeaker: " + eventManager.getSpeakerEvent(event) + "\n");
                        break;
                    case "5":
                        ui.present("Please enter attendee ID");
                        String attendeeID = scanner.nextLine();
                        ui.present("Please enter the message that you want to send");
                        String content = scanner.nextLine();
                        boolean error = messageController.attendeeSendMessage(username, attendeeID, content, "attendee");
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
                        messageController.organizerSendMessageToSingle(username, speakerName, message, "speaker");
                        boolean error1 = messageController.attendeeSendMessage(username, speakerName, message, "speaker");
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
                        ArrayList<String> messagesInThisConversation = messageController.orderedMessagesInConvo(conversationIdFinal);
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
                        messageController.reply(username, conversationIdFinal, contents);
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
                break;
            case "speaker":
                switch(option) {
                    case "1":
                        ui.present(userEventController.seeListOfEventsForSpeaker(username).toString());
                        break;
                    case "2":
                        ui.eventnameprompt();
                        String eventName = scanner.nextLine();
                        ui.messageprompt();
                        String content = scanner.nextLine();
                        messageController.speakerMessageByTalk(username, eventName, content);
                        ui.showPrompt("MS");
                        break;
                    case "3":
                        ArrayList<String> listOfTalkNames = userEventController.seeAllEventNamesForSpeaker(username);
                        ui.messageprompt();
                        String content1 = scanner.nextLine();
                        messageController.speakerMessageByMultiTalks(username, listOfTalkNames, content1);
                        ui.showPrompt("MMS");
                        break;
                    case "4":
                        ui.present("Please enter the username of the Attendee you wish to message:");
                        String attendeeUsername = scanner.nextLine();
                        ui.messageprompt();
                        String message = scanner.nextLine();
                        ArrayList<String> listOfTalkNames1 = userEventController.seeAllEventNamesForSpeaker(username);
                        boolean err = messageController.speakerMessageAttendee(username, listOfTalkNames1, attendeeUsername, message);
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
                        String contents = scanner.nextLine();
                        messageController.reply(username, conversationIdFinal, contents);
                        break;
                    default: {
                        ui.showError("INO");
                    }
                }
                break;
        }
    }
}