package Presenters;

import Controllers.AccountHandler;
import Scrap.CurrUsernameInfoFileHandler;
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
    private final AccountHandler accountHandler;
    private final LoginMenuPresenter loginMenuPresenter;
    private final CurrUsernameInfoFileHandler currUsernameInfoFileHandler;

    @FXML
    private TextField createUsername;
    @FXML
    private PasswordField createPassword;
    @FXML
    private Button signUp;
    @FXML
    private Button toLoginFromSignUp;
    @FXML
    private ComboBox<String> accountType;
    @FXML
    private Label accountTypeLabel;



   public SignUpMenuPresenter(SignUpMenuController signUpMenuController, AccountHandler accountHandler, LoginMenuPresenter loginMenuPresenter, CurrUsernameInfoFileHandler currUsernameInfoFileHandler){
       this.signUpMenuController = signUpMenuController;
       this.accountHandler = accountHandler;
       this.loginMenuPresenter = loginMenuPresenter;
       this.currUsernameInfoFileHandler = new CurrUsernameInfoFileHandler();
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
        accountType.getItems().addAll("attendee", "organizer", "speaker");
        privileges();
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
                returnToLogin();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
   }

    private void privileges(){
       if (accountHandler.getAccountType(loginMenuPresenter.getUsername()).equals("organizer")){
           accountType.setDisable(false);
           accountTypeLabel.setDisable(false);
       }
       accountType.setDisable(true);
       accountTypeLabel.setDisable(true);
    }

    @Override
    public void invalidUser() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");
        alert.setHeaderText("Something went wrong");
        alert.setContentText("Please look into it");
        createUsername.clear();
        createPassword.clear();
        createUsername.setPromptText("Username");
        createPassword.setPromptText("Password");

    }
}