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



    /**
     * Constructor method to initialize a new Controllers.MasterSystem instance in case
     * deserializing from the file fails. A newly created Controllers.MasterSystem adds an
     * organizer with username: admin and password: admin to the conference as
     * users can't create organizer accounts themselves.
     */
    public MasterSystem() {

    }
    /**
     * A method that is responsible for the flow of the program by taking user input,
     * using controllers to execute actions and displaying the result using the UI.
     */
    public void run(){
            this.attendeeManager = new AttendeeManager();
            this.organizerManager = new OrganizerManager();
            this.speakerManager = new SpeakerManager();
            this.adminManager = new AdminManager();

            this.eventManager = new EventManager();
            this.roomManager = new RoomManager();
            this.conversationManager = new ConversationManager();
            this.messageManager = new MessageManager();


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
                    organizerManager, speakerManager, adminManager, eventManager, accountHandler, conversationManager);
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
                    conversationMenuController, sceneHandler);
            this.attendeeEventMenuPresenter = new AttendeeEventMenuPresenter(attendeeEventMenuController,
                    loginMenuController);
            this.attendeeMenuPresenter = new AttendeeMenuPresenter(loginMenuController);
            this.attendeeMessagingMenuPresenter = new AttendeeMessagingMenuPresenter(loginMenuController,
                    attendeeMessagingDashboardMenuController, conversationMenuController);
            this.attendeeMessengerMenuPresenter = new AttendeeMessengerMenuPresenter(messengerMenuController,
                    loginMenuController, sceneHandler, conversationMenuController);

            this.organizerConferenceMenuPresenter = new OrganizerConferenceMenuPresenter();
            this.organizerEventMenuPresenter = new OrganizerEventMenuPresenter();
            this.organizerMenuPresenter = new OrganizerMenuPresenter();
            this.organizerMessagingMenuPresenter = new OrganizerMessagingMenuPresenter();
            this.organizerMessengerMenuPresenter = new OrganizerMessengerMenuPresenter();

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
            accountHandler.signup("admin", "admin", "organizer");
        }

}