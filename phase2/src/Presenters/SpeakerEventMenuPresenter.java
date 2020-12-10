package Presenters;

import Controllers.MessengerMenuController;
import Controllers.UserEventController;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.HashMap;
import java.util.List;

import static javafx.collections.FXCollections.observableArrayList;

public class SpeakerEventMenuPresenter {


    @FXML
    private TableView<String> table;
    @FXML
    private TableColumn<String, String> eventName, eventTime;

    private UserEventController userEventController;
    private LoginMenuPresenter loginMenuPresenter;

    public SpeakerEventMenuPresenter(UserEventController userEventController, LoginMenuPresenter loginMenuPresenter){
        this.userEventController = userEventController;
        this.loginMenuPresenter = loginMenuPresenter;
    }
    public void setLabelText(){
        String speakerID = loginMenuPresenter.getUsername();
        HashMap<String, String>list = userEventController.seeAllEventsForSpeaker(speakerID);

    }

}
