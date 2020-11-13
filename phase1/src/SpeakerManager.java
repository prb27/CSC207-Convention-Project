import java.util.ArrayList;
import java.util.HashMap;

public class SpeakerManager {
    private final ArrayList<Speaker> speakers = new ArrayList<>();

    public void addSpeaker(String username, String password){
        for (Speaker speaker: speakers){
            if (speaker.getUserId().equals(username))
            {
                System.out.println("Invalid username: " + username);
                return;
            }
        }
        speakers.add(new Speaker(username,password));
    }

    public void addContact(String speakerUsername, String otherUsername){

        Speaker speaker = getSpeaker(speakerUsername);
        if (speaker.equals(null)){
            System.out.println("Speaker not found");
            return;
        }
        ArrayList<String> contacts = getContactsForSpeaker(speakerUsername);
        if (!contacts.contains(otherUsername)) {
            contacts.add(otherUsername);
        }
    }

    public void addTalkToListOfTalks(String speakerUsername, String eventName, String eventTime){

        Speaker speaker = getSpeaker(speakerUsername);
        if (speaker.equals(null)){
            System.out.println("Speaker not found");
            return;
        }
        HashMap<String, String> listOfTalks = getListOfTalks(speakerUsername);
        if (!listOfTalks.containsKey(eventName)){
            listOfTalks.put(eventName, eventTime);
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

    public ArrayList<String> getContactsForSpeaker(String username){
        Speaker speaker = getSpeaker(username);
        if (speaker.equals(null)){
            System.out.println("Speaker not found");
            return null;
        }
        return speaker.getContacts();
    }

    public HashMap<String, String> getListOfTalks(String username){
        Speaker speaker = getSpeaker(username);
        if (speaker.equals(null)){
            System.out.println("Speaker not found");
            return null;
        }
        return speaker.getListOfTalks();
    }
}
