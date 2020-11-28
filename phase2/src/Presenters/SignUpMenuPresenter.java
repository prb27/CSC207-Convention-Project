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
    private Button signUpButton;

    public SignUpMenuPresenter(){

    }
    @FXML
    private void initialize(){
        //search panel
        signUpButton.setText("Sign Up");
//        loginButton.setOnAction(event -> callLoginMenu());
//        loginButton.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
//
//        loginButton.setOnKeyPressed(event -> {
//            if (event.getCode().equals(KeyCode.ENTER)) {
//                callLoginMenu();
//            }
//        });
    }
}