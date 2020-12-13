package Presenters;

import Controllers.LoginMenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ConversationMenuPresenter {
    @FXML
    private Label welcome;
    @FXML
    private Button goBack;
    @FXML
    private Button signOut;
    @FXML
    private Label description;
    @FXML
    private ListView<HBox> messages;

    private final LoginMenuController loginMenuController;

    public ConversationMenuPresenter(LoginMenuController loginMenuController){
        this.loginMenuController = loginMenuController;
    }

    @FXML
    private void initialize(){
        welcome.setText("Welcome: " + loginMenuController.getCurrUsername() + "!");
        description.setText("");
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
    }

    private void loadMessages(){

    }

    private void goBack() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/AttendeeMessagingMenuView.fxml"));
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
