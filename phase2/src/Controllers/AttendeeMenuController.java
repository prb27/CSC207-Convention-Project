package Controllers;


import NewUI.AttendeePresenterTextUI;
import NewUI.ErrorHandler;
import NewUI.EventMenuPresenterTextUI;
import UseCases.*;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * This class contains methods that are specific to actions that an Organizer is allowed to do. The methods in this class
 * collaborate with UseCase classes.
 * @author Ashwin
 */
public class AttendeeMenuController {

    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private AdminManager adminManager;
    private AccountHandler accountHandler;
    private EventManager eventManager;
    private UserEventController userEventController;
    private RoomManager roomManager;
    private AttendeePresenterTextUI attendeePresenterTextUI;
    private MessengerMenuController messengerMenuController;
    private ConversationManager conversationManager;
    private ConversationMenuController conversationMenuController;
    private EventMenuPresenterTextUI eventMenuPresenterTextUI;
    private ErrorHandler errorHandler;


    public AttendeeMenuController(AttendeeManager attendeeManager,
                                  OrganizerManager organizerManager, SpeakerManager speakerManager,
                                  AdminManager adminManager, AccountHandler accountHandler, EventManager eventManager,
                                  UserEventController userEventController, RoomManager roomManager,
                                 AttendeePresenterTextUI attendeePresenterTextUI, MessengerMenuController messengerMenuController,
                                  ConversationManager conversationManager, ConversationMenuController conversationMenuController,
                                  EventMenuPresenterTextUI eventMenuPresenterTextUI, ErrorHandler errorHandler){

        this.attendeeManager = attendeeManager;
        this.organizerManager = organizerManager;
        this.speakerManager = speakerManager;
        this.adminManager = adminManager;
        this.accountHandler = accountHandler;
        this.eventManager = eventManager;
        this.userEventController = userEventController;
        this.roomManager = roomManager;
        this.attendeePresenterTextUI = attendeePresenterTextUI;
        this.messengerMenuController = messengerMenuController;
        this.conversationManager = conversationManager;
        this.conversationMenuController = conversationMenuController;
        this.eventMenuPresenterTextUI = eventMenuPresenterTextUI;
        this.errorHandler = errorHandler;
    }

    public boolean attendeeUserCommandHandler(String username) {
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        switch(option) {
            case "0":
                return false;
            case "1":
                Map<String, List<String>> eventsToSignUpWithInfo = userEventController.seeAttendableEvents(username);
                for (String event : eventsToSignUpWithInfo.keySet()) {
                    attendeePresenterTextUI.present(event);
                    for (String info : eventsToSignUpWithInfo.get(event))
                        attendeePresenterTextUI.present(info);
                }
                attendeePresenterTextUI.present("\n\n");
                return true;
            case "2":
                eventMenuPresenterTextUI.promptEventNameToAdd();
                String eventName = scanner.nextLine();
                String err = userEventController.enrolUserInEvent(username, eventName);
                if (!err.equals("YES")) {
                    errorHandler.showError(err);
                } else {
                    errorHandler.success();
                }
                return true;
            case "3":
                eventMenuPresenterTextUI.promptEventTitleCancel();
                String eventname = scanner.nextLine();
                userEventController.cancelSeatForUser(username, eventname);
                eventMenuPresenterTextUI.promptNoLongerAttending(eventname);
                return true;
            case "4":
                List<String> eventsAttending = attendeeManager.getEventsAttending(username);
                eventMenuPresenterTextUI.presentEventsAttending(eventsAttending);
                return true;
            case "5":
                attendeePresenterTextUI.promptAttendeeID();
                String attendeeID = scanner.nextLine();
                attendeePresenterTextUI.promptMessageToSend();
                String content = scanner.nextLine();
                boolean error = messengerMenuController.attendeeSendMessage(username, attendeeID, content, "attendee");
                if (error) {
                    attendeePresenterTextUI.success();
                } else {
                    attendeePresenterTextUI.fail();
                }
                return true;
            case "6":
                attendeePresenterTextUI.promptForSpeakerUsername();
                String speakerName = scanner.nextLine();
                attendeePresenterTextUI.promptForMessageToSend();
                String message = scanner.nextLine();
                boolean error1 = messengerMenuController.attendeeSendMessage(username, speakerName, message, "speaker");
                if (error1) {
                    attendeePresenterTextUI.success();
                } else {
                    attendeePresenterTextUI.fail();
                }
                return true;
            case "7":
                Integer i = 1;
                for (String conversationId : attendeeManager.getConversations(username)) {
                    List<String> recipientsOfConversation = conversationManager.getConvoParticipants(conversationId);
                    StringBuilder recipients = new StringBuilder();
                    String k = i.toString();
                    attendeePresenterTextUI.convoNumUniqueId(k, conversationId);
                    for (String recipient : recipientsOfConversation) {
                        recipients.append(recipient);
                        recipients.append(", ");
                    }
                    attendeePresenterTextUI.presentRecipients(recipients);
                    i += 1;
                }
                if (attendeeManager.getConversations(username).isEmpty()){
                    attendeePresenterTextUI.noConvo();
                    return true;
                }
                attendeePresenterTextUI.promptConvoNumber();
                String conversationNumber = scanner.nextLine();
                String conversationIdFinal = attendeeManager.getConversations(username).get(Integer.parseInt(conversationNumber) - 1);
                List<String> messagesInThisConversation = conversationMenuController.orderedMessagesInConvo(conversationIdFinal);
                attendeePresenterTextUI.presentMessageInConvo(messagesInThisConversation);
                attendeePresenterTextUI.promptToReply();
                String reply = scanner.nextLine();
                if (!reply.equals("r")) {
                    return true;
                }
                attendeePresenterTextUI.promptMessageToSend();
                String contents = scanner.nextLine();
                conversationMenuController.reply(username, conversationIdFinal, contents);
                break;
            case "8":
                attendeePresenterTextUI.promptAttendeeUsernameAdded();
                String friendName = scanner.nextLine();
                if (!attendeeManager.isAttendee(friendName)) {
                    errorHandler.showError("UDE");
                    return true;
                }
                String errorCode = attendeeManager.aAddContactB(username, friendName);
                if(errorCode.equals("No"))
                    attendeePresenterTextUI.friendContactAlreadyExist(friendName);
                else
                    attendeePresenterTextUI.success();
                return true;
            default: {
                attendeePresenterTextUI.showError("INO");
            }
        }
        return true;
    }


}
