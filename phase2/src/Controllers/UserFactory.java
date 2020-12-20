package Controllers;

import Gateways.MessageDatabase;
import NewUI.*;
import UseCases.*;

public class UserFactory {

    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private AdminManager adminManager;
    private AccountHandler accountHandler;
    private EventManager eventManager;
    private MessageManager messageManager;
    private UserEventController userEventController;
    private RoomManager roomManager;
    private AttendeePresenterTextUI attendeePresenterTextUI;
    private OrganizerPresenterTextUI organizerPresenterTextUI;
    private SpeakerPresenterTextUI speakerPresenterTextUI;
    private AdminPresenterTextUI adminPresenterTextUI;
    private MessengerMenuController messengerMenuController;
    private ConversationManager conversationManager;
    private ConversationMenuController conversationMenuController;
    private ErrorHandler errorHandler;
    private PollController pollController;

    public UserFactory(AttendeeManager attendeeManager,
                       OrganizerManager organizerManager, SpeakerManager speakerManager,
                       AdminManager adminManager, AccountHandler accountHandler, EventManager eventManager,
                       MessageManager messageManager, UserEventController userEventController, RoomManager roomManager,
                       AttendeePresenterTextUI attendeePresenterTextUI, OrganizerPresenterTextUI organizerPresenterTextUI,
                       SpeakerPresenterTextUI speakerPresenterTextUI, AdminPresenterTextUI adminPresenterTextUI,
                       MessengerMenuController messengerMenuController,
                       ConversationManager conversationManager,
                       ConversationMenuController conversationMenuController, ErrorHandler errorHandler,
                       PollController pollController) {
        this.attendeeManager = attendeeManager;
        this.organizerManager = organizerManager;
        this.speakerManager = speakerManager;
        this.adminManager = adminManager;
        this.accountHandler = accountHandler;
        this.eventManager = eventManager;
        this.messageManager = messageManager;
        this.userEventController = userEventController;
        this.roomManager = roomManager;
        this.attendeePresenterTextUI = attendeePresenterTextUI;
        this.organizerPresenterTextUI = organizerPresenterTextUI;
        this.speakerPresenterTextUI = speakerPresenterTextUI;
        this.adminPresenterTextUI = adminPresenterTextUI;
        this.messengerMenuController = messengerMenuController;
        this.conversationManager = conversationManager;
        this.conversationMenuController = conversationMenuController;
        this.errorHandler = errorHandler;
        this.pollController = pollController;
    }

    public CommandHandler getUserController(String userType) {
        CommandHandler userController;
        if(userType.equals("attendee"))
            userController = new AttendeeMenuController(attendeeManager, organizerManager, speakerManager, adminManager,
                    accountHandler, eventManager, userEventController, roomManager, attendeePresenterTextUI, messengerMenuController, conversationManager,
                    conversationMenuController, errorHandler, pollController);
        else if(userType.equals("organizer"))
            userController = new OrganizerMenuController(attendeeManager, organizerManager, speakerManager, adminManager,
                    accountHandler, eventManager, userEventController, roomManager, organizerPresenterTextUI, messengerMenuController, conversationManager,
                    conversationMenuController, pollController);
        else if(userType.equals("speaker"))
            userController = new SpeakerMenuController(attendeeManager, organizerManager, speakerManager, adminManager,
                    accountHandler, eventManager, userEventController, roomManager, speakerPresenterTextUI, messengerMenuController, conversationManager,
                    conversationMenuController, pollController);
        else if(userType.equals("admin"))
            userController = new AdminMenuController(eventManager, messageManager, adminPresenterTextUI, messengerMenuController, accountHandler, errorHandler);
        else
            userController = null;
        return userController;
    }

}
