
import java.util.ArrayList;

public class OrganizerManager {

    private final ArrayList<Organizer> organizerList;


    public OrganizerManager(){

        organizerList = new ArrayList<>();

    }

    public boolean addOrganizer(String username, String password) {

        for(Organizer organizer: organizerList){
            if(organizer.getUserId().equals(username)){
                return false;
            }
        }
        Organizer newOrganizer = new Organizer(username, password);
        organizerList.add(newOrganizer);
        return true;

    }

    public boolean addContact(String organizerUsername, String contactUsername) {
    /// returns false if organizer doesn't exists. True if contactUsername is in the contactList of Organizer by
    /// by the end of the functions execution.
        Organizer organizer = getOrganizer(organizerUsername);
        if(organizer == null){
            return false;
        }
        else {
            ArrayList<String> newContacts = organizer.getContacts();
            if (!(isContact(organizer, contactUsername))) {
                newContacts.add(contactUsername);
                organizer.setContacts(newContacts);
                return true;
            }
            return true;
        }
    }

    public Organizer getOrganizer(String username){

        for(Organizer organizer: organizerList){
            if(organizer.getUserId().equals(username)) {
                return organizer;
            }
        }

        return null;

    }

    public ArrayList<String> getContactList(String username){
    //     returns null if organizer not found or organizer has no contacts
        Organizer organizer = getOrganizer(username);
        if(organizer == null){
            return null;
        }
        else {
            return(organizer.getContacts());
        }
    }

    private boolean isContact(Organizer organizer, String contactUsername){

        for(String contact: organizer.getContacts()){
            if(contactUsername.equals(contact)) {
                return true;
            }
        }

        return false;

    }

}