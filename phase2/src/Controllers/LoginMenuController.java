package Controllers;

import Presenters.Interfaces.ILoginMenu;

import java.io.IOException;

public class LoginMenuController {

    private final AccountHandler accountHandler;
    private ILoginMenu loginMenu;

    private String currUsername;

    public LoginMenuController(AccountHandler accountHandler){
        this.accountHandler = accountHandler;
        this.currUsername = "";

    }

    public void login(String username, String password) throws IOException {
        String accountType = accountHandler.login(username, password);
        if(accountType != null){
            switch (accountType){
                case "attendee":
                    setCurrUsername(username);
                    loginMenu.showAttendeeMenu(username);
                case "organizer":
                    setCurrUsername(username);
                    loginMenu.showOrganizerMenu(username);
                case "speaker":
                    setCurrUsername(username);
                    loginMenu.showSpeakerMenu(username);
            }
        } else {
            loginMenu.invalidUser();
        }
    }

    public void setLoginMenu(ILoginMenu loginMenu){
        this.loginMenu = loginMenu;
    }

    public void setCurrUsername(String username){
        this.currUsername = username;
    }
    public String getCurrUsername(){
        return this.currUsername;
    }
}
