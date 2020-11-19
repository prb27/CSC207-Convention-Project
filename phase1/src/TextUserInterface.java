import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextUserInterface {

    public static void main(String[] args) {
        String x = landingmenu();
        System.out.print(x + '\n');
        typemenu();
        loginmenu();
        attendeemenu("big mama");
        x = organizermenu("big mama");
        switch(x){
            case "1":
                System.out.println("1");
                break;
            case "2":
                System.out.println("1");
                break;
            case "3":
                System.out.println("1");
                break;
            case "4":
                String y = attendeemenu("big mama");
                System.out.println(y);
                break;
        }
        speakermenu("hi");
    }

    public static String landingmenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome! What would you like to do?");
        System.out.println("1: log in");
        System.out.println("2: sign up");
        String choice = input.nextLine();
        while (!("1".equals(choice) || "2".equals(choice))) {
            System.out.println("Please choose either 1 or 2.");
            choice = input.nextLine();
        }
        return choice;
    }

    // not used anymore.
    public static String typemenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("What type of user are you?");
        System.out.println("1: Organizer");
        System.out.println("2: Attendee");
        System.out.println("3: Speaker");
        String choice = input.nextLine();
        while (!("1".equals(choice) || "2".equals(choice) || "3".equals(choice))) {
            System.out.println("Please choose either 1 or 2 or 3.");
            choice = input.nextLine();
        }
        System.out.println(choice);
        return choice;
    }

    public static List<String> loginmenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your username:");
        String username = input.nextLine();
        System.out.println("Please enter your password:");
        String password = input.nextLine();

        // Should I insert a line here to check the size of the input? Should checking an input as acceptable
        // be handled in the master system or the presenter?
        // The presenter has to format the data into an acceptable input.

        List<String> details = new ArrayList<>();
        details.add(username);
        details.add(password);
        return details;
    }

    // create a method that takes in a string and prints out an error


    public static String attendeemenu (String username) {
        Scanner input = new Scanner(System.in);
        System.out.println("Hello " + username + "!");
        System.out.println("What would you like to do?\n");
        System.out.println("1: Available events to sign up for");
        System.out.println("2: Cancel spot in an event");
        System.out.println("3: See schedule of event signed up for");
        System.out.println("4: Send messages to another attendee");
        System.out.println("5: Send message to a speaker");                 // Might not be ready. Check with Khoa.
        String choice = input.nextLine();
        while (!("1".equals(choice) || "2".equals(choice) || "3".equals(choice) || "4".equals(choice) ||
                "5".equals(choice))){
            System.out.println("Please choose either 1 or 2 or 3 or 4 or 5.");
            choice = input.nextLine();
        }
        return choice;
    }


    public static String organizermenu (String username) {
        Scanner input = new Scanner(System.in);
        System.out.println("Hello " + username + "!");
        System.out.println("What would you like to do?\n");
        System.out.println("1: Create speaker account");
        System.out.println("2: Add a room into the system");
        System.out.println("3: Schedule speakers to speak in a room");
        System.out.println("4: Attendee options");
        String choice = input.nextLine();
        while (!("1".equals(choice) || "2".equals(choice) || "3".equals(choice) || "4".equals(choice))){
            System.out.println("Please choose either 1 or 2 or 3 or 4.");
            choice = input.nextLine();
        }
        return choice;
    }

    public static String speakermenu (String username) {
        Scanner input = new Scanner(System.in);
        System.out.println("Hello " + username + "!");
        System.out.println("What would you like to do?\n");
        System.out.println("1: View list of talks to give");
        System.out.println("2: Message all attendees signed up for a talk");
        System.out.println("3: Message an attendee attending a talk");
        String choice = input.nextLine();
        while (!("1".equals(choice) || "2".equals(choice) || "3".equals(choice))){
            System.out.println("Please choose either 1 or 2 or 3.");
            choice = input.nextLine();
        }
        return choice;




    }


}


////
////    public String loginmenu(){
////        // collects the username and password from the user and returns it
////        Scanner input = new Scanner(System.in);
////        System.out.println("Please enter your username:");
////        String username = input.nextLine();
////        System.out.println("Please enter your password:");
////        String password = input.nextLine();
////        // return something
////    }
//
//    public String signupmenu(){
//        Scanner input = new Scanner(System.in);
//        System.out.println("What type of user are you?");
//        System.out.println("1: Organizer");
//        System.out.println("2: Speaker");
//        int choice = input.nextInt();
//        while (choice > 2 || choice < 1) {
//            System.out.println("Please choose either 1 or 2.");
//            choice = input.nextInt();
//        }
//    }
//    public String UserUI () {
//        Scanner input = new Scanner(System.in);
//        System.out.println("1: Available events to sign up for");
//        System.out.println("2: Cancel spot in an event");
//        System.out.println("3: See schedule of event signed up for");
//        System.out.println("4: Send messages to another attendee");
//        System.out.println("5: Send message to a speaker");
//        int choice = input.nextInt();
//        while (choice > 5 || choice < 1) {
//            System.out.println("Please only choose either one of the available options.");
//            choice = input.nextInt();
//        }
//
//    }
//
//    public void OrganizerUI () {
//        Scanner input = new Scanner(System.in);
//        System.out.println("What would you like to do?");
//        System.out.println("1: Create speaker account");
//        System.out.println("2: Add a room into the system");
//        System.out.println("3: Schedule speakers to speak in a room");
//        System.out.println("4: Attendee functions");
//        int choice = input.nextInt();
//    }
//    }
//}


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
