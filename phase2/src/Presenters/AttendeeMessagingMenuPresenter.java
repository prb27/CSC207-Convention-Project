package Presenters;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class AttendeeMessagingMenuPresenter {
    @FXML
    private Label welcome;
    @FXML
    private Button messenger;
    @FXML
    private Button signOut;

    private final LoginMenuPresenter loginMenuPresenter;
    private final AttendeeMessengerMenuPresenter attendeeMessengerMenuPresenter;

    public AttendeeMessagingMenuPresenter(LoginMenuPresenter loginMenuPresenter,
                                          AttendeeMessengerMenuPresenter attendeeMessengerMenuPresenter){
        this.loginMenuPresenter = loginMenuPresenter;
        this.attendeeMessengerMenuPresenter = attendeeMessengerMenuPresenter;

    }

    @FXML
    private void initialize(){
        welcome.setText("Welcome: " + loginMenuPresenter.getUsername() + "!");
        messenger.setText("Messenger");
        messenger.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
        messenger.setOnAction(event -> {
            try {
                goToMessenger();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        });
        signOut.setText("Sign Out");
        signOut.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
        signOut.setOnAction(event -> {
            try {
                signOut();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        });

    }

    private void goToMessenger() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/AttendeeMessengerMenuView.fxml"));
        Stage stage = (Stage) messenger.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }

    private void signOut() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/LoginMenuView.fxml"));
        Stage stage = (Stage) signOut.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }

}
