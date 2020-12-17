package NewUI;

public class OrganizerPresenterTextUI{


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

    public void askForUserType(){
        System.out.println("Enter the type of user (attendee, organizer, speaker, admin): ");
    }

    public void askForUsername(){
        System.out.println("Enter the username: ");
    }

    public void askForPassword(){
        System.out.println("Enter the password: ");
    }

}
