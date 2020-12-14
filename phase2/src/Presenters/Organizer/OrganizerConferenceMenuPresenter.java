package Presenters.Organizer;

import Controllers.AccountHandler;
import Controllers.OrganizerMenuController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class OrganizerConferenceMenuPresenter {

    private OrganizerMenuController organizerMenuController;
    private AccountHandler accountHandler;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField newPassword;

    @FXML
    private PasswordField confirmNewPassword;

    @FXML
    private Button createUser;

    @FXML
    private ChoiceBox<String> userTypeChoice;
    ObservableList<String> userTypeChoiceList = FXCollections.observableArrayList("Attendee", "Organizer", "Speaker", "Admin");

    @FXML
    private ListView<String> userList;
    ObservableList<String> userListInput = FXCollections.observableArrayList();

    @FXML
    private TextField selectedUsername;

    @FXML
    private TextField selectedType;

    @FXML
    private Label freeAt;

    @FXML
    private ChoiceBox<String> timeChoices;

    @FXML
    private Label duration;

    @FXML
    private ChoiceBox<Integer> durationChoice;

    @FXML
    private TextField displayFree;

    public OrganizerConferenceMenuPresenter(OrganizerMenuController organizerMenuController, AccountHandler accountHandler){
        this.organizerMenuController = organizerMenuController;
        this.accountHandler = accountHandler;
    }

    public void initialize() {
        userTypeChoice.setItems(userTypeChoiceList);
        userTypeChoice.setValue("attendee");
        ObservableList<String> eventTimes = FXCollections.observableArrayList("9", "10", "11", "12", "1", "2", "3", "4", "5");
        timeChoices.setItems(eventTimes);
        timeChoices.setValue("9");
        ObservableList<Integer> durationChoices = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8);
        durationChoice.setItems(durationChoices);
        durationChoice.setValue(1);
        createUser.setOnAction(event -> createUser());
    }

    @FXML
    public void seeAttendees(){
        userListInput.removeAll();
        userListInput.addAll(organizerMenuController.listOfUsers("attendee"));
        userList.getItems().clear();
        userList.getItems().addAll(userListInput);
    }
    @FXML
    public void seeOrganizers(){
        userListInput.removeAll();
        userListInput.addAll(organizerMenuController.listOfUsers("organizer"));
        userList.getItems().clear();
        userList.getItems().addAll(userListInput);
    }
    @FXML
    public void seeSpeakers(){
        userListInput.removeAll();
        userListInput.addAll(organizerMenuController.listOfUsers("speaker"));
        userList.getItems().clear();
        userList.getItems().addAll(userListInput);
    }
    @FXML
    public void seeAdmin(){
        userListInput.removeAll();
        userListInput.addAll(organizerMenuController.listOfUsers("admin"));
        userList.getItems().clear();
        userList.getItems().addAll(userListInput);
    }

    @FXML
    public void seeAll(){
        userListInput.removeAll();
        userListInput.addAll(organizerMenuController.listOfUsers("all"));
        userList.getItems().clear();
        userList.getItems().addAll(userListInput);
    }

    @FXML
    public void createUser(){
        if(!newPassword.getText().equals(confirmNewPassword.getText())){
            mismatchedPassword();
        } else {
            if(!organizerMenuController.createNewAccount(usernameField.getText(), newPassword.getText(), userTypeChoice.getValue())){
                existingUser();
            }
        }
    }

    @FXML
    public void displayInfo(){
        String username = userList.getSelectionModel().getSelectedItem();
        selectedUsername.setText(username);

        String type = accountHandler.getAccountType(username);
        selectedType.setText(type);

        if(type.equals("speaker")){
            freeAt.setDisable(false);
            timeChoices.setDisable(false);
            duration.setDisable(false);
            durationChoice.setDisable(false);
            displayFree.setDisable(false);
            if(organizerMenuController.checkIfSpeakerFreeAtTimeFor(username, timeChoices.getValue(), durationChoice.getValue()).equals("YES")){
                displayFree.setText("Yes");
            } else {
                displayFree.setText("No");
            }
        } else {
            freeAt.setDisable(true);
            timeChoices.setDisable(true);
            duration.setDisable(true);
            durationChoice.setDisable(true);
            displayFree.setDisable(true);
        }

    }

    private void mismatchedPassword(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid information entered");
        alert.setHeaderText("Your passwords do not match ");
        usernameField.clear();
        newPassword.clear();
        confirmNewPassword.clear();
        usernameField.setPromptText("Username");
        newPassword.setPromptText("Password");
        alert.show();
    }

    private void existingUser(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid information entered");
        alert.setHeaderText("A user with that username already exists");
        usernameField.clear();
        newPassword.clear();
        confirmNewPassword.clear();
        usernameField.setPromptText("Username");
        newPassword.setPromptText("Password");
        alert.show();
    }
}
