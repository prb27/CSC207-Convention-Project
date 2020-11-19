import java.util.ArrayList;

/**
 * This class is responsible for keeping track of and enabling proper use of all Organizer objects (all organizers in the conference).
 * This is done by limiting the set of manipulations that can be done to the Organizer objects.
 * The only manipulations/uses that are allowed are:
 *      - creating new Organizer object
 *      - adding other Users to an Organizer's list of contacts
 *      - add a new conversation to an Organizer's list of participating conversations
 *      - get the list of contacts of a given Organizer
 *      - check if a User is a contact of an Organizer
 *      - get a conversation that an Organizer has
 *      - add an event to the list of events an Organizer is attending
 *      - remove an event from the list of events that an Organizer attends
 *      - check if an Organizer is attending an event
 *      - get list of titles of events that an Organizer is attending
 *      - check the password of a given Organizer
 * The class OrganizerManager has:
 *      - a method to check if there is an Organizer object with a given username
 *      - get all Organizer usernames
 * @author Ashwin Karthikeyan
 * @see Organizer
 */
public class OrganizerManager {

    private final ArrayList<Organizer> organizerList;


    public OrganizerManager(){

        organizerList = new ArrayList<>();

    }

    /**
     * if the username </username> already exists, doesn't create a new Organizer object with that </username> and
     * returns false.
     * Else creates an Organizer object and updates the list of all Organizer objects to reflect this creation
     * returns true if an Organizer is created.
     * @param username: the username to be assigned to this possibly new Organizer (param_type: String)
     * @param password: the password to be assigned to this possibly new Organizer (param_type: String)
     * @return boolean
     */
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

