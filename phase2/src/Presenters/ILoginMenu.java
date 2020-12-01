package Presenters;

public interface ILoginMenu {

    String getUsername();

    String getPassword();

    void invalidUser();

    void showAttendeeMenu();

    void showOrganizerMenu();

    void showSpeakerMenu();
}
