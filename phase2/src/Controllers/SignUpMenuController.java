package Controllers;

import Presenters.ISignUpMenu;
import UseCases.AttendeeManager;


public class SignUpMenuController {
    private AccountHandler accountHandler;
    private ISignUpMenu signUpMenu;

    public SignUpMenuController(AccountHandler accountHandler){
        this.accountHandler = accountHandler;
    }
    public boolean signUp(String username, String password){
        if (!accountHandler.signup(username, password, "attendee")){
            signUpMenu.invalidUser();
        }
        return accountHandler.signup(username, password, "attendee");
    }

    public void setSignUpMenu(ISignUpMenu signUpMenu){
        this.signUpMenu = signUpMenu;
    }


}
