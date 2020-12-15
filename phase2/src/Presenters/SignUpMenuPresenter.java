package Presenters;


import Gateways.ProgramGenerator;

import Controllers.SignUpMenuController;
import Presenters.Interfaces.ISignUpMenu;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;


public class SignUpMenuPresenter implements ISignUpMenu {

    private final SignUpMenuController signUpMenuController;
    private final ProgramGenerator programGenerator;
    @FXML
    private TextField createUsername;
    @FXML
    private PasswordField createPassword;
    @FXML
    private Button signUp;
    @FXML
    private Button toLoginFromSignUp;



   public SignUpMenuPresenter(SignUpMenuController signUpMenuController, ProgramGenerator programGenerator){
       this.signUpMenuController = signUpMenuController;
       this.programGenerator = programGenerator;
   }

    @FXML
    private void initialize(){
        signUp.setText("Sign Up");
        signUp.setOnAction(event -> { signUpAttendee(); });
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

        createUsername.setPromptText("Username");
        createPassword.setPromptText("Password");

    }

    public void returnToLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/LoginMenuView.fxml"));
        Stage stage = (Stage) toLoginFromSignUp.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }


    private void signUpAttendee(){
        if (signUpMenuController.signUp(createUsername.getText(), createPassword.getText())){
            try {
                programGenerator.readToDatabase();
                returnToLogin();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            invalidUser();
        }
   }

    public void invalidUser() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");
        alert.setHeaderText("Username and/or Password is not valid");
        alert.setContentText("Please look into it");
        createUsername.clear();
        createPassword.clear();
        createUsername.setPromptText("Username");
        createPassword.setPromptText("Password");

    }
}