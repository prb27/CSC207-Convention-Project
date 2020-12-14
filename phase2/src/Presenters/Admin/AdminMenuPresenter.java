package Presenters.Admin;

import Controllers.AdminMenuController;
import Controllers.LoginMenuController;
import Presenters.Interfaces.IAdminMenu;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;


public class AdminMenuPresenter implements IAdminMenu {
    private final LoginMenuController loginMenuController;
    private final AdminMenuController adminMenuController;
    @FXML
    private Label welcome;
    @FXML
    private Button signOut;
    @FXML
    private ListView<HBox> messages;

    public AdminMenuPresenter(LoginMenuController loginMenuController, AdminMenuController adminMenuController){
        this.loginMenuController = loginMenuController;
        this.adminMenuController = adminMenuController;
    }
    @FXML
    private void initialize(){
        welcome.setText("Welcome: " + loginMenuController.getCurrUsername() + "!");
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

    public void loadMessages(){
        messages.getItems().clear();
        List<String> allMessages = adminMenuController.getAllMessages();
        for (String message: allMessages){
            Label messageLabel = new Label();
            messageLabel.setText(message);
            Button delete = new Button("Delete");
            delete.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
            delete.setOnAction(event -> {
                adminMenuController.getAllMessages().remove(message);
                adminMenuController.updateMessages(message);
            });
            HBox hBox = new HBox(messageLabel, delete);
            messages.getItems().add(hBox);


        }
    }

    private void signOut() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/LoginMenuView.fxml"));
        Stage stage = (Stage) signOut.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }
}
