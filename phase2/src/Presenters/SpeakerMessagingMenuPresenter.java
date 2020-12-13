package Presenters;

import Controllers.SpeakerMessagingDashboardMenuController;
import Controllers.CurrUsernameInfoFileHandler;
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

    private final CurrUsernameInfoFileHandler currUsernameInfoFileHandler;
    private final SpeakerMessagingDashboardMenuController speakerMessagingDashboardMenuController;

    public SpeakerMessagingMenuPresenter(CurrUsernameInfoFileHandler currUsernameInfoFileHandler, SpeakerMessagingDashboardMenuController speakerMessagingDashboardMenuController){
        this.currUsernameInfoFileHandler = currUsernameInfoFileHandler;
        this.speakerMessagingDashboardMenuController = speakerMessagingDashboardMenuController;
    }


    @FXML
    private void initialize(){
        welcome.setText("Welcome: " + currUsernameInfoFileHandler.getName() + "!");
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

    private void loadConversations(){
        List<String> conversationIDs = speakerMessagingDashboardMenuController.getConversations(currUsernameInfoFileHandler.getName());
        Integer i = 0;
        for (String conversationID: conversationIDs){
            List<String> recipientsOfConversation = speakerMessagingDashboardMenuController.getConvoParticipants(conversationID);
            Label count = new Label();
            count.setText("Conversation Number " + i.toString() + ";");
            Label participants = new Label();
            StringBuilder recipients = new StringBuilder();
            for (String recipient: recipientsOfConversation){
                recipients.append(recipient);
                recipients.append(", ");
            }
            participants.setText("Participants" + recipients);
            Button viewConversation = new Button("View Conversation");

//            viewConversation.setOnAction(event -> {
//                try {
//                    viewConversation();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            });
            HBox hBox = new HBox(count, participants, viewConversation);
            conversations.getItems().add(hBox);
        }
    }


    private void goToMessenger() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/SpeakerMessengerMenuView.fxml"));
        Stage stage = (Stage) messenger.getScene().getWindow();
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

