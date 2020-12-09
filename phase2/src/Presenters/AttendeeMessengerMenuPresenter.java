package Presenters;

import Controllers.MessengerMenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
public class AttendeeMessengerMenuPresenter {

    @FXML
    private TextField recipientIDs;
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

    public AttendeeMessengerMenuPresenter(MessengerMenuController messengerMenuController, LoginMenuPresenter loginMenuPresenter){
        this.messengerMenuController = messengerMenuController;
        this.loginMenuPresenter = loginMenuPresenter;
    }

    @FXML
    private void initialize(){
        welcome.setText("Welcome: " + loginMenuPresenter.getUsername() + "!");
        setPrivilegesAttendee();


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
                sendMessage();
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

    private void setPrivilegesAttendee(){

        allRecipients.setOnAction(event -> {
            recipientIDs.setDisable(allRecipients.isSelected());
        });

    }
    private void sendMessage() throws IOException {
        if (!recipientIDs.getText().contains(",") && !recipientIDs.getText().trim().equals("")){
            String recipientID = recipientIDs.getText();
            String sender = loginMenuPresenter.getUsername();
            String message = content.getText();
            String recieverAccountType = messengerMenuController.getAccountType(recipientID);
            messengerMenuController.attendeeSendMessage(loginMenuPresenter.getUsername(), recipientIDs.getText(), content.getText(), messengerMenuController.getAccountType(rec));
        }
        if (recipientIDs.getText().trim() != ""){
            List<String> recipients = new ArrayList<>();
            String[] arrOfStr = recipientIDs.getText().split(",");
            for (String recipient: arrOfStr){
                recipients.add(recipient.trim());
            }

            goBack();
        }
    }
}