    /**
     * returns false if Organizer object with given username </organizerUsername> doesn't exists.
     * returns true if contactUsername is in the contactList of Organizer by
     * the end of the functions execution.
     * @param organizerUsername: the username in the Organzier object that we intend to manipulate (param_type: String)
     * @param contactUsername: the username of the Attendee/Organizer/Speaker Object that we intend to add as a
     *                       contact of Organizer object with username </organizerUsername>
     * @return : false if Organizer object with given username </organizerUsername> doesn't exists.
     *          true if contactUsername is in the contactList of Organizer by
     *          the end of the functions execution.
     */
    public boolean addContact(String organizerUsername, String contactUsername) {

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

    /**
     * If Organizer object with given username </username> doesn't exist, then returns null.
     * Else returns the list of usernames in Organizers contacts.
     * @param username: the username in the Organizer object that we intend to manipulate (param_type: String)
     * @return : null if no Organizer object with username </username> exists.
     *          Else, ArrayList<String> of contacts of Organizer object with username </username>.
     */
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

    /**
     * If Organizer object with given username </organizerUsername> exists, then it adds </conversationID> to that
     * Organizer object's ArrayList of conversation IDs.
     * @param organizerUsername: the username in the Organizer object that we intend to manipulate (param_type: String)
     * @param conversationID: the ID of the conversation that is intended to be added as a conversation with Organizer
     *                      object with username </organizerUsername>
     */
    public void addConversation(String organizerUsername, String conversationID){

        Organizer organizer = getOrganizer(organizerUsername);
        if(organizer != null){
            ArrayList<String> newConversations = organizer.getConversations();
            newConversations.add(conversationID);
            organizer.setConversations(newConversations);
        }
    }

    /**
     * If Organizer object with given username </organizerUsername> exists, then it returns the list of all Strings that
     * Organizer object holds as conversation IDs.
     * @param organizerUsername: the username in the Organizer object that we intend to manipulate (param_type: String)
     * @return : If Organizer with username </organizerUsername> doesn't exist, returns null. Else returns
     *         the IDs of the conversations that the Organizer object with username </organizerUsername> holds
     */
    public ArrayList<String> getConversations(String organizerUsername){

        Organizer organizer = getOrganizer(organizerUsername);
        if(organizer == null){
            return null;
        }
        else{
            return organizer.getConversations();
        }
    }

    /**
     * If Organizer object with given username </organizerUsername> doesn't exist or </organizerPassword>
     * doesn't match with the password of that Organizer object's password, then this returns returns false.
     * Returns true if and only if </organizerPassword> matches with the password of the password of Organizer object
     * with username </organizerUsername>
     * @param organizerUsername: the username in the Organzier object that we intend to manipulate (param_type: String)
     * @param organizerPassword: the string that we need to compare to this Organizer object's password (param_type: String)
     * @return : true if and only if </organizerPassword> matches with the password of the password of Organizer object
     *           with username </organizerUsername>
     */
    public boolean checkPassword(String organizerUsername, String organizerPassword){

        Organizer organizer = getOrganizer(organizerUsername);
        if(organizer == null){
            return false;
        }
        else{
            return organizer.getPassword().equals(organizerPassword);
        }

    }

    /**
     * If Organizer object with given username </organizerUsername> doesn't exist, then this method returns false.
     * Returns true if and only if Organizer object with username </organizerUsername> exists
     * @param organizerUsername: the username in the Organizer object that we intend to manipulate (param_type: String)
     * @return : true if and only if and Organizer object with username </organizerUsername> exists.
     */
    public boolean isOrganizer(String organizerUsername){

        Organizer organizer = getOrganizer(organizerUsername);
        return organizer != null;
    }

    /**
     * If Organizer object with given username </username> exists, then that Organizer object will have
     * </eventTitle> in its list of event titles of events attending by the end of this method's execution.
     * @param username: the username in the Organizer object that we intend to manipulate (param_type: String)
     * @param eventTitle: the title of the event that this Organizer object is newly going to attend (param_type: String)
     */
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

    /**
     * If Organizer object with given username </username> doesn't exist, then method returns "ODE". Else, returns "YES"
     * if </eventTitle> is in the list of event titles of events that the Organizer object is attending, and
     * returns no if the Organizer object doesn't have </eventTitle> in its list of event titles of events attending
     * @param username: the username in the Organizer object that we intend to manipulate (param_type: String)
     * @param eventTitle: the title of the event to check if it is in the list of event titles of
     *                 events that this Organizer object is attending (param_type: String)
     * @return : "ODE" - Organizer doesn't exist
     *           "NO" - Organizer is currently not registered to attend event with title </eventTitle>
     *           "YES" - Organizer is currently registered to attend event with title </eventTitle>
     */
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

    /**
     * If Organizer object with given username </username> exists, then that Organizer object will not have
     * </eventTitle> in its list of event titles of events attending by the end of this method's execution.
     * @param username: the username in the Organizer object that we intend to manipulate (param_type: String)
     * @param eventTitle: the title of the event that this Organizer object is not going to attend (param_type: String)
     */
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

    /**
     * if Organizer with username </username> exists, then
     * This method returns the events titles of the events that the Organizer object with username </username> stores.
     * This list of titles is the list of event titles of events that this organizer attends.
     * Else, this method returns null.
     * @param username: the username in the Organizer object that we intend to manipulate (param_type: String)
     * @return : if Organizer object with username </username> exists then
     *          ArrayList<String> of event titles that the Organizer object with username </username> stores.\
     *          Else, null
     */
    public ArrayList<String> getEventsAttending(String username){

        Organizer organizer = getOrganizer(username);
        if(organizer != null) {
            return organizer.getEventsAttending();
        }
        return null;
    }

    /**
     * Returns ArrayList<String> of usernames of all Organizer objects stored in this object of OrganizerManager.
     * This method is intended for use in message related fields.
     * @return : ArrayList<String> of usernames of all Organizer objects stored in this object of OrganizerManager.
     */
    public ArrayList<String> getAllOrganizerIds(){

        ArrayList<String> organizerUsernames = new ArrayList<>();
        for(Organizer organizer: organizerList){
            organizerUsernames.add(organizer.getUserId());
        }
        return organizerUsernames;

    }

}