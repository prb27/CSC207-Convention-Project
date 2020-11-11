import java.lang.reflect.Array;
import java.util.ArrayList;

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

    public void addContactForSpeaker(String speakerUsername, String otherUsername){

        Speaker speaker = getSpeaker(speakerUsername);
        if (speaker.equals(null)){
            System.out.println("Speaker not found");
            return;
        }
        ArrayList<String> contacts = getContacts(speakerUsername);
        if (!contacts.contains(otherUsername)) {
            contacts.add(otherUsername);
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

    public ArrayList<String> getContacts(String username){
        Speaker speaker = getSpeaker(username);
        if (speaker.equals(null)){
            System.out.println("Speaker not found");
            return null;
        }
        return speaker.getContacts();

    }







}
