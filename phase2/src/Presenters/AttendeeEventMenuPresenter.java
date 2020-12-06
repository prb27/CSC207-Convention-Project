package Presenters;

import Controllers.AttendeeEventMenuController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.List;

public class AttendeeEventMenuPresenter {

    @FXML
    public ListView<String> listOfInvitedEvents;

    @FXML
    private ListView<String> listOfAllEvents;

    @FXML
    private Menu listOfAttendedEvents;

    @FXML
    private Label welcome;

    @FXML
    private VBox events;

    private AttendeeEventMenuController attendeeEventMenuController;

    private LoginMenuPresenter loginMenuPresenter;

    public AttendeeEventMenuPresenter(AttendeeEventMenuController attendeeEventMenuController,
                                      LoginMenuPresenter loginMenuPresenter) {
        this.attendeeEventMenuController = attendeeEventMenuController;
        this.loginMenuPresenter = loginMenuPresenter;
    }

    @FXML
    private void initialize(){
        welcome.setText("Welcome:" + loginMenuPresenter.getUsername() + "!");
        events.setSpacing(10);

    }

}
