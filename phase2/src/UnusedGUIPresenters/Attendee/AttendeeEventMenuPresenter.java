package UnusedGUIPresenters.Attendee;

import Controllers.AttendeeEventMenuController;
import Controllers.LoginMenuController;
import Controllers.MasterSystem;
import Gateways.ProgramGenerator;
import UnusedGUIPresenters.LoginMenuPresenter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AttendeeEventMenuPresenter {

//    @FXML
//    private Label welcome;
//    @FXML
//    private ListView<HBox> eventsContainer;
//    @FXML
//    private Button signOut;
//    @FXML
//    private Button goBack;
//    @FXML
//    private CheckBox eventsContainerChecker;
//
//    private AttendeeEventMenuController attendeeEventMenuController;
//    private LoginMenuController loginMenuController;
//    private ProgramGenerator programGenerator;
//    private MasterSystem masterSystem;
//
//    public AttendeeEventMenuPresenter() {
//
//    }
//
//    public void setMasterSystem(MasterSystem masterSystem){
//        this.masterSystem = masterSystem;
//        this.attendeeEventMenuController = masterSystem.getAttendeeEventMenuController();
//        this.loginMenuController = masterSystem.getLoginMenuController();
//        this.programGenerator = masterSystem.getProgramGenerator();
//        welcome.setText("Welcome:" + loginMenuController.getCurrUsername() + "!");
//        eventsContainerChecker.setOnAction(event -> {
//            loadEventsContainer();
//        });
//    }
//
//    @FXML
//    private void initialize(){
//
//        signOut.setText("Sign Out");
//        signOut.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
//        signOut.setOnAction(event -> {
//            try {
//                signOut();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//        goBack.setText("Go Back");
//        goBack.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
//        goBack.setOnAction(event -> {
//            try {
//                goBack();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//        eventsContainerChecker.setText("See Events");
//
//
//
//    }
//    private void loadEventsContainer(){
//        if (eventsContainerChecker.isSelected()){
//            eventsContainer.getItems().clear();
//            List<String> attendableEvents = attendeeEventMenuController.seeAttendableEvents(loginMenuController.getCurrUsername());
//            for (String event: attendableEvents) {
//                Label eventLabel = new Label();
//                eventLabel.setText(event);
//                Button attend = new Button("Attend");
//                attend.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
//                attend.setOnAction(event1 -> {
//                    attendeeEventMenuController.addAttendedEvent(loginMenuController.getCurrUsername(), event);
//                    loadAttendedEvents();
//                });
//                HBox hBox = new HBox(eventLabel, attend);
//                eventsContainer.getItems().add(hBox);
//            }
//        }
//        else{
//            loadAttendedEvents();
//        }
//    }
//    private void loadAttendedEvents(){
//        eventsContainer.getItems().clear();
//        List<String> attendingEvents = attendeeEventMenuController.getListOfAllAttendedEvents(loginMenuController.getCurrUsername());
//        for (String event: attendingEvents) {
//            Label eventLabel = new Label();
//            eventLabel.setText(event);
//            Button remove = new Button("Remove");
//            remove.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
//            remove.setOnAction(event1 -> {
//                attendeeEventMenuController.getListOfAllAttendedEvents(loginMenuController.getCurrUsername()).remove(event);
//                loadEventsContainer();
//            });
//            HBox hBox = new HBox(eventLabel, remove);
//            eventsContainer.getItems().add(hBox);
//        }
//    }
//    private void goBack() throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Attendee/AttendeeMenuView.fxml"));
//        Stage stage = (Stage) goBack.getScene().getWindow();
//        Scene scene = new Scene(loader.load());
//        AttendeeMenuPresenter attendeeMenuPresenter = loader.getController();
//        attendeeMenuPresenter.setMasterSystem(masterSystem);
//        stage.setScene(scene);
//    }
//    private void signOut() throws IOException {
//        programGenerator.readToDatabase();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/LoginMenuView.fxml"));
//        Stage stage = (Stage) signOut.getScene().getWindow();
//        Scene scene = new Scene(loader.load());
//        LoginMenuPresenter loginMenuPresenter = loader.getController();
//        loginMenuPresenter.setMasterSystem(masterSystem);
//        stage.setScene(scene);
//    }

}
