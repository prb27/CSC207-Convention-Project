public class MasterSystem {

    public void run() {
        boolean loggedIn = false;
        String username;
        String password;
        String accountType;
        while(!loggedIn) {
            TextUI.loginScreen();
            username = TextUI.input();
            password = TextUI.input();
            accountType = TextUI.input();
            if(AccountHandler.login(username, password, accountType))
                loggedIn = true;
            else
                TextUI.incorrectLogin();
        }
    }

    public void commandHandler(String command, String menu)
}
