package NewUI;

import Controllers.UserEventController;
import UseCases.EventManager;

import java.util.List;

public class EventMenuPresenterTextUI {
    private final EventManager eventManager;

    public EventMenuPresenterTextUI(EventManager eventManager){

        this.eventManager = eventManager;
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
    public void promptEventNameToAdd() {
        System.out.println("Please enter the title of the event you want to attend " +
                "(exactly as it appears on the list of titles displayed)");
    }

    public void promptEventTitleCancel(){
        System.out.println("Please enter the event title you wish to cancel reservation");
    }
}
