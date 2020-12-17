package Controllers;

import Gateways.ProgramGenerator;
import NewUI.*;
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

    private AttendeeMenuController attendeeMenuController;
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

    private AdminTextUI adui;
    private OrganizerPresenterTextUI oui;
    private AttendeePresenterTextUI aui;
    private SpeakerTextUI sui;
    private TextUI ui;


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
        this.accountHandler = new AccountHandler(attendeeManager, organizerManager, speakerManager, adminManager);
        this.userEventController = new UserEventController(attendeeManager, organizerManager, speakerManager, eventManager, roomManager);
        this.messengerMenuController = new MessengerMenuController(messageManager, attendeeManager, organizerManager, speakerManager, eventManager, accountHandler, conversationManager);
        this.attendeeMessagingDashboardMenuController = new AttendeeMessagingDashboardMenuController(attendeeManager, conversationManager);
        this.speakerMessagingDashboardMenuController = new SpeakerMessagingDashboardMenuController(speakerManager, conversationManager);
        this.conversationMenuController = new ConversationMenuController(attendeeMessagingDashboardMenuController, speakerMessagingDashboardMenuController, conversationManager, messageManager);
        accountHandler.signup("org", "org", "organizer");

        this.adui = new AdminTextUI(attendeeManager, organizerManager, speakerManager, adminManager, messageManager,
                conversationManager, eventManager, roomManager);
        this.aui = new AttendeePresenterTextUI(attendeeManager, organizerManager, speakerManager, adminManager, messageManager,
                conversationManager, eventManager, roomManager);
        this.oui = new OrganizerPresenterTextUI(attendeeManager, organizerManager, speakerManager, adminManager, messageManager,
                conversationManager, eventManager, roomManager);
        this.sui = new SpeakerTextUI(attendeeManager, organizerManager, speakerManager, adminManager, messageManager,
                conversationManager, eventManager, roomManager);
        this.ui = new TextUI(attendeeManager, organizerManager, speakerManager, adminManager, messageManager,
                conversationManager, eventManager, roomManager);

        this.organizerMenuController = new OrganizerMenuController(attendeeManager, organizerManager, speakerManager, adminManager,
                accountHandler, eventManager, userEventController, roomManager, oui, messengerMenuController, conversationManager, conversationMenuController);
        this.attendeeMenuController = new AttendeeMenuController(attendeeManager, organizerManager, speakerManager, adminManager, accountHandler, eventManager, userEventController, roomManager, aui, messengerMenuController, conversationManager, conversationMenuController);
        this.speakerMenuController = new SpeakerMenuController(attendeeManager, organizerManager, speakerManager, adminManager, accountHandler, eventManager, userEventController, roomManager, sui, messengerMenuController, conversationManager, conversationMenuController);
        this.adminMenuController = new AdminMenuController(attendeeManager, speakerManager, organizerManager, conversationManager, conversationMenuController, eventManager, messageManager);

    }


