package Controllers;

import Presenters.AttendeeMenuPresenter;
import Presenters.OrganizerMenuPresenter;
import Presenters.SignUpMenuPresenter;
import Presenters.SpeakerMenuPresenter;
import javafx.stage.Stage;

public class LoginMenuController {

    private AccountHandler accountHandler;
    private SignUpMenuPresenter signUpMenuPresenter;
    private AttendeeMenuPresenter attendeeMenuPresenter;
    private OrganizerMenuPresenter organizerMenuPresenter;
    private SpeakerMenuPresenter speakerMenuPresenter;


    public LoginMenuController(AccountHandler accountHandler){
        this.accountHandler = accountHandler;
    }


    public boolean login(String username, String password, Stage primaryStage){
        String accountType = accountHandler.login(username, password);

        switch (accountType){
            case "attendee":
            case "organizer":
            case "speaker":

        }
    }
}
