package Presenters;

import Controllers.LoginMenuController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;


public class LoginMenuPresenter implements ILoginMenu{


    private LoginMenuController loginMenuController;

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
        loginButton.setOnAction(event -> callUserMenu());
        loginButton.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");

        signUpFromLogin.setText("Sign Up");
        signUpFromLogin.setOnAction(event -> returnToSignUp());
        signUpFromLogin.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");

        loginButton.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                callUserMenu();
            }
        });

        signUpFromLogin.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                returnToSignUp();
            }
        });
    }

    @FXML
    private void callUserMenu(){
        loginMenuController.login(usernameField.getText(), passwordField.getText());
    }

    private void returnToSignUp(){

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
    public void showAttendeeMenu() {
        
    }

    @Override
    public void showOrganizerMenu() {

    }

    @Override
    public void showSpeakerMenu() {

    }
}
