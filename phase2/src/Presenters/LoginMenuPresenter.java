package Presenters;

import Controllers.AccountHandler;
import Controllers.LoginMenuController;
import Controllers.MasterSystem;
import Presenters.Admin.AdminMenuPresenter;
import Presenters.Attendee.AttendeeMenuPresenter;
import Presenters.Interfaces.ILoginMenu;
import Presenters.Organizer.OrganizerMenuPresenter;
import Presenters.Speaker.SpeakerMenuPresenter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginMenuPresenter implements ILoginMenu {


    private LoginMenuController loginMenuController;
    private AccountHandler accountHandler;

    @FXML
    public TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button signUpFromLogin;
    private MasterSystem masterSystem;

    public LoginMenuPresenter() {
    }

    @FXML
    private void initialize(){
        //login panel
        loginButton.setText("Login");
        loginButton.setOnAction(event -> {
            try {
                callUserMenu();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        loginButton.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");

        signUpFromLogin.setText("Sign Up");
        signUpFromLogin.setOnAction(event -> {
            try {
                returnToSignUp();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        signUpFromLogin.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");

        signUpFromLogin.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                try {
                    returnToSignUp();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    private void callUserMenu() throws IOException {
        String accountType = accountHandler.login(usernameField.getText(), passwordField.getText());
        if(accountType != null) {
            switch (accountType) {
                case "attendee":
                    loginMenuController.setCurrUsername(usernameField.getText());
                    showAttendeeMenu(usernameField.getText());
                case "organizer":
                    loginMenuController.setCurrUsername(usernameField.getText());
                    showOrganizerMenu(usernameField.getText());
                case "speaker":
                    loginMenuController.setCurrUsername(usernameField.getText());
                    showSpeakerMenu(usernameField.getText());
//                case "admin":
//                    loginMenuController.setCurrUsername(usernameField.getText());
//                    showAdminMenu(usernameField.getText());
            }
        }
    }

    private void returnToSignUp() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/SignUpMenuView.fxml"));
        Stage stage = (Stage) signUpFromLogin.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        SignUpMenuPresenter signUpMenuPresenter = loader.getController();
        signUpMenuPresenter.setMasterSystem(masterSystem);
        stage.setScene(scene);
    }

    public void setMasterSystem(MasterSystem masterSystem){
        this.masterSystem = masterSystem;
        this.loginMenuController = masterSystem.getLoginMenuController();
        this.accountHandler = masterSystem.getAccountHandler();
    }

    @Override
    public String getUsername() {
        return usernameField.getText();
    }

    @Override
    public String getPassword() {
        return passwordField.getText();
    }

    @Override
    public void invalidUser() {

    }


    public void showAttendeeMenu(String username) throws IOException {
        FXMLLoader loader = new FXMLLoader(LoginMenuPresenter.class.getResource("/UI/Attendee/AttendeeMenuView.fxml"));
        Stage stage = (Stage) loginButton.getScene().getWindow();
        Scene attendeeMenuScene = new Scene(loader.load());

        AttendeeMenuPresenter attendeeMenuPresenter = loader.getController();
        attendeeMenuPresenter.setMasterSystem(masterSystem);

        stage.setScene(attendeeMenuScene);
    }



    public void showOrganizerMenu(String username) throws IOException {
        FXMLLoader loader = new FXMLLoader(LoginMenuPresenter.class.getResource("/UI/Organizer/OrganizerMenuView.fxml"));
        Stage stage = (Stage) loginButton.getScene().getWindow();
        Scene organizerMenuScene = new Scene(loader.load());

        OrganizerMenuPresenter organizerMenuPresenter = loader.getController();
        organizerMenuPresenter.setMasterSystem(masterSystem);

        stage.setScene(organizerMenuScene);
    }


    public void showSpeakerMenu(String username) throws IOException {
        FXMLLoader loader = new FXMLLoader(LoginMenuPresenter.class.getResource("/UI/Speaker/SpeakerMenuView.fxml"));
        Stage stage = (Stage) loginButton.getScene().getWindow();
        Scene speakerMenuScene = new Scene(loader.load());

        SpeakerMenuPresenter speakerMenuPresenter = loader.getController();
        speakerMenuPresenter.setMasterSystem(masterSystem);
        stage.setScene(speakerMenuScene);
    }

//    public void showAdminMenu(String username) throws IOException{
//        FXMLLoader loader = new FXMLLoader(LoginMenuPresenter.class.getResource("/UI/Admin/AdminMenuView.fxml"));
//            Stage stage = (Stage) loginButton.getScene().getWindow();
//        Scene speakerMenuScene = new Scene(loader.load());
//
//        AdminMenuPresenter adminMenuPresenter = loader.getController();
//        adminMenuPresenter.setMasterSystem(masterSystem);
//        stage.setScene(speakerMenuScene);
//    }
}
