package Presenters;

import Controllers.AttendeeEventMenuController;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class AttendeeEventMenuPresenter {

    @FXML
    public ListView listOfInvitedEvents;

    @FXML
    private ListView listOfAllEvents;

    @FXML
    private ListView listOfAttendedEvents;

    private AttendeeEventMenuController attendeeEventMenuController;

    public AttendeeEventMenuPresenter(AttendeeEventMenuController attendeeEventMenuController) {
        this.attendeeEventMenuController = attendeeEventMenuController;
    }

    @FXML
    private void initialize(){

        for(String eventName : attendeeEventMenuController.getListOfAllEvents()){
            listOfAllEvents.getItems().add(eventName);
        }

        for (String eventName: attendeeEventMenuController.getListOfAllAttendedEvents()){
            listOfAttendedEvents.getItems().add(eventName);
        }

    }

}
