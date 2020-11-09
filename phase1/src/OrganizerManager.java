import java.util.ArrayList;

public class OrganizerManager {

    private final ArrayList<Organizer> organizerList = new ArrayList<>();


    public void addOrganizer(String username, String password) {

        for(Organizer organizer: organizerList){
            if(organizer.getUserId().equals(username)){
                System.out.println("Username already exists");
                return;
            }
        }
        Organizer newOrganizer = new Organizer(username, password);
        organizerList.add(newOrganizer);

    }

    public void addContact(String organizerUsername, String contactUsername) {

        Organizer organizer = getOrganizer(organizerUsername);
        if(organizer == null){
            System.out.println("Main User not found");
        }
        else {
            ArrayList<String> newContacts = organizer.getContacts();
            newContacts.add(contactUsername);
            organizer.setContacts(newContacts);
        }
    }

    private Organizer getOrganizer(String username){

        for(Organizer organizer: organizerList){
            if(organizer.getUserId().equals(username)) {
                return organizer;
            }
        }

        return null;

    }

}
