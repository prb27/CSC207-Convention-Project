package Presenters.Organizer;

import Controllers.OrganizerMenuController;
import Controllers.UserEventController;
import UseCases.RoomManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class OrganizerEventCreationMenuPresenter {
    private RoomManager roomManager;
    private OrganizerMenuController organizerMenuController;
    private UserEventController userEventController;

    @FXML
    public Label username;

    @FXML
    public Label whatsOnDisplay;

    @FXML
    public CheckBox audioSystemCheck;

    @FXML
    public CheckBox projectorCheck;

    @FXML
    public ChoiceBox<String> startTimeChoices;

    ObservableList<String> startTimeChoiceList = FXCollections.observableArrayList("9", "10", "11", "12", "1", "2", "3", "4", "5");

    @FXML
    public ComboBox<Integer> durationChoices;
    ObservableList<Integer> durationChoiceList = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8);

    @FXML
    public Button findRoomButton;

    @FXML
    public TextField eventNameField;

    @FXML
    public TextField capacityField;

    @FXML
    public ListView<String> selectedSpeakerDisplay;
    ObservableList<String> speakers = FXCollections.observableArrayList();

    @FXML
    public Button findSpeakerButton;

    @FXML
    public TextField roomNumberDisplay;

    @FXML
    public Button createEventButton;

    @FXML
    public Button backButton;

    @FXML
    public Button signOutButton;

    @FXML
    public TextField powerSocketField;

    @FXML
    public ListView<String> displayListView;

    public OrganizerEventCreationMenuPresenter(RoomManager roomManager, OrganizerMenuController organizerMenuController, UserEventController userEventController){
        this.roomManager = roomManager;
        this.organizerMenuController = organizerMenuController;
        this.userEventController = userEventController;
    }

    public void initialize(){
        selectedSpeakerDisplay.getItems().clear();
        selectedSpeakerDisplay.setItems(speakers);
    }

    @FXML
    private void displayRooms(){
        if(powerSocketField.getText() == null || startTimeChoices.getValue() == null || durationChoices.getValue() == null){
            errorDisplay("Incomplete");
        }
        displayListView.getItems().clear();
        ObservableList<String> rooms = FXCollections.observableArrayList();
        rooms.addAll(roomManager.roomsWithRequirements(audioSystemCheck.isSelected(), projectorCheck.isSelected(),
                Integer.parseInt(powerSocketField.getText()), startTimeChoices.getValue(), durationChoices.getValue()));
        displayListView.setItems(rooms);
        displayListView.setOnMouseClicked(event -> displayToRoomNumber());
    }

    private void displayToRoomNumber(){
        roomNumberDisplay.clear();
        roomNumberDisplay.setText(displayListView.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void displaySpeakers(){
        displayListView.getItems().clear();
        ObservableList<String> speakers = FXCollections.observableArrayList();
        speakers.addAll(organizerMenuController.listOfUsers("speaker"));
        displayListView.setItems(speakers);
        displayListView.setOnMouseClicked(event -> addSpeaker());
    }


    private void addSpeaker() {
        String selectedSpeaker = displayListView.getSelectionModel().getSelectedItem();
        displayListView.getItems().remove(selectedSpeaker);
        selectedSpeakerDisplay.getItems().add(selectedSpeaker);
    }

    @FXML
    private void removeSelectedSpeaker(){
        String selectedSpeaker = selectedSpeakerDisplay.getSelectionModel().getSelectedItem();
        selectedSpeakerDisplay.getItems().remove(selectedSpeaker);
        displayListView.getItems().add(selectedSpeaker);
    }

    @FXML
    private void createEvent(){
        String result = userEventController.createEventInRoom(username.getText(), eventNameField.getText(),
                startTimeChoices.getValue(), durationChoices.getValue(), Integer.parseInt(capacityField.getText()),
                selectedSpeakerDisplay.getItems(), roomNumberDisplay.getText());
        if(!result.equals("YES")){
            errorDisplay(result);
        }
    }

    private void errorDisplay(String error){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        switch(error){
            case "Incomplete":
                alert.setTitle("Insufficient information entered");
                alert.setHeaderText("You have not selected enough information");
                alert.show();
            case "STC":
                alert.setTitle("Speaker time conflict");
                alert.setHeaderText("One or more of your speakers are not free at the specified time");
                alert.show();
            case "ESOT":
                alert.setTitle("Event scheduled over time");
                alert.setHeaderText("Your event would run past the end of the conference");
                alert.show();
            case "ECF":
                alert.setTitle("Event over capacity");
                alert.setHeaderText("Your event is too large for the room");
                alert.show();
            case "ETC":
                alert.setTitle("Event time conflict");
                alert.setHeaderText("There is a conflict with the timing of the event");
                alert.show();
        }
    }
}
