package NewUI;

import com.sun.org.apache.xpath.internal.operations.String;

public class AdminTextUI extends TextUI {
    public void emptyEventsDeleted() {
        System.out.println("all events without attendees were deleted");
    }

    public void messageID() {
        System.out.println("Please enter the message id you want to delete");
    }

    public void messageDeleted(String message) {
        System.out.println("message with id "+message+" was deleted");
    }
}
