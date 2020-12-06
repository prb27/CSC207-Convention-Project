package Presenters;


import Presenters.Interfaces.IAttendeeMenu;
import Presenters.LoginMenuPresenter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
// import javafx.scene.control.Label;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class AttendeeMenuPresenter implements IAttendeeMenu {


    @FXML
    private Button toEventsFromAttendee;

    @FXML
    private Button toMessagingFromAttendee;

    @FXML
    private Button signOut;

    @FXML
    private Label welcome;

    private LoginMenuPresenter loginMenuPresenter;


    public AttendeeMenuPresenter(LoginMenuPresenter loginMenuPresenter){
        this.loginMenuPresenter = loginMenuPresenter;
    }

    @FXML
    private void initialize(){
        toEventsFromAttendee.setText("Events");
        toEventsFromAttendee.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
        toEventsFromAttendee.setOnAction(event -> {
            try {
                goToEvents();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        toMessagingFromAttendee.setText("Messaging");
        toMessagingFromAttendee.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
        toMessagingFromAttendee.setOnAction(event -> {
            try {
                goToMessaging();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        signOut.setText("Sign Out");
        signOut.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
        signOut.setOnAction(event -> {
            try {
                signOut();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        welcome.setText("Welcome!" + loginMenuPresenter.getUsername() + "!");

    }

    private void goToEvents() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/AttendeeEventMenuView.fxml"));
        Stage stage = (Stage) toEventsFromAttendee.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);

    }
    private void goToMessaging() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/MessagingMenuView.fxml"));
        Stage stage = (Stage) toMessagingFromAttendee.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }

    private void signOut() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/LoginMenuView.fxml"));
        Stage stage = (Stage) signOut.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }



}
