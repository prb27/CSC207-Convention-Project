package Presenters.Organizer;

import Controllers.LoginMenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class OrganizerMessengerMenuPresenter {
    private LoginMenuController loginMenuController;

    @FXML
    private TextArea content;

    @FXML
    private Button sendMessage;

    @FXML
    private Button goBack;

    @FXML
    private Button signOut;

    @FXML
    private CheckBox allRecipients;

    @FXML
    private TextField recipientIDs;

    @FXML
    private Label username;

    public OrganizerMessengerMenuPresenter(LoginMenuController loginMenuController){
        this.loginMenuController = loginMenuController;
    }

    public void initialize(){
        username.setText(loginMenuController.getCurrUsername());

        sendMessage.setOnAction(event -> {
            sendMessage();
        });
        goBack.setOnAction(event -> {
            try {
                goBackToOptions();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        signOut.setOnAction(event -> {
            try {
                signOut();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void sendMessage() {
    }

    private void goBackToOptions() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Organizer/OrganizerMenuView.fxml"));
        Stage stage = (Stage) goBack.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }

    private void signOut() throws IOException {
        loginMenuController.setCurrUsername(null);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/LoginMenuView.fxml"));
        Stage stage = (Stage) signOut.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }
}
