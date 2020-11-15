public class AccountHandler {

    public boolean signup(String username, String password, String accountType) {
        switch(accountType) {
            case "attendee":
                return AttendeeManager.createAttendee(username, password);
            case "organizer":
                return OrganizerManager.createOrganizer(username, password);
            case "speaker":
                return SpeakerManager.createSpeaker(username, password);
            default:
                return false;
        }
    }

    public boolean signin(String username, String password, String accountType) {
        switch(accountType) {
            case "attendee":
                if(AttendeeManager.getAllAttendees().contains(username))
                    return AttendeeManager.checkPassword(username, password);
                return false;
            case "organizer":
                if(OrganizerManager.getAllOrganizers().contains(username))
                    return OrganizerManager.checkPassword(username, password);
                return false;
            case "speaker":
                if(SpeakerManager.getAllSpeakers().contains(username))
                    return SpeakerManager.checkPassword(username, password);
                return false;
            default:
                return false;
        }
    }

}