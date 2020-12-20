package Controllers;

import NewUI.AdminPresenterTextUI;
import NewUI.ErrorHandler;
import UseCases.*;
import java.util.*;

/**
 * This class is responsible for taking input and implementing all logic/actions related to an admin.
 * The following manipulations include:
 * - Admin ability to sign out
 * - Admin ability to delete empty events
 * - Admin ability to report another user
 * @author Khoa Pham, Vladimir Caterov
 * @see UseCases.AttendeeManager
 * @see UseCases.OrganizerManager
 * @see UseCases.SpeakerManager
 * @see UseCases.AdminManager
 * @see UseCases.EventManager
 * @see NewUI.AdminPresenterTextUI
 * @see Controllers.MessengerMenuController
 */

public class AdminMenuController implements CommandHandler{

    private final EventManager eventManager;
    private final MessageManager messageManager;
    private final AdminPresenterTextUI adminPresenterTextUI;
    private final ErrorHandler errorHandler;
    private final MessengerMenuController messengerMenuController;
    private final AccountHandler accountHandler;

    /**
     * A constructor for creating an AdminMenuController.
     */
    public AdminMenuController(EventManager eventManager,
                               MessageManager messageManager, AdminPresenterTextUI adminPresenterTextUI,
                               MessengerMenuController messengerMenuController, AccountHandler accountHandler, ErrorHandler errorHandler){

        this.eventManager = eventManager;
        this.messageManager = messageManager;
        this.adminPresenterTextUI = adminPresenterTextUI;
        this.messengerMenuController = messengerMenuController;
        this.accountHandler = accountHandler;
        this.errorHandler = errorHandler;
    }
    /**
     * This method allows admins to select between options from 0, 1, or 2 where 0 signs out the user,
     * 1 deletes all empty events, and 2 allows admins to report a given user by their username.
     * The following manipulations include:
     * @param username: the username of the admin signed in
     * @return boolean: True if the admin is remaining logged in, false if the admin wants to sign out
     */
    public boolean handleCommand(String username){
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        switch(option) {
            case "0":
                return false;
            case "1":
                deleteEventsWithoutAttendee();
                adminPresenterTextUI.emptyEventsDeleted();
                return true;
            case "2":
                adminPresenterTextUI.promptForUsername();
                String user = scanner.nextLine();
                String userType = accountHandler.getAccountType(user);
                if (messengerMenuController.adminSendMessage(username, user, "You Have Been Reported. Do not respond to this message", userType)){
                    return true;
                }
                else {
                    errorHandler.showError("UDE");
                    return true;
                }
        }
        return true;
    }

//    public List<String> getAllMessages() {
//        List<String> speakers = speakerManager.getAllSpeakerIds();
//        List<String> organizers = organizerManager.getAllOrganizerIds();
//        List<String> attendees = attendeeManager.getAllAttendeeIds();
//
//        List<String> speakerConversationMessages = new ArrayList<>();
//        List<String> organizerConversationMessages = new ArrayList<>();
//
//        for (String speaker : speakers) {
//            List<String> conversationIDS = speakerManager.getConversations(speaker);
//            for (String conversationID : conversationIDS) {
//                List<String> participants = conversationManager.getConvoParticipants(conversationID);
//                for (String member : participants) {
//                    if (!attendeeManager.isAttendee(member)) {
//                        List<String> orderedMessages = conversationMenuController.orderedMessagesInConvo(conversationID);
//                        speakerConversationMessages.addAll(orderedMessages);
//                    }
//                }
//
//            }
//        }
//
//        for (String organizer : organizers) {
//            List<String> conversationIDS = organizerManager.getConversations(organizer);
//            for (String conversationID : conversationIDS) {
//                List<String> participants = conversationManager.getConvoParticipants(conversationID);
//                for (String member : participants) {
//                    if (!attendeeManager.isAttendee(member)) {
//                        List<String> orderedMessages = conversationMenuController.orderedMessagesInConvo(conversationID);
//                        organizerConversationMessages.addAll(orderedMessages);
//                    }
//                }
//
//            }
//        }
//
//        for (String speakerMessage: speakerConversationMessages){
//            for (String organizerMessage: organizerConversationMessages){
//                if (!speakerMessage.equals(organizerMessage) && !allMessages.contains(speakerMessage) &&
//                        !allMessages.contains(organizerMessage)){
//                    allMessages.add(speakerMessage);
//                    allMessages.add(organizerMessage);
//                }
//                else{
//                    allMessages.add(speakerMessage);
//                }
//            }
//        }
//        return allMessages;
//    }
//    private void setAllMessages(List<String> newMessageList){
//        this.allMessages = newMessageList;
//    }

//    public void updateMessages(String message){
//      List<String> newMessages = new ArrayList<>();
//      List<String> speakers = speakerManager.getAllSpeakerIds();
//      List<String> organizers = organizerManager.getAllOrganizerIds();
//
//        for (String speaker : speakers) {
//            List<String> conversationIDS = speakerManager.getConversations(speaker);
//            for (String conversationID : conversationIDS) {
//                List<String> participants = conversationManager.getConvoParticipants(conversationID);
//                for (String member : participants) {
//                    if (!attendeeManager.isAttendee(member)) {
//                        List<String> orderedMessages = conversationMenuController.orderedMessagesInConvo(conversationID);
//                    }
//                }
//
//            }
//        }
//
//
//        for(String message1: allMessages){
//        }
//        setAllMessages(newMessages);
//        adminMenu.loadMessages();
//    }

    /**
     * Allow an Admin to delete events with no Attendees
     * call eventManager to perform!
     * This option should only be accessible from inside the Admin login!
     * Only one Admin can perform this task
     * @author Khoa Pham
     */
    public void deleteEventsWithoutAttendee() {
        List<String> allEmptyEvents = eventManager.getEmptyEvents();
        for (String event : allEmptyEvents) {
            eventManager.removeEvent(event);
        }
    }

    /**
     * Allow an Admin to delete any messages with the given id
     * This option should only be accessible from inside the Admin login!
     * (Only one Admin can perform this task)
     * @param message: the message to be deleted
     */
    public void deleteMessage(String message) {
        if (messageManager.messageExists(message))
            messageManager.deleteMessage(message);
    }

}
