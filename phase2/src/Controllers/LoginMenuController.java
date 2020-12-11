package Controllers;

import Presenters.Interfaces.ILoginMenu;

import java.io.IOException;

public class LoginMenuController {

    private final AccountHandler accountHandler;
    private ILoginMenu loginMenu;
    private CurrUsernameInfoFileHandler currUsernameInfoFileHandler;

    public LoginMenuController(AccountHandler accountHandler, CurrUsernameInfoFileHandler currUsernameInfoFileHandler){
        this.accountHandler = accountHandler;
        this.currUsernameInfoFileHandler = currUsernameInfoFileHandler;
    }

    public void login(String username, String password) throws IOException {
        String accountType = accountHandler.login(username, password);
        if(accountType != null){
            switch (accountType){
                case "attendee":
                    currUsernameInfoFileHandler.setName(username);
                    loginMenu.showAttendeeMenu(username);
                case "organizer":
                    currUsernameInfoFileHandler.setName(username);
                    loginMenu.showOrganizerMenu(username);
                case "speaker":
                    currUsernameInfoFileHandler.setName(username);
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
