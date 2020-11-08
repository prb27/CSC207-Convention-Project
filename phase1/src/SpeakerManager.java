import java.util.ArrayList;

public class SpeakerManager {
    private ArrayList<Speaker> speakers;

    public void addSpeakers(String username, String password){
        speakers.add(new Speaker(username,password));
    }



}
