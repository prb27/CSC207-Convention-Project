package Presenters.Organizer;

import Controllers.*;
import Gateways.ProgramGenerator;
import Presenters.LoginMenuPresenter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrganizerEventMenuPresenter {

    public Button backButton;
    public Button signOutButton;

    private OrganizerMenuController organizerMenuController;
    private EventsSearchEngine eventsSearchEngine;
    private ProgramGenerator programGenerator;
    private UserEventController userEventController;

    @FXML
    public Label username;
    @FXML
    public ListView<String> detailList;
    @FXML
    public TextField eventNameDisplay;
    @FXML
    public Label displayTag;
    @FXML
    public Button changeSpeakerButton;
    @FXML
    public Button changeTimeButton;
    @FXML
    public Button signUpButton;
    @FXML
    public Button cancelSpotButton;
    @FXML
    public Button removeEvent;
    @FXML
    public Button seeAllEventsButton;
    @FXML
    public Button seeAttendedEventsButton;
    @FXML
    public Button seeUnattendedEventsButton;
    @FXML
    public ListView<String> displayListView;
    @FXML
    public TextField searchSpeakerField;
    @FXML
    public CheckBox yesSpeaker;
    @FXML
    public CheckBox yesTime;
    @FXML
    public CheckBox yesDuration;
    @FXML
    public Button searchWithParametersButton;
    @FXML
    public ChoiceBox<String> startTimeChoice;
    @FXML
    public ChoiceBox<Integer> durationChoice;
    @FXML
    public TextField roomIdField;
    @FXML
    public TextField capacityField;
    @FXML
    public TextField socketField;
    @FXML
    public CheckBox yesProjector;
    @FXML
    public CheckBox yesAudioSystem;
    @FXML
    public TextField timeField;

    private MasterSystem masterSystem;

    public OrganizerEventMenuPresenter(){

    }

    public void initialize(){
        ObservableList<String> startTimeChoices = FXCollections.observableArrayList("9", "10", "11", "12", "1", "2", "3", "4");
        ObservableList<Integer> durationChoices = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8);
        startTimeChoice.setItems(startTimeChoices);
        durationChoice.setItems(durationChoices);
        backButton.setOnAction(event -> {
            try {
                goBack();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        signOutButton.setOnAction(event -> {
            try {
                signOut();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void signOut() throws IOException {
        programGenerator.readToDatabase();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Organizer/LoginMenuView.fxml"));
        Stage stage = (Stage) signOutButton.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        MasterSystem masterSystem = programGenerator.readFromDatabase();
        LoginMenuPresenter loginMenuPresenter = loader.getController();
        loginMenuPresenter.setMasterSystem(masterSystem);
        stage.setScene(scene);
    }

    private void goBack() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Organizer/OrganizerMenuView.fxml"));
        Stage stage = (Stage) backButton.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        OrganizerMenuPresenter organizerMenuPresenter = loader.getController();
        organizerMenuPresenter.setMasterSystem(masterSystem);
        stage.setScene(scene);
    }

    @FXML
    public void loadEventInfo(){
        String eventName = displayListView.getSelectionModel().getSelectedItem();

        if(eventName != null) {
            eventNameDisplay.setText(eventName);
            ObservableList<String> eventDetails = FXCollections.observableArrayList();
            for (int i = 0; i < organizerMenuController.getEventDetails(eventName).size(); i++) {
                if (i == 0) {
                    eventDetails.add("Start Time: " + organizerMenuController.getEventDetails(eventName).get(i));
                } else if (i == 1) {
                    eventDetails.add("Duration: " + organizerMenuController.getEventDetails(eventName).get(i));
                } else if (i == 2) {
                    eventDetails.add("Capacity: " + organizerMenuController.getEventDetails(eventName).get(i));
                } else if (i == 3) {
                    eventDetails.add("Room Number: " + organizerMenuController.getEventDetails(eventName).get(i));
                } else {
                    eventDetails.add("Speaker: " + organizerMenuController.getEventDetails(eventName).get(i));
                }
            }
            detailList.setItems(eventDetails);
        }
    }

    @FXML
    public void removeEvent() {
        detailList.getItems().clear();
        userEventController.removeCreatedEvent(username.getText(), eventNameDisplay.getText());
        eventNameDisplay.clear();
    }

    @FXML
    public void seeSpeakers(){
        displayListView.getItems().clear();
        ObservableList<String> speakers = FXCollections.observableArrayList();
        speakers.addAll(organizerMenuController.listOfUsers("speaker"));
        detailList.getItems().clear();
        displayListView.setOnMouseClicked(event -> addSpeakers());
        detailList.setOnMouseClicked(event -> removeSpeakers());
        changeSpeakerButton.setDisable(false);
    }

    private void removeSpeakers() {
        displayListView.getItems().add(detailList.getSelectionModel().getSelectedItem());
        detailList.getItems().remove(detailList.getSelectionModel().getSelectedItem());
    }

    private void addSpeakers() {
        detailList.getItems().add(detailList.getSelectionModel().getSelectedItem());
        displayListView.getItems().remove(detailList.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void changeSpeaker(){
        String room = organizerMenuController.getEventDetails(eventNameDisplay.getText()).get(3);
        List<String> names = new ArrayList<String>();
        names.add(timeField.getText());
        organizerMenuController.changeSpeakerForEventThroughOrganizer(username.getText(), eventNameDisplay.getText(),
                names, room);
    }

    @FXML
    public void changeTime(){
        String room = organizerMenuController.getEventDetails(eventNameDisplay.getText()).get(3);
        organizerMenuController.changeEventStartTime(username.getText(), eventNameDisplay.getText(),
                timeField.getText(), room);
    }

    @FXML
    public void signUp(){
        organizerMenuController.signUpAsOrganizer(eventNameDisplay.getText(), username.getText());
        seeNotAttending();
    }

    @FXML
    public void cancelSpot(){
        organizerMenuController.cancelSpotAsOrganizer(eventNameDisplay.getText(), username.getText());
        seeAttending();
    }

    @FXML
    public void seeAllEvents() {
        displayTag.setText("Events");
        signUpButton.setDisable(true);
        cancelSpotButton.setDisable(true);

        displayListView.setOnMouseClicked(event -> loadEventInfo());

        displayListView.getItems().clear();
        ObservableList<String> allEvents = FXCollections.observableArrayList();
        allEvents.addAll(eventsSearchEngine.allEventsUnformatted());
        displayListView.setItems(allEvents);

        changeSpeakerButton.setDisable(false);
        changeTimeButton.setDisable(false);
    }

    @FXML
    public void seeAttending() {
        displayTag.setText("Events");
        signUpButton.setDisable(true);
        changeSpeakerButton.setDisable(true);
        changeTimeButton.setDisable(true);

        displayListView.setOnMouseClicked(event -> loadEventInfo());

        displayListView.getItems().clear();
        ObservableList<String> attending = FXCollections.observableArrayList();
        attending.addAll(organizerMenuController.organizerEventsAttending(username.getText()));
        displayListView.setItems(attending);

        cancelSpotButton.setDisable(false);
    }

    @FXML
    public void seeNotAttending() {
        displayTag.setText("Events");
        changeSpeakerButton.setDisable(true);
        changeTimeButton.setDisable(true);
        cancelSpotButton.setDisable(true);

        displayListView.setOnMouseClicked(event -> loadEventInfo());

        displayListView.getItems().clear();
        ObservableList<String> notAttending = FXCollections.observableArrayList();
        notAttending.addAll(organizerMenuController.organizerEventsNotAttending(username.getText()));
        displayListView.setItems(notAttending);

        signUpButton.setDisable(false);
    }

    @FXML
    public void searchViaParameters() {
        displayTag.setText("Events");
        displayListView.setOnMouseClicked(event -> loadEventInfo());

        if(yesSpeaker.isSelected() && !yesTime.isSelected() && !yesDuration.isSelected()){
            displayListView.getItems().clear();
            ObservableList<String> events = FXCollections.observableArrayList();
            events.addAll(eventsSearchEngine.eventsWithSpeakerUnformatted(searchSpeakerField.getText()));
            displayListView.setItems(events);
        } else if(!yesSpeaker.isSelected() && yesTime.isSelected() && !yesDuration.isSelected()){
            displayListView.getItems().clear();
            ObservableList<String> events = FXCollections.observableArrayList();
            events.addAll(eventsSearchEngine.eventsAtStartTimeUnformatted(startTimeChoice.getValue()));
            displayListView.setItems(events);
        } else if(!yesSpeaker.isSelected() && !yesTime.isSelected() && yesDuration.isSelected()){
            displayListView.getItems().clear();
            ObservableList<String> events = FXCollections.observableArrayList();
            events.addAll(eventsSearchEngine.eventsForThisDurationUnformatted(durationChoice.getValue()));
            displayListView.setItems(events);
        } else if(yesSpeaker.isSelected() && yesTime.isSelected() && !yesDuration.isSelected()){
            displayListView.getItems().clear();
            ObservableList<String> events = FXCollections.observableArrayList();
            events.addAll(eventsSearchEngine.eventWithSpeakerAndStartTimeUnformatted(searchSpeakerField.getText(), startTimeChoice.getValue()));
            displayListView.setItems(events);
        } else if(yesSpeaker.isSelected() && !yesTime.isSelected() && yesDuration.isSelected()){
            displayListView.getItems().clear();
            ObservableList<String> events = FXCollections.observableArrayList();
            events.addAll(eventsSearchEngine.eventsWithSpeakerAndDurationUnformatted(searchSpeakerField.getText(), durationChoice.getValue()));
            displayListView.setItems(events);
        } else if(!yesSpeaker.isSelected() && yesTime.isSelected() && yesDuration.isSelected()){
            displayListView.getItems().clear();
            ObservableList<String> events = FXCollections.observableArrayList();
            events.addAll(eventsSearchEngine.eventsWithStartTimeAndDurationUnformatted(startTimeChoice.getValue(), durationChoice.getValue()));
            displayListView.setItems(events);
        }else if(yesSpeaker.isSelected() && yesTime.isSelected() && yesDuration.isSelected()){
            displayListView.getItems().clear();
            ObservableList<String> events = FXCollections.observableArrayList();
            events.addAll(eventsSearchEngine.eventWithSpeakerNameStartTimeAndDurationUnformatted(searchSpeakerField.getText(),
                    startTimeChoice.getValue(), durationChoice.getValue()));
            displayListView.setItems(events);
        } else {
            seeAllEvents();
        }
    }

    @FXML
    public void createRoom(){
        organizerMenuController.organizerAddNewRoom(username.getText(), roomIdField.getText(), Integer.parseInt(capacityField.getText()),
                yesProjector.isSelected(), yesAudioSystem.isSelected(), Integer.parseInt(socketField.getText()));

    }

    public void setMasterSystem(MasterSystem masterSystem){
        this.masterSystem = masterSystem;
        LoginMenuController loginMenuController = masterSystem.getLoginMenuController();
        this.organizerMenuController = masterSystem.getOrganizerMenuController();
        this.eventsSearchEngine = masterSystem.getEventsSearchEngine();
        this.programGenerator = masterSystem.getProgramGenerator();
        this.userEventController = masterSystem.getUserEventController();
        username.setText(loginMenuController.getCurrUsername());
    }
}
