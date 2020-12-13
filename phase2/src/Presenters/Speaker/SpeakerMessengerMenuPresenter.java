package Presenters.Speaker;

import Controllers.LoginMenuController;
import Scrap.CurrUsernameInfoFileHandler;
import Controllers.MessengerMenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    private final LoginMenuController loginMenuController;

    public SpeakerMessengerMenuPresenter(MessengerMenuController messengerMenuController, LoginMenuController loginMenuController){
        this.messengerMenuController = messengerMenuController;
        this.loginMenuController = loginMenuController;
    }

    @FXML
    private void initialize(){
        welcome.setText("Welcome: " + loginMenuController.getCurrUsername() + "!");


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
        if (!recipientIDs.getText().contains(",") && !recipientIDs.getText().trim().equals("") && !eventIDs.getText().trim().equals("")) {
            String recipientID = recipientIDs.getText();
            String eventName = eventIDs.getText();
            eventNames.add(eventName);
            String speakerID = loginMenuController.getCurrUsername();
            String message = content.getText();
            messengerMenuController.speakerMessageAttendee(speakerID, eventNames, recipientID, message);

            if (messengerMenuController.speakerMessageAttendee(speakerID, eventNames, recipientID, message)) {
                goBack();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Something went wrong");
                alert.setHeaderText("Something went wrong");
                alert.setContentText("Please look into it");
                recipientIDs.clear();
                recipientIDs.setDisable(false);
                allRecipients.setDisable(false);
            }
        }

        goBack();
    }

    private void sendMessageByTalk() throws IOException {

        if (allRecipients.isSelected() && !eventIDs.getText().trim().equals("")){
            String eventName = eventIDs.getText();
            String speakerID = loginMenuController.getCurrUsername();
            String message = content.getText();
            String validator = messengerMenuController.speakerMessageByTalk(speakerID, eventName, message);
            if(ErrorChecker(validator)){
                goBack();

            }

        }

    }

    private void sendMultiMessageByTalk() throws IOException {

        if (allRecipients.isSelected() && !eventIDs.getText().trim().equals("")){
            String eventName = eventIDs.getText();
            String speakerID = loginMenuController.getCurrUsername();
            String message = content.getText();
            String validator = messengerMenuController.speakerMessageByTalk(speakerID, eventName, message);
            if(ErrorChecker(validator)){
                goBack();

            }
        }

        goBack();

    }

    //Helper Method
    private boolean ErrorChecker(String validator){
        boolean checker = false;
        if (validator.equals("SDE")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Something went wrong");
            alert.setHeaderText("Something went wrong");
            alert.setContentText("Speaker Does Not Exist");
            recipientIDs.clear();
            recipientIDs.setDisable(false);
            allRecipients.setDisable(false);

        }
        else if (validator.equals("EDE")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Something went wrong");
            alert.setHeaderText("Something went wrong");
            alert.setContentText("Event Does Not Exist");
            recipientIDs.clear();
            recipientIDs.setDisable(false);
            allRecipients.setDisable(false);

        }
        else if (validator.equals("SEC")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Something went wrong");
            alert.setHeaderText("Something went wrong");
            alert.setContentText("Speaker and Event Conflict");
            recipientIDs.clear();
            recipientIDs.setDisable(false);
            allRecipients.setDisable(false);

        }
        else{
            checker = true;
        }

        return checker;

    }
}