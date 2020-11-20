import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is responsible for keeping track of all Speaker objects (Speakers at the tech-conference)
 * and allowing certain functionality.
 *
 * Responsibilities:
 *  - stores a list of Speaker objects
 *  - creates a new Speaker and adding it to the list of Speaker objects
 *  - adds a contact for the speaker
 *  - adds a conversation to the speakers list of conversations
 *  - gets a list of all contacts for a given Speaker
 *  - gets a list of all talks for a given Speaker
 *  - gets a list of all conversations for a given Speaker
 *  - gets a list of all Speaker usernames
 *  - validates a Speaker's password
 *  - validates a Speaker's availability
 *  - validates if a given username is an actual Speaker username
 *  - removes a talk from a Speaker's list of talks
 *
 * SpeakerManager contains two methods to support this:
 *  - gets a Speaker
 *  - gets a list of all Speakers
 * @author Vladimir Caterov
 * @see Speaker
 */
public class SpeakerManager implements Serializable {

    private final ArrayList<Speaker> speakers;

    /**
     * a constructor that creates a SpeakerManager object that stores a list of all speakers
     */
    public SpeakerManager(){
        speakers = new ArrayList<>();
    }

    /**
     * Creates a Speaker object and adds it to the list of all Speaker objects.
     * @param username
     * @param password
     * @return boolean
     * @see Speaker
     */
    public boolean createSpeaker(String username, String password){
        for (Speaker speaker: speakers){
            if (speaker.getUserId().equals(username))
            {
                return false;
            }
        }
        speakers.add(new Speaker(username,password));
        return true;
    }

    /**
     * Updates the contact information of a Speaker to include a new contact
     * @param speakerUsername
     * @param otherUsername
     * @return boolean
     */
    public boolean addContact(String speakerUsername, String otherUsername){

        Speaker speaker = getSpeaker(speakerUsername);
        if (speaker == null){
            return false;
        }
        else {
            ArrayList<String> contacts = getContactsForSpeaker(speakerUsername);
            if (!contacts.contains(otherUsername)) {
                contacts.add(otherUsername);
                speaker.setContacts(contacts);
                return true;
            }
            return true;
        }
    }

    public boolean addTalkToListOfTalks(String speakerUsername, String eventTime, String eventName){

        HashMap<String, String> newTalk = new HashMap<>();
        newTalk.put(eventTime, eventName);

        Speaker speaker = getSpeaker(speakerUsername);
        if (speaker == null){
            return false;
        }
        else{
            ArrayList<HashMap<String, String>> listOfTalks = getListOfTalks(speakerUsername);
            Boolean addable = true;
            for (HashMap<String, String> talk: listOfTalks){
                if (talk.containsKey(eventTime)){
                    addable = false;
                }
            }
            if(addable){
                listOfTalks.add(newTalk);
                speaker.setListOfTalks(listOfTalks);
            }
            return addable;
        }
    }

    public boolean addConversation(String username, String conversation){
        Speaker speaker = getSpeaker(username);
        if (speaker == null){
            return false;
        }
        else{
            ArrayList<String> conversations = speaker.getConversations();
            conversations.add(conversation);
            speaker.setConversations(conversations);
            return true;
        }
    }

    private Speaker getSpeaker(String username){
        for (Speaker speaker: speakers){
            if (speaker.getUserId().equals(username)){
                return speaker;
            }
        }
        return null;
    }
    private ArrayList<Speaker> getAllSpeakers(){
        return speakers;
    }

    public ArrayList<String> getContactsForSpeaker(String username){
        Speaker speaker = getSpeaker(username);
        if (speaker == null){
            return null;
        }
        return speaker.getContacts();
    }

    public ArrayList<HashMap<String, String>> getListOfTalks(String username){
        Speaker speaker = getSpeaker(username);
        if (speaker == null){
            return null;
        }
        return speaker.getListOfTalks();
    }

    public ArrayList<String> getConversations(String username){
        Speaker speaker = getSpeaker(username);
        if(speaker == null){
            return null;
        }
        return speaker.getConversations();
    }

    public ArrayList<String> getAllSpeakerIds(){
        ArrayList<String> speakerIds = new ArrayList<>();
        for (Speaker speaker: speakers){
            speakerIds.add(speaker.getUserId());
        }
        return speakerIds;
    }

    public boolean checkPassword(String username, String password){
        Speaker speaker = getSpeaker(username);
        if(speaker == null){
            return false;
        }
        else{
            return speaker.getPassword().equals(password);
        }
    }

    public boolean isSpeakerFreeAtTime(String username, String time){
        Speaker speaker = getSpeaker(username);
        if(speaker == null){
            return false;
        }
        else{
            boolean free = true;
            for (HashMap<String, String> talk: speaker.getListOfTalks()){
                if (talk.containsKey(time)) {
                    free = false;
                    break;
                }
            }
            return free;
        }
    }

    /**
     * Validates if username is the id of a Speaker object
     * @param username
     * @return boolean
     */
    public boolean isSpeaker(String username){
        if (getAllSpeakerIds().contains(username)){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * If a talk from a Speaker object's listOfTalks has been successfully removed, returns true.
     * Else returns false.
     *
     * @param speakerUsername
     * @param eventTime
     * @param eventName
     * @return boolean
     */

    public boolean removeTalkFromListOfTalks(String speakerUsername, String eventTime, String eventName){
        HashMap<String, String> selectedTalk = new HashMap<>();
        selectedTalk.put(eventTime, eventName);

        Speaker speaker = getSpeaker(speakerUsername);
        if (speaker == null){
            return false;
        }
        else {
            ArrayList<HashMap<String, String>> listOfTalks = getListOfTalks(speakerUsername);
            if(listOfTalks.contains(selectedTalk)){
                listOfTalks.remove(selectedTalk);
                speaker.setListOfTalks(listOfTalks);
                return true;
            }
            return false;
        }

    }
}
