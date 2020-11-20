import javafx.util.Pair;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class TextUserInterface implements Serializable {

    public void landingmenu() {
        System.out.println("Please type in only the integer for your choice.");

        System.out.println("\n1: log in");
        System.out.println("2: sign up");

        System.out.println("\n0: quit");
    }

    // not used anymore.
    public void signupmenu() {
        System.out.println("Please create a username and password.");
    }

    public void present(String input) {
        System.out.println(input);
    }

    public void usernameprompt() {
        System.out.println("Please enter your username:");
    }

    public void passwordprompt() {
        System.out.println("Please enter your password:");
    }

    public void eventnameprompt() {
        System.out.println("Please enter the event name:");
    }

    public void attendeenameprompt(){
        System.out.println("Please enter the username of the attendee:");
    }
    public void organizernameprompt(){
        System.out.println("Please enter the username of the organizer:");
    }
    public void speakernameprompt(){
        System.out.println("Please enter the username of the speaker:");
    }
    public void messageprompt(){
        System.out.println("Please enter the message you wish to send:");
    }


    //    this is specifically for Ashwin's work.
    //    Need to get everyone to use similar way of dealing with this bullshit.

    public void showError(String error) {
        switch (error) {
            case "INO":
                System.out.println("Invalid Input: please choose from one of the available integer options");
                break;
            case "ODE":
                System.out.println("Organizer doesn't exist");
                break;
            case "EDE":
                System.out.println("Event doesn't exist");
                break;
            case "SDE":
                System.out.println("Speaker doesn't exist");
                break;
            case "EFC":
                System.out.println("Event at full capacity");
                break;
            case "RAE":
                System.out.println("Room already exists");
                break;
            case "UDE":
                System.out.println("The user doesn't exist!");
                break;
            case "AE":
                System.out.println("Already attending the event.");
                break;
            case "ETC":
                System.out.println("Event time conflict");
                break;
            case "STC":
                System.out.println("Speaker time conflict");
                break;
            case "RO":
                System.out.println("Room occupied");
                break;
        }
    }

    public void showPrompt(String prompt) {
        switch (prompt) {
            case "TNA":
                System.out.println("You can not schedule an event at this time. Please choose one of the following times \n");
                System.out.println("9, 10, 11, 12, 1, 2, 3, 4, 5");
            case "LF":
                System.out.println("Signup failed. Please try again :p");
                break;
            case "UC":
                System.out.println("Account successfully created!");
                System.out.println("Please log in to the account.");
                break;
            case "SF":
                System.out.println("Signup failed. Please try again :p");
                break;
            case "EDE":
                System.out.println("Event doesn't exist");
                break;
            case "SDE":
                System.out.println("Speaker doesn't exist");
                break;
            case "EFC":
                System.out.println("Event at full capacity");
                break;
            case "RAE":
                System.out.println("Room already exists");
                break;
            case "MS":
                System.out.println("Message Sent Successfully!");
                break;
            case "AMS":
                System.out.println("Multiple Messages Sent Successfully!");
        }
    }

    public void attendeemenu(String username) {
        System.out.println("\n\nHello " + username + "!");
        System.out.println("What would you like to do?");

        System.out.println("\nEVENT FUNCTIONS:");
        System.out.println("1: Available events to sign up for"); //Basic for loop iterates over list that prints out line by line.
        System.out.println("2: Cancel spot in an event"); // Returns an error if spot doesn't exist otherwise, prints success.
        System.out.println("3: See schedule of event signed up for"); //Basic for loop iterates over list that prints out line by line.

        System.out.println("\nMESSAGING FUNCTIONS:");
        System.out.println("4: Send message to an attendee");
        System.out.println("5: Send message to a speaker of a talk");
        System.out.println("\n0: Sign out");
    }


    public void AttendeeEventSignup() {
        // Where is the get method for available events to sign up for? Can't find it.
    }

    public void AttendeeCancelSpot() {
        // Where is the get method for list of events an attendee is attending?
        System.out.println("Which of the events would you like to cancel your spot at?");
        System.out.println("Please key in the exact string for the ");
    }

    public void organizermenu(String username) {
        System.out.println("\n\nHello " + username + "!");
        System.out.println("What would you like to do?");

        System.out.println("\nCONFERENCE FUNCTIONS:");
        System.out.println("1. List of all attendees in the conference");
        System.out.println("2. List of all organizers in the conference");
        System.out.println("3. List of all speakers in the conference");
        System.out.println("4. Check if a speaker has an event at a certain time");
        System.out.println("5. Create a new organizer account");
        System.out.println("6: Create speaker account");

        System.out.println("\nEVENT FUNCTIONS:");
        System.out.println("7: Add a room into the system");
        System.out.println("8: Create new event or Schedule speaker for new event");
        System.out.println("9: Change speaker for an event (Warning: once this option is chosen, the given event name will be removed. \n All attendees of the event should" +
                " register again for this event.)");
        System.out.println("10: Change time of an event (Warning: once this option is chosen, the given event name will be removed, \n and a new event will be created at your chosen time. All attendees of the event should" +
                " register again for this event.)");
        System.out.println("11: Show events that I haven't signed up for");
        System.out.println("12: Sign up for an event");
        System.out.println("13: Cancel spot in an event");
        System.out.println("14: See schedule of events signed up for");

        System.out.println("\nMESSAGING FUNCTIONS: [Note: Since you are an organizer, you can send a message to any attendee/speaker/organizer]");
        System.out.println("15: Send message to an attendee");
        System.out.println("16: Send message to all attendees");
        System.out.println("17: Send message to a speaker");
        System.out.println("18: Send message to all speakers");
        System.out.println("\n0: Sign out");

    }

    public void speakermenu(String username) {
        System.out.println("\n\nHello " + username + "!");
        System.out.println("What would you like to do?");

        System.out.println("\nEVENT FUNCTIONS:");
        System.out.println("1: View list of talks to be given");

        System.out.println("\nMESSAGING FUNCTIONS:");
        System.out.println("2: Message all attendees signed up for a talk");
        System.out.println("3: Message all attendees attending multiple talks");
        System.out.println("4: Message an attendee attending a talk");
        System.out.println("\n0: Sign out");
    }
    /*
    public void speakerMessagingMenu(String username){
        System.out.println("MESSAGING:");
        System.out.println("What is the message?");
    }
    */

}

//    public void SpeakerListofEventsSpeaking() {
//        ArrayList<ArrayList<String>> ListofEvents = SpeakerManager;
//    }



// For Phase 1 To-Do:
// Hard code every menu for the UI and work with Akshat on hardcoding logic for MasterSystem.
// Gateway class that has a method to save the state of the class.
// The gateway class will have 2 methods read/write.
// The method will likely be called in the MasterSystem
// We only save upon logging out. And read upon logging in.
// Take into account errors in scheduling.
// Errors in login/signup.
// Create an error called error prompt that takes in a string and prints the string.
// Every method should have a clear function
