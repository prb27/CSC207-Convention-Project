package Presenters.Organizer;

import Controllers.LoginMenuController;
import Controllers.MasterSystem;
import Controllers.OrganizerMenuController;
import Controllers.UserEventController;
import Gateways.ProgramGenerator;
import Presenters.LoginMenuPresenter;
import UseCases.RoomManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class OrganizerEventCreationMenuPresenter {
    private RoomManager roomManager;
    private OrganizerMenuController organizerMenuController;
    private UserEventController userEventController;
    private LoginMenuController loginMenuController;
    private ProgramGenerator programGenerator;
    private MasterSystem masterSystem;

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

    @FXML
    public ComboBox<Integer> durationChoices;

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

    public OrganizerEventCreationMenuPresenter(){

    }

    public void initialize(){
        username.setText(loginMenuController.getCurrUsername());
        selectedSpeakerDisplay.getItems().clear();
        selectedSpeakerDisplay.setItems(speakers);
        ObservableList<String> startTimeChoiceList = FXCollections.observableArrayList("9", "10", "11", "12", "1", "2", "3", "4");
        ObservableList<Integer> durationChoiceList = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8);
        startTimeChoices.setItems(startTimeChoiceList);
        durationChoices.setItems(durationChoiceList);

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

    @FXML
    private void displayRooms(){
        if(powerSocketField.getText() == null || startTimeChoices.getValue() == null || durationChoices.getValue() == null){
            notificationDisplay("Incomplete");
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
        notificationDisplay(result);
    }

    @FXML
    private void goBack() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Organizer/OrganizerMenuView.fxml"));
        Stage stage = (Stage) backButton.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        OrganizerMenuPresenter organizerMenuPresenter = loader.getController();
        organizerMenuPresenter.setMasterSystem(masterSystem);
        stage.setScene(scene);
    }

    @FXML
    private void signOut() throws IOException {
        programGenerator.readToDatabase();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Organizer/LoginMenuView.fxml"));
        Stage stage = (Stage) signOutButton.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        LoginMenuPresenter loginMenuPresenter = loader.getController();
        loginMenuPresenter.setMasterSystem(masterSystem);
        stage.setScene(scene);
    }

    private void notificationDisplay(String error){
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
            case "YES":
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setTitle("Event created successfully");
                alert.setHeaderText("The event with the specified details has been created");
                resetMenu();
                alert.show();
        }
    }

    private void resetMenu(){
        roomNumberDisplay.clear();
        roomNumberDisplay.setPromptText("Room number");
        eventNameField.clear();
        eventNameField.setPromptText("Event name");
        capacityField.clear();
        capacityField.setPromptText("Capacity");
        audioSystemCheck.setSelected(false);
        projectorCheck.setSelected(false);
        powerSocketField.clear();
        selectedSpeakerDisplay.getItems().clear();
    }

    public void setMasterSystem(MasterSystem masterSystem) {
        this.roomManager = masterSystem.getRoomManager();
        this.organizerMenuController = masterSystem.getOrganizerMenuController();
        this.userEventController = masterSystem.getUserEventController();
        this.loginMenuController = masterSystem.getLoginMenuController();
        this.programGenerator = masterSystem.getProgramGenerator();
    }
}
