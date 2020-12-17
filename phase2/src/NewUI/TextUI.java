package NewUI;
import Controllers.*;
import Gateways.ProgramGenerator;
import Presenters.SceneHandler;
import UseCases.*;
import Main.Main;
import java.util.*;
import UseCases.*;

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

    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private AdminManager adminManager;
    private EventManager eventManager;
    private RoomManager roomManager;
    private ConversationManager conversationManager;
    private MessageManager messageManager;


    public TextUI(AttendeeManager attendeeManager, OrganizerManager organizerManager, SpeakerManager speakerManager,
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
    }



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

    public void failure(){
        System.out.println("Something went wrong");
    }

    public void success() {
        System.out.println("Successful!");
    }

    public void promptEventName() {
        System.out.println("Please enter the event's name.");
    }

    public void promptNoLongerAttending(String event){
        System.out.println("You are no longer attending " + event);
    }

    public void presentEventsAttending(List<String> eventsAttending){
        for (String event: eventsAttending){
            System.out.println("Event Title:" + event + "\nTime:" +
                    eventManager.getStartTime(event) + "\nRoom: " +
                    eventManager.getRoomNumber(event) + "\nSpeaker: " +
                    eventManager.getSpeakerEvent(event) + "\n");
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

    public void presentMessageInConvo(List<String> messagesInConvo) {
        for (String s : messagesInConvo) {
            System.out.println(s);
        }
    }

    public void promptToReply(){
        System.out.println("Enter \"r\" to reply in this conversation." +
                " [Any other input will exit this menu]");
    }

    public void promptMessageToSent(){
        System.out.println("Please enter the name of the attendee to be added");
    }

    public void friendContactAlreadyExist(String attendee, String friendName){
        System.out.println("Attendee " + friendName +" already exist in the contact list");
    }



    // Conversion of .string methods used in oUCH1
    public void presentAllUserIds(List<String> allUserIds) {
        for (String user : allUserIds) {
            System.out.println(user);
        }
    }

    public void promptForSpeakerUsername(){
        System.out.println("Please enter the speaker's username");
    }

    public void promptForTime(){
        System.out.println("Please enter the time");
    }

    public void notASpeaker(){
        System.out.println("Note a speaker");
    }

    public void onlyAllowedTime(){
        System.out.println("Please enter an allowed time");
    }

    public void messageForSpeakerFreeIfFree(String time){
        System.out.println("No, the speaker doesn't have an event at " + time);
    }

    public void messageForSpeakerFreeIfNotFree(String time){
        System.out.println("Yes, the speaker is talking at an event at " + time);
    }

    public void promptForOrganizerUsername(){
        System.out.println("Please enter the organizer's username");
    }

    public void promptForNewOrganizerPw(){
        System.out.println("Please enter this new organizer's password");
    }

    public void usernameAlreadyExists(){
        System.out.println("The username already exists");
    }

    public void promptForSpeakerPw(){
        System.out.println("Please enter password for this speaker");
    }

    public void promptForRoomID(){
        System.out.println("Please enter roomID:");
    }

    public void promptForRoomCapacity(){
        System.out.println("Please enter room capacity");
    }

    public void promptForProjectorExist(){
        System.out.println("Please enter whether the room has a projector (Y/N)");
    }

    public void promptForEventTime(){
        System.out.println("Please enter event time");
    }

    public void promptForEventDuration(){
        System.out.println("Please enter event duration");
    }

    public void promptForEventCapacity(){
        System.out.println("Please enter event capacity");
    }

    public void promptForEventRoom(){
        System.out.println("Please enter room number");
    }

    public void promptForSubjectLine(){
        System.out.println("Please enter subject line");
    }

    public void promptForEventSpeakers(){
        System.out.println("Please enter the speakers' username (enter after each name and type Over when done)");
    }

    public void promptForNewEventTime(){
        System.out.println("Please enter the new event time");
    }


    // Conversion of .string methods used in oUCH2

    public void presentEventsNotSignedUpFor(List<String> eventsNotSignedUpFor{
        for (String event: eventsNotSignedUpFor){
            System.out.println("Event Title:" + event + "\nTime:" +
                    eventManager.getStartTime(event) + "\nRoom: " +
                    eventManager.getRoomNumber(event) + "\nSpeaker: " +
                    eventManager.getSpeakerEvent(event) + "\n");
        }
    }

    public void promptEventNameAdd(){
        System.out.println("Please enter the title of the event you want to attend (exactly as it appears on the list of titles displayed)");
    }

    public void promptForEventName(){
        System.out.println("Please enter the event's name");
    }

    public void presentNoLongerAttendingEvent(String event){
        System.out.println("You are no longer attending " + event);
    }

    public void newLine() {
        System.out.println("\n\n");
    }

    
}
