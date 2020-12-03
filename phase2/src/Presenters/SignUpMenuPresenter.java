package Presenters;

import Controllers.SignUpMenuController;
import Main.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class SignUpMenuPresenter {

    private SignUpMenuController signUpMenuController;

    @FXML
    private TextField createUsername;
    @FXML
    private PasswordField createPassword;
    @FXML
    private Button signUp;
    @FXML
    private Button toLoginFromSignUp;
    @FXML
    private Label signUpWithInvalidUsername;


   public SignUpMenuPresenter(SignUpMenuController signUpMenuController){
       this.signUpMenuController = signUpMenuController;
   }

    @FXML
    private void initialize(){
        signUp.setText("Sign Up");
        signUp.setOnAction(event -> {
            try {
                signUpAttendee();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        signUp.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");

        toLoginFromSignUp.setText("Login");
        toLoginFromSignUp.setOnAction(event -> {
            try {
                returnToLogin();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        toLoginFromSignUp.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
        signUpWithInvalidUsername.setVisible(false);
    }

    public void returnToLogin() throws IOException {
        AnchorPane loginPage = FXMLLoader.load(getClass().getResource("/UI/LoginMenuView.fxml"));
        System.out.println(loginPage);
        Scene loginPageScene = new Scene(loginPage);
        System.out.println(loginPageScene);
        Stage stage = Main.getStage();
        stage.setScene(loginPageScene);
        stage.show();
    }


    private void signUpAttendee() throws IOException {
        if (signUpMenuController.signUp(createUsername.getText(), createPassword.getText())){
            returnToLogin();
        }
        signUpWithInvalidUsername.setVisible(true);
   }
}