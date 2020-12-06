package Presenters;

import Controllers.LoginMenuController;
import Presenters.Interfaces.ILoginMenu;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginMenuPresenter implements ILoginMenu {


    private LoginMenuController loginMenuController;

    @FXML
    public TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button signUpFromLogin;

    public LoginMenuPresenter(LoginMenuController loginMenuController) {
        this.loginMenuController = loginMenuController;
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

        loginButton.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                try {
                    callUserMenu();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

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
        loginMenuController.login(usernameField.getText(), passwordField.getText());
    }

    private void returnToSignUp() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/SignUpMenuView.fxml"));
        Stage stage = (Stage) signUpFromLogin.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
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

    @Override
    public void showAttendeeMenu(String username) throws IOException {
        FXMLLoader loader = new FXMLLoader(LoginMenuPresenter.class.getResource("/AttendeeMenuView.fxml"));
        Stage stage = (Stage) loginButton.getScene().getWindow();
        Scene attendeeMenuScene = new Scene(loader.load());
        stage.setScene(attendeeMenuScene);
    }

    @Override
    public void showOrganizerMenu(String username) throws IOException {
        FXMLLoader loader = new FXMLLoader(LoginMenuPresenter.class.getResource("/OrganizerMenuView.fxml"));
        Stage stage = (Stage) loginButton.getScene().getWindow();
        Scene organizerMenuScene = new Scene(loader.load());
        stage.setScene(organizerMenuScene);
    }

    @Override
    public void showSpeakerMenu(String username) throws IOException {
        FXMLLoader loader = new FXMLLoader(LoginMenuPresenter.class.getResource("/SpeakerMenuView.fxml"));
        Stage stage = (Stage) loginButton.getScene().getWindow();
        Scene speakerMenuScene = new Scene(loader.load());
        stage.setScene(speakerMenuScene);
    }
}
