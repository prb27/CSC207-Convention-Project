package Controllers;

public class LoginMenuController {

    private AccountHandler accountHandler;
    public String login(String username, String password){

        String accountType = accountHandler.login(username, password);
        return accountType; //JUST FILLER
    }
}
