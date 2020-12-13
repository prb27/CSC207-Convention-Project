package Presenters;

import Controllers.ConversationMenuController;
import Controllers.LoginMenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

public class SpeakerConversationMenuPresenter {
    @FXML
    private Label welcome;
    @FXML
    private Button goBack;
    @FXML
    private Button signOut;
    @FXML
    private Label description;
    @FXML
    private ListView<Label> messages;
    @FXML
    private Button reply;

    private final LoginMenuController loginMenuController;
    private final ConversationMenuController conversationMenuController;
    private final SceneHandler sceneHandler;

    public SpeakerConversationMenuPresenter(LoginMenuController loginMenuController, ConversationMenuController conversationMenuController, SceneHandler sceneHandler){
        this.loginMenuController = loginMenuController;
        this.conversationMenuController = conversationMenuController;
        this.sceneHandler = sceneHandler;

    }

    @FXML
    private void initialize(){
        welcome.setText("Welcome: " + loginMenuController.getCurrUsername() + "!");
        description.setText(conversationMenuController.getConversationInformation());
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
        reply.setText("Reply");
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Speaker/SpeakerMessengerMenuView.fxml"));
        Stage stage = (Stage) reply.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }
    private void goBack() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/SpeakerMessagingMenuView.fxml"));
        Stage stage = (Stage) signOut.getScene().getWindow();
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
