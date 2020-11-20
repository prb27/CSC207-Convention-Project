import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MasterSystem {

    private final TextUserInterface ui;
    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private final AccountHandler accountHandler;

    private final Scanner scanner = new Scanner(System.in);

    public MasterSystem() {
        this.ui = new TextUserInterface();
        this.attendeeManager = new AttendeeManager();
        this.organizerManager = new OrganizerManager();
        this.speakerManager = new SpeakerManager();
        this.accountHandler = new AccountHandler(attendeeManager, organizerManager, speakerManager);
    }

//    private String getInput(List<String> validOptions) {
//        String inputString = scanner.nextLine();
//        if(validOptions == null)
//            return inputString;
//        if(!validOptions.contains(inputString))
//            return null;
//        return inputString;
//    }

    public void run() {
        String currentUsername = null;
        String currentPassword = null;
        String currentAccountType = null;
        boolean loggedIn = false;

        while(!loggedIn) {

            String tempUsername;
            String tempPassword;
            String tempAccountType;

            ui.landingmenu();
            String landingOption = scanner.nextLine();

            switch(landingOption) {
                case "1":
                    ui.usernameprompt();
                    tempUsername = scanner.nextLine();
                    ui.passwordprompt();
                    tempPassword = scanner.nextLine();
                    tempAccountType = accountHandler.login(tempUsername, tempPassword);
                    while(tempAccountType == null){
                        ui.usernameprompt();
                        tempUsername = scanner.nextLine();
                        ui.passwordprompt();
                        tempPassword = scanner.nextLine();
                        tempAccountType = accountHandler.login(tempUsername, tempPassword);
                    }
                    currentUsername = tempUsername;
                    currentPassword = tempPassword;
                    currentAccountType = tempAccountType;
                    loggedIn = true;
                    break;

                case "2":
                    ui.signupmenu();
                    tempAccountType = scanner.nextLine();
                    ui.usernameprompt();
                    tempUsername = scanner.nextLine();
                    ui.passwordprompt();
                    tempPassword = scanner.nextLine();
                    if(accountHandler.signup(tempUsername, tempPassword, tempAccountType))
                        ui.showPrompt("UC");
                    else
                        ui.showPrompt("SF");
                    break;
            }

        while(loggedIn) {

            switch(currentAccountType) {
                case "attendee":
                    ui.attendeemenu(currentUsername);
                    break;
                case "organizer":
                    ui.organizermenu(currentUsername);
                    break;
                case "speaker":
                    ui.speakermenu(currentUsername);
                    break;
            }

            String option = scanner.nextLine();

            if(option.equals("0")) {
                loggedIn = false;
                currentUsername = null;
                currentPassword = null;
            }

            userCommandHandler(option, currentUsername, currentPassword, currentAccountType);
        }
    }
}

    private void userCommandHandler(String option, String username, String password, String userType) {
        switch(userType) {
            case "attendee":
                switch(option) {
                    case "1":


                        break;
                }
                break;
            case "organizer":
                break;
            case "speaker":
                break;
        }
    }
}


// This is the flow of the program. Broadly we will separate the flow into the following.

// Login/Signup Process:

// Step 1) Prompt if the user wants to login or signup.
// Step 2) Show the right menu depending on if the user wants to login or signup.
// Step 3.1) Show the login menu if the user wants to login. The login menu will prompt for username and password.
//             The mastercontroller will read the username and password input and return whether it was successful in
//              logging in. If it was successful in logging in, proceed. Else, go back to the landing menu.
// Step 3.2) Show the signup menu if the user wants to sign up. The signup menu should prompt what type of account
//            the user would like to create (Organizer, Speaker, Attendee). The mastercontroller will store that
//             selection. Then it will prompt for the username and password and check if the username is already
//              inside the list of usernames available. If we fail in signing up, the MasterController should then prompt
//              the user with an error that the username already exists, then it should prompt for a new username and password.
//               If the signup is successful. Go back to the landing menu.
// Step 4) At this point, the user should be able to log in and the MasterController should provide the right menu
//          for the user that is viewing. There will be 3 types of menus that can be displayed based on the type of user.

// Menu Selection Process:
// Basically one huge fucking switch statement with nested switch statements.
//
// I will lay out how the menu works for each type of user.

// OrganizerMenu Process:
// The organizer menu will have 8 options. We see the options listed below:
//public void organizermenu(String username) {
//    System.out.println("Hello " + username + "!");
//    System.out.println("What would you like to do?\n\n");
//
//    System.out.println("EVENT FUNCTIONS:");
//    System.out.println("1: Create speaker account");
//    System.out.println("2: Add a room into the system");
//    System.out.println("3: Schedule speakers to speak in a room");
//    System.out.println("4: Available events to sign up for");
//    System.out.println("5: Cancel spot in an event");
//    System.out.println("6: See schedule of event signed up for\n\n");
//
//    System.out.println("MESSAGING FUNCTIONS:");
//    System.out.println("7: Send message to an attendee");
//    System.out.println("8: Send message to all attendees");
//    System.out.println("9: Send message to a speaker");
//    System.out.println("10: Send message to all speakers");
//    System.out.println("\n\n0: quit");
//
//}

// If the organizer chooses 1. We will have to prompt for the username and password. Then we will need to signup for a
//
//
//

// AttendeeMenu Process:
// The attendee menu will have 5 options. We see the options listed below:
//public void attendeemenu(String username) {
//    System.out.println("Hello " + username + "!");
//    System.out.println("What would you like to do?\n\n");
//
//    System.out.println("EVENT FUNCTIONS:");
//    System.out.println("1: Available events to sign up for"); //Basic for loop iterates over list that prints out line by line.
//    System.out.println("2: Cancel spot in an event"); // Returns an error if spot doesn't exist otherwise, prints success.
//    System.out.println("3: See schedule of event signed up for\n\n"); //Basic for loop iterates over list that prints out line by line.
//
//    System.out.println("MESSAGING FUNCTIONS:");
//    System.out.println("4: Send message to an attendee");
//    System.out.println("5: Send message to a speaker of a talk");
//    System.out.println("\n\n0: quit");
//}
//
// If the user chooses 4, first we show a list of contacts. We retrieve the list of contatcs from the attendeemanager.
// Print a line that says who do you wanna send a message to?.
// Use UserMessageController.AttendeetoSingle to send a message to one of the people that the user has inputted.

//SpeakerMenu Process:
// The organizer menu will have 3 options. We see the options listed below:
//public void speakermenu(String username) {
//    System.out.println("Hello " + username + "!");
//    System.out.println("What would you like to do?\n\n");
//
//    System.out.println("EVENT FUNCTIONS:");
//    System.out.println("1: View list of talks to be given\n\n");
//
//    System.out.println("MESSAGING FUNCTIONS:");
//    System.out.println("2: Message all attendees signed up for a talk or multiple talks");
//    System.out.println("3: Message an attendee attending a talk");
//    System.out.println("\n\n0: quit");
//}


// If the user picks 1. Then the MasterController will have to reach into the UserEventController for it to call
//  the getter method in the Use Case