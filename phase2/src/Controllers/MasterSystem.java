package Controllers;

import Gateways.ProgramGenerator;
import Presenters.*;
import Presenters.Attendee.*;
import Presenters.Organizer.*;
import Presenters.Speaker.*;

import UseCases.*;


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

            this.sceneHandler = new SceneHandler();






            /* Create an organizer account when a new Controllers.MasterSystem object is created
             * to allow for the conference to have at least one organizer*/
            accountHandler.signup("organizer1", "organizer1", "organizer");

            /* Create an organizer account when a new Controllers.MasterSystem object is created
             * to allow for the conference to have at least one organizer*/
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
}