//    /**
//     * A method that is responsible for the flow of the program by taking user input,
//     * using controllers to execute actions and displaying the result using the UI.
//     */
//    public void run(){
//            this.accountHandler = new AccountHandler(attendeeManager, organizerManager, speakerManager, adminManager);
//            this.attendeeEventMenuController = new AttendeeEventMenuController(attendeeManager, eventManager);
//            this.attendeeMessagingDashboardMenuController = new AttendeeMessagingDashboardMenuController(attendeeManager,
//                    conversationManager);
//            this.conversationMenuController = new ConversationMenuController(attendeeMessagingDashboardMenuController,
//                    speakerMessagingDashboardMenuController, conversationManager, messageManager);
//            this.eventMenuController = new EventMenuController();
//            this.eventsSearchEngine = new EventsSearchEngine(eventManager);
//
//
//            this.loginMenuController = new LoginMenuController(accountHandler);
//            this.signUpMenuController = new SignUpMenuController(accountHandler);
//
//            this.messengerMenuController = new MessengerMenuController(messageManager, attendeeManager,
//                    organizerManager, speakerManager, eventManager, accountHandler, conversationManager);
//            this.organizerMenuController = new OrganizerMenuController(attendeeManager, organizerManager,
//                    speakerManager,adminManager, accountHandler, eventManager, userEventController, roomManager);
//            this.speakerMenuController = new SpeakerMenuController(attendeeManager, organizerManager, speakerManager,
//                    eventManager, roomManager, conversationManager, messageManager, "");
//            this.speakerMessagingDashboardMenuController = new SpeakerMessagingDashboardMenuController(speakerManager,
//                    conversationManager);
//            this.organizerMessagingDashboardMenuController = new OrganizerMessagingDashboardMenuController(organizerManager,
//                    conversationManager);
//            this.userEventController = new UserEventController(attendeeManager, organizerManager,
//                    speakerManager, eventManager, roomManager);
//
//            this.adminMenuController = new AdminMenuController(attendeeManager, speakerManager, organizerManager, conversationManager,
//                conversationMenuController, eventManager, messageManager);
//
//            this.sceneHandler = new SceneHandler();
//
//
//            /* Create an organizer account when a new Controllers.MasterSystem object is created
//             * to allow for the conference to have at least one organizer*/
//            accountHandler.signup("organizer1", "organizer1", "organizer");
//
//            /* Create an admin, since there is no way to sign up an admin account*/
//            accountHandler.signup("admin", "admin", "admin");
//        }
//        public LoginMenuController getLoginMenuController(){
//            return this.loginMenuController;
//        }
//        public ProgramGenerator getProgramGenerator(){
//            return this.programGenerator;
//        }
//        public SignUpMenuController getSignUpMenuController(){
//            return this.signUpMenuController;
//        }
//        public AttendeeEventMenuController getAttendeeEventMenuController(){
//            return this.attendeeEventMenuController;
//        }
//        public AttendeeMessagingDashboardMenuController getAttendeeMessagingDashboardMenuController(){
//            return this.attendeeMessagingDashboardMenuController;
//        }
//        public ConversationMenuController getConversationMenuController(){
//            return this.conversationMenuController;
//        }
//        public EventMenuController getEventMenuController(){
//            return this.eventMenuController;
//        }
//        public EventsSearchEngine getEventsSearchEngine(){
//            return this.eventsSearchEngine;
//        }
//        public MessengerMenuController getMessengerMenuController(){
//            return this.messengerMenuController;
//        }
//        public OrganizerMenuController getOrganizerMenuController(){
//            return this.organizerMenuController;
//        }
//        public SpeakerMenuController getSpeakerMenuController(){
//            return this.speakerMenuController;
//        }
//        public SpeakerMessagingDashboardMenuController getSpeakerMessagingDashboardMenuController(){
//            return this.speakerMessagingDashboardMenuController;
//        }
//        public UserEventController getUserEventController(){
//            return this.userEventController;
//        }
//        public SceneHandler getSceneHandler(){
//            return this.sceneHandler;
//        }
//        public AccountHandler getAccountHandler(){
//            return this.accountHandler;
//        }
//        public RoomManager getRoomManager(){
//            return this.roomManager;
//        }
//
//        public OrganizerMessagingDashboardMenuController getOrganizerMessagingDashboardController() {
//            return organizerMessagingDashboardMenuController;
//        }
//
//        public AdminMenuController getAdminMenuController() {
//            return adminMenuController;
//        }

    // Everything below is from Phase 1. We will be scraping the GUI and only implementing the TextUI now.


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
                    programGenerator.readToDatabase();
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
                        aui.attendeemenu(currentUsername);
                        break;
                    case "organizer":
                        oui.organizermenu(currentUsername);
                        break;
                    case "speaker":
                        sui.speakermenu(currentUsername);
                        break;
                }

                String option = scanner.nextLine();
                if (option.equals("0")) {
                    loggedIn = false;
                    currentUsername = null;
                    programGenerator.readToDatabase();
                } else {
                    switch (currentAccountType){
                        case "attendee":{
                            attendeeMenuController.attendeeUserCommandHandler(currentUsername);
                            break;
                        }
                        case "organizer":{
                            oui.organizermenu(currentUsername);
                            String option1 = scanner.nextLine();
                            organizerMenuController.organizerFunctionalities(option1, currentUsername);
                            break;
                        }
                        case "speaker":{
                            speakerMenuController.speakerUserCommandHandler(currentUsername);
                            break;
                        }
                        //case "admin":{
                        //    adminMenuController.
                        //}
                    }
                }
            }
        }

    }

}