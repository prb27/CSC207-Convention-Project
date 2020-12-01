package Controllers;

import Presenters.*;
import javafx.stage.Stage;

public class LoginMenuController {

    private AccountHandler accountHandler;
    private ILoginMenu loginMenu;

    public LoginMenuController(AccountHandler accountHandler, ILoginMenu loginMenu){
        this.accountHandler = accountHandler;
        this.loginMenu = loginMenu;
    }

    public void login(String username, String password){
        String accountType = accountHandler.login(username, password);
        if(accountType != null){
            switch (accountType){
                case "attendee":
                case "organizer":
                case "speaker":
            }
        } else {
            loginMenu.invalidUser();
        }
    }
}
