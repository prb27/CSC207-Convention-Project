package NewUI;

import Controllers.UserEventController;

import java.util.List;

public class EventMenuPresenterTextUI {
    private final UserEventController userEventController;

    public EventMenuPresenterTextUI(UserEventController userEventController){
        this.userEventController = userEventController;
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
}
