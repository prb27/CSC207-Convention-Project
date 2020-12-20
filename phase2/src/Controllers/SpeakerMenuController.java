package Controllers;

import NewUI.SpeakerPresenterTextUI;
import UseCases.*;

import java.util.List;
import java.util.Scanner;

/**
 * This class contains methods that are specific to actions that an Organizer is allowed to do. The methods in this class
 * collaborate with UseCase classes.
 * @author Khoa
 */
public class SpeakerMenuController {

    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private AdminManager adminManager;
    private AccountHandler accountHandler;
    private EventManager eventManager;
    private ConversationManager convoManager;
    private MessengerMenuController messengerMenuController;
    private UserEventController userEventController;
    private ConversationMenuController conversationMenuController;
    private RoomManager roomManager;
    private SpeakerPresenterTextUI sui;
    private PollController pollController;


    public SpeakerMenuController(AttendeeManager attendeeManager, OrganizerManager organizerManager,
                                 SpeakerManager speakerManager, AdminManager adminManager,
                                 AccountHandler accountHandler, EventManager eventManager,
                                 UserEventController userEventController, RoomManager roomManager,
                                 SpeakerPresenterTextUI speakerTextUI, MessengerMenuController messengerMenuController,
                                 ConversationManager conversationManager, ConversationMenuController convoMenuContro, PollController pollController){

        this.attendeeManager = attendeeManager;
        this.organizerManager = organizerManager;
        this.speakerManager = speakerManager;
        this.adminManager = adminManager;
        this.accountHandler = accountHandler;
        this.eventManager = eventManager;
        this.userEventController = userEventController;
        this.messengerMenuController = messengerMenuController;
        this.roomManager = roomManager;
        this.convoManager = conversationManager;
        this.conversationMenuController = convoMenuContro;
        this.sui = speakerTextUI;
        this.pollController = pollController;

    }


    /**
     * Private helper method that takes in the "speaker type" of the user and and handles the command
     * given by the user by calling the appropriate method using the appropriate controller
     * @param username: username of the currently logged in user
     */
    public boolean speakerUserCommandHandler(String username) {
        Scanner scanner = new Scanner(System.in);
        sui.speakermenu(username);
        String option = scanner.nextLine();
        switch(option) {
            case "0":
                return false;
            case "1": {
                sui.seeEventList(userEventController.seeListOfEventsForSpeaker(username));
                return true;
            }
            case "2": {
                sui.eventnameprompt();
                String eventName = scanner.nextLine();
                sui.messageprompt();
                String content = scanner.nextLine();
                messengerMenuController.speakerMessageByTalk(username, eventName, content);
                sui.showPrompt("MS");
                return true;
            }
            case "3": {
                List<String> listOfTalkNames = userEventController.seeAllEventNamesForSpeaker(username);
                sui.messageprompt();
                String content1 = scanner.nextLine();
                messengerMenuController.speakerMessageByMultiTalks(username, listOfTalkNames, content1);
                sui.showPrompt("MMS");
                return true;
            }
            case "4": {
                sui.getAttendeeToMess();
                String attendeeUsername = scanner.nextLine();
                sui.messageprompt();
                String message = scanner.nextLine();
                List<String> listOfTalkNames1 = userEventController.seeAllEventNamesForSpeaker(username);
                boolean err = messengerMenuController.speakerMessageAttendee(username, listOfTalkNames1, attendeeUsername, message);
                if (err) {
                    sui.success();
                } else {
                    sui.fail();
                }
                return true;
            }
            case "5": {
                Integer i = 1;
                for (String conversationId : speakerManager.getConversations(username)) {
                    List<String> recipientsOfConversation = convoManager.getConvoParticipants(conversationId);
                    StringBuilder recipients = new StringBuilder();
                    String k = Integer.toString(i);
                    sui.convoNumUniqueId(k, conversationId);
                    for (String recipient : recipientsOfConversation) {
                        recipients.append(recipient);
                        recipients.append(", ");
                    }
                    sui.presentRecipients(recipients);
                    i += 1;
                }
                if (speakerManager.getConversations(username).isEmpty()) {
                    sui.noConvo();
                    return true;
                }
                sui.promptConvoNumber();
                String conversationNumber = scanner.nextLine();
                String conversationIdFinal = speakerManager.getConversations(username).get(Integer.parseInt(conversationNumber) - 1);
                List<String> messagesInThisConversation = conversationMenuController.orderedMessagesInConvo(conversationIdFinal);
                sui.list(messagesInThisConversation);
                sui.rep();
                String reply = scanner.nextLine();
                if (!reply.equals("r")) {
                    return true;
                }
                sui.promptMessageToSend();
                String contents = scanner.nextLine();
                conversationMenuController.reply(username, conversationIdFinal, contents);
                return true;
            }
            case "6":{
                pollController.runPollFunctionality(username);
            }
            default: {
                sui.showError("INO");
            }
        }
        return true;
    }
}