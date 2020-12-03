package Controllers;

import Main.Main;
import Presenters.SignUpMenuPresenter;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class SignUpMenuController {
    private AccountHandler accountHandler;


    public boolean signUp(String username, String password){

        return accountHandler.signup(username, password, "attendee");

    }




}
