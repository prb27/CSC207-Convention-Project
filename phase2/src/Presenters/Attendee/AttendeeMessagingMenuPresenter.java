package Presenters.Attendee;

import Controllers.AttendeeMessagingDashboardMenuController;
import Controllers.ConversationMenuController;
import Controllers.LoginMenuController;
import Controllers.MasterSystem;
import Gateways.ProgramGenerator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AttendeeMessagingMenuPresenter {
    @FXML
    private ListView<HBox> conversations;
    @FXML
    private Label welcome;
    @FXML
    private Button messenger;
    @FXML
    private Button signOut;
    @FXML
    private Button goBack;

    private final LoginMenuController loginMenuController;
    private final AttendeeMessagingDashboardMenuController attendeeMessagingDashboardMenuController;
    private final ConversationMenuController conversationMenuController;
    private final ProgramGenerator programGenerator;

    public AttendeeMessagingMenuPresenter(MasterSystem masterSystem){
        this.loginMenuController = masterSystem.getLoginMenuController();
        this.attendeeMessagingDashboardMenuController = masterSystem.getAttendeeMessagingDashboardMenuController();
        this.conversationMenuController = masterSystem.getConversationMenuController();
        this.programGenerator = masterSystem.getProgramGenerator();
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

        loadConversations();


    }

    private void loadConversations(){
        conversations.getItems().clear();
        List<String> conversationIDs = attendeeMessagingDashboardMenuController.getConversations(loginMenuController.getCurrUsername());
        for (String conversationID: conversationIDs){
            List<String> recipientsOfConversation = attendeeMessagingDashboardMenuController.getConvoParticipants(conversationID);
            Label count = new Label();
            count.setText("Conversation ID: " + conversationID + ";");
            Label participants = new Label();
            StringBuilder recipients = new StringBuilder();
            for (String recipient: recipientsOfConversation){
                recipients.append(recipient);
                recipients.append(", ");
            }
            participants.setText("Participants: " + recipients);
            Button viewConversation = new Button("View Conversation");
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
        }
    }

    private void viewConversation() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Attendee/AttendeeConversationMenuView.fxml"));
        Stage stage = (Stage) conversations.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }

    private void goToMessenger() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Attendee/AttendeeMessengerMenuView.fxml"));
        Stage stage = (Stage) messenger.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }
    private void goBack() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Attendee/AttendeeMenuView.fxml"));
        Stage stage = (Stage) goBack.getScene().getWindow();
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
