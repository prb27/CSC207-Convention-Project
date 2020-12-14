package Presenters.Organizer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;


public class OrganizerMessagingMenuPresenter {

    @FXML
    private ListView orgMessages;

    @FXML
    private Button orgReply;

    @FXML
    private Button orgSignOut;

    @FXML
    private Button orgGoBack;

    @FXML
    private Label orgMessengerUsername;

    public void initialize(){
        orgReply.setOnAction(event -> {
            replyToConvo();
        });
        orgGoBack.setOnAction(event -> {
            try {
                goToOrgMenu();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        orgSignOut.setOnAction(event -> {
            try {
                goBackToLogin();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void loadConversations(){

    }
    private void goBackToLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/LoginMenuView.fxml"));
        Stage stage = (Stage) orgSignOut.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }

    private void goToOrgMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Organizer/OrganizerMenuView.fxml"));
        Stage stage = (Stage) orgGoBack.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }

    private void replyToConvo() {
    }
}
