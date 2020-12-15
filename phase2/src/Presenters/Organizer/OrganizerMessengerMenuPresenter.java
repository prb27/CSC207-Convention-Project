package Presenters.Organizer;

import Controllers.AccountHandler;
import Controllers.LoginMenuController;
import Controllers.MasterSystem;
import Controllers.MessengerMenuController;
import Gateways.ProgramGenerator;
import Presenters.LoginMenuPresenter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrganizerMessengerMenuPresenter {
    @FXML
    private CheckBox allEvents;
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

    private ChoiceBox<String> userTypeChoice;
    ObservableList<String> types = FXCollections.observableArrayList("attendee", "speaker", "organizer");

    private  ProgramGenerator programGenerator;
    private MessengerMenuController messengerMenuController;
    private  LoginMenuController loginMenuController;
    private MasterSystem masterSystem;
    private AccountHandler accountHandler;

    @FXML
    private void initialize(){

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
    }

    /**
     * Redirects user to the previous menu ie.messaging menu view
     * @throws IOException
     */
    private void goBack() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Organizer/OrganizerMessagingMenuView.fxml"));
        Stage stage = (Stage) goBack.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        OrganizerMessagingMenuPresenter organizerMessagingMenuPresenter = loader.getController();
        organizerMessagingMenuPresenter.setMasterSystem(masterSystem);
        stage.setScene(scene);
    }

    /**
     * Redirects user to loginmenuview
     * @throws IOException
     */
    private void signOut() throws IOException {
        programGenerator.readToDatabase();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/LoginMenuView.fxml"));
        Stage stage = (Stage) signOut.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        MasterSystem masterSystem = programGenerator.readFromDatabase();
        LoginMenuPresenter loginMenuPresenter = loader.getController();
        loginMenuPresenter.setMasterSystem(masterSystem);
        stage.setScene(scene);
    }

    @FXML
    public void sendMessage(){
        if(allRecipients.isSelected()){
            sendMessageToAll();
        } else if(allEvents.isSelected()){
            sendMessageByEvent();
        } else {
            sendMessageToIndividual();
        }
    }

    public void sendMessageToIndividual(){
        String userType = accountHandler.getAccountType(recipientIDs.getText());
        messengerMenuController.organizerSendMessageToSingle(welcome.getText(), recipientIDs.getText(), content.getText(), userType);
    }

    public void sendMessageToAll(){
        messengerMenuController.organizerSendMessageToAll(welcome.getText(), content.getText(), userTypeChoice.getValue());
    }
    public void sendMessageByEvent(){
        messengerMenuController.organizerMessageByEvent(welcome.getText(), eventIDs.getText(), content.getText());
    }
    
    public void setMasterSystem(MasterSystem masterSystem){
        this.masterSystem = masterSystem;
        this.messengerMenuController = masterSystem.getMessengerMenuController();
        this.loginMenuController = masterSystem.getLoginMenuController();
        this.programGenerator = masterSystem.getProgramGenerator();
        this.accountHandler = masterSystem.getAccountHandler();
        welcome.setText("Welcome: " + loginMenuController.getCurrUsername() + "!");
    }
}
