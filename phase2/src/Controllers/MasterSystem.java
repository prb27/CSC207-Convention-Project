package Controllers;

import Entities.Speaker;
import Gateways.ProgramGenerator;
import Presenters.*;
import Presenters.Attendee.*;
import Presenters.Organizer.*;
import Presenters.Speaker.*;
import Scrap.CurrUsernameInfoFileHandler;
import UI.TextUserInterface;
import UseCases.*;

import java.io.Serializable;

/**
 * Class that stores the instances of use case and controller classes and controls
 * the flow of the program by getting user input and and using other controllers to
 * execute tasks and display results using the UI.
 * @author Akshat Ayush
 */
public class MasterSystem implements Serializable {



    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private AdminManager adminManager;

    private EventManager eventManager;
    private RoomManager roomManager;

    private ConversationManager conversationManager;
    private MessageManager messageManager;

    private AttendeeConversationMenuPresenter attendeeConversationMenuPresenter;
    private AttendeeEventMenuPresenter attendeeEventMenuPresenter;
    private AttendeeMenuPresenter attendeeMenuPresenter;
    private AttendeeMessagingMenuPresenter attendeeMessagingMenuPresenter;
    private AttendeeMessengerMenuPresenter attendeeMessengerMenuPresenter;

    private OrganizerConferenceMenuPresenter organizerConferenceMenuPresenter;
    private OrganizerEventMenuPresenter organizerEventMenuPresenter;
    private OrganizerMenuPresenter organizerMenuPresenter;
    private OrganizerMessagingMenuPresenter organizerMessagingMenuPresenter;
    private OrganizerMessengerMenuPresenter organizerMessengerMenuPresenter;

    private SpeakerConversationMenuPresenter speakerConversationMenuPresenter;
    private SpeakerEventMenuPresenter speakerEventMenuPresenter;
    private SpeakerMenuPresenter speakerMenuPresenter;
    private SpeakerMessagingMenuPresenter speakerMessagingMenuPresenter;
    private SpeakerMessengerMenuPresenter speakerMessengerMenuPresenter;

    private EventMenuPresenter eventMenuPresenter;
    private LoginMenuPresenter loginMenuPresenter;
    private MessageMenuPresenter messageMenuPresenter;
    private SceneHandler sceneHandler;
    private SignUpMenuPresenter signUpMenuPresenter;

    private AccountHandler accountHandler;

    private AttendeeEventMenuController attendeeEventMenuController;
    private AttendeeMessagingDashboardMenuController attendeeMessagingDashboardMenuController;
    private ConversationMenuController conversationMenuController;
    private EventMenuController eventMenuController;
    private EventsSearchEngine eventsSearchEngine;
    private MessageMenuController messageMenuController;
    private MessengerMenuController messengerMenuController;
    private OrganizerMenuController organizerMenuController;
    private SpeakerMenuController speakerMenuController;
    private SpeakerMessagingDashboardMenuController speakerMessagingDashboardMenuController;
    private LoginMenuController loginMenuController;
    private SignUpMenuController signUpMenuController;

    private MessageController messageController;
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
                        EventManager eventManager, RoomManager roomManager) {
        this.attendeeManager = attendeeManager;
        this.organizerManager = organizerManager;
        this.speakerManager = speakerManager;
        this.adminManager = adminManager;
        this.messageManager = messageManager;
        this.conversationManager = conversationManager;
        this.eventManager = eventManager;
        this.roomManager = roomManager;
        this.programGenerator = new ProgramGenerator();
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

            this.messageController = new MessageController(attendeeManager, organizerManager,
                    speakerManager, eventManager, conversationManager, messageManager);
            this.messageMenuController = new MessageMenuController();
            this.messengerMenuController = new MessengerMenuController(messageManager, attendeeManager,
                    organizerManager, speakerManager, eventManager, accountHandler, conversationManager);
            this.organizerMenuController = new OrganizerMenuController(attendeeManager, organizerManager,
                    speakerManager,adminManager, accountHandler, eventManager, messageController,
                    userEventController, roomManager);
            this.speakerMenuController = new SpeakerMenuController(attendeeManager, organizerManager, speakerManager,
                    eventManager, roomManager, conversationManager, messageManager, "");
            this.speakerMessagingDashboardMenuController = new SpeakerMessagingDashboardMenuController(speakerManager,
                    conversationManager);
            this.userEventController = new UserEventController(attendeeManager, organizerManager,
                    speakerManager, eventManager, roomManager);

            this.attendeeConversationMenuPresenter = new AttendeeConversationMenuPresenter(loginMenuController,
                    conversationMenuController, sceneHandler, programGenerator);
            this.attendeeEventMenuPresenter = new AttendeeEventMenuPresenter(attendeeEventMenuController,
                    loginMenuController, programGenerator);
            this.attendeeMenuPresenter = new AttendeeMenuPresenter(loginMenuController, programGenerator);
            this.attendeeMessagingMenuPresenter = new AttendeeMessagingMenuPresenter(loginMenuController,
                    attendeeMessagingDashboardMenuController, conversationMenuController, programGenerator);
            this.attendeeMessengerMenuPresenter = new AttendeeMessengerMenuPresenter(messengerMenuController,
                    loginMenuController, sceneHandler, conversationMenuController, programGenerator);

            this.organizerConferenceMenuPresenter = new OrganizerConferenceMenuPresenter(organizerMenuController,
                    accountHandler);
            this.organizerEventMenuPresenter = new OrganizerEventMenuPresenter(loginMenuController,
                    organizerMenuController, eventsSearchEngine);
            this.organizerMenuPresenter = new OrganizerMenuPresenter(loginMenuController);
            this.organizerMessagingMenuPresenter = new OrganizerMessagingMenuPresenter(loginMenuController,speakerMessagingDashboardMenuController, conversationMenuController);
            this.organizerMessengerMenuPresenter = new OrganizerMessengerMenuPresenter(messengerMenuController, loginMenuController);

            this.speakerConversationMenuPresenter = new SpeakerConversationMenuPresenter(loginMenuController,
                    conversationMenuController, sceneHandler);
            this.speakerEventMenuPresenter = new SpeakerEventMenuPresenter(userEventController, loginMenuPresenter);
            this.speakerMenuPresenter = new SpeakerMenuPresenter();
            this.speakerMessagingMenuPresenter = new SpeakerMessagingMenuPresenter(loginMenuController,
                    speakerMessagingDashboardMenuController, conversationMenuController);
            this.speakerMessengerMenuPresenter = new SpeakerMessengerMenuPresenter(messengerMenuController,
                    loginMenuController);

            this.eventMenuPresenter = new EventMenuPresenter();
            this.loginMenuPresenter = new LoginMenuPresenter(loginMenuController);
            this.messageMenuController = new MessageMenuController();
            this.sceneHandler = new SceneHandler();
            this.signUpMenuPresenter = new SignUpMenuPresenter(signUpMenuController);





            /* Create an organizer account when a new Controllers.MasterSystem object is created
             * to allow for the conference to have at least one organizer*/
            accountHandler.signup("organizer1", "organizer1", "organizer");

            /* Create an organizer account when a new Controllers.MasterSystem object is created
             * to allow for the conference to have at least one organizer*/
            accountHandler.signup("admin", "admin", "admin");
        }

}