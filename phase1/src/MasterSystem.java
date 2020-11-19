import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MasterSystem {

    private final TextUI ui;
    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private final AccountHandler accountHandler;

    private final Scanner scanner = new Scanner(System.in);

    public MasterSystem() {
        this.ui = new TextUI();
        this.attendeeManager = new AttendeeManager();
        this.organizerManager = new OrganizerManager();
        this.speakerManager = new SpeakerManager();
        this.accountHandler = new AccountHandler(attendeeManager, organizerManager, speakerManager);
    }

//    private String getInput(List<String> validOptions) {
//        String inputString = scanner.nextLine();
//        if(validOptions == null)
//            return inputString;
//        if(!validOptions.contains(inputString))
//            return null;
//        return inputString;
//    }

    public void run() {
        String currentUsername = null;
        String currentPassword = null;
        String currentAccountType = null;
        boolean loggedIn = false;

        while(!loggedIn) {

            String tempUsername;
            String tempPassword;
            String tempAccountType;

            ui.landingmenu();
            String landingOption = scanner.nextLine();

            switch(landingOption) {
                case "1":
                    ui.usernameprompt();
                    tempUsername = scanner.nextLine();
                    ui.passwordprompt();
                    tempPassword = scanner.nextLine();
                    tempAccountType = accountHandler.login(tempUsername, tempPassword);
                    if(tempAccountType != null) {
                        currentUsername = tempUsername;
                        currentPassword = tempPassword;
                        currentAccountType = tempAccountType;
                        loggedIn = true;
                    } else {
                        continue;
                    }
                    break;
                case "2":
                    ui.signupmenu();
                    tempAccountType = scanner.nextLine();
                    ui.usernameprompt();
                    tempUsername = scanner.nextLine();
                    ui.passwordprompt();
                    tempPassword = scanner.nextLine();
                    if(accountHandler.signup(tempUsername, tempPassword, tempAccountType))
                        ui.showPrompt("User created");
                    else
                        continue;
                    break;
            }

            while(loggedIn) {

                switch(currentAccountType) {
                    case "attendee":
                        ui.attendeemenu(currentUsername);
                        break;
                    case "organizer":
                        ui.organizermenu(currentUsername);
                        break;
                    case "speaker":
                        ui.speakermenu(currentUsername);
                        break;
                }

                String option = scanner.nextLine();

                if(option.equals("0")) {
                    loggedIn = false;
                    currentUsername = null;
                    currentPassword = null;
                }

                userCommandHandler(option, currentUsername, currentPassword, currentAccountType);
            }
        }
    }

    private void userCommandHandler(String option, String username, String password, String userType) {
        switch(userType) {
            case "attendee":
                switch(option) {

                }
                break;
            case "organizer":
                break;
            case "speaker":
                break;
        }
    }
}
