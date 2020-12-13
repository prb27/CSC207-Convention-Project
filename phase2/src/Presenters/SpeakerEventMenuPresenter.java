package Presenters;

import Controllers.MessengerMenuController;
import Controllers.UserEventController;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static javafx.collections.FXCollections.observableArrayList;

public class SpeakerEventMenuPresenter {

    @FXML
    private Button goBack;
    @FXML
    private Button signOut;
    @FXML
    private Label welcome;
    @FXML
    private TableView<String> table;
    @FXML
    private TableColumn<String, String> eventName, eventTime;
    @FXML
    private VBox layout;
    @FXML
    private ListView<String> eventlist;

    private UserEventController userEventController;
    private LoginMenuPresenter loginMenuPresenter;

    public SpeakerEventMenuPresenter(UserEventController userEventController, LoginMenuPresenter loginMenuPresenter){
        this.userEventController = userEventController;
        this.loginMenuPresenter = loginMenuPresenter;
    }
    private void initialize() {
        welcome.setText("Welcome: " + loginMenuPresenter.getUsername() + "!");
        seeListofEvents();


        goBack.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
        goBack.setOnAction(event -> {
            try {
                goBack();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        signOut.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
        signOut.setOnAction(event -> {
            try {
                signOut();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void goBack() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/SpeakerMenuView.fxml"));
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
    private void seeListofEvents(){
        String speakerID = loginMenuPresenter.getUsername();
        //HashMap<String, String>list = userEventController.seeAllEventsForSpeaker(speakerID);

        List<String> events = userEventController.seeListOfEventsForSpeaker(speakerID);

        ObservableList<String> eventslist = FXCollections.observableArrayList(events);
        ListView<String> eventlist = new ListView<String>(eventslist);
        layout.getChildren().addAll(eventlist);

    }

}
