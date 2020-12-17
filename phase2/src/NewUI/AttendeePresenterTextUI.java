package NewUI;

import com.sun.org.apache.xpath.internal.operations.String;

public class AttendeePresenterTextUI {
    /**
     * print out a set of functions that an attendee is able to do
     * @author Juan Yi Loke
     * @param username: the username of the user that is being prompted
     */
    public void attendeeMainMenu(String username) {
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
    public void selectEvent(){
        System.out.println("Please enter the event title you want to attend (exactly as it appears on the event list)");
    }
    public void nameToMessage(){
        System.out.println("Please enter the ID of the person you wish to message: ");
    }
    public void content(){
        System.out.println("Please enter the ID of the person you wish to message: ");
    }
    public void reply() {
        System.out.println("Enter \"r\" to reply in this conversation. [Any other input will exit this menu]");
    }
}
