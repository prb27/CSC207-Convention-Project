package Controllers;

import Presenters.Interfaces.ILoginMenu;

import java.io.IOException;

public class LoginMenuController {

    private AccountHandler accountHandler;
    private ILoginMenu loginMenu;

    public LoginMenuController(AccountHandler accountHandler){
        this.accountHandler = accountHandler;
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

    public void setLoginMenu(ILoginMenu loginMenu){
        this.loginMenu = loginMenu;
    }
}
