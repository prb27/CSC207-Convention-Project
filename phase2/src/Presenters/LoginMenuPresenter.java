package Presenters;

import Controllers.LoginMenuController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;


public class LoginMenuPresenter {


    private LoginMenuController loginMenuController;

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
       if(loginMenuController.login(usernameField.getText(), passwordField.getText())){

       }
    }


}
