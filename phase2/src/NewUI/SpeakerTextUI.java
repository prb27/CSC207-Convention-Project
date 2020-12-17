package NewUI;

import java.util.List;

public class SpeakerTextUI extends TextUI{
    public void eventDisplay(String event, List<String> info) {
        System.out.println(event);
        for (String i: info)
            System.out.println(i);
    }

    public void seeEventList(List<String> eventList){
        for (String e: eventList)
            System.out.println(e);
    }

    public void getAttendeeToMess() {
        System.out.println("Please enter the username of the Attendee you wish to message:");
    }

    public void rep() {
        System.out.println("Enter \"r\" to reply in this conversation. [Any other input will exit this menu]");
    }

}
