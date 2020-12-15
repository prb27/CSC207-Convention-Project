package Presenters.Attendee;

import Controllers.ConversationMenuController;
import Controllers.LoginMenuController;
import Controllers.MasterSystem;
import Gateways.ProgramGenerator;
import Presenters.SceneHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;


public class AttendeeConversationMenuPresenter {
    @FXML
    private Label welcome;
    @FXML
    private Label description;
    @FXML
    private Button goBack;
    @FXML
    private Button signOut;
    @FXML
    private Button reply;
    @FXML
    private ListView<Label> messages;

    private LoginMenuController loginMenuController;
    private ConversationMenuController conversationMenuController;
    private SceneHandler sceneHandler;
    private ProgramGenerator programGenerator;
    private MasterSystem masterSystem;

    public AttendeeConversationMenuPresenter(){
    }

    public void setMasterSystem(MasterSystem masterSystem){
        this.masterSystem = masterSystem;
        this.loginMenuController = masterSystem.getLoginMenuController();
        this.conversationMenuController = masterSystem.getConversationMenuController();
        this.sceneHandler = masterSystem.getSceneHandler();
        this.programGenerator = masterSystem.getProgramGenerator();
    }

    @FXML
    public void initialize(){
        welcome.setText("Welcome: " + loginMenuController.getCurrUsername() + "!");
        description.setText(conversationMenuController.getCurrentConversationID());
        goBack.setText("Go Back");
        goBack.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
        goBack.setOnAction(event -> {
            try {
                goBack();
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
        loadMessages();
        reply.setText("Sign Out");
        reply.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
        reply.setOnAction(event -> {
            try {
                reply();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        });
    }

    private void loadMessages(){
        String currentConversationID = conversationMenuController.getCurrentConversationID();
        List<String> messages1 = conversationMenuController.orderedMessagesInConvo(currentConversationID);
        for (String message: messages1){
            Label messageLabel = new Label();
            messageLabel.setText(message);
            messages.getItems().add(messageLabel);
        }
    }
    private void reply() throws IOException {
        sceneHandler.storeScene(reply.getScene());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Attendee/AttendeeMessengerMenuView.fxml"));
        Stage stage = (Stage) reply.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }

    private void goBack() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Attendee/AttendeeMessagingMenuView.fxml"));
        Stage stage = (Stage) signOut.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }
    private void signOut() throws IOException {
        programGenerator.readToDatabase();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/LoginMenuView.fxml"));
        Stage stage = (Stage) signOut.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }

}
