package Controllers;

public class LoginMenuController {

    private AccountHandler accountHandler;

    public LoginMenuController(AccountHandler accountHandler){
        this.accountHandler = accountHandler;
    }

    public boolean loggedIn = false;
    public boolean login(String username, String password){
        String accountType = accountHandler.login(username, password);

        if (){
            loggedIn = true;
        }
        return loggedIn;
    }
}
