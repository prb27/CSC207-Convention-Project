package Presenters.Speaker;

import Controllers.ConversationMenuController;
import Controllers.LoginMenuController;
import Controllers.SpeakerMessagingDashboardMenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class SpeakerMessagingMenuPresenter {
    @FXML
    private ListView<HBox> conversations;
    @FXML
    private Label welcome;
    @FXML
    private Button messenger;
    @FXML
    private Button signOut;

    private final ConversationMenuController conversationMenuController;
    private final SpeakerMessagingDashboardMenuController speakerMessagingDashboardMenuController;
    private final LoginMenuController loginMenuController;


    public SpeakerMessagingMenuPresenter(LoginMenuController loginMenuController, SpeakerMessagingDashboardMenuController speakerMessagingDashboardMenuController, ConversationMenuController conversationMenuController){
        this.loginMenuController = loginMenuController;
        this.conversationMenuController = conversationMenuController;
        this.speakerMessagingDashboardMenuController = speakerMessagingDashboardMenuController;
    }


    @FXML
    private void initialize(){
        welcome.setText("Welcome: " + loginMenuController.getCurrUsername() + "!");
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
        loadConversations();

    }

    /**
     * Loads all conversations of the user by interacting with conversationmenucontroller
     */
    private void loadConversations(){
        List<String> conversationIDs = speakerMessagingDashboardMenuController.getConversations(loginMenuController.getCurrUsername());
        Integer i = 0;
        for (String conversationID: conversationIDs){
            List<String> recipientsOfConversation = speakerMessagingDashboardMenuController.getConvoParticipants(conversationID);
            Label count = new Label();
            count.setText("Conversation ID " + conversationID + ";");
            Label participants = new Label();
            StringBuilder recipients = new StringBuilder();
            for (String recipient: recipientsOfConversation){
                recipients.append(recipient);
                recipients.append(", ");
            }
            participants.setText("Participants" + recipients);
            Button viewConversation = new Button("View Conversation");
            Integer finalI = i;
            viewConversation.setOnAction(event -> {
                try {
                    conversationMenuController.setCurrentConversationID(conversationID);
                    conversationMenuController.setConversationInformation(participants.getText());
                    viewConversation();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            HBox hBox = new HBox(count, participants, viewConversation);
            hBox.setSpacing(10);
            conversations.getItems().add(hBox);
            i+=1;
        }
    }

    /**
     * Redirects user to speakerconversationmenuview which shows all conversations of user
     * @throws IOException
     */
    private void viewConversation() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Speaker/SpeakerConversationMenuView.fxml"));
        Stage stage = (Stage) conversations.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }

    /**
     * Redirects user back to messengermenuview
     * @throws IOException
     */
    private void goToMessenger() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Speaker/SpeakerMessengerMenuView.fxml"));
        Stage stage = (Stage) messenger.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }

    /**
     * Redirects user to login menu view
     * @throws IOException
     */

    private void signOut() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/LoginMenuView.fxml"));
        Stage stage = (Stage) signOut.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }

}

