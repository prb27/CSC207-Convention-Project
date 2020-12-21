package Controllers;


import NewUI.*;
import UseCases.*;
/**
 * This class is responsible for creating CommandHandlers (controllers) for each respective menu.
 * Each Menu has a list of options to choose from and the CommandHandler is a controller that handles all
 * option selection
 * @author Akshat Ayush
 * @see UseCases.AttendeeManager
 * @see UseCases.OrganizerManager
 * @see UseCases.SpeakerManager
 * @see UseCases.AdminManager
 * @see UseCases.EventManager
 * @see UseCases.MessageManager
 * @see UseCases.ConversationManager
 * @see UseCases.RoomManager
 * @see Controllers.AccountHandler
 * @see Controllers.ConversationMenuController
 * @see Controllers.UserEventController
 * @see Controllers.MessengerMenuController
 * @see Controllers.PollController
 * @see NewUI.AttendeePresenterTextUI
 * @see NewUI.AdminPresenterTextUI
 * @see NewUI.OrganizerPresenterTextUI
 * @see NewUI.SpeakerPresenterTextUI
 * @see NewUI.ErrorHandler
 */
public class UserFactory {

    private final AttendeeManager attendeeManager;
    private final OrganizerManager organizerManager;
    private final SpeakerManager speakerManager;
    private final AccountHandler accountHandler;
    private final EventManager eventManager;
    private final UserEventController userEventController;
    private final RoomManager roomManager;
    private final AttendeePresenterTextUI attendeePresenterTextUI;
    private final OrganizerPresenterTextUI organizerPresenterTextUI;
    private final SpeakerPresenterTextUI speakerPresenterTextUI;
    private final AdminPresenterTextUI adminPresenterTextUI;
    private final MessengerMenuController messengerMenuController;
    private final ConversationManager conversationManager;
    private final ConversationMenuController conversationMenuController;
    private final ErrorHandler errorHandler;
    private final PollController pollController;

    public UserFactory(AttendeeManager attendeeManager,
                       OrganizerManager organizerManager, SpeakerManager speakerManager,
                       AccountHandler accountHandler, EventManager eventManager,
                       UserEventController userEventController, RoomManager roomManager,
                       AttendeePresenterTextUI attendeePresenterTextUI, OrganizerPresenterTextUI organizerPresenterTextUI,
                       SpeakerPresenterTextUI speakerPresenterTextUI, AdminPresenterTextUI adminPresenterTextUI,
                       MessengerMenuController messengerMenuController,
                       ConversationManager conversationManager,
                       ConversationMenuController conversationMenuController, ErrorHandler errorHandler,
                       PollController pollController) {
        this.attendeeManager = attendeeManager;
        this.organizerManager = organizerManager;
        this.speakerManager = speakerManager;
        this.accountHandler = accountHandler;
        this.eventManager = eventManager;
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
    /**
     *Create a Command handler object by implementing the Factory Design Pattern where provided
     *with a String object userType, the method checks what user type it is and creates a new
     * CommandHandler (userController) for the respective Menus
     *Implemented Factory Design Pattern
     *
     * @param userType : the type of user that is logging in
     * @return CommandHandler: returns the appropriate command handler associated with the userType
     */
    public CommandHandler getUserController(String userType) {
        CommandHandler userController;
        if(userType.equals("attendee"))
            userController = new AttendeeMenuController(attendeeManager, userEventController, attendeePresenterTextUI, messengerMenuController, conversationManager, conversationMenuController, errorHandler, pollController);
        else if(userType.equals("organizer"))
            userController = new OrganizerMenuController(organizerManager, speakerManager, accountHandler, eventManager, userEventController, roomManager, organizerPresenterTextUI, messengerMenuController, conversationManager,conversationMenuController, pollController);
        else if(userType.equals("speaker"))
            userController = new SpeakerMenuController(speakerManager,
                    userEventController, speakerPresenterTextUI, messengerMenuController, conversationManager,
                    conversationMenuController, pollController);
        else if(userType.equals("admin"))
            userController = new AdminMenuController(eventManager, adminPresenterTextUI, messengerMenuController, accountHandler, errorHandler, speakerManager);
        else
            userController = null;
        return userController;
    }

}
