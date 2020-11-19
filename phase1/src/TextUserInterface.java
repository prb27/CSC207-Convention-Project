import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class TextUserInterface {

    public static void main(String[] args) {
    }

    public static void landingmenu() {
        System.out.println("Welcome! What would you like to do?\n");
        System.out.println("Please type in only the integer for your choice!");
        System.out.println("1: log in");
        System.out.println("2: sign up");
        System.out.println("\n\n0: quit");
    }

    // not used anymore.
    public static void signupmenu() {
        System.out.println("What type of user would you like to sign up as?");
        System.out.println("1: Organizer");
        System.out.println("2: Attendee");
        System.out.println("3: Speaker");
        System.out.println("\n\n0: quit");
    }

    public static List<String> loginmenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your username:");
        String username = input.nextLine();
        System.out.println("Please enter your password:");
        String password = input.nextLine();
        List<String> details = new ArrayList<>();
        details.add(username);
        details.add(password);
        return details;
    }

    public void usernameprompt(){
        System.out.println("Please enter your username:");
    }

    public void passwordprompt(){
        System.out.println("Please enter your password:");
    }

    //    this is specifically for Ashwin's work.
    //    Need to get everyone to use similar way of dealing with this bullshit.
    public static void showError(String error){
        switch(error){
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

        }
    }

    public void attendeemenu(String username) {
        System.out.println("Hello " + username + "!");
        System.out.println("What would you like to do?\n");
        System.out.println("1: Available events to sign up for");
        System.out.println("2: Cancel spot in an event");
        System.out.println("3: See schedule of event signed up for");
        System.out.println("4: Send messages to another attendee");
        System.out.println("5: Send message to a speaker");
        System.out.println("\n\n0: quit");
    }

    public void AttendeeEventSignup() {
    // Where is the get method for available events to sign up for? Can't find it.
    }

    public void AttendeeCancelSpot() {
    // Where is the get method for list of events an attendee is attending?
        System.out.println("Which of the events would you like to cancel your spot at?");
        System.out.println("Please key in the exact string for the ");
    }

//    public void SpeakerListofEventsSpeaking() {
//        HashMap<String, String> ListofEvents = SpeakerManager.getListofTalks;
//    }

    public void organizermenu(String username){
        System.out.println("Hello " + username + "!");
        System.out.println("What would you like to do?\n");
        System.out.println("1: Create speaker account");
        System.out.println("2: Add a room into the system");
        System.out.println("3: Schedule speakers to speak in a room");
        System.out.println("4: Available events to sign up for");
        System.out.println("5: Cancel spot in an event");
        System.out.println("6: See schedule of event signed up for");
        System.out.println("7: Send messages to another attendee");
        System.out.println("8: Send message to a speaker");
        System.out.println("\n\n0: quit");

    }

    public void speakermenu(String username) {
        System.out.println("Hello " + username + "!");
        System.out.println("What would you like to do?\n");
        System.out.println("1: View list of talks to give");
        System.out.println("2: Message all attendees signed up for a talk");
        System.out.println("3: Message an attendee attending a talk");
        System.out.println("\n\n0: quit");
    }
}


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
