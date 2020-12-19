package Controllers;

import Gateways.ProgramGenerator;
import NewUI.*;
//import Presenters.*;
//import Presenters.Attendee.*;
//import Presenters.Organizer.*;
//import Presenters.Speaker.*;

import UseCases.*;

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



    private ConversationMenuController conversationMenuController;
    private EventsSearchEngine eventsSearchEngine;
    private MessengerMenuController messengerMenuController;

    private AccountHandler accountHandler;
    private AttendeeMenuController attendeeMenuController;
    private OrganizerMenuController organizerMenuController;
    private SpeakerMenuController speakerMenuController;
    private AdminMenuController adminMenuController;

    private UserEventController userEventController;

    private ProgramGenerator programGenerator;

    private AdminPresenterTextUI adui;
    private OrganizerPresenterTextUI oui;
    private AttendeePresenterTextUI aui;
    private SpeakerPresenterTextUI sui;
    private TextUI ui;
    private ErrorHandler errorHandler;
    private LandingMenu landingMenu;


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
        this.messengerMenuController = new MessengerMenuController(messageManager, attendeeManager, organizerManager, speakerManager, eventManager, accountHandler, conversationManager, adminManager);
        this.conversationMenuController = new ConversationMenuController(conversationManager, messageManager);
        accountHandler.signup("org", "org", "organizer");

        this.adui = new AdminPresenterTextUI(attendeeManager, organizerManager, speakerManager, adminManager, messageManager,
                conversationManager, eventManager, roomManager);
        this.aui = new AttendeePresenterTextUI(attendeeManager, organizerManager, speakerManager, adminManager, messageManager,
                conversationManager, eventManager, roomManager);
        this.oui = new OrganizerPresenterTextUI(attendeeManager, organizerManager, speakerManager, adminManager, messageManager,
                conversationManager, eventManager, roomManager);
        this.sui = new SpeakerPresenterTextUI(attendeeManager, organizerManager, speakerManager, adminManager, messageManager,
                conversationManager, eventManager, roomManager);
        this.ui = new TextUI(attendeeManager, organizerManager, speakerManager, adminManager, messageManager,
                conversationManager, eventManager, roomManager);
        this.landingMenu = new LandingMenu();
        this.errorHandler = new ErrorHandler();

        this.organizerMenuController = new OrganizerMenuController(attendeeManager, organizerManager, speakerManager, adminManager,
                accountHandler, eventManager, userEventController, roomManager, oui, messengerMenuController, conversationManager, conversationMenuController);
        this.attendeeMenuController = new AttendeeMenuController(attendeeManager, organizerManager, speakerManager, adminManager, accountHandler, eventManager, userEventController, roomManager, aui, messengerMenuController, conversationManager, conversationMenuController, errorHandler);
        this.speakerMenuController = new SpeakerMenuController(attendeeManager, organizerManager, speakerManager, adminManager, accountHandler, eventManager, userEventController, roomManager, sui, messengerMenuController, conversationManager, conversationMenuController);
        this.adminMenuController = new AdminMenuController(attendeeManager, speakerManager, organizerManager, conversationManager, conversationMenuController, eventManager, messageManager, adui, messengerMenuController, accountHandler);

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

            landingMenu.landingmenu();
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
                    errorHandler.showError("INO");
            }

            while(loggedIn) {
                boolean inMenu = true;
                switch(currentAccountType) {
                    case "attendee":
                        while(inMenu){
                            aui.attendeemenu(currentUsername);
                            inMenu = attendeeMenuController.attendeeUserCommandHandler(currentUsername);
                        }
                        loggedIn = false;
                        currentUsername = null;
                        programGenerator.readToDatabase();
                        break;

                    case "organizer":
                        while(inMenu){
                            oui.organizermenu(currentUsername);
                            inMenu = organizerMenuController.organizerFunctionalities(currentUsername);
                        }
                        loggedIn = false;
                        currentUsername = null;
                        programGenerator.readToDatabase();
                        break;
                    case "speaker":
                        while(inMenu){
                            sui.speakermenu(currentUsername);
                            inMenu = speakerMenuController.speakerUserCommandHandler(currentUsername);
                        }
                        loggedIn = false;
                        currentUsername = null;
                        programGenerator.readToDatabase();
                        break;
                    case "admin":
                        while(inMenu){
                            adui.adminmenu(currentUsername);
                            inMenu = adminMenuController.adminFunctionalities(currentUsername);
                        }
                        loggedIn = false;
                        currentUsername = null;
                        programGenerator.readToDatabase();
                        break;
                }
            }
        }
    }

}
