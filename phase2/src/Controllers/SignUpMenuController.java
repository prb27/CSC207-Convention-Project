package Controllers;

import UseCases.AttendeeManager;


public class SignUpMenuController {
    private AccountHandler accountHandler;

    private AttendeeManager attendeeManager;

    public SignUpMenuController(AccountHandler accountHandler, AttendeeManager attendeeManager){
        this.attendeeManager = attendeeManager;
        this.accountHandler = accountHandler;
    }
    public boolean signUp(String username, String password){

        return accountHandler.signup(username, password, "attendee");

    }



}
