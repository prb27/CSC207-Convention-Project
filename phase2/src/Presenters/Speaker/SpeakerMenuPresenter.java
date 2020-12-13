package Presenters.Speaker;


import Presenters.Interfaces.ISpeakerMenu;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
// import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class SpeakerMenuPresenter implements ISpeakerMenu {


    @FXML
    private Button toEventsFromSpeaker;

    @FXML
    private Button toMessagingFromSpeaker;

    @FXML
    private Button signOut;


    public SpeakerMenuPresenter(){
    }

    @FXML
    private void initialize(){
        toEventsFromSpeaker.setText("Events");
        toEventsFromSpeaker.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
        toEventsFromSpeaker.setOnAction(event -> {
            try {
                goToEvents();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        toMessagingFromSpeaker.setText("Messaging");
        toMessagingFromSpeaker.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
        toMessagingFromSpeaker.setOnAction(event -> {
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

    }

    private void goToEvents() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Speaker/SpeakerEventMenuView.fxml"));
        Stage stage = (Stage) toEventsFromSpeaker.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);

    }
    private void goToMessaging() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/MessagingMenuView.fxml"));
        Stage stage = (Stage) toMessagingFromSpeaker.getScene().getWindow();
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

