package Presenters;

import Controllers.LoginMenuController;
import Controllers.SignUpMenuController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;


public class SignUpMenuPresenter {

    private SignUpMenuController signUpMenuController;

    @FXML
    private TextField createUsername;
    @FXML
    private PasswordField createPassword;
    @FXML
    private Button signUp;
    @FXML
    private Button loginFromSignUp;

    public SignUpMenuPresenter(){
    }

    @FXML
    private void initialize(){
        signUp.setText("Sign Up");
        signUp.setOnAction(event -> createUser());
        signUp.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");

        signUp.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                createUser();
            }
        });

        loginFromSignUp.setText("Login");
        loginFromSignUp.setOnAction(event -> returnToLogin());
        loginFromSignUp.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
        loginFromSignUp.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                returnToLogin();
            }
        });
    }

    private void returnToLogin(){

    }
    private void createUser(){

        }
}