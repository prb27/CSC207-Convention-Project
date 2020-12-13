package Presenters.Attendee;

import Controllers.LoginMenuController;
import Controllers.MessengerMenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
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



    private final MessengerMenuController messengerMenuController;
    private final LoginMenuController loginMenuController;

    public AttendeeMessengerMenuPresenter(MessengerMenuController messengerMenuController, LoginMenuController loginMenuController){
        this.messengerMenuController = messengerMenuController;
        this.loginMenuController = loginMenuController;
    }

    @FXML
    private void initialize(){
        welcome.setText("Welcome: " + loginMenuController.getCurrUsername() + "!");
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
                if (!content.getText().equals("") && !recipientIDs.getText().equals("")){
                    sendMessage();
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

    private void setPrivilegesAttendee(){

        allRecipients.setOnAction(event -> {
            recipientIDs.setDisable(allRecipients.isSelected());
        });
        recipientIDs.setOnAction(event -> {
            allRecipients.setDisable(recipientIDs.getText().equals(""));
        });

    }
    private void sendMessage() throws IOException {
        if (allRecipients.isSelected()){
            String sender = loginMenuController.getCurrUsername();
            String message = content.getText();
            for (String recipient: messengerMenuController.getUsersToMessage(sender)){
                String recipientType = messengerMenuController.getAccountType(recipient);
                messengerMenuController.attendeeSendMessage(sender, recipient, message, recipientType);
            }
            goBack();
        }
        if (!recipientIDs.getText().contains(",") && !recipientIDs.getText().trim().equals("")){
            String recipientID = recipientIDs.getText();
            String sender = loginMenuController.getCurrUsername();
            String message = content.getText();
            String receiverType = messengerMenuController.getAccountType(recipientID);
            if(messengerMenuController.attendeeSendMessage(sender, recipientID, message, receiverType)){
                goBack();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Something went wrong");
                alert.setHeaderText("Something went wrong");
                alert.setContentText("Please look into it");
                recipientIDs.clear();
                recipientIDs.setDisable(false);
                allRecipients.setDisable(false);
            }
            goBack();
        }
    }
}
