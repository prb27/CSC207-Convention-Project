
import java.util.ArrayList;
import java.util.HashMap;

public class SpeakerManager {
    private final ArrayList<Speaker> speakers = new ArrayList<>();

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

    public boolean addContact(String speakerUsername, String otherUsername){

        Speaker speaker = getSpeaker(speakerUsername);
        if (speaker.equals(null)){
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

        Speaker speaker = getSpeaker(speakerUsername);
        if (speaker.equals(null)){
            return false;
        }
        else{
            HashMap<String, String> listOfTalks = getListOfTalks(speakerUsername);
            if (!listOfTalks.containsKey(eventTime)){
                listOfTalks.put(eventTime, eventName);
                speaker.setListOfTalks(listOfTalks);
                return true;
            }
            return true;
        }
    }

    public boolean addConversation(String username, String conversation){
        Speaker speaker = getSpeaker(username);
        if (speaker.equals(null)){
            return false;
        }
        else{
            ArrayList<String> conversations = speaker.getConversations();
            conversations.add(conversation);
            speaker.setConversations(conversations);
            return true;
        }
    }

    public Speaker getSpeaker(String username){
        for (Speaker speaker: speakers){
            if (speaker.getUserId().equals(username)){
                return speaker;
            }
        }
        return null;
    }
    public ArrayList<Speaker> getAllSpeakers(){
        return speakers;
    }

    public ArrayList<String> getContactsForSpeaker(String username){
        Speaker speaker = getSpeaker(username);
        if (speaker.equals(null)){
            return null;
        }
        return speaker.getContacts();
    }

    public HashMap<String, String> getListOfTalks(String username){
        Speaker speaker = getSpeaker(username);
        if (speaker.equals(null)){
            return null;
        }
        return speaker.getListOfTalks();
    }

    public ArrayList<String> getConversations(String username){
        Speaker speaker = getSpeaker(username);
        if(speaker.equals(null)){
            return null;
        }
        return speaker.getConversations();
    }

    public boolean checkPassword(String username, String password){
        Speaker speaker = getSpeaker(username);
        if(speaker.equals(null)){
            return false;
        }
        else{
            return speaker.getPassword().equals(password);
        }
    }

    public boolean isSpeakerFreeAtTime(String username, String time){
        Speaker speaker = getSpeaker(username);
        if(speaker.equals(null)){
            return false;
        }
        else{
            if(!(speaker.getListOfTalks().containsKey(time))){
                return true;
            }
            else{
                return false;
            }
        }
    }

    public boolean isSpeaker(String username){
        if (speakers.contains(username)){
            return true;
        }
        else{
            return false;
        }
    }
}