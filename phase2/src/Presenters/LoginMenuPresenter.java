package Presenters;

import Controllers.LoginMenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import Main.Main;

import java.io.IOException;

public class LoginMenuPresenter implements ILoginMenu{


    private LoginMenuController loginMenuController;
    private Main main;

    @FXML
    private TextField usernameField;
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
        FXMLLoader loader = new FXMLLoader(LoginMenuPresenter.class.getResource("/SignUpMenuView.fxml"));
        AnchorPane signUpPage = (AnchorPane) loader.load();
        Scene signUpPageScene = new Scene(signUpPage);

        main.getStage().setScene(signUpPageScene);
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
        AnchorPane attendeeMenu = (AnchorPane) loader.load();
        Scene attendeeMenuScene = new Scene(attendeeMenu);

        main.getStage().setScene(attendeeMenuScene);
    }

    @Override
    public void showOrganizerMenu(String username) throws IOException {
        FXMLLoader loader = new FXMLLoader(LoginMenuPresenter.class.getResource("/OrganizerMenuView.fxml"));
        AnchorPane organizerMenu = (AnchorPane) loader.load();
        Scene organizerMenuScene = new Scene(organizerMenu);

        main.getStage().setScene(organizerMenuScene);
    }

    @Override
    public void showSpeakerMenu(String username) throws IOException {
        FXMLLoader loader = new FXMLLoader(LoginMenuPresenter.class.getResource("/SpeakerMenuView.fxml"));
        AnchorPane speakerMenu = (AnchorPane) loader.load();
        Scene speakerMenuScene = new Scene(speakerMenu);

        main.getStage().setScene(speakerMenuScene);
    }
}
