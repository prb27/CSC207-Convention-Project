package Presenters;

import Controllers.LoginMenuController;
import Controllers.SignUpMenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import Main.Main;


public class SignUpMenuPresenter  {

    private SignUpMenuController signUpMenuController;
    private Main main;

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
        loginFromSignUp.setOnAction(event -> {
            try {
                returnToLogin();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        loginFromSignUp.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
        loginFromSignUp.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                try {
                    returnToLogin();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void returnToLogin() throws Exception {
        FXMLLoader loader = new FXMLLoader(SignUpMenuPresenter.class.getResource("/LoginMenuView.fxml"));
        AnchorPane loginPage = (AnchorPane) loader.load();
        Scene loginPageScene = new Scene(loginPage);

        main.getStage().setScene(loginPageScene);
    }


    private void createUser(){

        }
}