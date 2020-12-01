package Presenters;

import Controllers.AccountHandler;
import Controllers.LoginMenuController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class LoginMenuPresenter implements ILoginMenu{


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
        //login panel
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
       }

    @Override
    public String getUsername() {
        return usernameField.getText();
    }

    @Override
    public void setUsername() {

    }

    @Override
    public String getPassword() {
        return passwordField.getText();
    }

    @Override
    public String setPassword() {
        return null;
    }

    @Override
    public void invalidUser() {

    }
}
