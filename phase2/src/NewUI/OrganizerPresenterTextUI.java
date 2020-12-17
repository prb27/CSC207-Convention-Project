package NewUI;

import UseCases.*;

import java.util.ArrayList;
import java.util.List;

public class OrganizerPresenterTextUI extends TextUI {

    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private AdminManager adminManager;
    private EventManager eventManager;
    private RoomManager roomManager;
    private ConversationManager conversationManager;
    private MessageManager messageManager;


    public OrganizerPresenterTextUI(AttendeeManager attendeeManager, OrganizerManager organizerManager, SpeakerManager speakerManager,
                  AdminManager adminManager, MessageManager messageManager, ConversationManager conversationManager,
                  EventManager eventManager, RoomManager roomManager) {
        super(attendeeManager, organizerManager, speakerManager, adminManager, messageManager, conversationManager,
                eventManager, roomManager);
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
        System.out.println("1: View list of users in the conference");
        System.out.println("2: Check if a speaker has an event at a certain time");
        System.out.println("3: Create a new account");

        System.out.println("\nEVENT FUNCTIONS:");
        System.out.println("4: Add a room into the system");
        System.out.println("5: Create new event or Schedule speaker for new event");
        System.out.println("6: Remove an event");
        System.out.println("7: Change speaker for an event (Warning: once this option is chosen, the given event name will be removed. \n All attendees of the event should" +
                " register again for this event.)");
        System.out.println("8: Change time of an event (Warning: once this option is chosen, the given event name will be removed, \n and a new event will be created at your chosen time. All attendees of the event should" +
                " register again for this event.)");
        System.out.println("9: Show events that I haven't signed up for");
        System.out.println("10: Sign up for an event");
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
     * Prints a List of usernames of all Users in this conference of the user type provided in the parameter.
     * @param type Type of user (options: "attendee", "organizer", "speaker" and "all") (param_type: String)
     * @author Ashwin
     */
    public void listOfUsers(String type){

        List<String> users = new ArrayList<>();
        if(type.equals("attendee") || type.equals("all")){
            users.addAll(attendeeManager.getAllAttendeeIds());
        }
        if(type.equals("organizer") || type.equals("all")){
            users.addAll(organizerManager.getAllOrganizerIds());
        }
        if(type.equals("speaker") || type.equals("all")){
            users.addAll(speakerManager.getAllSpeakerIds());
        }
        if(type.equals("admin") || type.equals("all")){
            users.add(adminManager.getAdminName());
        }
        for(String user: users){
            System.out.println(user + "\n");
        }

    }

    public void askForUserType(){
        System.out.println("Enter the type of user (attendee, organizer, speaker, admin): ");
    }

    public void askForUsername(){
        System.out.println("Enter the username: ");
    }

    public void askForPassword(){
        System.out.println("Enter the password: ");
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
        System.out.println("Not a speaker");
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

    public void presentEvents(List<String> eventsNotSignedUpFor){
        for (String event: eventsNotSignedUpFor){
            System.out.println("Event Title: " + event + "\nTime: " + eventManager.getStartTime(event) + "\nRoom: "
                    + eventManager.getRoomNumber(event) + "\nSpeaker: " + eventManager.getSpeakerEvent(event) + "\n");
        }
    }

    public void promptEventWantToAttend(){
        System.out.println("Please enter the title of the event you want to attend (exactly as it appears on the list of titles displayed)");
    }

    public void promptForEventName(){
        System.out.println("Please enter the event's name");
    }

    public void presentNoLongerAttendingEvent(String event){
        System.out.println("You are no longer attending " + event);
    }

}
