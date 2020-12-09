package Presenters;

import Controllers.MessengerMenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
public class SpeakerMessengerMenuPresenter {

    @FXML
    private TextField recipientIDs;
    @FXML
    private TextField eventIDs;
    @FXML
    private TextArea content;
    @FXML
    private Button sendMessage;
    @FXML
    private Button goBack;
    @FXML
    private Button signOut;
    @FXML
    private Label welcome;
    @FXML
    private CheckBox allRecipients;



    private MessengerMenuController messengerMenuController;
    private LoginMenuPresenter loginMenuPresenter;

    public SpeakerMessengerMenuPresenter(MessengerMenuController messengerMenuController, LoginMenuPresenter loginMenuPresenter){
        this.messengerMenuController = messengerMenuController;
        this.loginMenuPresenter = loginMenuPresenter;
    }

    @FXML
    private void initialize(){
        welcome.setText("Welcome: " + loginMenuPresenter.getUsername() + "!");


        goBack.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
        goBack.setOnAction(event -> {
            try {
                goBack();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        });
        signOut.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
        signOut.setOnAction(event -> {
            try {
                signOut();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        });
        sendMessage.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
        sendMessage.setOnAction(event -> {
            try {

                if(!recipientIDs.getText().contains(",") && !recipientIDs.getText().trim().equals("") && !eventIDs.getText().trim().equals("") && !allRecipients.isSelected()){
                    sendSpeakerMessage();
                }
                if (allRecipients.isSelected() && !eventIDs.getText().trim().equals("")){
                    sendMessageByTalk();

                }
                if(allRecipients.isSelected() && !eventIDs.getText().trim().equals("") && eventIDs.getText().contains(",")){
                    sendMultiMessageByTalk();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void goBack() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/MessagingMenuView.fxml"));
        Stage stage = (Stage) goBack.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }

    private void signOut() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/LoginMenuView.fxml"));
        Stage stage = (Stage) signOut.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }


    private void sendSpeakerMessage() throws IOException {
        List  <String> eventNames = new ArrayList();
        if (!recipientIDs.getText().contains(",") && !recipientIDs.getText().trim().equals("") && !eventIDs.getText().trim().equals("")){
            String recipientID = recipientIDs.getText();
            String eventName = eventIDs.getText();
            eventNames.add(eventName);
            String speakerID = loginMenuPresenter.getUsername();
            String message = content.getText();
            messengerMenuController.speakerMessageAttendee(speakerID, eventNames, recipientID, message);
        }

        goBack();
    }

    private void sendMessageByTalk() throws IOException {

        if (allRecipients.isSelected() && !eventIDs.getText().trim().equals("")){
            String eventName = eventIDs.getText();
            String speakerID = loginMenuPresenter.getUsername();
            String message = content.getText();
            messengerMenuController.speakerMessageByTalk(speakerID, eventName, message);
        }

        goBack();

    }
    private void sendMultiMessageByTalk() throws IOException {

        if (allRecipients.isSelected() && !eventIDs.getText().trim().equals("")){
            String eventName = eventIDs.getText();
            String speakerID = loginMenuPresenter.getUsername();
            String message = content.getText();
            messengerMenuController.speakerMessageByTalk(speakerID, eventName, message);
        }

        goBack();

    }
}
