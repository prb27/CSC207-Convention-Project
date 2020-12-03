package Controllers;

import Presenters.*;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginMenuController {

    private AccountHandler accountHandler;
    private ILoginMenu loginMenu;

    public LoginMenuController(AccountHandler accountHandler, ILoginMenu loginMenu){
        this.accountHandler = accountHandler;
        this.loginMenu = loginMenu;
    }

    public void login(String username, String password) throws IOException {
        String accountType = accountHandler.login(username, password);
        if(accountType != null){
            switch (accountType){
                case "attendee":
                    loginMenu.showAttendeeMenu(username);
                case "organizer":
                    loginMenu.showOrganizerMenu(username);
                case "speaker":
                    loginMenu.showSpeakerMenu(username);
            }
        } else {
            loginMenu.invalidUser();
        }
    }
}
