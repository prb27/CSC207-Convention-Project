package Presenters;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class OrganizerMenuPresenter {

    @FXML
    public Button createNewAccountButton;

    @FXML
    private Button toEventsFromOrganizer;

    @FXML
    private Button toMessagingFromOrganizer;

    private void initialize(){
        createNewAccountButton.setOnAction(event -> {
            try {
                goToNewAccountMenu();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        
        toEventsFromOrganizer.setOnAction(event -> {
            try {
                goToOrganizerEventsMenu();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        
        toMessagingFromOrganizer.setOnAction(event -> {
            try {
                goToOrganizerMessagingMenu();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void goToOrganizerMessagingMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/OrganizerMessagingMenuView.fxml"));
        Stage stage = (Stage) toMessagingFromOrganizer.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }

    private void goToOrganizerEventsMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/OrganizerEventMenuView.fxml"));
        Stage stage = (Stage) toEventsFromOrganizer.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }

    private void goToNewAccountMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/AccountCreationMenuView.fxml"));
        Stage stage = (Stage) createNewAccountButton.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }


}
