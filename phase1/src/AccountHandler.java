/**
 * This class is responsible for creating accounts of all user types
 * with a unique username and password and allowing a user to sign in
 * to the conference using their username and password
 * @author Akshat Ayush
 * @see AttendeeManager
 * @see OrganizerManager
 * @see SpeakerManager
 */
public class AccountHandler {

    AttendeeManager attendeeManager;
    OrganizerManager organizerManager;
    SpeakerManager speakerManager;

    /**
     * A constructor to create an object of AccountHandler
     *
     * @param attendeeManager: an object of AttendeeManager class
     * @param organizerManager: an object of OrganizerManager class
     * @param speakerManager: an object of SpeakerManager class
     */
    public AccountHandler(AttendeeManager attendeeManager, OrganizerManager organizerManager, SpeakerManager speakerManager) {
        this.attendeeManager = attendeeManager;
        this.organizerManager = organizerManager;
        this.speakerManager = speakerManager;
    }

    /**
     *
     * @param username: username inputted by the user
     * @param password: password inputted by the user
     * @param accountType: account type of the user
     * @return
     */
    public boolean signup(String username, String password, String accountType) {
        switch(accountType) {
            case "attendee":
                return attendeeManager.createAttendee(username, password);
            case "organizer":
                return organizerManager.createOrganizer(username, password);
            case "speaker":
                return speakerManager.createSpeaker(username, password);
            default:
                return false;
        }
    }

    public boolean login(String username, String password, String accountType) {
        switch(accountType) {
            case "attendee":
                if(attendeeManager.getAllAttendees().contains(username))
                    return attendeeManager.checkPassword(username, password);
                return false;
            case "organizer":
                if(organizerManager.getAllOrganizers().contains(username))
                    return organizerManager.checkPassword(username, password);
                return false;
            case "speaker":
                if(speakerManager.getAllSpeakers().contains(username))
                    return speakerManager.checkPassword(username, password);
                return false;
            default:
                return false;
        }
    }

}