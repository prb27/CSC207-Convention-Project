package NewUI;
import Controllers.MasterSystem;
import com.sun.org.apache.xpath.internal.operations.String;
import Main.Main;
import java.util.*;

    /**
     * Class that stores methods used by the Controllers.MasterSystem class to send out prompts for users to reply to
     * The Class has the following methods:
     *  - a method to print out what is shown in the landing menu (i.e. what someone sees when first opening the program)
     *  - a method to print out what is shown in the sign up menu
     *  - a method to print out any input string
     *  - a method to print out a prompt for a username
     *  - a method to print out a prompt for a password
     *  - a method to print out a prompt for an event name
     *  - a method to print out a prompt for a message that a user wishes to send
     *  - a method to print out a specific error based on errors captured by the program
     *  - a method to print out a specific prompt based on an input called by the Controllers.MasterSystem
     *  - a method to print out the functions that an organizer is able to do
     *  - a method to print out the functions that a speaker is able to do
     *  - a method to print out the functions that an attendee is able to do
     * @author Juan Yi Loke
     */
    public class TextUI{

        /**
         * print out the landing menu which prompts the user to either log in or sign up
         * @author Juan Yi Loke
         */
        public void landingmenu() {
            System.out.println("Conference System\n");
            System.out.println("1: Log in");
            System.out.println("2: Sign up");

            System.out.println("\n0: Quit");
        }

        /**
         * print out the sign up menu which prompts the user to create a username and password
         * @author Juan Yi Loke
         */
        public void signupmenu() {
            System.out.println("Please create a username and password.");
        }

        /**
         * print out the sign up menu which prompts to create a username and password
         * @author Juan Yi Loke
         * @param input: a string that will be printed out in the command line
         */
        public void present(String input) {
            System.out.println(input);
        }

        /**
         * print out a prompt for a username
         * @author Juan Yi Loke
         */
        public void usernameprompt() {
            System.out.println("Please enter your username:");
        }

        /**
         * print out a prompt for a password
         * @author Juan Yi Loke
         */
        public void passwordprompt() {
            System.out.println("Please enter your password:");
        }

        /**
         * print out a prompt for an event name
         * @author Juan Yi Loke
         */
        public void eventnameprompt() {
            System.out.println("Please enter the event name:");
        }

        /**
         * print out a prompt for a message to be sent
         * @author Juan Yi Loke
         */
        public void messageprompt(){
            System.out.println("Please enter the message you wish to send:");
        }


        /**
         * print out an error message based on errors caught
         * @author Juan Yi Loke
         * @param error: the identifier for the error which is being captured
         */
        public void showError(String error) {
            switch (error) {
                case "TNA":
                    System.out.println("You can not schedule an event at this time. Please choose one of the following times \n");
                    System.out.println("9, 10, 11, 12, 1, 2, 3, 4, 5");
                    break;
                case "ARO":
                    System.out.println("All Rooms Occupied");
                    break;
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

        /**
         * print out a prompt message based on prompts that needs to be prompted
         * @author Juan Yi Loke
         * @param prompt: the identifier for the prompt that needs to be prompted
         */
        public void showPrompt(String prompt) {
            switch (prompt) {
                case "LF":
                    System.out.println("Login failed. Please try again :p");
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
                    System.out.println("Message sent successfully!");
                    break;
                case "AMS":
                    System.out.println("Multiple messages sent successfully!");
            }
        }

        /**
         * print out a set of functions that an attendee is able to do
         * @author Juan Yi Loke
         * @param username: the username of the user that is being prompted
         */
        public void attendeemenu(String username) {
            System.out.println("\n\nHello " + username + "!");
            System.out.println("What would you like to do?");

            System.out.println("\nEVENT FUNCTIONS:");
            System.out.println("1: Available events to sign up for");
            System.out.println("2: Sign up for an event");
            System.out.println("3: Cancel spot in an event");
            System.out.println("4: See schedule of event signed up for");

            System.out.println("\nMESSAGING FUNCTIONS:");
            System.out.println("5: Send message to an attendee");
            System.out.println("6: Send message to a speaker of a talk");
            System.out.println("7: View all conversations");
            System.out.println("8: Add another attendee to friend list");
            System.out.println("\n0: Sign-out");
        }

        /**
         * print out a set of functions that an organizer is able to do
         * @author Juan Yi Loke
         * @param username: the username of the user that is being prompted
         */
        public void organizermenu(String username) {
            System.out.println("\n\nHello " + username + "!");
            System.out.println("What would you like to do?");

            System.out.println("\nCONFERENCE FUNCTIONS:");
            System.out.println("1: List of all attendees in the conference");
            System.out.println("2: List of all organizers in the conference");
            System.out.println("3: List of all speakers in the conference");
            System.out.println("4: Check if a speaker has an event at a certain time");
            System.out.println("5: Create a new organizer account");
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
            System.out.println("19: View Conversations");
            System.out.println("20: Send message to everyone attending an event");
            System.out.println("\n0: Sign-out");

        }

        /**
         * print out a set of functions that a speaker is able to do
         * @author Juan Yi Loke
         * @param username: the username of the user that is being prompted
         */
        public void speakermenu(String username) {
            System.out.println("\n\nHello " + username + "!");
            System.out.println("What would you like to do?");

            System.out.println("\nEVENT FUNCTIONS:");
            System.out.println("1: View list of talks to be given");

            System.out.println("\nMESSAGING FUNCTIONS:");
            System.out.println("2: Message all attendees signed up for a talk");
            System.out.println("3: Message all attendees attending multiple talks");
            System.out.println("4: Message an attendee attending a talk");
            System.out.println("5: View Conversations");
            System.out.println("\n0: Sign-out");
        }

        // Everything below is for attendee case

        public void presentEventsNotSignedUpFor(Map<String, List<String>> eventsNotSignedUpFor){
            for (String event: eventsNotSignedUpFor.keySet()) {
                System.out.println(event);
                for (String eventInfo: eventsNotSignedUpFor.get(event))
                    System.out.println(eventInfo);
            }
        }

        public void promptEventTitleToAdd() {
            System.out.println("Please enter the title of the event you want to attend " +
                    "(exactly as it appears on the list of titles displayed)");
        }

        public void success() {
            System.out.println("Successful!");
        }

        public void promptEventTitle() {
            System.out.println("Please enter the event's name.");
        }

        public void promptNoLongerAttending(String event){
            System.out.println("You are no longer attending " + event);
        }

        public void presentAttendeeEventsAttending(List<String> eventsAttending){
            for (String event: eventsAttending){
                System.out.println("Event Title:" + event + "\nTime:" +
                MasterSystem.getEventManager.getStartTime(event) + "\nRoom: " +
                MasterSystem.getEventManager.getRoomNumber(event) + "\nSpeaker: " +
                MasterSystem.getEventManager.getSpeakerEvent(event) + "\n");
            }
        }

        public void promptAttendeeID(){
            System.out.println("Please enter attendee ID");
        }

        public void promptMessageToSend(){
            System.out.println("Please enter the message that you want to send");
        }

        public void failure(){
            System.out.println("Something went wrong");
        }

        public void convoNumUniqueId(String i, String conversationId){
            System.out.println("Conversation Number " + i + "\n" + "Uniqueness Identifier: " + conversationId);
        }

        public void presentRecipients(StringBuilder recipients){
            System.out.println("Recipients: " + recipients);
        }

        public void noConvo(){
            System.out.println("You have no conversations");
        }

        public void promptConvoNumber(){
            System.out.println("Choose a conversation number");
        }


        public void newLine() {
            System.out.println("\n\n");
        }

        public void success() {
            System.out.println("Successful");
        }

        public void fail() {
            System.out.println("Something went wrong");
        }


}
