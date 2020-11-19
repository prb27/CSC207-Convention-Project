import java.util.ArrayList;

/**
 * This class is responsible for keeping track of and enabling proper use of all Organizer objects (all organizers in the conference).
 * This is done by limiting the set of manipulations that can be done to the Organizer objects.
 * The only manipulations that are allowed are:
 *      - creating new Organizer object
 *      - adding other Users to an Organizer's list of contacts
 *      - add a new conversation to an Attendee's list of participating conversations
 *      - get the list of contacts from a given Attendee
 *      - get an Attendee given their username
 *      - get all Attendees
 *      - check the password of a given Attendee
 * @author Khoa Pham
 * @see Attendee
 */
public class OrganizerManager {

    private final ArrayList<Organizer> organizerList;


    public OrganizerManager(){

        organizerList = new ArrayList<>();

    }

    public boolean createOrganizer(String username, String password) {

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

    private Organizer getOrganizer(String username){

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

    public boolean addConversation(String organizerUsername, String conversationID){

        Organizer organizer = getOrganizer(organizerUsername);
        if(organizer == null){
            return false;
        }
        else{
            ArrayList<String> newConversations = organizer.getConversations();
            newConversations.add(conversationID);
            organizer.setConversations(newConversations);
            return true;
        }
    }

    public boolean getConversation(String organizerUsername, String conversationID){

        Organizer organizer = getOrganizer(organizerUsername);
        if(organizer == null){
            return false;
        }
        else{
            ArrayList<String> newConversations = organizer.getConversations();
            newConversations.add(conversationID);
            organizer.setConversations(newConversations);
            return true;
        }
    }

    public boolean checkPassword(String organizerUsernamer, String organizerPassword){

        Organizer organizer = getOrganizer(organizerUsernamer);
        if(organizer == null){
            return false;
        }
        else{
            return organizer.getPassword().equals(organizerPassword);
        }

    }

    public boolean isOrganizer(String organizerUsername){

        Organizer organizer = getOrganizer(organizerUsername);
        return organizer != null;
    }

    public void addAttendingEvent(String username, String eventTitle){

        Organizer organizer = getOrganizer(username);
        if(organizer != null){
            if (isAttending(username, eventTitle).equals("NO")) {
                ArrayList<String> eventsAttending = organizer.getEventsAttending();
                eventsAttending.add(eventTitle);
                organizer.setEventsAttending(eventsAttending);
            }
        }
    }

    public String isAttending(String username, String eventTitle){

        Organizer organizer = getOrganizer(username);
        if(organizer == null){
            return "ODE";
        }
        else {
            for (String event : organizer.getEventsAttending()) {
                if (event.equals(eventTitle)) {
                    return "YES";
                }
            }
        }

        return "NO";

    }

    public void removeAttendingEvent(String username, String eventTitle){

        Organizer organizer = getOrganizer(username);
        if(organizer != null) {
            if (isAttending(username, eventTitle).equals("YES")) {
                ArrayList<String> eventsAttending = organizer.getEventsAttending();
                eventsAttending.remove(eventTitle);
                organizer.setEventsAttending(eventsAttending);
            }
        }

    }

    private ArrayList<Organizer> getAllOrganizers() {

        return organizerList;

    }

    public ArrayList<String> getEventsAttending(String username){

        Organizer organizer = getOrganizer(username);
        return organizer.getEventsAttending();

    }

    public ArrayList<String> getAllOrganizerIds(){

        ArrayList<String> organizerUsernames = new ArrayList<>();
        for(Organizer organizer: organizerList){
            organizerUsernames.add(organizer.getUserId());
        }
        return organizerUsernames;

    }

}