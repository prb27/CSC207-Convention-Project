package Presenters;

import Controllers.AccountHandler;
import Controllers.LoginMenuController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class LoginMenuPresenter{


    private LoginMenuController loginMenuController;
    private AccountHandler accountHandler;
    @FXML
    private TextField usernameField;
    @FXML
    private Label usernameLabel;
    @FXML
    private PasswordField passwordField;
    @FXML
    private HBox Username;
    @FXML
    private HBox Password;
    @FXML
    private Button loginButton;


    @FXML
    private void initialize(){
        //search panel
        loginButton.setText("Login");
        loginButton.setOnAction(event -> callUserMenu());
        loginButton.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");

        loginButton.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                callUserMenu();
            }
        });
    }

    @FXML
    private void callUserMenu(){
        loginMenuController.login(usernameField.getText(), passwordField.getText());
        String accountType = accountHandler.login(usernameField.getText(), passwordField.getText());
        switch (accountType){
            case "attendee":
            case "organizer":
            case "speaker":
        }

       }
    }
