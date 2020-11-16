public class AccountHandler {

    AttendeeManager attendeeManager; // = MasterSystem.getAttendeeManager();
    OrganizerManager organizerManager; // = MasterSystem.getOrganizerManager();
    SpeakerManager speakerManager; // = MasterSystem.getSpeakerManager();

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