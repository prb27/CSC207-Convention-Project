package Presenters.Organizer;
import Controllers.LoginMenuController;
import Controllers.MasterSystem;
import Gateways.ProgramGenerator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class OrganizerMenuPresenter {

    private LoginMenuController loginMenuController;
    private ProgramGenerator programGenerator;
    private MasterSystem masterSystem;

    @FXML
    private Label username;

    @FXML
    private Button toEventsFromOrganizer;

    @FXML
    private Button toMessagingFromOrganizer;

    @FXML
    private Button signOutButton;

    @FXML
    private Button toConferenceFromOrganizer;

    @FXML
    private Button createEventButton;

    public OrganizerMenuPresenter(){

    }

    public void initialize(){
        username.setText(loginMenuController.getCurrUsername());
        toEventsFromOrganizer.setText("Events");
        toEventsFromOrganizer.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
        toEventsFromOrganizer.setOnAction(event -> {
            try {
                goToEvents();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        toMessagingFromOrganizer.setText("Messaging");
        toMessagingFromOrganizer.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
        toMessagingFromOrganizer.setOnAction(event -> {
            try {
                goToMessaging();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        toConferenceFromOrganizer.setText("Events");
        toConferenceFromOrganizer.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
        toConferenceFromOrganizer.setOnAction(event -> {
            try {
                goToConference();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        signOutButton.setText("Sign Out");
        signOutButton.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
        signOutButton.setOnAction(event -> {
            try {
                signOut();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    private void goToEvents() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Organizer/OrganizerEventMenuView.fxml"));
        Stage stage = (Stage) toEventsFromOrganizer.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }
    private void goToMessaging() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Organizer/OrganizerMessagingMenuView.fxml"));
        Stage stage = (Stage) toMessagingFromOrganizer.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }

    private void goToConference() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Organizer/OrganizerConferenceMenuView.fxml"));
        Stage stage = (Stage) toConferenceFromOrganizer.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }

    @FXML
    private void goToEventCreation() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Organizer/OrganizerEventCreationMenuView.fxml"));
        Stage stage = (Stage) createEventButton.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }

    private void signOut() throws IOException {
        programGenerator.readToDatabase();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/LoginMenuView.fxml"));
        Stage stage = (Stage) signOutButton.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }

    public void setMasterSystem(MasterSystem masterSystem) {
        this.masterSystem = masterSystem;
        this.programGenerator = masterSystem.getProgramGenerator();
        this.loginMenuController = masterSystem.getLoginMenuController();
    }
}
